/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Escritorio;

import static Escritorio.Database.getConectar;
import java.sql.DriverManager; 
import java.sql.Connection; 
import java.sql.ResultSet; 
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Inventario extends javax.swing.JFrame {
    Admin fradmin = null;
    private Connection conexion;     
    private Statement st;     
    private ResultSet rs;
    
    public void Conectar() {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/pdesign", "root", "");
            st = conexion.createStatement();
            rs = st.executeQuery("Select * from existencias;");
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
            this.jtf_nombre.setText(rs.getString("nombre"));
            this.jtf_codigo.setText(rs.getString("codigo"));
            this.jtf_cantidad.setText(rs.getString("cantidad"));
            this.jta_especificaciones.setText(rs.getString("especificaciones"));
            this.jcb_unidad_medida.setSelectedItem(rs.getObject("unidad_m"));
            this.jcb_presentacion.setSelectedItem(rs.getObject("Tipo_m"));
            this.jcb_clase.setSelectedItem(rs.getObject("clase"));
        }catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 002" + ex.getMessage());
        }
    }
    
    public void vaciar(){
        this.jta_especificaciones.setText("");
        this.jtf_cantidad.setText("00");
        this.jtf_codigo.setText("00");
        this.jtf_nombre.setText("");
        this.jcb_presentacion.setSelectedIndex(0);
        this.jcb_unidad_medida.setSelectedIndex(0);
    }
    public void botones(boolean funcion){
        this.jb_nuevo_articulo.setEnabled(funcion);
        this.jb_modificar_articulo.setEnabled(funcion);
        this.jb_eliminar_articulo.setEnabled(funcion);
    }
    public void Habilitar(boolean funcion){
        this.jta_especificaciones.setEditable(funcion);
        this.jtf_cantidad.setEditable(funcion);
        this.jtf_nombre.setEditable(funcion);
        this.jcb_clase.setEnabled(funcion);
        this.jcb_presentacion.setEnabled(funcion);
        this.jcb_unidad_medida.setEnabled(funcion);
        this.jb_guardar.setEnabled(funcion);
        this.jb_cancelar.setEnabled(funcion);
    }
    private void guardar(){
     try{
            int codigo =Integer.parseInt(this.jtf_codigo.getText());
            String name = this.jtf_nombre.getText();
            Object medida = this.jcb_unidad_medida.getSelectedItem();
            String unidad = String.valueOf(medida);
            Object tipo = this.jcb_clase.getSelectedItem();
            String clase = String.valueOf(tipo);
            Object forma = this.jcb_presentacion.getSelectedItem();
            String presentacion = String.valueOf(forma);
            int cantidad = Integer.parseInt(this.jtf_cantidad.getText());
            String especificaciones = this.jta_especificaciones.getText();
            if (codigo == 00){
        //toma de valores
                st.executeUpdate("Insert into existencias (clase,nombre,especificaciones,cantidad, unidad_m,Tipo_m)"+"values ('"+ clase +"','"+ name +"','"+ especificaciones +"','"+ cantidad +"','"+ unidad +"','"+ presentacion +"');");
            }
            else{
                st.executeUpdate("Update existencias set nombre = '"+ name +"', clase = '"+ clase +"', especificaciones ='"+especificaciones+"', cantidad ='"+cantidad+"',unidad_m = '"+unidad+"', Tipo_m = '"+presentacion+"' where codigo = '"+codigo+"';");
            }
            rs=st.executeQuery("Select * from existencias");             
            rs.next();
        }catch (SQLException err){
            JOptionPane.showMessageDialog(null,"Error 003 "+err.getMessage()); 
        }
    }
    public void llenartabla(){
        DefaultTableModel modelo = new DefaultTableModel();
        ResultSet rs = Database.getTabla("select codigo, clase, nombre, cantidad, especificaciones, unidad_m,Tipo_m from existencias");
        modelo.setColumnIdentifiers(new Object[]{"Código","Tipo", "Nombre", "Cantidad"});
        try {
           while (rs.next()){
            modelo.addRow(new Object[]{rs.getString("codigo"), rs.getString("clase"), rs.getString("nombre"), rs.getInt("cantidad")});
        }
           jtable_articulos.setModel(modelo);
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error 004 " + e.getMessage());
        }
    }
    
        private void borrarRegistro(){
        int confirmar = JOptionPane.showConfirmDialog(null, "¿Desea eiminar el registro?");
        if (JOptionPane.OK_OPTION== confirmar){
        try {
            int codigo = Integer.parseInt(this.jtf_codigo.getText());
            st.executeUpdate(" Delete from existencias where codigo = "+ codigo+";");
            JOptionPane.showMessageDialog(null, "Articulo Borrado");
            rs=st.executeQuery("Select * from existencias");             
            rs.next();
            inicio();
        }
        
        catch(Exception err){
            JOptionPane.showMessageDialog(null, "Error 004 " + err.getMessage());
        }
        }
    }
    public Inventario() {
        initComponents();
        Conectar();
        llenartabla();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg_tipo = new javax.swing.ButtonGroup();
        bg_presentacion = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtable_articulos = new javax.swing.JTable();
        jp_Articulo = new javax.swing.JPanel();
        jb_eliminar_articulo = new javax.swing.JButton();
        jb_modificar_articulo = new javax.swing.JButton();
        jb_nuevo_articulo = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jp_Descripcion = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jta_especificaciones = new javax.swing.JTextArea();
        jl_especificaciones = new javax.swing.JLabel();
        jtf_codigo = new javax.swing.JTextField();
        jl_codigo = new javax.swing.JLabel();
        jl_tipo = new javax.swing.JLabel();
        jl_nombre = new javax.swing.JLabel();
        jtf_nombre = new javax.swing.JTextField();
        jl_cantidad = new javax.swing.JLabel();
        jtf_cantidad = new javax.swing.JTextField();
        jl_unidad_medida = new javax.swing.JLabel();
        jcb_unidad_medida = new javax.swing.JComboBox<>();
        jl_presentación = new javax.swing.JLabel();
        jb_guardar = new javax.swing.JButton();
        jb_cancelar = new javax.swing.JButton();
        jcb_presentacion = new javax.swing.JComboBox<>();
        jcb_clase = new javax.swing.JComboBox<>();
        jp_busqueda = new javax.swing.JPanel();
        jl_Inventario = new javax.swing.JLabel();
        jtf_buscar_especifico = new javax.swing.JTextField();
        jb_salir = new javax.swing.JButton();
        jl_busqueda = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jtable_articulos = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int ColumnIndex){
                return false;
            }
        };
        jtable_articulos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Código", "Tipo", "Nombre", "Cantidad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtable_articulos.setCellSelectionEnabled(true);
        jtable_articulos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtable_articulosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtable_articulos);

        jb_eliminar_articulo.setText("Eliminar Artículo");
        jb_eliminar_articulo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jb_eliminar_articuloMouseClicked(evt);
            }
        });

        jb_modificar_articulo.setText("Modificar Artículo");
        jb_modificar_articulo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jb_modificar_articuloMouseClicked(evt);
            }
        });

        jb_nuevo_articulo.setText("Nuevo Artículo");
        jb_nuevo_articulo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jb_nuevo_articuloMouseClicked(evt);
            }
        });

        jLabel2.setText("Artículo");

        javax.swing.GroupLayout jp_ArticuloLayout = new javax.swing.GroupLayout(jp_Articulo);
        jp_Articulo.setLayout(jp_ArticuloLayout);
        jp_ArticuloLayout.setHorizontalGroup(
            jp_ArticuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_ArticuloLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jp_ArticuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jp_ArticuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jb_modificar_articulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jb_nuevo_articulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jb_eliminar_articulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jp_ArticuloLayout.setVerticalGroup(
            jp_ArticuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_ArticuloLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jb_nuevo_articulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jb_modificar_articulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jb_eliminar_articulo)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jLabel3.setText("Descripción del artículo");

        jta_especificaciones.setEditable(false);
        jta_especificaciones.setColumns(20);
        jta_especificaciones.setRows(5);
        jScrollPane2.setViewportView(jta_especificaciones);

        jl_especificaciones.setText("Especificaciones");

        jtf_codigo.setEditable(false);
        jtf_codigo.setText("00");

        jl_codigo.setText("Código:");

        jl_tipo.setText("Tipo:");

        jl_nombre.setText("Nombre:");

        jtf_nombre.setEditable(false);
        jtf_nombre.setText(" ");

        jl_cantidad.setText("Cantidad:");

        jtf_cantidad.setEditable(false);
        jtf_cantidad.setText("0");

        jl_unidad_medida.setText("Unidad de medida:");

        jcb_unidad_medida.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Metros", "Galones", "Kilogramos", "Mililigramos", "Litros", "Mililitros", "Piezas" }));
        jcb_unidad_medida.setEnabled(false);
        jcb_unidad_medida.setOpaque(false);

        jl_presentación.setText("Presentación:");

        jb_guardar.setText("Guadar");
        jb_guardar.setEnabled(false);
        jb_guardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jb_guardarMouseClicked(evt);
            }
        });

        jb_cancelar.setText("Cancelar");
        jb_cancelar.setEnabled(false);
        jb_cancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jb_cancelarMouseClicked(evt);
            }
        });

        jcb_presentacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "Solido", "Liquido" }));
        jcb_presentacion.setEnabled(false);

        jcb_clase.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Material", "Herramienta" }));
        jcb_clase.setEnabled(false);

        javax.swing.GroupLayout jp_DescripcionLayout = new javax.swing.GroupLayout(jp_Descripcion);
        jp_Descripcion.setLayout(jp_DescripcionLayout);
        jp_DescripcionLayout.setHorizontalGroup(
            jp_DescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_DescripcionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_DescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_DescripcionLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jp_DescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jp_DescripcionLayout.createSequentialGroup()
                                .addComponent(jl_unidad_medida)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jcb_unidad_medida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56)
                                .addComponent(jcb_presentacion, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jp_DescripcionLayout.createSequentialGroup()
                                .addComponent(jb_guardar)
                                .addGap(18, 18, 18)
                                .addComponent(jb_cancelar))
                            .addGroup(jp_DescripcionLayout.createSequentialGroup()
                                .addGroup(jp_DescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jcb_clase, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jp_DescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jl_tipo)
                                        .addGroup(jp_DescripcionLayout.createSequentialGroup()
                                            .addComponent(jl_codigo)
                                            .addGap(18, 18, 18)
                                            .addComponent(jtf_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(jp_DescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jp_DescripcionLayout.createSequentialGroup()
                                        .addGap(94, 94, 94)
                                        .addComponent(jl_cantidad)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jtf_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jp_DescripcionLayout.createSequentialGroup()
                                        .addGap(92, 92, 92)
                                        .addComponent(jl_nombre)
                                        .addGap(18, 18, 18)
                                        .addComponent(jtf_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_DescripcionLayout.createSequentialGroup()
                                        .addGap(124, 124, 124)
                                        .addComponent(jl_presentación)
                                        .addGap(83, 83, 83)))))
                        .addGap(45, 45, 45)
                        .addGroup(jp_DescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jl_especificaciones)))
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jp_DescripcionLayout.setVerticalGroup(
            jp_DescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_DescripcionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jp_DescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_DescripcionLayout.createSequentialGroup()
                        .addGroup(jp_DescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jl_codigo)
                            .addComponent(jtf_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jl_tipo)
                        .addGroup(jp_DescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jp_DescripcionLayout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jl_presentación))
                            .addGroup(jp_DescripcionLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcb_clase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jp_DescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jl_unidad_medida)
                            .addComponent(jcb_unidad_medida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcb_presentacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jp_DescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jb_guardar)
                            .addComponent(jb_cancelar)))
                    .addGroup(jp_DescripcionLayout.createSequentialGroup()
                        .addGroup(jp_DescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtf_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jl_nombre)
                            .addComponent(jl_especificaciones))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jp_DescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jp_DescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jtf_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jl_cantidad))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jl_Inventario.setText("Inventario");

        jtf_buscar_especifico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtf_buscar_especificoKeyPressed(evt);
            }
        });

        jb_salir.setText("Salir");
        jb_salir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jb_salirMouseClicked(evt);
            }
        });

        jl_busqueda.setText("Busqueda:");

        javax.swing.GroupLayout jp_busquedaLayout = new javax.swing.GroupLayout(jp_busqueda);
        jp_busqueda.setLayout(jp_busquedaLayout);
        jp_busquedaLayout.setHorizontalGroup(
            jp_busquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_busquedaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jl_Inventario)
                .addGap(334, 334, 334))
            .addGroup(jp_busquedaLayout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jl_busqueda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jtf_buscar_especifico, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jb_salir)
                .addContainerGap())
        );
        jp_busquedaLayout.setVerticalGroup(
            jp_busquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_busquedaLayout.createSequentialGroup()
                .addComponent(jl_Inventario)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_busquedaLayout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(jp_busquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtf_buscar_especifico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jb_salir)
                    .addComponent(jl_busqueda))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jp_busqueda, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jp_Descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jp_Articulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jp_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jp_Articulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jp_Descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jb_nuevo_articuloMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jb_nuevo_articuloMouseClicked
        botones(false);
        vaciar();
        Habilitar(true);
    }//GEN-LAST:event_jb_nuevo_articuloMouseClicked

    private void jb_modificar_articuloMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jb_modificar_articuloMouseClicked
        botones(false);
        Habilitar(true);
    }//GEN-LAST:event_jb_modificar_articuloMouseClicked

    private void jb_cancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jb_cancelarMouseClicked
        botones(true);
        Habilitar(false);
        inicio();
    }//GEN-LAST:event_jb_cancelarMouseClicked

    private void jb_guardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jb_guardarMouseClicked
        guardar();
        llenartabla();
        Habilitar(false);
        botones(true);
        inicio();
    }//GEN-LAST:event_jb_guardarMouseClicked

    private void jb_eliminar_articuloMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jb_eliminar_articuloMouseClicked
        borrarRegistro();
        llenartabla();
        Habilitar(false);
        botones(true);
        inicio();
    }//GEN-LAST:event_jb_eliminar_articuloMouseClicked

    private void jtable_articulosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtable_articulosMouseClicked
        try {
            int numero;
            Object cod;
            numero = jtable_articulos.getSelectedRow();
            cod = jtable_articulos.getValueAt(numero, 0);
            System.out.println(cod);
            String sql = ("Select * from existencias where codigo ="+cod+";");
            rs = st.executeQuery(sql);

            this.jtf_nombre.setText(rs.getString("nombre"));
            this.jtf_codigo.setText(rs.getString("codigo"));
            this.jtf_cantidad.setText(rs.getString("cantidad"));
            this.jta_especificaciones.setText(rs.getString("especificaciones"));
            this.jcb_unidad_medida.setSelectedItem(rs.getObject("unidad_m"));
            this.jcb_presentacion.setSelectedItem(rs.getObject("Tipo_m"));
            this.jcb_clase.setSelectedItem(rs.getObject("clase"));
            rs=st.executeQuery("Select * from existencias"); 
            rs.next();
        } catch (SQLException ex) {
             
        }inicio();

    }//GEN-LAST:event_jtable_articulosMouseClicked

    private void jtf_buscar_especificoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtf_buscar_especificoKeyPressed
        String[] columnas = {"Código","Tipo", "Nombre", "Cantidad"};
        String[] busqueda = new String[30];
        String sql = "Select codigo, clase, nombre, cantidad from existencias where nombre like '%"+ jtf_buscar_especifico.getText()+"%'"
                + "OR clase like '%"+jtf_buscar_especifico.getText()+"%'";
 
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
        Database db = new Database();
        Connection conexion = getConectar();
        try{
           Statement st = (Statement) conexion.createStatement();
           ResultSet rs = st.executeQuery(sql);
           while (rs.next()){
             busqueda[0] = rs.getString("codigo");
             busqueda[1] = rs.getString("clase");
             busqueda[2] = rs.getString("nombre");
             busqueda[3] = rs.getString("cantidad");
             modelo.addRow(busqueda);
           }
           jtable_articulos.setModel(modelo);
        }catch(Exception e){}
        
    }//GEN-LAST:event_jtf_buscar_especificoKeyPressed

    private void jb_salirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jb_salirMouseClicked
         
        if(fradmin == null)
       {
         fradmin = new Admin();
         fradmin.setVisible(true);
         Admin.frin = null;
         this.dispose();
       }
    }//GEN-LAST:event_jb_salirMouseClicked

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
            java.util.logging.Logger.getLogger(Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inventario().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bg_presentacion;
    private javax.swing.ButtonGroup bg_tipo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jb_cancelar;
    private javax.swing.JButton jb_eliminar_articulo;
    private javax.swing.JButton jb_guardar;
    private javax.swing.JButton jb_modificar_articulo;
    private javax.swing.JButton jb_nuevo_articulo;
    private javax.swing.JButton jb_salir;
    private javax.swing.JComboBox<String> jcb_clase;
    private javax.swing.JComboBox<String> jcb_presentacion;
    private javax.swing.JComboBox<String> jcb_unidad_medida;
    private javax.swing.JLabel jl_Inventario;
    private javax.swing.JLabel jl_busqueda;
    private javax.swing.JLabel jl_cantidad;
    private javax.swing.JLabel jl_codigo;
    private javax.swing.JLabel jl_especificaciones;
    private javax.swing.JLabel jl_nombre;
    private javax.swing.JLabel jl_presentación;
    private javax.swing.JLabel jl_tipo;
    private javax.swing.JLabel jl_unidad_medida;
    private javax.swing.JPanel jp_Articulo;
    private javax.swing.JPanel jp_Descripcion;
    private javax.swing.JPanel jp_busqueda;
    private javax.swing.JTextArea jta_especificaciones;
    private javax.swing.JTable jtable_articulos;
    private javax.swing.JTextField jtf_buscar_especifico;
    private javax.swing.JTextField jtf_cantidad;
    private javax.swing.JTextField jtf_codigo;
    private javax.swing.JTextField jtf_nombre;
    // End of variables declaration//GEN-END:variables
}
