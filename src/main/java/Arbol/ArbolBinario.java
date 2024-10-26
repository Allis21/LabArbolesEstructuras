package Arbol;


import java.util.LinkedList;
import java.util.Queue;

public class ArbolBinario {
    public Nodo raiz;
    public NodoExpresion raizExpresion;

    public void insertar(int valor) {
        raiz = insertarRec(raiz, valor);
    }

    private Nodo insertarRec(Nodo raiz, int valor) {
        if (raiz == null) {
            raiz = new Nodo(valor);
            return raiz;
        }
        if (valor < raiz.valor)
            raiz.izquierdo = insertarRec(raiz.izquierdo, valor);
        else if (valor > raiz.valor)
            raiz.derecho = insertarRec(raiz.derecho, valor);
        return raiz;
    }

    public void eliminar(int valor) {
        raiz = eliminarRec(raiz, valor);
    }

    private Nodo eliminarRec(Nodo raiz, int valor) {
        if (raiz == null) return null;
        if (valor < raiz.valor)
            raiz.izquierdo = eliminarRec(raiz.izquierdo, valor);
        else if (valor > raiz.valor)
            raiz.derecho = eliminarRec(raiz.derecho, valor);
        else {
            if (raiz.izquierdo == null) return raiz.derecho;
            if (raiz.derecho == null) return raiz.izquierdo;

            raiz.valor = obtenerMinimo(raiz.derecho);
            raiz.derecho = eliminarRec(raiz.derecho, raiz.valor);
        }
        return raiz;
    }

    public int obtenerAltura(Nodo nodo) {
        if (nodo == null) return 0;
        return 1 + Math.max(obtenerAltura(nodo.izquierdo), obtenerAltura(nodo.derecho));
    }

    public int contarHojas(Nodo nodo) {
        if (nodo == null) return 0;
        if (nodo.izquierdo == null && nodo.derecho == null) return 1;
        return contarHojas(nodo.izquierdo) + contarHojas(nodo.derecho);
    }

    public int obtenerMinimo(Nodo nodo) {
        while (nodo.izquierdo != null) nodo = nodo.izquierdo;
        return nodo.valor;
    }

    public String recorridoAmplitud() {
        if (raiz == null) return "";
        Queue<Nodo> queue = new LinkedList<>();
        queue.add(raiz);
        StringBuilder resultado = new StringBuilder();
        while (!queue.isEmpty()) {
            Nodo actual = queue.poll();
            resultado.append(actual.valor).append(" ");
            if (actual.izquierdo != null) queue.add(actual.izquierdo);
            if (actual.derecho != null) queue.add(actual.derecho);
        }
        return resultado.toString();
    }
    public int obtenerNivel(int valor) {
        return obtenerNivelRec(raiz, valor, 1);
    }

    private int obtenerNivelRec(Nodo nodo, int valor, int nivel) {
        if (nodo == null) return -1; // Si el nodo es nulo, el valor no se encuentra
        if (nodo.valor == valor) return nivel;

        int resultadoIzq = obtenerNivelRec(nodo.izquierdo, valor, nivel + 1);
        if (resultadoIzq != -1) return resultadoIzq; // Si encontró en el lado izquierdo, retorna

        return obtenerNivelRec(nodo.derecho, valor, nivel + 1); // Busca en el lado derecho
    }
    public int obtenerMinimoNoRecursivo() {
        if (raiz == null) throw new RuntimeException("El árbol está vacío");
        Nodo actual = raiz;
        while (actual.izquierdo != null) {
            actual = actual.izquierdo;
        }
        return actual.valor;
    }
    public void imprimirHorizontal() {
        imprimirHorizontalRec(raiz, 0);
    }

    private void imprimirHorizontalRec(Nodo nodo, int nivel) {
        if (nodo == null) return;
        imprimirHorizontalRec(nodo.derecho, nivel + 1);
        System.out.println("    ".repeat(nivel) + nodo.valor);
        imprimirHorizontalRec(nodo.izquierdo, nivel + 1);
    }
    public boolean sonIdenticos(ArbolBinario otroArbol) {
        return sonIdenticosRec(this.raiz, otroArbol.raiz);
    }

    private boolean sonIdenticosRec(Nodo nodo1, Nodo nodo2) {
        if (nodo1 == null && nodo2 == null) return true;
        if (nodo1 == null || nodo2 == null) return false;
        return (nodo1.valor == nodo2.valor) &&
                sonIdenticosRec(nodo1.izquierdo, nodo2.izquierdo) &&
                sonIdenticosRec(nodo1.derecho, nodo2.derecho);
    }
    public int obtenerAlturaSinRecursividad() {
        if (raiz == null) return 0;
        Queue<Nodo> cola = new LinkedList<>();
        cola.add(raiz);
        int altura = 0;

        while (!cola.isEmpty()) {
            int cantidadNodos = cola.size();
            altura++;
            for (int i = 0; i < cantidadNodos; i++) {
                Nodo actual = cola.poll();
                if (actual.izquierdo != null) cola.add(actual.izquierdo);
                if (actual.derecho != null) cola.add(actual.derecho);
            }
        }
        return altura;
    }
    public int obtenerMinimoRecursivo() {
        if (raiz == null) {
            throw new RuntimeException("El árbol está vacío");
        }
        return obtenerMinimoRecursivo(raiz);
    }

    private int obtenerMinimoRecursivo(Nodo nodo) {
        // Caso base: si no hay hijo izquierdo, este es el valor mínimo
        if (nodo.izquierdo == null) {
            return nodo.valor;
        }
        // Caso recursivo: seguir buscando en el subárbol izquierdo
        return obtenerMinimoRecursivo(nodo.izquierdo);
    }

    public void dibujarExpresionA() {
        NodoExpresion nodoMultiplicacion = new NodoExpresion("*");
        NodoExpresion nodoDivision = new NodoExpresion("/");
        NodoExpresion nodoSuma = new NodoExpresion("+");

        NodoExpresion nodoA = new NodoExpresion("a");
        NodoExpresion nodoB = new NodoExpresion("b");
        NodoExpresion nodoC = new NodoExpresion("c");
        NodoExpresion nodoD = new NodoExpresion("d");

        nodoMultiplicacion.izquierdo = nodoA;
        nodoMultiplicacion.derecho = nodoB;
        nodoDivision.izquierdo = nodoC;
        nodoDivision.derecho = nodoD;

        nodoSuma.izquierdo = nodoMultiplicacion;
        nodoSuma.derecho = nodoDivision;

        raizExpresion = nodoSuma;
    }


}
