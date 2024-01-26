/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyecto2p_grupo2;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import modelo.Vuelo;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ReservaVuelo3Controller implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cargarComboBox();
        cargarVuelos();
        ejecutarTarea();
    }    
    public static String origenSeleccionado;
    public static String destinoSeleccionado;
    
    @FXML
    private Label lblTitulo;
    
    @FXML
    public ComboBox<String> cbOrden;
    
    @FXML
    private VBox rootVuelo1;
    
    @FXML
    private VBox seccionVuelos;
    
    public void cargarTitulo(){
        String titulo = "Selecciona tu vuelo "+origenSeleccionado+" - "+destinoSeleccionado;
        lblTitulo.setText(titulo);
    }
    
    public void cargarVuelos(){
        cbOrden.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent a){
                seccionVuelos.getChildren().clear();
                ejecutarTareaOrdenada();
            }
        }); 
    }
    
    public void ejecutarTarea(){
        Thread t = new Thread(new Runnable(){
            @Override
            public void run(){
                System.out.println("Empieza hilo "+Thread.currentThread());
                ArrayList<Vuelo> vuelos = Vuelo.leerVuelos();
                for(int i=0; i<vuelos.size(); i++){
                    Vuelo v = vuelos.get(i);
                    if(origenSeleccionado.equals(v.getOrigen()) && destinoSeleccionado.equals(v.getDestino())){
                        try{
                            Thread.sleep(1000);
                        }catch(InterruptedException e){
                            
                        }
                        System.out.println("Mostrando vuelo"+(i+1));
                        crearBorderPane(v);
                    }
                }
                System.out.println("Termina hilo "+Thread.currentThread());
            }
        });
        t.setName("Vuelos origen - destino");
        t.start();
    }
    
    public void ejecutarTareaOrdenada(){
        Thread t = new Thread(new Runnable(){
            @Override
            public void run(){
                System.out.println("Empieza hilo "+Thread.currentThread());
                ArrayList<Vuelo> vuelos = Vuelo.leerVuelos();
                Vuelo.tipoOrden = cbOrden.getValue();
                Collections.sort(vuelos);
                for(int i=0; i<vuelos.size(); i++){
                    Vuelo v = vuelos.get(i);
                    if(origenSeleccionado.equals(v.getOrigen()) && destinoSeleccionado.equals(v.getDestino())){
                        try{
                            Thread.sleep(1000);
                        }catch(InterruptedException e){
                            
                        }
                        System.out.println("Mostrando vuelo"+(i+1));
                        crearBorderPane(v);
                    }
                }
                System.out.println("Termina hilo "+Thread.currentThread());
            }
        });
        t.setName("Vuelos origen - destino");
        t.start();
    }
    
    public void crearBorderPane(Vuelo v){
        BorderPane bpVuelos = new BorderPane();
        bpVuelos.setStyle("-fx-border-color: #7B6458;");
        bpVuelos.setPadding(new Insets(20,25,20,25));
        bpVuelos.setPrefWidth(398);
        bpVuelos.setPrefHeight(109);
        bpVuelos.setMinSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        bpVuelos.setMaxSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        Label lblDuracion = new Label();
        Label lblSalida = new Label();
        Label lblLlegada = new Label();
        Label lblPrecio = new Label();
        Line linea = new Line();
        linea.setStyle("-fx-stroke: #7B6458;");
        linea.setEndX(100);
        linea.setEndY(0);
        linea.setStartX(-159.3296356201172);
        linea.setStartY(-0.3289127051830292);
        linea.setScaleX(1);
        linea.setScaleY(1);
        linea.setScaleZ(1);
        linea.setRotationAxis(new Point3D(0,0,1));
        bpVuelos.setCenter(linea);
        lblDuracion.setText(String.valueOf("Duración: "+v.getDuracion()+" horas"));
        lblDuracion.setStyle("-fx-font-weight: bold;");
        BorderPane.setAlignment(lblDuracion, Pos.CENTER);
        bpVuelos.setTop(lblDuracion);
        lblSalida.setText(v.getHoraSalida());
        bpVuelos.setLeft(lblSalida);
        lblLlegada.setText(v.getHoraLlegada());
        bpVuelos.setRight(lblLlegada);
        lblPrecio.setText(String.valueOf(v.getPrecio()));
        lblPrecio.setStyle("-fx-font-weight: bold;");
        BorderPane.setAlignment(lblPrecio, Pos.CENTER);
        bpVuelos.setBottom(lblPrecio);
        Platform.runLater(new Runnable(){
            @Override
            public void run(){
                seccionVuelos.getChildren().add(bpVuelos);
                mostrarReservaVuelo4(bpVuelos, v);
            }
        });
    }
    
    public void cargarComboBox(){
        cbOrden.getItems().setAll("Precio", "Duración");
    }
    
    public void mostrarReservaVuelo4(BorderPane bp, Vuelo v){
        bp.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent m){
                ReservaVuelo4Controller.precioVuelo = v.getPrecio();
                Stage s = (Stage)bp.getScene().getWindow();
                FXMLLoader fl = new FXMLLoader(Main.class.getResource("ReservaVuelo4.fxml"));
                Parent rootVuelo4 = null;
                try{
                    rootVuelo4 = fl.load();
                }catch(IOException e){
                    
                }
                Scene ventanaVuelo4 = new Scene(rootVuelo4);
                s.setScene(ventanaVuelo4);
                s.setTitle("Reserva Vuelo 2");
                s.show();
            }
        });
    }
}
