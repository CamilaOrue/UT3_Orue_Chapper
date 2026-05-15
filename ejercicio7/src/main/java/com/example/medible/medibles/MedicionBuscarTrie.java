package com.example.medible.medibles;

import com.example.medible.lib.Medible;
import com.example.tda.trie.TTrie;

import java.util.List;

public class MedicionBuscarTrie extends Medible<List<String>> {

    private final TTrie<String> trie;

    public MedicionBuscarTrie(TTrie<String> trie) {
        this.trie = trie;
    }

    @Override
    public void ejecutar(int repeticiones, List<String> palabras) {
        for (int i = 0; i < repeticiones; i++) {
            for (String palabra : palabras) {
                trie.buscar(palabra);
            }
        }
    }

    @Override
    public Object getObjetoAMedirMemoria() {
        return this.trie;
    }
}
