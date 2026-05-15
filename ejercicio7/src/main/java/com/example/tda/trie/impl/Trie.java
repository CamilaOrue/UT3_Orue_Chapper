package com.example.tda.trie.impl;

import com.example.tda.trie.Entry;
import com.example.tda.trie.TTrie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class Trie<T> implements TTrie<T>, Serializable {

    private final NodoTrie<T> raiz;

    public Trie() {
        this.raiz = new NodoTrie<>();
    }

    @Override
    public boolean insertar(String palabra, T dato) {
        if (palabra == null || palabra.isEmpty()) return false;
        return raiz.insertar(palabra, dato);
    }

    @Override
    public Entry<T> buscar(String palabra) {
        if (palabra == null || palabra.isEmpty()) return null;
        return raiz.buscar(palabra);
    }

    @Override
    public List<Entry<T>> predecir(String prefijo) {
        if (prefijo == null) return new ArrayList<>();
        return raiz.predecir(prefijo);
    }

    @Override
    public void recorrer(Consumer<Entry<T>> consumer) {
        raiz.recorrer(consumer);
    }

    // ---- Nodo interno ----

    private static class NodoTrie<T> implements Serializable {
        private T dato;
        private boolean esPalabra;
        private final Map<Character, NodoTrie<T>> hijos;

        NodoTrie() {
            this.hijos = new HashMap<>();
            this.esPalabra = false;
        }

        boolean insertar(String palabra, T dato) {
            NodoTrie<T> actual = this;
            for (char c : palabra.toCharArray()) {
                actual.hijos.putIfAbsent(c, new NodoTrie<>());
                actual = actual.hijos.get(c);
            }
            if (actual.esPalabra) return false;
            actual.esPalabra = true;
            actual.dato = dato;
            return true;
        }

        Entry<T> buscar(String palabra) {
            NodoTrie<T> actual = this;
            for (char c : palabra.toCharArray()) {
                if (!actual.hijos.containsKey(c)) return null;
                actual = actual.hijos.get(c);
            }
            return new Entry<>(actual.dato, actual.esPalabra, palabra);
        }

        List<Entry<T>> predecir(String prefijo) {
            List<Entry<T>> resultado = new ArrayList<>();
            NodoTrie<T> actual = this;
            for (char c : prefijo.toCharArray()) {
                if (!actual.hijos.containsKey(c)) return resultado;
                actual = actual.hijos.get(c);
            }
            recolectar(actual, prefijo, resultado);
            return resultado;
        }

        private void recolectar(NodoTrie<T> nodo, String prefijo, List<Entry<T>> resultado) {
            if (nodo.esPalabra) {
                resultado.add(new Entry<>(nodo.dato, true, prefijo));
            }
            for (Map.Entry<Character, NodoTrie<T>> entry : nodo.hijos.entrySet()) {
                recolectar(entry.getValue(), prefijo + entry.getKey(), resultado);
            }
        }

        void recorrer(Consumer<Entry<T>> consumer) {
            recorrer(this, "", consumer);
        }

        private void recorrer(NodoTrie<T> nodo, String actual, Consumer<Entry<T>> consumer) {
            if (nodo.esPalabra) {
                consumer.accept(new Entry<>(nodo.dato, true, actual));
            }
            for (Map.Entry<Character, NodoTrie<T>> entry : nodo.hijos.entrySet()) {
                recorrer(entry.getValue(), actual + entry.getKey(), consumer);
            }
        }
    }
}
