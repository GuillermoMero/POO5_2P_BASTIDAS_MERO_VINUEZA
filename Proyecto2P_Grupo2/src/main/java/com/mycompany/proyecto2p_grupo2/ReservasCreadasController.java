/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyecto2p_grupo2;

import java.net.URL;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
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
        cargarListaReservas();
    }
    
    @FXML
    private ListView<String> listReservas;
    
    private ArrayList<String> a = new ArrayList<>();
    
    public void cargarListaReservas(){
       
        a.add("Vacio");
        a.add("Vacio 2");
        listReservas.getItems().setAll(a);
        Timeline timeline;
        timeline = new Timeline(new KeyFrame(
                Duration.seconds(5),
                event -> actualizarLista()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    
    public void actualizarLista(){
        ArrayList<String> b = new ArrayList<>();
        b.add("Cambio");
        b.add("cambio 2");
        listReservas.getItems().clear();
        listReservas.getItems().addAll(b);
    }
}
