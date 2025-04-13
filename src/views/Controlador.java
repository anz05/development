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
import views.VistaMenuInicio;

public class Controlador implements ActionListener {

    private data.Persistencia persistencia;
    private VistaMenuInicio vistaMenuInicio;

    public Controlador() {
    }

    public void iniciar() {

        persistencia = new Persistencia();
        vistaMenuInicio = new VistaMenuInicio();
        vistaMenuInicio.setControlador(this);
        vistaMenuInicio.ejecutar();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("OCURRIO UN EVENTO: " + e.getActionCommand());

        try {
            //Botones de vista menu
            if (e.getActionCommand().equals(vistaMenuInicio.botonListarInt)) {
                vistaAnimales.setVisible(true);
            }
            if (e.getActionCommand().equals(vistaMenuInicio.botonAgregarInt)) {
                vistaAgregar.setControlador(this);
                vistaAgregar.ejecutar();

            }
            if (e.getActionCommand().equals(vistaMenuInicio.botonSalirInt)) {
                vistaMenuInicio.salir();
            }

        } catch (Exception exp) {
            System.out.println("Error inesperado: " + exp.getMessage());
            exp.printStackTrace(); // Esta funcion da la línea exacta donde falló
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



