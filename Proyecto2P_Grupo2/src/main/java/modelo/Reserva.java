/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Random;

/**
 *
 * @author LENOVO
 */
public class Reserva  implements Serializable, Pagable{
    private static final long serialVersionUID = 1L;
    
    private Cliente cliente;
    private String ciudadOrigen;
    private String ciudadDestino;
    private String fechaSalida;
    private String fechaRegreso;
    private int numeroPasajeros;
    private int numida;
    private int numRegreso;
    private Tarifa tarifaIda;
    private Tarifa tarifaRegreso;
    private String codigo;

    public Reserva(Cliente cliente, String ciudadOrigen, String ciudadDestino, String fechaSalida, String fechaRegreso, int numeroPasajeros, int numida, int numRegreso, Tarifa tarifaIda, Tarifa tarifaRegreso, String codigo) {
        this.cliente = cliente;
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
        this.fechaSalida = fechaSalida;
        this.fechaRegreso = fechaRegreso;
        this.numeroPasajeros = numeroPasajeros;
        this.numida = numida;
        this.numRegreso = numRegreso;
        this.tarifaIda = tarifaIda;
        this.tarifaRegreso = tarifaRegreso;
        this.codigo = codigo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getCiudadOrigen() {
        return ciudadOrigen;
    }

    public void setCiudadOrigen(String ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    public String getCiudadDestino() {
        return ciudadDestino;
    }

    public void setCiudadDestino(String ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getFechaRegreso() {
        return fechaRegreso;
    }

    public void setFechaRegreso(String fechaRegreso) {
        this.fechaRegreso = fechaRegreso;
    }

    public int getNumeroPasajeros() {
        return numeroPasajeros;
    }

    public void setNumeroPasajeros(int numeroPasajeros) {
        this.numeroPasajeros = numeroPasajeros;
    }

    public int getNumida() {
        return numida;
    }

    public void setNumida(int numida) {
        this.numida = numida;
    }

    public int getNumRegreso() {
        return numRegreso;
    }

    public void setNumRegreso(int numRegreso) {
        this.numRegreso = numRegreso;
    }

    public Tarifa getTarifaIda() {
        return tarifaIda;
    }

    public void setTarifaIda(Tarifa tarifaIda) {
        this.tarifaIda = tarifaIda;
    }

    public Tarifa getTarifaRegreso() {
        return tarifaRegreso;
    }

    public void setTarifaRegreso(Tarifa tarifaRegreso) {
        this.tarifaRegreso = tarifaRegreso;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    private void escribirReserva(String nombreArchivo, Reserva reserva) {
        try (FileWriter fw = new FileWriter(nombreArchivo, true);
             BufferedWriter bw = new BufferedWriter(fw)) {

            String linea = construirLineaReserva(reserva);
            bw.write(linea);
            bw.newLine();

            System.out.println("Reserva guardada en el archivo.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static String construirLineaReserva(Reserva reserva) {
        return reserva.getCodigo() + ";" +
           reserva.getCliente() + ";" +
           reserva.getCiudadOrigen() + ";" +
           reserva.getCiudadDestino() + ";" +
           reserva.getFechaSalida() + ";" +
           reserva.getFechaRegreso() + ";" +
           reserva.getNumeroPasajeros() + ";" +
           reserva.getNumida() + ";" +
           reserva.getNumRegreso() + ";" +
           reserva.getTarifaIda() + ";" +
           reserva.getTarifaRegreso();
    }
    
    
     private double obtenerDescuento(List<Tarifa> tarifas) {
        double descuento = 0.0;

        for (Tarifa tarifa : tarifas) {
            if (tarifa.getTipo().equals("Descuento") && tarifa.getCaracteristicas().contains(ciudadDestino)) {
                descuento = tarifa.getPorcentaje() / 100.0;
                break;
            }
        }

        return descuento * (tarifaIda.getPorcentaje() + tarifaRegreso.getPorcentaje());
    }
     
     
     private int generarIdPago() {
        
        return new Random().nextInt(1000);
    }
     
      private void serializarReserva(Reserva reserva) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(reserva.getCodigo() + ".bin"))) {

            oos.writeObject(reserva);
            System.out.println("Reserva serializada correctamente.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Pago generarTransaccion() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**@Override
    public Pago generarTransaccion() {
        double totalReserva = tarifaIda.getPorcentaje()+ tarifaRegreso.getPorcentaje();
        double descuento = obtenerDescuento(tarifas);
        String formaPago = "Tarjeta de crédito"; 
        double totalPagar = totalReserva - descuento;

        Pago pago = new Pago(generarIdPago(), codigo, totalReserva, descuento, formaPago, totalPagar);

        escribirReserva("Reservas.txt", this);

        serializarReserva(this);

        return pago;
    }*/

    

    }

