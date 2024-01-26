/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import com.mycompany.proyecto2p_grupo2.Main;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
public class Tarifa {
    private String nombre;
    private String tipo;
    private ArrayList<String> caracteristicas;
    private int porcentaje;

    public Tarifa(String nombre, String tipo, int porcentaje) {
        this.caracteristicas = new ArrayList<>();
        this.nombre = nombre;
        this.tipo = tipo;
        this.porcentaje = porcentaje;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public ArrayList<String> getCaracteristicas() {
        return caracteristicas;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }

    public void setCaracteristicas(ArrayList<String> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }
    
    public static ArrayList<Tarifa> leerTarifas(){
        ArrayList<Tarifa> tarifas = new ArrayList<>();
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        try{
            archivo = new File(Main.pathFiles+"tarifas.txt");
            fr = new FileReader(archivo,StandardCharsets.UTF_8);
            br = new BufferedReader(fr);
            br.readLine();
            String linea;
            while((linea=br.readLine())!=null){
                String partes [] = linea.split(",");
                String subPartes [] = partes[2].split("-");
                Tarifa t = new Tarifa(partes[0],partes[1],Integer.parseInt(partes[3]));
                for(int i=0; i<subPartes.length;i++){
                    t.getCaracteristicas().add(subPartes[i]);
                }
                tarifas.add(t);
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
        return tarifas;
    }
}
