/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import FileSystem.Sistema;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class ConfiguracionesForzadas extends javax.swing.JPanel {
    private Sistema sistema;
    private Scanner scan;
    
    /**
     * Creates new form ConfiguracionesPanel
     */
    public ConfiguracionesForzadas() {
        initComponents();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        text_nombre = new javax.swing.JTextField();
        text_tamano_bloque = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        text_tamano_sistema = new javax.swing.JTextField();
        boton_guardar = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel1.setText("Nombre del Sistema de Archivos");

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 0, 36)); // NOI18N
        jLabel2.setText("CONFIGURACIONES");

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel3.setText("Tamaño de los Bloque de Datos");

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel4.setText("Tamaño del Sistema de Archivos");

        boton_guardar.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        boton_guardar.setText("Guardar");
        boton_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_guardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addComponent(jLabel2)
                        .addGap(0, 122, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(text_tamano_bloque, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(text_nombre)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(text_tamano_sistema)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addComponent(boton_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(125, 125, 125)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(text_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(text_tamano_bloque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(text_tamano_sistema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                .addComponent(boton_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void boton_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_guardarActionPerformed
        String nombre = text_nombre.getText();
        int tamano_bloque;
        int tamano_sistema;
        String fecha_creacion = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        
        if (text_nombre.equals("") || text_tamano_bloque.equals("") || text_tamano_sistema.equals("")) {
            JOptionPane.showMessageDialog(this, "Por favor llene todos los datos correctamente");
            return;
        }
            
        try {
            tamano_bloque = Integer.parseInt(text_tamano_bloque.getText());
            tamano_sistema = Integer.parseInt(text_tamano_sistema.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Los datos ingresados no son validos");
            return;
        }
        
        if (Integer.parseInt(text_tamano_bloque.getText()) < 200) {
            JOptionPane.showMessageDialog(this, "Cada bloque debe tener al menos 200 Kb de almacenamiento.");
            return;
        }
        
        if (Integer.parseInt(text_tamano_sistema.getText()) < 10000) {
            JOptionPane.showMessageDialog(this, "El sistema debe tener al menos 10,000 Kb de almacenamiento.");
            return;
        }
        
        if (Integer.parseInt(text_tamano_sistema.getText())/Integer.parseInt(text_tamano_bloque.getText()) < 50) {
            JOptionPane.showMessageDialog(this, "El sistema debe tener al menos 50 bloques (cant_bloques = tamano_sistema/tamano_bloque");
            return;
        }
        
        // Preparar lo que se escribira
        
        String config = "NOMBRE: " + text_nombre.getText().trim() + "\nTAMANO_BLOQUE: " + text_tamano_bloque.getText().trim() + "\nTAMANO_SISTEMA: " + text_tamano_sistema.getText().trim() + "\nFECHA_CREACION: " + fecha_creacion;
        
        try {
            FileWriter escribir_config = new FileWriter("config.txt");
            escribir_config.write(config);
            escribir_config.close();
            
            
        } catch (Exception e) {
            
        }
        
        sistema = new Sistema();
        JOptionPane.showMessageDialog(this, "Bienvenido!");
        
        JFrame padre = (JFrame) SwingUtilities.getWindowAncestor(this);

        padre.setContentPane(new MainPanel(sistema));
        padre.repaint();
        padre.revalidate();
        
        
    }//GEN-LAST:event_boton_guardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boton_guardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField text_nombre;
    private javax.swing.JTextField text_tamano_bloque;
    private javax.swing.JTextField text_tamano_sistema;
    // End of variables declaration//GEN-END:variables
}
