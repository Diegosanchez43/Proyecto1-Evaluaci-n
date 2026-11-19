import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class Juego {
    static Scanner sc = new Scanner(System.in);
    static Random random = new Random();
    static Objetos pocion = generarPocion();        
    static Objetos pocionMediana = generarPocionMediana();        
    static Objetos pocionGrande = generarPocionGrande();       
    static Objetos cuchillo = generarCuchillo();       
    static Objetos bomba = generarBomba();  
    static int puntosPartida;
    static int nivelSubidos;
    

    
    public static int getPuntosPartida() {
        return puntosPartida;
    }

    public static void setPuntosPartida(int puntosPartida) {
        Juego.puntosPartida = puntosPartida;
    }

    public static int getNivelSubidos() {
        return nivelSubidos;
    }

    public static void setNivelSubidos(int nivelSubidos) {
        Juego.nivelSubidos = nivelSubidos;
    }
    

    public static void main(String[] args) {
        hacerXML.crearXML();
        System.out.println("====================================");
        System.out.println("       AVENTURA EN ELDORIA");
        System.out.println("====================================");
        pausa(700);
        System.out.println("Bienvenido, jugador");
        System.out.println();
        System.out.println("  1. Jugar");
        System.out.println("  2. Salir");
        System.out.println("  3. Leer las normas");
        System.out.println("  4. ver ranking");
        System.out.println();
        System.out.print("Elige una opcion: ");
        
        boolean valido = false;
        do {
            int menu = sc.nextInt();
            switch (menu) {
                case 1:
                    puntosPartida = 0;
                    System.out.println("\nPreparando tu aventura...");
                    pausa(1000);
                    Personajes jugador = eleccionPersonaje();
                    pausa(1000);
                    primerPaso(jugador);
                    pausa(1200);
                    segunoPaso(cuchillo, pocion, jugador);
                    pausa(1200);
                    tercerPaso(jugador, bomba);
                    pausa(1200);
                    cuartoPaso(jugador, pocionMediana, pocionGrande);
                    pausa(1200);
                    quintoPaso(jugador);
                    pausa(1200);
                    fin(jugador);
                    hacerXML.escribirXML(jugador.getNombre(), puntosPartida);
                    

                    sc.close();
                    valido = true;
                    break;
                case 2:
                    System.out.println("\n====================================");
                    System.out.println("Gracias por jugar. Hasta la proxima!");
                    System.out.println("====================================");
                    valido = true;
                    System.exit(0);
                    break;
                case 3:
                    System.out.println("\n------------- NORMAS --------------");
                    System.out.println("1. Sigue las instrucciones y elige usando texto o numeros.");
                    System.out.println("2. Los combates se desarrollan por turnos.");
                    System.out.println("3. Puedes usar objetos durante los combates.");
                    System.out.println("4. Elige un personaje y sobrevive a los peligros de Eldoria.");
                    System.out.println("-----------------------------------\n");
                    pausa(700);
                    valido = true;
                    break;
                case 4:
                    hacerXML.guardarXML();
                    valido = true;
                default:
                    break;
            }
        
        } while (valido); {
            System.out.println("opcion no valida");
            return;
        }
    }    

    public static String nombre(){
       // Scanner sc = new Scanner(System.in);
            System.out.println("Introduce Nombre");
            String nombre = sc.nextLine();
           // sc.close();    
            return nombre;
    }
        

    public static Personajes creacionGuerrero(){
        String nombre = nombre();
        Personajes jugador = new Personajes(nombre, 12, 10);
        jugador.mostrarDatos();
        generarInventario(jugador);
        return jugador;
    }

    public static Personajes creacionMago(){
        String nombre = nombre();
        Personajes jugador = new Personajes(nombre, 18, 5);
        jugador.mostrarDatos();
        generarInventario(jugador);
        return jugador;
    }

    public static Personajes creacionVagabundo(){
        String nombre = nombre();
        Personajes jugador = new Personajes(nombre, 10, 2);
        jugador.mostrarDatos();
        generarInventario(jugador);
        return jugador;
    }

    public static Personajes creacionDemonio(){
        System.out.println("En las profundidades del Infierno, donde las llamas nunca se apagan y los gritos resuenan eternamente, una antigua puerta comienza a abrirse. De entre las sombras emerge una figura demoníaca, con enormes cuernos, ojos carmesí y un aura que desprende un poder aterrador.\n");
        pausa(1000);
        System.out.println("Tras siglos de encierro, finalmente has conseguido escapar.\n");
        pausa(1000);
        System.out.println("Ya no eres un humano, ni una criatura mortal. Tu cuerpo ha cambiado, tu alma está consumida por el fuego infernal y un poder desconocido recorre cada parte de tu ser.\n");
        pausa(1000);
        System.out.println("Ahora eres un demonio.\n");
        pausa(1000);
        System.out.println("El mundo de los mortales se extiende ante ti. Y esta vez, no has venido a obedecer órdenes.");
        String nombre = nombre();
        Personajes jugador = new Personajes(nombre, 100, 100);
        jugador.mostrarDatos();
        generarInventario(jugador);
        return jugador;
    } 

    public static Objetos generarPocion(){
         Objetos pocion = new Objetos("Pocion", 0, "Cura 10 de vida", 20);
         return pocion;
    }

    public static Objetos generarPocionMediana(){
         Objetos pocionMediana = new Objetos("Pocion mediana", 0, "Cura 30 de vida", 40);
         return pocionMediana;
    }

    public static Objetos generarPocionGrande(){
        Objetos pocionGrande = new Objetos("Pocion grande", 0, "Cura 60 de vida", 90);
        return pocionGrande;
    }

    public static Objetos generarCuchillo(){
        Objetos cuchillo = new Objetos("Cuchillo arrojadizo", "Lnzalo para hacer 25 de daño", 0, 25);
        return cuchillo;
    }

    public static Objetos generarBomba(){
        Objetos bomba = new Objetos ("Bomba", "Lanzala para causar 60 de daño ", 0, 60);
        return bomba;
    }

    public static void generarInventario(Personajes jugador){       
        jugador.añadirObjeto(pocion);       
        jugador.añadirObjeto(pocionMediana);       
        jugador.añadirObjeto(pocionGrande);        
        jugador.añadirObjeto(cuchillo);
        jugador.añadirObjeto(bomba);
    }

    public static Personajes eleccionPersonaje(){
       // Scanner sc = new Scanner(System.in);
        Personajes jugador = new Personajes(null, 0, 0);
        System.out.println("Selecciona el personaje: Guerrero-1, Mago-2, Vagabundo-3");
        boolean valido = false;
        do {
            int clase = sc.nextInt();
            
            switch (clase) {
                case 1:
                    valido = true;
                    jugador = creacionGuerrero(); 
                    break;                   
                case 2:
                    valido = true;
                    jugador = creacionMago();
                    break;
                case 3:
                    valido = true;
                    jugador = creacionVagabundo();
                    break;
                case 666:
                    valido = true;
                    jugador = creacionDemonio();
                    break;
                default:
                    System.out.println("No es valido");
                    break;
            }
        //sc.close();

        } while (!valido);
        return jugador;
        
    }

    public static Enemigos generarSlime(){
        Enemigos enemigo = new Enemigos("Slime", 40, 9, 0, 5);
        return enemigo;
    }

    public static Enemigos generarEsqueleto(){
        Enemigos enemigo = new Enemigos("Esqueleto", 75, 16, 4, 13);
        return enemigo;
    }

    public static Enemigos generarNoMuerto(){
        Enemigos enemigo = new Enemigos("No Muerto", 120, 23, 7, 23);
        return enemigo;
    }

    public static int combate(Personajes jugador, Enemigos enemigo){
        //Scanner sc = new Scanner(System.in);
        int experiencia;

        System.out.println("\n====================================");
        System.out.println("       COMBATE: " + enemigo.getNombre().toUpperCase());
        System.out.println("====================================");
        pausa(900);
        
        while(jugador.getVida() > 0 && enemigo.getVida() > 0){
            System.out.println("\n------------- ESTADO --------------");
            jugador.mostrarVida();
            enemigo.mostrarVida();
            pausa(500);
            System.out.println("\nTurno de " + jugador.getNombre());
            System.out.println("  1. Atacar");
            System.out.println("  2. Usar un objeto");
            System.out.print("Elige una opcion: ");

            int opcion = sc.nextInt();
           // sc.nextLine();

            boolean turnoConsumido = false;
            switch (opcion) {
                case 1:
                    int vidaAnterior = enemigo.getVida();
                    enemigo.recibirDanyo(jugador.getAtaque());
                    System.out.println("\n>> Atacas a " + enemigo.getNombre() + " y le haces "
                            + (vidaAnterior - enemigo.getVida()) + " de daño.");
                    pausa(800);
                    turnoConsumido = true;
                    break;
                
                case 2:
                    turnoConsumido = usarInventario(jugador, enemigo);
                    if (turnoConsumido) {
                        System.out.println("\n>> Has usado un objeto.");
                        pausa(800);
                    }
                    break;

                default:
                    System.out.println("\nOpcion no valida. Elige 1 o 2.");
                    break;

                    
            }
            //sc.close();
            if (turnoConsumido == true && enemigo.getVida() > 0 ) {
                System.out.println("\nTurno de " + enemigo.getNombre() + "...");
                pausa(700);
                int danyo = enemigo.getAtaque() - jugador.getDefensa();
                if (danyo < 0 ) {
                    danyo = 0;
                }
                jugador.recibirDanyo(enemigo.getAtaque());
                System.out.println("<< " + enemigo.getNombre() + " te ataca y te hace "
                        + danyo + " de daño.");
                pausa(900);
            }
            
        } 
    
        if(jugador.getVida() > 0) {
            System.out.println("\n====================================");
            System.out.println("      " + enemigo.getNombre().toUpperCase() + " DERROTADO");
            System.out.println("consiges " + enemigo.getExperiencia() + " Experiencia");
            System.out.println("====================================");
            
            
            pausa(1000);
        } else {
            System.out.println("\n====================================");
            System.out.println("             HAS MUERTO");
            System.out.println("====================================");
            hacerXML.escribirXML(jugador.getNombre(), puntosPartida);
            System.exit(0);
        }
        experiencia =+ enemigo.getExperiencia();
        puntosPartida = experiencia;
        return experiencia;
        
    }

    public static void primerPaso(Personajes jugador) {
        
        System.out.println("Despiertas en una fría celda de piedra, sin recordar cómo has llegado allí. A tu alrededor solo hay barrotes oxidados, humedad y un silencio inquietante. Tras explorar un poco, descubres que la puerta está abierta. Sin pensarlo demasiado, sales de la prisión y recorres sus oscuros pasillos hasta encontrar la salida.\n" + 
                        "Al cruzar la puerta principal, un mundo completamente desconocido se extiende ante tus ojos.\n" +                        
                        "Te encuentras en un reino medieval llamado Eldoria, una tierra de castillos, aldeas, bosques encantados y peligrosas mazmorras. La magia existe y criaturas fantásticas habitan cada rincón del continente. Caballeros, mercaderes, aventureros y monstruos forman parte de un mundo donde la supervivencia no siempre está garantizada.\n" +
                        "Sin armas, dinero ni un destino claro, decides adentrarte en el bosque cercano. Mientras caminas, escuchas un extraño sonido entre los arbustos.\n");  
        pausa(3500);
        System.out.println("Al acercarte, descubres una pequeña criatura gelatinosa de color azul que te observa con dos grandes ojos negros.\n" + 
                        "—¡Plop!\n" +
                        "Es un Slime, una de las criaturas más comunes de estas tierras.");
        pausa(1500);
        System.out.println("      _______");
        System.out.println("    /         \\");
        System.out.println("   /  o     o  \\");
        System.out.println("  |      ^      |");
        System.out.println("  |             |");
        System.out.println("   \\___________/");
        System.out.println("    ~~~~~~~~~~~");                
        Enemigos enemigo = new Enemigos(null, 0, 0, 0, 0);
        enemigo = generarSlime();
        nivel(jugador, combate(jugador, enemigo));
        
    }

    public static void segunoPaso(Objetos cuchillo, Objetos pocion, Personajes jugador){
        //Random random = new Random();
        System.out.println("Tras la aplastante derrota contra el slime, ves como deja caer una pocion ");
        pausa(1000);
        pocion.conseguir(1);
        pausa(700);
        System.out.println("El pequeño Slime comienza a seguirte mientras avanzas por el bosque. Tras unos minutos caminando, llegas a un pequeño claro donde los rayos del sol iluminan el suelo. Entre unas raíces y arbustos descubres algo que llama tu atención: un antiguo cofre de madera, cubierto de musgo y con detalles metálicos oxidados.\n" + //
                        "\n" + //
                        "Te acercas para examinarlo. Parece llevar mucho tiempo abandonado y no tiene ningún candado visible. Podría contener objetos valiosos, armas o incluso algún tesoro olvidado. Sin embargo, tampoco puedes descartar que esconda alguna trampa.\n" + //
                        "\n" + //
                        "El Slime se acerca dando pequeños saltos y se queda mirando el cofre.\n" + //
                        "\n" + //
                        "¿Decides abrirlo o prefieres dejarlo donde está?\n" +//
                        "\n" + //
                        "Habrirlo = si\n" + //
                        "Dejarlo = no\n");
        pausa(700);
        sc.nextLine();
        String opcion = sc.nextLine();
        opcion = opcion.toLowerCase();
        
        
        int probabilidad = random.nextInt(0,9);
        switch (opcion) {
            case "si":
                if (probabilidad >= 2 ) {
                    System.out.println("DEL COFRE SALE UN SLIME !");
                    pausa(700);
                    System.out.println("      _______");
                    System.out.println("    /         \\");
                    System.out.println("   /  o     o  \\");
                    System.out.println("  |      ^      |");
                    System.out.println("  |             |");
                    System.out.println("   \\___________/");
                    System.out.println("    ~~~~~~~~~~~");
                    Enemigos enemigo = generarSlime();
                    nivel(jugador, combate(jugador, enemigo));
                }else{
                    System.out.println("Encuentras un cuchillo ");
                    pausa(700);
                    cuchillo.conseguir(1);
                }
                break;
            default:
                System.out.println("No abres el cofre ");
                pausa(700);
                break;
        }

       
    }

    public static void tercerPaso(Personajes jugador, Objetos bomba){
        System.out.println("Te acercas al cofre para examinarlo con más detalle. Su madera está desgastada por el paso del tiempo y algunos símbolos extraños decoran su tapa. Mientras decides qué hacer, escuchas un crujido justo detrás de él.\n" + //
                        "\n" + //
                        "De repente, unos huesos comienzan a levantarse del suelo hasta formar una figura humanoide. ¡Un esqueleto! La criatura sostiene una espada oxidada y sus cuencas vacías brillan con una tenue luz roja.\n" + //
                        "\n" + //
                        "Sin darte tiempo a reaccionar, el esqueleto se coloca frente a ti, bloqueando el camino. El pequeño Slime retrocede asustado mientras la criatura levanta su espada.\n" + //
                        "\n" + //
                        "¡Un enemigo ha aparecido! ¡Prepárate para combatir!");
        pausa(1000);
        System.out.println("       .-'''''''-.");
        System.out.println("     .'           '.");
        System.out.println("    /               \\");
        System.out.println("   |   .---. .---.  |");
        System.out.println("   |  /     V     \\ |");
        System.out.println("   |  \\           / |");
        System.out.println("   |    --/   \\--   |");
        System.out.println("   |        ^       |");
        System.out.println("   |       / \\      |");
        System.out.println("    \\              /");
        System.out.println("     '.          .'");
        System.out.println("       |         |");
        System.out.println("       | | | | | |");
        System.out.println("       | | | | | |");
        System.out.println("       |_|_|_|_|_|");
        pausa(700);
                Enemigos enemigo = generarEsqueleto();
        nivel(jugador, combate(jugador, enemigo));
                System.out.println("Al acabar con el esqueleto ves como deja caer una bomba");
                pausa(800);
                bomba.conseguir(1);
    }

    public static void cuartoPaso(Personajes jugador, Objetos pocionMEdiana, Objetos pocionGrande){
        Enemigos enemigo;
        boolean validacion = false;
        System.out.println("Tras continuar tu camino por el bosque, llegas a una bifurcación con dos senderos diferentes. El de la izquierda parece adentrarse en una zona oscura y frondosa, mientras que el de la derecha conduce hacia unas antiguas ruinas de piedra.\n" + //
                        "\n" + //
                        "De repente, sientes una extraña presencia que parece provenir de más adelante. No sabes qué es, pero tienes la sensación de que algo poderoso se esconde en algún lugar del bosque.\n" + //
                        "\n" + //
                        "El ambiente se vuelve cada vez más inquietante y ambos caminos parecen guardar sus propios secretos.\n" + //
                        "\n" + //
                        "¿Qué camino decides tomar: izquierda o derecha?");
        pausa(800);
                 
            do {
                String decision = sc.nextLine();
                decision = decision.toLowerCase();    
                switch (decision) {
                    case "izquierda":
                        System.out.println("En el camino te encuentras una pocion mediana y un cuchillo arrojaizo,\n" + 
                        "probablemente los recursos de quien ya se intento enfrentar a la presencia antes");
                        pausa(700);
                        pocionMEdiana.conseguir(1);
                        System.out.println("aparece un slime ");
                        pausa(700);
                        System.out.println("      _______");
                        System.out.println("    /         \\");
                        System.out.println("   /  o     o  \\");
                        System.out.println("  |      ^      |");
                        System.out.println("  |             |");
                        System.out.println("   \\___________/");
                        System.out.println("    ~~~~~~~~~~~");
                        enemigo = generarSlime();

                        nivel(jugador, combate(jugador, enemigo));
                        validacion = true;
                                        break;

                        case "derecha":
                                        
                        System.out.println("En el camino te encuentras una pocion grande\n" + 
                        "probablemente los recursos de quien ya se intento enfrentar a la presencia antes");
                        pausa(700);
                        pocionGrande.conseguir(1);
                        System.out.println("te encuentras un esqueleto");
                        pausa(700);
                         pausa(1000);
                        System.out.println("       .-'''''''-.");
                        System.out.println("     .'           '.");
                        System.out.println("    /               \\");
                        System.out.println("   |   .---. .---.  |");
                        System.out.println("   |  /     V     \\ |");
                        System.out.println("   |  \\           / |");
                        System.out.println("   |    --/   \\--   |");
                        System.out.println("   |        ^       |");
                        System.out.println("   |       / \\      |");
                        System.out.println("    \\              /");
                        System.out.println("     '.          .'");
                        System.out.println("       |         |");
                        System.out.println("       | | | | | |");
                        System.out.println("       | | | | | |");
                        System.out.println("       |_|_|_|_|_|");
                        enemigo = generarEsqueleto();
                        nivel(jugador, combate(jugador, enemigo));
                        validacion = true;
                        break;
                }
        } while (!validacion); {
            System.out.println("opcion no valida");
            return;
        }
    }

    public static void quintoPaso(Personajes jugador){
        System.out.println("Tras elegir uno de los caminos, te adentras en el bosque, atravesando árboles cada vez más densos hasta llegar a un amplio claro. El silencio es absoluto y una extraña niebla cubre el suelo.\n" + //
                        "\n" + //
                        "En el centro del claro se encuentra una figura humanoide de aspecto aterrador. Su piel está pálida y desgastada, sus ropas parecen pertenecer a otra época y sus ojos desprenden un tenue brillo verdoso.\n" + //
                        "\n" + //
                        "Es un No Muerto.\n" + //
                        "\n" + //
                        "La criatura gira lentamente la cabeza hacia ti y comienza a caminar en tu dirección, arrastrando los pies por la hierba. Parece que llevaba tiempo esperándote.\n" + //
                        "\n" + //
                        "¡Un No Muerto ha aparecido! ¡Prepárate para combatir!");
        pausa(1200);
        System.out.println("       .-'''''''-.");
        System.out.println("     .'           '.");
        System.out.println("    /   _       O   \\");
        System.out.println("   |   ( )   .---.   |");
        System.out.println("   |       .'     '. |");
        System.out.println("   |  O   /  X      \\|");
        System.out.println("   |      \\         /");
        System.out.println("    \\      '.___.' /");
        System.out.println("     '.           .'");
        System.out.println("       |  |   |  |");
        System.out.println("       |  | | |  |");
        System.out.println("       |  | | |  |");
        System.out.println("       '._|_|_|_.'");
        Enemigos enemigo = generarNoMuerto();
        int experiencia = combate(jugador, enemigo);
        nivel(jugador, experiencia);

    }

    public static void fin(Personajes jugador) {
        System.out.println("Tras un intenso combate, consigues derrotar al No Muerto. La criatura cae al suelo y sus huesos se esparcen por todo el claro.\n" + //
                        "\n" + //
                        "—¡JA! ¡Eso ha sido demasiado fácil! —exclamas, levantando los brazos en señal de victoria.\n" + //
                        "\n");
        pausa(1200);
        System.out.println("Sin embargo, justo cuando te dispones a celebrar tu hazaña, escuchas un extraño ruido procedente de tu estómago. Tienes hambre.\n" + //
                        "\n" + //
                        "Decides sacar una de las monedas que encontraste anteriormente para comprar algo de comida... pero al intentar guardarla, se te cae al suelo.\n" + //
                        "\n");
        pausa(1200);
        System.out.println("Te agachas para recogerla, pierdes el equilibrio, das tres vueltas sobre ti mismo y acabas golpeándote la cabeza contra el único árbol que había en todo el maldito claro.\n" + //
                        "\n" + //
                        "Caes al suelo completamente inmóvil.\n" + //
                        "\n");
        pausa(1000);
        System.out.println(
                        "¡HAS MUERTO!\n" + //
                        "\n" + //
                        "Motivo de la muerte: el verdadero jefe final era la gravedad.");
                        jugador.mostrarDatos();
    }
    
    


    public static boolean usarInventario(Personajes jugador, Enemigos enemigo) {
        ArrayList<Objetos> inventario = jugador.getInventario();

        jugador.mostrarObjetos();
        System.out.println("selecciona 0 para canelar");
        int seleccion = sc.nextInt();

        if (seleccion == 0) {
            return false;
        }
        if (seleccion < 1 || seleccion > inventario.size()) {
            System.out.println("Selección no válida. ");
            return false;
        }

        Objetos objeto = inventario.get(seleccion - 1);

        if (!objeto.usosTotales()) {
            System.out.println("No te quedan " + objeto.getNombre());
            return false;
        }

        boolean objetoUsado = false;
        if (objeto.getCuracion() > 0) {
            objeto.usarCuracion(jugador);
            objetoUsado = true;
        }

        if (objeto.getDanyo() > 0) {
            objeto.hacerDanyo(enemigo);
            objetoUsado = true;
        }

        return objetoUsado;
    }

    static void pausa(int milisegundos) {
        try{
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
     }

     public static void nivel(Personajes jugador, int experiencia){
        int experienciaFake = experiencia;
        if (experienciaFake >= 6 && experienciaFake < 13 ){
            experienciaFake = 6;
        }
        if (experienciaFake >= 13 && experienciaFake < 22 ){
            experienciaFake = 13;
        }
        if (experienciaFake >= 22 && experienciaFake < 32 ){
            experienciaFake = 22;
        }
        if (experienciaFake >= 32 ){
            experienciaFake = 32;
        }

        if (experienciaFake == 6 || experienciaFake == 13 || experienciaFake == 22 || experienciaFake == 32) {
            jugador.subirNivel();
        }
     }

      
    

    

}