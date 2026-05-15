package com.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

public class AppTest {

    @Test
    public void testConteoFrecuencias() {

        String texto = "hola hola mundo";

        Map<String, Integer> frecuencias = new HashMap<>();

        String[] palabras =
                texto.toLowerCase().split("[\\p{Punct}\\s]+");

        for (String palabra : palabras) {

            if (!palabra.isEmpty()) {

                frecuencias.put(
                        palabra,
                        frecuencias.getOrDefault(palabra, 0) + 1
                );
            }
        }

        assertEquals(Integer.valueOf(2), frecuencias.get("hola"));
        assertEquals(Integer.valueOf(1), frecuencias.get("mundo"));
    }

    @Test
    public void testEliminarPuntuacion() {

        String texto = "hola, mundo.";

        String[] palabras =
                texto.toLowerCase().split("[\\p{Punct}\\s]+");

        assertEquals("hola", palabras[0]);
        assertEquals("mundo", palabras[1]);
    }
}