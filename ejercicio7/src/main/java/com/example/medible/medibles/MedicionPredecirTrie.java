package com.example.medible.medibles;

import com.example.medible.lib.Medible;
import com.example.tda.trie.TTrie;

import java.util.List;

public class MedicionPredecirTrie extends Medible<List<String>> {

    private final TTrie<String> trie;

    public MedicionPredecirTrie(TTrie<String> trie) {
        this.trie = trie;
    }

    @Override
    public void ejecutar(int repeticiones, List<String> prefijos) {
        for (int i = 0; i < repeticiones; i++) {
            for (String prefijo : prefijos) {
                trie.predecir(prefijo);
            }
        }
    }

    @Override
    public Object getObjetoAMedirMemoria() {
        return this.trie;
    }
}
