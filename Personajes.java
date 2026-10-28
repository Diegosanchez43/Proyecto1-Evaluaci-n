public class Personajes
{
    //Atributos
    private String nombre;
    private int vida;
    private int ataque;
    private int nivel;
    private int defensa;


    //constructores

    public Personajes()
    {

    }

    public Personajes(String nombre)
    {
        this.nombre = nombre;
        this.vida = 100;
        this.ataque = ataque;
        this.nivel = nivel;
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
        if (nivel == nivel +1){
            vida = vida + 10;
            ataque = ataque + 3;
            defensa = defensa + 3;
        }
    }

}