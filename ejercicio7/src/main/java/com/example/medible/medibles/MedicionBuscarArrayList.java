package com.example.medible.medibles;

import com.example.medible.lib.Medible;

import java.util.ArrayList;
import java.util.List;

public class MedicionBuscarArrayList extends Medible<List<String>> {

    private final ArrayList<String> list;

    public MedicionBuscarArrayList(ArrayList<String> list) {
        this.list = list;
    }

    @Override
    public void ejecutar(int repeticiones, List<String> palabras) {
        for (int i = 0; i < repeticiones; i++) {
            for (String palabra : palabras) {
                list.contains(palabra);
            }
        }
    }

    @Override
    public Object getObjetoAMedirMemoria() {
        return this.list;
    }
}
