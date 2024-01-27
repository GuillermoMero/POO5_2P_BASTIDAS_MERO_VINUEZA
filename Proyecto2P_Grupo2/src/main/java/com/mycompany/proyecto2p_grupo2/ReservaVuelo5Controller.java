/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyecto2p_grupo2;

import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
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
                ejecutarTarea();
            }
        });
        t.start();
    }
    
    public void ejecutarTarea(){
        Label lblIda = new Label();
        lblIda.setText("Vuelo ida "+fechaIda.getDayOfMonth()+" de "+fechaIda.getMonth().JANUARY); //adio
        lblIda.setStyle("-fx-font-weight: bold;");
        Label lblRutaIda = new Label();
        String origen = vueloIda.getOrigen();
        String destino = vueloIda.getDestino();
        lblRutaIda.setText(origen+" a "+destino);
        lblRutaIda.setStyle("-fx-font-weight: bold;");
        VBox seccionIda = new VBox();
        seccionIda.setPrefWidth(398);
        seccionIda.setPrefHeight(205);
        BorderPane bpVuelos = new BorderPane();
        bpVuelos.setStyle("-fx-border-color: #7B6458;");
        bpVuelos.setPadding(new Insets(20,25,20,25));
        bpVuelos.setPrefWidth(398);
        bpVuelos.setPrefHeight(160);
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
        lblPrecio.setText(String.valueOf(vueloIda.getPrecio()));
        lblPrecio.setStyle("-fx-font-weight: bold;");
        BorderPane.setAlignment(lblPrecio, Pos.CENTER);
        VBox vBottom = new VBox();
        vBottom.setAlignment(Pos.TOP_CENTER);
        HBox hBottom = new HBox();
        hBottom.setAlignment(Pos.BOTTOM_RIGHT);
        Button btnDetalles = new Button();
        btnDetalles.setText("Detalles");
        btnDetalles.setStyle("-fx-font-weight: bold;");
        vBottom.getChildren().addAll(lblPrecio,hBottom);
        bpVuelos.setBottom(vBottom);
        seccionIda.getChildren().addAll(lblIda,lblRutaIda,bpVuelos);
        Platform.runLater(new Runnable(){
            @Override
            public void run(){
                seccionResumenes.getChildren().add(seccionIda);
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
}
