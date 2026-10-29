import java.util.Scanner;

public class Juego {
    public static void main(String[] args) {

        eleccionPersonaje();

    }
    


    static public String nombre(){
        Scanner sc = new Scanner(System.in);
            System.out.println("Introduce Nombre");
            String nombre = sc.nextLine();
            return nombre;
    }

    static public void creacionGuerrero(){
        String nombre = nombre();
        Personajes guerrero = new Personajes(nombre, 12, 10);
        guerrero.mostrarDatos();
    }

    static public void creacionMago(){
        String nombre = nombre();
        Personajes mago = new Personajes(nombre, 18, 5);
        mago.mostrarDatos();
    }

    static public void creacionVagabundo(){
        String nombre = nombre();
        Personajes vagabndo = new Personajes(nombre, 10, 2);
        vagabndo.mostrarDatos();
    }

    static public void eleccionPersonaje(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Selecciona el personaje: Guerrero-1, Mago-2, Vagabundo-3");
        int clase = sc.nextInt();
        
        switch (clase) {
            case 1:
                creacionGuerrero();
                break;
            case 2:
                creacionMago();
                break;
            case 3:
                creacionVagabundo();
        
            default:
                System.out.println("No es valido");
                break;
        }
    }
}