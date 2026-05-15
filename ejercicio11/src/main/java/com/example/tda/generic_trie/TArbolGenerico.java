package com.example.tda.generic_trie;

import java.util.function.Consumer;

public interface TArbolGenerico<T extends Comparable<T>> {
    boolean agregarHijo(Comparable<T> padre, T hijo);

    void eliminar(Comparable<T> criterio);

    T obtenerPadre(Comparable<T> criterio);

    T buscar(Comparable<T> criterio);

    void preOrden(Consumer<T> consumidor);

    void inOrden(Consumer<T> consumidor);

    void postOrden(Consumer<T> consumidor);

    void vaciar();

    int grado(Comparable<T> nodo);

    int altura(Comparable<T> nodo);
}
