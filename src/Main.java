import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import testJuego1_Utils.Validaciones;

/**
 * Clase principal del juego. Contiene el menú inicial,
 * la creación de equipos y el desarrollo de las rondas.
 * */
public class Main {
    public static Personajes[] equipos;

    /**
     * Punto de entrada del programa. Muestra el menú principal y
     * permite acceder a las distintas opciones del juego.
     *
     * @param args Argumentos de línea de comandos.
     * */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion = -1;
        int partida = 1;

        do {
            System.out.print("---- MENU ----\n" +
                    "1º JUGAR\n" +
                    "2º REGLAS DEL JUEGO\n" +
                    "3º INFORMACIÓN GENERAL\n" +
                    "4º INFORMACIÓN DE PERSONAJES\n" +
                    "5º MOSTRAR PERSONAJES GANADORES\n" +
                    "0º SALIR\n" +
                    "Elige una opción: ");

            do {
                try {
                    opcion = sc.nextInt();
                    sc.nextLine();
                    if (opcion < 0 || opcion > 5) {
                        System.out.println("Has introducido una opción no existente. Elige otra opción (0-5).");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Has introducido un valor no válido. Elige otra opción (0-5).");
                    sc.nextLine();
                    opcion = -1;
                }
            } while (opcion < 0 || opcion > 5);

            switch (opcion) {
                case 0:
                    System.out.println("Saliendo del juego...");
                    try {
                        new FileWriter("ganadores.txt").close();
                    } catch (IOException e) {
                        System.out.println("ERROR DE BORRADO");
                    }
                    break;

                case 1:
                    System.out.println("Creando equipos...");
                    equipos = new Personajes[pedirNumeroEquipos(sc)];
                    crearEquipos(equipos, sc);
                    jugar(equipos, sc, partida);
                    partida += 1;
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

                case 5:
                    Ganadores.lecturaArchivo();
                    break;
            }

        } while (opcion != 0);
    }

    /**
     * Método que pregunta al usuario cuantos equipos quiere crear para la partida.
     * El mínimo son 3; si mete algo menor, reseteamos el valor a 0 para que el bucle le vuelva a preguntar.
     *
     * @param sc Scanner para leer lo que escribe el usuario.
     * @return Número de equipos que ha elegido, siempre 3 o más.
     */
    public static int pedirNumeroEquipos(Scanner sc) {
        int numEquipos = 0;

        do {
            System.out.print("¿Cuántos equipos quieres crear? (mínimo 3): ");
            try {
                numEquipos = sc.nextInt();
                sc.nextLine();
                numEquipos = (numEquipos >= 3) ? numEquipos : 0;
                if (numEquipos == 0) {
                    System.out.println("Has introducido una opción no existente. Elige otra opción (mínimo 3).");
                }
            } catch (InputMismatchException e) {
                System.out.println("Has introducido un valor no válido. Elige otra opción (mínimo 3).");
                sc.nextLine();
                numEquipos = 0;
            }
        } while (numEquipos < 3);

        return numEquipos;
    }

    /**
     * Método que pide un identificador válido y único.
     * El formato debe ser de 4 números seguidos de 3 letras mayúsculas,
     * y no puede repetirse dentro del array de equipos.
     *
     * @param sc Scanner utilizado para leer lo que escribe el usuario.
     * @param equipos Array de personajes ya creados.
     * @return Identificador válido y no repetido.
     */
    public static String pedirIdentificador(Scanner sc, Personajes[] equipos) {
        String identificador;
        boolean repetido = true;

        do {
            System.out.print("Introduce el identificador (4 números y 3 letras mayúsculas, ej. 4325EIS): ");
            identificador = sc.nextLine().trim();

            if (!Validaciones.validarIdentificador(identificador)) {
                System.out.println("Identificador no válido. Debe tener 4 números seguidos de 3 letras mayúsculas.");
            } else {
                repetido = false;

                for (int i = 0; i < equipos.length; i++) {
                    if (equipos[i] != null && equipos[i].getIdentificador().equals(identificador)) {
                        System.out.println("Ese identificador ya está en uso. Introduce otro distinto.");
                        repetido = true;
                        break;
                    }
                }
            }
        } while (repetido);

        return identificador;
    }

