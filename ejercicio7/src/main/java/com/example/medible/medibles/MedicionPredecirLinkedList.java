package com.example.medible.medibles;

import com.example.medible.lib.Medible;

import java.util.LinkedList;
import java.util.List;

public class MedicionPredecirLinkedList extends Medible<List<String>> {

    private final LinkedList<String> list;

    public MedicionPredecirLinkedList(LinkedList<String> list) {
        this.list = list;
    }

    @Override
    public void ejecutar(int repeticiones, List<String> prefijos) {
        for (int i = 0; i < repeticiones; i++) {
            for (String prefijo : prefijos) {
                for (String palabra : list) {
                    palabra.startsWith(prefijo);
                }
            }
        }
    }

    @Override
    public Object getObjetoAMedirMemoria() {
        return this.list;
    }
}
