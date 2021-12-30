package vista;

import controlador.ControlDatos;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelo.Articulos;
import modelo.Clientes;
import modelo.EstadisticasClientes;
import modelo.FacturasCab;
import modelo.FacturasLin;
public class main extends javax.swing.JFrame {
    private ControlDatos control;
    private DefaultTableModel modeloArt;
    private int indiceArticulos;
    private int indiceClientes;
    private int indiceFacturas;
    public main() {
        initComponents();
        setTitle("Hibernate");
        control = new ControlDatos();
        //Se rellenan todas las tablas al iniciar el programa para evitar cargarlas al abrir su pestaña.
        rellenarTablas(0);
        rellenarTablas(1);
        rellenarTablas(2);
        rellenarTablas(3);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaArticulos = new javax.swing.JTable();
        botonAniadirArticulo = new javax.swing.JButton();
        botonBorrarArticulo = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        campoArticulosReferencia = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        campoArticulosDesc = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        campoArticulosPrecio = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        campoArticulosIva = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        campoArticulosStock = new javax.swing.JFormattedTextField();
        botonModArt = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        campoArticulosBuscarReferencia = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaClientes = new javax.swing.JTable();
        botonAniadirClientes = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        campoClientesBuscarDni = new javax.swing.JTextField();
        botonCrearEstad = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaFacturas = new javax.swing.JTable();
        botonGenerarFactura = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        campoConsultaFactura = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaEstad = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hibernate");

        tablaArticulos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Reference", "Description", "Value", "VAT Percent", "Stock"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaArticulos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaArticulosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaArticulos);
        if (tablaArticulos.getColumnModel().getColumnCount() > 0) {
            tablaArticulos.getColumnModel().getColumn(0).setResizable(false);
            tablaArticulos.getColumnModel().getColumn(1).setResizable(false);
            tablaArticulos.getColumnModel().getColumn(2).setResizable(false);
            tablaArticulos.getColumnModel().getColumn(3).setResizable(false);
            tablaArticulos.getColumnModel().getColumn(4).setResizable(false);
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 994, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
        );

        botonAniadirArticulo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        botonAniadirArticulo.setText("Add");
        botonAniadirArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAniadirArticuloActionPerformed(evt);
            }
        });

        botonBorrarArticulo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        botonBorrarArticulo.setText("Remove");
        botonBorrarArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBorrarArticuloActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Reference");

        campoArticulosReferencia.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Description");

        campoArticulosDesc.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Value");

        campoArticulosPrecio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        campoArticulosPrecio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("VAT");

        campoArticulosIva.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        campoArticulosIva.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Stock");

        campoArticulosStock.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        campoArticulosStock.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        botonModArt.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        botonModArt.setText("Modify");
        botonModArt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonModArtActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setText("Search");

        campoArticulosBuscarReferencia.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        campoArticulosBuscarReferencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                campoArticulosBuscarReferenciaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonAniadirArticulo)
                    .addComponent(botonBorrarArticulo)
                    .addComponent(botonModArt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campoArticulosDesc)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(campoArticulosPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoArticulosStock, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoArticulosReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoArticulosIva)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(332, 332, 332)
                        .addComponent(campoArticulosBuscarReferencia))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(337, 337, 337)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {campoArticulosPrecio, campoArticulosReferencia, jLabel1, jLabel2, jLabel3});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botonAniadirArticulo, botonBorrarArticulo, botonModArt});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botonAniadirArticulo)
                            .addComponent(jLabel6)
                            .addComponent(jLabel1)
                            .addComponent(campoArticulosReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(campoArticulosIva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campoArticulosBuscarReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(botonBorrarArticulo)
                                    .addComponent(jLabel2)
                                    .addComponent(campoArticulosDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botonModArt))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(campoArticulosPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(campoArticulosStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {botonAniadirArticulo, botonBorrarArticulo, botonModArt});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel1, jLabel2, jLabel3});

        jTabbedPane1.addTab("Articles", jPanel2);

        tablaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DNI", "Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tablaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaClientesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaClientes);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 994, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
        );

        botonAniadirClientes.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        botonAniadirClientes.setText("Add");
        botonAniadirClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAniadirClientesActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel8.setText("Search");

        campoClientesBuscarDni.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        campoClientesBuscarDni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                campoClientesBuscarDniKeyReleased(evt);
            }
        });

        botonCrearEstad.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        botonCrearEstad.setText("New statistic");
        botonCrearEstad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCrearEstadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonCrearEstad)
                    .addComponent(botonAniadirClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(campoClientesBuscarDni, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botonAniadirClientes, botonCrearEstad});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(botonAniadirClientes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonCrearEstad)
                    .addComponent(campoClientesBuscarDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Customers", jPanel1);

        tablaFacturas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Bill Num", "Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaFacturas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaFacturasMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tablaFacturas);
        if (tablaFacturas.getColumnModel().getColumnCount() > 0) {
            tablaFacturas.getColumnModel().getColumn(0).setResizable(false);
            tablaFacturas.getColumnModel().getColumn(1).setResizable(false);
        }

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
        );

        botonGenerarFactura.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        botonGenerarFactura.setText("Add");
        botonGenerarFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGenerarFacturaActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel10.setText("Search");

        campoConsultaFactura.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        campoConsultaFactura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                campoConsultaFacturaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botonGenerarFactura)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 777, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(campoConsultaFactura, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoConsultaFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(botonGenerarFactura))
                .addGap(0, 64, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Bills", jPanel5);

        tablaEstad.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Year", "Month Num", "Month", "DNI", "Name", "Base", "Discount Sum", "VAT Sum", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tablaEstad);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 994, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Customer Statistics", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void campoConsultaFacturaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoConsultaFacturaKeyReleased
        //Cada vez que se escribe una letra en el campo de consulta se realiza un filtro
        TableRowSorter<TableModel> ordenador = new TableRowSorter<>(((DefaultTableModel) tablaFacturas.getModel()));
        ordenador.setRowFilter(RowFilter.regexFilter(campoConsultaFactura.getText()));
        tablaFacturas.setRowSorter(ordenador);
    }//GEN-LAST:event_campoConsultaFacturaKeyReleased

    private void botonGenerarFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGenerarFacturaActionPerformed
        //Abre el dialog de nuevas facturas y ofertas
        DialogPreNuevaFactura dialogPreNuevaFactura = new DialogPreNuevaFactura(null, true);
        dialogPreNuevaFactura.setVisible(true);     
        rellenarTablas(2);
        rellenarTablas(0);
    }//GEN-LAST:event_botonGenerarFacturaActionPerformed

    private void tablaFacturasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaFacturasMouseClicked
        //Abre un dialog con los datos de la factura seleccionada
        indiceFacturas = tablaFacturas.getSelectedRow();
        FacturasCab fac = (FacturasCab) tablaFacturas.getValueAt(indiceFacturas, 0);
        DialogFacturas dialogFacturas = new DialogFacturas(null, true);
        dialogFacturas.rellenarTabla(fac);
        dialogFacturas.setVisible(true);
        rellenarTablas(2);
        rellenarTablas(0);
    }//GEN-LAST:event_tablaFacturasMouseClicked

    private void campoClientesBuscarDniKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoClientesBuscarDniKeyReleased
        //Cada vez que se escribe una letra en el campo de consulta se realiza un filtro
        TableRowSorter<TableModel> ordenador = new TableRowSorter<>(((DefaultTableModel) tablaClientes.getModel()));
        ordenador.setRowFilter(RowFilter.regexFilter(campoClientesBuscarDni.getText()));
        tablaClientes.setRowSorter(ordenador);
    }//GEN-LAST:event_campoClientesBuscarDniKeyReleased

    private void botonAniadirClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAniadirClientesActionPerformed
        //Abre el dialog para crear clientes
        DialogAniadirCliente dialogAniadirCliente = new DialogAniadirCliente(null, true);
        dialogAniadirCliente.setVisible(true);
        rellenarTablas(1);
    }//GEN-LAST:event_botonAniadirClientesActionPerformed

    private void tablaClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaClientesMouseClicked
        //Abre el dialog que muestra los datos del cliente seleccionado
        indiceClientes = tablaClientes.getSelectedRow();
        Clientes cliente = (Clientes) tablaClientes.getValueAt(indiceClientes, 0);

        DialogClientes dialogClientes = new DialogClientes(null, true);
        dialogClientes.rellenarCampos(cliente);
        dialogClientes.setVisible(true);
        rellenarTablas(1);

    }//GEN-LAST:event_tablaClientesMouseClicked

    private void campoArticulosBuscarReferenciaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoArticulosBuscarReferenciaKeyReleased
        //Cada vez que se escribe una letra en el campo de consulta se realiza un filtro
        TableRowSorter<TableModel> ordenador = new TableRowSorter<>(((DefaultTableModel) tablaArticulos.getModel()));
        ordenador.setRowFilter(RowFilter.regexFilter(campoArticulosBuscarReferencia.getText()));
        tablaArticulos.setRowSorter(ordenador);
    }//GEN-LAST:event_campoArticulosBuscarReferenciaKeyReleased

    private void botonBorrarArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBorrarArticuloActionPerformed
        if(tablaArticulos.getSelectedRow() != -1){
            Articulos art = (Articulos) tablaArticulos.getValueAt(indiceArticulos, 0);
            Set<FacturasLin> facturasAsociadas = art.getFacturasLins();
            if(facturasAsociadas.isEmpty()){
                int seleccion = JOptionPane.showConfirmDialog(
                    null, "Delete " + art.toString()+" with description: " + art.getDescripcion() + "?",
                    "Deleting product",
                    JOptionPane.YES_NO_OPTION);
                if(seleccion == JOptionPane.YES_OPTION){
                    control.borrarArticulo(art); //Este metodo borra el registro de la tabla de la base de datos
                    rellenarTablas(0);
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "No se pueden borrar articulos relacionados a facturas.");
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Select a row.");
        }
    }//GEN-LAST:event_botonBorrarArticuloActionPerformed

    private void botonAniadirArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAniadirArticuloActionPerformed
        DialogAniadirArticulos dialogArticulos = new DialogAniadirArticulos(null, true);
        indiceArticulos = tablaArticulos.getSelectedRow();
        if(indiceArticulos != -1){ //Si se selecciona un campo se rellenan los campos del dialog de articulos con sus datos
            DefaultTableModel modeloArti = (DefaultTableModel) tablaArticulos.getModel();
            Articulos articulo = (Articulos) modeloArti.getValueAt(indiceArticulos, 0);
            dialogArticulos.rellenarCampos(articulo);
        }
        dialogArticulos.setVisible(true); //Si no, simplemente se abre con los campos vacios
        rellenarTablas(0);
    }//GEN-LAST:event_botonAniadirArticuloActionPerformed

    private void tablaArticulosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaArticulosMouseClicked
        indiceArticulos = tablaArticulos.getSelectedRow();
        rellenarTextosArticulos(); //Rellena los campos de texto con los datos del articulo
    }//GEN-LAST:event_tablaArticulosMouseClicked

    private void botonCrearEstadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCrearEstadActionPerformed
        DialogEstadistica dialogEstadistica = new DialogEstadistica(null, true); //Abre el dialog para crear estadisticas
        dialogEstadistica.setVisible(true);
        rellenarTablas(3); //Se rellenan las tablas para reflejar los cambios
    }//GEN-LAST:event_botonCrearEstadActionPerformed

    private void botonModArtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonModArtActionPerformed
        indiceArticulos = tablaArticulos.getSelectedRow();
        if(indiceArticulos != -1){
            Articulos art = (Articulos) tablaArticulos.getValueAt(indiceArticulos, 0);
            if(campoArticulosDesc.getText().length() != 0){
                art.setDescripcion(campoArticulosDesc.getText());
            }
            if(campoArticulosIva.getText().length() != 0){
                art.setPorciva(BigDecimal.valueOf(Double.valueOf(campoArticulosIva.getText())));
            }
            if(campoArticulosPrecio.getText().length() != 0){
                art.setPrecio(BigDecimal.valueOf(Double.valueOf(campoArticulosPrecio.getText())));
            }
            if(campoArticulosStock.getText().length() != 0){
                art.setStock(BigDecimal.valueOf(Double.valueOf(campoArticulosStock.getText())));
            }
            control.editarArticulo(art);
            JOptionPane.showMessageDialog(null, "Product modified.");
            rellenarTablas(0);
        }
        else{
            JOptionPane.showMessageDialog(null, "Select a row.");
        }
    }//GEN-LAST:event_botonModArtActionPerformed

    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available8, stay with the default look and feel.
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
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main().setVisible(true);
            }
        });
    }
    
    //Metodos para rellenar y borrar todas las tablas pasandoles como parametro la tabla en cuestion
    // 0: articulos, 1: clientes, 2: facturas
    
    private void borrarTablas(int tabla){ 
        switch (tabla){
            case 0:
                DefaultTableModel modeloTablaArticulos = (DefaultTableModel) tablaArticulos.getModel();
                while(tablaArticulos.getRowCount() != 0){                                   
                    modeloTablaArticulos.removeRow(0);
                }
                break;
            case 1:
                DefaultTableModel modeloTablaClientes = (DefaultTableModel) tablaClientes.getModel();
                while(tablaClientes.getRowCount() != 0){                                   
                    modeloTablaClientes.removeRow(0);
                }
                break;
            case 2:
                DefaultTableModel modeloTablaFacturas = (DefaultTableModel) tablaFacturas.getModel();
                while(tablaFacturas.getRowCount() != 0){                                   
                    modeloTablaFacturas.removeRow(0);
                }
                break;
            case 3:
                DefaultTableModel modeloTablaEstad = (DefaultTableModel) tablaEstad.getModel();
                while(tablaEstad.getRowCount() != 0){                                   
                    modeloTablaEstad.removeRow(0);
                }
        }
    }
    
    private void rellenarTablas(int tabla){ 
        switch (tabla){
            case 0:
                borrarTablas(tabla);
                modeloArt = (DefaultTableModel) tablaArticulos.getModel();
                List<Articulos> listaArt = control.recibirArticulos();
                listaArt.sort(Comparator.comparing(Articulos::getReferencia));
                listaArt.forEach((art) ->{
                    modeloArt.addRow(new Object[]{art, art.getDescripcion(), art.getPrecio(), art.getPorciva(), art.getStock()});
                });
                break;
            case 1:
                borrarTablas(tabla);
                DefaultTableModel modeloTablaClientes = (DefaultTableModel) tablaClientes.getModel();
                List<Clientes> clientesOrdenados = control.recibirClientes();
                clientesOrdenados.sort(Comparator.comparing(Clientes::getDnicif));
                clientesOrdenados.forEach((client) ->{
                modeloTablaClientes.addRow(new Object[]{client, client.getNombrecli()});
                });
                break;
            case 2:
                borrarTablas(tabla);
                DefaultTableModel modeloTablaFacturas = (DefaultTableModel) tablaFacturas.getModel();
                List<FacturasCab> facturasOrdenadas = control.recibirFacturas();
                facturasOrdenadas.sort(Comparator.comparing(FacturasCab::getNumfac));
                facturasOrdenadas.forEach((fac) ->{
                modeloTablaFacturas.addRow(new Object[]{fac, fac.getFechafac()});
                });
                break;
            case 3:
                borrarTablas(tabla);
                DefaultTableModel modeloTablaEstad = (DefaultTableModel) tablaEstad.getModel();
                List<EstadisticasClientes> estadisticas = control.recibirEstad();
                estadisticas.forEach((est) -> {
                    modeloTablaEstad.addRow(new Object[]{est.getId().getAnio(), est.getId().getMesNum(), est.getMesNom(), est.getClientes().getDnicif(), est.getClientes().getNombrecli(), est.getSumaBase(), est.getSumaDtos(), est.getSumaIva(), est.getSumaTotales()});
                });
                break;
        }
    }
    
    private void rellenarTextosArticulos(){ //Rellena los campos de articulos al seleccionar un registro de la tabla de articulos
        modeloArt = (DefaultTableModel) tablaArticulos.getModel();
        Articulos art = (Articulos) modeloArt.getValueAt(indiceArticulos, 0);
        campoArticulosReferencia.setText(art.getReferencia());
        campoArticulosDesc.setText(art.getDescripcion());
        campoArticulosPrecio.setText(art.getPrecio().toString());
        campoArticulosIva.setText(art.getPorciva().toString());
        campoArticulosStock.setText(art.getStock().toString());
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAniadirArticulo;
    private javax.swing.JButton botonAniadirClientes;
    private javax.swing.JButton botonBorrarArticulo;
    private javax.swing.JButton botonCrearEstad;
    private javax.swing.JButton botonGenerarFactura;
    private javax.swing.JButton botonModArt;
    private javax.swing.JTextField campoArticulosBuscarReferencia;
    private javax.swing.JTextField campoArticulosDesc;
    private javax.swing.JFormattedTextField campoArticulosIva;
    private javax.swing.JFormattedTextField campoArticulosPrecio;
    private javax.swing.JTextField campoArticulosReferencia;
    private javax.swing.JFormattedTextField campoArticulosStock;
    private javax.swing.JTextField campoClientesBuscarDni;
    private javax.swing.JTextField campoConsultaFactura;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tablaArticulos;
    private javax.swing.JTable tablaClientes;
    private javax.swing.JTable tablaEstad;
    private javax.swing.JTable tablaFacturas;
    // End of variables declaration//GEN-END:variables
}
