package domain;

public class Especie {

    private String nombre;
    private double porcentajePesoCarnivoro;

    private TipoAlimentacion tipoAlimentacion;

    public Especie(String nombre, TipoAlimentacion tipoAlimentacion, double porcentajePesoCarnivoro) {
        this.nombre = nombre;
        if (tipoAlimentacion.esCarnivoro()) {
            this.porcentajePesoCarnivoro = porcentajePesoCarnivoro;
        }
        this.tipoAlimentacion = tipoAlimentacion;
    }

    public Especie(String nombre, TipoAlimentacion tipoAlimentacion) {
        this.nombre = nombre;
        if(tipoAlimentacion.esCarnivoro()){
            if(nombre.equals("León")){
                this.porcentajePesoCarnivoro = 0.2;
            }
            if(nombre.equals("Tigre")){
                this.porcentajePesoCarnivoro = 0.15;
            } 
        }
        this.tipoAlimentacion = tipoAlimentacion;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPorcentajePesoCarnivoro() {
        return porcentajePesoCarnivoro;
    }

    public TipoAlimentacion getTipoAlimentacion() {
        return tipoAlimentacion;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
