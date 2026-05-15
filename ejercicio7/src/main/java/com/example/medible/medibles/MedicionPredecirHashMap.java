package com.example.medible.medibles;

import com.example.medible.lib.Medible;

import java.util.List;
import java.util.Map;

public class MedicionPredecirHashMap extends Medible<List<String>> {

    private final Map<String, String> map;

    public MedicionPredecirHashMap(Map<String, String> map) {
        this.map = map;
    }

    @Override
    public void ejecutar(int repeticiones, List<String> prefijos) {
        for (int i = 0; i < repeticiones; i++) {
            for (String prefijo : prefijos) {
                for (String clave : map.keySet()) {
                    clave.startsWith(prefijo);
                }
            }
        }
    }

    @Override
    public Object getObjetoAMedirMemoria() {
        return this.map;
    }
}
