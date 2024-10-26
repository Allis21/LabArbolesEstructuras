package gui;

import Arbol.ArbolBinario;
import javax.swing.*;
import java.awt.*;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ArbolBinarioApp extends JFrame {
    private ArbolBinario arbol1;
    private ArbolBinario arbol2;
    private JTextArea areaResultados;
    private PanelDibujo panelDibujo1;
    private PanelDibujo panelDibujo2;
    private JLabel lblArbol1;
    private JLabel lblArbol2;
    private JPanel panelArboles;

    public ArbolBinarioApp() {
        setTitle("Comparación de Árboles Binarios");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Changed to 3 rows, 6 columns for better organization
        JPanel panelBotones = new JPanel(new GridLayout(3, 6, 10, 10));
        crearBotones(panelBotones);

        panelArboles = new JPanel(new GridLayout(1, 2, 20, 0));

        JPanel panel1 = new JPanel(new BorderLayout());
        lblArbol1 = new JLabel("Árbol 1", SwingConstants.CENTER);
        lblArbol1.setFont(new Font("Arial", Font.BOLD, 14));
        panelDibujo1 = new PanelDibujo();
        panelDibujo1.setPreferredSize(new Dimension(500, 400));
        panel1.add(lblArbol1, BorderLayout.NORTH);
        panel1.add(panelDibujo1, BorderLayout.CENTER);

        JPanel panel2 = new JPanel(new BorderLayout());
        lblArbol2 = new JLabel("Árbol 2", SwingConstants.CENTER);
        lblArbol2.setFont(new Font("Arial", Font.BOLD, 14));
        panelDibujo2 = new PanelDibujo();
        panelDibujo2.setPreferredSize(new Dimension(500, 400));
        panel2.add(lblArbol2, BorderLayout.NORTH);
        panel2.add(panelDibujo2, BorderLayout.CENTER);

        panelArboles.add(panel1);
        panelArboles.add(panel2);

        areaResultados = new JTextArea(8, 50);
        areaResultados.setEditable(false);
        areaResultados.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollResultados = new JScrollPane(areaResultados);
        scrollResultados.setBorder(BorderFactory.createTitledBorder("Resultados"));

        panelPrincipal.add(panelBotones, BorderLayout.NORTH);
        panelPrincipal.add(panelArboles, BorderLayout.CENTER);
        panelPrincipal.add(scrollResultados, BorderLayout.SOUTH);

        add(panelPrincipal);
    }

    private void crearBotones(JPanel panelBotones) {
        // Árbol 1 operations
        JButton btnCrearArbol1 = crearBoton("Crear Árbol 1", "Crea un nuevo árbol binario vacío");
        JButton btnInsertarNodo1 = crearBoton("Insertar en Árbol 1", "Inserta un nuevo valor en el árbol 1");
        JButton btnEliminarNodo1 = crearBoton("Eliminar de Árbol 1", "Elimina un valor del árbol 1");

        // Árbol 2 operations
        JButton btnCrearArbol2 = crearBoton("Crear Árbol 2", "Crea un nuevo árbol binario vacío");
        JButton btnInsertarNodo2 = crearBoton("Insertar en Árbol 2", "Inserta un nuevo valor en el árbol 2");
        JButton btnEliminarNodo2 = crearBoton("Eliminar de Árbol 2", "Elimina un valor del árbol 2");

        // Tree analysis operations - Row 1
        JButton btnVerificarIdenticos = crearBoton("Verificar si son Idénticos", "Compara si los árboles son idénticos");
        JButton btnAltura = crearBoton("Obtener Alturas (Recursivo)", "Muestra la altura de ambos árboles usando recursividad");
        JButton btnAlturaSinRecursividad = crearBoton("Obtener Alturas (No Recursivo)", "Muestra la altura de ambos árboles sin usar recursividad");
        JButton btnAmplitud = crearBoton("Recorrido Amplitud", "Muestra el recorrido en amplitud de ambos árboles");
        JButton btnNivel = crearBoton("Obtener Nivel", "Obtiene el nivel de un elemento en ambos árboles");
        JButton btnMinimo = crearBoton("Valor Mínimo", "Encuentra el valor mínimo en ambos árboles");

        // Tree analysis operations - Row 2
        JButton btnHojas = crearBoton("Contar Hojas", "Cuenta las hojas de ambos árboles");
        JButton btnImprimirHorizontal = crearBoton("Imprimir Horizontal", "Muestra los árboles en formato horizontal");
        JButton btnMinimoRecursivo = crearBoton("Valor Mínimo (Recursivo)", "Encuentra el valor mínimo en ambos árboles usando recursividad");

        // Adding buttons in order by row
        // First row - Basic operations for both trees
        panelBotones.add(btnCrearArbol1);
        panelBotones.add(btnInsertarNodo1);
        panelBotones.add(btnEliminarNodo1);
        panelBotones.add(btnCrearArbol2);
        panelBotones.add(btnInsertarNodo2);
        panelBotones.add(btnEliminarNodo2);

        // Second row - First set of analysis operations
        panelBotones.add(btnVerificarIdenticos);
        panelBotones.add(btnAltura);
        panelBotones.add(btnAlturaSinRecursividad);
        panelBotones.add(btnAmplitud);
        panelBotones.add(btnNivel);
        panelBotones.add(btnMinimo);

        // Third row - Second set of analysis operations
        panelBotones.add(btnHojas);
        panelBotones.add(btnImprimirHorizontal);
        panelBotones.add(btnMinimoRecursivo);
        // Adding empty panels to fill the remaining spots in the grid
        panelBotones.add(new JPanel());
        panelBotones.add(new JPanel());
        panelBotones.add(new JPanel());

        // Action Listeners
        btnCrearArbol1.addActionListener(e -> {
            arbol1 = new ArbolBinario();
            panelDibujo1.setArbol(arbol1);
            areaResultados.setText("Árbol 1 creado exitosamente.");
        });

        btnCrearArbol2.addActionListener(e -> {
            arbol2 = new ArbolBinario();
            panelDibujo2.setArbol(arbol2);
            areaResultados.setText("Árbol 2 creado exitosamente.");
        });

        btnInsertarNodo1.addActionListener(e -> insertarNodo(arbol1, panelDibujo1, "Árbol 1"));
        btnInsertarNodo2.addActionListener(e -> insertarNodo(arbol2, panelDibujo2, "Árbol 2"));
        btnEliminarNodo1.addActionListener(e -> eliminarNodo(arbol1, panelDibujo1, "Árbol 1"));
        btnEliminarNodo2.addActionListener(e -> eliminarNodo(arbol2, panelDibujo2, "Árbol 2"));

        btnVerificarIdenticos.addActionListener(e -> verificarIdenticos());
        btnAltura.addActionListener(e -> mostrarAlturas());
        btnAlturaSinRecursividad.addActionListener(e -> mostrarAlturasSinRecursividad());
        btnAmplitud.addActionListener(e -> mostrarRecorridoAmplitud());
        btnNivel.addActionListener(e -> obtenerNivel());
        btnMinimo.addActionListener(e -> obtenerMinimos());
        btnHojas.addActionListener(e -> contarHojas());
        btnImprimirHorizontal.addActionListener(e -> imprimirHorizontal());
        btnMinimoRecursivo.addActionListener(e -> obtenerMinimosRecursivo());
    }

    private void mostrarAlturasSinRecursividad() {
        StringBuilder resultado = new StringBuilder("Alturas de los árboles (sin recursividad):\n");
        if (arbol1 != null) {
            resultado.append("Árbol 1: ").append(arbol1.obtenerAlturaSinRecursividad()).append("\n");
        } else {
            resultado.append("Árbol 1: No creado\n");
        }
        if (arbol2 != null) {
            resultado.append("Árbol 2: ").append(arbol2.obtenerAlturaSinRecursividad());
        } else {
            resultado.append("Árbol 2: No creado");
        }
        areaResultados.setText(resultado.toString());
    }

    // Keep existing helper methods
    private JButton crearBoton(String texto, String tooltip) {
        JButton boton = new JButton(texto);
        boton.setToolTipText(tooltip);
        return boton;
    }

    private void insertarNodo(ArbolBinario arbol, PanelDibujo panel, String nombreArbol) {
        if (arbol == null) {
            JOptionPane.showMessageDialog(this,
                    "Primero debe crear el " + nombreArbol + ".",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            String valor = JOptionPane.showInputDialog("Ingrese el valor para " + nombreArbol + ":");
            if (valor != null && !valor.trim().isEmpty()) {
                arbol.insertar(Integer.parseInt(valor));
                panel.repaint();
                areaResultados.setText("Valor " + valor + " insertado en " + nombreArbol);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un número válido.");
        }
    }

    private void eliminarNodo(ArbolBinario arbol, PanelDibujo panel, String nombreArbol) {
        if (arbol == null) {
            JOptionPane.showMessageDialog(this,
                    "Primero debe crear el " + nombreArbol + ".",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            String valor = JOptionPane.showInputDialog("Ingrese el valor a eliminar de " + nombreArbol + ":");
            if (valor != null && !valor.trim().isEmpty()) {
                arbol.eliminar(Integer.parseInt(valor));
                panel.repaint();
                areaResultados.setText("Valor " + valor + " eliminado de " + nombreArbol);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un número válido.");
        }
    }

    private void verificarIdenticos() {
        if (arbol1 == null || arbol2 == null) {
            areaResultados.setText("Debe crear ambos árboles primero.");
            return;
        }
        boolean sonIdenticos = arbol1.sonIdenticos(arbol2);
        areaResultados.setText("Los árboles " + (sonIdenticos ? "son" : "no son") + " idénticos.");
    }

    private void mostrarAlturas() {
        StringBuilder resultado = new StringBuilder("Alturas de los árboles (recursivo):\n");
        if (arbol1 != null) {
            resultado.append("Árbol 1: ").append(arbol1.obtenerAltura(arbol1.raiz)).append("\n");
        } else {
            resultado.append("Árbol 1: No creado\n");
        }
        if (arbol2 != null) {
            resultado.append("Árbol 2: ").append(arbol2.obtenerAltura(arbol2.raiz));
        } else {
            resultado.append("Árbol 2: No creado");
        }
        areaResultados.setText(resultado.toString());
    }

    private void mostrarRecorridoAmplitud() {
        StringBuilder resultado = new StringBuilder("Recorrido en amplitud:\n");
        if (arbol1 != null) {
            resultado.append("Árbol 1: ").append(arbol1.recorridoAmplitud()).append("\n");
        } else {
            resultado.append("Árbol 1: No creado\n");
        }
        if (arbol2 != null) {
            resultado.append("Árbol 2: ").append(arbol2.recorridoAmplitud());
        } else {
            resultado.append("Árbol 2: No creado");
        }
        areaResultados.setText(resultado.toString());
    }

    private void obtenerNivel() {
        try {
            String valor = JOptionPane.showInputDialog("Ingrese el valor a buscar:");
            if (valor != null && !valor.trim().isEmpty()) {
                int num = Integer.parseInt(valor);
                StringBuilder resultado = new StringBuilder("Nivel del valor " + valor + ":\n");
                if (arbol1 != null) {
                    int nivel1 = arbol1.obtenerNivel(num);
                    resultado.append("Árbol 1: ").append(nivel1 != -1 ? nivel1 : "No encontrado").append("\n");
                } else {
                    resultado.append("Árbol 1: No creado\n");
                }
                if (arbol2 != null) {
                    int nivel2 = arbol2.obtenerNivel(num);
                    resultado.append("Árbol 2: ").append(nivel2 != -1 ? nivel2 : "No encontrado");
                } else {
                    resultado.append("Árbol 2: No creado");
                }
                areaResultados.setText(resultado.toString());
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un número válido.");
        }
    }

    private void obtenerMinimos() {
        StringBuilder resultado = new StringBuilder("Valores mínimos:\n");
        if (arbol1 != null && arbol1.raiz != null) {
            resultado.append("Árbol 1: ").append(arbol1.obtenerMinimoNoRecursivo()).append("\n");
        } else {
            resultado.append("Árbol 1: No creado o vacío\n");
        }
        if (arbol2 != null && arbol2.raiz != null) {
            resultado.append("Árbol 2: ").append(arbol2.obtenerMinimoNoRecursivo());
        } else {
            resultado.append("Árbol 2: No creado o vacío");
        }
        areaResultados.setText(resultado.toString());
    }
    private void contarHojas() {
        StringBuilder resultado = new StringBuilder("Número de hojas:\n");
        if (arbol1 != null) {
            resultado.append("Árbol 1: ").append(arbol1.contarHojas(arbol1.raiz)).append("\n");
        } else {
            resultado.append("Árbol 1: No creado\n");
        }
        if (arbol2 != null) {
            resultado.append("Árbol 2: ").append(arbol2.contarHojas(arbol2.raiz));
        } else {
            resultado.append("Árbol 2: No creado");
        }
        areaResultados.setText(resultado.toString());
    }
    private void imprimirHorizontal() {
        StringBuilder resultado = new StringBuilder("Representación horizontal de los árboles:\n\n");

        if (arbol1 != null) {
            resultado.append("Árbol 1:\n");
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(new OutputStream() {
                @Override
                public void write(int b) {
                    pw.write(b);
                }
            }));

            arbol1.imprimirHorizontal();
            System.setOut(originalOut);
            resultado.append(sw.toString()).append("\n\n");
        } else {
            resultado.append("Árbol 1: No creado\n\n");
        }

        if (arbol2 != null) {
            resultado.append("Árbol 2:\n");
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(new OutputStream() {
                @Override
                public void write(int b) {
                    pw.write(b);
                }
            }));

            arbol2.imprimirHorizontal();
            System.setOut(originalOut);
            resultado.append(sw.toString());
        } else {
            resultado.append("Árbol 2: No creado");
        }

        areaResultados.setText(resultado.toString());
    }
    private void obtenerMinimosRecursivo() {
        StringBuilder resultado = new StringBuilder("Valores mínimos (recursivo):\n");
        if (arbol1 != null && arbol1.raiz != null) {
            try {
                resultado.append("Árbol 1: ").append(arbol1.obtenerMinimoRecursivo()).append("\n");
            } catch (RuntimeException ex) {
                resultado.append("Árbol 1: Vacío\n");
            }
        } else {
            resultado.append("Árbol 1: No creado o vacío\n");
        }
        if (arbol2 != null && arbol2.raiz != null) {
            try {
                resultado.append("Árbol 2: ").append(arbol2.obtenerMinimoRecursivo());
            } catch (RuntimeException ex) {
                resultado.append("Árbol 2: Vacío");
            }
        } else {
            resultado.append("Árbol 2: No creado o vacío");
        }
        areaResultados.setText(resultado.toString());
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ArbolBinarioApp().setVisible(true);
        });
    }
}