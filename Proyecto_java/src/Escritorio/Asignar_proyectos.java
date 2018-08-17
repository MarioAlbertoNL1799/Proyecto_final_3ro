/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Escritorio;

import static Escritorio.Admin.frprove;
import static Escritorio.Database.getConectar;
import java.sql.DriverManager; 
import java.sql.Connection; 
import java.sql.ResultSet; 
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Asignar_proyectos extends javax.swing.JFrame {

    private Connection conexion;     
    private Statement st;     
    private ResultSet rs;
    
    public void Conectar() {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/pdesign", "root", "");
            st = conexion.createStatement();
            String query = "Select * from usar left outer join proyecto on usar.no_proyecto = proyecto.id_p " +
                    "left outer join existencias on usar.requerido = existencias.codigo " + 
                    "left outer join usuario on usar.id_user = usuario.id";
            rs = st.executeQuery(query);
            inicio();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 000" + ex.getMessage());
        }
    }
     public void inicio() {
        try {
            rs.first();
            datos();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 001" + ex.getMessage());
        }
     }
     public void Tabla(){
        DefaultTableModel modeloT = new DefaultTableModel();
        String sql = "select conteo, no_proyecto, nombre_proyecto, existencias.nombre, clase, usar.cantidad, username " +
"from usar left outer join proyecto on usar.no_proyecto = proyecto.id_p " +
"left outer join existencias on usar.requerido = existencias.codigo " +
"left outer join usuario on usar.id_user = usuario.id;";
        ResultSet rs = Database.getTabla(sql);
        System.out.println(sql);
        modeloT.setColumnIdentifiers(new Object[]{"Conteo","Cod Proyecto","Nombre", "Artículo", "Tipo","Cantidad","Empleado"});
        try {
           while (rs.next()){
            modeloT.addRow(new Object[]{rs.getString("conteo"),rs.getString("no_proyecto"), rs.getString("proyecto.nombre_proyecto"), rs.getString("existencias.nombre"), 
                rs.getString("clase"), rs.getInt("usar.cantidad"), rs.getString("username")});
        }
           jt_resultados.setModel(modeloT);
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error 004 " + e.getMessage());
        }
    }
   public void verproyectos(){
       Proyecto pr = new Proyecto();
       pr.setVisible(true);
    }
    public Asignar_proyectos() {
        initComponents();
        Tabla();
        Conectar();
        ver_articulos();
        ver_usuarios();
        ver_proyectos();
        abrir_proyectos(false);
        abrir_usuarios(false);
        abrir_articulos(false);
        
    }
     public void datos(){
        try {
            this.jtf_cantidad.setText(rs.getString("usar.cantidad"));
            this.jtf_no_proyecto.setText(rs.getString("no_proyecto"));
            this.jtf_id_usuario.setText(rs.getString("usuario.id"));
            this.jtf_id_articulo.setText(rs.getString("existencias.codigo"));
            this.jtf_conteo.setText(rs.getString("conteo"));
           
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 007" + ex.getMessage());
        }
     }
     public void vacio(){
         this.jtf_cantidad.setText("0");
            this.jtf_no_proyecto.setText("0");
            this.jtf_id_usuario.setText("0");
            this.jtf_id_articulo.setText("0");
            this.jtf_conteo.setText("0");
     }
     public void botones(boolean a){
         this.jb_guardar.setEnabled(a);
         this.jb_cancelar.setEnabled(a);
         this.jtf_cantidad.setEditable(a);
         this.jtf_no_proyecto.setEditable(a);
         this.jtf_id_usuario.setEditable(a);
         this.jtf_id_articulo.setEditable(a);
     }
     public void botones_p(boolean b){
         this.jb_asignar.setEnabled(b);
         this.jb_eliminar.setEnabled(b);
     }
     private void guardar(){
     try{
            int no_proyect =Integer.parseInt(this.jtf_no_proyecto.getText());
            int cant = Integer.parseInt(this.jtf_cantidad.getText());
            int id_usuario = Integer.parseInt(this.jtf_id_usuario.getText());
            int articulo_id = Integer.parseInt(this.jtf_id_articulo.getText());
           if (id_usuario >=1 ){
               st.executeUpdate("Insert into usar (no_proyecto, requerido, cantidad, id_user)"+"values ('"+ no_proyect +"','"+ articulo_id +"','"+ cant +"','"+ id_usuario +"');");
           }
           else{
               st.executeUpdate("Insert into usar (no_proyecto, requerido, cantidad)"+"values ('"+ no_proyect +"','"+ articulo_id +"','"+ cant +"');");
           }
            String sql = "Select * from usar left outer join proyecto on usar.no_proyecto = proyecto.id_p " +
                    "left outer join existencias on usar.requerido = existencias.codigo " + 
                    "left outer join usuario on usar.id_user = usuario.id";
            rs=st.executeQuery(sql);             
            rs.next();
        }catch (SQLException err){
            JOptionPane.showMessageDialog(null,"Error 003 "+err.getMessage()); 
        }
    }
     private void borrar(){
         int valor = jt_resultados.getSelectedRow();
         Object cont = jt_resultados.getValueAt(valor,0);
         int confirmar = JOptionPane.showConfirmDialog(null, "¿Desea eiminar el registro?");
        if (JOptionPane.OK_OPTION== confirmar){
        try {
            st.executeUpdate(" Delete from usar where conteo = "+ cont +";");
            JOptionPane.showMessageDialog(null, "Registro Borrado");
            rs=st.executeQuery("Select * from usar left outer join proyecto on usar.no_proyecto = proyecto.id_p " +
                    "left outer join existencias on usar.requerido = existencias.codigo " + 
                    "left outer join usuario on usar.id_user = usuario.id");             
            rs.next();
            inicio();
        }catch(Exception e){}
        }
     }
     
     public void ver_proyectos(){
        DefaultTableModel modelvproy = new DefaultTableModel();
        ResultSet rs = Database.getTabla("Select id_p, nombre_proyecto from proyecto");
        modelvproy.setColumnIdentifiers(new Object[]{"ID","Proyecto"});
        try {
           while (rs.next()){
            modelvproy.addRow(new Object[]{rs.getString("id_p"), rs.getString("nombre_proyecto")});
        }
           jt_v_proyecto.setModel(modelvproy);
        }
        catch (SQLException e){
            System.out.println (e);
        }}
     public void ver_usuarios(){
     DefaultTableModel modelvus = new DefaultTableModel();
        ResultSet rs = Database.getTabla("Select id, username from usuario");
        modelvus.setColumnIdentifiers(new Object[]{"ID","Usuario"});
        try {
           while (rs.next()){
            modelvus.addRow(new Object[]{rs.getString("id"), rs.getString("username")});
        }
           jt_v_usuarios.setModel(modelvus);
        }
        catch (SQLException e){
            System.out.println (e);
        }}
     public void ver_articulos(){
        DefaultTableModel modelvart = new DefaultTableModel();
        ResultSet rs = Database.getTabla("select codigo, nombre, cantidad from existencias");
        modelvart.setColumnIdentifiers(new Object[]{"Código", "Nombre", "Existencias"});
        try {
           while (rs.next()){
            modelvart.addRow(new Object[]{rs.getString("codigo"), rs.getString("nombre"),rs.getInt("cantidad")});
        }
           jt_v_articulos.setModel(modelvart);
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error 004 " + e.getMessage());
        }
    }
     public void abrir_proyectos(boolean o){
       this.jp_proyectos.setVisible(o);
    }
     public void abrir_usuarios(boolean o){
       this.jp_usuarios.setVisible(o);
    }
     public void abrir_articulos(boolean o){
       this.jp_articulos.setVisible(o);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jp_busqueda = new javax.swing.JPanel();
        jtf_buscar_especifico = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jt_resultados = new javax.swing.JTable();
        jp_botones = new javax.swing.JPanel();
        jb_ver_proyectos = new javax.swing.JButton();
        jb_ver_usuaros = new javax.swing.JButton();
        jb_ver_articulos = new javax.swing.JButton();
        jb_salir = new javax.swing.JButton();
        jp_detalles = new javax.swing.JPanel();
        jl_detalle = new javax.swing.JLabel();
        jl_id_proyecto = new javax.swing.JLabel();
        jtf_no_proyecto = new javax.swing.JTextField();
        jtf_id_articulo = new javax.swing.JTextField();
        jl_requerido = new javax.swing.JLabel();
        jl_cantidad = new javax.swing.JLabel();
        jtf_cantidad = new javax.swing.JTextField();
        jl_id_user = new javax.swing.JLabel();
        jtf_id_usuario = new javax.swing.JTextField();
        jl_conteo = new javax.swing.JLabel();
        jtf_conteo = new javax.swing.JTextField();
        jp_usuarios = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jt_v_usuarios = new javax.swing.JTable();
        jp_proyectos = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jt_v_proyecto = new javax.swing.JTable();
        jp_articulos = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jt_v_articulos = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jb_eliminar = new javax.swing.JButton();
        jb_asignar = new javax.swing.JButton();
        jb_cancelar = new javax.swing.JButton();
        jb_guardar = new javax.swing.JButton();

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
        jScrollPane2.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jtf_buscar_especifico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtf_buscar_especificoKeyPressed(evt);
            }
        });

        jLabel1.setText("Buscar");

        jt_resultados = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int ColumnIndex){
                return false;
            }
        };
        jt_resultados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Artículo", "Tipo", "Cantidad", "Empleados"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jt_resultados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt_resultadosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jt_resultados);

        javax.swing.GroupLayout jp_busquedaLayout = new javax.swing.GroupLayout(jp_busqueda);
        jp_busqueda.setLayout(jp_busquedaLayout);
        jp_busquedaLayout.setHorizontalGroup(
            jp_busquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_busquedaLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jtf_buscar_especifico, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_busquedaLayout.createSequentialGroup()
                .addGap(0, 24, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jp_busquedaLayout.setVerticalGroup(
            jp_busquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_busquedaLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jp_busquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtf_buscar_especifico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jb_ver_proyectos.setText("Ver proyectos");
        jb_ver_proyectos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jb_ver_proyectosMouseClicked(evt);
            }
        });

        jb_ver_usuaros.setText("Ver usuarios");
        jb_ver_usuaros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jb_ver_usuarosMouseClicked(evt);
            }
        });

        jb_ver_articulos.setText("Ver articulos");
        jb_ver_articulos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jb_ver_articulosMouseClicked(evt);
            }
        });

        jb_salir.setText("Salir");
        jb_salir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jb_salirMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jp_botonesLayout = new javax.swing.GroupLayout(jp_botones);
        jp_botones.setLayout(jp_botonesLayout);
        jp_botonesLayout.setHorizontalGroup(
            jp_botonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_botonesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jp_botonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_botonesLayout.createSequentialGroup()
                        .addGroup(jp_botonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jp_botonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jb_ver_articulos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jb_ver_usuaros, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
                            .addComponent(jb_ver_proyectos, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_botonesLayout.createSequentialGroup()
                        .addComponent(jb_salir)
                        .addContainerGap())))
        );
        jp_botonesLayout.setVerticalGroup(
            jp_botonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_botonesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jb_salir)
                .addGap(52, 52, 52)
                .addComponent(jb_ver_proyectos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jb_ver_usuaros)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jb_ver_articulos)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jl_detalle.setText("Asignación");

        jl_id_proyecto.setText("ID proyecto:");

        jtf_no_proyecto.setEditable(false);
        jtf_no_proyecto.setText("0");

        jtf_id_articulo.setEditable(false);
        jtf_id_articulo.setText("0");

        jl_requerido.setText("ID artículo:");

        jl_cantidad.setText("Cantidad:");

        jtf_cantidad.setEditable(false);
        jtf_cantidad.setText("0");

        jl_id_user.setText("ID usuario:");

        jtf_id_usuario.setEditable(false);
        jtf_id_usuario.setText("0");

        jl_conteo.setText("Conteo:");

        jtf_conteo.setEditable(false);
        jtf_conteo.setText("   ");

        javax.swing.GroupLayout jp_detallesLayout = new javax.swing.GroupLayout(jp_detalles);
        jp_detalles.setLayout(jp_detallesLayout);
        jp_detallesLayout.setHorizontalGroup(
            jp_detallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_detallesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_detallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_detallesLayout.createSequentialGroup()
                        .addComponent(jl_id_proyecto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtf_no_proyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jl_detalle)
                    .addGroup(jp_detallesLayout.createSequentialGroup()
                        .addComponent(jl_cantidad)
                        .addGap(24, 24, 24)
                        .addComponent(jtf_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(42, 42, 42)
                .addGroup(jp_detallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_detallesLayout.createSequentialGroup()
                        .addComponent(jl_id_user)
                        .addGap(18, 18, 18)
                        .addComponent(jtf_id_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jp_detallesLayout.createSequentialGroup()
                        .addComponent(jl_requerido, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtf_id_articulo, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jl_conteo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtf_conteo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jp_detallesLayout.setVerticalGroup(
            jp_detallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_detallesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jl_detalle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jp_detallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl_id_proyecto)
                    .addComponent(jtf_no_proyecto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jl_requerido)
                    .addComponent(jtf_id_articulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jl_conteo)
                    .addComponent(jtf_conteo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jp_detallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_detallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jl_cantidad)
                        .addComponent(jtf_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jl_id_user))
                    .addComponent(jtf_id_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(291, 291, 291))
        );

        jt_v_usuarios = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int ColumnIndex){
                return false;
            }
        };
        jt_v_usuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Usuario"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jt_v_usuarios);

        javax.swing.GroupLayout jp_usuariosLayout = new javax.swing.GroupLayout(jp_usuarios);
        jp_usuarios.setLayout(jp_usuariosLayout);
        jp_usuariosLayout.setHorizontalGroup(
            jp_usuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_usuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jp_usuariosLayout.setVerticalGroup(
            jp_usuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_usuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jt_v_proyecto = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int ColumnIndex){
                return false;
            }
        };
        jt_v_proyecto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Proyectos"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jt_v_proyecto);

        javax.swing.GroupLayout jp_proyectosLayout = new javax.swing.GroupLayout(jp_proyectos);
        jp_proyectos.setLayout(jp_proyectosLayout);
        jp_proyectosLayout.setHorizontalGroup(
            jp_proyectosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_proyectosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jp_proyectosLayout.setVerticalGroup(
            jp_proyectosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_proyectosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jt_v_articulos = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int ColumnIndex){
                return false;
            }
        };
        jt_v_articulos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Articulos", "Existencias"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(jt_v_articulos);

        javax.swing.GroupLayout jp_articulosLayout = new javax.swing.GroupLayout(jp_articulos);
        jp_articulos.setLayout(jp_articulosLayout);
        jp_articulosLayout.setHorizontalGroup(
            jp_articulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_articulosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                .addContainerGap())
        );
        jp_articulosLayout.setVerticalGroup(
            jp_articulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_articulosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jb_eliminar.setText("Eliminar");
        jb_eliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jb_eliminarMouseClicked(evt);
            }
        });

        jb_asignar.setText("Asignar");
        jb_asignar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jb_asignarMouseClicked(evt);
            }
        });

        jb_cancelar.setText("Cancelar");
        jb_cancelar.setEnabled(false);
        jb_cancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jb_cancelarMouseClicked(evt);
            }
        });

        jb_guardar.setText("Guardar ");
        jb_guardar.setEnabled(false);
        jb_guardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jb_guardarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jb_cancelar)
                    .addComponent(jb_guardar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jb_asignar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jb_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jb_guardar)
                    .addComponent(jb_asignar))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jb_cancelar)
                    .addComponent(jb_eliminar))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jp_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jp_botones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jp_usuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jp_proyectos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jp_detalles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jp_articulos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(26, 26, 26))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jp_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jp_botones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jp_detalles, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jp_usuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jp_proyectos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jp_articulos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jb_ver_proyectosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jb_ver_proyectosMouseClicked
        abrir_proyectos(true);
    }//GEN-LAST:event_jb_ver_proyectosMouseClicked

    private void jb_ver_usuarosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jb_ver_usuarosMouseClicked
        abrir_usuarios(true);
    }//GEN-LAST:event_jb_ver_usuarosMouseClicked

    private void jb_ver_articulosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jb_ver_articulosMouseClicked
        abrir_articulos(true);
    }//GEN-LAST:event_jb_ver_articulosMouseClicked

    private void jb_asignarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jb_asignarMouseClicked
        vacio();
        botones(true);
        botones_p(false);
    }//GEN-LAST:event_jb_asignarMouseClicked

    private void jb_cancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jb_cancelarMouseClicked
        botones(false);
        botones_p(true);
        inicio();
    }//GEN-LAST:event_jb_cancelarMouseClicked

    private void jb_guardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jb_guardarMouseClicked
        guardar();
        inicio();
        Tabla();
        botones(false);
        botones_p(true);
    }//GEN-LAST:event_jb_guardarMouseClicked

    private void jb_eliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jb_eliminarMouseClicked
        borrar();
        Tabla();
        inicio();
    }//GEN-LAST:event_jb_eliminarMouseClicked

    private void jt_resultadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_resultadosMouseClicked
        try {
            int numero;
            Object cod;
            numero = jt_resultados.getSelectedRow();
            cod = jt_resultados.getValueAt(numero, 0);
            System.out.println(cod);
            String sql = ("Select * from usar left outer join proyecto on usar.no_proyecto = proyecto.id_p " +
                    "left outer join existencias on usar.requerido = existencias.codigo " + 
                    "left outer join usuario on usar.id_user = usuario.id where conteo ="+cod+";");
            rs = st.executeQuery(sql);
            
            this.jtf_cantidad.setText(rs.getString("usar.cantidad"));
            this.jtf_no_proyecto.setText(rs.getString("no_proyecto"));
            this.jtf_id_usuario.setText(rs.getString("usuario.id"));
            this.jtf_id_articulo.setText(rs.getString("existencias.codigo"));
            this.jtf_conteo.setText(rs.getString("conteo"));
            
            rs=st.executeQuery("Select * from usar;"); 
            rs.next();
        } catch (SQLException ex) {
             
        }inicio();
    }//GEN-LAST:event_jt_resultadosMouseClicked

    private void jb_salirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jb_salirMouseClicked
         if(Admin.frpro == null)
       {
         Admin.frpro = new Proyecto();
         Admin.frpro.setVisible(true);
         Proyecto.fraspro = null;
         this.dispose();
       }
    }//GEN-LAST:event_jb_salirMouseClicked

    private void jtf_buscar_especificoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtf_buscar_especificoKeyPressed
        String[] columnas = {"Conteo","Cod Proyecto","Nombre", "Artículo", "Tipo","Cantidad","Empleado"};
        String[] busqueda = new String[30];
        String sql = "select conteo, no_proyecto, nombre_proyecto, existencias.nombre, clase, usar.cantidad, username " +
