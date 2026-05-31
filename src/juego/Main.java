package juego;

import testJuego1_Utils.Validaciones;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import swing.Ventana;

/**
 * Clase principal del juego. Contiene el menú inicial,
 * la creación de equipos y el desarrollo de las rondas.
 * */
public class Main {
    public static Personajes[] equipos;
    public static int numeroPartida = 1;
    public static String[] mensajesPasivas = new String[500];
    public static int numMensajesPasivas = 0;

    /**
     * Punto de entrada del programa. Muestra el menú principal y
     * permite acceder a las distintas opciones del juego.
     *
     * @param args Argumentos de línea de comandos.
     * */
    public static void main(String[] args) {
        new java.io.File("ganadores.txt").deleteOnExit();

        SwingUtilities.invokeLater(() -> {
            new Ventana();
            Sonidos.reproducirMusica("/audio/menu.wav");
        });
    }

    /**
     * Método que pide un identificador válido y único.
     * El formato debe ser de 4 números seguidos de 3 letras mayúsculas,
     * y no puede repetirse dentro del array de equipos.
     *
     * @param equipos Array de personajes ya creados.
     * @return Identificador válido y no repetido.
     */
    public static String pedirIdentificador(Personajes[] equipos) {
        String identificador;
        boolean repetido = true;

        do {
            identificador = JOptionPane.showInputDialog(null,
                    "Introduce el identificador (4 números y 3 letras mayúsculas, ej. 4325EIS):");

            if (identificador == null) {
                return null;
            }

            identificador = identificador.trim();

            if (!Validaciones.validarIdentificador(identificador)) {
                JOptionPane.showMessageDialog(null,
                        "Identificador no válido. Debe tener 4 números seguidos de 3 letras mayúsculas.");
            } else {
                repetido = false;

                for (int i = 0; i < equipos.length; i++) {
                    if (equipos[i] != null && equipos[i].getIdentificador().equals(identificador)) {
                        JOptionPane.showMessageDialog(null,
                                "Ese identificador ya está en uso. Introduce otro distinto.");
                        repetido = true;
                        break;
                    }
                }
            }
        } while (repetido);

        return identificador;
    }

