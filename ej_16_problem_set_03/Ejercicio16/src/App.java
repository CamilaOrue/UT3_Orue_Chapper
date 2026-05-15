import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        NodoArbol abuela = new NodoArbol(new Persona("Abuela", 1920));
        NodoArbol hijo1 = new NodoArbol(new Persona("Hijo1", 1945));
        NodoArbol hijo2 = new NodoArbol(new Persona("Hijo2", 1946));
        NodoArbol hijo3 = new NodoArbol(new Persona("Hijo3", 1947));
        abuela.agregarHijo(hijo1);
        abuela.agregarHijo(hijo2);
        abuela.agregarHijo(hijo3);

        NodoArbol nieto1 = new NodoArbol(new Persona("Nieto1", 1976));
        NodoArbol nieto2 = new NodoArbol(new Persona("Nieto2", 1980));
        hijo2.agregarHijo(nieto1);
        hijo2.agregarHijo(nieto2);

        NodoArbol nieto3 = new NodoArbol(new Persona("Nieto3", 1980));
        NodoArbol nieto4 = new NodoArbol(new Persona("Nieto4", 1982));
        NodoArbol nieto5 = new NodoArbol(new Persona("Nieto5", 1985));
        NodoArbol nieto6 = new NodoArbol(new Persona("Nieto6", 1986));
        hijo1.agregarHijo(nieto3);
        hijo3.agregarHijo(nieto4);
        hijo3.agregarHijo(nieto5);
        hijo3.agregarHijo(nieto6);

        ArbolGenealogico arbol = new ArbolGenealogico(abuela);
        ArrayList<Persona> descendientes = arbol.listarDescendientes(abuela.getHijos().get(1));
        for (Persona p : descendientes) {
            System.out.println(p.getNombre());
        }

        System.out.println(arbol.calcularAltura(abuela));
        System.out.println(arbol.contarCantidadPersonas(abuela));
        for (Persona p : arbol.obtenerGeneracionCompleta(abuela, 0)) {
            System.out.println(p.getNombre());
        }
        for (Persona p : arbol.obtenerGeneracionCompleta(abuela, 1)) {
            System.out.println(p.getNombre());
        }
        for (Persona p : arbol.obtenerGeneracionCompleta(abuela, 2)) {
            System.out.println(p.getNombre());
        }
    }
}
