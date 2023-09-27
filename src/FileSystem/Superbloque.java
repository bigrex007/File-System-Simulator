/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FileSystem;

public class Superbloque {
    private final String fecha_creacion_sistema;
    private final String nombre_sistema;
    private final Inodo raiz;
    
    private Inodo inodo_directorio_actual; // inodo relacionado al directorio actual

    private final int tamano_sistema; // tamano del sistema en kb
    private final int tamano_bloque; // tamano de cada bloque en kb
    private final int cant_bloques; // cantidad de bloques disponibles, calculado a partir del tamano del sistema y del tamano por bloque
    
    private int cant_bloques_ocupado;
    private int cant_bloques_disponible;
    
    private int tamano_sistema_ocupado;
    private int tamano_sistema_disponible;
    
    private int cant_archivos;
    private int cant_directorios;

    public Superbloque(String nombre_sistema, int tamano_bloque, int tamano_sistema, String fecha_creacion_sistema) {
        this.fecha_creacion_sistema = fecha_creacion_sistema;
        this.nombre_sistema = nombre_sistema;
        raiz = new Inodo();
        
        this.tamano_bloque = tamano_bloque;
        this.tamano_sistema = tamano_sistema;
        
        cant_bloques = tamano_sistema/tamano_bloque;
        
        cant_bloques_ocupado = 1;
        cant_bloques_disponible = cant_bloques - 1;
        
        tamano_sistema_ocupado = 0;
        tamano_sistema_disponible = tamano_sistema;
        
        cant_archivos = 0;
        cant_directorios = 1;
        
        
        inodo_directorio_actual = raiz;
    }

    public String getFecha_creacion_sistema() {
        return fecha_creacion_sistema;
    }

    public String getNombre_sistema() {
        return nombre_sistema;
    }

    public int getTamano_sistema() {
        return tamano_sistema;
    }

    public int getTamano_bloque() {
        return tamano_bloque;
    }

    public int getCant_bloques() {
        return cant_bloques;
    }

    public int getCant_bloques_ocupado() {
        return cant_bloques_ocupado;
    }

    public int getCant_bloques_disponible() {
        return cant_bloques_disponible;
    }

    public int getTamano_sistema_ocupado() {
        return tamano_sistema_ocupado;
    }

    public int getTamano_sistema_disponible() {
        return tamano_sistema_disponible;
    }

    public int getCant_archivos() {
        return cant_archivos;
    }

    public int getCant_directorios() {
        return cant_directorios;
    }

    public Inodo getRaiz() {
        return raiz;
    }
      
    // Funciones para manipular superbloque
    
    // Funciones para manipular el sistema de archivos
    
    public void setInodo_directorio_actual(Inodo inodo_directorio_actual) {
        this.inodo_directorio_actual = inodo_directorio_actual;
        
    }

    public Inodo getInodo_directorio_actual() {
        return inodo_directorio_actual;
    }
    
    public boolean crearArchivo(String nombre, int tamano) {
        if (cant_bloques_disponible < tamano/tamano_bloque + 1 || tamano_sistema_disponible < tamano) {
            return false;
        }
 
        if (inodo_directorio_actual.crearInodo_archivo(nombre, tamano, (int)Math.ceil((double)tamano/tamano_bloque))) {
            cant_bloques_disponible -= Math.ceil((double)tamano/tamano_bloque);
            cant_bloques_ocupado += Math.ceil((double)tamano/tamano_bloque);

            tamano_sistema_disponible -= tamano;
            tamano_sistema_ocupado += tamano;

            cant_archivos++;
            
            return true;
        } else
            return false;
    }
    
    public boolean borrarArchivo(String nombre) {
        
        Inodo inodo_archivo_borrar = inodo_directorio_actual;
            for (Inodo archivo: inodo_directorio_actual.getInodos_archivo())
                if (archivo.getNombre().equals(nombre))
                    inodo_archivo_borrar = archivo;
        
        int cant_bloques_liberado = 0;
        int tamano_sistema_liberado = 0;
            
        if (inodo_archivo_borrar != null) {
            cant_bloques_liberado = inodo_archivo_borrar.getCant_bloques_asignados();
            tamano_sistema_liberado = inodo_archivo_borrar.getTamano();
            
        }
            
        if (inodo_directorio_actual.borrarInodo_archivo(nombre)) {
            
            
            cant_bloques_disponible += cant_bloques_liberado;
            cant_bloques_ocupado -= cant_bloques_liberado;

            tamano_sistema_disponible += tamano_sistema_liberado;
            tamano_sistema_ocupado -= tamano_sistema_liberado;

            cant_archivos--;
            
            return true;
        } else
            return false;
        
    }
    
    public boolean crearDirectorio(String nombre) {
        if (cant_bloques_disponible == 0) {
            return false;
        }
        
        cant_bloques_disponible--;
        cant_bloques_ocupado++;
        
        cant_directorios++;
        
        return inodo_directorio_actual.crearInodo_directorio(nombre);
    }
    
    public boolean borrarDirectorio(String nombre) {
        
        Inodo inodo_directorio_borrar = inodo_directorio_actual;
        
        for (Inodo directorio: inodo_directorio_actual.getInodos_directorio())
            if (directorio.getNombre().equals(nombre))
                inodo_directorio_borrar = directorio;
        
        int cant_bloques_liberado = 0;
        int tamano_sistema_liberado = 0;
        cant_directorios = 1 + cantDirectorios(inodo_directorio_borrar);
        cant_archivos = cantArchivos(inodo_directorio_borrar);
            
        if (inodo_directorio_borrar != null) {
            cant_bloques_liberado = inodo_directorio_borrar.getCant_bloques_asignados();
            tamano_sistema_liberado = inodo_directorio_borrar.getTamano();
            
        }
            
        if (inodo_directorio_actual.borrarInodo_directorio(nombre)) {
            
            tamano_sistema_disponible += tamano_sistema_liberado;
            tamano_sistema_ocupado -= tamano_sistema_liberado;
            
            cant_bloques_disponible += cant_bloques_liberado;
            cant_bloques_ocupado -= cant_bloques_liberado;
            
            return true;
        } else
            return false;
        
    }
    
    private int cantDirectorios(Inodo inodo_directorio_borrar) {
        int cant = 0;
        
        if (inodo_directorio_borrar.getInodos_directorio().isEmpty())
            return 1;
        
        for (Inodo directorio: inodo_directorio_borrar.getInodos_directorio())
            cant += cantDirectorios(directorio);
        
        return cant;
    }
    
    private int cantArchivos(Inodo inodo_directorio_borrar) {
        int cant = 0;
        
        if (inodo_directorio_borrar.getInodos_archivo().isEmpty())
            return 1;
        
        for (Inodo archivo: inodo_directorio_borrar.getInodos_archivo())
            cant += cantArchivos(archivo);
            
        return cant;
    }
    
}
