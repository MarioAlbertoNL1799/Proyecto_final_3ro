/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Escritorio;

<<<<<<< HEAD

import java.sql.DriverManager; 
import java.sql.Connection; 
import java.sql.ResultSet; 
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Proyecto extends javax.swing.JFrame {
    public static Asignar_proyectos fraspro = null;
    private Connection conexion;     
    private Statement st;     
    private ResultSet rs;
    Asignar_proyectos a = new Asignar_proyectos();
    Admin fradmin = null;
    public void Conectar() {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/pdesign", "root", "");
            st = conexion.createStatement();
            rs = st.executeQuery("Select * from proyecto;");
            inicio();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 000" + ex.getMessage());
        }
    }
    public void inicio() {
        try {
            rs.first();
            llenarDatos();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 001" + ex.getMessage());
        }
     }
    public void llenarDatos() {
        try{
            this.jtf_id_p.setText(rs.getString("id_p"));
            this.jta_caracteristicas.setText(rs.getString("caracteristicas"));
            this.jtf_nombre_proyecto.setText(rs.getString("nombre_proyecto"));
            this.jcb_tipo.setSelectedItem(rs.getObject("tipo"));
        }catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 002" + ex.getMessage());
        }
    }
    public void Tabla_proyecto() {
        DefaultTableModel model1 = new DefaultTableModel();
        ResultSet rs = Database.getTabla("Select id_p, nombre_proyecto from proyecto");
        model1.setColumnIdentifiers(new Object[]{"ID","Proyecto"});
        try {
           while (rs.next()){
            model1.addRow(new Object[]{rs.getString("id_p"), rs.getString("nombre_proyecto")});
        }
           jtable_proyecto.setModel(model1);
        }
        catch (SQLException e){
            System.out.println (e);
        }
    }
    public Proyecto() {
        initComponents();
        Conectar();
        Tabla_proyecto();
    }
    
    public void Habilitar(boolean funcion){
        this.jta_caracteristicas.setEditable(funcion);
        this.jtf_nombre_proyecto.setEditable(funcion);
        this.jcb_tipo.setEnabled(funcion);
        this.jb_Cancelar.setEnabled(funcion);
        this.jb_guardar.setEnabled(funcion);
    }
    public void vaciar(){
        this.jta_caracteristicas.setText("");
        this.jtf_nombre_proyecto.setText("");
        this.jtf_id_p.setText("00");
        this.jcb_tipo.setSelectedIndex(0);
    }
    public void botones(boolean funcion){
        this.jb_nuevo.setEnabled(funcion);
        this.jb_editar.setEnabled(funcion);
        this.jb_eliminar.setEnabled(funcion);
    }
    private void guardar(){
     try{
            int id_proy =Integer.parseInt(this.jtf_id_p.getText());
            String proyecto = this.jtf_nombre_proyecto.getText(); 
            Object estilo = this.jcb_tipo.getSelectedItem();
            String tipo = String.valueOf(estilo);
            String car = this.jta_caracteristicas.getText();
            if (id_proy == 00){
        //toma de valores
                st.executeUpdate("Insert into proyecto (nombre_proyecto,caracteristicas,tipo)"+"values ('"+ proyecto +"','"+ car +"','"+ tipo +"');");
            }
            else{
                st.executeUpdate("Update proyecto set nombre_proyecto = '"+ proyecto +"', caracteristicas = '"+ car +"', tipo ='"+tipo+"' where id_p = '"+id_proy+"';");
            }
            rs=st.executeQuery("Select * from proyecto");             
            rs.next();
        }catch (SQLException err){
            JOptionPane.showMessageDialog(null,"Error 003 "+err.getMessage()); 
        }
    }
    private void borrarRegistro(){
        int confirmar = JOptionPane.showConfirmDialog(null, "¿Desea eiminar el registro?");
        if (JOptionPane.OK_OPTION== confirmar){
        try {
            int id_proy = Integer.parseInt(this.jtf_id_p.getText());
            st.executeUpdate(" Delete from proyecto where id_p = "+ id_proy);
            JOptionPane.showMessageDialog(null, "Articulo Borrado");
            rs=st.executeQuery("Select * from proyecto");             
            rs.first();
            inicio();
        }
        
        catch(Exception err){
            JOptionPane.showMessageDialog(null, "Error 004 " + err.getMessage());
        }
        }}
     public void Cerrar(){
         Admin admin =  new Admin();
         admin.setVisible(true);
         this.setVisible(false);
       
     }
    /**

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jp_Detalles = new javax.swing.JPanel();
        jl_detalles = new javax.swing.JLabel();
        jl_id_p = new javax.swing.JLabel();
        jl_nombre_proyecto = new javax.swing.JLabel();
        jl_caracteristicas = new javax.swing.JLabel();
        jl_tipo = new javax.swing.JLabel();
        jtf_id_p = new javax.swing.JTextField();
        jtf_nombre_proyecto = new javax.swing.JTextField();
        jcb_tipo = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jta_caracteristicas = new javax.swing.JTextArea();
        jp_Botones = new javax.swing.JPanel();
        jb_nuevo = new javax.swing.JButton();
        jb_editar = new javax.swing.JButton();
        jb_eliminar = new javax.swing.JButton();
        jb_guardar = new javax.swing.JButton();
        jb_Cancelar = new javax.swing.JButton();
        jb_asignar = new javax.swing.JButton();
        jb_salir = new javax.swing.JButton();
        jl_lista = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtable_proyecto = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jl_detalles.setText("Detalles");

        jl_id_p.setText("ID:");

        jl_nombre_proyecto.setText("Nombre:");

        jl_caracteristicas.setText("Características:");

        jl_tipo.setText("Tipo:");

        jtf_id_p.setEditable(false);
        jtf_id_p.setText("00");

        jtf_nombre_proyecto.setEditable(false);
        jtf_nombre_proyecto.setText(" ");

        jcb_tipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "demo", "diseño", "prototipo", "industrial", "otro" }));
        jcb_tipo.setEnabled(false);

        jta_caracteristicas.setEditable(false);
        jta_caracteristicas.setColumns(20);
        jta_caracteristicas.setRows(5);
        jScrollPane2.setViewportView(jta_caracteristicas);

        javax.swing.GroupLayout jp_DetallesLayout = new javax.swing.GroupLayout(jp_Detalles);
        jp_Detalles.setLayout(jp_DetallesLayout);
        jp_DetallesLayout.setHorizontalGroup(
            jp_DetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_DetallesLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jp_DetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jl_detalles)
                    .addGroup(jp_DetallesLayout.createSequentialGroup()
                        .addGroup(jp_DetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jp_DetallesLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jl_tipo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jcb_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_DetallesLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jp_DetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jl_nombre_proyecto)
                                    .addComponent(jl_id_p))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jp_DetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtf_id_p, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtf_nombre_proyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(jp_DetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jl_caracteristicas)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jp_DetallesLayout.setVerticalGroup(
            jp_DetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_DetallesLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jl_detalles)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jl_caracteristicas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jp_DetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_DetallesLayout.createSequentialGroup()
                        .addGroup(jp_DetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtf_id_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jl_id_p))
                        .addGap(18, 18, 18)
                        .addGroup(jp_DetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtf_nombre_proyecto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jl_nombre_proyecto))
                        .addGap(21, 21, 21)
                        .addGroup(jp_DetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jcb_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jl_tipo)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(272, 272, 272))
        );

        jb_nuevo.setText("Nuevo Proyecto");
        jb_nuevo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jb_nuevoMouseClicked(evt);
            }
        });

        jb_editar.setText("Editar Proyecto");
        jb_editar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jb_editarMouseClicked(evt);
            }
        });

        jb_eliminar.setText("Eliminar proyecto");
        jb_eliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jb_eliminarMouseClicked(evt);
            }
        });

        jb_guardar.setText("Guardar ");
        jb_guardar.setEnabled(false);
        jb_guardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jb_guardarMouseClicked(evt);
            }
        });

        jb_Cancelar.setText("Cancelar");
        jb_Cancelar.setEnabled(false);
        jb_Cancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jb_CancelarMouseClicked(evt);
            }
        });

        jb_asignar.setText("Asignar proyecto");
        jb_asignar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jb_asignarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jp_BotonesLayout = new javax.swing.GroupLayout(jp_Botones);
        jp_Botones.setLayout(jp_BotonesLayout);
        jp_BotonesLayout.setHorizontalGroup(
            jp_BotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_BotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_BotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jb_guardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jb_nuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_BotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jb_editar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jb_Cancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_BotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jb_eliminar)
                    .addComponent(jb_asignar))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jp_BotonesLayout.setVerticalGroup(
            jp_BotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_BotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_BotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jb_nuevo)
                    .addComponent(jb_editar)
                    .addComponent(jb_eliminar))
                .addGap(23, 23, 23)
                .addGroup(jp_BotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jb_guardar)
                    .addComponent(jb_Cancelar)
                    .addComponent(jb_asignar))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jb_salir.setText("Salir");
        jb_salir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jb_salirMouseClicked(evt);
            }
        });

        jl_lista.setText("Lista de proyectos:");

        jtable_proyecto = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int ColumnIndex){
                return false;
            }
        };
        jtable_proyecto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Proyecto"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtable_proyecto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtable_proyectoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jtable_proyecto);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jp_Botones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jb_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jl_lista)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jp_Detalles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jp_Detalles, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jl_lista)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jp_Botones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jb_salir)
                        .addGap(22, 22, 22))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jb_nuevoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jb_nuevoMouseClicked
        Habilitar(true);
        vaciar();
        botones(false);
    }//GEN-LAST:event_jb_nuevoMouseClicked

    private void jb_editarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jb_editarMouseClicked
        Habilitar(true);
        botones(false);
    }//GEN-LAST:event_jb_editarMouseClicked

    private void jb_CancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jb_CancelarMouseClicked
        Habilitar(false);
        botones(true);
        inicio();
    }//GEN-LAST:event_jb_CancelarMouseClicked

    private void jb_guardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jb_guardarMouseClicked
        guardar();
        Habilitar(false);
        botones(true);
        Tabla_proyecto();
        inicio();
    }//GEN-LAST:event_jb_guardarMouseClicked

    private void jb_eliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jb_eliminarMouseClicked
        borrarRegistro();
        Habilitar(false);
        botones(true);
        inicio();
        Tabla_proyecto();
    }//GEN-LAST:event_jb_eliminarMouseClicked

    private void jtable_proyectoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtable_proyectoMouseClicked
        try {
            int numero;
            Object cod;
            numero = jtable_proyecto.getSelectedRow();
            cod = jtable_proyecto.getValueAt(numero, 0);
            String sql = ("Select * from proyecto where id_p ="+cod+";");
            rs = st.executeQuery(sql);
            this.jtf_nombre_proyecto.setText(rs.getString("nombre_proyecto"));
            this.jta_caracteristicas.setText(rs.getString("caracteristicas"));
            this.jtf_id_p.setText(rs.getString("id_p"));
            this.jcb_tipo.setSelectedItem(rs.getObject("tipo"));
            rs=st.executeQuery("Select * from proyecto"); 
            rs.first();
        } catch (SQLException ex) {
            
        }
        inicio();
    }//GEN-LAST:event_jtable_proyectoMouseClicked

    private void jb_salirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jb_salirMouseClicked
        Cerrar();
    }//GEN-LAST:event_jb_salirMouseClicked

    private void jb_asignarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jb_asignarMouseClicked
         if(fraspro == null)
       {
         fraspro = new Asignar_proyectos();
         fraspro.setVisible(true);
         Admin.frpro= null;
         this.setVisible(false);
       }
    }//GEN-LAST:event_jb_asignarMouseClicked

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
            java.util.logging.Logger.getLogger(Proyecto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Proyecto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Proyecto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Proyecto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Proyecto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton jb_Cancelar;
    private javax.swing.JButton jb_asignar;
    private javax.swing.JButton jb_editar;
    private javax.swing.JButton jb_eliminar;
    private javax.swing.JButton jb_guardar;
    private javax.swing.JButton jb_nuevo;
    private javax.swing.JButton jb_salir;
    private javax.swing.JComboBox<String> jcb_tipo;
    private javax.swing.JLabel jl_caracteristicas;
    private javax.swing.JLabel jl_detalles;
    private javax.swing.JLabel jl_id_p;
    private javax.swing.JLabel jl_lista;
    private javax.swing.JLabel jl_nombre_proyecto;
    private javax.swing.JLabel jl_tipo;
    private javax.swing.JPanel jp_Botones;
    private javax.swing.JPanel jp_Detalles;
    private javax.swing.JTextArea jta_caracteristicas;
    private javax.swing.JTable jtable_proyecto;
=======
/**
 *
 * @author manl_
 */