    /**
     * Método que crea todos los equipos de la partida pidiendo al usuario un personaje para cada jugador.
     *
     * @param equipos Array donde se guardarán los personajes creados.
     * @param sc Scanner utilizado para leer lo que escribe el usuario.
     * */
    public static void crearEquipos(Personajes[] equipos, Scanner sc) {
        for (int i = 0; i < equipos.length; i++) {
            System.out.println("\n[Jugador " + (i + 1) + "]");
            int opcionPersonaje = pedirPersonaje(sc);
            String identificador = pedirIdentificador(sc,equipos);
            equipos[i] = crearPersonaje(opcionPersonaje, identificador, sc);

            System.out.println("Equipo creado: " + equipos[i].getNombre()
                    + " | ID: " + equipos[i].getIdentificador()
                    + " | Tipo: " + obtenerRaza(equipos[i])
                    + " | Vidas: " + equipos[i].getVidas()
                    + " | Misiles/ronda: " + equipos[i].getMisilesRonda());
        }
    }

    /**
     * Método que crea un personaje según la opción seleccionada por el usuario.
     *
     * @param opcion Número que corresponde al personaje elegido.
     * @param identificador Identificador del personaje.
     * @param sc Scanner utilizado para crear un personaje personalizado si se necesita.
     * @return Instancia del personaje seleccionado.
     * */
    public static Personajes crearPersonaje(int opcion, String identificador, Scanner sc) {
        switch (opcion) {
            case 1:
                return new Jinx(identificador);
            case 2:
                return new Jhin(identificador);
            case 3:
                return new Gangplank(identificador);
            case 4:
                return new Rumble(identificador);
            case 5:
                return new Heimerdinger(identificador);
            case 6:
                return new KogMaw(identificador);
            case 7:
                return new AurelionSol(identificador);
            case 8:
                return new Sylas(identificador);
            case 9:
                return new Tristana(identificador);
            case 10:
                return new Xerath(identificador);
            case 11:
                return new Ziggs(identificador);
            case 12:
                return new Corki(identificador);
            case 13:
                return crearPersonalizado(identificador, sc);
            case 14:
                int aleatorio = (int) (Math.random() * 12) + 1;
                return crearPersonaje(aleatorio, identificador, sc);
            default:
                return new Jinx(identificador);
        }
    }

