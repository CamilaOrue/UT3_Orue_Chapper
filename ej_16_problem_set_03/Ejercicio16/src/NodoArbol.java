import java.util.ArrayList;

public class NodoArbol {
    private Persona persona;
    private ArrayList<NodoArbol> hijos;

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public ArrayList<NodoArbol> getHijos() {
        return hijos;
    }

    public void setHijos(ArrayList<NodoArbol> hijos) {
        this.hijos = hijos;
    }

    public NodoArbol(Persona persona) {
        this.persona = persona;
        this.hijos = new ArrayList<>();
    }

    public void agregarHijo(NodoArbol hijo) {
        this.hijos.add(hijo);
    }

}
