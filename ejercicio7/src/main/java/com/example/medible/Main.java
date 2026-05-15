package com.example.medible;

import com.example.medible.lib.Medible;
import com.example.medible.lib.Medicion;
import com.example.medible.medibles.*;
import com.example.tda.trie.TTrie;
import com.example.tda.trie.impl.Trie;
import com.example.utils.FileUtils;

import java.util.*;

public class Main {

    private static final int REPETICIONES = 20;

    public static void main(String[] args) {

        // Estructuras para buscar
        TTrie<String> trie = new Trie<>();
        LinkedList<String> linkedList = new LinkedList<>();
        ArrayList<String> arrayList = new ArrayList<>();
        Map<String, String> hashMap = new HashMap<>();
        Map<String, String> treeMap = new TreeMap<>();

        // Estructuras para predecir
        TTrie<String> triePredecir = new Trie<>();
        LinkedList<String> linkedListPredecir = new LinkedList<>();
        Map<String, String> hashMapPredecir = new HashMap<>();

        List<String> palabrasParaAgregar = new LinkedList<>();
        List<String> palabrasParaBuscar = new LinkedList<>();

        FileUtils.leerLineas("ut03/listado-general-desordenado.txt", palabrasParaAgregar::add);
        FileUtils.leerLineas("ut03/listado-general-palabrasBuscar.txt", palabrasParaBuscar::add);

        for (String p : palabrasParaAgregar) {
            trie.insertar(p, p);
            linkedList.add(p);
            arrayList.add(p);
            hashMap.put(p, p);
            treeMap.put(p, p);

            triePredecir.insertar(p, p);
            linkedListPredecir.add(p);
            hashMapPredecir.put(p, p);
        }

        // ---- Parte 3 y 4: Buscar ----
        List<Medible<List<String>>> mediblesBuscar = new LinkedList<>();
        mediblesBuscar.add(new MedicionBuscarLinkedList(linkedList));
        mediblesBuscar.add(new MedicionBuscarArrayList(arrayList));
        mediblesBuscar.add(new MedicionBuscarTrie(trie));
        mediblesBuscar.add(new MedicionBuscarHashMap(hashMap));
        mediblesBuscar.add(new MedicionBuscarTreeMap(treeMap));

        StringBuilder sbBuscar = new StringBuilder();
        sbBuscar.append("algoritmo,memoria,tiempo\n");

        System.out.println("=== BUSCAR ===");
        for (Medible<List<String>> m : mediblesBuscar) {
            Medicion mi = m.medir(REPETICIONES, palabrasParaBuscar);
            mi.print();
        }

        //ileUtils.escribirLineas("salida-buscar.csv", sbBuscar.toString());

        // ---- Parte 5: Predecir con prefijo "cas" ----
        List<String> prefijos = new LinkedList<>();
        prefijos.add("cas");

        List<Medible<List<String>>> mediblesPredecir = new LinkedList<>();
        mediblesPredecir.add(new MedicionPredecirTrie(triePredecir));
        mediblesPredecir.add(new MedicionPredecirLinkedList(linkedListPredecir));
        mediblesPredecir.add(new MedicionPredecirHashMap(hashMapPredecir));

        StringBuilder sbPredecir = new StringBuilder();
        sbPredecir.append("algoritmo,memoria,tiempo\n");

        System.out.println("=== PREDECIR ===");
        for (Medible<List<String>> m : mediblesPredecir) {
            Medicion mi = m.medir(REPETICIONES, prefijos);
            mi.print();
        }
    }
}
