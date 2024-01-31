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
import java.util.Random;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import modelo.Cliente;
import modelo.Reserva;
import modelo.Tarifa;
import modelo.Vuelo;

/**
 * FXML Controller class
 *
 * @author Paula
 */
public class ConfirmacionCompraController implements Initializable {

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         try(FileInputStream input = new FileInputStream("images/Av.png")){
            Image i = new Image(input,360,360,false,false);
            imgAv.setImage(i);
        }catch(IOException e){
            System.out.println("No se encuentra el archivo");
        }
        codigoReserva = generarCodigoReserva();
        cargarLabelReserva();
        mostrarTiempo();
    }
    
    /**
     *
     */
    public static Cliente cliente;

    /**
     *
     */
    public static Vuelo vueloIda;

    /**
     *
     */
    public static Vuelo vueloRegreso;

    /**
     *
     */
    public static LocalDate fechaIda;

    /**
     *
     */
    public static LocalDate fechaRegreso;

    /**
     *
     */
    public static int numPasajeros;

    /**
     *
     */
    public static Tarifa tarifaIda;

    /**
     *
     */
    public static Tarifa tarifaRegreso;

    /**
     *
     */
    public static String codigoReserva;

    /**
     *
     */
    public static double totalPagar;

    /**
     *
     */
    public static int descuento;

    /**
     *
     */
    public static String tipoPago;

    /**
     *
     */
    public static double totalReserva;
    
    @FXML
    private ImageView imgAv;
    
    @FXML
    private Label lblReserva;
    
    @FXML
    private Label lblTiempo;
    
    @FXML
    private Button btnAceptar;
    
    /**
     * Carga el código de reserva en un Label.
     */
    
    public void cargarLabelReserva(){
        lblReserva.setText("Reserva: "+codigoReserva);
    }
    
    /**
     * Genera un código de reserva aleatorio.
     *
     * @return Código de reserva generado.
     */
    
    public String generarCodigoReserva(){
        Random rd = new Random();
        StringBuilder codigo = new StringBuilder();
        for(int i=0;i<6;i++){
            char letra = (char)(rd.nextInt(26)+'A');
            codigo.append(letra);
        }
        return codigo.toString();
    }
    
    /**
     * Inicia un hilo para ejecutar una tarea, muestra información de tiempo y cierra la ventana actual.
     */
    
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
    
    /**
     * Método que ejecuta una tarea de cuenta regresiva para cerrar la ventana.
     */
    
    public void ejecutarTarea(){
        for(int i=0; i<5;i++){
            String tiempo = "Ventana cerrado en "+(5-i)+" segundos...";
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
    
    /**
     * Método que cierra la ventana de detalles de promoción.
     * @param s Instancia de Stage que representa la ventana actual.
     */
    
    public void cerrarVentana(Stage s){
        Platform.runLater(new Runnable(){
            @Override
            public void run(){
                s.close();
            }
        });
    }
    
    /**
     * Maneja el evento cuando se hace clic en el botón "Aceptar".
     * Crea una nueva reserva, la guarda en un archivo y realiza una transacción de pago si es necesario.
     * Cierra la ventana actual.
     *
     * @param event Evento de acción generado por el clic en el botón "Aceptar".
     */
    
    @FXML
    void aceptar(ActionEvent e){
        String fechaI = obtenerFormatoFecha(fechaIda);
        String fechaR = obtenerFormatoFecha(fechaRegreso);
        Reserva r = new Reserva(cliente,vueloIda.getOrigen(),vueloIda.getDestino(),fechaI,fechaR,numPasajeros, vueloIda.getNumVuelo(), vueloRegreso.getNumVuelo(), tarifaIda, tarifaRegreso, codigoReserva);
        Reserva.escribirReservas(r);
        Reserva.serializarReserva(r);
        if(totalPagar != 0.0){
            r.generarTransaccion(totalPagar, tipoPago, descuento, totalReserva);
        }else{
            r.generarTransaccion(totalReserva, tipoPago, descuento, totalReserva);
        }
        Stage s = (Stage)btnAceptar.getScene().getWindow();
        s.close();
    }
    
    /**
     * Convierte una fecha en formato LocalDate a una cadena en formato "dd/MM/yyyy".
     *
     * @param fecha Fecha en formato LocalDate.
     * @return Fecha formateada como una cadena.
     */
    
    public String obtenerFormatoFecha(LocalDate fecha){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaFormateada = fecha.format(formatter);
        return fechaFormateada;
    }
}
