public class Objetos {
    private String nombre;
    private String desctripcion;
    private int usos;
    private int curacion;
    private int danyo;
    
    public Objetos(String nombre, String desctripcion, int usos, int danyo) {
        this.nombre = nombre;
        this.desctripcion = desctripcion;
        this.usos = usos;
        this.danyo = danyo;
    }
    
    public Objetos(String nombre, int usos, String desctripcion, int curacion) {
        this.nombre = nombre;
        this.desctripcion = desctripcion;
        this.usos = usos;
        this.curacion = curacion;
    }
    public Objetos(String nombre, String desctripcion, int usos, int curacion, int danyo) {
        this.nombre = nombre;
        this.desctripcion = desctripcion;
        this.usos = usos;
        this.curacion = curacion;
        this.danyo = danyo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDesctripcion() {
        return desctripcion;
    }

    public void setDesctripcion(String desctripcion) {
        this.desctripcion = desctripcion;
    }

    public int getUsos() {
        return usos;
    }

    public void setUsos(int usos) {
        this.usos = usos;
    }

    public int getCuracion() {
        return curacion;
    }

    public void setCuracion(int curacion) {
        this.curacion = curacion;
    }

    public int getDanyo() {
        return danyo;
    }

    public void setDanyo(int danyo) {
        this.danyo = danyo;
    }

    public boolean usarCuracion(Personajes personajes) {
        if (usos<=0){
            return false;
        }

        personajes.curacion(curacion);
        usos--;
        return true;

    }


}
