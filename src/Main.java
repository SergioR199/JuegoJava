import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * */
public class Main {

    private static final Scanner sc = new Scanner(System.in);

    private static final int VIDAS_INICIALES = 200;
    private static final int MISILES_POR_RONDA = 50;
    private static final int MIN_EQUIPOS = 3;
    private static final int MAX_EQUIPOS = 5;

    /**
     *
     * */
    public static void main(String[] args) {

        int opcion;
        ArrayList<Planeta> equipos = new ArrayList<>();

        opcion = mostrarMenu();

        switch (opcion) {
            case 1:
                System.out.println("Creando equipos...");
                crearEquipos(equipos, pedirNumeroEquipos());
                jugar(equipos);
                break;

            case 2:
                InformacionJuego.mostrarReglas();
                break;

            case 3:
                InformacionJuego.mostrarInformacionGeneral();
                break;

            case 4:
                InformacionJuego.mostrarInfoPersonajes(sc);
                break;

            case 0:
                System.out.println("Saliendo del juego...");
                break;
        }

        sc.close();
    }

    /**
     *
     * */
    public static int mostrarMenu() {
        while (true) {
            try {
                System.out.println("\n---- MENU ----");
                System.out.println("1º JUGAR");
                System.out.println("2º REGLAS DEL JUEGO");
                System.out.println("3º INFORMACIÓN GENERAL");
                System.out.println("4º INFORMACIÓN DE PERSONAJES");
                System.out.println("0º SALIR");
                System.out.print("Elige opción: ");

                int opcion = sc.nextInt();
                sc.nextLine();

                if (opcion < 0 || opcion > 4) {
                    System.out.println("Has introducido una opción no existente. Elige otra opción (0-4).");
                } else {
                    return opcion;
                }

            } catch (InputMismatchException e) {
                System.out.println("Has introducido un valor no válido. Elige otra opción (0-4).");
                sc.nextLine();
            }
        }
    }

    /**
     *
     * */
    public static int pedirNumeroEquipos() {
        return leerEnteroRango(
                MIN_EQUIPOS,
                MAX_EQUIPOS,
                "¿Cuántos equipos quieres crear? (" + MIN_EQUIPOS + "-" + MAX_EQUIPOS + "): "
        );
    }

    /**
     *
     * */
    public static void crearEquipos(ArrayList<Planeta> equipos, int numEquipos) {
        for (int i = 0; i < numEquipos; i++) {
            System.out.println("\n[Jugador " + (i + 1) + "]");
            String nombre = pedirPersonaje();
            equipos.add(new Planeta(nombre, VIDAS_INICIALES, MISILES_POR_RONDA));
        }
    }

    /**
     *
     * */
    public static String pedirPersonaje() {
        while (true) {
            try {
                InformacionJuego.mostrarPersonajes();
                System.out.println("13- Otro nombre");
                System.out.println("14- Aleatorio");
                System.out.println("0- Salir");
                System.out.print("Escoge el número del personaje: ");

                int personaje = sc.nextInt();
                sc.nextLine();

                if (personaje == 0) {
                    return "SinNombre";
                }

                if (personaje >= 1 && personaje <= InformacionJuego.getTotalPersonajes()) {
                    return InformacionJuego.getPersonaje(personaje - 1);
                }

                if (personaje == 13) {
                    System.out.print("Escribe el nombre que quieras: ");
                    return sc.nextLine();
                }

                if (personaje == 14) {
                    int aleatorio = (int) (Math.random() * InformacionJuego.getTotalPersonajes());
                    String nombre = InformacionJuego.getPersonaje(aleatorio);
                    System.out.println("¡Te ha tocado " + nombre + "!");
                    return nombre;
                }

                System.out.println("Has introducido una opción no existente. Elige otra opción (0-14).");

            } catch (InputMismatchException e) {
                System.out.println("Has introducido un valor no válido. Elige otra opción (0-14).");
                sc.nextLine();
            }
        }
    }

