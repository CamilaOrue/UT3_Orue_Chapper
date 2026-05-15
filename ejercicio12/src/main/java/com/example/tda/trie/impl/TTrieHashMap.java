package com.example.tda.trie.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

import com.example.tda.trie.Entry;
import com.example.tda.trie.TTrie;


public class TTrieHashMap<T> implements TTrie<T> {

    private final TNodoTrieHashMap<T> raiz;

    public TTrieHashMap() {
        this.raiz = new TNodoTrieHashMap<>();
    }

    @Override
    public boolean insertar(String palabra, T dato) {

        if (palabra == null || palabra.isEmpty()) {
            return false;
        }

        return raiz.insertar(
            palabra.toLowerCase(),
            dato
        );
    }

    @Override
    public Entry<T> buscar(String palabra) {

        if (palabra == null || palabra.isEmpty()) {
            return null;
        }

        return raiz.buscar(
            palabra.toLowerCase()
        );
    }

    @Override
    public List<Entry<T>> predecir(String prefijo) {

        List<Entry<T>> resultados = new LinkedList<>();

        if (prefijo != null && !prefijo.isEmpty()) {

            raiz.predecir(
                prefijo.toLowerCase(),
                resultados
            );
        }

        return resultados;
    }

    @Override
    public void recorrer(Consumer<Entry<T>> consumer) {

        if (consumer != null) {
            raiz.recorrer("", consumer);
        }
    }
}