/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;
import java.util.List;

/**
 *
 * @author Eduardo
 */
public class Ruta {
    private List<Aeropuerto> aeropuertos;

    private double distanciaTotal;
    private double costoTotal;
    private double tiempoTotal;

    public Ruta(List<Aeropuerto> aeropuertos,
                 double distanciaTotal,
                 double costoTotal,
                 double tiempoTotal) {

        this.aeropuertos = aeropuertos;
        this.distanciaTotal = distanciaTotal;
        this.costoTotal = costoTotal;
        this.tiempoTotal = tiempoTotal;
    }

    public List<Aeropuerto> getAeropuertos() {
        return aeropuertos;
    }

    public double getDistanciaTotal() {
        return distanciaTotal;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public double getTiempoTotal() {
        return tiempoTotal;
    }
}
