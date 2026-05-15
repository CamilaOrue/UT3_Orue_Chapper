import java.util.ArrayList;

public class ArbolGenealogico {
    private NodoArbol raiz;

    public ArbolGenealogico(NodoArbol raiz) {
        this.raiz = raiz;

    }

    public ArrayList<Persona> listarDescendientes(NodoArbol nodo) {
        ArrayList<Persona> lista = new ArrayList<>();
        for (NodoArbol hijo : nodo.getHijos()) {
            lista.add(hijo.getPersona());
            lista.addAll(listarDescendientes(hijo));
        }
        return lista;

    }

    public int calcularAltura(NodoArbol nodo) {
        if (nodo.getHijos().isEmpty()) {
            return 0;
        }
        int maxAltura = 0;
        for (NodoArbol hijo : nodo.getHijos()) {
            int alturaHijo = calcularAltura(hijo);
            if (alturaHijo > maxAltura) {
                maxAltura = alturaHijo;
            }
        }
        return maxAltura + 1;

    }

    public int contarCantidadPersonas(NodoArbol nodo) {
        if (nodo.getHijos().isEmpty()) {
            return 1;
        }
        int cantPersonas = 0;
        for (NodoArbol hijo : nodo.getHijos()) {
            cantPersonas += contarCantidadPersonas(hijo);

        }
        return cantPersonas + 1;
    }

    public ArrayList<Persona> obtenerGeneracionCompleta(NodoArbol nodo, int generacion) {
        ArrayList<Persona> lista = new ArrayList<>();
        if (generacion == 0) {
            lista.add(nodo.getPersona());
            return lista;
        }
        for (NodoArbol hijo : nodo.getHijos()) {
            lista.addAll(obtenerGeneracionCompleta(hijo, generacion - 1));
        }

        return lista;

    }

    public boolean esDescendiente(NodoArbol ancestro, NodoArbol buscado) {
        if (ancestro.getHijos().isEmpty()) {
            return false;
        }
        for (NodoArbol hijo : ancestro.getHijos()) {
            if (hijo.getPersona().getNombre().equals(buscado.getPersona().getNombre())) {
                return true;

            }
            if (esDescendiente(hijo, buscado)) {
                return true;
            }
        }

        return false;

    }

    public NodoArbol ancestroComun(NodoArbol raiz, NodoArbol nodo1, NodoArbol nodo2) {
        for (NodoArbol hijo : raiz.getHijos()) {
            NodoArbol resultado = ancestroComun(hijo, nodo1, nodo2);
            if (resultado != null) {
                return resultado;
            }
        }
        if (esDescendiente(raiz, nodo1) && esDescendiente(raiz, nodo2)) {
            return raiz;
        }
        return null;
    }
}
