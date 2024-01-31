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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modelo.Cliente;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class BienvenidosController implements Initializable {

    /**
     * Initializes the controller class.
     */
    /**
     * Método llamado automáticamente al cargar la vista.
     * Inicializa la imagen de bienvenida desde un archivo y realiza otras acciones necesarias.
     * @param url URL no utilizada.
     * @param rb ResourceBundle no utilizado.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try(FileInputStream input = new FileInputStream("src/main/resources/images/imgBienvenido.png")){
            Image i = new Image(input,614,402,false,false);
            imgBienvenido.setImage(i);
        }catch(IOException e){
            System.out.println("No se encuentra el archivo");
        }
       
        // TODO
    }    
    
    @FXML
    private Text txtBienvenido;
    
    @FXML
    private Text txtNombre;
    
    @FXML
    Button btnPromo;
    
    @FXML
    Button btnReserva;
    
    @FXML
    ImageView imgBienvenido;
    
    /**
     * Método que configura el mensaje de bienvenida y el nombre en la vista.
     * @param genero Género del usuario ("M" para masculino, "F" para femenino).
     * @param nombre Nombre del usuario.
     */
    public void darBienvenida(String genero, String nombre){
        if(genero.equals("M")){
            txtBienvenido.setText("Bienvenido");
        }else if(genero.equals("F")){
            txtBienvenido.setText("Bienvenida");
        }
        txtNombre.setText(nombre);
    }
    /**
     * Método que se llama cuando se hace clic en el botón de mostrar promociones.
     * Abre una nueva ventana para mostrar el mapa de promociones.
     * @param e Evento de acción generado por el clic del botón.
     */
    
    @FXML
    void mostrarPromociones(ActionEvent e){
        Stage s = new Stage();
        FXMLLoader fl = new FXMLLoader(Main.class.getResource("MapaPromociones.fxml"));
        Parent rootPromo = null;
        try{
            rootPromo = fl.load();
        }catch (IOException i){
            
        }
        Scene scene = new Scene(rootPromo);
        s.setTitle("Mapa promociones");
        s.setScene(scene);
        s.show();
    }
    
    /**
     * Método que se llama cuando se hace clic en el botón de mostrar Reservar vuelo.
     * Abre una nueva ventana para mostrar las reservas de vuelo.
     * @param e Evento de acción generado por el clic del botón.
     */
    
    @FXML
    void mostrarReservaVuelo(ActionEvent e){
        Stage s = new Stage();
        FXMLLoader fl = new FXMLLoader(Main.class.getResource("ReservaVuelo.fxml"));
        Parent root = null;
        try{
            root = fl.load();
        }catch(IOException i){
            
        }
        Scene ventanaReservaVuelo = new Scene(root);
        s.setTitle("Reserva Vuelo");
        s.setScene(ventanaReservaVuelo);
        s.show();
    }
}
