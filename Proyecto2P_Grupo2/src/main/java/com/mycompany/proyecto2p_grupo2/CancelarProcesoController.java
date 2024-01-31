/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyecto2p_grupo2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Paula
 */
public class CancelarProcesoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public static Stage stageProcesoPago;
    
    @FXML
    private Button btnSi;
    
    @FXML
    private Button btnNo;
    
    @FXML
    void si(ActionEvent e){
        Stage s = (Stage) btnSi.getScene().getWindow();
        s.close();
        stageProcesoPago.close();
    }
    
    @FXML
    void no(ActionEvent e){
        Stage s = (Stage) btnNo.getScene().getWindow();
        s.close();
    }
}
