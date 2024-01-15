/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author LENOVO
 */
public class Reserva {
    private String codigo;
    private String nombreCliente;
    private String apellidoCliente;
    public Reserva(String c, String n, String a){
        this.nombreCliente = n;
        this.codigo = c;
        this.apellidoCliente = a;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setNombreCliente(String nomCliente) {
        this.nombreCliente = nomCliente;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getApellidoCliente() {
        return apellidoCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }
    
    @Override
    public String toString(){
        return codigo+"-"+nombreCliente+" "+apellidoCliente;
    }
}