    /**
     * Método que crea un personaje personalizado introducido por el usuario.
     *
     * @param identificador Identificador del personaje.
     * @param sc Scanner utilizado para leer el nombre y el tipo del personaje.
     * @return Instancia de un personaje personalizado.
     * */
    public static Personajes crearPersonalizado(String identificador, Scanner sc) {
        String nombre;
        int raza;

        System.out.print("Escribe el nombre del personaje: ");
        nombre = sc.nextLine();

        do {
            try {
                System.out.println("Elige la raza del personaje:\n" +
                        "1- HUMANO\n" +
                        "2- YORDLE\n" +
                        "3- SER MAGICO\n" +
                        "Opción: ");
                raza = sc.nextInt();
                sc.nextLine();
                if (raza < 1 || raza > 3) {
                    System.out.println("Opción no válida. Elige entre 1 y 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Has introducido un valor no válido.");
                sc.nextLine();
                raza = 0;
            }
        } while (raza < 1 || raza > 3);

        switch (raza) {
            case 1:
                return new HumanoPersonalizado(nombre, identificador, 200, 50);
            case 2:
                return new YordlePersonalizado(nombre, identificador, 200, 50);
            case 3:
                return new SerMagicoPersonalizado(nombre, identificador, 200, 50);
            default:
                return new HumanoPersonalizado(nombre, identificador, 200, 50);
        }
    }

    /**
     * Método que muestra la lista de personajes disponibles y devuelve la opción elegida.
     *
     * @param sc Scanner utilizado para leer la selección del usuario.
     * @return Número del personaje seleccionado.
     * */
    public static int pedirPersonaje(Scanner sc) {
        int personaje = 0;
        boolean valido = false;

        while (!valido) {
            try {
                InformacionJuego.mostrarPersonajes();
                System.out.println("13- Personalizado\n" +
                        "14- Aleatorio\n" +
                        "Escoge el número del personaje: ");
                personaje = sc.nextInt();
                sc.nextLine();

                if (personaje < 1 || personaje > 14) {
                    System.out.println("Has introducido una opción no existente. Elige otra opción (1-14).");
                    continue;
                }

                valido = true;
            } catch (InputMismatchException e) {
                System.out.println("Has introducido un valor no válido. Elige otra opción (1-14).");
                sc.nextLine();
            }
        }

        return personaje;
    }

    /**
     * Método que ejecuta la partida completa hasta que solo quede un equipo con vida.
     *
     * @param equipos Array con todos los personajes de la partida.
     * @param sc Scanner utilizado para leer las decisiones de los jugadores.
     * @param partida Número de partida actual.
     * */
    public static void jugar(Personajes[] equipos, Scanner sc, int partida) {
        int ronda = 1;
        int turnoGlobal = 0;

        while (equiposVivos(equipos) > 1) {
            for (Personajes p : equipos) {
                if (p.getVidas() > 0) {
                    p.pasivaRonda(turnoGlobal);
                }
            }

            turnoGlobal++;
            System.out.println("\n---- " + ronda + "ª RONDA ----");
            mostrarEstadoRonda(equipos);

            String[] listaAcciones = new String[200];
            int numAcciones = 0;

            for (int i = 0; i < equipos.length && equiposVivos(equipos) > 1; i++) {
                if (equipos[i].getVidas() > 0) {
                    numAcciones = turnoEquipo(equipos, i, sc, listaAcciones, numAcciones, ronda);
                }
            }

            System.out.println("\n---- ACCIONES DE LA RONDA ----");
            for (int i = 0; i < numAcciones; i++) {
                System.out.println(listaAcciones[i]);
            }

            for (Personajes e : equipos) {
                if (e.getVidas() > 0) {
                    e.reiniciarMisilesRonda();
                }
            }

            System.out.println("\n---- ESTADO FINAL DE LA RONDA ----\n" +
                    "Equipos vivos: " + equiposVivos(equipos));
            for (Personajes e : equipos) {
                if (e.getVidas() > 0) {
                    System.out.println(e.getNombre() + " [" + e.getIdentificador() + "]: " + e.getVidas()
                            + " vidas | Tipo: " + obtenerRaza(e)
                            + " | Misiles próxima ronda: " + e.getMisilesRonda());
                }
            }

            ronda++;
            System.out.println();
        }

        Ganadores.anunciarGanador(equipos, partida);
    }

    /**
     * Método que cuenta cuántos personajes quedan vivos en la partida.
     * Recorre el array y por cada uno comprueba si tiene vidas usando un operador ternario.
     *
     * @param equipos El array con todos los personajes de la partida.
     * @return Cuantos personajes tienen todavia vidas por encima de cero.
     */
    public static int equiposVivos(Personajes[] equipos) {
        int vivos = 0;
        for (Personajes e : equipos) {
           if (e.getVidas()>0){
               vivos++;
           }
        }
        return vivos;
    }

    /**
     * Método que muestra el estado de todos los equipos que siguen en pie durante la ronda.
     * También usamos un operador ternario para que escriba "vida" o "vidas" dependiendo
     * de si son 1 o varias.
     *
     * @param equipos El array con todos los personajes de la partida.
     */
    public static void mostrarEstadoRonda(Personajes[] equipos) {
        for (Personajes equipo : equipos) {
            if (equipo.getVidas() > 0) {
                String vidaLabel = (equipo.getVidas() == 1) ? "vida" : "vidas";
                System.out.println(equipo.getNombre() + " [" + equipo.getIdentificador() + "] > " + equipo.getVidas()
                        + " " + vidaLabel + " | Tipo: " + obtenerRaza(equipo)
                        + " | Misiles: " + equipo.getMisilesRonda());
            }
        }
    }

    /**
     * Método que gestiona el turno de un equipo, permitiendo atacar o defenderse.
     *
     * @param equipos Array con todos los personajes de la partida.
     * @param indiceAtacante Índice del personaje que realiza el turno.
     * @param sc Scanner utilizado para leer las decisiones del usuario.
     * @param listaAcciones Array donde se guardan los mensajes de cada acción.
     * @param numAcciones Número actual de acciones guardadas.
     * @param ronda Ronda actual.
     * @return Número actualizado de acciones guardadas.
     * */
    public static int turnoEquipo(Personajes[] equipos, int indiceAtacante, Scanner sc, String[] listaAcciones, int numAcciones, int ronda) {
        Personajes atacante = equipos[indiceAtacante];

        while (atacante.getVidas() > 0 && atacante.getMisilesRonda() > 0 && equiposVivos(equipos) > 1) {
            int accion = -1;

            if (ronda == 1) {
                System.out.println("\nTurno de: " + atacante.getNombre() + "\n" +
                        "1. Atacar\n" +
                        "0. Terminar turno");

                do {
                    try {
                        System.out.print("En la primera ronda solo puedes atacar (1) o terminar turno (0): ");
                        accion = sc.nextInt();
                        sc.nextLine();

                        if (accion != 0 && accion != 1) {
                            System.out.println("Elige 0 o 1.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Valor no válido. Elige 0 o 1.");
                        sc.nextLine();
                        accion = -1;
                    }
                } while (accion != 0 && accion != 1);

            } else {
                System.out.println("\nTurno de: " + atacante.getNombre() + "\n" +
                        "1. Atacar\n" +
                        "2. Defenderse\n" +
                        "0. Terminar turno");

                do {
                    try {
                        System.out.print("¿Qué quieres hacer? 1 (Atacar), 2 (Defenderte), 0 (Terminar turno): ");
                        accion = sc.nextInt();
                        sc.nextLine();

                        if (accion < 0 || accion > 2) {
                            System.out.println("Elige 0, 1 o 2.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Valor no válido. Elige 0, 1 o 2.");
                        sc.nextLine();
                        accion = -1;
                    }
                } while (accion < 0 || accion > 2);
            }

            if (accion == 0) {
                break;
            }

            if (accion == 2) {
                int misilesDefensa = -1;

                do {
                    try {
                        System.out.print("Misiles disponibles: " + atacante.getMisilesRonda() +
                                "\n¿Cuántos misiles quieres usar para defenderte? ");
                        misilesDefensa = sc.nextInt();
                        sc.nextLine();

                        if (misilesDefensa < 0 || misilesDefensa > atacante.getMisilesRonda()) {
                            System.out.println("Usa entre 1 y " + atacante.getMisilesRonda() + " misiles.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Has introducido un valor no válido.");
                        sc.nextLine();
                        misilesDefensa = -1;
                    }
                } while (misilesDefensa < 0 || misilesDefensa > atacante.getMisilesRonda());

                if (misilesDefensa == 0) {
                    System.out.println("No has gastado misiles en defenderte.");
                    continue;
                }

                atacante.setMisilesRonda(atacante.getMisilesRonda() - misilesDefensa);
                int defensaTotal = atacante.accion("defender", misilesDefensa, atacante);

                String mensaje = atacante.getNombre() + " [" + atacante.getIdentificador() + "] se defiende con "
                        + misilesDefensa + " misiles y recupera " + defensaTotal + " vidas.";
                listaAcciones[numAcciones] = mensaje;
                numAcciones++;

                if (atacante.getMisilesRonda() > 0) {
                    System.out.println("Te quedan " + atacante.getMisilesRonda() + " misiles.");
                }
                continue;
            }

            if (accion == 1) {
                int misiles = -1;

                do {
                    try {
                        System.out.print("Misiles disponibles: " + atacante.getMisilesRonda() +
                                "\n¿Cuántos misiles quieres usar para atacar? ");
                        misiles = sc.nextInt();
                        sc.nextLine();

                        if (misiles < 0 || misiles > atacante.getMisilesRonda()) {
                            System.out.println("Usa entre 1 y " + atacante.getMisilesRonda() + " misiles.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Has introducido un valor no válido.");
                        sc.nextLine();
                        misiles = -1;
                    }
                } while (misiles < 0 || misiles > atacante.getMisilesRonda());

                if (misiles == 0) {
                    System.out.println("No has usado misiles para atacar.");
                    continue;
                }

                int[] objetivosValidos = new int[equipos.length];
                int totalObjetivos = 0;

                System.out.println("Objetivos disponibles:");
                for (int j = 0; j < equipos.length; j++) {
                    if (j != indiceAtacante && equipos[j].getVidas() > 0) {
                        objetivosValidos[totalObjetivos] = j;
                        System.out.println((totalObjetivos + 1) + ". " +
                                equipos[j].getNombre() + " [" + equipos[j].getIdentificador() + "]");
                        totalObjetivos++;
                    }
                }

                if (totalObjetivos == 0) {
                    break;
                }

                int opcionObjetivo = -1;
                do {
                    try {
                        System.out.print("¿A quién quieres atacar? Introduce el número: ");
                        opcionObjetivo = sc.nextInt();
                        sc.nextLine();

                        if (opcionObjetivo < 1 || opcionObjetivo > totalObjetivos) {
                            System.out.println("Elige un objetivo válido.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Objetivo no válido.");
                        sc.nextLine();
                        opcionObjetivo = -1;
                    }
                } while (opcionObjetivo < 1 || opcionObjetivo > totalObjetivos);

                int indiceObjetivoReal = objetivosValidos[opcionObjetivo - 1];
                int danioHecho = equipos[indiceObjetivoReal].accion("atacar", misiles, atacante);

                if (atacante instanceof Rumble) {
                    ((Rumble) atacante).alAtacar(equipos, indiceObjetivoReal);
                }

                if (atacante instanceof Ziggs) {
                    ((Ziggs) atacante).alAtacar(equipos, indiceAtacante, danioHecho);
                }

                String mensaje = atacante.getNombre() + " [" + atacante.getIdentificador() + "] (" + obtenerRaza(atacante) + ") ataca a "
                        + equipos[indiceObjetivoReal].getNombre() + " [" + equipos[indiceObjetivoReal].getIdentificador() + "] ("
                        + obtenerRaza(equipos[indiceObjetivoReal]) + ") con "
                        + misiles + " misiles y causa " + danioHecho + " de daño.";
                listaAcciones[numAcciones] = mensaje;
                numAcciones++;

                if (atacante.getMisilesRonda() > 0) {
                    System.out.println("Te quedan " + atacante.getMisilesRonda() + " misiles.");
                }
            }
        }

        return numAcciones;
    }

    /**
     * Método que lee el número entero que introduce el usuario dentro del rango definido,
     * teniendo que repetir hasta que se introduzca un valor válido.
     *
     * @param sc Scanner que se utiliza para leer el número que introduce el usuario.
     * @param min Valor mínimo.
     * @param max Valor máximo.
     * @param mensaje Mensaje que se le muestra al usuario.
     * @return Número entero válido.
     * */
    public static int leerEnteroRango(Scanner sc, int min, int max, String mensaje) {
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

    /**
     * Método que obtiene la raza de un personaje según su clase concreta. Comprueba el tipo real
     * del objeto usando instanceof y devuelve la raza correspondiente:
     * HUMANO, YORDLE, SER MAGICO o DESCONOCIDA si no coincide con ninguna clase conocida.
     *
     * @param p Personaje del que se quiere obtener la raza.
     * @return String con la raza del personaje.
     * */
    public static String obtenerRaza(Personajes p) {
        if (p instanceof Gangplank ||
                p instanceof HumanoPersonalizado ||
                p instanceof Jhin ||
                p instanceof Jinx ||
                p instanceof Sylas) {
            return "HUMANO";
        }

        if (p instanceof Corki ||
                p instanceof Heimerdinger ||
                p instanceof Rumble ||
                p instanceof Tristana ||
                p instanceof YordlePersonalizado ||
                p instanceof Ziggs) {
            return "YORDLE";
        }

        if (p instanceof AurelionSol ||
                p instanceof KogMaw ||
                p instanceof SerMagicoPersonalizado ||
                p instanceof Xerath) {
            return "SER MAGICO";
        }

        return "DESCONOCIDA";
    }
}