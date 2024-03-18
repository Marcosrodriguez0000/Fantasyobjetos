import java.io.*;
import java.util.ArrayList;

public class GestorArchivosJugadores {

    public static void guardarJugadoresEnArchivo(ArrayList<Jugador> jugadores, String nombreArchivo) {
        try {
            FileOutputStream fileOut = new FileOutputStream(nombreArchivo);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(jugadores);
            objectOut.close();
            fileOut.close();
            System.out.println("Los jugadores se han guardado en " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al guardar los jugadores: " + e.getMessage());
        }
    }

    public static ArrayList<Jugador> cargarJugadoresDesdeArchivo(String nombreArchivo) {
        ArrayList<Jugador> jugadores = new ArrayList<>();
        try {
            FileInputStream fileIn = new FileInputStream(nombreArchivo);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            jugadores = (ArrayList<Jugador>) objectIn.readObject();
            objectIn.close();
            fileIn.close();
            System.out.println("Los jugadores se han cargado desde " + nombreArchivo);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar los jugadores: " + e.getMessage());
        }
        return jugadores;
    }
}