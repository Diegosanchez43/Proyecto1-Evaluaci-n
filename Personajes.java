import java.util.ArrayList;

public class Personajes
{
    //Atributos
    private ArrayList<String> inventario = new ArrayList<>();
    private String nombre;
    private int vida;
    private int ataque;
    private int nivel;
    private int defensa;


    //constructores

    public Personajes()
    {

    }

    

    public Personajes(String nombre, int ataque, int defensa, int vida)
    {
        this.nombre = nombre;
        this.ataque = ataque;
        this.nivel = 1;
        this.defensa = defensa;
        this.vida = vida;
    }

      public Personajes(String nombre, int ataque, int defensa)
    {
        this.nombre = nombre;
        this.vida = 100;
        this.ataque = ataque;
        this.nivel = 1;
        this.defensa = defensa;
    }

    //getters / setters

    public String getNombre()
    {
        return this.nombre;
    }

    public void setNombre(String newName)
    {
        this.nombre = newName;
    }


    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa (int defensa) {
        this.defensa = defensa;
    }

    //Métodos



    public void mostrarDatos()
    {
        System.out.println("Nombre: " + nombre);
        System.out.println("Vida: " + vida);
        System.out.println("Ataque: " + ataque);
        System.out.println("Nivel: " + nivel);
        System.out.println("Defensa: " + defensa);
    }

    public void subirNivel(){
            nivel ++;
            vida += 10;
            ataque += 3;
            defensa += 3;
        
    }

    public void curación(int curacion){
        vida += curacion;
    }

    public void recibirDaño(int danyo){
        int danyoRecibido = Math.max(0, danyo - defensa);
        vida = Math.max(0, vida - danyoRecibido);
    }

    public boolean vivo(int vida){
        boolean vivo;
        if (vida == 0){
            vivo = false;
        } else {
            vivo = true;
        }
        return vivo;
    }

    public void añadirObjeto(String objeto) {
        inventario.add(objeto);
    }

    public void mostrarObjetos() {
        System.out.println(inventario);
    }
}