    /**
     * Método que crea un personaje según la opción seleccionada por el usuario.
     *
     * @param opcion Número que corresponde al personaje elegido.
     * @param identificador Identificador del personaje.
     * @return Instancia del personaje seleccionado.
     * */
    public static Personajes crearPersonaje(int opcion, String identificador) {
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
                return crearPersonalizado(identificador);
            case 14:
                int aleatorio = (int) (Math.random() * 12) + 1;
                return crearPersonaje(aleatorio, identificador);
            default:
                return new Jinx(identificador);
        }
    }

    /**
     * Método que crea un personaje personalizado introducido por el usuario.
     *
     * @param identificador Identificador del personaje.
     * @return Instancia de un personaje personalizado.
     * */
    public static Personajes crearPersonalizado(String identificador) {
        String nombre = JOptionPane.showInputDialog(null, "Escribe el nombre del personaje:");

        if (nombre == null || nombre.trim().isEmpty()) {
            nombre = "Personalizado";
        }

        String[] opciones = {"HUMANO", "YORDLE", "SER MAGICO"};
        int raza = JOptionPane.showOptionDialog(null, "Elige la raza del personaje:",
                "Raza", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                opciones, opciones[0]);

        switch (raza) {
            case 0:
                return new HumanoPersonalizado(nombre, identificador, 200, 50);
            case 1:
                return new YordlePersonalizado(nombre, identificador, 200, 50);
            case 2:
                return new SerMagicoPersonalizado(nombre, identificador, 200, 50);
            default:
                return new HumanoPersonalizado(nombre, identificador, 200, 50);
        }
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
     * @return Devuelve el estado de los personajes.
     */
    public static String mostrarEstadoRonda(Personajes[] equipos) {
        String texto = "";

        for (Personajes equipo : equipos) {
            if (equipo.getVidas() > 0) {
                String vidaLabel = (equipo.getVidas() == 1) ? "vida" : "vidas";
                texto += equipo.getNombre() + " [" + equipo.getIdentificador() + "] > "
                        + equipo.getVidas() + " " + vidaLabel
                        + " | Tipo: " + obtenerRaza(equipo)
                        + " | Misiles: " + equipo.getMisilesRonda() + "\n";
            }
        }

        return texto;
    }

    /**
     * Metodo que añade el texto cuando se usa una pasiva.
     *
     * @param mensaje Texto donde te dice que pasiva estas usando.
     */
    public static void agregarMensajePasiva(String mensaje) {
        if (numMensajesPasivas < mensajesPasivas.length) {
            mensajesPasivas[numMensajesPasivas] = mensaje;
            numMensajesPasivas++;
        }
    }

    /**
     * Método que gestiona el turno de un equipo, permitiendo atacar o defenderse.
     *
     * @param equipos Array con todos los personajes de la partida.
     * @param indiceAtacante Índice del personaje que realiza el turno.
     * @param accionSwing Parámetro de texto que indica qué botón pulsaste en la interfaz.
     * @param listaAcciones Array donde se guardan los mensajes de cada acción.
     * @param numAcciones Número actual de acciones guardadas.
     * @param ronda Ronda actual.
     * @return Número actualizado de acciones guardadas.
     * */
    public static int turnoEquipo(Personajes[] equipos, int indiceAtacante, String accionSwing, String[] listaAcciones, int numAcciones, int ronda) {
        Personajes atacante = equipos[indiceAtacante];

        if (atacante == null || atacante.getVidas() <= 0 || atacante.getMisilesRonda() <= 0) {
            return numAcciones;
        }

        atacante.pasivaRonda(ronda);

        if (accionSwing == null) {
            return numAcciones;
        }

        accionSwing = accionSwing.trim().toUpperCase();

        if (ronda == 1 && accionSwing.equals("DEFENDER")) {
            JOptionPane.showMessageDialog(null, "En la primera ronda no se puede defender.");
            return numAcciones;
        }

        if (accionSwing.equals("TERMINAR")) {
            return numAcciones;
        }

        if (accionSwing.equals("DEFENDER")) {
            String entrada = JOptionPane.showInputDialog(
                    null,
                    "Misiles disponibles: " + atacante.getMisilesRonda() +
                            "\n¿Cuántos misiles quieres usar para defenderte?",
                    "Defender",
                    JOptionPane.QUESTION_MESSAGE
            );

            if (entrada == null) {
                return numAcciones;
            }

            int misilesDefensa;

            try {
                misilesDefensa = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Has introducido un valor no válido.");
                return numAcciones;
            }

            if (misilesDefensa <= 0 || misilesDefensa > atacante.getMisilesRonda()) {
                JOptionPane.showMessageDialog(null, "Usa entre 1 y " + atacante.getMisilesRonda() + " misiles.");
                return numAcciones;
            }

            atacante.setMisilesRonda(atacante.getMisilesRonda() - misilesDefensa);
            int defensaTotal = atacante.accion("defender", misilesDefensa, atacante);

            String mensaje = atacante.getNombre() + " [" + atacante.getIdentificador() + "] se defiende con "
                    + misilesDefensa + " misiles y recupera " + defensaTotal + " vidas.";
            listaAcciones[numAcciones] = mensaje;
            numAcciones++;

            return numAcciones;
        }

        if (accionSwing.equals("ATACAR")) {
            String entrada = JOptionPane.showInputDialog(
                    null,
                    "Misiles disponibles: " + atacante.getMisilesRonda() +
                            "\n¿Cuántos misiles quieres usar para atacar?",
                    "Atacar",
                    JOptionPane.QUESTION_MESSAGE
            );

            if (entrada == null) {
                return numAcciones;
            }

            int misiles;

            try {
                misiles = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Has introducido un valor no válido.");
                return numAcciones;
            }

            if (misiles <= 0 || misiles > atacante.getMisilesRonda()) {
                JOptionPane.showMessageDialog(null, "Usa entre 1 y " + atacante.getMisilesRonda() + " misiles.");
                return numAcciones;
            }

            int[] objetivosValidos = new int[equipos.length];
            String[] nombresObjetivos = new String[equipos.length];
            int totalObjetivos = 0;

            for (int j = 0; j < equipos.length; j++) {
                if (j != indiceAtacante && equipos[j] != null && equipos[j].getVidas() > 0) {
                    objetivosValidos[totalObjetivos] = j;
                    nombresObjetivos[totalObjetivos] = equipos[j].getNombre() + " [" + equipos[j].getIdentificador() + "] ("
                            + obtenerRaza(equipos[j]) + ")";
                    totalObjetivos++;
                }
            }

            if (totalObjetivos == 0) {
                JOptionPane.showMessageDialog(null, "No hay objetivos disponibles.");
                return numAcciones;
            }

            String[] opcionesFinales = new String[totalObjetivos];
            for (int i = 0; i < totalObjetivos; i++) {
                opcionesFinales[i] = nombresObjetivos[i];
            }

            Object seleccion = JOptionPane.showInputDialog(
                    null,
                    "¿A quién quieres atacar?",
                    "Seleccionar objetivo",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opcionesFinales,
                    opcionesFinales[0]
            );

            if (seleccion == null) {
                return numAcciones;
            }

            int indiceObjetivoReal = -1;
            for (int i = 0; i < totalObjetivos; i++) {
                if (opcionesFinales[i].equals(seleccion.toString())) {
                    indiceObjetivoReal = objetivosValidos[i];
                    break;
                }
            }

            if (indiceObjetivoReal == -1) {
                JOptionPane.showMessageDialog(null, "Objetivo no válido.");
                return numAcciones;
            }

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

            if (!equipos[indiceObjetivoReal].getMensajeEsquiva().isEmpty()) {
                listaAcciones[numAcciones] = equipos[indiceObjetivoReal].getMensajeEsquiva();
                numAcciones++;
                equipos[indiceObjetivoReal].setMensajeEsquiva("");
            }

            return numAcciones;
        }

        return numAcciones;
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