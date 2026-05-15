package com.example;

import com.example.tda.trie.Entry;
import com.example.tda.trie.TTrie;
import com.example.tda.trie.impl.TTrieHashMap;
import java.util.LinkedList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        
        // --- PRUEBA 1: AUTOCOMPLETAR ---
        System.out.println("=== PRUEBA 1: AUTOCOMPLETAR ===");
        TTrie<Integer> trieAuto = new TTrieHashMap<>();
        String[] palabras = {"algoritmo", "algebra", "alemania", "alicia", "altillo", "barco"};
        
        for (String p : palabras) {
            trieAuto.insertar(p, 1);
        }

        String prefijo = "al";
        List<Entry<Integer>> sugerencias = trieAuto.predecir(prefijo);
        
        System.out.println("Sugerencias para '" + prefijo + "':");
        for (Entry<Integer> e : sugerencias) {
            System.out.println("  -> " + e.getPalabra());
        }
        System.out.println();


        // --- PRUEBA 2: BÚSQUEDA DE PATRONES (Suffix Tree) ---
        System.out.println("=== PRUEBA 2: BÚSQUEDA DE PATRONES ===");
        String texto = "panamabanana";
        TTrie<List<Integer>> trieSufijos = new TTrieHashMap<>();

        // Construcción del árbol de sufijos
        for (int i = 0; i < texto.length(); i++) {
            String sufijo = texto.substring(i);
            
            // Verificamos si el sufijo ya existe para no perder posiciones previas
            Entry<List<Integer>> existente = trieSufijos.buscar(sufijo);
            List<Integer> posiciones = (existente != null) ? existente.getDato() : new LinkedList<>();
            
            posiciones.add(i);
            trieSufijos.insertar(sufijo, posiciones);
        }

        // Caso A: Patrón que aparece varias veces
        probarPatron(trieSufijos, "ana");
        
        // Caso B: Patrón que aparece una vez
        probarPatron(trieSufijos, "pan");
        
        // Caso C: Patrón que NO existe
        probarPatron(trieSufijos, "casa");
    }

    private static void probarPatron(TTrie<List<Integer>> trie, String patron) {

    List<Entry<List<Integer>>> resultados = trie.predecir(patron);

    if (!resultados.isEmpty()) {

        List<Integer> posiciones = new LinkedList<>();

        for (Entry<List<Integer>> e : resultados) {
            posiciones.addAll(e.getDato());
        }

        System.out.println(
            "Patrón '" + patron + "' encontrado en posiciones: " + posiciones
        );

    } else {

        System.out.println(
            "Patrón '" + patron + "' NO encontrado."
        );
    }
}
}