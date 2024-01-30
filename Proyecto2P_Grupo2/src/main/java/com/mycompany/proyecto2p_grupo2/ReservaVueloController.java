/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyecto2p_grupo2;

import java.io.FileInputStream;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
        try(FileInputStream input = new FileInputStream("src/main/resources/images/Avion.png")){
            Image i = new Image(input);
            imgAvi.setFitWidth(94);
            imgAvi.setFitHeight(116);
            imgAvi.setImage(i);
        }catch(IOException e){
            System.out.println("No se encuentra el archivo");
        }
      
        try{
            cargarOrigenes();
            cargarDestino();
            cargarSpinner();
            cambiarIdioma();
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
    
    @FXML
    private ImageView imgAvi;
    
    /**
     * Carga los datos de los origenes en el ComboBox.
     */
    
    public void cargarOrigenes(){
        ArrayList<String> origenes = new ArrayList<>();
        origenes.add("Guayaquil");
        origenes.add("Quito");
        origenes.add("Cuenca");
        cbOrigenes.getItems().setAll(origenes);
    }
    
    /**
     * Carga los destinos desde un archivo en el ComboBox.
     * @throws IOException Si hay un problema al leer el archivo.
     */
    
    public void cargarDestino() throws IOException{
        ArrayList<Destino> destinos = Destino.leerDestinos();
        cbDestinos.getItems().setAll(destinos);
    }
    
    /**
     * Configura el Spinner para seleccionar la cantidad de pasajeros.
     */
    
    public void cargarSpinner(){
        SpinnerValueFactory<Integer> valores = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 1);
        spCantidades.setValueFactory(valores);
    }
    
    /**
     * Cambia el idioma de los DatePickers a español.
     */
    
    public void cambiarIdioma(){
        dpSalida.setConverter(new SpanishDateConverter());
        dpRegreso.setConverter(new SpanishDateConverter());
    }
    
    /**
     * Método invocado cuando se presiona el botón "Buscar".
     * @param e Evento del botón.
     */
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
    
    
    /**
     * Muestra la interfaz de ReservaVuelo1 con los datos seleccionados.
     * @param s Escenario actual.
     * @throws DatosNoIngresadosException Si no se han ingresado todos los datos necesarios.
     */
    public void mostrarVuelo1(Stage s) throws DatosNoIngresadosException{
        if(cbOrigenes.getValue()!=null && cbDestinos.getValue()!=null && dpSalida.getValue()!=null && dpRegreso.getValue()!=null){
            FXMLLoader fl = new FXMLLoader(Main.class.getResource("ReservaVuelo1.fxml"));
            Parent root = null;
            try{
                root = fl.load();
            }catch(IOException i){
                
            }
            ReservaDatosPersonalesController.numPasajeros = spCantidades.getValue();
            ReservaVuelo1Controller rc1 = fl.getController();
            rc1.cargarTitulo();
            PagoAController.destino = cbDestinos.getValue();
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
