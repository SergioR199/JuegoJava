import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        Planeta[] equipos = null;

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
                    equipos = new Planeta[pedirNumeroEquipos(sc)];
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

    public static void crearEquipos(Planeta[] equipos, Scanner sc) {
        for (int i = 0; i < equipos.length; i++) {
            System.out.println("\n[Jugador " + (i + 1) + "]");
            String nombre = pedirPersonaje(sc);
            TipoPlaneta tipo = pedirTipoPlaneta(sc);
            equipos[i] = new Planeta(nombre, 200, 50, tipo);

            System.out.println("Equipo creado: " + equipos[i].getNombre()
                    + " | Tipo: " + equipos[i].getTipo()
                    + " | Vidas: " + equipos[i].getVidas()
                    + " | Misiles/ronda: " + equipos[i].getMisiles_ronda());
        }
    }

    public static TipoPlaneta pedirTipoPlaneta(Scanner sc) {
        int opcion = 0;

        do {
            try {
                System.out.println("\n---- TIPOS DE PLANETA ----");
                System.out.println("1- CABALLERO");
                System.out.println("2- ASESINO");
                System.out.println("3- TANQUE");
                System.out.println("4- MAGO");
                System.out.println("5- TIRADOR");
                System.out.println("6- LADRON");
                System.out.print("Elige el tipo de planeta: ");
                opcion = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Has introducido un valor no válido.");
                sc.nextLine();
                continue;
            }

            if (opcion < 1 || opcion > 6) {
                System.out.println("Opción no válida. Elige entre 1 y 6.");
            }
        } while (opcion < 1 || opcion > 6);

        switch (opcion) {
            case 1:
                return TipoPlaneta.CABALLERO;
            case 2:
                return TipoPlaneta.ASESINO;
            case 3:
                return TipoPlaneta.TANQUE;
            case 4:
                return TipoPlaneta.MAGO;
            case 5:
                return TipoPlaneta.TIRADOR;
            case 6:
                return TipoPlaneta.LADRON;
            default:
                return TipoPlaneta.CABALLERO;
        }
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
                System.out.println("0-  Salir");
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
                System.out.println("Has introducido un valor no válido. Elige otra opción (0-14)");
                sc.nextLine();
            }
        }

        return nombre;
    }

    public static void jugar(Planeta[] equipos, Scanner sc) {
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

            System.out.println("\n---- ATAQUES DE LA RONDA ----");
            for (int i = 0; i < numAtaques; i++) {
                System.out.println(listaAtaques[i]);
            }

            for (Planeta e : equipos) {
                if (e.getVidas() > 0) {
                    e.reiniciarMisilesRonda();
                }
            }

            System.out.println("\n---- ESTADO FINAL DE LA RONDA ----");
            System.out.println("Equipos vivos: " + equiposVivos(equipos));
            for (Planeta e : equipos) {
                if (e.getVidas() > 0) {
                    System.out.println(e.getNombre() + ": " + e.getVidas()
                            + " vidas | Tipo: " + e.getTipo()
                            + " | Misiles próxima ronda: " + e.getMisiles_ronda());
                }
            }
            System.out.println();
        }

        anunciarGanador(equipos);
    }

    public static int equiposVivos(Planeta[] equipos) {
        int vivos = 0;
        for (Planeta e : equipos) {
            if (e.getVidas() > 0) {
                vivos++;
            }
        }
        return vivos;
    }

    public static void mostrarEstadoRonda(Planeta[] equipos) {
        for (Planeta equipo : equipos) {
            if (equipo.getVidas() > 0) {
                System.out.println(equipo.getNombre() + " > " + equipo.getVidas()
                        + " vidas | Tipo: " + equipo.getTipo()
                        + " | Misiles: " + equipo.getMisiles_ronda());
            }
        }
    }

    public static int turnoEquipo(Planeta[] equipos, int indiceAtacante, Scanner sc, String[] listaAtaques,
                                  int numAtaques) {
        Planeta atacante = equipos[indiceAtacante];
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
            while (atacante.getMisiles_ronda() > 0) {
                int misilesDefensa = 0;

                do {
                    try {
                        System.out.println("Misiles disponibles: " + atacante.getMisiles_ronda());
                        System.out.print("¿Cuántos misiles quieres usar para defenderte? ");
                        misilesDefensa = sc.nextInt();
                        sc.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("Has introducido un valor no válido. Elige otra opción (0-" + atacante.getMisiles_ronda() + ")");
                        sc.nextLine();
                        continue;
                    }

                    if (misilesDefensa < 0 || misilesDefensa > atacante.getMisiles_ronda()) {
                        System.out.println("Usa entre 0 y " + atacante.getMisiles_ronda() + " misiles");
                    }
                } while (misilesDefensa < 0 || misilesDefensa > atacante.getMisiles_ronda());

                if (misilesDefensa == 0) {
                    break;
                }

                atacante.setMisiles_ronda(atacante.getMisiles_ronda() - misilesDefensa);
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

        while (atacante.getMisiles_ronda() > 0 && equiposVivos(equipos) > 1) {
            int misiles = 0;

            do {
                try {
                    System.out.println("Misiles disponibles: " + atacante.getMisiles_ronda());
                    System.out.print("¿Cuántos misiles quieres usar? ");
                    misiles = sc.nextInt();
                    sc.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("Has introducido un valor no válido. Elige otra opción (0-" + atacante.getMisiles_ronda() + ").");
                    sc.nextLine();
                    continue;
                }

                if (misiles < 0 || misiles > atacante.getMisiles_ronda()) {
                    System.out.println("Has introducido una opción no existente. Elige otra opción (0-" + atacante.getMisiles_ronda() + ").");
                }
            } while (misiles < 0 || misiles > atacante.getMisiles_ronda());

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
            atacante.setMisiles_ronda(atacante.getMisiles_ronda() - misiles);

            String mensaje = atacante.getNombre() + " (" + atacante.getTipo() + ") ataca a "
                    + equipos[objetivo].getNombre() + " (" + equipos[objetivo].getTipo() + ") con "
                    + misiles + " misiles y causa " + danioHecho + " de daño.";
            listaAtaques[numAtaques] = mensaje;
            numAtaques++;
        }

        return numAtaques;
    }

    public static void anunciarGanador(Planeta[] equipos) {
        for (Planeta e : equipos) {
            if (e.getVidas() > 0) {
                System.out.println("\n¡" + e.getNombre() + " GANA LA PARTIDA!\n");
                return;
            }
        }
    }

    public static void mostrarReglas() {
        System.out.println("\n--- REGLAS DEL JUEGO ---");
        System.out.println("1. Cada equipo comienza con 200 vidas y 50 misiles por ronda.");
        System.out.println("2. Cada equipo elige un tipo de planeta.");
        System.out.println("3. CABALLERO: al defenderse recupera el doble.");
        System.out.println("4. ASESINO: al atacar hace el doble de daño.");
        System.out.println("5. TANQUE: tiene el doble de vida y recibe la mitad de daño.");
        System.out.println("6. MAGO: ignora la reducción de daño del tanque.");
        System.out.println("7. TIRADOR: hace 1.5x de daño.");
        System.out.println("8. LADRON: tiene la mitad de vida y 50% de probabilidad de esquivar.");
        System.out.println("9. El último equipo en pie gana la partida.\n");
    }

    public static void mostrarInformacion() {
        System.out.println("\n--- INFORMACIÓN ---");
        System.out.println("Versión: 1.2b");
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
                    System.out.println("Jinx - Explosiva y caótica tiradora de apoyo a distancia.");
                    break;
                case 2:
                    System.out.println("Jhin - Artista asesino con cuatro disparos explosivos.");
                    break;
                case 3:
                    System.out.println("Gangplank - Pirata francotirador con mucho poder de área.");
                    break;
                case 4:
                    System.out.println("Rumble - Yordle mecánico con un dron de combate eléctrico.");
                    break;
                case 5:
                    System.out.println("Heimerdinger - Yordle científico prototipo que usa campos y torretas.");
                    break;
                case 6:
                    System.out.println("Kog'Maw - Devorador de estrellas con gran alcance.");
                    break;
                case 7:
                    System.out.println("Aurelion Sol - Dragón cósmico que controla el espacio.");
                    break;
                case 8:
                    System.out.println("Sylas - Mago rebelde que roba habilidades enemigas.");
                    break;
                case 9:
                    System.out.println("Tristana - Yordle artillera con salto y explosión.");
                    break;
                case 10:
                    System.out.println("Xerath - Mago de largo alcance con poder masivo.");
                    break;
                case 11:
                    System.out.println("Ziggs - Yordle ingeniero explosivo especializado en zonas.");
                    break;
                case 12:
                    System.out.println("Corki - Piloto de combate con misiles y bombas.");
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