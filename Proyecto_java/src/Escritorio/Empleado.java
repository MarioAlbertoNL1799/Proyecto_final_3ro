/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Escritorio;

import static Escritorio.Database.getConectar;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Diego
 */
public class Empleado extends javax.swing.JFrame {
    private Connection conexion;     
    private Statement st;     
    private ResultSet rs;
    Admin fradmin = null;
    
    public void mostrar() throws SQLException{
        
        try{
            Connection conexion = getConectar();
            st = conexion.createStatement();
            rs = st.executeQuery("Select * from empleado");
            principio();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error mostrar " + ex.getMessage());   
        }
        
    }
    public void principio() {
        try {
            rs.first();
            llenarDatos();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error llenado:1 " + ex.getMessage());
        }
    }
    public void limpiar(){
        this.jtf_rfc.setText("");
        this.jtf_nombre.setText("");
        this.jtf_ape_p.setText("");
        this.jtf_ape_m.setText("");
        this.jtf_locker.setText("");
        this.jtf_telefono.setText("");
        this.jcb_area.setSelectedIndex(0);
        this.jcb_genero.setSelectedIndex(0);
        this.jft_fecha.setText("");
    }
    public void llenarDatos() {
        try{
            this.jtf_rfc.setText(rs.getString("rfc"));
            this.jtf_nombre.setText(rs.getString("nombre_e"));
            this.jtf_ape_p.setText(rs.getString("ape_pat"));
            this.jtf_ape_m.setText(rs.getString("ape_mat"));
            this.jcb_genero.setSelectedItem(rs.getObject("genero"));
            this.jtf_locker.setText(rs.getString("locker"));
            this.jft_fecha.setText(rs.getString("fecha_nacimiento"));
            this.jtf_telefono.setText(rs.getString("telefono"));
            this.jcb_area.setSelectedItem(rs.getObject("area"));
        }catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error llenado:2 " + ex.getMessage());
        }
    }
    public void llenartabla(){
        DefaultTableModel modeloe = new DefaultTableModel();
        rs = Database.getTabla("SELECT rfc, nombre_e, ape_pat, ape_mat, genero, fecha_nacimiento, telefono, locker, area FROM empleado");
        modeloe.setColumnIdentifiers(new Object[]{"RFC","Nombre", "Apellido paterno", "Apellido paterno", "Género", "Fecha de nacimiento", "Teléfono", "Locker", "Área"});
        try {
           while (rs.next()){
            modeloe.addRow(new Object[]{rs.getString("rfc"), rs.getString("nombre_e"), rs.getString("ape_pat"), rs.getString("ape_mat"), rs.getString("genero"), rs.getDate("fecha_nacimiento"), rs.getString("telefono"),rs.getInt("locker"),rs.getString("area")});
        }
           jt_empleado.setModel(modeloe);
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error tabla " + e.getMessage());
        }
    }
    private void habilitar(){
        this.jtf_rfc.setEditable(true);
        this.jtf_nombre.setEditable(true);
        this.jtf_ape_p.setEditable(true);
        this.jtf_ape_m.setEditable(true);
        this.jtf_locker.setEditable(true);
        this.jtf_telefono.setEditable(true);
        this.jcb_area.setEnabled(true);
        this.jcb_genero.setEnabled(true);
        this.jft_fecha.setEditable(true);
    }
    
    private void deshabilitar(){
        this.jtf_rfc.setEditable(false);
        this.jtf_nombre.setEditable(false);
        this.jtf_ape_p.setEditable(false);
        this.jtf_ape_m.setEditable(false);
        this.jtf_locker.setEditable(false);
        this.jtf_telefono.setEditable(false);
        this.jcb_area.setEnabled(false);
        this.jcb_genero.setEnabled(false);
        this.jft_fecha.setEditable(false);
    }
    public void guardar(){
        try{
            String rfc = this.jtf_rfc.getText();
            String nombre = this.jtf_nombre.getText();
            String apep = this.jtf_ape_p.getText();
            String apem = this.jtf_ape_m.getText();
            String tel = this.jtf_telefono.getText();
            String fecha = this.jft_fecha.getText();
            Object genero = this.jcb_genero.getSelectedItem();
            int loc =Integer.parseInt(this.jtf_locker.getText());
            Object area = this.jcb_area.getSelectedItem();
                st.executeUpdate("Insert into empleado (rfc,nombre_e,ape_pat,ape_mat,genero,fecha_nacimiento,telefono,locker,area)"+"values ('"+ rfc +"','"+ nombre +"','"+ apep +"','"+ apem +"','"+ genero +"','"+ fecha +"','"+ tel +"',"+ loc +",'"+ area +"');");
                JOptionPane.showMessageDialog(null,"Guardado correctamente"); 
                rs=st.executeQuery("Select * from empleado");             
                rs.next();
        }catch (SQLException err){
            JOptionPane.showMessageDialog(null,"Error al Guardar "+err.getMessage()); 
        } 
    }
    public void modificar(){
        try{
            String rfc = this.jtf_rfc.getText();
            String nombre = this.jtf_nombre.getText();
            String apep = this.jtf_ape_p.getText();
            String apem = this.jtf_ape_m.getText();
            String tel = this.jtf_telefono.getText();
            String fecha = this.jft_fecha.getText();
            Object genero = this.jcb_genero.getSelectedItem();
            int loc =Integer.parseInt(this.jtf_locker.getText());
            Object area = this.jcb_area.getSelectedItem();
            st.executeUpdate("Update empleado set rfc = '"+ rfc +"', nombre_e = '"+ nombre +"', ape_pat ='"+apep+"', ape_mat ='"+apem+"',genero = '"+genero+"', fecha_nacimiento = '"+fecha+"', telefono = '"+tel+"', locker = '"+ loc +"', area = '"+ area +"' where rfc = '"+rfc+"';");
            JOptionPane.showMessageDialog(null,"Actualizado correctamente"); 
            rs=st.executeQuery("Select * from empleado");             
            rs.next();
        }catch (SQLException err){
            JOptionPane.showMessageDialog(null,"Error al Actualizar "+err.getMessage()); 
        } 
    }
    private void borrar(){
        int confirmar = JOptionPane.showConfirmDialog(null, "¿Desea eiminar el registro?");
        if (JOptionPane.OK_OPTION== confirmar){
        try {
            String rfc = this.jtf_rfc.getText();
            st.executeUpdate(" Delete from empleado where rfc = "+ rfc+";");
            JOptionPane.showMessageDialog(null, "Empleado Borrado");
            rs=st.executeQuery("Select * from empleado");             
            rs.next();
            principio();
        }
        
        catch(Exception err){
            JOptionPane.showMessageDialog(null, "Error 004 " + err.getMessage());
        }
        }
    }
    public Empleado() throws SQLException {
        initComponents();
        mostrar();
        llenartabla();
        Sletras(jtf_nombre);
        Sletras(jtf_ape_p);
        Sletras(jtf_ape_m);
        Snum(jtf_locker);
        Snum(jtf_telefono);
        Snum(jft_fecha);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
     public void Sletras(JTextField a){
        a.addKeyListener(new KeyAdapter (){
        public void keyTyped(KeyEvent e){
            char c=e.getKeyChar();
            if(Character.isDigit(c)){
                e.consume();
            }
            }    
        });
    }
    public void Snum(JTextField a){
        a.addKeyListener(new KeyAdapter (){
        public void keyTyped(KeyEvent e){
            char c=e.getKeyChar();
            if(Character.isLetter(c)){
                e.consume();
            }
            }    
        });
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jl_rfc = new javax.swing.JLabel();
        jtf_rfc = new javax.swing.JTextField();
        jtf_nombre = new javax.swing.JTextField();
        jl_nombre = new javax.swing.JLabel();
        jtf_ape_p = new javax.swing.JTextField();
        jtf_ape_m = new javax.swing.JTextField();
        jl_ape_p = new javax.swing.JLabel();
        jl_ape_m = new javax.swing.JLabel();
        jl_genero = new javax.swing.JLabel();
        jl_telefono = new javax.swing.JLabel();
        jtf_telefono = new javax.swing.JTextField();
        jl_area = new javax.swing.JLabel();
        jcb_area = new javax.swing.JComboBox<>();
        jl_fecha = new javax.swing.JLabel();
        jl_locker = new javax.swing.JLabel();
        jtf_locker = new javax.swing.JTextField();
        jcb_genero = new javax.swing.JComboBox<>();
        jft_fecha = new javax.swing.JFormattedTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jt_empleado = new javax.swing.JTable();
        jb_guardar = new javax.swing.JButton();
        jb_modificar = new javax.swing.JButton();
        jb_eliminar = new javax.swing.JButton();
        jb_cancelar = new javax.swing.JButton();
        jb_actualizar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jm_nuevo = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jm_inicio = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jm_ayuda = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jl_rfc.setText("RFC");

        jtf_rfc.setEditable(false);

        jtf_nombre.setEditable(false);

        jl_nombre.setText("Nombre");

        jtf_ape_p.setEditable(false);

        jtf_ape_m.setEditable(false);

        jl_ape_p.setText("Apellido paterno");

        jl_ape_m.setText("Apellido materno");

        jl_genero.setText("Género");

        jl_telefono.setText("Teléfono");

        jtf_telefono.setEditable(false);

        jl_area.setText("Área");

        jcb_area.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administración", "Diseño", "Finanzas", "Experimental", "Manufactura", "Vigilante" }));
        jcb_area.setEnabled(false);

        jl_fecha.setText("Fecha de nacimiento");

        jl_locker.setText("Locker");

        jtf_locker.setEditable(false);

        jcb_genero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Femenino", "Masculino" }));
        jcb_genero.setEnabled(false);

        jft_fecha.setEditable(false);
        jft_fecha.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy-mm-dd"))));

        jt_empleado = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int ColumnIndex){
                return false;
            }
        };
        jt_empleado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jt_empleado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt_empleadoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jt_empleado);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jl_nombre)
                            .addComponent(jl_rfc)
                            .addComponent(jl_telefono)
                            .addComponent(jtf_nombre, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                            .addComponent(jtf_rfc)
                            .addComponent(jtf_telefono))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jl_ape_p)
                                .addComponent(jl_area)
                                .addComponent(jcb_area, 0, 121, Short.MAX_VALUE)
                                .addComponent(jtf_ape_p))
                            .addComponent(jl_genero)
                            .addComponent(jcb_genero, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtf_ape_m, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jl_ape_m))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtf_locker, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jl_locker)
                                    .addComponent(jl_fecha)
                                    .addComponent(jft_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 775, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl_rfc)
                    .addComponent(jl_genero)
                    .addComponent(jl_locker))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtf_rfc, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jcb_genero)
                        .addComponent(jtf_locker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl_nombre)
                    .addComponent(jl_ape_p)
                    .addComponent(jl_ape_m))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jtf_ape_p, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                        .addComponent(jtf_ape_m))
                    .addComponent(jtf_nombre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl_telefono)
                    .addComponent(jl_area)
                    .addComponent(jl_fecha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtf_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcb_area, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jft_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        jb_guardar.setText("Guardar");
        jb_guardar.setEnabled(false);
        jb_guardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jb_guardarMouseClicked(evt);
            }
        });

        jb_modificar.setText("Modificar");
        jb_modificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jb_modificarMouseClicked(evt);
            }
        });

        jb_eliminar.setText("Eliminar");
        jb_eliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jb_eliminarMouseClicked(evt);
            }
        });

        jb_cancelar.setText("Cancelar");
        jb_cancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jb_cancelarMouseClicked(evt);
            }
        });

        jb_actualizar.setText("Actualizar");
        jb_actualizar.setEnabled(false);
        jb_actualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jb_actualizarMouseClicked(evt);
            }
        });

        jMenu1.setText("Menú");

        jm_nuevo.setText("Nuevo empleado");
        jm_nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jm_nuevoActionPerformed(evt);
            }
        });
        jMenu1.add(jm_nuevo);
        jMenu1.add(jSeparator1);

        jm_inicio.setText("Inicio");
        jm_inicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jm_inicioActionPerformed(evt);
            }
        });
        jMenu1.add(jm_inicio);
        jMenu1.add(jSeparator2);

        jm_ayuda.setText("Ayuda");
        jMenu1.add(jm_ayuda);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jb_guardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jb_actualizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jb_modificar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jb_eliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jb_cancelar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jb_guardar)
                    .addComponent(jb_modificar)
                    .addComponent(jb_eliminar)
                    .addComponent(jb_cancelar)
                    .addComponent(jb_actualizar))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jm_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jm_nuevoActionPerformed
        jb_guardar.setEnabled(true);
        limpiar();
        habilitar();
    }//GEN-LAST:event_jm_nuevoActionPerformed

    private void jb_modificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jb_modificarMouseClicked
        habilitar();
        jb_actualizar.setEnabled(true); 
    }//GEN-LAST:event_jb_modificarMouseClicked

    private void jb_cancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jb_cancelarMouseClicked
        deshabilitar();
        jb_guardar.setEnabled(false);
        principio();
        jb_actualizar.setEnabled(false); 
    }//GEN-LAST:event_jb_cancelarMouseClicked

    private void jb_guardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jb_guardarMouseClicked
        guardar();
    }//GEN-LAST:event_jb_guardarMouseClicked

    private void jb_actualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jb_actualizarMouseClicked
        modificar();
        llenartabla();
        principio();
    }//GEN-LAST:event_jb_actualizarMouseClicked

    private void jb_eliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jb_eliminarMouseClicked
        borrar();
    }//GEN-LAST:event_jb_eliminarMouseClicked

    private void jt_empleadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_empleadoMouseClicked
        try {
            int col;
            Object dato;
            col = jt_empleado.getSelectedRow();
            dato = jt_empleado.getValueAt(col, 0);
            String sql = ("SELECT * FROM empleado WHERE rfc ="+dato+";");
            rs = st.executeQuery(sql);
            this.jtf_rfc.setText(rs.getString("rfc"));
            this.jtf_nombre.setText(rs.getString("nombre_e"));
            this.jtf_ape_p.setText(rs.getString("ape_pat"));
            this.jtf_ape_m.setText(rs.getString("ape_mat"));
            this.jcb_genero.setSelectedItem(rs.getObject("genero"));
            this.jtf_locker.setText(rs.getString("locker"));
            this.jft_fecha.setText(rs.getString("fecha_nacimiento"));
            this.jtf_telefono.setText(rs.getString("telefono"));
            this.jcb_area.setSelectedItem(rs.getObject("area"));
            rs=st.executeQuery("SELECT * FROM empleado"); 
            rs.next();
        } catch (SQLException ex) {
             
        }principio();

    }//GEN-LAST:event_jt_empleadoMouseClicked

    private void jm_inicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jm_inicioActionPerformed
         
        if(fradmin == null)
       {
         fradmin = new Admin();
         fradmin.setVisible(true);
         Admin.frempleado = null;
         this.setVisible(false);
       }
    }//GEN-LAST:event_jm_inicioActionPerformed

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
            java.util.logging.Logger.getLogger(Empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Empleado().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JButton jb_actualizar;
    private javax.swing.JButton jb_cancelar;
    private javax.swing.JButton jb_eliminar;
    private javax.swing.JButton jb_guardar;
    private javax.swing.JButton jb_modificar;
    private javax.swing.JComboBox<String> jcb_area;
    private javax.swing.JComboBox<String> jcb_genero;
    private javax.swing.JFormattedTextField jft_fecha;
    private javax.swing.JLabel jl_ape_m;
    private javax.swing.JLabel jl_ape_p;
    private javax.swing.JLabel jl_area;
    private javax.swing.JLabel jl_fecha;
    private javax.swing.JLabel jl_genero;
    private javax.swing.JLabel jl_locker;
    private javax.swing.JLabel jl_nombre;
    private javax.swing.JLabel jl_rfc;
    private javax.swing.JLabel jl_telefono;
    private javax.swing.JMenuItem jm_ayuda;
    private javax.swing.JMenuItem jm_inicio;
    private javax.swing.JMenuItem jm_nuevo;
    private javax.swing.JTable jt_empleado;
    private javax.swing.JTextField jtf_ape_m;
    private javax.swing.JTextField jtf_ape_p;
    private javax.swing.JTextField jtf_locker;
    private javax.swing.JTextField jtf_nombre;
    private javax.swing.JTextField jtf_rfc;
    private javax.swing.JTextField jtf_telefono;
    // End of variables declaration//GEN-END:variables
}
