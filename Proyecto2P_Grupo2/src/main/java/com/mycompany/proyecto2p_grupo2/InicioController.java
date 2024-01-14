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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;
import modelo.Cliente;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class InicioController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try(FileInputStream input = new FileInputStream("src/main/resources/images/img1.png")){
            Image i = new Image(input,578,369,false,false);
            imgView.setImage(i);
        }catch(IOException e){
            System.out.println("No se encuentra el archivo");
        }
        
        // TODO
    }
    @FXML
    ImageView imgView;
    @FXML
    AnchorPane anchorImg;
    @FXML
    Button btnInicio;
    
    @FXML
    static TextField txtUsuario;
    
    @FXML
    static PasswordField txtContrasenia;
    
    @FXML
    Label lblMensaje;
    
    static String userIngresado;
    static String contraIngresada;
    static Cliente clienteIngresada;
    
    public static Cliente elegirCliente(){
        Cliente c2;
        userIngresado = txtUsuario.getText();
        contraIngresada = txtContrasenia.getText();
        if(userIngresado.equals(c.getUsuario()) && contraIngresada.equals(c.getContrasenia())){
            
        }
        return null
    }
    @FXML
    public void iniciarSesion(ActionEvent e){
        String userIngresado = txtUsuario.getText();
        String contraIngresada = txtContrasenia.getText();
        Cliente c = Cliente.leerClientes();
        
        if(userIngresado.equals(c.getUsuario()) && contraIngresada.equals(c.getContrasenia())){
            /***btnInicio.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent e){
                    Stage s = (Stage)btnInicio.getScene().getWindow();
                    
                    Pane rootBienvenida = new Pane();
                    VBox v = new  VBox();
                    Label lblBienvenida = new Label();
                    lblBienvenida.setText("Bienvenido "+c.getNombre());
                    Button btnPromo = new Button();
                    btnPromo.setStyle("Bold");
                    btnPromo.setText("Promociones del mes");
                    Button btnReserva = new Button();
                    btnReserva.setStyle("Bold");
                    btnReserva.setText("Reserva tu vuelo");
                    v.getChildren().addAll(lblBienvenida,btnPromo,btnReserva);
                    v.setSpacing(20);
                    rootBienvenida.getChildren().addAll(v);
                    Scene ventanaBienvenida = new Scene(rootBienvenida, 400, 450);
                    s.setTitle("Bienvenidos");
                    s.setScene(ventanaBienvenida);
                    s.show();
                }
            });***/
            Stage s = (Stage)btnInicio.getScene().getWindow();
            FXMLLoader f = new FXMLLoader(Main.class.getResource("Bienvenidos.fxml"));
            Parent root = null;
            try{
                root = f.load();
            }catch(IOException i){
                
            }
            Scene ventanaBienvenida = new Scene(root);
            s.setTitle("Bienvenidos");
            s.setScene(ventanaBienvenida);
            s.show();
        }else{
            lblMensaje.setText("El usuario o contraseña no existe");
        }
    }

    
}
