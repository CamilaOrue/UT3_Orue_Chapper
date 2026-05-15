# Ejercicio 15

## 1. Identidad lógica de un Libro

El atributo que define la identidad de un libro es el **isbn**. El ISBN es un identificador único internacional — no pueden existir dos libros distintos con el mismo ISBN. El título puede repetirse entre ediciones y el autor puede tener varios libros, pero el ISBN siempre es único.

---

## 2. Implementación de equals y hashCode

Ver implementación en `Libro.java`.

```java
@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Libro unLibro = (Libro) o;
    return isbn.equals(unLibro.isbn);
}

@Override
public int hashCode() {
    return isbn.hashCode();
}
```

---

## 3. Error conceptual si equals usa isbn pero hashCode usa titulo

Si `equals` compara por `isbn` pero `hashCode` se calcula con `titulo`, se rompe el contrato. Dos libros con el mismo ISBN serían iguales según `equals`, pero si tienen títulos distintos darían hashes distintos. Eso viola la regla básica: objetos iguales tienen que tener el mismo hash. El resultado es que estructuras como `HashSet` o `HashMap` dejan de funcionar bien — pueden insertar duplicados o no encontrar elementos que sí están.

---

## 4. Ejemplo con HashSet

```java
HashSet<Libro> set = new HashSet<>();

Libro libro1 = new Libro("978-123", "El principito", "Saint-Exupéry", 1943);
Libro libro2 = new Libro("978-123", "El principito", "Saint-Exupéry", 1943);

set.add(libro1);
set.add(libro2);

System.out.println(set.size()); // imprime 1
```

`libro1` y `libro2` son objetos distintos en memoria pero representan el mismo libro.

---

## 5. Comportamiento esperado en el HashSet

Con `equals` y `hashCode` bien implementados, el `HashSet` detecta que los dos objetos son el mismo libro (mismo ISBN → mismo hash → `equals` devuelve true) y no inserta el duplicado. El tamaño del set queda en 1.

---

## 6. Casos de prueba

```java
// Caso 1: mismo objeto comparado consigo mismo
System.out.println(libro1.equals(libro1)); // true

// Caso 2: dos objetos con mismo ISBN
System.out.println(libro1.equals(libro2)); // true

// Caso 3: dos objetos con distinto ISBN
Libro libro3 = new Libro("978-999", "Otro libro", "Otro autor", 2000);
System.out.println(libro1.equals(libro3)); // false

// Caso 4: comparar con null
System.out.println(libro1.equals(null)); // false

// Caso 5: mismo ISBN → mismo hashCode
System.out.println(libro1.hashCode() == libro2.hashCode()); // true
```

Resultados obtenidos: `1`, `true`, `true`, `false`, `false`, `true`. El contrato se cumple.
