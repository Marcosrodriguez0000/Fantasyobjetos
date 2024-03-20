import java.io.*;
import java.util.ArrayList;

public class GestorArchivosJugadores {

    public static void guardarJugadoresEnArchivo(ArrayList<String> jugadores, String nombreArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (String jugador : jugadores) {
                writer.write(jugador);
                writer.newLine();
            }
            System.out.println("Los jugadores se han guardado en " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al guardar los jugadores: " + e.getMessage());
        }
    }

    public static ArrayList<String> cargarJugadoresDesdeArchivo(String nombreArchivo) {
        ArrayList<String> jugadores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                jugadores.add(linea);
            }
            System.out.println("Los jugadores se han cargado desde " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al cargar los jugadores: " + e.getMessage());
        }
        return jugadores;
    }
}