package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import Arbol.ArbolBinario;
import Arbol.Nodo;
import Arbol.NodoExpresion;

public class PanelDibujo extends JPanel {
    private ArbolBinario arbol;
    private static final int DIAMETRO_NODO = 35;
    private static final int ESPACIO_VERTICAL = 60;
    private static final int PADDING = 35;
    private NodoExpresion raiz;

    // Colores mejorados con mejor contraste y estética
    private final Color[] colores = {
            new Color(0x2E86AB), // Azul oscuro - Raíz
            new Color(0x34AC98), // Verde azulado
            new Color(0x40BF7D), // Verde medio
            new Color(0x8CBF40), // Verde lima
            new Color(0xBFB740), // Amarillo mostaza
            new Color(0xBF8D40), // Naranja marrón
            new Color(0xBF6340), // Naranja rojizo
            new Color(0xBF4040), // Rojo
            new Color(0x9340BF)  // Púrpura
    };

    // Añadimos un color para el borde y el texto
    private final Color colorBorde = new Color(0x2C3E50);
    private final Color colorTexto = new Color(0xFFFFFF);

    public PanelDibujo() {
        setBackground(new Color(0xF5F6FA));
        // Habilitar antialiasing
        setDoubleBuffered(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Configurar renderizado para mejor calidad
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        if (arbol != null && arbol.raiz != null) {
            // Calcular la altura del árbol para ajustar el espaciado
            int altura = arbol.obtenerAltura(arbol.raiz);
            int espacioHorizontal = calcularEspacioHorizontal(altura);

            // Dibujar el árbol centrado
            dibujarNodo(g2d, arbol.raiz, getWidth() / 2, PADDING + DIAMETRO_NODO,
                    espacioHorizontal, 0);
        }

        //Dibujar el árbol B
        if (raiz != null) {
            dibujarArbolB(g, raiz, getWidth() / 2, 30, 100,0); // Llama a un método para dibujar el árbol
        }
    }

    private int calcularEspacioHorizontal(int altura) {
        return Math.max(DIAMETRO_NODO * 2, (getWidth() - 2 * PADDING) / (1 << altura));
    }

    private void dibujarNodo(Graphics2D g2d, Nodo nodo, int x, int y, int espacio,
                             int nivel) {
        if (nodo == null) return;

        // Seleccionar color según nivel
        Color colorNodo = nivel < colores.length ? colores[nivel] : colores[colores.length - 1];

        // Crear efecto de sombra
        g2d.setColor(new Color(0, 0, 0, 50));
        g2d.fillOval(x - DIAMETRO_NODO/2 + 2, y - DIAMETRO_NODO/2 + 2,
                DIAMETRO_NODO, DIAMETRO_NODO);

        // Dibujar conexiones primero (para que queden detrás de los nodos)
        g2d.setStroke(new BasicStroke(2.0f));
        g2d.setColor(new Color(0x95A5A6));

        if (nodo.izquierdo != null) {
            g2d.draw(new Line2D.Double(
                    x, y,
                    x - espacio*2, y + ESPACIO_VERTICAL
            ));
        }
        if (nodo.derecho != null) {
            g2d.draw(new Line2D.Double(
                    x, y,
                    x + espacio*2, y + ESPACIO_VERTICAL
            ));
        }

        // Dibujar el nodo
        // Círculo principal
        g2d.setColor(colorNodo);
        g2d.fill(new Ellipse2D.Double(
                x - DIAMETRO_NODO/2, y - DIAMETRO_NODO/2,
                DIAMETRO_NODO, DIAMETRO_NODO
        ));

        // Borde del nodo
        g2d.setColor(colorBorde);
        g2d.setStroke(new BasicStroke(2.0f));
        g2d.draw(new Ellipse2D.Double(
                x - DIAMETRO_NODO/2, y - DIAMETRO_NODO/2,
                DIAMETRO_NODO, DIAMETRO_NODO
        ));

        // Dibujar el valor
        String valorStr = String.valueOf(nodo.valor);
        g2d.setFont(new Font("Arial", Font.BOLD, 14));
        FontMetrics fm = g2d.getFontMetrics();
        g2d.setColor(colorTexto);
        g2d.drawString(valorStr,
                x - fm.stringWidth(valorStr) / 2,
                y + fm.getAscent() / 2
        );

        // Dibujar los nodos hijos
        if (nodo.izquierdo != null) {
            dibujarNodo(g2d, nodo.izquierdo, x - espacio*2, y + ESPACIO_VERTICAL,
                    espacio/2, nivel + 1);
        }
        if (nodo.derecho != null) {
            dibujarNodo(g2d, nodo.derecho, x + espacio*2, y + ESPACIO_VERTICAL,
                    espacio/2, nivel + 1);
        }
    }

    //Métodos para dibujar el árbol B del punto 10

    private void dibujarArbolB(Graphics g, NodoExpresion nodo, int x, int y, int espacio, int nivel) {
        if (nodo == null) return;

        Graphics2D g2d = (Graphics2D) g;

        // Seleccionar color según nivel
        Color colorNodo = nivel < colores.length ? colores[nivel] : colores[colores.length - 1];

        // Crear efecto de sombra
        g2d.setColor(new Color(0, 0, 0, 50));
        g2d.fillOval(x - DIAMETRO_NODO/2 + 2, y - DIAMETRO_NODO/2 + 2, DIAMETRO_NODO, DIAMETRO_NODO);

        // Dibujar conexiones primero (para que queden detrás de los nodos)
        g2d.setStroke(new BasicStroke(2.0f));
        g2d.setColor(new Color(0x95A5A6));

        if (nodo.izquierdo != null) {
            g2d.draw(new Line2D.Double(
                    x, y,
                    x - espacio * 2, y + ESPACIO_VERTICAL
            ));
        }
        if (nodo.derecho != null) {
            g2d.draw(new Line2D.Double(
                    x, y,
                    x + espacio * 2, y + ESPACIO_VERTICAL
            ));
        }

        // Dibujar el nodo
        // Círculo principal
        g2d.setColor(colorNodo);
        g2d.fill(new Ellipse2D.Double(
                x - DIAMETRO_NODO / 2, y - DIAMETRO_NODO / 2,
                DIAMETRO_NODO, DIAMETRO_NODO
        ));

        // Borde del nodo
        g2d.setColor(colorBorde);
        g2d.setStroke(new BasicStroke(2.0f));
        g2d.draw(new Ellipse2D.Double(
                x - DIAMETRO_NODO / 2, y - DIAMETRO_NODO / 2,
                DIAMETRO_NODO, DIAMETRO_NODO
        ));

        // Dibujar el valor
        String valorStr = String.valueOf(nodo.valor);
        g2d.setFont(new Font("Arial", Font.BOLD, 13));
        FontMetrics fm = g2d.getFontMetrics();
        g2d.setColor(colorTexto);
        g2d.drawString(valorStr,
                x - fm.stringWidth(valorStr) / 2,
                y + fm.getAscent() / 2
        );

        // Dibujar los nodos hijos
        if (nodo.izquierdo != null) {
            dibujarArbolB(g2d, nodo.izquierdo, x - espacio * 2, y + ESPACIO_VERTICAL,
                    espacio / 2, nivel + 1);
        }
        if (nodo.derecho != null) {
            dibujarArbolB(g2d, nodo.derecho, x + espacio * 2, y + ESPACIO_VERTICAL,
                    espacio / 2, nivel + 1);
        }
    }

    public void setArbol(ArbolBinario arbol) {
        this.arbol = arbol;
        repaint();
    }

    public void setArbol(NodoExpresion raiz) {
        this.raiz = raiz;
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        if (arbol != null && arbol.raiz != null) {
            int altura = arbol.obtenerAltura(arbol.raiz);
            return new Dimension(
                    DIAMETRO_NODO * (1 << altura) + PADDING * 2,
                    ESPACIO_VERTICAL * altura + DIAMETRO_NODO * 2 + PADDING * 2
            );
        }
        return new Dimension(300, 200);
    }
}