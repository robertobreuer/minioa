/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vista;


import com.modelo.conexion.ConexionMinio;
import com.modelo.conexion.ConexionMysql;
import static com.vista.MainFrame.model;
import io.minio.MinioClient;
import io.minio.Result;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 *
 * @author rbreuer
 */
public class LogsDialog extends javax.swing.JDialog {

    /**
     * Creates new form LogsDialog
     */
    public LogsDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();        
    }
    static DefaultTableModel tableModelLogs = new DefaultTableModel();
    static ConexionMysql conn_mysql = new ConexionMysql();
    static DefaultListModel<String> model = new DefaultListModel<>();

    void cargarTablaLogsQuery(String nombreBucket) throws SQLException{        
        
        conn_mysql.selectTableMinio_pdf_copy(nombreBucket);
        tableModelLogs.addColumn("Id");
        tableModelLogs.addColumn("Nombre de archivo");
        tableModelLogs.addColumn("Fecha de subida");
       
        while(ConexionMysql.rs.next()){
           // Object [] fila = new Object[3];
            String [] fila = new String [3];
            for (int i=0;i<3;i++){
                fila[i] = String.valueOf( ConexionMysql.rs.getObject(i+1) ); // El primer indice en rs es el 1, no el cero, por eso se suma 1.
            }
            tableModelLogs.addRow(fila);
            tablaLogs.setModel(tableModelLogs);
        }
      }
 
    void cargarTablaLogs(){
        
        try{
            
            Iterable<Result<Item>> myObjects = ConexionMinio.minioClient.listObjects(MainFrame.nombreBucket);
            TableColumnModel tcm = tablaLogs.getColumnModel();
            tableModelLogs.addColumn("Nombre de archivo");
            tableModelLogs.addColumn("Tamaño");
            tableModelLogs.addColumn("Ultima modificación");
            
            
            
            for(Result<Item> result : myObjects){
               Item item = result.get();
                 String fila [] = new String[3];
                   
                fila[0]= String.valueOf(item.objectName());
                fila[1]= String.valueOf(item.size());
                fila[2]=String.valueOf(item.lastModified());
         
      //trae datos de array desde la base de datos
                    for(int i=0;i<fila.length;i++){
                       Object[] rows = {fila[0], fila[1], fila[2]};
                 //   System.out.println("print for:  "+fila[0]+","+ fila[1]+","+ fila[2]);  
                      tableModelLogs.addRow(rows);
                      System.out.println(Arrays.toString(rows));
       
       
                    }
            tablaLogs.setModel(tableModelLogs);
            }
            
        }catch(Exception e){
        }
    }
    
    void resetListModel(){
     model.removeAllElements();
     
    }
    
    void resetTableModel(){
        // resetea el modelo de la tabla
       tableModelLogs.setRowCount(0);
       tableModelLogs.setColumnCount(0);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaLogs = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        tablaLogs.setAutoCreateRowSorter(true);
        tablaLogs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaLogs);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Logs de Sistema");

        jMenu1.setText("Archivo");
        jMenu1.add(jSeparator2);

        jMenuItem3.setText("Cerrar");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);
        jMenu1.add(jSeparator1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(201, 201, 201)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
       resetTableModel();
       conn_mysql.conectar();
        try {           
            cargarTablaLogsQuery(MainFrame.nombreBucket);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No hay logs disponibles para buket: "+ MainFrame.nombreBucket );
            Logger.getLogger(LogsDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
            //System.out.print(MainFrame.nombreBucket);
    }//GEN-LAST:event_formWindowOpened

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        this.hide();
        
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LogsDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LogsDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LogsDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LogsDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                LogsDialog dialog = new LogsDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JTable tablaLogs;
    // End of variables declaration//GEN-END:variables
}
