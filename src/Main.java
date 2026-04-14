import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        Personaje[] equipos = null;

        do {
            try {
                System.out.println("---- MENU ----");
                System.out.println("1º JUGAR");
                System.out.println("2º REGLAS DEL JUEGO");
                System.out.println("3º INFORMACIÓN GENERAL");
                System.out.println("4º INFORMACIÓN DE PERSONAJES");
                System.out.println("0º SALIR");
                opcion = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Has introducido un valor no válido. Elige otra opción (0-4)");
                sc.nextLine();
                continue;
            }

            if (opcion < 0 || opcion > 4) {
                System.out.println("Has introducido una opción no existente. Elige otra opción (0-4)");
                continue;
            }

            switch (opcion) {
                case 0:
                    System.out.println("Saliendo del juego...");
                    break;

                case 1:
                    System.out.println("Creando equipos...");
                    equipos = new Personaje[pedirNumeroEquipos(sc)];
                    crearEquipos(equipos, sc);
                    jugar(equipos, sc);
                    break;

                case 2:
                    mostrarReglas();
                    break;

                case 3:
                    mostrarInformacion();
                    break;

                case 4:
                    infoPersonajes(sc);
                    break;
            }

        } while (opcion != 0);

        sc.close();
    }

    public static int pedirNumeroEquipos(Scanner sc) {
        int numEquipos = 0;

        do {
            System.out.print("¿Cuántos equipos quieres crear? (2-5): ");

            try {
                numEquipos = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Has introducido un valor no válido. Elige otra opción (2-5).");
                sc.nextLine();
                continue;
            }

            if (numEquipos < 2 || numEquipos > 5) {
                System.out.println("Has introducido una opción no existente. Elige otra opción (2-5).");
            }
        } while (numEquipos < 2 || numEquipos > 5);

        return numEquipos;
    }

    public static void crearEquipos(Personaje[] equipos, Scanner sc) {
        for (int i = 0; i < equipos.length; i++) {
            System.out.println("\n[Jugador " + (i + 1) + "]");
            String nombre = pedirPersonaje(sc);
            Tipo tipo = obtenerTipoPorNombre(nombre);
            equipos[i] = new Personaje(nombre, 200, 50, tipo);

            System.out.println("Equipo creado: " + equipos[i].getNombre()
                    + " | Tipo: " + equipos[i].getTipo()
                    + " | Vidas: " + equipos[i].getVidas()
                    + " | Misiles/ronda: " + equipos[i].getMisilesRonda());
        }
    }

    public static Tipo obtenerTipoPorNombre(String nombre) {
        if (nombre.equalsIgnoreCase("Corki")
                || nombre.equalsIgnoreCase("Rumble")
                || nombre.equalsIgnoreCase("Heimer")
                || nombre.equalsIgnoreCase("Heimerdinger")
                || nombre.equalsIgnoreCase("Tristana")
                || nombre.equalsIgnoreCase("Ziggs")) {
            return Tipo.YORDLE;
        }

        if (nombre.equalsIgnoreCase("Jinx")
                || nombre.equalsIgnoreCase("Jhin")
                || nombre.equalsIgnoreCase("Gangplank")
                || nombre.equalsIgnoreCase("Sylas")) {
            return Tipo.HUMANO;
        }

        if (nombre.equalsIgnoreCase("Aurelion")
                || nombre.equalsIgnoreCase("Aurelion Sol")
                || nombre.equalsIgnoreCase("Xerath")
                || nombre.equalsIgnoreCase("Kog'Maw")
                || nombre.equalsIgnoreCase("Kogmaw")) {
            return Tipo.SER_MAGICO;
        }

        return Tipo.HUMANO;
    }

    public static String pedirPersonaje(Scanner sc) {
        int personaje = 0;
        boolean valido = false;
        String nombre = "";

        while (!valido) {
            try {
                mostrarPersonajes();
                System.out.println("13- Otro nombre");
                System.out.println("14- Aleatorio");
                System.out.print("Escoge el número del personaje: ");
                personaje = sc.nextInt();
                sc.nextLine();

                if (personaje < 1 || personaje > 14) {
                    System.out.println("Has introducido una opción no existente. Elige otra opción (1-14)");
                    continue;
                }

                switch (personaje) {
                    case 1:
                        nombre = "Jinx";
                        break;
                    case 2:
                        nombre = "Jhin";
                        break;
                    case 3:
                        nombre = "Gangplank";
                        break;
                    case 4:
                        nombre = "Rumble";
                        break;
                    case 5:
                        nombre = "Heimerdinger";
                        break;
                    case 6:
                        nombre = "Kog'Maw";
                        break;
                    case 7:
                        nombre = "Aurelion Sol";
                        break;
                    case 8:
                        nombre = "Sylas";
                        break;
                    case 9:
                        nombre = "Tristana";
                        break;
                    case 10:
                        nombre = "Xerath";
                        break;
                    case 11:
                        nombre = "Ziggs";
                        break;
                    case 12:
                        nombre = "Corki";
                        break;
                    case 13:
                        System.out.print("Escribe el nombre que quieras: ");
                        nombre = sc.nextLine();
                        break;
                    case 14:
                        int aleatorio = (int) (Math.random() * 12) + 1;

                        switch (aleatorio) {
                            case 1:
                                nombre = "Jinx";
                                break;
                            case 2:
                                nombre = "Jhin";
                                break;
                            case 3:
                                nombre = "Gangplank";
                                break;
                            case 4:
                                nombre = "Rumble";
                                break;
                            case 5:
                                nombre = "Heimerdinger";
                                break;
                            case 6:
                                nombre = "Kog'Maw";
                                break;
                            case 7:
                                nombre = "Aurelion Sol";
                                break;
                            case 8:
                                nombre = "Sylas";
                                break;
                            case 9:
                                nombre = "Tristana";
                                break;
                            case 10:
                                nombre = "Xerath";
                                break;
                            case 11:
                                nombre = "Ziggs";
                                break;
                            case 12:
                                nombre = "Corki";
                                break;
                        }

                        System.out.println("¡Te ha tocado " + nombre + "!");
                        break;
                }

                valido = true;
            } catch (InputMismatchException e) {
                System.out.println("Has introducido un valor no válido. Elige otra opción (1-14)");
                sc.nextLine();
            }
        }

        return nombre;
    }

    public static void jugar(Personaje[] equipos, Scanner sc) {
        int ronda = 1;

        while (equiposVivos(equipos) > 1) {
            System.out.println("\n---- " + (ronda++) + "ª RONDA ----");
            mostrarEstadoRonda(equipos);

            String[] listaAtaques = new String[100];
            int numAtaques = 0;

            for (int i = 0; i < equipos.length && equiposVivos(equipos) > 1; i++) {
                if (equipos[i].getVidas() > 0) {
                    numAtaques = turnoEquipo(equipos, i, sc, listaAtaques, numAtaques);
                }
            }

            System.out.println("\n---- ACCIONES DE LA RONDA ----");
            for (int i = 0; i < numAtaques; i++) {
                System.out.println(listaAtaques[i]);
            }

            for (Personaje e : equipos) {
                if (e.getVidas() > 0) {
                    e.reiniciarMisilesRonda();
                }
            }

            System.out.println("\n---- ESTADO FINAL DE LA RONDA ----");
            System.out.println("Equipos vivos: " + equiposVivos(equipos));
            for (Personaje e : equipos) {
                if (e.getVidas() > 0) {
                    System.out.println(e.getNombre() + ": " + e.getVidas()
                            + " vidas | Tipo: " + e.getTipo()
                            + " | Misiles próxima ronda: " + e.getMisilesRonda());
                }
            }
            System.out.println();
        }

        anunciarGanador(equipos);
    }

    public static int equiposVivos(Personaje[] equipos) {
        int vivos = 0;
        for (Personaje e : equipos) {
            if (e.getVidas() > 0) {
                vivos++;
            }
        }
        return vivos;
    }

    public static void mostrarEstadoRonda(Personaje[] equipos) {
        for (Personaje equipo : equipos) {
            if (equipo.getVidas() > 0) {
                System.out.println(equipo.getNombre() + " > " + equipo.getVidas()
                        + " vidas | Tipo: " + equipo.getTipo()
                        + " | Misiles: " + equipo.getMisilesRonda());
            }
        }
    }

    public static int turnoEquipo(Personaje[] equipos, int indiceAtacante, Scanner sc, String[] listaAtaques,
                                  int numAtaques) {
        Personaje atacante = equipos[indiceAtacante];
        System.out.println("\nTurno de ataque de: " + atacante.getNombre());
        System.out.println("1. Atacar");
        System.out.println("2. Defenderse");

        int accion = 0;

        do {
            try {
                System.out.print("¿Qué quieres hacer? (1-2): ");
                accion = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Valor no válido. Elige 1 o 2.");
                sc.nextLine();
                continue;
            }

            if (accion < 1 || accion > 2) {
                System.out.println("Elige 1 (Atacar) o 2 (Defenderse)");
            }
        } while (accion < 1 || accion > 2);

        if (accion == 2) {
            while (atacante.getMisilesRonda() > 0) {
                int misilesDefensa = 0;

                do {
                    try {
                        System.out.println("Misiles disponibles: " + atacante.getMisilesRonda());
                        System.out.print("¿Cuántos misiles quieres usar para defenderte? ");
                        misilesDefensa = sc.nextInt();
                        sc.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("Has introducido un valor no válido. Elige otra opción (0-" + atacante.getMisilesRonda() + ")");
                        sc.nextLine();
                        continue;
                    }

                    if (misilesDefensa < 0 || misilesDefensa > atacante.getMisilesRonda()) {
                        System.out.println("Usa entre 0 y " + atacante.getMisilesRonda() + " misiles");
                    }
                } while (misilesDefensa < 0 || misilesDefensa > atacante.getMisilesRonda());

                if (misilesDefensa == 0) {
                    break;
                }

                atacante.setMisilesRonda(atacante.getMisilesRonda() - misilesDefensa);
                int defensaTotal = atacante.defender(misilesDefensa);

                String mensaje = atacante.getNombre() + " se defiende con " + misilesDefensa +
                        " misiles y recupera " + defensaTotal + " vidas.";
                listaAtaques[numAtaques] = mensaje;
                numAtaques++;
            }

            return numAtaques;
        }

        System.out.println("Objetivos disponibles: ");
        for (int j = 0; j < equipos.length; j++) {
            if (j != indiceAtacante && equipos[j].getVidas() > 0) {
                System.out.println((j + 1) + ". " + equipos[j].getNombre());
            }
        }

        while (atacante.getMisilesRonda() > 0 && equiposVivos(equipos) > 1) {
            int misiles = 0;

            do {
                try {
                    System.out.println("Misiles disponibles: " + atacante.getMisilesRonda());
                    System.out.print("¿Cuántos misiles quieres usar? ");
                    misiles = sc.nextInt();
                    sc.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("Has introducido un valor no válido. Elige otra opción (0-" + atacante.getMisilesRonda() + ").");
                    sc.nextLine();
                    continue;
                }

                if (misiles < 0 || misiles > atacante.getMisilesRonda()) {
                    System.out.println("Has introducido una opción no existente. Elige otra opción (0-" + atacante.getMisilesRonda() + ").");
                }
            } while (misiles < 0 || misiles > atacante.getMisilesRonda());

            if (misiles == 0) {
                break;
            }

            int objetivo = 0;
            do {
                try {
                    System.out.print("¿A quién quieres atacar? Introduce el número: ");
                    objetivo = sc.nextInt() - 1;
                    sc.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("Objetivo no válido. Elige otra opción (1-" + equipos.length + ").");
                    sc.nextLine();
                    continue;
                }
            } while (objetivo < 0
                    || objetivo >= equipos.length
                    || objetivo == indiceAtacante
                    || equipos[objetivo].getVidas() <= 0);

            int danioHecho = equipos[objetivo].combate(misiles, atacante);
            atacante.setMisilesRonda(atacante.getMisilesRonda() - misiles);

            String mensaje = atacante.getNombre() + " (" + atacante.getTipo() + ") ataca a "
                    + equipos[objetivo].getNombre() + " (" + equipos[objetivo].getTipo() + ") con "
                    + misiles + " misiles y causa " + danioHecho + " de daño.";
            listaAtaques[numAtaques] = mensaje;
            numAtaques++;
        }

        return numAtaques;
    }

    public static void anunciarGanador(Personaje[] equipos) {
        for (Personaje e : equipos) {
            if (e.getVidas() > 0) {
                System.out.println("\n¡" + e.getNombre() + " GANA LA PARTIDA!\n");
                return;
            }
        }
    }

    public static void mostrarReglas() {
        System.out.println("\n--- REGLAS DEL JUEGO ---");
        System.out.println("1. Cada equipo comienza con 200 vidas y 50 misiles por ronda.");
        System.out.println("2. El tipo se asigna automáticamente según el personaje.");
        System.out.println("3. YORDLES: Corki, Rumble, Heimerdinger, Tristana y Ziggs.");
        System.out.println("4. HUMANOS: Jinx, Jhin, Gangplank y Sylas.");
        System.out.println("5. SERES MAGICOS: Aurelion Sol, Xerath y Kog'Maw.");
        System.out.println("6. Los YORDLES tienen un 25% de probabilidad de esquivar.");
        System.out.println("7. Los HUMANOS hacen 1,25x de daño a los YORDLES.");
        System.out.println("8. Los SERES MAGICOS hacen 1,75x a HUMANOS y 0,75x a YORDLES.");
        System.out.println("9. El último equipo en pie gana la partida.\n");
    }

    public static void mostrarInformacion() {
        System.out.println("\n--- INFORMACIÓN ---");
        System.out.println("Versión: 1.0");
        System.out.println("Contacto: losdelfondo@gmail.com");
        System.out.println("Autores: Adrián Vera, Agustín Olivera, Ángel Brito, Mauro Barros, Sergio Rial\n");
    }

    public static void infoPersonajes(Scanner sc) {
        int personaje = 0;

        do {
            try {
                mostrarPersonajes();
                System.out.println("0-  Salir");
                System.out.print("Escoge el número del personaje: \n");
                personaje = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Has introducido un valor no válido. Elige otra opción (0-12)");
                sc.nextLine();
                continue;
            }

            if (personaje < 0 || personaje > 12) {
                System.out.println("Has introducido una opción no existente. Elige otra opción (0-12)");
            }

            switch (personaje) {
                case 1:
                    System.out.println("Jinx - HUMANO");
                    break;
                case 2:
                    System.out.println("Jhin - HUMANO");
                    break;
                case 3:
                    System.out.println("Gangplank - HUMANO");
                    break;
                case 4:
                    System.out.println("Rumble - YORDLE");
                    break;
                case 5:
                    System.out.println("Heimerdinger - YORDLE");
                    break;
                case 6:
                    System.out.println("Kog'Maw - SER_MAGICO");
                    break;
                case 7:
                    System.out.println("Aurelion Sol - SER_MAGICO");
                    break;
                case 8:
                    System.out.println("Sylas - HUMANO");
                    break;
                case 9:
                    System.out.println("Tristana - YORDLE");
                    break;
                case 10:
                    System.out.println("Xerath - SER_MAGICO");
                    break;
                case 11:
                    System.out.println("Ziggs - YORDLE");
                    break;
                case 12:
                    System.out.println("Corki - YORDLE");
                    break;
            }
        } while (personaje != 0);
    }

    public static void mostrarPersonajes() {
        System.out.println("\n---- PERSONAJES ----");
        System.out.println("1-  Jinx");
        System.out.println("2-  Jhin");
        System.out.println("3-  Gangplank");
        System.out.println("4-  Rumble");
        System.out.println("5-  Heimerdinger");
        System.out.println("6-  Kog'Maw");
        System.out.println("7-  Aurelion Sol");
        System.out.println("8-  Sylas");
        System.out.println("9-  Tristana");
        System.out.println("10- Xerath");
        System.out.println("11- Ziggs");
        System.out.println("12- Corki");
    }
}