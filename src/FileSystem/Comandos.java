/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FileSystem;

import GUI.ConfiguracionesForzadas;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Comandos {
    
    public static String directorioActual(JPanel main_panel, Sistema sistema) {
        
        String path = sistema.getSuperbloque().getInodo_directorio_actual().getNombre() + "||";
        Inodo inodo_nav = sistema.getSuperbloque().getInodo_directorio_actual();
        
        while (!inodo_nav.equals(sistema.getSuperbloque().getRaiz())) {
            
            inodo_nav = inodo_nav.getInodo_padre();
            
            path = inodo_nav.getNombre() + "||" + path;
            
        }
        
        return path;
        
    }
    
    public static String ingresarDirectorio(JPanel main_panel, Sistema sistema, String nombre) {
        
        for(Inodo inodo: sistema.getSuperbloque().getInodo_directorio_actual().getInodos_directorio()) {
            if (inodo.getNombre().equals(nombre)) {
                sistema.getSuperbloque().setInodo_directorio_actual(inodo);
                return "SE INGRESO AL DIRECTORIO DE MANERA EXITOSA";
            }
        }
        
        return "DIRECTORIO NO ENCONTRADO EN DIRECTORIO ACTUAL";
        
    }
    
    public static String verArchivo(JPanel main_panel, Sistema sistema, String nombre) {
        
        for(Inodo inodo: sistema.getSuperbloque().getInodo_directorio_actual().getInodos_archivo()) {
            if (inodo.getNombre().equals(nombre)) {
                return "NOMBRE: " + inodo.getNombre() + " TAMANO: " + inodo.getTamano() + "KB CANT_BLOQUES: " + inodo.getCant_bloques_asignados() + " FECHA_CREACION: " + inodo.getFecha_creacion();
            }
        }
        
        return "ARCHIVO NO ENCONTRADO EN DIRECTORIO ACTUAL";
        
    }
    
    public static String regresarPadre(JPanel main_panel, Sistema sistema) {
        
        if(sistema.getSuperbloque().getInodo_directorio_actual().getNombre().equals("raiz")) {
            return "SE ENCUENTRA EN DIRECTORIO ROOT";
            
        } else {
            sistema.getSuperbloque().setInodo_directorio_actual(sistema.getSuperbloque().getInodo_directorio_actual().getInodo_padre());
        
            return "SE HA MOVIDO AL DIRECTORIO PADRE";
        }
        
        
        
    }
    
    public static String crearDirectorio(JPanel main_panel, Sistema sistema, String nombre) {
        
        for(Inodo inodo: sistema.getSuperbloque().getInodo_directorio_actual().getInodos_directorio()) {
            if (inodo.getNombre().equals(nombre)) {
                return "NOMBRE INVALIDO, DIRECTORIO YA EXISTE";
            }
        }
        
        if(sistema.getSuperbloque().crearDirectorio(nombre)) {
            return "DIRECTORIO CREADO EXITOSAMENTE";
        } else {
            return "NO HAY SUFICIENTES BLOQUES DE DATOS EN EL DISCO";
        }
    }
    
    public static String crearArchivo(JPanel main_panel, Sistema sistema, String[] args) {
        
        String nombre;
        int tamano;
        
        try {
            
            nombre = args[1];
            
        } catch (Exception e) {
            
            return "NOMBRE INVALIDO, NO SE PUDO CREAR EL ARCHIVO";
            
        }
        
        try {
            
            tamano = Integer.parseInt(args[2]);
            if (tamano <= 0)
                return "TAMANO DE ARCHIVO INVALIDO, NO PUEDE SER IGUAL O MENOR A 0";
            
        } catch (Exception e) {
            return "CODIGO NO VALIDO, REVISAR SINTAXIS\n";
            
        }
        
        if (sistema.getSuperbloque().crearArchivo(nombre, tamano)) {
            return "ARCHIVO CREADO EXITOSAMENTE";
        } else {
            return "NO SE PUEDE CREAR ARCHIVO, REVISE ESPACIO O ARCHIVOS REPETIDOS";
        }
        
    }
    
    public static String verEspacioDisponible(Sistema sistema) {
        
        return ("SE TIENE QUE HAY " + sistema.getSuperbloque().getCant_bloques_disponible() + " DISPONIBLES DE LOS " + sistema.getSuperbloque().getCant_bloques() + " BLOQUES QUE HAY EN TOTAL.\nADEMAS HAY " + sistema.getSuperbloque().getTamano_sistema_disponible() + "KB DISPONIBLES DE " + sistema.getSuperbloque().getTamano_sistema() + "KB");
    
    }

    public static String borrarArchivo(JPanel main_panel, Sistema sistema, String nombre) {
        
        if (sistema.getSuperbloque().borrarArchivo(nombre)) {
            return "ARCHIVO BORRADO CON EXITO";
        } else {
            return "NO EXISTE EL ARCHIVO QUE DESEA BORRAR";
        }
        
    }
    
    public static String borrarDirectorio(JPanel main_panel, Sistema sistema, String nombre) {
        
        if (sistema.getSuperbloque().borrarDirectorio(nombre)) {
            return "DIRECTORIO BORRADO CON EXITO";
        } else {
            return "NO EXISTE EL DIRECTORIO QUE DESEA BORRAR";
        }
        
    }
    
    public static String listarContenido(JPanel main_panel, Sistema sistema) {
        String contenido = "Directorios: ";
        
        for (Inodo directorio: sistema.getSuperbloque().getInodo_directorio_actual().getInodos_directorio())
            contenido += "[" + directorio.getNombre() + "] ";
        
        contenido += "\nArchivos: ";
        
        for (Inodo archivo: sistema.getSuperbloque().getInodo_directorio_actual().getInodos_archivo())
            contenido += "[" + archivo.getNombre() + "] ";
        
        return contenido;
        
    }
    
    public static void format(JPanel main_panel, Sistema sistema) {
        sistema = null;
  
        new File("config.txt").delete();
        new File("historial.txt").delete();
        
        JFrame padre = (JFrame) SwingUtilities.getWindowAncestor(main_panel);

        padre.setContentPane(new ConfiguracionesForzadas());
        padre.repaint();
        padre.revalidate();
        
    }
    
    public static String estadisticas(JPanel main_panel, Sistema sistema) {
        String estadisticas = "Nombre del Sistema:" + sistema.getSuperbloque().getNombre_sistema() + "\nFecha de creacion del sistema: " + sistema.getSuperbloque().getFecha_creacion_sistema() + "\nCantidad de archivos: " + sistema.getSuperbloque().getCant_archivos() + "\nCantidad de directorios: " + sistema.getSuperbloque().getCant_directorios() + "\nCantidad de espacio utilizado: " + sistema.getSuperbloque().getTamano_sistema_ocupado() + "Kb\nEspacio disponible en el sistema: " + sistema.getSuperbloque().getTamano_sistema_disponible() + "Kb\nCantidad de bloques utilizados: " + sistema.getSuperbloque().getCant_bloques_ocupado() + "\nCantidad de bloques disponibles: " + sistema.getSuperbloque().getCant_bloques_disponible() + "\nTamano total del sistema: " + sistema.getSuperbloque().getTamano_sistema() + "Kb\nCantidad total de bloques: " + sistema.getSuperbloque().getCant_bloques() + "\nTamano de cada bloque: " + sistema.getSuperbloque().getTamano_bloque() + "Kb";
        
        return estadisticas;
    }
    
}