    /**
     *
     * */
    public static void jugar(ArrayList<Planeta> equipos) {
        int ronda = 1;

        while (equiposVivos(equipos) > 1) {

            System.out.println("\n---- " + ronda + "ª RONDA ----");
            mostrarEstadoRonda(equipos);

            int[] vidasInicio = guardarVidasInicio(equipos);
            int[] danoPendiente = new int[equipos.size()];
            int[] defensaPendiente = new int[equipos.size()];

            ArrayList<String> listaAcciones = new ArrayList<>();

            for (int i = 0; i < equipos.size(); i++) {
                if (vidasInicio[i] > 0 && equiposVivos(equipos) > 1) {
                    turnoEquipo(
                            equipos,
                            i,
                            ronda,
                            danoPendiente,
                            defensaPendiente,
                            listaAcciones
                    );
                }
            }

            System.out.println("\n---- ACCIONES DE LA RONDA ----");
            for (String accion : listaAcciones) {
                System.out.println(accion);
            }


            aplicarResultadosRonda(equipos, danoPendiente, defensaPendiente);
            mostrarResumenRonda(equipos, vidasInicio, danoPendiente, defensaPendiente);

            ronda++;
        }

        anunciarGanador(equipos);
    }

    /**
     *
     * */
    public static int[] guardarVidasInicio(ArrayList<Planeta> equipos) {
        int[] vidasInicio = new int[equipos.size()];
        for (int i = 0; i < equipos.size(); i++) {
            vidasInicio[i] = equipos.get(i).getVidas();
        }
        return vidasInicio;
    }

    /**
     *
     * */
    public static void turnoEquipo(ArrayList<Planeta> equipos,
                                  int indiceAtacante,
                                  int ronda,
                                  int[] danoPendiente,
                                  int[] defensaPendiente,
                                  ArrayList<String> listaAcciones) {

        Planeta atacante = equipos.get(indiceAtacante);

        if (!atacante.estaVivo()) {
            return;
        }

        System.out.println("\nTurno de: " + atacante.getNombre());
        System.out.println("Misiles disponibles: " + atacante.getMisiles_ronda());

        int accion = 1;

        if (ronda == 1) {
            System.out.println("En la primera ronda solo se puede atacar.");
        } else {
            System.out.println("1. Atacar");
            System.out.println("2. Defenderse");
            accion = leerEnteroRango(1, 2, "¿Qué quieres hacer? (1-2): ");
        }

        if (accion == 1) {
            procesarAtaque(equipos, indiceAtacante, danoPendiente, listaAcciones);
        } else {
            procesarDefensa(equipos, indiceAtacante, defensaPendiente, listaAcciones);
        }

        atacante.recargarMisiles(MISILES_POR_RONDA);
    }

    /**
     *
     * */
    public static void procesarAtaque(ArrayList<Planeta> equipos,
                                     int indiceAtacante,
                                     int[] danoPendiente,
                                     ArrayList<String> listaAcciones) {

        Planeta atacante = equipos.get(indiceAtacante);

        while (atacante.getMisiles_ronda() > 0 && equiposVivos(equipos) > 1) {

            int objetivo = elegirObjetivo(equipos, indiceAtacante);
            if (objetivo == -1) {
                break;
            }

            System.out.println("Misiles disponibles: " + atacante.getMisiles_ronda());

            int misiles = leerEnteroRango(
                    0,
                    atacante.getMisiles_ronda(),
                    "¿Cuántos misiles quieres usar contra " + equipos.get(objetivo).getNombre() + "? "
            );

            if (misiles == 0) {
                break;
            }

            atacante.usarMisiles(misiles);
            danoPendiente[objetivo] += misiles;

            listaAcciones.add(
                    atacante.getNombre()
                            + " ataca a " + equipos.get(objetivo).getNombre()
                            + " con " + misiles + " misiles."
            );
        }
    }

