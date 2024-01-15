/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyecto2p_grupo2;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class MapaPromocionesController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try(FileInputStream input = new FileInputStream("src/main/resources/images/mapa.png")){
            Image i = new Image(input,2628,1686,false,false);
            imgMapa.setImage(i);
        }catch(IOException e){
            System.out.println("No se encuentra el archivo");
        }
        
        
    }    
    
    @FXML
    private ImageView imgMapa;
    
}
