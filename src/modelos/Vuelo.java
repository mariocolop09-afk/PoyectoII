/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author Eduardo
 */
public class Vuelo {
    private Aeropuerto origen;
    private Aeropuerto destino;

    private double distancia;
    private double precio;
    private double tiempoVuelo;
    private double tiempoEspera;

    public Vuelo(Aeropuerto origen, Aeropuerto destino,
                  double distancia,
                  double precio,
                  double tiempoVuelo,
                  double tiempoEspera) {

        this.origen = origen;
        this.destino = destino;
        this.distancia = distancia;
        this.precio = precio;
        this.tiempoVuelo = tiempoVuelo;
        this.tiempoEspera = tiempoEspera;
    }

    public Aeropuerto getOrigen() {
        return origen;
    }

    public Aeropuerto getDestino() {
        return destino;
    }

    public double getDistancia() {
        return distancia;
    }

    public double getPrecio() {
        return precio;
    }

    public double getTiempoVuelo() {
        return tiempoVuelo;
    }

    public double getTiempoEspera() {
        return tiempoEspera;
    }

    public double getTiempoTotal() {
        return tiempoVuelo + tiempoEspera;
    }

    @Override
    public String toString() {
        return origen.getCodigo() + " -> " + destino.getCodigo();
    }
}
