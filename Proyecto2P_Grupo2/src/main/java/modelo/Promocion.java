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
public class Promocion {
    private double x;
    private double y;
    private String pais;
    private String codigo;
    private int descuento;
    
    public Promocion(double x, double y, String c, String p, int d){
        this.x = x;
        this.y = y;
        this.pais = p;
        this.codigo = c;
        this.descuento = d;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String getPais() {
        return pais;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }
    
    public static ArrayList<Promocion> leerPromociones(){
        File f = null;
        FileReader fr = null;
        BufferedReader br = null;
        ArrayList<Promocion> promociones = new ArrayList<>();
        Promocion p;
        try{
            f = new File(Main.pathFiles+"promociones.txt");
            fr = new FileReader(f,StandardCharsets.UTF_8);
            br = new BufferedReader(fr);
            br.readLine();
            String linea;
            while((linea=br.readLine())!=null){
                String[] partes = linea.split(",");
                p = new Promocion(Double.parseDouble(partes[0].trim()),Double.parseDouble(partes[1].trim()),partes[2].trim(),partes[3].trim(),Integer.parseInt(partes[4].trim()));
                promociones.add(p);
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
        return promociones;
        
    }
}
