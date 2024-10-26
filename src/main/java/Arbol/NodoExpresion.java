package Arbol;

public class NodoExpresion {
        public String valor;
        public NodoExpresion izquierdo, derecho;

        public NodoExpresion(String valor) {
            this.valor = valor;
            izquierdo = derecho = null;
        }

}
