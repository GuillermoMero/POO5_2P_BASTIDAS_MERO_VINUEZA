/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2p_grupo2;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author LENOVO
 */
public class Main extends Application{
    public static String pathFiles = "src/main/resources/files/";
    private static Scene scene;
    
    @Override
    public void start(Stage s) throws IOException{
        FXMLLoader f = new FXMLLoader(Main.class.getResource("Inicio.fxml"));
        Parent root = f.load();
        scene = new Scene(root);
        s.setTitle("Inicio");
        s.setScene(scene);
        s.show();
    }
    
    public static void main(String [] args){
        launch();
    }
}
