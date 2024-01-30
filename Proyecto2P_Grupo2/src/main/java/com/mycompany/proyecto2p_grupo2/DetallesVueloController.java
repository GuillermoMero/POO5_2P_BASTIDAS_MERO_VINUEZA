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
import javafx.scene.control.Label;
import javafx.stage.Stage;
import modelo.Tarifa;
import modelo.Vuelo;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class DetallesVueloController implements Initializable {

    /**
     * Initializes the controller class.
     */
    /**
     * Método llamado automáticamente al cargar la vista.
     * Inicializa la visualización de detalles del vuelo.
     * @param url URL no utilizada.
     * @param rb ResourceBundle no utilizado.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cargarLabels();
    }    
    
    public static Vuelo vuelo;
    
    public static Tarifa tarifa;
    
    @FXML
    private Label lblNumVuelo;
    
    @FXML
    private Label lblCodigo;
    
    @FXML
    private Label lblTarifa;
    
    @FXML
    private Button btnCerrar;
    
    /**
     * Método que carga la información del vuelo y tarifa en las etiquetas correspondientes.
     */
    
    public void cargarLabels(){
        lblNumVuelo.setText("Vuelo: "+vuelo.getNumVuelo());
        lblCodigo.setText(vuelo.getCodigoAvion());
        lblTarifa.setText("Tarifa "+tarifa.getTipo());
    }
    
    /**
     * Método que se llama cuando se hace clic en el botón de cerrar.
     * Cierra la ventana de detalles de vuelo.
     * @param e Evento de acción generado por el clic del botón.
     */
    
    @FXML
    void cerrar(ActionEvent e){
        Stage s = (Stage)btnCerrar.getScene().getWindow();
        s.close();
    }
}
