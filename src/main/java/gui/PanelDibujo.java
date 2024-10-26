package gui;


import javax.swing.*;
import java.awt.*;
import Arbol.ArbolBinario;
import Arbol.Nodo;

public class PanelDibujo extends JPanel {
    private ArbolBinario arbol;
    private static final int DIAMETRO_NODO = 30;
    private static final int ESPACIO_HORIZONTAL = 50;
    private static final int ESPACIO_VERTICAL = 50;

    // Definimos la paleta de colores en función de la profundidad
    private final Color[] colores = {

            new Color(0xbc6c25),// Nivel 0 (Raíz)
            new Color(0xdda15e),// Nivel 1
            new Color(0x155d27),// Nivel 2
            new Color(0x25a244),// Nivel 3
            new Color(0x386641),// Nivel 4
            new Color(0x6A994E),// Nivel 5
            new Color(0xA7C957),// Nivel 6
            new Color(0xF2E8CF),// Nivel 7
            new Color(0xBC4749) // Nivel 8
    };

    public void setArbol(ArbolBinario arbol) {
        this.arbol = arbol;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (arbol != null && arbol.raiz != null) {
            dibujarNodo(g, arbol.raiz, getWidth() / 2, 50, getWidth() / 4, 0);
        }
    }

    /**
     * Dibuja un nodo del árbol binario recursivamente.
     * @param g Graphics para dibujar.
     * @param nodo El nodo actual.
     * @param x La posición x del nodo.
     * @param y La posición y del nodo.
     * @param espacio Espacio horizontal entre nodos.
     * @param nivel Nivel de profundidad del nodo.
     */
    private void dibujarNodo(Graphics g, Nodo nodo, int x, int y, int espacio, int nivel) {
        if (nodo == null) return;

        // Seleccionamos el color basado en el nivel de profundidad
        Color colorNodo = nivel < colores.length ? colores[nivel] : colores[colores.length - 1];
        g.setColor(colorNodo);

        // Dibujamos el nodo como un óvalo coloreado
        g.fillOval(x - DIAMETRO_NODO / 2, y - DIAMETRO_NODO / 2, DIAMETRO_NODO, DIAMETRO_NODO);
        g.setColor(Color.BLACK);
        g.drawOval(x - DIAMETRO_NODO / 2, y - DIAMETRO_NODO / 2, DIAMETRO_NODO, DIAMETRO_NODO);
        g.drawString(Integer.toString(nodo.valor), x - 6, y + 4);

        // Dibujamos las conexiones a los hijos
        if (nodo.izquierdo != null) {
            g.drawLine(x, y, x - espacio, y + ESPACIO_VERTICAL);
            dibujarNodo(g, nodo.izquierdo, x - espacio, y + ESPACIO_VERTICAL, espacio / 2, nivel + 1);
        }

        if (nodo.derecho != null) {
            g.drawLine(x, y, x + espacio, y + ESPACIO_VERTICAL);
            dibujarNodo(g, nodo.derecho, x + espacio, y + ESPACIO_VERTICAL, espacio / 2, nivel + 1);
        }
    }
}
