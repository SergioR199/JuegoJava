import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        Personajes[] equipos = null;

        do {
            try {
                System.out.println("---- MENU ----");
                System.out.println("1º JUGAR");
                System.out.println("2º REGLAS DEL JUEGO");
                System.out.println("3º INFORMACIÓN GENERAL");
                System.out.println("4º INFORMACIÓN DE PERSONAJES");
                System.out.println("0º SALIR");
                System.out.print("Elige una opción: ");
                opcion = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Has introducido un valor no válido. Elige otra opción (0-4).");
                sc.nextLine();
                continue;
            }

            if (opcion < 0 || opcion > 4) {
                System.out.println("Has introducido una opción no existente. Elige otra opción (0-4).");
                continue;
            }

            switch (opcion) {
                case 0:
                    System.out.println("Saliendo del juego...");
                    break;
                case 1:
                    System.out.println("Creando equipos...");
                    equipos = new Personajes[pedirNumeroEquipos(sc)];
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

    public static void crearEquipos(Personajes[] equipos, Scanner sc) {
        for (int i = 0; i < equipos.length; i++) {
            System.out.println("\n[Jugador " + (i + 1) + "]");
            int opcionPersonaje = pedirPersonaje(sc);
            equipos[i] = crearPersonaje(opcionPersonaje, sc);

            System.out.println("Equipo creado: " + equipos[i].getNombre()
                    + " | Tipo: " + equipos[i].getTipo()
                    + " | Vidas: " + equipos[i].getVidas()
                    + " | Misiles/ronda: " + equipos[i].getMisilesRonda());
        }
    }

    public static Personajes crearPersonaje(int opcion, Scanner sc) {
        switch (opcion) {
            case 1:
                return new Jinx();
            case 2:
                return new Jhin();
            case 3:
                return new Gangplank();
            case 4:
                return new Rumble();
            case 5:
                return new Heimerdinger();
            case 6:
                return new KogMaw();
            case 7:
                return new AurelionSol();
            case 8:
                return new Sylas();
            case 9:
                return new Tristana();
            case 10:
                return new Xerath();
            case 11:
                return new Ziggs();
            case 12:
                return new Corki();
            case 13:
                return crearPersonalizado(sc);
            case 14:
                int aleatorio = (int) (Math.random() * 12) + 1;
                return crearPersonaje(aleatorio, sc);
            default:
                return new Jinx();
        }
    }

    public static Personajes crearPersonalizado(Scanner sc) {
        String nombre;
        int tipo;

        System.out.print("Escribe el nombre del personaje: ");
        nombre = sc.nextLine();

        do {
            try {
                System.out.println("Elige el tipo del personaje:");
                System.out.println("1- HUMANO");
                System.out.println("2- YORDLE");
                System.out.println("3- SER MAGICO");
                System.out.print("Opción: ");
                tipo = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Has introducido un valor no válido.");
                sc.nextLine();
                tipo = 0;
                continue;
            }

            if (tipo < 1 || tipo > 3) {
                System.out.println("Opción no válida. Elige entre 1 y 3.");
            }
        } while (tipo < 1 || tipo > 3);

        switch (tipo) {
            case 1:
                return new HumanoPersonalizado(nombre, 200, 50);
            case 2:
                return new YordlePersonalizado(nombre, 200, 50);
            case 3:
                return new SerMagicoPersonalizado(nombre, 200, 50);
            default:
                return new HumanoPersonalizado(nombre, 200, 50);
        }
    }

    public static int pedirPersonaje(Scanner sc) {
        int personaje = 0;
        boolean valido = false;

        while (!valido) {
            try {
                mostrarPersonajes();
                System.out.println("13- Personalizado");
                System.out.println("14- Aleatorio");
                System.out.print("Escoge el número del personaje: ");
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

    public static void jugar(Personajes[] equipos, Scanner sc) {
        int ronda = 1;

        while (equiposVivos(equipos) > 1) {
            System.out.println("\n---- " + (ronda++) + "ª RONDA ----");
            mostrarEstadoRonda(equipos);

            String[] listaAcciones = new String[200];
            int numAcciones = 0;

            for (int i = 0; i < equipos.length && equiposVivos(equipos) > 1; i++) {
                if (equipos[i].getVidas() > 0) {
                    numAcciones = turnoEquipo(equipos, i, sc, listaAcciones, numAcciones);
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

            System.out.println("\n---- ESTADO FINAL DE LA RONDA ----");
            System.out.println("Equipos vivos: " + equiposVivos(equipos));
            for (Personajes e : equipos) {
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

    public static int equiposVivos(Personajes[] equipos) {
        int vivos = 0;
        for (Personajes e : equipos) {
            if (e.getVidas() > 0) {
                vivos++;
            }
        }
        return vivos;
    }

    public static void mostrarEstadoRonda(Personajes[] equipos) {
        for (Personajes equipo : equipos) {
            if (equipo.getVidas() > 0) {
                System.out.println(equipo.getNombre() + " > " + equipo.getVidas()
                        + " vidas | Tipo: " + equipo.getTipo()
                        + " | Misiles: " + equipo.getMisilesRonda());
            }
        }
    }

    public static int turnoEquipo(Personajes[] equipos, int indiceAtacante, Scanner sc, String[] listaAcciones,
                                  int numAcciones) {
        Personajes atacante = equipos[indiceAtacante];
        System.out.println("\nTurno de: " + atacante.getNombre());
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
                System.out.println("Elige 1 (Atacar) o 2 (Defenderse).");
            }
        } while (accion < 1 || accion > 2);

        if (accion == 2) {
            int misilesDefensa = 0;

            do {
                try {
                    System.out.println("Misiles disponibles: " + atacante.getMisilesRonda());
                    System.out.print("¿Cuántos misiles quieres usar para defenderte? ");
                    misilesDefensa = sc.nextInt();
                    sc.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("Has introducido un valor no válido.");
                    sc.nextLine();
                    continue;
                }

                if (misilesDefensa < 0 || misilesDefensa > atacante.getMisilesRonda()) {
                    System.out.println("Usa entre 0 y " + atacante.getMisilesRonda() + " misiles.");
                }
            } while (misilesDefensa < 0 || misilesDefensa > atacante.getMisilesRonda());

            if (misilesDefensa > 0) {
                atacante.setMisilesRonda(atacante.getMisilesRonda() - misilesDefensa);
                int defensaTotal = atacante.defender(misilesDefensa);

                String mensaje = atacante.getNombre() + " se defiende con " + misilesDefensa
                        + " misiles y recupera " + defensaTotal + " vidas.";
                listaAcciones[numAcciones] = mensaje;
                numAcciones++;
            }

            return numAcciones;
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
                    System.out.println("Has introducido un valor no válido.");
                    sc.nextLine();
                    continue;
                }

                if (misiles < 0 || misiles > atacante.getMisilesRonda()) {
                    System.out.println("Usa entre 0 y " + atacante.getMisilesRonda() + " misiles.");
                }
            } while (misiles < 0 || misiles > atacante.getMisilesRonda());

            if (misiles == 0) {
                break;
            }

            int[] objetivosValidos = new int[equipos.length];
            int totalObjetivos = 0;

            System.out.println("Objetivos disponibles:");
            for (int j = 0; j < equipos.length; j++) {
                if (j != indiceAtacante && equipos[j].getVidas() > 0) {
                    objetivosValidos[totalObjetivos] = j;
                    System.out.println((totalObjetivos + 1) + ". " + equipos[j].getNombre());
                    totalObjetivos++;
                }
            }

            int opcionObjetivo = 0;
            do {
                try {
                    System.out.print("¿A quién quieres atacar? Introduce el número: ");
                    opcionObjetivo = sc.nextInt();
                    sc.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("Objetivo no válido.");
                    sc.nextLine();
                    continue;
                }

                if (opcionObjetivo < 1 || opcionObjetivo > totalObjetivos) {
                    System.out.println("Elige un objetivo válido.");
                }
            } while (opcionObjetivo < 1 || opcionObjetivo > totalObjetivos);

            int indiceObjetivoReal = objetivosValidos[opcionObjetivo - 1];

            int danioHecho = equipos[indiceObjetivoReal].combate(misiles, atacante);
            atacante.setMisilesRonda(atacante.getMisilesRonda() - misiles);

            String mensaje = atacante.getNombre() + " (" + atacante.getTipo() + ") ataca a "
                    + equipos[indiceObjetivoReal].getNombre() + " (" + equipos[indiceObjetivoReal].getTipo() + ") con "
                    + misiles + " misiles y causa " + danioHecho + " de daño.";
            listaAcciones[numAcciones] = mensaje;
            numAcciones++;
        }

        return numAcciones;
    }

    public static void anunciarGanador(Personajes[] equipos) {
        for (Personajes e : equipos) {
            if (e.getVidas() > 0) {
                System.out.println("\n¡" + e.getNombre() + " GANA LA PARTIDA!\n");
                return;
            }
        }
    }

    public static void mostrarReglas() {
        System.out.println("\n--- REGLAS DEL JUEGO ---");
        System.out.println("1. Cada personaje tiene estadísticas base propias.");
        System.out.println("2. Los YORDLES tienen un 25% de probabilidad de esquivar ataques.");
        System.out.println("3. Los HUMANOS hacen 1,25x de daño a los YORDLES.");
        System.out.println("4. Los SERES MAGICOS hacen 1,75x de daño a HUMANOS y 0,75x a YORDLES.");
        System.out.println("5. Cada equipo puede atacar o defenderse en su turno.");
        System.out.println("6. El último equipo en pie gana la partida.\n");
    }

    public static void mostrarInformacion() {
        System.out.println("\n--- INFORMACIÓN ---");
        System.out.println("Versión: 2.1");
        System.out.println("Juego basado en herencia por tipo y personaje.");
        System.out.println("Sistema de daño y estadísticas base personalizadas.\n");
    }

    public static void infoPersonajes(Scanner sc) {
        int personaje = 0;

        do {
            try {
                mostrarPersonajes();
                System.out.println("0- Salir");
                System.out.print("Escoge el número del personaje: ");
                personaje = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Has introducido un valor no válido. Elige otra opción (0-12).");
                sc.nextLine();
                continue;
            }

            if (personaje < 0 || personaje > 12) {
                System.out.println("Has introducido una opción no existente. Elige otra opción (0-12).");
                continue;
            }

            switch (personaje) {
                case 1:
                    System.out.println("Jinx - HUMANO | Vidas: 180 | Misiles: 55");
                    break;
                case 2:
                    System.out.println("Jhin - HUMANO | Vidas: 170 | Misiles: 60");
                    break;
                case 3:
                    System.out.println("Gangplank - HUMANO | Vidas: 210 | Misiles: 45");
                    break;
                case 4:
                    System.out.println("Rumble - YORDLE | Vidas: 160 | Misiles: 50");
                    break;
                case 5:
                    System.out.println("Heimerdinger - YORDLE | Vidas: 150 | Misiles: 55");
                    break;
                case 6:
                    System.out.println("Kog'Maw - SER MAGICO | Vidas: 170 | Misiles: 60");
                    break;
                case 7:
                    System.out.println("Aurelion Sol - SER MAGICO | Vidas: 220 | Misiles: 45");
                    break;
                case 8:
                    System.out.println("Sylas - HUMANO | Vidas: 190 | Misiles: 50");
                    break;
                case 9:
                    System.out.println("Tristana - YORDLE | Vidas: 155 | Misiles: 60");
                    break;
                case 10:
                    System.out.println("Xerath - SER MAGICO | Vidas: 160 | Misiles: 70");
                    break;
                case 11:
                    System.out.println("Ziggs - YORDLE | Vidas: 145 | Misiles: 65");
                    break;
                case 12:
                    System.out.println("Corki - YORDLE | Vidas: 165 | Misiles: 55");
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