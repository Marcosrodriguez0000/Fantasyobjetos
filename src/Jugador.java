import java.io.Serializable;

public class Jugador implements Serializable {
    private String nombre;
    private double precio;

    public Jugador(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return nombre + " - " + precio;
    }
}

