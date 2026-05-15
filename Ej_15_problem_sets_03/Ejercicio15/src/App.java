import java.util.HashSet;

public class App {
    public static void main(String[] args) throws Exception {
        HashSet<Libro> set = new HashSet<>();

        Libro libro1 = new Libro("978-123", "El principito", "Saint-Exupéry", 1943);
        Libro libro2 = new Libro("978-123", "El principito", "Saint-Exupéry", 1943);
        Libro libro3 = new Libro("978-124", "El principito", "Saint-Exupéry", 1943);

        set.add(libro1);
        set.add(libro2);

        System.out.println(set.size());
        System.out.println(libro1.equals(libro1));
        System.out.println(libro1.equals(libro2));
        System.out.println(libro1.equals(libro3));
        System.out.println(libro1.equals(null));
        System.out.println(libro1.hashCode() == libro2.hashCode());

    }
}
