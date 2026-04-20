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
                mostrarPersonajes();
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
     * Método que anuncia qué equipo ha ganado la partida.
     *
     * @param equipos Array con todos los personajes de la partida.
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

                    System.out.println("ERROR DE ESRITURA");
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

                System.out.println("ERROR DE ESRITURA");
            }

            lecturaArchivo();
        }
    }

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

    /**
     *
     * */
    public static void mostrarReglas() {
        System.out.println("\n--- REGLAS DEL JUEGO ---\n" +
                "1. La partida se juega con un mínimo de 3 equipos.\n" +
                "2. Cada equipo comienza con las vidas y los misiles por ronda determinados por su personaje.\n" +
                "3. Cada personaje tiene estadísticas base propias.\n" +
                "4. Los YORDLES tienen un 25% de probabilidad de esquivar ataques.\n" +
                "5. Los HUMANOS hacen 1,25x de daño a los YORDLES.\n" +
                "6. Los SERES MAGICOS hacen 1,75x de daño a HUMANOS y 0,75x a YORDLES.\n" +
                "7. En la primera ronda solo se puede atacar.\n" +
                "8. El daño se aplica al final de la ronda, después de que todos jueguen.\n" +
                "9. A partir de la ronda 2 puedes atacar o defenderte.\n" +
                "10. Primero eliges a quién atacas y luego cuántos misiles usas.\n" +
                "11. Al final de cada ronda se muestra un resumen.\n" +
                "12. El último equipo en pie gana la partida.\n");
    }

    /**
     *
     * */
    public static void mostrarInformacion() {
        System.out.println("\n--- INFORMACIÓN GENERAL ---\n" +
                "Versión: 2.1\n" +
                "Contacto: losdelfondo@gmail.com\n" +
                "Autores: Adrián Vera, Agustín Olivera, Ángel Brito, Mauro Barros, Sergio Rial\n");
    }

    /**
     *
     * */
    public static void infoPersonajes(Scanner sc) {
        int personaje = -1;

        do {
            mostrarPersonajes();

            do{
                System.out.print("Escoge el número del personaje: ");
                try {
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
            }while(personaje < 0 || personaje > 12);


            switch (personaje) {
                case 1:
                    System.out.println("\nJinx - HUMANO | Vidas: 180 | Misiles: 55\n" +
                            "Supermegacohete Requetemortal!: al matar gana +10 misiles permanentes.\n");
                    break;
                case 2:
                    System.out.println("\nJhin - HUMANO | Vidas: 170 | Misiles: 60\n" +
                            "Uno,Dos,Tres...CUATRO!: cada 4 ataques hace daño crítico x2.\n");
                    break;
                case 3:
                    System.out.println("\nGangplank - HUMANO | Vidas: 210 | Misiles: 45\n" +
                            "Vitamina C: su curación es un 75% en vez de 50%.\n");
                    break;
                case 4:
                    System.out.println("\nRumble - YORDLE | Vidas: 160 | Misiles: 50\n" +
                            "Mechero: aplica quemadura (5 de daño por ronda al enemigo).\n");
                    break;
                case 5:
                    System.out.println("\nHeimerdinger - YORDLE | Vidas: 150 | Misiles: 55\n" +
                            "Eureka!: cada 2 rondas coloca una torreta que hace 10 de daño global.\n");
                    break;
                case 6:
                    System.out.println("\nKog'Maw - SER MAGICO | Vidas: 170 | Misiles: 60\n" +
                            "Avanzamos sin descanso!: al morir hace 20 de daño al atacante.\n");
                    break;
                case 7:
                    System.out.println("\nAurelion Sol - SER MAGICO | Vidas: 220 | Misiles: 45\n" +
                            "Experiencia Galactica: gana +5 de daño permanente cada turno.\n");
                    break;
                case 8:
                    System.out.println("\nSylas - HUMANO | Vidas: 190 | Misiles: 50\n" +
                            "Matarreyes: roba la habilidad de un enemigo y la activa cada 2 turnos.\n");
                    break;
                case 9:
                    System.out.println("\nTristana - YORDLE | Vidas: 155 | Misiles: 60\n" +
                            "Sanguinaria: al matar se cura 15 de vida.\n");
                    break;
                case 10:
                    System.out.println("\nXerath - SER MAGICO | Vidas: 160 | Misiles: 70\n" +
                            "Reloj de arena de Zhonya: si baja de 50 de vida, bloquea el siguiente ataque.\n");
                    break;
                case 11:
                    System.out.println("\nZiggs - YORDLE | Vidas: 145 | Misiles: 65\n" +
                            "Megabomba incendiaria: cada 4 turnos lanza un misil que hace 75 daño + 10 en área.\n");
                    break;
                case 12:
                    System.out.println("\nCorki - YORDLE | Vidas: 165 | Misiles: 55\n" +
                            "Bombas fuera!: cada 4 turnos lanza un misil que hace 75 daño y 10 en área.\n");
                    break;
            }
        } while (personaje != 0);
    }

    /**
     *
     * */
    public static void mostrarPersonajes() {
        System.out.print("\n---- PERSONAJES ----\n" +
                "1-  Jinx\n" +
                "2-  Jhin\n" +
                "3-  Gangplank\n" +
                "4-  Rumble\n" +
                "5-  Heimerdinger\n" +
                "6-  Kog'Maw\n" +
                "7-  Aurelion Sol\n" +
                "8-  Sylas\n" +
                "9-  Tristana\n" +
                "10- Xerath\n" +
                "11- Ziggs\n" +
                "12- Corki\n" +
                "0- Salir\n");
    }
}