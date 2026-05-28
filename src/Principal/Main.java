/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;
import grafo.GrafoVuelos;
import vistas.VentanaPrincipal;
/**
 *
 * @author Eduardo
 */
public class Main {
        public static void main(String[] args) {
         
         //crear grafo principal
         GrafoVuelos grafo = new GrafoVuelos();

        
        VentanaPrincipal ventana = new VentanaPrincipal();
        ventana.setVisible(true);
    }
}
