/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;

/**
 *
 * @author Paula
 */
public class Pago implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String codigoReserva;
    private double totalReserva;
    private double descuento;
    private String formaPago;
    private double totalPagar;

    public Pago(int id, String codigoReserva, double totalReserva, double descuento, String formaPago, double totalPagar) {
        this.id = id;
        this.codigoReserva = codigoReserva;
        this.totalReserva = totalReserva;
        this.descuento = descuento;
        this.formaPago = formaPago;
        this.totalPagar = totalPagar;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigoReserva() {
        return codigoReserva;
    }

    public void setCodigoReserva(String codigoReserva) {
        this.codigoReserva = codigoReserva;
    }

    public double getTotalReserva() {
        return totalReserva;
    }

    public void setTotalReserva(double totalReserva) {
        this.totalReserva = totalReserva;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public double getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(double totalPagar) {
        this.totalPagar = totalPagar;
    }
    
    
}
