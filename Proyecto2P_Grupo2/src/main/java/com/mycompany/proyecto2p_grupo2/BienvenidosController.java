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
    
    public void darBienvenida(String genero, String nombre){
        if(genero.equals("M")){
            txtBienvenido.setText("Bienvenido");
        }else if(genero.equals("F")){
            txtBienvenido.setText("Bienvenida");
        }
        txtNombre.setText(nombre);
    }
    
    @FXML
    void mostrarPromociones(ActionEvent e){
        Stage s = (Stage)btnPromo.getScene().getWindow();
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
    
    @FXML
    void mostrarReservaVuelo(ActionEvent e){
        
    }
}
