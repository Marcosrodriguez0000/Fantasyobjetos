import java.io.Serializable;

public class Jugador implements Serializable{
    private String nombre;
    private float precio;

    public Jugador(String nombre, float precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public float getPrecio() {
        return precio;
    }
}
