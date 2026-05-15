package com.example.tda.generic_trie;

import java.util.List;
import java.util.function.Consumer;

public interface TNodoGenerico<T extends Comparable<T>> {
    T getDato();

    boolean agregarHijo(T padre, T hijo);

    TNodoGenerico<T> eliminar(Comparable<T> criterio);

    TNodoGenerico<T> buscar(Comparable<T> criterio);

    TNodoGenerico<T> obtenerPadre(Comparable<T> criterio);

    void preOrden(Consumer<TNodoGenerico<T>> consumidor);

    void inOrden(Consumer<TNodoGenerico<T>> consumidor);

    void postOrden(Consumer<TNodoGenerico<T>> consumidor);

    int altura();

    int grado();

    void vaciar();

    List<T> obtenerHijos();
}
