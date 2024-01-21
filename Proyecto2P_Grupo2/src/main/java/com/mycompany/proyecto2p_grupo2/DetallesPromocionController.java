/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyecto2p_grupo2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class DetallesPromocionController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        mostrarTiempo();
    }    
    
    @FXML
    private Label lblPais;
    
    @FXML
    private Label lblCodigo;
    
    @FXML
    private Label lblDescuento;
    
    @FXML
    private Label lblTiempo;
    
    @FXML
    private Button btnCerrar;
    
    public void cargarLabels(String pais, String codigo, String descuento){
        lblPais.setText(pais);
        lblCodigo.setText(codigo);
        lblDescuento.setText(descuento);
    }
    
    
    
    public void mostrarTiempo(){
        Thread t = new Thread(new Runnable(){
            @Override
            public void run() {
                System.out.println("Empezo hilo "+Thread.currentThread());
                ejecutarTarea();
                Stage s = (Stage)lblTiempo.getScene().getWindow();
                cerrarVentana(s);
                System.out.println("Termina hilo "+Thread.currentThread());
            }
        });
        t.setName("Tiempo de cierre");
        t.setDaemon(true);
        t.start();
        
    }
    
    public void ejecutarTarea(){
        for(int i=0; i<5;i++){
            String tiempo = "Cerrando en "+(5-i)+" segundos...";
            System.out.println(tiempo);
            Platform.runLater(new Runnable(){
                @Override
                public void run(){
                    lblTiempo.setText(tiempo);
                }
            });
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    
    public void cerrarVentana(Stage s){
        Platform.runLater(new Runnable(){
            @Override
            public void run(){
                s.close();
            }
        });
    }
    
    @FXML
    void cerrar(ActionEvent e){
        Stage s = (Stage)btnCerrar.getScene().getWindow();
        s.close();
    }
}
