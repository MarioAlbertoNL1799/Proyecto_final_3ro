/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Escritorio;

/**
 *
 * @author manl_
 */
public class existencias extends javax.swing.JFrame {

    /**
     * Creates new form existencias
     */
    public existencias() {
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

        bg_clase = new javax.swing.ButtonGroup();
        jp_Descripcion = new javax.swing.JPanel();
        jl_descripcion = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jta_especificaciones = new javax.swing.JTextArea();
        jl_especificaciones = new javax.swing.JLabel();
        jtf_codigo = new javax.swing.JTextField();
        jl_codigo = new javax.swing.JLabel();
        jl_tipo = new javax.swing.JLabel();
        jl_nombre = new javax.swing.JLabel();
        jl_cantidad = new javax.swing.JLabel();
        jl_unidad_m = new javax.swing.JLabel();
        jcb_unidad_m = new javax.swing.JComboBox<>();
        jl_presentación = new javax.swing.JLabel();
        jrb_liquido = new javax.swing.JRadioButton();
        jrb_liquido1 = new javax.swing.JRadioButton();
        jtf_nombre = new javax.swing.JTextField();
        jtf_cantidad = new javax.swing.JTextField();
        jrb_Herramienta = new javax.swing.JRadioButton();
        jrb_Material = new javax.swing.JRadioButton();
        jp_Botones = new javax.swing.JPanel();
        jb_Nuevo = new javax.swing.JButton();
        jb_Modificar = new javax.swing.JButton();
        jb_Eliminar = new javax.swing.JButton();
        jb_Guardar = new javax.swing.JButton();
        jb_Salir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jl_lista = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jl_descripcion.setText("Descripción del artículo");

        jta_especificaciones.setEditable(false);
        jta_especificaciones.setColumns(20);
        jta_especificaciones.setRows(5);
        jta_especificaciones.setEnabled(false);
        jScrollPane2.setViewportView(jta_especificaciones);

        jl_especificaciones.setText("Especificaciones");

        jtf_codigo.setText("00");
        jtf_codigo.setEnabled(false);

        jl_codigo.setText("Código:");

        jl_tipo.setText("Tipo:");

        jl_nombre.setText("Nombre:");

        jl_cantidad.setText("Cantidad:");

        jl_unidad_m.setText("Unidad de medida:");

        jcb_unidad_m.setEditable(true);
        jcb_unidad_m.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Metros", "Galones", "Kilogramos", "Mililigramos", "Litros", "Mililitros", "Piezas", " " }));
        jcb_unidad_m.setEnabled(false);

        jl_presentación.setText("Presentación:");
        jl_presentación.setEnabled(false);

        jrb_liquido.setText("Sólido");
        jrb_liquido.setEnabled(false);

        jrb_liquido1.setText("Líquido");
        jrb_liquido1.setEnabled(false);

        jtf_nombre.setEnabled(false);

        jtf_cantidad.setText("0");
        jtf_cantidad.setEnabled(false);

        bg_clase.add(jrb_Herramienta);
        jrb_Herramienta.setSelected(true);
        jrb_Herramienta.setText("Herramienta");
        jrb_Herramienta.setEnabled(false);

        bg_clase.add(jrb_Material);
        jrb_Material.setText("Material");
        jrb_Material.setEnabled(false);

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
                                .addGroup(jp_DescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jl_tipo)
                                    .addGroup(jp_DescripcionLayout.createSequentialGroup()
                                        .addComponent(jl_codigo)
                                        .addGap(18, 18, 18)
                                        .addComponent(jtf_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jp_DescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jp_DescripcionLayout.createSequentialGroup()
                                        .addGap(118, 118, 118)
                                        .addComponent(jl_cantidad)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jtf_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jp_DescripcionLayout.createSequentialGroup()
                                        .addGap(116, 116, 116)
                                        .addComponent(jl_nombre)
                                        .addGap(18, 18, 18)
                                        .addComponent(jtf_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jp_DescripcionLayout.createSequentialGroup()
                                .addGroup(jp_DescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jl_unidad_m)
                                    .addComponent(jrb_Herramienta))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jp_DescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jp_DescripcionLayout.createSequentialGroup()
                                        .addComponent(jrb_Material)
                                        .addGap(71, 71, 71)
                                        .addComponent(jl_presentación))
                                    .addGroup(jp_DescripcionLayout.createSequentialGroup()
                                        .addComponent(jcb_unidad_m, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(50, 50, 50)
                                        .addComponent(jrb_liquido)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jrb_liquido1))))
                            .addComponent(jl_especificaciones)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jl_descripcion))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jp_DescripcionLayout.setVerticalGroup(
            jp_DescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_DescripcionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jl_descripcion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jp_DescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jp_DescripcionLayout.createSequentialGroup()
                        .addGroup(jp_DescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jl_codigo)
                            .addComponent(jtf_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jl_tipo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jp_DescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jl_presentación)
                            .addGroup(jp_DescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jrb_Herramienta)
                                .addComponent(jrb_Material)))
                        .addGroup(jp_DescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jp_DescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jl_unidad_m)
                                .addComponent(jcb_unidad_m, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jp_DescripcionLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jp_DescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jrb_liquido)
                                    .addComponent(jrb_liquido1)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jp_DescripcionLayout.createSequentialGroup()
                        .addGroup(jp_DescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jl_nombre)
                            .addComponent(jtf_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jp_DescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jl_cantidad)
                            .addComponent(jtf_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jl_especificaciones)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jb_Nuevo.setText("Nuevo");

        jb_Modificar.setText("Modificar");

        jb_Eliminar.setText("Eliminar");

        jb_Guardar.setText("Guardar");

        jb_Salir.setText("Salir");

        javax.swing.GroupLayout jp_BotonesLayout = new javax.swing.GroupLayout(jp_Botones);
        jp_Botones.setLayout(jp_BotonesLayout);
        jp_BotonesLayout.setHorizontalGroup(
            jp_BotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_BotonesLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jp_BotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_BotonesLayout.createSequentialGroup()
                        .addComponent(jb_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jp_BotonesLayout.createSequentialGroup()
                        .addComponent(jb_Nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jb_Guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jb_Modificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addComponent(jb_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))))
        );
        jp_BotonesLayout.setVerticalGroup(
            jp_BotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_BotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_BotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jb_Nuevo)
                    .addComponent(jb_Modificar)
                    .addComponent(jb_Eliminar)
                    .addComponent(jb_Guardar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jb_Salir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jList1);

        jl_lista.setText("Lista");

        jMenu1.setText("Menú");

        jMenuItem1.setText("Inicio");
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Ayuda");
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jp_Descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jp_Botones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jl_lista))
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jp_Descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jl_lista)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jp_Botones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            java.util.logging.Logger.getLogger(existencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(existencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(existencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(existencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new existencias().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bg_clase;
    private javax.swing.JList<String> jList1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jb_Eliminar;
    private javax.swing.JButton jb_Guardar;
    private javax.swing.JButton jb_Modificar;
    private javax.swing.JButton jb_Nuevo;
    private javax.swing.JButton jb_Salir;
    private javax.swing.JComboBox<String> jcb_unidad_m;
    private javax.swing.JLabel jl_cantidad;
    private javax.swing.JLabel jl_codigo;
    private javax.swing.JLabel jl_descripcion;
    private javax.swing.JLabel jl_especificaciones;
    private javax.swing.JLabel jl_lista;
    private javax.swing.JLabel jl_nombre;
    private javax.swing.JLabel jl_presentación;
    private javax.swing.JLabel jl_tipo;
    private javax.swing.JLabel jl_unidad_m;
    private javax.swing.JPanel jp_Botones;
    private javax.swing.JPanel jp_Descripcion;
    private javax.swing.JRadioButton jrb_Herramienta;
    private javax.swing.JRadioButton jrb_Material;
    private javax.swing.JRadioButton jrb_liquido;
    private javax.swing.JRadioButton jrb_liquido1;
    private javax.swing.JTextArea jta_especificaciones;
    private javax.swing.JTextField jtf_cantidad;
    private javax.swing.JTextField jtf_codigo;
    private javax.swing.JTextField jtf_nombre;
    // End of variables declaration//GEN-END:variables
}
