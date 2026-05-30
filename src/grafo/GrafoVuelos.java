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
    //objeto registro aeropuerto
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

            //objeto registrar vuelo
            Vuelo vuelo = new Vuelo(
                    origen,
                    destino,
                    distancia,
                    precio,
                    tiempoVuelo,
                    tiempoEspera
            );

            //lo guarda 
            origen.agregarVuelo(vuelo);
        }
    }

    public String mostrarConexiones() {

        StringBuilder sb = new StringBuilder();

        //recorre todo los aeropuertos
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

        //obtener nodos origen - destino
    Aeropuerto origen = buscarAeropuerto(origenCodigo);

    Aeropuerto destino = buscarAeropuerto(destinoCodigo);

    //guardar distancia minima conocida en cada aeropuero
    Map<Aeropuerto, Double> distancias = new HashMap<>();

    //guardar camino *ruta final*
    Map<Aeropuerto, Aeropuerto> anteriores = new HashMap<>();

    //control de nodos 
    Set<Aeropuerto> visitados = new HashSet<>();

    //inicializamos distancias en infinito
    for (Aeropuerto aeropuerto : aeropuertos) {

        distancias.put(aeropuerto, Double.MAX_VALUE);
    }

    //definimos distancia en 0
    distancias.put(origen, 0.0);

    //dijkstra
    while (visitados.size() < aeropuertos.size()) {

        Aeropuerto actual = null;
        double menor = Double.MAX_VALUE;

        //buscar nodo menor distancia
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

        //revisar vuelos vecinos
        for (Vuelo vuelo : actual.getVuelos()) {

            Aeropuerto vecino = vuelo.getDestino();

            //elegir peso segun criterio
            double peso = obtenerPeso(vuelo, criterio);

            double nuevaDistancia =
                    distancias.get(actual) + peso;
            
            //si encontramos mejor camino, actualizamos
            if (nuevaDistancia < distancias.get(vecino)) {

                distancias.put(vecino, nuevaDistancia);

                anteriores.put(vecino, actual);
            }
        }
    }

    // Reconstruccion de la ruta final
    List<Aeropuerto> ruta = new ArrayList<>();

    Aeropuerto actual = destino;

    while (actual != null) {

        ruta.add(0, actual);

        actual = anteriores.get(actual);
    }

    //Calcular totales
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

    //retornar ruta con resultados
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
