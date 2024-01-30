/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyecto2p_grupo2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ReservaDatosPersonalesController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cargarFormulario();
    }    
    
    public static int numPasajeros;
    
    @FXML
    private VBox seccionDatos;
    
    private TextField txtNombre;
    private TextField txtApellido;
    private TextField txtPasaporte;
    private TextField txtCorreo;
    
    public void cargarFormulario(){
        Thread t = new Thread(new Runnable(){
            @Override
            public void run(){
                System.out.println("Empieza hilo "+Thread.currentThread());
                for(int i=1; i<=numPasajeros; i++){
                    try{
                        Thread.sleep(1000);
                    }catch(InterruptedException e){
                        
                    }
                    crearFormulario(i);
                    
                }
                try{
                    Thread.sleep(1000);
                }catch(InterruptedException e){
                
                }
                cargarBoton();
                System.out.println("Termina hilo "+Thread.currentThread());
            }
        });
        t.setName("Datos personales");
        t.start();
    }
    
    public void crearFormulario(int i){
        VBox seccionFormulario = new VBox();
        seccionFormulario.setPrefWidth(540);
        seccionFormulario.setPrefHeight(165);
        HBox seccionLabel = new HBox();
        seccionLabel.setPadding(new Insets(0,0,0,20));
        seccionLabel.setPrefWidth(540);
        seccionLabel.setPrefHeight(29);
        Label lblPasajero = new Label();
        lblPasajero.setStyle("-fx-font-size: 14; -fx-font-weight: bold;");
        lblPasajero.setText("Pasajero "+i+":");
        seccionLabel.getChildren().add(lblPasajero);
        GridPane gpDatos = new GridPane();
        gpDatos.setStyle("-fx-border-color:#7b6458;");
        gpDatos.setPadding(new Insets(30,20,30,20));
        gpDatos.setPrefWidth(540);
        gpDatos.setPrefHeight(135);
        gpDatos.setVgap(20);
        ColumnConstraints colum0 = new ColumnConstraints();
        colum0.setMinWidth(10);
        colum0.setPrefWidth(73.66666666666667);
        colum0.setMaxWidth(130.33334350585938);
        colum0.setPercentWidth(-1);
        colum0.setHalignment(HPos.CENTER);
        ColumnConstraints colum1 = new ColumnConstraints();
        colum1.setMinWidth(10);
        colum1.setPrefWidth(146.33334859212243);
        colum1.setMaxWidth(146.33334859212243);
        colum1.setPercentWidth(-1);
        ColumnConstraints colum2 = new ColumnConstraints();
        colum2.setMinWidth(4.000055948893248);
        colum2.setPrefWidth(67.66666666666669);
        colum2.setMaxWidth(211.66667683919275);
        colum2.setPercentWidth(-1);
        ColumnConstraints colum3 = new ColumnConstraints();
        colum3.setMinWidth(10);
        colum3.setPrefWidth(74.33335367838544);
        colum3.setMaxWidth(190.33333333333331);
        colum3.setPercentWidth(-1);
        colum3.setHalignment(HPos.CENTER);
        ColumnConstraints colum4 = new ColumnConstraints();
        colum4.setMinWidth(10);
        colum4.setPrefWidth(146.3333129882813);
        colum4.setMaxWidth(221.33335367838544);
        colum4.setPercentWidth(-1);
        gpDatos.getColumnConstraints().addAll(colum0,colum1,colum2,colum3,colum4);
        RowConstraints row0 = new RowConstraints();
        row0.setMinHeight(10);
        row0.setPrefHeight(76.66664632161458);
        row0.setMaxHeight(79.33332443237306);
        row0.setPercentHeight(-1);
        row0.setValignment(VPos.CENTER);
        RowConstraints row1 = new RowConstraints();
        row1.setMinHeight(10);
        row1.setPrefHeight(76.66668701171875);
        row1.setMaxHeight(115.99998092651366);
        row1.setPercentHeight(-1);
        row1.setValignment(VPos.CENTER);
        Label lblNombre = new Label();
        lblNombre.setText("Nombre:");
        lblNombre.setAlignment(Pos.CENTER_LEFT);
        Label lblApellido = new Label();
        lblApellido.setText("Apellido:");
        lblApellido.setAlignment(Pos.CENTER_LEFT);
        Label lblPasaporte = new Label();
        lblPasaporte.setText("Pasaporte:");
        lblPasaporte.setAlignment(Pos.CENTER_LEFT);
        Label lblCorreo = new Label();
        lblCorreo.setText("Correo:");
        TextField txtNombre = new TextField();
        txtNombre.setAlignment(Pos.CENTER_LEFT);
        TextField txtApellido = new TextField();
        txtApellido.setAlignment(Pos.CENTER_LEFT);
        TextField txtPasaporte = new TextField();
        txtPasaporte.setAlignment(Pos.CENTER_LEFT);
        TextField txtCorreo = new TextField();
        txtCorreo.setAlignment(Pos.CENTER_LEFT);
        gpDatos.add(lblNombre, 0, 0);
        GridPane.setHalignment(lblNombre, javafx.geometry.HPos.CENTER);
        gpDatos.add(lblApellido,0,1);
        GridPane.setHalignment(lblApellido, javafx.geometry.HPos.CENTER);
        gpDatos.add(txtNombre, 1, 0);
        GridPane.setHalignment(txtNombre, javafx.geometry.HPos.CENTER);
        gpDatos.add(txtApellido, 1, 1);
        GridPane.setHalignment(txtApellido, javafx.geometry.HPos.CENTER);
        gpDatos.add(lblPasaporte, 3, 0);
        GridPane.setHalignment(lblPasaporte, javafx.geometry.HPos.CENTER);
        gpDatos.add(lblCorreo, 3, 1);
        GridPane.setHalignment(lblCorreo, javafx.geometry.HPos.CENTER);
        gpDatos.add(txtPasaporte, 4, 0);
        GridPane.setHalignment(txtPasaporte, javafx.geometry.HPos.CENTER);
        gpDatos.add(txtCorreo, 4, 1);
        GridPane.setHalignment(txtCorreo, javafx.geometry.HPos.CENTER);
        seccionFormulario.getChildren().addAll(seccionLabel, gpDatos);
        Platform.runLater(new Runnable(){
            @Override
            public void run(){
                seccionDatos.getChildren().add(seccionFormulario);
            }
        });
    }
    
    public void cargarBoton(){
        HBox seccionBoton = new HBox();
        Button btnContinuar = new Button();
        btnContinuar.setText("Continuar");
        btnContinuar.setStyle("-fx-font-weight: bold; -fx-background-color: #7B6458; -fx-border-radius: 5; -fx-border-color: White; -fx-border-width: 3; -fx-background-radius: 8;-fx-text-fill: White;");
        Label lblMensaje = new Label();
        seccionBoton.getChildren().addAll(lblMensaje,btnContinuar);
        Platform.runLater(new Runnable(){
            @Override
            public void run(){
                seccionDatos.getChildren().add(seccionBoton);
                mostrarPago(btnContinuar);
            }
        });
    }
    
    public void mostrarPago(Button btn){
        btn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                Stage s = (Stage) btn.getScene().getWindow();
                FXMLLoader fl = new FXMLLoader(Main.class.getResource("PagoA.fxml"));
                Parent rootPago = null;
                try{
                    rootPago = fl.load();
                }catch(IOException i){
                    i.printStackTrace();
                }
                Scene ventanaPago = new Scene(rootPago);
                s.setTitle("Pago");
                s.setScene(ventanaPago);
                s.show();
            }
        });
    }
    
}
