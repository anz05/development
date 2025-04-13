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
public interface InterfaceVistaMenuInicio {
    
    public static String botonListarInt = "boton de listar menu";
    public static String botonAgregarInt = "boton de agregar al menu";
    public static String botonSalirInt = "boton de salir de la ventana Menu";

    public void ejecutar();

    public void salir();
    
    public void setControlador(views.Controlador c);
}
