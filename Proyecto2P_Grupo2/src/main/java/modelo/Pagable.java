/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modelo;

/**
 *
 * @author Paula
 */
interface Pagable {
    void generarTransaccion(double totalPagar, String tipoPago,int descuento, double totalReserva);
}
