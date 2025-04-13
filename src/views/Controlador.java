package views;

import data.Persistencia;
import domain.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import javax.swing.JOptionPane;
import views.AnimalViewModel;
import views.AnimalViewModel;
import views.ComidaViewModel;
import views.ComidaViewModel;
import views.ListarAnimalesView;
import views.VistaAgregar;

public class Controlador implements ActionListener {

    private data.Persistencia persistencia;
    private VistaAgregar vistaAgregar;
    private ListarAnimalesView vistaAnimales;

    public Controlador() {
    }

    public void iniciar() {

        persistencia = new Persistencia();

        vistaAnimales = new ListarAnimalesView();
        vistaAgregar = new VistaAgregar();
        vistaAgregar.cargarComboPais(persistencia);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("OCURRIO UN EVENTO: " + e.getActionCommand());

        try { 
            //Botones de la vista agregar
            if (e.getActionCommand().equals(vistaAgregar.botonAgregarInt)) {
                try {
                    int edad = vistaAgregar.getEdad();
                    if (edad < 0) {
                        return;
                    }

                    double peso = vistaAgregar.getPeso();
                    if (peso < 0) {
                        return;
                    }

                    int idSector = vistaAgregar.getComboSector();
                    String nombrePais = vistaAgregar.getComboPais();

                    Sector sector = Persistencia.buscarSector(idSector);
                    Pais pais = Persistencia.buscarPais(nombrePais);

                    Mamifero mamifero;

                    if (vistaAgregar.getEtiquetaTipoAlimentacion().equals("Carnívoro")) {
                        Especie especie = new Especie(
                                vistaAgregar.getComboEspecie(),
                                TipoAlimentacion.CARNIVORO
                        );
                        mamifero = new Carnivoro(edad, peso, especie, sector, pais);

                        Persistencia.agregarMamifero(mamifero);

                        System.out.println("Mamífero agregado con éxito");

                        vistaAnimales.listarAnimales();

                    }
                    if (vistaAgregar.getEtiquetaTipoAlimentacion().equals("Herbívoro")) {
                        Especie especie = new Especie(
                                vistaAgregar.getComboEspecie(),
                                TipoAlimentacion.HERBIVORO
                        );
                        double valorfijo = 0;
                        if (especie.getNombre().equals("Jirafa")) {
                            valorfijo = 170;
                        }
                        if (especie.getNombre().equals("Elefante")) {
                            valorfijo = 320;
                        }
                        mamifero = new Herbivoro(edad, peso, especie, sector, valorfijo, pais);

                        Persistencia.agregarMamifero(mamifero);

                        System.out.println("Mamífero agregado con éxito");

                        vistaAnimales.listarAnimales();

                    }
                    JOptionPane.showMessageDialog(vistaAgregar, "Mamífero agregado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    vistaAgregar.setCambiosRealizados(true);

                } catch (InvalidPropertiesFormatException ex) {
                    System.err.println("Error en los datos del mamífero: " + ex.getMessage());
                }
            }
            if (e.getActionCommand().equals(vistaAgregar.botonSalirInt)) {
                if (!vistaAgregar.isCambiosRealizados()) {
                    int opcion = JOptionPane.showConfirmDialog(
                            vistaAgregar,
                            "Está saliendo sin guardar. ¿Desea continuar?",
                            "Advertencia",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                    );

                    if (opcion == JOptionPane.YES_OPTION) {
                        vistaAgregar.cerrar();
                    }
                } else {
                    vistaAgregar.cerrar();
                }
            }

        } catch (Exception exp) {
            System.out.println("Error inesperado: " + exp.getMessage());
            exp.printStackTrace(); // Esto te da la línea exacta donde falló
        }

    }

    public static TipoAlimentacion[] getTiposAlimentacion() {
        return TipoAlimentacion.values();
    }

    public static ArrayList<Especie> getEspecies() {
        return Persistencia.getEspecies();
    }

    public static ArrayList<Sector> getSectores() {
        return Persistencia.getSectores();
    }

    public static ArrayList<AnimalViewModel> getAnimales() {
        ArrayList<AnimalViewModel> animales = new ArrayList<>();
        for (Mamifero animal : Persistencia.getAnimales()) {
            animales.add(new AnimalViewModel(animal));
        }
        return animales;
    }

    public static ComidaViewModel calcularComida() {
        double totalCarnivoros = Persistencia.getTotalComida(TipoAlimentacion.CARNIVORO);
        double totalHerbivoros = Persistencia.getTotalComida(TipoAlimentacion.HERBIVORO);
        return new ComidaViewModel(totalCarnivoros, totalHerbivoros);
    }

}
