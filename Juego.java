import java.util.Scanner;
import java.util.Random;

public class Juego {
    public static void main(String[] args) {

        eleccionPersonaje();
    }
    


    static public String nombre(){
        Scanner sc = new Scanner(System.in);
            System.out.println("Introduce Nombre");
            String nombre = sc.nextLine();
            sc.close();    
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

    static public void generarSlime(){
        Enemigos slime = new Enemigos("Slime", 40, 5, 0, 5);
    }

    static public void generarEsqueleto(){
        Enemigos esqueleto = new Enemigos("Esqueleto", 60, 12, 4, 13);
    }

    static public void generarNoMuerto(){
        Enemigos noMuerto = new Enemigos("No Muerto", 80, 19, 7, 23);
    }

    static public void encontrarSlime(){
        Random random = new Random();
        int probabilidadSlime = random.nextInt(3) + 1;
        if(probabilidadSlime == 1){
            generarSlime();
        }
        
    }

    static public void encontrarEsqueleto(){
        Random random = new Random();
        int probabilidadEsqueleto = random.nextInt(7) + 1;
        if(probabilidadEsqueleto == 2){
            generarEsqueleto();
        }
    }

    static public void encontrarNoMuerto(){
        Random random = new Random();
        int probabilidadNoMuerto = random.nextInt(15) + 1;
        if(probabilidadNoMuerto == 3){
            generarNoMuerto();
        }
    }
}