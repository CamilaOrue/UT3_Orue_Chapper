package com.example.medible.medibles;

import com.example.medible.lib.Medible;

import java.util.List;
import java.util.Map;

public class MedicionBuscarHashMap extends Medible<List<String>> {

    private final Map<String, String> map;

    public MedicionBuscarHashMap(Map<String, String> map) {
        this.map = map;
    }

    @Override
    public void ejecutar(int repeticiones, List<String> palabras) {
        for (int i = 0; i < repeticiones; i++) {
            for (String palabra : palabras) {
                map.get(palabra);
            }
        }
    }

    @Override
    public Object getObjetoAMedirMemoria() {
        return this.map;
    }
}
