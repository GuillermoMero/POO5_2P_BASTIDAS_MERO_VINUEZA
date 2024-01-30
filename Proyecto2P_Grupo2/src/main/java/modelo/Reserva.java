/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

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
    
    public void escribirReserva(String nombreArchivo, Reserva reserva){
        FileWriter f = null;
        BufferedWriter bw = null;

        try {
            f = new FileWriter("Reservas.txt", true);
            bw = new BufferedWriter(f);

            String linea = construirLineaReserva(reserva);

            bw.write(linea);
            bw.newLine(); 

            System.out.println("Reserva guardada en el archivo.");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
                if (f != null) {
                    f.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    private static String construirLineaReserva(Reserva reserva) {
        // Construir la línea con la información de la reserva
        return String.format("%s;%s;%s;%s;%s;%d;%s;%s;%f;%f;%s",
                reserva.getCodigo(),
                reserva.getCliente(),
                reserva.getCiudadOrigen(),
                reserva.getCiudadDestino(),
                reserva.getFechaSalida(),
                reserva.getFechaRegreso(),
                reserva.getNumeroPasajeros(),
                reserva.getNumida(),
                reserva.getNumRegreso(),
                reserva.getTarifaIda(),
                reserva.getTarifaRegreso());
    }
    
    @Override
    public Pago generarTransaccion(List<Promocion> promociones) {
        double totalReserva = tarifaIda.getPrecio() + tarifaRegreso.getPrecio();
        double descuento = obtenerDescuento(promociones);
        String formaPago = "Tarjeta de crédito"; // Ejemplo, ajusta según tu lógica
        double totalPagar = totalReserva - descuento;

        Pago pago = new Pago(generarIdPago(), codigo, totalReserva, descuento, formaPago, totalPagar);

        // Guarda la reserva en el archivo reservas.txt
        escribirReserva("Reservas.txt");

        // Serializa la reserva en un archivo binario con el nombre del código de la reserva
        serializarReserva(this);

        return pago;
    }
    
     private double obtenerDescuento(List<Promocion> promociones) {
       
        double descuento = 0.0;

        for (Promocion promocio : promociones) {
            if (promocio.(this.ciudadDestino)) {
                descuento = promocion.getDescuento();
                break; // Aplicamos el descuento de la primera promoción que cumple con los criterios
            }
        }

        return descuento * (tarifaIda + tarifaRegreso);
    }
    

    }

