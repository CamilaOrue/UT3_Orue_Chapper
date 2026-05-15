# Ejercicio 14

## Claves a insertar (en orden)
45, 12, 37, 82, 29, 54, 31, 76, 18, 93, 11, 68

---

## 1. Tamaño de la tabla

Se eligió **13** como tamaño de la tabla. Es el primo más cercano por encima de 12 (la cantidad de claves), y usar un número primo ayuda a reducir colisiones porque no comparte factores con los valores que se insertan.

---

## 2. Función hash

```
h(k) = k % 13
```

Función estándar para claves enteras, simple y suficiente para este caso.

---

## 3. Estado final de las tablas

### Sondeo lineal
Si hay colisión en la posición `p`, se prueba `p+1`, `p+2`, etc. hasta encontrar un lugar libre. Si se llega al final, se vuelve al inicio.

```
Pos  0  → 76
Pos  1  → 11
Pos  2  → 54
Pos  3  → 29
Pos  4  → 82
Pos  5  → 31
Pos  6  → 45
Pos  7  → 18
Pos  8  → 93
Pos  9  → 68
Pos 10  → vacío
Pos 11  → 37
Pos 12  → 12
```

### Sondeo cuadrático
Si hay colisión en `p`, se prueba `p+1²`, `p+2²`, `p+3²`, etc.

```
Pos  0  → 68
Pos  1  → 93
Pos  2  → 54
Pos  3  → 29
Pos  4  → 82
Pos  5  → 31
Pos  6  → 45
Pos  7  → 76
Pos  8  → vacío
Pos  9  → 18
Pos 10  → 11
Pos 11  → 37
Pos 12  → 12
```

### Encadenamiento separado
Cada posición tiene una lista enlazada. Las colisiones se resuelven agregando el elemento a la lista correspondiente.

```
Pos  0  → vacío
Pos  1  → vacío
Pos  2  → [54] → [93]
Pos  3  → [29] → [68]
Pos  4  → [82]
Pos  5  → [31] → [18]
Pos  6  → [45]
Pos  7  → vacío
Pos  8  → vacío
Pos  9  → vacío
Pos 10  → vacío
Pos 11  → [37] → [76] → [11]
Pos 12  → [12]
```

---

## 4. Colisiones totales

| Estrategia | Colisiones |
|---|---|
| Sondeo lineal | 5 |
| Sondeo cuadrático | 16 |
| Encadenamiento separado | 5 |

---

## 5. Promedio de comparaciones — búsquedas exitosas

### Sondeo lineal
| Clave | Comparaciones |
|---|---|
| 45, 12, 37, 82, 29, 54, 31 | 1 cada una (7 total) |
| 76 | 3 |
| 18 | 2 |
| 93 | 7 |
| 11 | 4 |
| 68 | 7 |

Total: 30 / 12 = **2.5**

### Sondeo cuadrático
| Clave | Comparaciones |
|---|---|
| 45, 12, 37, 82, 29, 54, 31 | 1 cada una (7 total) |
| 76 | 3 |
| 18 | 2 |
| 93 | 5 |
| 11 | 5 |
| 68 | 6 |

Total: 26 / 12 = **2.17**

### Encadenamiento separado
| Clave | Comparaciones |
|---|---|
| 54, 29, 82, 31, 45, 37, 12 | 1 cada una (7 total) |
| 93, 68, 76, 18 | 2 cada una (8 total) |
| 11 | 3 |

Total: 18 / 12 = **1.5**

---

## 6. Promedio de comparaciones — búsquedas no exitosas

Claves de prueba: **1, 2 y 3** (no están en ninguna de las tablas).

### Sondeo lineal
Se recorre desde la posición hash hasta encontrar el primer bucket vacío (posición 10 en este caso).

| Clave | Hash | Comparaciones |
|---|---|---|
| 1 | 1 | 10 |
| 2 | 2 | 9 |
| 3 | 3 | 8 |

Promedio: 27 / 3 = **9**

### Sondeo cuadrático
Con sondeo cuadrático no se puede garantizar llegar al bucket vacío, porque los saltos pueden saltear esa posición indefinidamente. Por eso no es posible calcular un promedio confiable para búsquedas no exitosas con este método.

### Encadenamiento separado
| Clave | Hash | Comparaciones |
|---|---|---|
| 1 | 1 | 0 (bucket vacío) |
| 2 | 2 | 2 (lista: 54 → 93) |
| 3 | 3 | 2 (lista: 29 → 68) |

Promedio: 4 / 3 = **1.33**

---

## 7. Conclusión

| Estrategia | Colisiones | Búsqueda exitosa | Búsqueda no exitosa |
|---|---|---|---|
| Sondeo lineal | 5 | 2.5 | 9.0 |
| Sondeo cuadrático | 16 | 2.17 | No garantizada |
| Encadenamiento separado | 5 | 1.5 | 1.33 |

El encadenamiento separado fue claramente el mejor método para este conjunto de datos, tanto en búsquedas exitosas como no exitosas. Tiene sentido que Java lo use internamente en su HashMap.

El sondeo lineal tiene la ventaja de que si hay espacio, el elemento siempre se inserta. Pero tiene un problema conocido como clustering: al llenar posiciones consecutivas se forman grupos que hacen que las colisiones siguientes sean cada vez más largas, como pasó con el 93, el 11 y el 68.

El sondeo cuadrático tuvo la mayor cantidad de colisiones para estos datos y además tiene la limitación de que los saltos grandes pueden hacer que nunca se llegue a un bucket vacío, aunque exista espacio en la tabla.
