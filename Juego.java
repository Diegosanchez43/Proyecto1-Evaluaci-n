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
    
    public static void main(String[] args) {   
        System.out.println("Vienvenido jugador\n" +
                            "1:Jugar\n" +
                            "2:salir"
        );
        
        boolean valido = false;
        do {
            int menu = sc.nextInt();
            switch (menu) {
                case 1:
                    Personajes jugador = eleccionPersonaje();
                    primerPaso(jugador);
                    segunoPaso(cuchillo, pocion, jugador);

                    sc.close();
                    valido = true;
                    break;
                case 2:
                    System.out.println("Hasta la proxima");
                    valido = true;
                    break;
                case 3:
                    System.out.println("Reglas:\n" +
                                        "Elecciones basadas en texto y numeros depende de la ocasion\n" 

                    );
                default:
                    break;
            }
        
        } while (valido); {
            System.out.println("opcion no valida");
        }
    }    

    static public String nombre(){
       // Scanner sc = new Scanner(System.in);
            System.out.println("Introduce Nombre");
            String nombre = sc.nextLine();
           // sc.close();    
            return nombre;
    }

    public static void generarMain() {
    
    }
        

    static public Personajes creacionGuerrero(){
        String nombre = nombre();
        Personajes jugador = new Personajes(nombre, 12, 10);
        jugador.mostrarDatos();
        generarInventario(jugador);
        return jugador;
    }

    static public Personajes creacionMago(){
        String nombre = nombre();
        Personajes jugador = new Personajes(nombre, 18, 5);
        jugador.mostrarDatos();
        generarInventario(jugador);
        return jugador;
    }

    static public Personajes creacionVagabundo(){
        String nombre = nombre();
        Personajes jugador = new Personajes(nombre, 10, 2);
        jugador.mostrarDatos();
        generarInventario(jugador);
        return jugador;
    }

    static public Objetos generarPocion(){
         Objetos pocion = new Objetos("Pocion", 0, "Cura 10 de vida", 10);
         return pocion;
    }

    static public Objetos generarPocionMediana(){
         Objetos pocionMediana = new Objetos("Pocion mediana", 0, "Cura 30 de vida", 30);
         return pocionMediana;
    }

    static public Objetos generarPocionGrande(){
        Objetos pocionGrande = new Objetos("Pocion grande", 0, "Cura 60 de vida", 60);
        return pocionGrande;
    }

    static public Objetos generarCuchillo(){
        Objetos cuchillo = new Objetos("Cuchillo arrojadizo", "Lnzalo para hacer 25 de daño", 0, 25);
        return cuchillo;
    }

    static public Objetos generarBomba(){
        Objetos bomba = new Objetos ("Bomba", "Lanzala para causar 60 de daño ", 0, 60);
        return bomba;
    }

    static public void generarInventario(Personajes jugador){       
        jugador.añadirObjeto(pocion);       
        jugador.añadirObjeto(pocionMediana);       
        jugador.añadirObjeto(pocionGrande);        
        jugador.añadirObjeto(cuchillo);
        jugador.añadirObjeto(bomba);
    }

    static public Personajes eleccionPersonaje(){
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
                default:
                    System.out.println("No es valido");
                    break;
            }
        //sc.close();

        } while (!valido);
        return jugador;
        
    }

    static public Enemigos generarSlime(){
        Enemigos enemigo = new Enemigos("Slime", 40, 9, 0, 5);
        return enemigo;
    }

    static public Enemigos generarEsqueleto(){
        Enemigos enemigo = new Enemigos("Esqueleto", 60, 16, 4, 13);
        return enemigo;
    }

    static public Enemigos generarNoMuerto(){
        Enemigos enemigo = new Enemigos("No Muerto", 80, 23, 7, 23);
        return enemigo;
    }

    static public void combate(Personajes jugador, Enemigos enemigo){
        //Scanner sc = new Scanner(System.in);

        System.out.println("Empieza el combate con " + enemigo.getNombre() + "!");
        
        while(jugador.getVida() > 0 && enemigo.getVida() > 0){
            jugador.mostrarVida();
            enemigo.mostrarVida();
            System.out.println("Que vas a hacer " + jugador.getNombre() + "?");
            System.out.println("1. Atacar");
            System.out.println("2 usar objeto");

            int opcion = sc.nextInt();
           // sc.nextLine();

            switch (opcion) {
                case 1:
                    int vidaAnterior = enemigo.getVida();
                    enemigo.recibirDanyo(jugador.getAtaque());
                    System.out.println("Haces " + (vidaAnterior - enemigo.getVida()) + " de daño.");
                    break;
                
                case 2:
                    usarInventario(jugador, enemigo);
                    break;

                default:
                    break;

                    
            }
            //sc.close();
            if (enemigo.getVida() > 0 ) {
                int danyo = enemigo.getAtaque() - jugador.getDefensa();
                if (danyo < 0 ) {
                    danyo = 0;
                }
                jugador.recibirDanyo(enemigo.getAtaque());
                System.out.println(enemigo.getNombre() + " te ataca y te hace " + (danyo) + " de daño");
            }
            
        } 
    
        if(jugador.getVida() > 0) {
            System.out.println("El enemigo ha sido derrotado");
        } else {
            System.out.println("Has muerto ");
        }
    }

    static public void primerPaso(Personajes jugador) {
        
        System.out.println("Despiertas en una fría celda de piedra, sin recordar cómo has llegado allí. A tu alrededor solo hay barrotes oxidados, humedad y un silencio inquietante. Tras explorar un poco, descubres que la puerta está abierta. Sin pensarlo demasiado, sales de la prisión y recorres sus oscuros pasillos hasta encontrar la salida.\n" + 
                        "Al cruzar la puerta principal, un mundo completamente desconocido se extiende ante tus ojos.\n" +                        
                        "Te encuentras en un reino medieval llamado Eldoria, una tierra de castillos, aldeas, bosques encantados y peligrosas mazmorras. La magia existe y criaturas fantásticas habitan cada rincón del continente. Caballeros, mercaderes, aventureros y monstruos forman parte de un mundo donde la supervivencia no siempre está garantizada.\n" +
                        "Sin armas, dinero ni un destino claro, decides adentrarte en el bosque cercano. Mientras caminas, escuchas un extraño sonido entre los arbustos.\n");  
        pausa(3500);
        System.out.println("Al acercarte, descubres una pequeña criatura gelatinosa de color azul que te observa con dos grandes ojos negros.\n" + 
                        "—¡Plop!\n" +
                        "Es un Slime, una de las criaturas más comunes de estas tierras.");
        pausa(1500);                
        Enemigos enemigo = new Enemigos(null, 0, 0, 0, 0);
        enemigo = generarSlime();
        combate(jugador, enemigo);
        
    }

    static public void segunoPaso(Objetos cuchillo, Objetos pocion, Personajes jugador){
        //Random random = new Random();
        System.out.println("Tras la aplastante derrota contra el slime, ves como deja caer una pocion ");
        pocion.conseguir(1);
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
        sc.nextLine();
        String opcion = sc.nextLine();
        opcion = opcion.toLowerCase();
        
        
        int probabilidad = random.nextInt(0,9);
        switch (opcion) {
            case "si":
                if (probabilidad >= 2 ) {
                    Enemigos enemigo = generarSlime();
                    combate(jugador, enemigo);
                }else{
                    System.out.println("Encuentras un cuchillo ");
                    cuchillo.conseguir(1);
                }
                break;
            case "Si":
                if (probabilidad >= 2 ) {
                    Enemigos enemigo = generarSlime();
                    combate(jugador, enemigo);
                }else{
                    System.out.println("Encuentras un cuchillo ");
                    cuchillo.conseguir(1);
                }
                break;
            default:
                System.out.println("No habres el cofre ");
                break;
        }

        Enemigos enemigo = generarEsqueleto();
        combate(jugador, enemigo);
        
    }

    static public void usarInventario(Personajes jugador, Enemigos enemigo) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Objetos> inventario = jugador.getInventario();

        jugador.mostrarObjetos();
        System.out.println("selecciona 0 para canelar");
        int seleccion = sc.nextInt();

        if (seleccion == 0) {
            return;
        }
        if (seleccion < 1 || seleccion > inventario.size()) {
            System.out.println("Selección no válida. ");
            return;
        }

        Objetos objeto = inventario.get(seleccion - 1);

        if (!objeto.usosTotales()) {
            System.out.println("No te quedan " + objeto.getNombre());
            return;
        }

        if (objeto.getCuracion() > 0) {
            objeto.usarCuracion(jugador);
        }

        if (objeto.getDanyo() > 0) {
            objeto.hacerDanyo(enemigo);
        }

    }

    static void pausa(int milisegundos) {
        try{
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
     }

    

}