/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import com.mycompany.proyecto2p_grupo2.Main;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
public class Cliente implements Serializable{
    private String cedula;
    private String nombre;
    private String apellido;
    private String usuario;
    private String contrasenia;
    private String genero;
    
    public Cliente(String id, String n, String a, String u, String c, String g){
        this.cedula = id;
        this.nombre = n;
        this.apellido = a;
        this.usuario = u;
        this.contrasenia = c;
        this.genero = g;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public String getGenero() {
        return genero;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    public static ArrayList<Cliente> leerClientes(){
        File f = null;
        FileReader fr = null;
        BufferedReader br = null;
        ArrayList<Cliente> clientes = new ArrayList<>();
        Cliente c;
        try{
            f = new File(Main.pathFiles+"clientes.txt");
            fr = new FileReader(f,StandardCharsets.UTF_8);
            br = new BufferedReader(fr);
            br.readLine();
            String linea;
            while((linea=br.readLine())!=null){
                String[] partes = linea.split(",");
                c = new Cliente(partes[0].trim(),partes[1].trim(),partes[2].trim(),partes[3].trim(),partes[4].trim(),partes[5].trim());
                clientes.add(c);
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
        return clientes;
    }
    /***
    public static void main(String [] args){
        Cliente c = leerClientes();
        System.out.println(c.getNombre());
    }***/
}
