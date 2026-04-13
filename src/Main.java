import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase principal del juego, gestion del menu,
 * creación de equipos y desarrollo de rondas.
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
        Planeta[] equipos;

        opcion = mostrarMenu();

        switch (opcion) {
            case 1:
                System.out.println("Creando equipos...");
                equipos = new Planeta[pedirNumeroEquipos()];
                crearEquipos(equipos);
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
    public static void crearEquipos(Planeta[] equipos) {
        for (int i = 0; i < equipos.length; i++) {
            System.out.println("\n[Jugador " + (i + 1) + "]");
            String nombre = pedirPersonaje();
            equipos[i] = new Planeta(nombre, VIDAS_INICIALES, MISILES_POR_RONDA);
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
    public static void jugar(Planeta[] equipos) {
        int ronda = 1;

        while (equiposVivos(equipos) > 1) {

            System.out.println("\n---- " + ronda + "ª RONDA ----");
            mostrarEstadoRonda(equipos);

            int[] vidasInicio = guardarVidasInicio(equipos);
            int[] danoPendiente = new int[equipos.length];
            int[] defensaPendiente = new int[equipos.length];

            String[] listaAcciones = new String[200];
            int numAcciones = 0;

            for (int i = 0; i < equipos.length; i++) {
                if (vidasInicio[i] > 0 && equiposVivos(equipos) > 1) {
                    numAcciones = turnoEquipo(
                            equipos,
                            i,
                            ronda,
                            danoPendiente,
                            defensaPendiente,
                            listaAcciones,
                            numAcciones
                    );
                }
            }

            System.out.println("\n---- ACCIONES DE LA RONDA ----");
            for (int i = 0; i < numAcciones; i++) {
                System.out.println(listaAcciones[i]);
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
    public static int[] guardarVidasInicio(Planeta[] equipos) {
        int[] vidasInicio = new int[equipos.length];
        for (int i = 0; i < equipos.length; i++) {
            vidasInicio[i] = equipos[i].getVidas();
        }
        return vidasInicio;
    }

    /**
     *
     * */
    public static int turnoEquipo(Planeta[] equipos,
                                  int indiceAtacante,
                                  int ronda,
                                  int[] danoPendiente,
                                  int[] defensaPendiente,
                                  String[] listaAcciones,
                                  int numAcciones) {

        Planeta atacante = equipos[indiceAtacante];

        if (!atacante.estaVivo()) {
            return numAcciones;
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
            numAcciones = procesarAtaque(equipos, indiceAtacante, danoPendiente, listaAcciones, numAcciones);
        } else {
            numAcciones = procesarDefensa(equipos, indiceAtacante, defensaPendiente, listaAcciones, numAcciones);
        }

        atacante.recargarMisiles(MISILES_POR_RONDA);
        return numAcciones;
    }

    /**
     *
     * */
    public static int procesarAtaque(Planeta[] equipos,
                                     int indiceAtacante,
                                     int[] danoPendiente,
                                     String[] listaAcciones,
                                     int numAcciones) {

        Planeta atacante = equipos[indiceAtacante];

        while (atacante.getMisiles_ronda() > 0 && equiposVivos(equipos) > 1) {

            int objetivo = elegirObjetivo(equipos, indiceAtacante);
            if (objetivo == -1) {
                break;
            }

            System.out.println("Misiles disponibles: " + atacante.getMisiles_ronda());

            int misiles = leerEnteroRango(
                    0,
                    atacante.getMisiles_ronda(),
                    "¿Cuántos misiles quieres usar contra " + equipos[objetivo].getNombre() + "? "
            );

            if (misiles == 0) {
                break;
            }

            atacante.usarMisiles(misiles);
            danoPendiente[objetivo] += misiles;

            listaAcciones[numAcciones++] = atacante.getNombre()
                    + " ataca a " + equipos[objetivo].getNombre()
                    + " con " + misiles + " misiles.";
        }

        return numAcciones;
    }

    /**
     *
     * */
    public static int procesarDefensa(Planeta[] equipos,
                                      int indiceAtacante,
                                      int[] defensaPendiente,
                                      String[] listaAcciones,
                                      int numAcciones) {

        Planeta atacante = equipos[indiceAtacante];

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

            listaAcciones[numAcciones++] = atacante.getNombre()
                    + " se defiende usando " + misilesDefensa
                    + " misiles (" + defensaTotal + " de defensa).";
        }

        return numAcciones;
    }

    /**
     *
     * */
    public static int elegirObjetivo(Planeta[] equipos, int indiceAtacante) {
        System.out.println("Objetivos disponibles:");

        int[] indicesReales = new int[equipos.length];
        int contador = 0;

        for (int i = 0; i < equipos.length; i++) {
            if (i != indiceAtacante && equipos[i].estaVivo()) {
                System.out.println((contador + 1) + ". " + equipos[i].getNombre()
                        + " (" + equipos[i].getVidas() + " vidas)");
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
    public static void aplicarResultadosRonda(Planeta[] equipos,
                                              int[] danoPendiente,
                                              int[] defensaPendiente) {

        for (int i = 0; i < equipos.length; i++) {
            if (!equipos[i].estaVivo()) {
                continue;
            }

            int danoFinal = danoPendiente[i] - defensaPendiente[i];
            if (danoFinal < 0) {
                danoFinal = 0;
            }

            equipos[i].recibirDanio(danoFinal);
        }
    }

    /**
     *
     * */
    public static void mostrarResumenRonda(Planeta[] equipos,
                                           int[] vidasInicio,
                                           int[] danoPendiente,
                                           int[] defensaPendiente) {

        System.out.println("\n---- RESUMEN DE LA RONDA ----");

        for (int i = 0; i < equipos.length; i++) {
            int danoFinal = danoPendiente[i] - defensaPendiente[i];
            if (danoFinal < 0) {
                danoFinal = 0;
            }

            System.out.println(equipos[i].getNombre()
                    + " | Inicio: " + vidasInicio[i]
                    + " | Daño recibido: " + danoPendiente[i]
                    + " | Defensa: " + defensaPendiente[i]
                    + " | Daño final: " + danoFinal
                    + " | Fin: " + equipos[i].getVidas());
        }

        System.out.println("------------------------------");
    }

    /**
     *
     * */
    public static int equiposVivos(Planeta[] equipos) {
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
    public static void mostrarEstadoRonda(Planeta[] equipos) {
        for (Planeta equipo : equipos) {
            if (equipo.estaVivo()) {
                System.out.println(equipo.getNombre() + " > " + equipo.getVidas() + " vidas");
            }
        }
    }

    /**
     *
     * */
    public static void anunciarGanador(Planeta[] equipos) {
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