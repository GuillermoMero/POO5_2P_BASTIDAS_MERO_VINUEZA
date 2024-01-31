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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.CodigoInvalidoException;
import modelo.DatosIncompletosException;
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
        mostrarPrecioTotal();
        verificarRadioCredito();
    }  
    
    public static Destino destino;
    
    public static double totalPrecio;
    public Label lblNumero; 
    public Label lblFecha;
    public Label lblCVV;
    public TextField txtNumTC;
    public DatePicker dpExpiracion;
    public PasswordField pwCVV;
    
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
    
    @FXML
    private Label lblDescuento;
    
    @FXML
    private Label lblTotal;
    
    @FXML
    private Button btnPagar;
    
    @FXML
    private Button btnCancelar;
    
    public void verificarTextField(){
        txtCodigo.setOnKeyTyped(new EventHandler<KeyEvent>(){
            public void handle(KeyEvent e){
                ejecutarTarea();
            }
        });
        
    }
    
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
    
    public void verificarException() throws CodigoInvalidoException{
        if(!verificarCodigo()){
            throw new CodigoInvalidoException("Codigo ingresado invalido");
        }
    }
    
    public void mostrarInvalido(){
        Platform.runLater(new Runnable(){
            @Override
            public void run(){
                lblInvalido.setText("inválido");
            }
        });
    }
    
    public boolean verificarCodigo(){
        ArrayList<Promocion> promos = Promocion.leerPromociones();
        ArrayList<Destino> destinos = Destino.leerDestinos();
        for(Promocion p: promos){
            String codigo = txtCodigo.getText();
            if(codigo.equals(p.getCodigo()) && destino.getPais().toUpperCase().equals(p.getPais())){
                Platform.runLater(() -> lblDescuento.setText("Descuento: "+p.getDescuento()+"%"));
                mostrarDescuentoPromo(p);
                return true;
            }
        }
        return false;
    }
    
    public void verificarRadioEfectivo(){
        rbEfectivo.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                seccionMetodo.getChildren().clear();
                Platform.runLater(()-> rbCredito.setSelected(false));
                mostrarMensaje();
            }
        });
    }
    
    public void verificarRadioCredito(){
        rbCredito.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                seccionMetodo.getChildren().clear();
                Platform.runLater(()-> rbEfectivo.setSelected(false));
                mostrarFormularioTC();
            }
        });
    }
    
    public void mostrarMensaje(){
        Platform.runLater(()->{
        seccionMetodo.setPrefWidth(399);
        seccionMetodo.setPrefHeight(94);
        Label lblMensaje = new Label();
        lblMensaje.setText("Estimado cliente, tiene 24 horas para acercarse a realizar el pago de lo contrario, la reserva se anulará.");
        lblMensaje.setWrapText(true);
        lblMensaje.setStyle("-fx-font-weight: bold;");
        seccionMetodo.getChildren().add(lblMensaje);
        });
    }
    
    public void mostrarFormularioTC(){
        Platform.runLater(()->{
            seccionMetodo.setPrefWidth(399);
            seccionMetodo.setPrefHeight(193);
            seccionMetodo.setAlignment(Pos.TOP_LEFT);
            seccionMetodo.setSpacing(10);
            Label lblInformacion = new Label();
            lblInformacion.setText("Informacion de la Tarjeta");
            GridPane gpCredito = new GridPane();
            gpCredito.setStyle("-fx-border-color:#7b6458;");
            gpCredito.setPadding(new Insets(20,10,20,10));
            gpCredito.setVgap(20);
            gpCredito.setHgap(20);
            ColumnConstraints colum0 = new ColumnConstraints();
            colum0.setMinWidth(10);
            colum0.setPrefWidth(111.00002034505208);
            colum0.setMaxWidth(160);
            colum0.setPercentWidth(-1);
            colum0.setHalignment(HPos.RIGHT);
            ColumnConstraints colum1 = new ColumnConstraints();
            colum1.setMinWidth(10);
            colum1.setPrefWidth(168.33331298828122);
            colum1.setMaxWidth(275);
            colum1.setPercentWidth(-1);
            ColumnConstraints colum2 = new ColumnConstraints();
            gpCredito.getColumnConstraints().addAll(colum0,colum1, colum2);
            RowConstraints row0 = new RowConstraints();
            row0.setMinHeight(10);
            row0.setPrefHeight(30);
            row0.setMaxHeight(USE_COMPUTED_SIZE);
            row0.setPercentHeight(-1);
            RowConstraints row1 = new RowConstraints();
            row1.setMinHeight(10);
            row1.setPrefHeight(30);
            row1.setMaxHeight(USE_COMPUTED_SIZE);
            row1.setPercentHeight(-1);
            RowConstraints row2 = new RowConstraints();
            row2.setMinHeight(10);
            row2.setPrefHeight(30);
            row2.setMaxHeight(USE_COMPUTED_SIZE);
            row2.setPercentHeight(-1);
            gpCredito.getRowConstraints().addAll(row0,row1,row2);
            lblNumero = new Label();
            lblNumero.setText("Numero de tarjeta:");
            lblFecha = new Label("Fecha de expiración:");
            lblCVV = new Label();
            lblCVV.setText("CVV:");
            txtNumTC = new TextField();
            dpExpiracion = new DatePicker();
            pwCVV = new PasswordField();
            gpCredito.add(lblNumero, 0, 0);
            gpCredito.add(lblFecha, 0, 1);
            gpCredito.add(lblCVV, 0, 2);
            gpCredito.add(txtNumTC, 1, 0);
            gpCredito.add(dpExpiracion, 1, 1);
            gpCredito.add(pwCVV, 1, 2);
            seccionMetodo.getChildren().addAll(lblInformacion, gpCredito);
            pagar(gpCredito);
        });
    }
    
    public void mostrarDescuentoPromo(Promocion p){
        Platform.runLater(new Runnable(){
            @Override
            public void run(){
                double total = totalPrecio - (totalPrecio*(p.getDescuento()*0.01));
                lblTotal.setText("Total a pagar: "+String.format("%.2f", total));
            }
        });
    }
    
    public void mostrarPrecioTotal(){
        lblPrecio.setText("Resumen de compra: "+String.format("%.2f", totalPrecio));
        lblTotal.setText("Total a pagar: "+String.format("%.2f", totalPrecio));
    }
    
    public void pagar(GridPane gp){
        btnPagar.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                if(rbCredito.isSelected()){
                    ejecutarPago(gp);
                    mostrarConfirmacionCompra();
                }else{
                    mostrarConfirmacionCompra();
                }
            }
        });
    }
    
    public void ejecutarPago(GridPane gp){
        Thread t = new Thread(new Runnable(){
            @Override
            public void run(){
                try{
                    verificarFormularioTC();
                }catch(DatosIncompletosException d){
                    System.out.println(d.getMessage());
                    Platform.runLater(() ->{
                        Label lblAviso = new Label("Complete");
                        gp.add(lblAviso, 2, 1); 
                    });
                }
            }
        });
        t.start();
    }
    
    public void verificarFormularioTC()throws DatosIncompletosException{
        if(txtNumTC.getText().isEmpty() ||  dpExpiracion.getValue()==null || pwCVV.getText().isEmpty()){
            throw new DatosIncompletosException("Datos incompletos");
        }
    }
    
    public void mostrarConfirmacionCompra(){
        Stage s = (Stage) btnPagar.getScene().getWindow();
        FXMLLoader fl = new FXMLLoader(Main.class.getResource("ConfirmacionCompra.fxml"));
        Parent rootConfirmacionCompra = null;
        try{
            rootConfirmacionCompra = fl.load();
        }catch(IOException e){
            
        }
        Scene ventanaConfirmacion = new Scene(rootConfirmacionCompra);
        s.setTitle("Confirmacion Compra");
        s.setScene(ventanaConfirmacion);
        s.show();
    }
}
