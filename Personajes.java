import java.util.ArrayList;

public class Personajes
{
    //Atributos
    private ArrayList<Objetos> inventario = new ArrayList<>();
    private String nombre;
    private int vida;
    private int vidaMaxima;
    private int ataque;
    private int nivel;
    private int defensa;


    //constructores
    

    public Personajes(String nombre, int ataque, int defensa, int vida)
    {
        this.nombre = nombre;
        this.ataque = ataque;
        this.nivel = 1;
        this.defensa = defensa;
        this.vida = vida;
        this.vidaMaxima = vida;
    }

      public Personajes(String nombre, int ataque, int defensa)
    {
        this.nombre = nombre;
        this.vida = 100;
        this.vidaMaxima = vida;
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

    public int getVidaMaxima(){
        return vidaMaxima;
    }

    public void setVidaMaxima(){
        this.vidaMaxima = vida;
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

    
    public ArrayList<Objetos> getInventario() {
        return inventario;
    }

    public void setInventario(ArrayList<Objetos> inventario) {
        this.inventario = inventario;
    }


    //Métodos

    public void mostrarDatos()
    {
        System.out.println("Nombre: " + nombre);
        System.out.println("Vida: " + vidaMaxima + "/" + vida );
        System.out.println("Ataque: " + ataque);
        System.out.println("Nivel: " + nivel);
        System.out.println("Defensa: " + defensa);
    }

    public void subirNivel(){
            nivel ++;
            vidaMaxima += 10;
            ataque += 3;
            defensa += 3;
        
    }

    public void curacion(int curacion){
        vida += curacion;
        if (vida >vidaMaxima) {
            vida = vidaMaxima;
        }
    }

    public void recibirDanyo(int danyo){
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

    public void añadirObjeto(Objetos objeto) {
        inventario.add(objeto);
    }

    public void eliminarObjeto(Objetos objeto) {
        inventario.remove(objeto);
    }

    public void mostrarObjetos() {
            System.out.println("Selecciona objeto");
        if (inventario.isEmpty()) {
            System.out.println("El inventario esta vacio ");
            return;
        }

        for (int i = 0; i< inventario.size(); i++) {
            Objetos objeto = inventario.get(i);
            System.out.println((i + 1) + ". " + objeto.getNombre() + ": " + objeto.getUsos());
            
        }
    }

    public void mostrarVida(){
        System.out.println(this.nombre + "Vida:" + vida + "/" + vidaMaxima);
    }

    

    
}