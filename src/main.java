import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class main {

        static Scanner lectura = new Scanner(System.in);
        static ArrayList<String> jugadoresDisponibles = new ArrayList<>();
        static ArrayList<String> jugadoresFichados = new ArrayList<>();
        static ArrayList<Float> preciosDisponibles = new ArrayList<>();
        static ArrayList<Float> preciosFichados = new ArrayList<>();
        static float presupuesto = 1000;

        public static void main(String[] args) {
            System.out.println("\r\n" + "███████╗ █████╗ ███╗   ██╗████████╗ █████╗ ███████╗██╗   ██╗\r\n"
                    + "██╔════╝██╔══██╗████╗  ██║╚══██╔══╝██╔══██╗██╔════╝╚██╗ ██╔╝\r\n"
                    + "█████╗  ███████║██╔██╗ ██║   ██║   ███████║███████╗ ╚████╔╝ \r\n"
                    + "██╔══╝  ██╔══██║██║╚██╗██║   ██║   ██╔══██║╚════██║  ╚██╔╝  \r\n"
                    + "██║     ██║  ██║██║ ╚████║   ██║   ██║  ██║███████║   ██║   \r\n"
                    + "╚═╝     ╚═╝  ╚═╝╚═╝  ╚═══╝   ╚═╝   ╚═╝  ╚═╝╚══════╝   ╚═╝   \r\n"
                    + "                                                            \r\n" + "");

            inicializarJugadores();

            mostrarMenu();

        }


        private static void inicializarJugadores() {
            agregarJugador("BA-Luka Doncic", 300.0);
            agregarJugador("BA-Stephen Curry", 100.0);
            agregarJugador("BA-De'Aaron Fox", 50.0);
            agregarJugador("BA-Tyler Herro", 20.0);
            agregarJugador("E-Donovan Mitchell", 150.0);
            agregarJugador("E-Desmond Bane", 50.0);
            agregarJugador("E-Cam Thomas", 90.0);
            agregarJugador("E-Anthony Edwars", 100.0);
            agregarJugador("A-Jayson Tatum", 30.0);
            agregarJugador("A-LeBron James", 50.0);
            agregarJugador("A-Kyle Kuzma", 10.0);
            agregarJugador("A-Kawhi Leonard", 100.0);
            agregarJugador("AP-Kevin durant", 140.0);
            agregarJugador("AP-Anthony Davis", 70.0);
            agregarJugador("AP-Lauri Markkanen", 50.0);
            agregarJugador("AP-Tobias Harris", 30.0);
            agregarJugador("C-Joel Embiid", 20.0);
            agregarJugador("C-Nikola Jokic", 100.0);
            agregarJugador("C-Bam Adebayo", 200.0);
            agregarJugador("C-Victor Wembanyama", 150.0);

        }

        private static void mostrarMenu() {
            int opcion;
            do {
                System.out.println("----------- MENU DE OPCIONES DE MERCADO -----------");
                System.out.println("1. Mostrar jugadores disponibles");
                System.out.println("2. Mostrar tu plantilla");
                System.out.println("3. Fichar jugador");
                System.out.println("4. Eliminar jugador de tu plantilla");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opción: ");
                opcion = lectura.nextInt();

                switch (opcion) {
                    case 1:
                        mostrarJugadoresDisponibles();
                        break;
                    case 2:
                        mostrarPlantilla();
                        break;
                    case 3:
                        ficharJugador();
                        break;
                    case 4:
                        eliminarJugador();
                        break;
                }
            } while (opcion != 0);
        }


        private static void mostrarJugadoresDisponibles() {

            int posicion;
            float precio;
            String jugador;
            System.out.println("-----------------------------------");
            System.out.println("Seleccione una posición:");
            System.out.println("1. Bases");
            System.out.println("2. Escoltas");
            System.out.println("3. Aleros");
            System.out.println("4. Ala-Pivots");
            System.out.println("5. Centros");
            System.out.print("Seleccione una posición (0 para cancelar): ");
            posicion = lectura.nextInt();
            // Lo qe hacemos es validar y mostrar solo los jugadores que estan por debajo
            // del presupuesto
            if (posicion >= 1 && posicion <= 5) {
                System.out.println("-----------------------------------");
                System.out.println("Jugadores disponibles por debajo de tu presupuesto: " + presupuesto);
                for (int i = 0; i < jugadoresDisponibles.size(); i++) {
                    jugador = jugadoresDisponibles.get(i);
                    precio = preciosDisponibles.get(i);

                    if (cumplePresupuesto(precio) && cumplePosicion(jugador, posicion)) {
                        System.out.println((i + 1) + ". 🏀​​ " + jugador + " - " + precio);
                    }
                }
            } else if (posicion != 0) {
                System.out.println("Selección de posición inválida.");
            }
        }


        private static boolean cumplePresupuesto(float precio) {
            return presupuesto >= precio;
        }


        private static boolean cumplePosicion(String jugador, int posicion) {
            switch (posicion) {
                case 1:
                    return jugador.startsWith("BA-");
                case 2:
                    return jugador.startsWith("E-");
                case 3:
                    return jugador.startsWith("A-");
                case 4:
                    return jugador.startsWith("AP-");
                case 5:
                    return jugador.startsWith("C-");
                default:
                    return false;
            }
        }


        private static void mostrarPlantilla() {
            System.out.println("-----------------------------------");
            System.out.println("Tu plantilla:");
            for (int i = 0; i < jugadoresFichados.size(); i++) {
                System.out.println((i + 1) + ". 🏀​​ " + jugadoresFichados.get(i) + " - " + preciosFichados.get(i));
            }
        }


        private static void ficharJugador() {
        int seleccion;
        String jugadorSeleccionado = null;
        float precioJugador = 0;
        int nJugadoresFichados = jugadoresFichados.size();

        // Verifica si hay hueco en la plantilla ya que tenemos que maximo ocho
        // jugadores
        if (nJugadoresFichados < 8) {
            mostrarJugadoresDisponibles();
            System.out.print("Seleccione un jugador para fichar (0 para cancelar): ");
            seleccion = lectura.nextInt();


            if (seleccion >= 1 && seleccion <= jugadoresDisponibles.size()) {
                jugadorSeleccionado = jugadoresDisponibles.remove(seleccion - 1);
                precioJugador = preciosDisponibles.remove(seleccion - 1);

                // Verificamos si tienes el presupuesto para fichar los jugadores disponibles
                if (presupuesto >= precioJugador) {
                    jugadoresFichados.add(jugadorSeleccionado);
                    preciosFichados.add(precioJugador);
                    presupuesto -= precioJugador;
                    System.out.println("-----------------------------------");
                    System.out.println("Fichaste a " + jugadorSeleccionado + " por " + precioJugador
                            + ". Presupuesto restante: " + presupuesto);
                } else {
                    System.out.println("No tienes presupuesto suficiente para fichar a este jugador.");
                }
            } else if (seleccion != 0) {
                System.out.println("Selección inválida.");
            }
        } else {
            // Verificamos si la palntilla tiene 8 jugadores y si de esos ocho hay uno por
            // posicion
            if (!validarPlantilla()) {
                System.out.println("-----------------------------------");
                System.out.println(
                        "⚠️ La plantilla no es válida. Debes tener al menos un jugador por posición. Elimina alguno y termina de fichar las posiciones que te faltan.");
            } else if (nJugadoresFichados == 8) {
                System.out.println("-----------------------------------");
                System.out.println(" ✔️ Plantilla completada, tienes ocho jugadores no puedes fichar mas");
                mostrarMenu();
            }
        }

    }


        private static boolean validarPlantilla() {
            // Bucle para la posicion de 1 a 5
            for (int posicion = 1; posicion <= 5; posicion++) {
                boolean tieneJugador = false;
                // Bucle para el jugador fichado
                for (String jugador : jugadoresFichados) {
                    if (cumplePosicion(jugador, posicion)) {
                        tieneJugador = true;
                        break;
                    }
                }
                // Si la plantilla no tiene ningun jugador que me lo retorne como no valida
                if (!tieneJugador) {
                    return false;
                }
            }
            // Si si la tiene la plantilla es valida
            return true;
        }


        private static void eliminarJugador() {
            // Mostramos la funcion de la plantilla
            mostrarPlantilla();
            String jugadorEliminado;
            float precioEliminado;
            System.out.print("Seleccione un jugador para eliminar (0 para cancelar): ");
            int seleccion = lectura.nextInt();
            // Esto lo usamos para verificar el jugador
            if (seleccion >= 1 && seleccion <= jugadoresFichados.size()) {
                // Se elimina y nos devuelve el precio del jugador al presupuesto
                jugadorEliminado = jugadoresFichados.remove(seleccion - 1);
                precioEliminado = preciosFichados.remove(seleccion - 1);
                jugadoresDisponibles.add(jugadorEliminado);
                preciosDisponibles.add(precioEliminado);
                presupuesto += precioEliminado;
                System.out.println("-----------------------------------");
                System.out.println(
                        "Eliminaste a " + jugadorEliminado + " de tu plantilla. Presupuesto restante: " + presupuesto);
            } else if (seleccion != 0) {
                System.out.println("Selección inválida.");
            }
        }


        private static void agregarJugador(String nombre, double precio) {
            jugadoresDisponibles.add(nombre);
            preciosDisponibles.add((float) precio);
        }

}
