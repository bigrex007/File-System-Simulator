/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FileSystem;

import java.io.File;
import java.util.Scanner;

public class Sistema {
    private Superbloque superbloque; // donde se almacena la informacion del sistema

    private Scanner scan;
    
    public Sistema() {
        
        try {
            
            String nombre, tamano_bloque, tamano_sistema, fecha_creacion;
            
            scan = new Scanner(new File("config.txt"));
            
            nombre = scan.nextLine().replace("NOMBRE: ", "");
            tamano_bloque = scan.nextLine().replace("TAMANO_BLOQUE: ", "");
            tamano_sistema = scan.nextLine().replace("TAMANO_SISTEMA: ", "");
            fecha_creacion = scan.nextLine().replace("FECHA_CREACION: ", "");
            
            superbloque = new Superbloque(nombre, Integer.parseInt(tamano_bloque), Integer.parseInt(tamano_sistema), fecha_creacion);
            
            scan.close();
            
            
        } catch (Exception e) {
            
        }
        
    }
 
    public Superbloque getSuperbloque() {
        return superbloque;
    }
 
}
