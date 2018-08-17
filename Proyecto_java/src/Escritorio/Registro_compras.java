/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Escritorio;

import java.sql.DriverManager; 
import java.sql.Connection; 
import java.sql.ResultSet; 
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Registro_compras extends javax.swing.JFrame {

    private Connection conexion;     
    private Statement st;     
    private ResultSet rs;
    Admin fradmin = null;
    public void Conectar() {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/pdesign", "root", "");
            st = conexion.createStatement();
            rs = st.executeQuery("Select * from compras;");
            inicio();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 000" + ex.getMessage());
        }
    }
     public void inicio() {
        try {
            rs.first();
            llenado();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 001" + ex.getMessage());
        }
    }
    public void Registros(){
        DefaultTableModel modelo = new DefaultTableModel();
        ResultSet rs = Database.getTabla("select num_reg, Comprobante, cod_e, cantidad, fecha,Forma_pago, costo, Forma_entrega,NO_PROVE from compras");
        modelo.setColumnIdentifiers(new Object[]{"Registro","Comprobante", "Cod. Artic.", "Cantidad","Fecha",
            "F. pago","Costo","F. entrega", "ID Prov"});
        try {
           while (rs.next()){
            modelo.addRow(new Object[]{rs.getInt("num_reg"),rs.getString("Comprobante"), rs.getInt("cod_e"), rs.getInt("cantidad"), rs.getDate("fecha"), 
                rs.getString("Forma_pago"),rs.getString("costo"), rs.getString("Forma_entrega"), rs.getInt("NO_PROVE")});
        }
           jt_registro.setModel(modelo);
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error 004 " + e.getMessage());
        }
    }
    public void llenado(){
        try {
            this.jtf_Comprobante.setText(rs.getString("Comprobante"));
            this.jtf_codigo_articulo.setText(rs.getString("cod_e"));
            this.jtf_cantidad.setText(rs.getString("cantidad"));
            this.jftf_fecha.setText(rs.getString("fecha"));
            this.jcb_Forma_pago.setSelectedItem(rs.getObject("Forma_pago"));
            this.jtf_costo.setText(rs.getString("costo"));
            this.jcb_Forma_entrega.setSelectedItem(rs.getObject("Forma_entrega"));
            this.jtf_NO_PROVE.setText(rs.getString("NO_PROVE"));
            this.jtf_no_reg.setText(rs.getString("num_reg"));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 005 " + ex.getMessage());
        }
    }
    private void guardar(){
     try{
            int n_reg =Integer.parseInt(this.jtf_no_reg.getText());
            String comprobante = this.jtf_Comprobante.getText();
            Object pago = this.jcb_Forma_pago.getSelectedItem();
            String unidad = String.valueOf(pago);
            Object entrega = this.jcb_Forma_entrega.getSelectedItem();
            String clase = String.valueOf(entrega);
            int cantidad = Integer.parseInt(this.jtf_cantidad.getText());
            float costo = Float.parseFloat(this.jtf_costo.getText());
            String fecha = this.jftf_fecha.getText();
            int articulo = Integer.parseInt(this.jtf_codigo_articulo.getText());
            int proveedor = Integer.parseInt(this.jtf_NO_PROVE.getText());
            if (n_reg == 0){
        //toma de valores
                st.executeUpdate("Insert into compras (Comprobante,cod_e,cantidad, fecha,Forma_pago,costo,Forma_entrega,NO_PROVE )"+"values ('"
                        + ""+ comprobante +"','"+ articulo +"','"+ cantidad +"','"+ fecha +"','"+ pago +"','"+ costo +"','"+entrega+"','"+ proveedor +"');");
            }
            else{
                st.executeUpdate("Update compras set Comprobante = '"+ comprobante +"', cod_e = '"+ articulo +"', cantidad ='"+cantidad+"', fecha ='"+fecha+""
                        + "',Forma_pago = '"+pago+"', costo = '"+costo+"', Forma_entrega ='"+entrega+"', NO_PROVE = '"+proveedor+"' where num_reg = '"+n_reg+"';");
            }
            rs=st.executeQuery("Select * from compras");             
            rs.next();
        }catch (SQLException err){
            JOptionPane.showMessageDialog(null,"Error 003 "+err.getMessage()); 
        }
    }
    
    private void borrarRegistro(){
        int confirmar = JOptionPane.showConfirmDialog(null, "¿Desea eiminar el registro?");
        if (JOptionPane.OK_OPTION== confirmar){
        try {
            int reg = Integer.parseInt(this.jtf_no_reg.getText());
            st.executeUpdate(" Delete from compras where num_reg = "+ reg+";");
            JOptionPane.showMessageDialog(null, "Articulo Borrado");
            rs=st.executeQuery("Select * from compras");             
            rs.next();
            inicio();
        }
        
        catch(Exception err){
            JOptionPane.showMessageDialog(null, "Error 004 " + err.getMessage());
        }
        }
    }
    public void campos_vacios(){
        this.jtf_Comprobante.setText("");
        this.jtf_codigo_articulo.setText("");
        this.jtf_cantidad.setText("0");
        this.jftf_fecha.setText("aaaa-mm-dd");
        this.jcb_Forma_pago.setSelectedItem(0);
        this.jtf_costo.setText("00.00");
        this.jcb_Forma_entrega.setSelectedItem(0);
        this.jtf_NO_PROVE.setText("0");
       this.jtf_no_reg.setText("0");
    }
    public void campos(boolean f){
         this.jtf_Comprobante.setEditable(f);
        this.jtf_codigo_articulo.setEditable(f);
        this.jtf_cantidad.setEditable(f);
        this.jftf_fecha.setEditable(f);
        this.jcb_Forma_pago.setEnabled(f);
        this.jtf_costo.setEditable(f);
        this.jcb_Forma_entrega.setEnabled(f);
        this.jtf_NO_PROVE.setEditable(f);
        this.jb_guardar.setEnabled(f);
        this.jb_cancelar.setEnabled(f);
    }
    public void botones(boolean f){
        this.jb_modificar.setEnabled(f);
        this.jb_agregar.setEnabled(f);
        this.jb_eliminar.setEnabled(f);
    }
    public void abrir_articulos(boolean o){
       this.jp_articulos.setVisible(o);
    }
    public void abrir_proveedores(boolean o){
       this.jp_proveedores.setVisible(o);
    }
    
    public Registro_compras() {
        initComponents();
        Conectar();
        Registros();
        Tabla_proveedores();
        tabla_articulos();
        abrir_proveedores(false);
        abrir_articulos(false);
    }
    public void Tabla_proveedores() {
        DefaultTableModel modelA = new DefaultTableModel();
        ResultSet rs = Database.getTabla("Select id_proveedor, Nombre_P from proveedor");
        modelA.setColumnIdentifiers(new Object[]{"ID","Proveedor"});
        try {
           while (rs.next()){
            modelA.addRow(new Object[]{rs.getString("id_pRoveedor"), rs.getString("Nombre_P")});
        }
           jt_proveedores.setModel(modelA);
        }
        catch (SQLException e){
            System.out.println (e);
        }
    }
    public void tabla_articulos(){
        DefaultTableModel modelB = new DefaultTableModel();
        ResultSet rs = Database.getTabla("select codigo, nombre from existencias");
        modelB.setColumnIdentifiers(new Object[]{"Código","Nombre"});
        try {
           while (rs.next()){
            modelB.addRow(new Object[]{rs.getString("codigo"), rs.getString("nombre")});
        }
           jt_articulos.setModel(modelB);
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error 004 " + e.getMessage());
        }
    }
     public void Cerrar(){
         Admin a = new Admin();
             a.setVisible(true);
         this.setVisible(false);
     }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg_Tipo = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jp_registro = new javax.swing.JPanel();
        jl_NO_PROVE = new javax.swing.JLabel();
        jl_Comprobante = new javax.swing.JLabel();
        jl_cod_e = new javax.swing.JLabel();
        jl_Forma_pago = new javax.swing.JLabel();
        jl_Forma_entrega = new javax.swing.JLabel();
        jcb_Forma_pago = new javax.swing.JComboBox<>();
        jcb_Forma_entrega = new javax.swing.JComboBox<>();
        jtf_NO_PROVE = new javax.swing.JTextField();
        jtf_codigo_articulo = new javax.swing.JTextField();
        jtf_Comprobante = new javax.swing.JTextField();
        jl_dia = new javax.swing.JLabel();
        jl_mes = new javax.swing.JLabel();
        jl_año = new javax.swing.JLabel();
        jl_fecha = new javax.swing.JLabel();
        jl_costo = new javax.swing.JLabel();
        jtf_costo = new javax.swing.JTextField();
        jtf_cantidad = new javax.swing.JTextField();
        jl_cantidad = new javax.swing.JLabel();
        jb_guardar = new javax.swing.JButton();
        jb_cancelar = new javax.swing.JButton();
        jl_no_reg = new javax.swing.JLabel();
        jftf_fecha = new javax.swing.JFormattedTextField();
        jtf_no_reg = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jt_registro = new javax.swing.JTable();
        jp_botones = new javax.swing.JPanel();
        jb_ver_prov = new javax.swing.JButton();
        jb_ver_art = new javax.swing.JButton();
        jb_modificar = new javax.swing.JButton();
        jb_eliminar = new javax.swing.JButton();
        jb_salir = new javax.swing.JButton();
        jb_agregar = new javax.swing.JButton();
        jp_articulos = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jt_articulos = new javax.swing.JTable();
        jp_proveedores = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jt_proveedores = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jl_NO_PROVE.setText("No. Proveedor:");

        jl_Comprobante.setText("Comprobante:");

        jl_cod_e.setText("Código articulo:");

        jl_Forma_pago.setText("Forma de pago:");

        jl_Forma_entrega.setText("Forma de entrega:");

        jcb_Forma_pago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "EFECTIVO", "CREDITO", "DEBITO", "OTRO" }));
        jcb_Forma_pago.setEnabled(false);

        jcb_Forma_entrega.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Física", "Correo", "Pendiente" }));
        jcb_Forma_entrega.setEnabled(false);

        jtf_NO_PROVE.setEditable(false);
        jtf_NO_PROVE.setText("  ");

        jtf_codigo_articulo.setEditable(false);
        jtf_codigo_articulo.setText(" ");

        jtf_Comprobante.setEditable(false);
        jtf_Comprobante.setText("  ");

        jl_dia.setText("Día");

        jl_mes.setText("Mes");

        jl_año.setText("Año");

        jl_fecha.setText("Fecha:");

        jl_costo.setText("Costo: $");

        jtf_costo.setEditable(false);
        jtf_costo.setText("00.00");

        jtf_cantidad.setEditable(false);
        jtf_cantidad.setText("1");

        jl_cantidad.setText("Cantidad:");

        jb_guardar.setText("Guardar");
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

        jl_no_reg.setText("No. Registro");

        jftf_fecha.setEditable(false);
        jftf_fecha.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy-MM-dd"))));

        jtf_no_reg.setEditable(false);
        jtf_no_reg.setText(" ");
        jtf_no_reg.setEnabled(false);

        javax.swing.GroupLayout jp_registroLayout = new javax.swing.GroupLayout(jp_registro);
        jp_registro.setLayout(jp_registroLayout);
        jp_registroLayout.setHorizontalGroup(
            jp_registroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_registroLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jp_registroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jl_Forma_entrega)
                    .addComponent(jl_Forma_pago)
                    .addGroup(jp_registroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jl_Comprobante)
                        .addComponent(jl_NO_PROVE))
                    .addGroup(jp_registroLayout.createSequentialGroup()
                        .addComponent(jl_cod_e)
                        .addGap(5, 5, 5)))
                .addGap(18, 18, 18)
                .addGroup(jp_registroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_registroLayout.createSequentialGroup()
                        .addGroup(jp_registroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jp_registroLayout.createSequentialGroup()
                                .addComponent(jtf_NO_PROVE, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jl_no_reg)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jtf_no_reg, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14))
                            .addGroup(jp_registroLayout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addGroup(jp_registroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jftf_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jp_registroLayout.createSequentialGroup()
                                        .addComponent(jl_año)
                                        .addGap(18, 18, 18)
                                        .addComponent(jl_mes)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jl_dia))
                                    .addComponent(jtf_costo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jp_registroLayout.createSequentialGroup()
                                .addGroup(jp_registroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtf_Comprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtf_codigo_articulo, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                                .addGroup(jp_registroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jl_costo)
                                    .addComponent(jl_fecha))
                                .addGap(89, 89, 89)))
                        .addGap(167, 167, 167))
                    .addGroup(jp_registroLayout.createSequentialGroup()
                        .addGroup(jp_registroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jp_registroLayout.createSequentialGroup()
                                .addComponent(jcb_Forma_pago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jp_registroLayout.createSequentialGroup()
                                .addComponent(jcb_Forma_entrega, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jl_cantidad)
                                .addGap(18, 18, 18)
                                .addComponent(jtf_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(80, 80, 80)))
                        .addGroup(jp_registroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jb_cancelar, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jb_guardar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34))))
        );
        jp_registroLayout.setVerticalGroup(
            jp_registroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_registroLayout.createSequentialGroup()
                .addGroup(jp_registroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_registroLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jp_registroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jl_no_reg)
                            .addComponent(jtf_no_reg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jp_registroLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jp_registroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jl_NO_PROVE)
                            .addComponent(jtf_NO_PROVE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jp_registroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jp_registroLayout.createSequentialGroup()
                                .addGroup(jp_registroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jl_Comprobante)
                                    .addComponent(jtf_Comprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                                .addGroup(jp_registroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jl_cod_e)
                                    .addComponent(jtf_codigo_articulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jp_registroLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jp_registroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jl_año)
                                    .addComponent(jl_mes)
                                    .addComponent(jl_dia, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jp_registroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jl_fecha)
                                    .addComponent(jftf_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jp_registroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jp_registroLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jp_registroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jcb_Forma_pago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jl_Forma_pago))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jp_registroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jcb_Forma_entrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jl_Forma_entrega)
                                    .addComponent(jb_guardar)))
                            .addGroup(jp_registroLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(jp_registroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jtf_costo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jl_costo))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jp_registroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jtf_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jl_cantidad))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jb_cancelar)
                .addGap(25, 25, 25))
        );

        jt_registro = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int ColumnIndex){
                return false;
            }
        };
        jt_registro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "No.registro", "Comprobante", "Proveedor", "Articulo", "Cantidad", "Fecha", "Forma de pago", "Costo", "Forma de entrega"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jt_registro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt_registroMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jt_registro);

        jb_ver_prov.setText("Ver Proveedores");
        jb_ver_prov.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jb_ver_provMouseClicked(evt);
            }
        });

        jb_ver_art.setText("Ver Artículos");
        jb_ver_art.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jb_ver_artMouseClicked(evt);
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

        jb_salir.setText("Salir");
        jb_salir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jb_salirMouseClicked(evt);
            }
        });

        jb_agregar.setText("Agregar");
        jb_agregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jb_agregarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jp_botonesLayout = new javax.swing.GroupLayout(jp_botones);
        jp_botones.setLayout(jp_botonesLayout);
        jp_botonesLayout.setHorizontalGroup(
            jp_botonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_botonesLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jb_modificar)
                .addGroup(jp_botonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_botonesLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 232, Short.MAX_VALUE)
                        .addComponent(jb_ver_art, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jb_ver_prov)
                        .addGap(18, 18, 18)
                        .addComponent(jb_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jp_botonesLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jb_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jb_agregar)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jp_botonesLayout.setVerticalGroup(
            jp_botonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_botonesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_botonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jb_modificar)
                    .addComponent(jb_eliminar)
                    .addComponent(jb_agregar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_botonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jb_ver_prov)
                    .addComponent(jb_salir)
                    .addComponent(jb_ver_art))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jt_articulos = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int ColumnIndex){
                return false;
            }
        };
        jt_articulos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Nombre articulo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jt_articulos);

        javax.swing.GroupLayout jp_articulosLayout = new javax.swing.GroupLayout(jp_articulos);
        jp_articulos.setLayout(jp_articulosLayout);
        jp_articulosLayout.setHorizontalGroup(
            jp_articulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_articulosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(204, 204, 204))
        );
        jp_articulosLayout.setVerticalGroup(
            jp_articulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_articulosLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 22, Short.MAX_VALUE))
        );

        jt_proveedores = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int ColumnIndex){
                return false;
            }
        };
        jt_proveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Proveedor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jt_proveedores);

        javax.swing.GroupLayout jp_proveedoresLayout = new javax.swing.GroupLayout(jp_proveedores);
        jp_proveedores.setLayout(jp_proveedoresLayout);
        jp_proveedoresLayout.setHorizontalGroup(
            jp_proveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_proveedoresLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jp_proveedoresLayout.setVerticalGroup(
            jp_proveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_proveedoresLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jp_articulos, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jp_proveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 673, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(27, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jp_botones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jp_registro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jp_registro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jp_botones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jp_articulos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jp_proveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(26, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jb_ver_artMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jb_ver_artMouseClicked
        abrir_articulos(true);
    }//GEN-LAST:event_jb_ver_artMouseClicked

    private void jb_ver_provMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jb_ver_provMouseClicked
        abrir_proveedores(true);
    }//GEN-LAST:event_jb_ver_provMouseClicked

    private void jb_modificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jb_modificarMouseClicked
        botones(false);
        campos(true);
    }//GEN-LAST:event_jb_modificarMouseClicked

    private void jt_registroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_registroMouseClicked
            int numero;
            numero = jt_registro.getSelectedRow();
            this.jtf_no_reg.setText(jt_registro.getValueAt(numero,0).toString());
            this.jtf_Comprobante.setText(jt_registro.getValueAt(numero,1).toString());
            this.jtf_codigo_articulo.setText(jt_registro.getValueAt(numero,2).toString());
            this.jtf_cantidad.setText(jt_registro.getValueAt(numero,3).toString());
            this.jftf_fecha.setText(jt_registro.getValueAt(numero,4).toString());
            this.jcb_Forma_pago.setSelectedItem(jt_registro.getValueAt(numero,5).toString());
            this.jtf_costo.setText(jt_registro.getValueAt(numero,6).toString());
            this.jcb_Forma_entrega.setSelectedItem(jt_registro.getValueAt(numero,7).toString());
            this.jtf_NO_PROVE.setText(jt_registro.getValueAt(numero,8).toString());
           
    }//GEN-LAST:event_jt_registroMouseClicked

    private void jb_agregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jb_agregarMouseClicked
        botones(false);
        campos(true);
        campos_vacios();
    }//GEN-LAST:event_jb_agregarMouseClicked

    private void jb_guardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jb_guardarMouseClicked
        guardar();
        inicio();
        Registros();
        botones(true);
        campos(false);
    }//GEN-LAST:event_jb_guardarMouseClicked

    private void jb_cancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jb_cancelarMouseClicked
        botones(true);
        campos(false);
        inicio();
    }//GEN-LAST:event_jb_cancelarMouseClicked

    private void jb_eliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jb_eliminarMouseClicked
        borrarRegistro();
        Registros();
        campos(false);
        botones(true);
        inicio();
    }//GEN-LAST:event_jb_eliminarMouseClicked

    private void jb_salirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jb_salirMouseClicked
        
        if(fradmin == null)
       {
         fradmin = new Admin();
         fradmin.setVisible(true);
         Admin.frcompra = null;
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
            java.util.logging.Logger.getLogger(Registro_compras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registro_compras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registro_compras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registro_compras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registro_compras().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bg_Tipo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton jb_agregar;
    private javax.swing.JButton jb_cancelar;
    private javax.swing.JButton jb_eliminar;
    private javax.swing.JButton jb_guardar;
    private javax.swing.JButton jb_modificar;
    private javax.swing.JButton jb_salir;
    private javax.swing.JButton jb_ver_art;
    private javax.swing.JButton jb_ver_prov;
    private javax.swing.JComboBox<String> jcb_Forma_entrega;
    private javax.swing.JComboBox<String> jcb_Forma_pago;
    private javax.swing.JFormattedTextField jftf_fecha;
    private javax.swing.JLabel jl_Comprobante;
    private javax.swing.JLabel jl_Forma_entrega;
    private javax.swing.JLabel jl_Forma_pago;
    private javax.swing.JLabel jl_NO_PROVE;
    private javax.swing.JLabel jl_año;
    private javax.swing.JLabel jl_cantidad;
    private javax.swing.JLabel jl_cod_e;
    private javax.swing.JLabel jl_costo;
    private javax.swing.JLabel jl_dia;
    private javax.swing.JLabel jl_fecha;
    private javax.swing.JLabel jl_mes;
    private javax.swing.JLabel jl_no_reg;
    private javax.swing.JPanel jp_articulos;
    private javax.swing.JPanel jp_botones;
    private javax.swing.JPanel jp_proveedores;
    private javax.swing.JPanel jp_registro;
    private javax.swing.JTable jt_articulos;
    private javax.swing.JTable jt_proveedores;
    private javax.swing.JTable jt_registro;
    private javax.swing.JTextField jtf_Comprobante;
    private javax.swing.JTextField jtf_NO_PROVE;
    private javax.swing.JTextField jtf_cantidad;
    private javax.swing.JTextField jtf_codigo_articulo;
    private javax.swing.JTextField jtf_costo;
    private javax.swing.JTextField jtf_no_reg;
    // End of variables declaration//GEN-END:variables
}
