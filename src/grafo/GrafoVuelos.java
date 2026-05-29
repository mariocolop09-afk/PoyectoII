/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafo;

import modelos.Aeropuerto;
import modelos.Vuelo;
import modelos.Ruta;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class GrafoVuelos {

    private List<Aeropuerto> aeropuertos;

    public GrafoVuelos() {
        aeropuertos = new ArrayList<>();
    }

    public void agregarAeropuerto(Aeropuerto aeropuerto) {
        if (buscarAeropuerto(aeropuerto.getCodigo()) == null) {
        aeropuertos.add(aeropuerto);
    }
    }

    public Aeropuerto buscarAeropuerto(String codigo) {

        for (Aeropuerto aeropuerto : aeropuertos) {

            if (aeropuerto.getCodigo().equalsIgnoreCase(codigo)) {
                return aeropuerto;
            }
        }

        return null;
    }

    public void agregarVuelo(String origenCodigo,
                              String destinoCodigo,
                              double distancia,
                              double precio,
                              double tiempoVuelo,
                              double tiempoEspera) {

        Aeropuerto origen = buscarAeropuerto(origenCodigo);
        Aeropuerto destino = buscarAeropuerto(destinoCodigo);

        if (origen != null && destino != null) {

            Vuelo vuelo = new Vuelo(
                    origen,
                    destino,
                    distancia,
                    precio,
                    tiempoVuelo,
                    tiempoEspera
            );

            origen.agregarVuelo(vuelo);
        }
    }

    public String mostrarConexiones() {

        StringBuilder sb = new StringBuilder();

        for (Aeropuerto aeropuerto : aeropuertos) {

            sb.append(aeropuerto.getCodigo()).append(" -> \n");

            for (Vuelo vuelo : aeropuerto.getVuelos()) {

                sb.append("   ")
                  .append(vuelo.getDestino().getCodigo())
                  .append(" | Distancia: ")
                  .append(vuelo.getDistancia())
                  .append(" km\n");
            }

            sb.append("\n");
        }

        return sb.toString();
    }

    public List<Aeropuerto> getAeropuertos() {

        return aeropuertos;
    }
}
