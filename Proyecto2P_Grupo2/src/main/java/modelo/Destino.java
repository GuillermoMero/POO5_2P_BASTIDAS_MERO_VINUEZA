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
public class Destino {
    private String pais;
    private String ciudad;

    public Destino(String ciudad, String pais) {
        this.pais = pais;
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public String toString() {
        return pais;
    }
    
    public ArrayList<Destino> leerDestinos(){
        ArrayList<Destino> destinos = new ArrayList<>();
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        try{
            archivo = new File(Main.pathFiles+"destinos.txt");
            fr = new FileReader(archivo,StandardCharsets.UTF_8);
            br = new BufferedReader(fr);
            String linea;
            while((linea=br.readLine())!=null){
                String [] partes = linea.split(",");
                Destino d = new Destino(partes[0],partes[1]);
                destinos.add(d);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(null!= fr){
                    fr.close();
                }
            }catch(Exception e2){
                e2.printStackTrace();
            }
        }
        return destinos;
    }
}
