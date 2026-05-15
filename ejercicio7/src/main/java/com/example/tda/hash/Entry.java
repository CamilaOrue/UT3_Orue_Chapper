package com.example.tda.hash;

public class Entry<K, V> {
    private final K clave;
    private final V valor;

    public Entry(K clave, V valor) {
        this.clave = clave;
        this.valor = valor;
    }

    public K getClave() {
        return clave;
    }

    public V getValor() {
        return valor;
    }
}
