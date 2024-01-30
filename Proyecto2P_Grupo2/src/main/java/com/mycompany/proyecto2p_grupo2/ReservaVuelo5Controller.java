/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyecto2p_grupo2;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import modelo.Tarifa;
import modelo.Vuelo;

/**
 * FXML Controller class
 *
 * @author Paula
 */
public class ReservaVuelo5Controller implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cargarResumen();
    }
    
    public static LocalDate fechaIda;
    public static LocalDate fechaRegreso;
    public static Vuelo vueloIda;
    public static Vuelo vueloRegreso;
    
    @FXML
    private VBox seccionResumenes;
    
    public void cargarResumen(){
        Thread t = new Thread(new Runnable(){
            @Override
            public void run(){
                System.out.println("Empezando hilo "+Thread.currentThread());
                try{
                    Thread.sleep(1000);
                }catch(InterruptedException e){
                    
                }
                cargarIda();
                try{
                    Thread.sleep(1000);
                }catch(InterruptedException e){
                    
                }
                cargarRegreso();
                try{
                    Thread.sleep(1000);
                }catch(InterruptedException e){
                    
                }
                cargarBoton();
                System.out.println("Termina hilo "+Thread.currentThread());
            }
        });
        t.setName("Mostrar resumen de reserva");
        t.start();
    }
    
    public void cargarIda(){
        Label lblIda = new Label();
        String mes = String.valueOf(fechaIda.getMonth());
        lblIda.setText("Vuelo de ida: "+fechaIda.getDayOfMonth()+" de "+mes.toLowerCase()+":"); //adio
        lblIda.setStyle("-fx-font-weight: bold;");
        Label lblRutaIda = new Label();
        String origen = vueloIda.getOrigen();
        String destino = vueloIda.getDestino();
        lblRutaIda.setText(origen+" a "+destino);
        lblRutaIda.setStyle("-fx-font-weight: bold;");
        VBox seccionIda = new VBox();
        seccionIda.setPrefWidth(398);
        seccionIda.setPrefHeight(205);
        seccionIda.setSpacing(10);
        BorderPane bpVuelos = new BorderPane();
        bpVuelos.setPadding(new Insets(20,25,20,25));
        bpVuelos.setPrefWidth(398);
        bpVuelos.setPrefHeight(109);
        bpVuelos.setMinSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        bpVuelos.setMaxSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        Label lblDuracion = new Label();
        Label lblSalida = new Label();
        Label lblLlegada = new Label();
        Label lblPrecio = new Label();
        Line linea = new Line();
        linea.setStyle("-fx-stroke: #7B6458;");
        linea.setEndX(100);
        linea.setEndY(0);
        linea.setStartX(-159.3296356201172);
        linea.setStartY(-0.3289127051830292);
        linea.setScaleX(1);
        linea.setScaleY(1);
        linea.setScaleZ(1);
        linea.setRotationAxis(new Point3D(0,0,1));
        bpVuelos.setCenter(linea);
        lblDuracion.setText(String.valueOf("Duración: "+vueloIda.getDuracion()+" horas"));
        lblDuracion.setStyle("-fx-font-weight: bold;");
        BorderPane.setAlignment(lblDuracion, Pos.CENTER);
        bpVuelos.setTop(lblDuracion);
        lblSalida.setText(vueloIda.getHoraSalida());
        bpVuelos.setLeft(lblSalida);
        lblLlegada.setText(vueloIda.getHoraLlegada());
        bpVuelos.setRight(lblLlegada);
        lblPrecio.setText(String.format("%.2f", vueloIda.getPrecio()));
        lblPrecio.setStyle("-fx-font-weight: bold;");
        BorderPane.setAlignment(lblPrecio, Pos.CENTER);
        Button btnDetalles = new Button();
        btnDetalles.setText("Detalles del vuelo");
        btnDetalles.setStyle("-fx-font-weight: bold; -fx-background-color: #7B6458; -fx-border-radius: 5; -fx-border-color: White; -fx-border-width: 3; -fx-background-radius: 8; -fx-text-fill: White");
        bpVuelos.setBottom(lblPrecio);
        VBox seccionVuelo = new VBox();
        seccionVuelo.setAlignment(Pos.CENTER_RIGHT);
        bpVuelos.setPrefWidth(398);
        bpVuelos.setPrefHeight(140);
        seccionVuelo.setStyle("-fx-border-color: #7B6458;");
        HBox hBoton = new HBox();
        hBoton.setPadding(new Insets(10,20,15,10));
        hBoton.setAlignment(Pos.CENTER_RIGHT);
        hBoton.getChildren().add(btnDetalles);
        seccionVuelo.getChildren().addAll(bpVuelos,hBoton);
        seccionIda.getChildren().addAll(lblIda,lblRutaIda,seccionVuelo);
        Platform.runLater(new Runnable(){
            @Override
            public void run(){
                seccionResumenes.getChildren().add(seccionIda);
                mostrarDetallesVueloIda(vueloIda, btnDetalles);
            }
        });
        
    }
    
    public void cargarRegreso(){
        Label lblIda = new Label();
        String mes = String.valueOf(fechaRegreso.getMonth());
        lblIda.setText("Vuelo de regeso: "+fechaRegreso.getDayOfMonth()+" de "+mes.toLowerCase()+":"); //adio
        lblIda.setStyle("-fx-font-weight: bold;");
        Label lblRutaIda = new Label();
        String origen = vueloRegreso.getOrigen();
        String destino = vueloRegreso.getDestino();
        lblRutaIda.setText(origen+" a "+destino);
        lblRutaIda.setStyle("-fx-font-weight: bold;");
        VBox seccionIda = new VBox();
        seccionIda.setPrefWidth(398);
        seccionIda.setPrefHeight(205);
        seccionIda.setSpacing(10);
        BorderPane bpVuelos = new BorderPane();
        bpVuelos.setPadding(new Insets(20,25,20,25));
        bpVuelos.setPrefWidth(398);
        bpVuelos.setPrefHeight(109);
        bpVuelos.setMinSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        bpVuelos.setMaxSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        Label lblDuracion = new Label();
        Label lblSalida = new Label();
        Label lblLlegada = new Label();
        Label lblPrecio = new Label();
        Line linea = new Line();
        linea.setStyle("-fx-stroke: #7B6458;");
        linea.setEndX(100);
        linea.setEndY(0);
        linea.setStartX(-159.3296356201172);
        linea.setStartY(-0.3289127051830292);
        linea.setScaleX(1);
        linea.setScaleY(1);
        linea.setScaleZ(1);
        linea.setRotationAxis(new Point3D(0,0,1));
        bpVuelos.setCenter(linea);
        lblDuracion.setText(String.valueOf("Duración: "+vueloRegreso.getDuracion()+" horas"));
        lblDuracion.setStyle("-fx-font-weight: bold;");
        BorderPane.setAlignment(lblDuracion, Pos.CENTER);
        bpVuelos.setTop(lblDuracion);
        lblSalida.setText(vueloRegreso.getHoraSalida());
        bpVuelos.setLeft(lblSalida);
        lblLlegada.setText(vueloRegreso.getHoraLlegada());
        bpVuelos.setRight(lblLlegada);
        lblPrecio.setText(String.format("%.2f", vueloRegreso.getPrecio()));
        lblPrecio.setStyle("-fx-font-weight: bold;");
        BorderPane.setAlignment(lblPrecio, Pos.CENTER);
        Button btnDetalles = new Button();
        btnDetalles.setText("Detalles del vuelo");
        btnDetalles.setStyle("-fx-font-weight: bold; -fx-background-color: #7B6458; -fx-border-radius: 5; -fx-border-color: White; -fx-border-width: 3; -fx-background-radius: 8;-fx-text-fill: White;");
        bpVuelos.setBottom(lblPrecio);
        VBox seccionVuelo = new VBox();
        seccionVuelo.setAlignment(Pos.CENTER_RIGHT);
        bpVuelos.setPrefWidth(398);
        bpVuelos.setPrefHeight(140);
        seccionVuelo.setStyle("-fx-border-color: #7B6458;");
        HBox hBoton = new HBox();
        hBoton.setPadding(new Insets(10,20,15,10));
        hBoton.setAlignment(Pos.CENTER_RIGHT);
        hBoton.getChildren().add(btnDetalles);
        seccionVuelo.getChildren().addAll(bpVuelos,hBoton);
        seccionIda.getChildren().addAll(lblIda,lblRutaIda,seccionVuelo);
        Platform.runLater(new Runnable(){
            @Override
            public void run(){
                seccionResumenes.getChildren().add(seccionIda);
                mostrarDetallesVueloRegreso(vueloRegreso, btnDetalles);
            }
        });
        
    }
    
    public void cargarBoton(){
        HBox hTotal = new HBox();
        hTotal.setAlignment(Pos.CENTER_RIGHT);
        Label lblTotal = new Label();
        double total = vueloIda.getPrecio()+vueloRegreso.getPrecio();
        lblTotal.setText("Total de tu reserva: "+String.format("%.2f", total));
        lblTotal.setStyle("-fx-font-weight: bold;");
        hTotal.getChildren().add(lblTotal);
        Button btnContinuar = new Button();
        btnContinuar.setStyle("-fx-font-weight: bold; -fx-background-color: #7B6458; -fx-border-radius: 5; -fx-border-color: White; -fx-border-width: 3; -fx-background-radius: 8;-fx-text-fill: White;");
        btnContinuar.setText("Continuar");
        Platform.runLater(new Runnable(){
            @Override
            public void run(){
                seccionResumenes.getChildren().add(hTotal);
                try{
                    Thread.sleep(1000);
                }catch(InterruptedException e){
                    
                }
                seccionResumenes.getChildren().add(btnContinuar);
            }
        });
    }
    
    public static String obtenerNombreMes(int numeroMes) {
        if (numeroMes >= 1 && numeroMes <= 12) {
            // El método values() devuelve un array con los meses, y usamos el índice (numeroMes - 1) para obtener el mes correcto.
            return Month.values()[numeroMes - 1].toString();
        } else {
            return "Mes no válido";
        }
    }
    
    public void mostrarDetallesVueloIda(Vuelo v, Button btn){
        btn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                Stage s = new Stage();
                FXMLLoader fl = new FXMLLoader(Main.class.getResource("DetallesVueloIda.fxml"));
                Parent rootDetallesVuelo = null;
                try{
                    rootDetallesVuelo = fl.load();
                }catch(IOException i){
                    i.printStackTrace();
                }
                DetallesVueloIdaController.vuelo = v;
                Scene ventanaDV = new Scene(rootDetallesVuelo);
                s.setTitle("Detalles Vuelo ida");
                s.setScene(ventanaDV);
                s.show();
            }
        });
    }
    
    public void mostrarDetallesVueloRegreso(Vuelo v, Button btn){
        btn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                Stage s = new Stage();
                FXMLLoader fl = new FXMLLoader(Main.class.getResource("DetallesVueloRegreso.fxml"));
                Parent rootDetallesVuelo = null;
                try{
                    rootDetallesVuelo = fl.load();
                }catch(IOException i){
                    i.printStackTrace();
                }
                DetallesVueloIdaController.vuelo = v;
                Scene ventanaDV = new Scene(rootDetallesVuelo);
                s.setTitle("Detalles Vuelo regreso");
                s.setScene(ventanaDV);
                s.show();
            }
        });
    }
}
