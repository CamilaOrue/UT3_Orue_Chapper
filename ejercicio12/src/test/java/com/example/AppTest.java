package com.example;

import com.example.tda.trie.Entry;
import com.example.tda.trie.TTrie;
import com.example.tda.trie.impl.TTrieHashMap;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AppTest {

    @Test
    public void testAutocompletar() {

        TTrie<Integer> trie = new TTrieHashMap<>();

        trie.insertar("algoritmo", 1);
        trie.insertar("algebra", 1);
        trie.insertar("alemania", 1);

        List<Entry<Integer>> resultados = trie.predecir("al");

        assertFalse(resultados.isEmpty());
        assertEquals(3, resultados.size());
    }

    @Test
    public void testBuscarPatronExistente() {

        String texto = "panamabanana";

        TTrie<List<Integer>> trie = new TTrieHashMap<>();

        for (int i = 0; i < texto.length(); i++) {

            String sufijo = texto.substring(i);

            Entry<List<Integer>> existente = trie.buscar(sufijo);

            List<Integer> posiciones =
                    (existente != null)
                            ? existente.getDato()
                            : new LinkedList<>();

            posiciones.add(i);

            trie.insertar(sufijo, posiciones);
        }

        List<Entry<List<Integer>>> resultados =
                trie.predecir("ana");

        assertFalse(resultados.isEmpty());
    }

    @Test
    public void testBuscarPatronInexistente() {

        String texto = "panamabanana";

        TTrie<List<Integer>> trie = new TTrieHashMap<>();

        for (int i = 0; i < texto.length(); i++) {

            trie.insertar(
                    texto.substring(i),
                    new LinkedList<>()
            );
        }

        List<Entry<List<Integer>>> resultados =
                trie.predecir("casa");

        assertTrue(resultados.isEmpty());
    }
}