"from usar left outer join proyecto on usar.no_proyecto = proyecto.id_p " +
"left outer join existencias on usar.requerido = existencias.codigo " +
"left outer join usuario on usar.id_user = usuario.id where nombre_proyecto like '%"+jtf_buscar_especifico.getText()+"%'"
                + " OR existencias.nombre like '%"+jtf_buscar_especifico.getText()+"%'"
                + " OR clase like '%"+jtf_buscar_especifico.getText()+"%'"
                + " OR username like '%"+jtf_buscar_especifico.getText()+"%'";
 
        DefaultTableModel modelor = new DefaultTableModel(null, columnas);
        Database db = new Database();
        Connection conexion = getConectar();
        try{
           Statement st = (Statement) conexion.createStatement();
           ResultSet rs = st.executeQuery(sql);
           while (rs.next()){
             busqueda[0] = rs.getString("conteo");
             busqueda[1] = rs.getString("no_proyecto");
             busqueda[2] = rs.getString("proyecto.nombre_proyecto");
             busqueda[3] = rs.getString("existencias.nombre");
             busqueda[4] = rs.getString("clase");
             busqueda[5] = rs.getString("usar.cantidad");
             busqueda[6] = rs.getString("username");
             modelor.addRow(busqueda);
           }
           jt_resultados.setModel(modelor);
        }catch(Exception e){}
    }//GEN-LAST:event_jtf_buscar_especificoKeyPressed

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
            java.util.logging.Logger.getLogger(Asignar_proyectos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Asignar_proyectos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Asignar_proyectos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Asignar_proyectos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Asignar_proyectos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton jb_asignar;
    private javax.swing.JButton jb_cancelar;
    private javax.swing.JButton jb_eliminar;
    private javax.swing.JButton jb_guardar;
    private javax.swing.JButton jb_salir;
    private javax.swing.JButton jb_ver_articulos;
    private javax.swing.JButton jb_ver_proyectos;
    private javax.swing.JButton jb_ver_usuaros;
    private javax.swing.JLabel jl_cantidad;
    private javax.swing.JLabel jl_conteo;
    private javax.swing.JLabel jl_detalle;
    private javax.swing.JLabel jl_id_proyecto;
    private javax.swing.JLabel jl_id_user;
    private javax.swing.JLabel jl_requerido;
    private javax.swing.JPanel jp_articulos;
    private javax.swing.JPanel jp_botones;
    private javax.swing.JPanel jp_busqueda;
    private javax.swing.JPanel jp_detalles;
    private javax.swing.JPanel jp_proyectos;
    private javax.swing.JPanel jp_usuarios;
    private javax.swing.JTable jt_resultados;
    private javax.swing.JTable jt_v_articulos;
    private javax.swing.JTable jt_v_proyecto;
    private javax.swing.JTable jt_v_usuarios;
    private javax.swing.JTextField jtf_buscar_especifico;
    private javax.swing.JTextField jtf_cantidad;
    private javax.swing.JTextField jtf_conteo;
    private javax.swing.JTextField jtf_id_articulo;
    private javax.swing.JTextField jtf_id_usuario;
    private javax.swing.JTextField jtf_no_proyecto;
    // End of variables declaration//GEN-END:variables
}
