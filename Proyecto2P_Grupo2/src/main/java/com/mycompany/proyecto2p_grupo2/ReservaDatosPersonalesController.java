/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyecto2p_grupo2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
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
                    
                }
                System.out.println("Termina hilo "+Thread.currentThread());
            }
        });
        t.setName("Datos personales");
        t.start();
    }
    
    public void crearFormulario(){
        VBox seccionFormulario = new VBox();
        seccionFormulario.setPrefWidth(540);
        seccionFormulario.setPrefHeight(165);
        HBox seccionLabel = new HBox();
        seccionLabel.setPadding(new Insets(0,0,0,20));
        seccionLabel.setPrefWidth(540);
        seccionLabel.setPrefHeight(29);
        Label lblPasajero = new Label();
    }
}
