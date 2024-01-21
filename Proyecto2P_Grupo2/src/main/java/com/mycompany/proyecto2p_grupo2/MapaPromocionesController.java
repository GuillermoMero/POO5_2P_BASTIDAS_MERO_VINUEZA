/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyecto2p_grupo2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.Promocion;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class MapaPromocionesController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try(FileInputStream input = new FileInputStream("src/main/resources/images/mapa.png")){
            Image i = new Image(input);
            imgMapa.setImage(i);
        }catch(IOException e){
            System.out.println("No se encuentra el archivo");
        }
        mostrarPuntos();
    }    
    
    @FXML
    private ImageView imgMapa;
    
    @FXML
    private Pane seccionMapa;
    
    @FXML
    private AnchorPane root;
    
    public void mostrarPuntos(){
        ArrayList<Promocion> promos = Promocion.leerPromociones();
        Random rd = new Random();
        int numAleatorio = rd.nextInt(10) + 1;
        Thread t = new Thread(new Runnable(){
            @Override
            public void run(){
                System.out.println("Empieza hilo "+Thread.currentThread());
                for(Promocion p: promos){
                    ImageView imgUbi = new ImageView(); 
                    System.out.println("Mostrando promoción en "+p.getPais());
                    cargarImagen(imgUbi,p);
                    try{
                        Thread.sleep(numAleatorio*1000);
                    }catch(InterruptedException e){
                        
                    }
                }
                System.out.println("Termina hilo "+Thread.currentThread());
            }
        });
        t.setName("Promociones");
        t.start();
    }
    
    public void cargarImagen(ImageView img, Promocion p){
        Platform.runLater(new Runnable(){
            @Override
            public void run(){
                Image i = null;
                try(FileInputStream in = new FileInputStream(Main.pathImages+"ubicacion.png")){
                    i = new Image(in);
                    img.setImage(i);
                    img.setFitWidth(25);
                    img.setFitHeight(28);
                    img.setLayoutX(p.getX());
                    img.setLayoutY(p.getY());
                    seccionMapa.getChildren().add(img);
                    mostrarDetalles(img,p);
                }catch(IOException e){
                    System.out.println("No se encuentra el archivo");
                }
            }
        });
    }
    
    public void mostrarDetalles(ImageView i, Promocion p){
        i.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent m){
                Stage s = new Stage();
                FXMLLoader fl = new FXMLLoader(Main.class.getResource("DetallesPromocion.fxml"));
                Parent root = null;
                try{
                    root = fl.load();
                }catch(IOException e){
                    
                }
                DetallesPromocionController dp = fl.getController();
                String descuento = "Descuento: "+String.valueOf(p.getDescuento())+"%";
                dp.cargarLabels(p.getPais(),p.getCodigo(), descuento);
                Scene ventanaDetalles = new Scene(root);
                s.setScene(ventanaDetalles);
                s.setTitle("Detalles Reservas");
                s.show();
            }
        });
    }
    
    
}