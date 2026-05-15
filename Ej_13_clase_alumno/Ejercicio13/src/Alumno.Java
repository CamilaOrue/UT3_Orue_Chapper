public class Alumno {
    private int id;
    private String fullName;
    private String email;
    


    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alumno unAlumno = (Alumno) o;
        return id == unAlumno.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
/*El contrato hashCode() en Java establece que si dos objetos 
 son considerados iguales mediante el método equals(), ambos deben
 devolver el mismo valor entero al invocar hashCode(). Este mecanismo 
 garantiza el funcionamiento correcto de colecciones basadas en hash 
 (HashMap, HashSet) al optimizar la búsqueda y almacenamiento.
 Si llamás hashCode() varias veces sobre el mismo objeto sin modificarlo,
 siempre debe devolver el mismo valor.
 Dos objetos con el mismo hashCode() no necesariamente son iguales según equals()*/
}