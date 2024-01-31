/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyecto2p_grupo2;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import modelo.CodigoInvalidoException;
import modelo.Destino;
import modelo.Promocion;

/**
 * FXML Controller class
 *
 * @author Paula
 */
public class PagoAController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        verificarTextField();
        verificarRadioEfectivo();
    }  
    
    public static Destino destino;
    
    @FXML
    private VBox seccionMetodo;
    
    @FXML
    private RadioButton rbCredito;
    
    @FXML
    private RadioButton rbEfectivo;
    
    @FXML
    private VBox seccionLabels;
    
    @FXML
    private Label lblPrecio;
    
    @FXML
    private TextField txtCodigo;
    
    @FXML
    private Label lblInvalido;
    
    public void verificarTextField(){
        txtCodigo.setOnKeyTyped(new EventHandler<KeyEvent>(){
            public void handle(KeyEvent e){
                ejecutarTarea();
            }
        });
        
    }
    
     /**
     * Ejecuta una tarea en un hilo separado al escribir en el TextField txtCodigo.
     *
     * @throws CodigoInvalidoException Si el código ingresado no es válido.
     */
    
    public void ejecutarTarea()throws CodigoInvalidoException{
        Thread t = new Thread(new Runnable(){
            @Override
            public void run(){
                System.out.println("Empieza hilo");
                try{
                    verificarException();
                    Platform.runLater(() -> lblInvalido.setText("válido"));
                }catch(CodigoInvalidoException c){
                    System.out.println(c.getMessage());
                    mostrarInvalido();
                }
                System.out.println("Termino hilo");    
            }
        });
        t.start();
    }
    
    /**
     * Verifica si hay excepciones al ejecutar la tarea.
     *
     * @throws CodigoInvalidoException Si el código ingresado no es válido.
     */
    
    public void verificarException() throws CodigoInvalidoException{
        if(!verificarCodigo()){
            throw new CodigoInvalidoException("Codigo ingresado invalido");
        }
    }
    
    /**
     * Muestra "inválido" en la interfaz gráfica en caso de excepción.
     */
    
    public void mostrarInvalido(){
        Platform.runLater(new Runnable(){
            @Override
            public void run(){
                lblInvalido.setText("inválido");
            }
        });
    }
    
    /**
     * Verifica si el código ingresado en el TextField coincide con alguna promoción y destino válido.
     *
     * @return true si el código es válido, false si no lo es.
     */
    
    public boolean verificarCodigo(){
        ArrayList<Promocion> promos = Promocion.leerPromociones();
        ArrayList<Destino> destinos = Destino.leerDestinos();
        for(Promocion p: promos){
            String codigo = txtCodigo.getText();
            if(codigo.equals(p.getCodigo()) && destino.getPais().toUpperCase().equals(p.getPais())){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Configura un evento para el RadioButton rbEfectivo cuando se selecciona.
     * Desactiva la selección del RadioButton rbCredito cuando rbEfectivo se selecciona.
     */
    
    public void verificarRadioEfectivo(){
        rbEfectivo.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                Platform.runLater(()-> rbCredito.setSelected(false));
                
            }
        });
    }
}
