package gui;


import Arbol.ArbolBinario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArbolBinarioApp extends JFrame {
    private ArbolBinario arbol;
    private JTextArea areaResultados;
    private PanelDibujo panelDibujo;

    public ArbolBinarioApp() {
        // Configuración de la ventana principal
        setTitle("Gestión de Árbol Binario");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel de menú con botones
        JPanel panelMenu = new JPanel();
        panelMenu.setLayout(new GridLayout(0, 2, 10, 10));

        JButton btnCrearArbol = new JButton("Crear Árbol");
        JButton btnInsertarNodo = new JButton("Insertar Nodo");
        JButton btnEliminarNodo = new JButton("Eliminar Nodo");
        JButton btnAltura = new JButton("Obtener Altura");
        JButton btnContarHojas = new JButton("Contar Hojas");
        JButton btnValorMinimo = new JButton("Valor Mínimo");
        JButton btnVerificarIdentico = new JButton("Verificar Árboles Idénticos");
        JButton btnRecorridoAmplitud = new JButton("Recorrido en Amplitud");

        panelMenu.add(btnCrearArbol);
        panelMenu.add(btnInsertarNodo);
        panelMenu.add(btnEliminarNodo);
        panelMenu.add(btnAltura);
        panelMenu.add(btnContarHojas);
        panelMenu.add(btnValorMinimo);
        panelMenu.add(btnVerificarIdentico);
        panelMenu.add(btnRecorridoAmplitud);

        // Área de texto para mostrar resultados
        areaResultados = new JTextArea();
        areaResultados.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaResultados);

        // Añadir el panel de menú y el área de resultados a la ventana
        add(panelMenu, BorderLayout.NORTH);
        add(scroll, BorderLayout.SOUTH);

        // Panel de dibujo
        panelDibujo = new PanelDibujo();
        add(panelDibujo, BorderLayout.CENTER);

        // Configuración de acciones de los botones
        btnCrearArbol.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                arbol = new ArbolBinario();
                areaResultados.setText("Árbol creado.");
                panelDibujo.setArbol(arbol);
                panelDibujo.repaint();
            }
        });

        btnInsertarNodo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String valor = JOptionPane.showInputDialog("Ingrese el valor del nodo:");
                if (valor != null) {
                    arbol.insertar(Integer.parseInt(valor));
                    areaResultados.setText("Nodo " + valor + " insertado.");
                    panelDibujo.repaint();
                }
            }
        });

        btnEliminarNodo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String valor = JOptionPane.showInputDialog("Ingrese el valor del nodo a eliminar:");
                if (valor != null) {
                    arbol.eliminar(Integer.parseInt(valor));
                    areaResultados.setText("Nodo " + valor + " eliminado.");
                    panelDibujo.repaint();
                }
            }
        });

        btnAltura.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int altura = arbol.obtenerAltura(arbol.raiz);
                areaResultados.setText("Altura del árbol: " + altura);
            }
        });

        btnContarHojas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int hojas = arbol.contarHojas(arbol.raiz);
                areaResultados.setText("Número de hojas: " + hojas);
            }
        });

        btnValorMinimo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int valorMin = arbol.obtenerMinimo(arbol.raiz);
                areaResultados.setText("Valor mínimo del árbol: " + valorMin);
            }
        });

        btnVerificarIdentico.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                areaResultados.setText("Funcionalidad de verificar árboles idénticos no implementada.");
            }
        });

        btnRecorridoAmplitud.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String recorrido = arbol.recorridoAmplitud();
                areaResultados.setText("Recorrido en amplitud: " + recorrido);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ArbolBinarioApp().setVisible(true);
            }
        });
    }
}
