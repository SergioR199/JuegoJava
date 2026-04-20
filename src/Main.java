import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

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
                    "0º SALIR\n" +
                    "Elige una opción: ");

             do{
                try {            
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
             }while (opcion < 0 || opcion > 4);

            switch (opcion) {
                case 0:
                    System.out.println("Saliendo del juego...");

                    try{
                        new FileWriter ("ganadores.txt").close();
                    }catch(IOException e){
                        System.out.println("ERROR DE BORRADO");
                    }
                    break;

                case 1:
                    System.out.println("Creando equipos...");
                    equipos = new Personajes[pedirNumeroEquipos(sc)];
                    crearEquipos(equipos, sc);
                    jugar(equipos, sc, partida);
                    partida +=1;

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
            }

        } while (opcion != 0);
    }

    /**
     * Método que solicita al usuario el número de equipos que participarán en la partida.
     *
     * @param sc Scanner utilizado para leer lo que escribe el usuario.
     * @return Número de equipos válido mínimo de 3.
     * */
    public static int pedirNumeroEquipos(Scanner sc) {
        int numEquipos = 0;

        do {
            System.out.print("¿Cuántos equipos quieres crear? (mínimo 3): ");
            try {
                numEquipos = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Has introducido un valor no válido. Elige otra opción (mínimo 3).");
                sc.nextLine();
                continue;
            }

            if (numEquipos < 3) {
                System.out.println("Has introducido una opción no existente. Elige otra opción (mínimo 3).");
            }
        } while (numEquipos < 3);

        return numEquipos;
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
            equipos[i] = crearPersonaje(opcionPersonaje, sc);

            System.out.println("Equipo creado: " + equipos[i].getNombre()
                    + " | Tipo: " + equipos[i].getTipo()
                    + " | Vidas: " + equipos[i].getVidas()
                    + " | Misiles/ronda: " + equipos[i].getMisilesRonda());
        }
    }

    /**
     * Método que crea un personaje según la opción seleccionada por el usuario.
     *
     * @param opcion Número que corresponde al personaje elegido.
     * @param sc Scanner utilizado para crear un personaje personalizado si se necesita.
     * @return Instancia del personaje seleccionado.
     * */
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

    /**
     * Método que crea un personaje personalizado introducido por el usuario.
     *
     * @param sc Scanner utilizado para leer el nombre y el tipo del personaje.
     * @return Instancia de un personaje personalizado.
     * */
    public static Personajes crearPersonalizado(Scanner sc) {
        String nombre;
        int tipo;

        System.out.print("Escribe el nombre del personaje: ");
        nombre = sc.nextLine();

        do {
            try {
                System.out.println("Elige el tipo del personaje:\n" +
                        "1- HUMANO\n" +
                        "2- YORDLE\n" +
                        "3- SER MAGICO\n" +
                        "Opción: ");
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
     * */
    public static void jugar(Personajes[] equipos, Scanner sc, int partida) {
        int ronda = 1;
        int turnoGlobal = 0;
        while (equiposVivos(equipos) > 1) {
            for (Personajes p : equipos) {
                if (p.getVidas() > 0) {
                    p.alInicioTurnoGlobal(turnoGlobal);
                }
            }
            turnoGlobal++;
            System.out.println("\n---- " + (ronda++) + "ª RONDA ----");
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
                    System.out.println(e.getNombre() + ": " + e.getVidas()
                            + " vidas | Tipo: " + e.getTipo()
                            + " | Misiles próxima ronda: " + e.getMisilesRonda());
                }
            }
            System.out.println();
        }

        anunciarGanador(equipos, partida);
    }

    /**
     * Método que cuenta cuántos equipos siguen vivos.
     *
     * @param equipos Array con todos los personajes de la partida.
     * @return Número de personajes con vidas mayores que cero.
     * */
    public static int equiposVivos(Personajes[] equipos) {
        int vivos = 0;
        for (Personajes e : equipos) {
            if (e.getVidas() > 0) {
                vivos++;
            }
        }
        return vivos;
    }

    /**
     * Método que muestra por pantalla el estado actual de todos los equipos vivos.
     *
     * @param equipos Array con todos los personajes de la partida.
     * */
    public static void mostrarEstadoRonda(Personajes[] equipos) {
        for (Personajes equipo : equipos) {
            if (equipo.getVidas() > 0) {
                System.out.println(equipo.getNombre() + " > " + equipo.getVidas()
                        + " vidas | Tipo: " + equipo.getTipo()
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
     * @return Número actualizado de acciones guardadas.
     * */
    public static int turnoEquipo(Personajes[] equipos, int indiceAtacante, Scanner sc, String[] listaAcciones,int numAcciones, int ronda) {
        Personajes atacante = equipos[indiceAtacante];
       
        System.out.println("\nTurno de: " + atacante.getNombre() + "\n" +
                "1. Atacar\n" +
                "2. Defenderse");

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
                    System.out.println("Misiles disponibles: " + atacante.getMisilesRonda() + "\n" +
                            "¿Cuántos misiles quieres usar para defenderte? ");
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
                    System.out.println("Misiles disponibles: " + atacante.getMisilesRonda() + "\n" +
                            "¿Cuántos misiles quieres usar? ");
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
            atacante.alAtacar(equipos, indiceAtacante, danioHecho);
            atacante.setMisilesRonda(atacante.getMisilesRonda() - misiles);

            String mensaje = atacante.getNombre() + " (" + atacante.getTipo() + ") ataca a "
                    + equipos[indiceObjetivoReal].getNombre() + " (" + equipos[indiceObjetivoReal].getTipo() + ") con "
                    + misiles + " misiles y causa " + danioHecho + " de daño.";
            listaAcciones[numAcciones] = mensaje;
            numAcciones++;
        }

        return numAcciones;
    }

    /**
     * Método que anuncia qué equipo ha ganado la partida y lo guarda en el archivo "ganadores.txt".
     * Si ningún personaje tiene vidas, se registra un empate.
     *
     * @param equipos Array con todos los personajes de la partida.
     * @param partida Número de la partida que se está registrando.
     * */
    public static void anunciarGanador(Personajes[] equipos, int partida) {
        boolean empate = true;

        for (Personajes equipo : equipos) {
            if (equipo.getVidas() > 0) {

                empate = false;

                try{

                    BufferedWriter guardarGanadores = new BufferedWriter(new FileWriter("ganadores.txt", true));

                    guardarGanadores.write("\tGANADOR DE LA " + partida + "ª PARTIDA");
                    guardarGanadores.newLine();
                    guardarGanadores.write(equipo.getNombre());
                    guardarGanadores.newLine();
                    guardarGanadores.newLine();

                    guardarGanadores.close();

                }catch(IOException e){

                    System.out.println("ERROR DE ESCRITURA");
                }

                lecturaArchivo();

                return;
            }
        }

        if(empate){

            try{

                BufferedWriter guardarGanadores = new BufferedWriter(new FileWriter("ganadores.txt", true));

                guardarGanadores.write("\tGANADOR DE LA " + partida + "ª PARTIDA");
                guardarGanadores.newLine();
                guardarGanadores.write("EMPATE");
                guardarGanadores.newLine();
                guardarGanadores.newLine();

                guardarGanadores.close();

            }catch(IOException e){

                System.out.println("ERROR DE ESCRITURA");
            }

            lecturaArchivo();
        }
    }

    /**
     * Método que lee el contenido de "ganadores.txt" y lo muestra por consola.
     * */
    public static void lecturaArchivo(){

        String linea = "";

        try{

            BufferedReader lecturaGanadores = new BufferedReader(new FileReader("ganadores.txt"));

            while ((linea = lecturaGanadores.readLine()) != null) {

                System.out.println(linea);
            }

            lecturaGanadores.close();

        }catch(IOException e){

            System.out.println("ERROR DE LECTURA");
        }
    }
}