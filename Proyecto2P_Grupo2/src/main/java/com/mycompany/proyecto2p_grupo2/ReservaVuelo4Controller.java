/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyecto2p_grupo2;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.Tarifa;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ReservaVuelo4Controller implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cargarTarifas();
    }    
    
    public static double precioVuelo;
    
    @FXML
    private VBox seccionTarifas;
    
    public void cargarTarifas(){
        Thread t = new Thread(new Runnable(){
            @Override
            public void run(){
                ArrayList<Tarifa> tarifas = Tarifa.leerTarifas();
                Tarifa t1 = tarifas.get(0);
                Tarifa t2 = tarifas.get(1);
                Tarifa t3 = tarifas.get(2);
                System.out.println("Empieza hilo "+Thread.currentThread());
                try{
                    Thread.sleep(1000);
                }catch(InterruptedException i){
                    
                }
                cargarTarifa1(t1);
                System.out.println("Mostrando tarifa 1");
                try{
                    Thread.sleep(1000);
                }catch(InterruptedException i){
                    
                }
                System.out.println("Mostrando tarifa 2");
                cargarTarifa2(t2);
                try{
                    Thread.sleep(1000);
                }catch(InterruptedException i){
                    
                }
                System.out.println("Mostrando tarifa 3");
                cargarTarifa3(t3);
                System.out.println("Termina hilo "+Thread.currentThread());
            }
        });
        t.setName("Mostrar tarifas");
        t.start();
    }
    
    public void cargarTarifa1(Tarifa t){
        VBox vbTarifa1 = new VBox();
        vbTarifa1.setAlignment(Pos.BOTTOM_CENTER);
        vbTarifa1.setStyle("-fx-border-color: #ff9a12;");
        vbTarifa1.setPadding(new Insets(10,0,0,0));
        vbTarifa1.setPrefWidth(367);
        vbTarifa1.setPrefHeight(173);
        Label lblTipo1 = new Label();
        String cadena = t.getTipo()+": "+t.getNombre();
        lblTipo1.setText(cadena.toUpperCase());
        lblTipo1.setStyle("-fx-font-weight: bold;-fx-text-fill: #ff9a12;-fx-font-size:14;");
        HBox hb1A = new HBox();
        hb1A.setAlignment(Pos.CENTER_LEFT);
        hb1A.setSpacing(15);
        hb1A.setPrefWidth(367);
        hb1A.setPrefHeight(31);
        ImageView img1A = new ImageView();
        img1A.setFitWidth(64);
        img1A.setFitHeight(47);
        Label lbl1A = new Label();
        lbl1A.setText(t.getCaracteristicas().get(0));
        hb1A.getChildren().addAll(img1A, lbl1A);
        HBox hb1B = new HBox();
        hb1B.setAlignment(Pos.CENTER_LEFT);
        hb1B.setSpacing(15);
        hb1B.setPrefWidth(367);
        hb1B.setPrefHeight(31);
        ImageView img1B = new ImageView();
        img1B.setFitWidth(64);
        img1B.setFitHeight(47);
        Label lbl1B = new Label();
        lbl1B.setText(t.getCaracteristicas().get(1));
        hb1B.getChildren().addAll(img1B, lbl1B);
        HBox hb1C = new HBox();
        hb1C.setAlignment(Pos.CENTER);
        hb1C.setStyle("-fx-background-color:  #ff9a12");
        hb1C.setPrefWidth(379);
        hb1C.setPrefHeight(45);
        Label lbl1C = new Label();
        double valor = precioVuelo+(precioVuelo*(t.getPorcentaje()*0.01));
        lbl1C.setText(String.format("%.2f", valor));
        lbl1C.setStyle("-fx-text-fill: White; -fx-font-weight: bold;");
        hb1C.getChildren().add(lbl1C);
        vbTarifa1.getChildren().addAll(lblTipo1, hb1A, hb1B, hb1C);
        Platform.runLater(new Runnable(){
            @Override
            public void run(){
                seccionTarifas.getChildren().addAll(vbTarifa1);
                mostrarVuelo3(vbTarifa1, valor);
            }
        });
    }
    
    public void cargarTarifa2(Tarifa t){
        VBox vbTarifa2 = new VBox();
        vbTarifa2.setAlignment(Pos.BOTTOM_CENTER);
        vbTarifa2.setStyle("-fx-border-color:  #e64d27;");
        vbTarifa2.setPadding(new Insets(10,0,0,0));
        vbTarifa2.setPrefWidth(367);
        vbTarifa2.setPrefHeight(267);
        Label lblTipo2 = new Label();
        String cadena = t.getTipo()+": "+t.getNombre();
        lblTipo2.setText(cadena.toUpperCase());
        lblTipo2.setStyle("-fx-font-weight: bold;-fx-text-fill:  #e64d27;-fx-font-size:14;");
        HBox hb2A = new HBox();
        hb2A.setAlignment(Pos.CENTER_LEFT);
        hb2A.setSpacing(15);
        hb2A.setPrefWidth(367);
        hb2A.setPrefHeight(31);
        ImageView img2A = new ImageView();
        img2A.setFitWidth(64);
        img2A.setFitHeight(47);
        Label lbl2A = new Label();
        lbl2A.setText(t.getCaracteristicas().get(0));
        hb2A.getChildren().addAll(img2A, lbl2A);
        HBox hb2B = new HBox();
        hb2B.setAlignment(Pos.CENTER_LEFT);
        hb2B.setSpacing(15);
        hb2B.setPrefWidth(367);
        hb2B.setPrefHeight(31);
        ImageView img2B = new ImageView();
        img2B.setFitWidth(64);
        img2B.setFitHeight(47);
        Label lbl2B = new Label();
        lbl2B.setText(t.getCaracteristicas().get(1));
        hb2B.getChildren().addAll(img2B, lbl2B);
        HBox hb2C = new HBox();
        hb2C.setAlignment(Pos.CENTER_LEFT);
        hb2C.setSpacing(15);
        hb2C.setPrefWidth(367);
        hb2C.setPrefHeight(31);
        ImageView img2C = new ImageView();
        img2C.setFitWidth(64);
        img2C.setFitHeight(47);
        Label lbl2C = new Label();
        lbl2C.setText(t.getCaracteristicas().get(2));
        hb2C.getChildren().addAll(img2C, lbl2C);
        HBox hb2D = new HBox();
        hb2D.setAlignment(Pos.CENTER_LEFT);
        hb2D.setSpacing(15);
        hb2D.setPrefWidth(367);
        hb2D.setPrefHeight(31);
        ImageView img2D = new ImageView();
        img2D.setFitWidth(64);
        img2D.setFitHeight(47);
        Label lbl2D = new Label();
        lbl2D.setText(t.getCaracteristicas().get(3));
        hb2D.getChildren().addAll(img2D, lbl2D);
        HBox hb2E = new HBox();
        hb2E.setAlignment(Pos.CENTER);
        hb2E.setStyle("-fx-background-color:  #e64d27");
        hb2E.setPrefWidth(379);
        hb2E.setPrefHeight(45);
        Label lbl2E = new Label();
        double porcentaje = t.getPorcentaje()*0.01;
        double valor = precioVuelo+(precioVuelo*porcentaje);
        lbl2E.setText(String.format("%.2f", valor));
        lbl2E.setStyle("-fx-text-fill: White; -fx-font-weight: bold;");
        hb2E.getChildren().add(lbl2E);
        vbTarifa2.getChildren().addAll(lblTipo2, hb2A, hb2B, hb2C,hb2D, hb2E);
        Platform.runLater(new Runnable(){
            @Override
            public void run(){
                seccionTarifas.getChildren().addAll(vbTarifa2);
                mostrarVuelo3(vbTarifa2, valor);
            }
        });
    }
    
    public void cargarTarifa3(Tarifa t){
        VBox vbTarifa3 = new VBox();
        vbTarifa3.setAlignment(Pos.BOTTOM_CENTER);
        vbTarifa3.setStyle("-fx-border-color:  #ef37af;");
        vbTarifa3.setPadding(new Insets(10,0,0,0));
        vbTarifa3.setPrefWidth(367);
        vbTarifa3.setPrefHeight(318);
        Label lblTipo3 = new Label();
        String cadena = t.getTipo()+": "+t.getNombre();
        lblTipo3.setText(cadena.toUpperCase());
        lblTipo3.setStyle("-fx-font-weight: bold;-fx-text-fill: #ef37af;-fx-font-size:14;");
        HBox hb3A = new HBox();
        hb3A.setAlignment(Pos.CENTER_LEFT);
        hb3A.setSpacing(15);
        hb3A.setPrefWidth(367);
        hb3A.setPrefHeight(31);
        ImageView img3A = new ImageView();
        img3A.setFitWidth(64);
        img3A.setFitHeight(47);
        Label lbl3A = new Label();
        lbl3A.setText(t.getCaracteristicas().get(0));
        hb3A.getChildren().addAll(img3A, lbl3A);
        HBox hb3B = new HBox();
        hb3B.setAlignment(Pos.CENTER_LEFT);
        hb3B.setSpacing(15);
        hb3B.setPrefWidth(367);
        hb3B.setPrefHeight(31);
        ImageView img3B = new ImageView();
        img3B.setFitWidth(64);
        img3B.setFitHeight(47);
        Label lbl3B = new Label();
        lbl3B.setText(t.getCaracteristicas().get(1));
        hb3B.getChildren().addAll(img3B, lbl3B);
        HBox hb3C = new HBox();
        hb3C.setAlignment(Pos.CENTER_LEFT);
        hb3C.setSpacing(15);
        hb3C.setPrefWidth(367);
        hb3C.setPrefHeight(31);
        ImageView img3C = new ImageView();
        img3C.setFitWidth(64);
        img3C.setFitHeight(47);
        Label lbl3C = new Label();
        lbl3C.setText(t.getCaracteristicas().get(2));
        hb3C.getChildren().addAll(img3C, lbl3C);
        HBox hb3D = new HBox();
        hb3D.setAlignment(Pos.CENTER_LEFT);
        hb3D.setSpacing(15);
        hb3D.setPrefWidth(367);
        hb3D.setPrefHeight(31);
        ImageView img3D = new ImageView();
        img3D.setFitWidth(64);
        img3D.setFitHeight(47);
        Label lbl3D = new Label();
        lbl3D.setText(t.getCaracteristicas().get(3));
        hb3D.getChildren().addAll(img3D, lbl3D);
        HBox hb3E = new HBox();
        hb3E.setAlignment(Pos.CENTER_LEFT);
        hb3E.setSpacing(15);
        hb3E.setPrefWidth(367);
        hb3E.setPrefHeight(31);
        ImageView img3E = new ImageView();
        img3E.setFitWidth(64);
        img3E.setFitHeight(47);
        Label lbl3E = new Label();
        lbl3E.setText(t.getCaracteristicas().get(4));
        hb3E.getChildren().addAll(img3E, lbl3E);
        HBox hb3F = new HBox();
        hb3F.setAlignment(Pos.CENTER);
        hb3F.setStyle("-fx-background-color: #ef37af");
        hb3F.setPrefWidth(379);
        hb3F.setPrefHeight(45);
        Label lbl3F = new Label();
        double porcentaje = t.getPorcentaje()*0.01;
        double valor = precioVuelo+(precioVuelo*porcentaje);
        lbl3F.setText(String.format("%.2f", valor));
        lbl3F.setStyle("-fx-text-fill: White; -fx-font-weight: bold;");
        hb3F.getChildren().add(lbl3F);
        vbTarifa3.getChildren().addAll(lblTipo3, hb3A, hb3B, hb3C,hb3D, hb3E, hb3F);
        Platform.runLater(new Runnable(){
            @Override
            public void run(){
                seccionTarifas.getChildren().addAll(vbTarifa3);
                mostrarVuelo3(vbTarifa3, valor);
            }
        });
    }
    
    public void mostrarVuelo3(VBox v, double valor){
        v.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent m){
                ReservaVuelo5Controller.vueloRegreso.setPrecio(valor);
                Stage s = (Stage) v.getScene().getWindow();
                FXMLLoader fl = new FXMLLoader(Main.class.getResource("ReservaVuelo5.fxml"));
                ReservaVuelo3Controller.origenSeleccionado = ReservaVuelo1Controller.destinoSeleccionado;
                ReservaVuelo3Controller.destinoSeleccionado = ReservaVuelo1Controller.origenSeleccionado;
                Parent rootVuelo5 = null;
                try{
                    rootVuelo5 = fl.load();
                }catch(IOException e){
                    
                }
                Scene scene = new Scene(rootVuelo5);
                s.setScene(scene);
                s.setTitle("Reserva vuelo 5");
                s.show();
            }
        });
    }
}
