package com.example;

import java.io.*;
import java.util.*;

public class App {
    public static void main(String[] args) {
        Map<String, Integer> frecuencias = new HashMap<>();
        String rutaArchivo = "src\\main\\java\\com\\example\\libro.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Limpiar puntuación y separar por espacios
                String[] palabras = linea.toLowerCase().split("[\\p{Punct}\\s]+");

                for (String palabra : palabras) {
                    if (!palabra.isEmpty()) {
                        // Si existe sumamos 1, si no, iniciamos en 1
                        frecuencias.put(palabra, frecuencias.getOrDefault(palabra, 0) + 1);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return;
        }

        // Obtener las 10 más frecuentes
        frecuencias.entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .limit(10)
            .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
    }
}