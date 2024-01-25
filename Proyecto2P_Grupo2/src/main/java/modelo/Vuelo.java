/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import com.mycompany.proyecto2p_grupo2.Main;
import com.mycompany.proyecto2p_grupo2.ReservaVuelo1Controller;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;

/**
 *
 * @author LENOVO
 */
public class Vuelo implements Comparable<Vuelo>{
    private String numVuelo;
    private String origen;
    private String destino;
    private int duracion;
    private String horaSalida;
    private String horaLlegada;
    private String codigoAvion;
    private double precio;
    public static String tipoOrden;

    public Vuelo(String numVuelo, String origen, String destino, int duracion, String horaSalida, String horaLlegada, String codigoAvion, double precio) {
        this.numVuelo = numVuelo;
        this.origen = origen;
        this.destino = destino;
        this.duracion = duracion;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
        this.codigoAvion = codigoAvion;
        this.precio = precio;
    }
    
    

    public String getNumVuelo() {
        return numVuelo;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public int getDuracion() {
        return duracion;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public String getHoraLlegada() {
        return horaLlegada;
    }

    public String getCodigoAvion() {
        return codigoAvion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setNumVuelo(String numVuelo) {
        this.numVuelo = numVuelo;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public void setHoraLlegada(String horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public void setCodigoAvion(String codigoAvion) {
        this.codigoAvion = codigoAvion;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    public static ArrayList<Vuelo> leerVuelos(){
        ArrayList<Vuelo> vuelos = new ArrayList<>();
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        try{
            archivo = new File(Main.pathFiles+"vuelos.txt");
            fr = new FileReader(archivo, StandardCharsets.UTF_8);
            br = new BufferedReader(fr);
            br.readLine();
            String linea;
            while((linea=br.readLine())!=null){
                String [] partes = linea.split(",");
                Vuelo v = new Vuelo(partes[0],partes[1],partes[2],Integer.parseInt(partes[3]),partes[4],partes[5],partes[6],Double.parseDouble(partes[7]));
                vuelos.add(v);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(null!=fr){
                    fr.close();
                }
            }catch(Exception e2){
                e2.printStackTrace();
            }
        }
        return vuelos;
    }
    
    @Override
    public int compareTo(Vuelo v){
        //FXMLLoader fl = new FXMLLoader(Main.class.getResource("ReservaVuelo1.fxml"));
        
        //ReservaVuelo1Controller rc1 = fl.getController();
        //String tipoOrden = ReservaVuelo1Controller.cbOrden.getValue();
        if(tipoOrden.equals("Precio")){
            if(this.precio < v.precio){
                return -1;
            }else if(this.precio > v.precio){
                return -1;
            }
        }else if(tipoOrden.equals("Duración")){
            if(this.duracion < v.duracion){
                return -1;
            }else if(this.duracion > v.duracion){
                return -1;
            }
        }
        return 0;
    }
    
}
