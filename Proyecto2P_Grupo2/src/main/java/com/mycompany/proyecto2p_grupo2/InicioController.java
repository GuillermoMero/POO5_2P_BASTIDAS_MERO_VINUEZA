/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyecto2p_grupo2;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

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
    /**
     * Método llamado automáticamente al cargar la vista.
     * Inicializa la visualización de la imagen de inicio.
     * @param url URL no utilizada.
     * @param rb ResourceBundle no utilizado.
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
    TextField txtUsuario;
    
    @FXML
    PasswordField txtContrasenia;
    
    @FXML
    Label lblMensaje;
    
  
    /**
     * Método que se llama cuando se hace clic en el botón de inicio de sesión.
     * Verifica las credenciales ingresadas y carga la siguiente vista si son válidas.
     * @param e Evento de acción generado por el clic del botón.
     */
    
    @FXML
    void iniciarSesion(ActionEvent e){
        String userIngresado = txtUsuario.getText();
        String contraIngresada = txtContrasenia.getText();
        ArrayList<Cliente> clientes = Cliente.leerClientes();
        for(int i=0; i<clientes.size();i++){
            Cliente c = clientes.get(i);
             if(userIngresado.equals(c.getUsuario()) && contraIngresada.equals(c.getContrasenia())){
                Stage s = (Stage)btnInicio.getScene().getWindow();
                ConfirmacionCompraController.cliente = c;
                cargarBienvenidos(c,s); 
                cargarReservasCreadas(c);
            }else{
                lblMensaje.setText("El usuario o contraseña no existe");
            }
        } 
    }
    /**
     * Método que carga la vista de bienvenida con el cliente autenticado.
     * @param c Cliente autenticado.
     * @param s Stage actual.
     */
    
    public void cargarBienvenidos(Cliente c, Stage s){
        FXMLLoader f = new FXMLLoader(Main.class.getResource("Bienvenidos.fxml"));
        Parent root = null;
        try{
            root = f.load();
        }catch(IOException e2){
            
        }
        BienvenidosController bc = f.getController();
        bc.darBienvenida(c.getGenero(),c.getNombre());
        Scene ventanaBienvenida = new Scene(root);
        s.setTitle("Bienvenidos");
        s.setScene(ventanaBienvenida);
        s.show(); 
    }
    
    /**
     * Método que carga la vista de reservas creadas con el cliente autenticado.
     * @param c Cliente autenticado.
     */
    
    public void cargarReservasCreadas(Cliente c){
        Stage s = new Stage();
        FXMLLoader fl = new FXMLLoader(Main.class.getResource("ReservasCreadas.fxml"));
        Parent root = null;
        try{
            root = fl.load();
        }catch(IOException e){
            
        }
        Scene ventanaReservasC = new Scene(root);
        s.setTitle("Reservas Creadas");
        s.setScene(ventanaReservasC);
        s.show();
        
    }
}