    /**
     *
     * */
    public static void procesarDefensa(ArrayList<Planeta> equipos,
                                      int indiceAtacante,
                                      int[] defensaPendiente,
                                       ArrayList<String> listaAcciones) {

        Planeta atacante = equipos.get(indiceAtacante);

        while (atacante.getMisiles_ronda() > 0) {

            System.out.println("Misiles disponibles: " + atacante.getMisiles_ronda());

            int misilesDefensa = leerEnteroRango(
                    0,
                    atacante.getMisiles_ronda(),
                    "¿Cuántos misiles quieres usar para defenderte? "
            );

            if (misilesDefensa == 0) {
                break;
            }

            atacante.usarMisiles(misilesDefensa);
            int defensaTotal = misilesDefensa / 2;
            defensaPendiente[indiceAtacante] += defensaTotal;

            listaAcciones.add(
                    atacante.getNombre()
                            + " se defiende usando " + misilesDefensa
                            + " misiles (" + defensaTotal + " de defensa)."
            );
        }
    }

    /**
     *
     * */
    public static int elegirObjetivo(ArrayList<Planeta> equipos, int indiceAtacante) {
        System.out.println("Objetivos disponibles:");

        int[] indicesReales = new int[equipos.size()];
        int contador = 0;

        for (int i = 0; i < equipos.size(); i++) {
            if (i != indiceAtacante && equipos.get(i).estaVivo()) {
                System.out.println((contador + 1) + ". " + equipos.get(i).getNombre()
                        + " (" + equipos.get(i).getVidas() + " vidas)");
                indicesReales[contador] = i;
                contador++;
            }
        }

        if (contador == 0) {
            System.out.println("No hay objetivos vivos.");
            return -1;
        }

        int opcion = leerEnteroRango(
                1,
                contador,
                "¿A quién quieres atacar? Introduce el número: "
        );

        return indicesReales[opcion - 1];
    }

    /**
     *
     * */
    public static void aplicarResultadosRonda(ArrayList<Planeta> equipos,
                                              int[] danoPendiente,
                                              int[] defensaPendiente) {

        for (int i = 0; i < equipos.size(); i++) {
            if (!equipos.get(i).estaVivo()) {
                continue;
            }

            int danoFinal = danoPendiente[i] - defensaPendiente[i];
            if (danoFinal < 0) {
                danoFinal = 0;
            }

            equipos.get(i).recibirDanio(danoFinal);
        }
    }

    /**
     *
     * */
    public static void mostrarResumenRonda(ArrayList<Planeta> equipos,
                                           int[] vidasInicio,
                                           int[] danoPendiente,
                                           int[] defensaPendiente) {

        System.out.println("\n---- RESUMEN DE LA RONDA ----");

        for (int i = 0; i < equipos.size(); i++) {
            int danoFinal = danoPendiente[i] - defensaPendiente[i];
            if (danoFinal < 0) {
                danoFinal = 0;
            }

            System.out.println(equipos.get(i).getNombre()
                    + " | Inicio: " + vidasInicio[i]
                    + " | Daño recibido: " + danoPendiente[i]
                    + " | Defensa: " + defensaPendiente[i]
                    + " | Daño final: " + danoFinal
                    + " | Fin: " + equipos.get(i).getVidas());
        }

        System.out.println("------------------------------");
    }

    /**
     *
     * */
    public static int equiposVivos(ArrayList<Planeta> equipos) {
        int vivos = 0;
        for (Planeta equipo : equipos) {
            if (equipo.estaVivo()) {
                vivos++;
            }
        }
        return vivos;
    }

    /**
     *
     * */
    public static void mostrarEstadoRonda(ArrayList<Planeta> equipos) {
        for (Planeta equipo : equipos) {
            if (equipo.estaVivo()) {
                System.out.println(equipo.getNombre() + " > " + equipo.getVidas() + " vidas");
            }
        }
    }

    /**
     *
     * */
    public static void anunciarGanador(ArrayList<Planeta> equipos) {
        for (Planeta equipo : equipos) {
            if (equipo.estaVivo()) {
                System.out.println("\n¡" + equipo.getNombre() + " GANA LA PARTIDA!\n");
                return;
            }
        }
    }

    /**
     *
     * */
    public static int leerEnteroRango(int min, int max, String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                int valor = sc.nextInt();
                sc.nextLine();

                if (valor < min || valor > max) {
                    System.out.println("Elige un número entre " + min + " y " + max + ".");
                } else {
                    return valor;
                }

            } catch (InputMismatchException e) {
                System.out.println("Valor no válido. Introduce un número entero.");
                sc.nextLine();
            }
        }
    }
}