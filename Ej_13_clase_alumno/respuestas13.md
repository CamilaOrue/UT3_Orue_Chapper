# Ejercicio 13

## Parte 1 — hashCode en Object, Integer y String

### Object.hashCode()
La clase `Object` usa la **dirección de memoria** del objeto para calcular su hash. Tiene sentido porque cada objeto ocupa un lugar único en memoria, así que dos objetos distintos siempre van a tener hashes distintos. Como `Object` no define ninguna igualdad lógica entre instancias, la dirección de memoria es lo único que tiene para identificarlas.

### Integer.hashCode()
Para `Integer` el hash es directamente el **valor entero**. Por ejemplo, `new Integer(37).hashCode()` devuelve `37`. Si dos enteros tienen el mismo valor, son lógicamente iguales, así que tiene sentido que tengan el mismo hash.

### String.hashCode()
`String` usa el **polinomio de Horner**, que combina el valor de cada carácter con su posición usando 31 como multiplicador:

```
s[0]*31^(n-1) + s[1]*31^(n-2) + ... + s[n-1]
```

Así, dos Strings con las mismas letras siempre dan el mismo hash.

Implementación real en el código fuente de Java (OpenJDK):
```java
public int hashCode() {
    int h = 0;
    for (char c : value) {
        h = 31 * h + c;
    }
    return h;
}
```

### ¿Por qué cambia la implementación según el tipo?
Porque el hash tiene que reflejar la **igualdad lógica** del objeto. La regla en Java es: si dos objetos son iguales según `equals`, tienen que tener el mismo `hashCode`. Como la igualdad lógica es distinta para cada tipo, la implementación también cambia.

---

## Parte 2 — Estructura interna de un HashMap

Un HashMap tiene internamente un **array de buckets**. Al insertar un elemento, Java calcula su hash y hace `hash % capacidad` para saber en qué bucket guardarlo. Si dos elementos caen en el mismo bucket (colisión), ese bucket tiene una **lista enlazada** con los dos. El tamaño por defecto es 16 buckets.

### Inserción de las strings

| String | Hash | Bucket (hash % 16) |
|---|---|---|
| "Hola" | 2212168 | 8 |
| "HolaMundo" | 204522098 | 2 |
| "HashMap" | — | 2 |
| "Colecciones" | 1354562204 | 12 |

`"HolaMundo"` y `"HashMap"` caen en el bucket 2 → colisión, se resuelve con lista enlazada.

### Estado de la estructura

```
Bucket  0  → null
Bucket  1  → null
Bucket  2  → ["HolaMundo"] → ["HashMap"] → null
Bucket  3  → null
Bucket  4  → null
Bucket  5  → null
Bucket  6  → null
Bucket  7  → null
Bucket  8  → ["Hola"] → null
Bucket  9  → null
Bucket 10  → null
Bucket 11  → null
Bucket 12  → ["Colecciones"] → null
Bucket 13  → null
Bucket 14  → null
Bucket 15  → null
```

---

## Parte 3 — equals y hashCode para la clase Alumno

La identidad de un `Alumno` la define el `id`, porque es el único identificador único. El nombre puede repetirse y el email puede cambiar, pero el `id` no.

Ver implementación en `Alumno.java`.

### Contrato general de hashCode

- Si dos objetos son iguales según `equals`, los dos tienen que devolver el mismo valor en `hashCode`.
- Si llamás `hashCode()` varias veces sobre el mismo objeto sin modificarlo, siempre tiene que devolver el mismo valor.
- Que dos objetos tengan el mismo `hashCode` no significa que sean iguales — eso es simplemente una colisión y está permitido.

Respetar este contrato es lo que hace que `HashMap` y `HashSet` funcionen correctamente.
