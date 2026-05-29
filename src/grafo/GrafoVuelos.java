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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
    
    public Ruta calcularRuta(String origenCodigo,
                         String destinoCodigo,
                         String criterio) {

    Aeropuerto origen = buscarAeropuerto(origenCodigo);

    Aeropuerto destino = buscarAeropuerto(destinoCodigo);

    Map<Aeropuerto, Double> distancias = new HashMap<>();

    Map<Aeropuerto, Aeropuerto> anteriores = new HashMap<>();

    Set<Aeropuerto> visitados = new HashSet<>();

    for (Aeropuerto aeropuerto : aeropuertos) {

        distancias.put(aeropuerto, Double.MAX_VALUE);
    }

    distancias.put(origen, 0.0);

    while (visitados.size() < aeropuertos.size()) {

        Aeropuerto actual = null;

        double menor = Double.MAX_VALUE;

        for (Aeropuerto aeropuerto : aeropuertos) {

            if (!visitados.contains(aeropuerto)
                    && distancias.get(aeropuerto) < menor) {

                menor = distancias.get(aeropuerto);

                actual = aeropuerto;
            }
        }

        if (actual == null) {
            break;
        }

        visitados.add(actual);

        for (Vuelo vuelo : actual.getVuelos()) {

            Aeropuerto vecino = vuelo.getDestino();

            double peso = obtenerPeso(vuelo, criterio);

            double nuevaDistancia =
                    distancias.get(actual) + peso;

            if (nuevaDistancia < distancias.get(vecino)) {

                distancias.put(vecino, nuevaDistancia);

                anteriores.put(vecino, actual);
            }
        }
    }

    List<Aeropuerto> ruta = new ArrayList<>();

    Aeropuerto actual = destino;

    while (actual != null) {

        ruta.add(0, actual);

        actual = anteriores.get(actual);
    }

    double distanciaTotal = 0;

    double costoTotal = 0;

    double tiempoTotal = 0;

    for (int i = 0; i < ruta.size() - 1; i++) {

        Aeropuerto a = ruta.get(i);

        Aeropuerto b = ruta.get(i + 1);

        for (Vuelo vuelo : a.getVuelos()) {

            if (vuelo.getDestino().equals(b)) {

                distanciaTotal += vuelo.getDistancia();

                costoTotal += vuelo.getPrecio();

                tiempoTotal += vuelo.getTiempoTotal();
            }
        }
    }

    return new Ruta(
            ruta,
            distanciaTotal,
            costoTotal,
            tiempoTotal
    );
}
    
    private double obtenerPeso(Vuelo vuelo,
                           String criterio) {

    switch (criterio) {

        case "Menor Precio":

            return vuelo.getPrecio();

        case "Menor Tiempo":

            return vuelo.getTiempoTotal();

        default:

            return vuelo.getDistancia();
    }
}
}
