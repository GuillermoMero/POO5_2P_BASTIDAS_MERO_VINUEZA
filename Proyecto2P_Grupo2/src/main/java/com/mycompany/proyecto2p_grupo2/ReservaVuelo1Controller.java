/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyecto2p_grupo2;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import modelo.Vuelo;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ReservaVuelo1Controller implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cargarComboBox();
    }    
    
    @FXML
    private Label lblTitulo;
    
    @FXML
    private ComboBox<String> cbOrden;
    
    @FXML
    private Label lblOrden;
    
    @FXML
    private VBox rootVuelo1;
    
    public String origenSeleccionado;
    public String destinoSeleccionado;
    
    public void cargarTitulo(String origen, String destino){
        String titulo = "Selecciona tu vuelo "+origen+" - "+destino;
        lblTitulo.setText(titulo);
        seleccionarRuta(origen,destino);
    }
    
    public void seleccionarRuta(String origen, String destino){
        cargarVuelos(origen,destino);
    }
    
    
    public void cargarVuelos(String origen, String destino){
        Thread t = new Thread(new Runnable(){
            @Override
            public void run(){
                ejecutarTarea(origen, destino);
                Vuelo.tipoOrden = cbOrden.getValue();
            }
        });
        t.setName("Vuelos origen - destino");
        t.start();
    }
    
    public void ejecutarTarea(String origen, String destino){
        cbOrden.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent a){
                System.out.println("Empieza hilo "+Thread.currentThread());
                ArrayList<Vuelo> vuelos = Vuelo.leerVuelos();
                Collections.sort(vuelos);
                for(Vuelo v: vuelos){
                    if(origen.equals(v.getOrigen()) && destino.equals(v.getDestino())){
                        System.out.println("Prueba"+1);
                        crearBorderPane(v);
                        try{
                            Thread.sleep(1000);
                        }catch(InterruptedException e){
                            
                        }
                    }else{
                        System.out.println("Algo salio mal");
                    }
                }
                System.out.println("Termina hilo "+Thread.currentThread());
            }
        });
       
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
        lblDuracion.setText(String.valueOf(v.getDuracion()));
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
                lblOrden.setText("");
                rootVuelo1.getChildren().add(bpVuelos);
            }
        });
    }
    
    public void cargarComboBox(){
        cbOrden.getItems().setAll("Precio", "Duración");
    }
    
    //public String elegirOrden(){
      //  return cbOrden.getValue();
    //}
}
