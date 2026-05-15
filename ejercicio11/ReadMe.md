La clase más apropiada es HashMap<String, Integer>.

Justificación:

Eficiencia en acceso: 
El problema pide una implementación eficiente. HashMap ofrece una complejidad de tiempo promedio de $O(1)$ para las operaciones de inserción (put) y búsqueda (get). Dado que un libro puede tener miles de palabras únicas, necesitamos rapidez para actualizar el contador de cada una.

Estructura Llave-Valor: 
Es ideal para mapear una palabra única a un entero que represente su frecuencia.

Orden irrelevante en el procesamiento: 
Durante la lectura del archivo, no necesitamos que las palabras estén ordenadas alfabéticamente (lo cual haría a TreeMap más lento con su $O(\log n)$). Solo nos interesa la velocidad de conteo.