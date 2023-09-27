/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FileSystem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Inodo {
    
    private Scanner scan;
    
    private Inodo inodo_padre; // inumero del inodo padre
    private String nombre; // Nombre del archivo/directorio
    private final String tipo; // Si es archivo o directorio
    private final String fecha_creacion; // fecha en el que el archivo o directorio fue creado
    private int tamano; // tamano del archivo/directorio
    private int cant_bloques_asignados; // cantidad correspondientes del bloque 
    private final ArrayList<Inodo> inodos_hijo; // inodos hijos a este inodo
    
    // Para crear el inodo root, el inodo_padre de root siempre sera null
    public Inodo() {
        
        nombre = "raiz";
        inodo_padre = null;
        tipo = "directorio";
        
        fecha_creacion = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        tamano = 0;
        cant_bloques_asignados = 1;
        //bloques_asignados = new ArrayList();
        inodos_hijo = new ArrayList();
        
    }
    
    // Para crear un nuevo inodo de un directorio
    public Inodo(String nombre, Inodo inodo_padre) {
        
        this.inodo_padre = inodo_padre;
        this.nombre = nombre;
        tipo = "directorio";
        
        fecha_creacion = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        tamano = 0;
        cant_bloques_asignados = 1;
        inodos_hijo = new ArrayList();
        
    }
    
    // Para crear un nuevo inodo de un archivo
    public Inodo(String nombre, Inodo inodo_padre, int tamano, int cant_bloques_asignados) {
        
        this.inodo_padre = inodo_padre;
        this.nombre = nombre;
        tipo = "archivo";
        
        fecha_creacion = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        this.tamano = tamano;
        this.cant_bloques_asignados = cant_bloques_asignados;
        inodos_hijo = new ArrayList();
        
    }
    
    // Setters y Getters basicos de la clase
    public Inodo getInodo_padre() {
        return inodo_padre;
    }

    public void setInodo_padre(Inodo inodo_padre) {
        this.inodo_padre = inodo_padre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCant_bloques_asignados() {
        return cant_bloques_asignados;
    }

    public void setCant_bloques_asignados(int cant_bloques_asignados) {
        this.cant_bloques_asignados = cant_bloques_asignados;
    }

    public String getTipo() {
        return tipo;
    }

    public int getTamano() {
        return tamano;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }
    
    public ArrayList<Inodo> getInodos_archivo() {
        ArrayList<Inodo> archivos = new ArrayList();
        
        for (Inodo archivo: inodos_hijo) {
            if (archivo.getTipo().equals("archivo")) {
                archivos.add(archivo);
            }
        }
        
        return archivos;
        
    }
    
    public ArrayList<Inodo> getInodos_directorio() {
        ArrayList<Inodo> directorios = new ArrayList();
        
        for (Inodo directorio: inodos_hijo) {
            if (directorio.getTipo().equals("directorio")) {
                directorios.add(directorio);
            }
        }
        
        return directorios;
        
    }

    public boolean crearInodo_archivo(String nombre, int tamano_archivo, int cant_bloques_asignados) {

        for(Inodo archivo: getInodos_archivo())
            if (archivo.getNombre().equals(nombre))
                return false;
        
        inodos_hijo.add(new Inodo(nombre, this, tamano_archivo, cant_bloques_asignados));
        
        recalcularTamano();
        recalcularCant_bloques_asignados();
        
        return true;
        
    }
    
    public boolean crearInodo_directorio(String nombre) {
        
        for(Inodo directorio: getInodos_directorio())
            if (directorio.getNombre().equals(nombre))
                return false;
        
        inodos_hijo.add(new Inodo(nombre, this));
        
        recalcularCant_bloques_asignados();
        
        return true;
        
    }
    
    public boolean borrarInodo_archivo(String nombre) {
        for(Inodo archivo: getInodos_archivo())
            if (archivo.getNombre().equals(nombre)) {
                inodos_hijo.remove(archivo);
                recalcularTamano();
                recalcularCant_bloques_asignados();
                return true;   
            }
        
        return false;
        
    }
    
    public boolean borrarInodo_directorio(String nombre) {
        for(Inodo directorio: getInodos_directorio())
            if (directorio.getNombre().equals(nombre)) {
                inodos_hijo.remove(directorio);
                recalcularTamano();
                recalcularCant_bloques_asignados();
                return true;   
            }
        
        return false;
        
    }
    
    // funciones para recalcular el tamaño de forma recursiva en caso de cualquier cambio o modificacion
    public void recalcularTamano(){
        this.tamano = 0;
        
        for(Inodo inodo: inodos_hijo)
            tamano += inodo.getTamano();
        
        if (inodo_padre != null)
            inodo_padre.recalcularTamano();

    }
    
    public void recalcularCant_bloques_asignados(){
        cant_bloques_asignados = 1;
        
        for(Inodo inodo: inodos_hijo)
            cant_bloques_asignados += inodo.getCant_bloques_asignados();
        
        if (inodo_padre != null)
            inodo_padre.recalcularCant_bloques_asignados();

    }

}
