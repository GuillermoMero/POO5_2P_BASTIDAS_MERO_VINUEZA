/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import com.mycompany.proyecto2p_grupo2.Main;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author LENOVO
 */
public class Reserva implements Serializable, Pagable{
    private static final long serialVersionUID = 1L;
    
    private Cliente cliente;
    private String ciudadOrigen;
    private String ciudadDestino;
    private String fechaSalida;
    private String fechaRegreso;
    private int numeroPasajeros;
    private String numIda;
    private String numRegreso;
    private Tarifa tarifaIda;
    private Tarifa tarifaRegreso;
    private String codigo;

    public Reserva(Cliente cliente, String ciudadOrigen, String ciudadDestino, String fechaSalida, String fechaRegreso, int numeroPasajeros, String numIda, String numRegreso, Tarifa tarifaIda, Tarifa tarifaRegreso, String codigo) {
        this.cliente = cliente;
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
        this.fechaSalida = fechaSalida;
        this.fechaRegreso = fechaRegreso;
        this.numeroPasajeros = numeroPasajeros;
        this.numIda = numIda;
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

    public String getNumIda() {
        return numIda;
    }

    public void setNumIda(String numida) {
        this.numIda = numida;
    }

    public String getNumRegreso() {
        return numRegreso;
    }

    public void setNumRegreso(String numRegreso) {
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
    
    /**
     * Escribe la reserva en el archivo "reservas.txt".
     * @param reserva Reserva a ser escrita.
     */
    
    public static void escribirReservas(Reserva reserva) {
        try (FileWriter fw = new FileWriter(Main.pathFiles+"reservas.txt", true);
             BufferedWriter bw = new BufferedWriter(fw)) {

            String linea = construirLineaReserva(reserva);
            bw.write(linea);
            bw.newLine();

            System.out.println("Reserva guardada en el archivo.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Construye una línea de reserva para ser escrita en el archivo.
     * @param reserva Reserva a ser convertida a una cadena.
     * @return Cadena representando la reserva.
     */
    
    private static String construirLineaReserva(Reserva reserva) {
        return reserva.getCodigo() + "," +
           reserva.getCliente().getCedula() + "," +
           reserva.getCiudadOrigen() + "," +
           reserva.getCiudadDestino() + "," +
           reserva.getFechaSalida() + "," +
           reserva.getFechaRegreso() + "," +
           reserva.getNumeroPasajeros() + "," +
           reserva.getNumIda() + "," +
           reserva.getNumRegreso() + "," +
           reserva.getTarifaIda().getTipo() + "," +
           reserva.getTarifaRegreso().getTipo();
    }
    
    /**
     * Obtiene el descuento aplicado a una reserva.
     * @param tarifas Lista de tarifas asociadas al proceso.
     * @return Descuento aplicado.
     */
    
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
     
    /**
     * Genera un ID de pago aleatorio.
     * @return ID de pago generado.
     */
    private int generarIdPago() {
        return new Random().nextInt(1000);
    }
     
    public static void serializarReserva(Reserva reserva) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(Main.pathFiles+reserva.getCodigo() + ".bin"))) {

            oos.writeObject(reserva);
            System.out.println("Reserva serializada correctamente.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Genera una transacción asociada a la reserva y la escribe en un archivo de pagos.
     * @param totalPagar Total a pagar.
     * @param tipoPago Tipo de pago.
     * @param descuento Descuento aplicado.
     * @param totalReserva Total de la reserva.
     */
    @Override
    public void generarTransaccion(double totalPagar, String tipoPago,int descuento, double totalReserva){
        Pago pago = new Pago(generarIdPago(), this.codigo, totalReserva, descuento, tipoPago, totalPagar);
        Pago.escribirPagos(pago);
    }
    
    /**
     * Lee las reservas desde el archivo "reservas.txt".
     * @return Lista de reservas leídas desde el archivo.
     */
    
    public static ArrayList<Reserva> leerReservas(){
        ArrayList<Reserva> reservas = new ArrayList<>();
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        try{
            archivo = new File(Main.pathFiles+"reservas.txt");
            fr = new FileReader(archivo,StandardCharsets.UTF_8);
            br = new BufferedReader(fr);
            String linea;
            while((linea=br.readLine())!=null){
                ArrayList<Cliente> clientes = Cliente.leerClientes();
                ArrayList<Tarifa> tarifas = Tarifa.leerTarifas();
                String partes [] = linea.split(",");
                Cliente cliente = null;
                Tarifa tIda = null;
                Tarifa tRegreso = null;
                for(Cliente c: clientes){
                    if(c.getCedula().equals(partes[1])){
                        cliente = c;
                    }
                }
                for(Tarifa t: tarifas){
                    if(t.getTipo().equals(partes[9])){
                        tIda = t;
                    }
                    if(t.getTipo().equals(partes[10])){
                        tRegreso = t;
                    }
                }
                Reserva r = new Reserva(cliente,partes[2],partes[3],partes[4],partes[5],Integer.parseInt(partes[6]),partes[7],partes[8],tIda,tRegreso,partes[0]);
                reservas.add(r);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(fr!=null){
                    fr.close();
                }
            }catch(Exception e2){
                e2.printStackTrace();
            }
        }
        return reservas;
    }
    
    @Override
    public String toString(){
        return codigo+" - "+cliente.getNombre()+" "+cliente.getApellido();
    }
}

