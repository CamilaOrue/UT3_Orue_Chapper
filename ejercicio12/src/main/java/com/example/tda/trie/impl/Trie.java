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

    
}
