/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;


/**
 *
 * @author Jose
 */
public interface InterfaceVistaAgregar {

    public static String botonAgregarInt = "boton de agregar al menu de la vista agregar";
    public static String botonSalirInt = "boton de salir de la vista agregar";

    public void ejecutar();
    
    public void cerrar();

    public void setControlador(views.Controlador c);

    public int getEdad();

    public double getPeso();

    public String getComboPais();

    public String getComboEspecie();

    public int getComboSector();

    public void setEtiquetaAlimentacion(String alimento);
    
    public void actualizarComboSectores(String especie);
    
    public void cargarComboPais(data.Persistencia persistencia);
}
