/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyecto2p_grupo2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ReservaDatosPersonalesController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public static int numPasajeros;
    
    @FXML
    private VBox seccionDatos;
    
    public void cargarFormulario(){
        Thread t = new Thread(new Runnable(){
            @Override
            public void run(){
                System.out.println("Empieza hilo "+Thread.currentThread());
                for(int i=0; i<numPasajeros; i++){
                    try{
                        Thread.sleep(1000);
                    }catch(InterruptedException e){
                        
                    }
                    crearFormulario(i);
                }
                System.out.println("Termina hilo "+Thread.currentThread());
            }
        });
        t.setName("Datos personales");
        t.start();
    }
    
    public void crearFormulario(int i){
        VBox seccionFormulario = new VBox();
        seccionFormulario.setPrefWidth(540);
        seccionFormulario.setPrefHeight(165);
        HBox seccionLabel = new HBox();
        seccionLabel.setPadding(new Insets(0,0,0,20));
        seccionLabel.setPrefWidth(540);
        seccionLabel.setPrefHeight(29);
        Label lblPasajero = new Label();
        lblPasajero.setStyle("-fx-font-size: 14; -fx-font-weight: bold;");
        lblPasajero.setText("Pasajero "+i+":");
        seccionLabel.getChildren().add(lblPasajero);
        GridPane gpDatos = new GridPane();
        gpDatos.setStyle("-fx-border-color:black;");
        gpDatos.setPadding(new Insets(20,20,20,20));
        gpDatos.setPrefWidth(540);
        gpDatos.setPrefHeight(135);
        gpDatos.getColumnConstraints().get(0).setMinWidth(10);
        gpDatos.getColumnConstraints().get(0).setPrefWidth(73.66666666666667);
        gpDatos.getColumnConstraints().get(0).setMaxWidth(130.33334350585938);
        gpDatos.getColumnConstraints().get(0).setPercentWidth(-1);
        gpDatos.getColumnConstraints().get(1).setMinWidth(10);
        gpDatos.getColumnConstraints().get(1).setPrefWidth(146.33334859212243);
        gpDatos.getColumnConstraints().get(1).setMaxWidth(146.33334859212243);
        gpDatos.getColumnConstraints().get(1).setPercentWidth(-1);
        gpDatos.getColumnConstraints().get(2).setMinWidth(4.000055948893248);
        gpDatos.getColumnConstraints().get(2).setPrefWidth(67.66666666666669);
        gpDatos.getColumnConstraints().get(2).setMaxWidth(211.66667683919275);
        gpDatos.getColumnConstraints().get(2).setPercentWidth(-1);
        gpDatos.getColumnConstraints().get(3).setMinWidth(10);
        gpDatos.getColumnConstraints().get(3).setPrefWidth(74.33335367838544);
        gpDatos.getColumnConstraints().get(3).setMaxWidth(190.33333333333331);
        gpDatos.getColumnConstraints().get(3).setPercentWidth(-1);
        gpDatos.getColumnConstraints().get(4).setMinWidth(10);
        gpDatos.getColumnConstraints().get(4).setPrefWidth(146.3333129882813);
        gpDatos.getColumnConstraints().get(4).setMaxWidth(221.33335367838544);
        gpDatos.getColumnConstraints().get(4).setPercentWidth(-1);
        gpDatos.getRowConstraints().get(0).setPrefHeight(76.66664632161458);
        gpDatos.getRowConstraints().get(0).setMaxHeight(79.33332443237306);
        gpDatos.getRowConstraints().get(1).setPrefHeight(76.66668701171875);
        gpDatos.getRowConstraints().get(1).setMaxHeight(115.99998092651366);
        gpDatos.getColumnConstraints().get(0).setHalignment(HPos.CENTER);
        gpDatos.getColumnConstraints().get(3).setHalignment(HPos.CENTER);
        Label lblNombre = new Label();
        lblNombre.setText("Nombre:");
        Label lblApellido = new Label();
        lblApellido.setText("Apellido:");
        Label lblPasaporte = new Label();
        lblPasaporte.setText("Pasajarte:");
        Label lblCorreo = new Label();
        lblCorreo.setText("Correo:");
        TextField txtNombre = new TextField();
        TextField txtApellido = new TextField();
        TextField txtPasaporte = new TextField();
        TextField txtCorreo = new TextField();
        gpDatos.add(lblNombre, 0, 0);
        gpDatos.add(lblApellido,0,1);
        gpDatos.add(txtNombre, 1, 0);
        gpDatos.add(txtApellido, 1, 1);
        gpDatos.add(lblPasaporte, 3, 0);
        gpDatos.add(lblCorreo, 3, 1);
        gpDatos.add(txtPasaporte, 4, 0);
        gpDatos.add(txtCorreo, 4, 1);
        seccionFormulario.getChildren().addAll(seccionLabel, gpDatos);
        Platform.runLater(new Runnable(){
            @Override
            public void run(){
                seccionDatos.getChildren().add(seccionFormulario);
            }
        });
    }
}