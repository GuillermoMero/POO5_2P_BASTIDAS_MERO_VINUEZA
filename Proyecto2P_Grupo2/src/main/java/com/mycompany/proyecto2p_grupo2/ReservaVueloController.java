/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyecto2p_grupo2;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;
import modelo.DatosNoIngresadosException;
import modelo.Destino;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ReservaVueloController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try{
            cargarOrigenes();
            cargarDestino();
            cargarSpinner();
        }catch(IOException | RuntimeException e){
            
        }
        
    }    
    
    @FXML
    private ComboBox<String> cbOrigenes;
    
    @FXML
    private ComboBox<Destino> cbDestinos;
    
    @FXML
    private DatePicker dpSalida;
    
    @FXML
    private DatePicker dpRegreso;
    
    @FXML
    private Spinner <Integer> spCantidades;
    
    @FXML
    private Button btnBuscar;
    
    @FXML
    private Label lblAviso;
    
    public void cargarOrigenes(){
        ArrayList<String> origenes = new ArrayList<>();
        origenes.add("Guayaquil");
        origenes.add("Quito");
        origenes.add("Cuenca");
        cbOrigenes.getItems().setAll(origenes);
    }
    
    public void cargarDestino() throws IOException{
        ArrayList<Destino> destinos = Destino.leerDestinos();
        cbDestinos.getItems().setAll(destinos);
    }
    
    public void cargarSpinner(){
        SpinnerValueFactory<Integer> valores = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1);
        spCantidades.setValueFactory(valores);
    }
    
    @FXML
    void buscar(ActionEvent e){
        Stage s = (Stage)btnBuscar.getScene().getWindow();
        try{
            mostrarVuelo1(s);
        }catch(DatosNoIngresadosException d){
            System.out.println(d.getMessage());
            lblAviso.setText(d.getMessage());
        }
    }
    
    public void mostrarVuelo1(Stage s) throws DatosNoIngresadosException{
        if(cbOrigenes.getValue()!=null && cbDestinos.getValue()!=null && dpSalida.getValue()!=null && dpRegreso.getValue()!=null){
            FXMLLoader fl = new FXMLLoader(Main.class.getResource("ReservaVuelo1.fxml"));
            Parent root = null;
            try{
                root = fl.load();
            }catch(IOException i){
                
            }
            ReservaVuelo1Controller rc1 = fl.getController();
            rc1.cargarTitulo();
            ReservaVuelo1Controller.origenSeleccionado = cbOrigenes.getValue();
            ReservaVuelo1Controller.destinoSeleccionado = String.valueOf(cbDestinos.getValue());
            ReservaVuelo3Controller.origenSeleccionado = String.valueOf(cbDestinos.getValue());
            ReservaVuelo3Controller.destinoSeleccionado = cbOrigenes.getValue();
            ReservaVuelo5Controller.fechaIda = dpSalida.getValue();
            ReservaVuelo5Controller.fechaRegreso = dpRegreso.getValue();
            rc1.cargarTitulo();
            Scene ventanaVuelo1 = new Scene(root);
            s.setTitle("Reserva Vuelo 1");
            s.setScene(ventanaVuelo1);
            s.show();
        }else{
            throw new DatosNoIngresadosException("Primero ingrese los de datos de reserva");
        }
    }
}
