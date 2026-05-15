public class Persona {
    private String nombre;
    private int anionacimiento;

    public Persona(String nombre, int anionacimiento) {
        this.nombre = nombre;
        this.anionacimiento = anionacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnionacimiento() {
        return anionacimiento;
    }

    public void setAnionacimiento(int anionacimiento) {
        this.anionacimiento = anionacimiento;
    }

}
