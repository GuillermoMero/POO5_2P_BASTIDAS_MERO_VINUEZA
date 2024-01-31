/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyecto2p_grupo2;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import modelo.Tarifa;
import modelo.Vuelo;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class DetallesVueloIdaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    /**
     * Inicializa el controlador.
     * @param url La ubicación para resolver rutas relativas de recursos.
     * @param rb Los recursos para la internacionalización.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try(FileInputStream input = new FileInputStream("images/Avi.png")){
            Image i = new Image(input,357,328,false,false);
            imgA.setImage(i);
        }catch(IOException e){
            System.out.println("No se encuentra el archivo");
        }
        cargarLabels();
    } 
    
    public static Vuelo vuelo;
    public static Tarifa tarifa;
    
    @FXML
    private ImageView imgA;
    
    @FXML
    private Label lblNumVuelo;
    
    @FXML
    private Label lblCodigo;
    
    @FXML
    private Label lblTarifa;
    
    @FXML
    private Button btnCerrar;
    
    /**
     * Carga las etiquetas con la información del vuelo y la tarifa.
     */
    
    public void cargarLabels(){
        lblNumVuelo.setText("Vuelo: "+vuelo.getNumVuelo());
        lblCodigo.setText(vuelo.getCodigoAvion());
        lblTarifa.setText("Tarifa "+tarifa.getTipo());
    }
    
    /**
     * Maneja el evento de cerrar la ventana.
     * @param e Evento de acción.
     */
    
    @FXML
    void cerrar(ActionEvent e){
        Stage s = (Stage)btnCerrar.getScene().getWindow();
        s.close();
    }
}
