public class Enemigos extends Personajes{

    private int experiencia;


    public Enemigos(String nombre,int vida, int ataque, int defensa, int experiencia) {
        super(nombre, ataque, defensa,vida);
        this.experiencia = experiencia;
       
    }


    public int getExperiencia() {
        return experiencia;
    }


    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }


    @Override 
    public void mostrarDatos()
    {
        System.out.println("Nombre: " + getNombre());
        System.out.println("Vida: " + getVida());
    }

    
    public void examinar()
    {
         super.mostrarDatos();
         System.out.println("Experiencia: " + experiencia);
    }
}
