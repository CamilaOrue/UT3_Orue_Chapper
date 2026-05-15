package com.example.tda.trie.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import com.example.tda.trie.Entry;

public class TNodoTrieHashMap<T> {

    private final Map<Character, TNodoTrieHashMap<T>> hijos;
    private T dato;
    private boolean esPalabra;

    public TNodoTrieHashMap() {
        this.hijos = new HashMap<>();
        this.esPalabra = false;
    }

    public boolean insertar(String palabra, T dato) {
        TNodoTrieHashMap<T> nodo = this;

        for (char c : palabra.toCharArray()) {
            nodo.hijos.putIfAbsent(c, new TNodoTrieHashMap<>());
            nodo = nodo.hijos.get(c);
        }

        if (nodo.esPalabra) {
            return false;
        }

        nodo.esPalabra = true;
        nodo.dato = dato;

        return true;
    }

    public Entry<T> buscar(String palabra) {
        TNodoTrieHashMap<T> nodo = this;

        for (char c : palabra.toCharArray()) {
            nodo = nodo.hijos.get(c);

            if (nodo == null) {
                return null;
            }
        }

        if (!nodo.esPalabra) {
            return null;
        }

        return new Entry<>(nodo.dato, true, palabra);
    }

    public void predecir(String prefijo, List<Entry<T>> resultados) {
        TNodoTrieHashMap<T> nodoBase = buscarNodo(prefijo);

        if (nodoBase != null) {
            nodoBase.recorrer(prefijo, resultados::add);
        }
    }

    private TNodoTrieHashMap<T> buscarNodo(String prefijo) {
        TNodoTrieHashMap<T> nodo = this;

        for (char c : prefijo.toCharArray()) {
            nodo = nodo.hijos.get(c);

            if (nodo == null) {
                return null;
            }
        }

        return nodo;
    }

    public void recorrer(String prefijoAcumulado, Consumer<Entry<T>> consumer) {

        if (this.esPalabra) {
            consumer.accept(new Entry<>(this.dato, true, prefijoAcumulado));
        }

        for (Map.Entry<Character, TNodoTrieHashMap<T>> entry : hijos.entrySet()) {
            entry.getValue().recorrer(
                prefijoAcumulado + entry.getKey(),
                consumer
            );
        }
    }
}