public class Proyecto extends javax.swing.JFrame {

    /**
     * Creates new form Proyecto
     */
    public Proyecto() {
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

        jp_Detalles = new javax.swing.JPanel();
        jl_detalles = new javax.swing.JLabel();
        jl_id_p = new javax.swing.JLabel();
        jl_nombre_proyecto = new javax.swing.JLabel();
        jl_caracteristicas = new javax.swing.JLabel();
        jl_tipo = new javax.swing.JLabel();
        jtf_id_p = new javax.swing.JTextField();
        jtf_nombre_proyecto = new javax.swing.JTextField();
        jcb_tipo = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jta_caracteristicas = new javax.swing.JTextArea();
        jp_Botones = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jb_salir = new javax.swing.JButton();
        jl_lista = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jl_detalles.setText("Detalles");

        jl_id_p.setText("ID:");

        jl_nombre_proyecto.setText("Nombre:");

        jl_caracteristicas.setText("Características:");

        jl_tipo.setText("Tipo:");

        jtf_id_p.setText("00");

        jtf_nombre_proyecto.setText(" ");

        jcb_tipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "demo", "diseño", "prototipo", "industrial", "otro" }));

        jta_caracteristicas.setColumns(20);
        jta_caracteristicas.setRows(5);
        jScrollPane2.setViewportView(jta_caracteristicas);

        javax.swing.GroupLayout jp_DetallesLayout = new javax.swing.GroupLayout(jp_Detalles);
        jp_Detalles.setLayout(jp_DetallesLayout);
        jp_DetallesLayout.setHorizontalGroup(
            jp_DetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_DetallesLayout.createSequentialGroup()
                .addGroup(jp_DetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_DetallesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jl_detalles))
                    .addGroup(jp_DetallesLayout.createSequentialGroup()
                        .addGroup(jp_DetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jp_DetallesLayout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jl_tipo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jcb_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_DetallesLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jp_DetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jl_nombre_proyecto)
                                    .addComponent(jl_id_p))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jp_DetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtf_id_p, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtf_nombre_proyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                        .addGroup(jp_DetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jl_caracteristicas)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jp_DetallesLayout.setVerticalGroup(
            jp_DetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_DetallesLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jl_detalles)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jl_caracteristicas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_DetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_DetallesLayout.createSequentialGroup()
                        .addGroup(jp_DetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtf_id_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jl_id_p))
                        .addGap(18, 18, 18)
                        .addGroup(jp_DetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtf_nombre_proyecto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jl_nombre_proyecto))
                        .addGap(21, 21, 21)
                        .addGroup(jp_DetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jcb_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jl_tipo)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jButton1.setText("Nuevo Proyecto");

        jButton2.setText("Editar Proyecto");

        jButton3.setText("Eliminar proyecto");

        jButton4.setText("Guardar ");

        jButton5.setText("Cancelar");

        javax.swing.GroupLayout jp_BotonesLayout = new javax.swing.GroupLayout(jp_Botones);
        jp_Botones.setLayout(jp_BotonesLayout);
        jp_BotonesLayout.setHorizontalGroup(
            jp_BotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_BotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_BotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_BotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jp_BotonesLayout.setVerticalGroup(
            jp_BotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_BotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_BotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jp_BotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jList1);

        jb_salir.setText("Salir");

        jl_lista.setText("Lista de proyectos:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jl_lista)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jp_Detalles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jp_Botones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jb_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jl_lista)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jp_Detalles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jp_Botones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jb_salir)
                        .addGap(22, 22, 22))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Proyecto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Proyecto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Proyecto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Proyecto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Proyecto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jb_salir;
    private javax.swing.JComboBox<String> jcb_tipo;
    private javax.swing.JLabel jl_caracteristicas;
    private javax.swing.JLabel jl_detalles;
    private javax.swing.JLabel jl_id_p;
    private javax.swing.JLabel jl_lista;
    private javax.swing.JLabel jl_nombre_proyecto;
    private javax.swing.JLabel jl_tipo;
    private javax.swing.JPanel jp_Botones;
    private javax.swing.JPanel jp_Detalles;
    private javax.swing.JTextArea jta_caracteristicas;
>>>>>>> origin/master
    private javax.swing.JTextField jtf_id_p;
    private javax.swing.JTextField jtf_nombre_proyecto;
    // End of variables declaration//GEN-END:variables
}
