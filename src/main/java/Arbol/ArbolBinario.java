package Arbol;


import java.util.LinkedList;
import java.util.Queue;

public class ArbolBinario {
    public Nodo raiz;

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
}
