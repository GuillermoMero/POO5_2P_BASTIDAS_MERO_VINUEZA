/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyecto2p_grupo2;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import modelo.Reserva;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ReservasCreadasController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try(FileInputStream input = new FileInputStream("images/Avion.png")){
            Image i = new Image(input,360,360,false,false);
            imgAvion.setImage(i);
        }catch(IOException e){
            System.out.println("No se encuentra el archivo");
        }
        cargarListaReservas();
    }
    @FXML
    private ImageView imgAvion;
    @FXML
    private ListView<Reserva> listReservas;
    
    /**
     * Carga la lista de reservas con datos iniciales y programa una actualización periódica.
     */
    
    public void cargarListaReservas(){
        Thread t = new Thread(new Runnable(){
            @Override
            public void run(){
                for(int i=0; i<50; i++){
                    try{
                        Thread.sleep(5000);
                    }catch(InterruptedException e){
                        
                    }
                    actualizarLista();
                    System.out.println("Lista reserva creadas actulizada");
                }
            }
        });
        t.start();
    }
    
    /**
     * Actualiza la lista de reservas con nuevos datos.
     */
    
    public void actualizarLista(){
        Platform.runLater(()->{
           ArrayList<Reserva> reservas = Reserva.leerReservas();
            listReservas.getItems().clear();
            listReservas.getItems().setAll(reservas);
        });
    }
}
