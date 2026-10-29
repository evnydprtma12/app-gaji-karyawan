
package ProjekUAS;
//import ProjekUAS.DBconfig;
//import com.mysql.cj.jdbc.PreparedStatementWrapper;
//import com.sun.source.tree.CatchTree;
import java.awt.Image;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import javax.swing.ImageIcon;

public class TabelKaryawan extends javax.swing.JFrame {

    private Statement stm;
    private DriverManager dm;
    private Connection con;
    private ResultSet res;
    private String sql="";
    
    public TabelKaryawan() {
        initComponents();
        KoneksiDatabase();
        tampiltabel();
        iconkembali();
        icontbk();
        backgron();
        setLocationRelativeTo(this);
    }
    
    private void KoneksiDatabase() {
        try {
            con = DBconfig.sambungkan();
            if(con != null){
                
            }else{
                System.out.println("Koneksi Gagal");
            }
        }catch(Exception e){
            //pesan koneksi gagal
            System.out.println("Koneksi Gagal"+ e.getMessage());
        }
        
    }
    
    public void tampiltabel() {
        DefaultTableModel model =  new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("NIP");
        model.addColumn("Nama");
        model.addColumn("Jabatan");
        model.addColumn("Gaji");
        
        try {
            int runningNum = 1;
            String query = "Select * from tbl_karyawan";
            con = DBconfig.sambungkan();
            stm = con.createStatement();
            ResultSet res = stm.executeQuery(query);
            
            while (res.next()){
                model.addRow(new Object[]{
                    runningNum++,
                    res.getString("nip"),
                    res.getString("nama"),
                    res.getString("jabatan"),
                    res.getString("gaji"),
                        
                });
            }
        
            tablek.setModel(model);
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.err.println(e.getMessage());
        }
    }

    
    public void tambahdata(){
        
        try {
            con = DBconfig.sambungkan();
            stm = con.createStatement();
            String nama = jtextnama.getText();
            String nip = jtextnip.getText();
            String jabatan = jtextjabatan.getText();
            String gaji = jtextgaji.getText();
            String query = "INSERT INTO tbl_karyawan(nip,nama,jabatan,gaji)VALUES (?,?,?,?)";
            try(PreparedStatement p = con.prepareStatement(query)){
                p.setString(1,nip);
                p.setString(2,nama);
                p.setString(3,jabatan);
                p.setString(4,gaji);
                p.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Ditambahkan");
            }
            con.close();
//            tampiltabel();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Tambah Data Gagal");
        }
    }
    
    public void hapusdata(){
        try {
            con = DBconfig.sambungkan();
            stm = con.createStatement();
            int Selectedrow = tablek.getSelectedRow();
            
                Object nip = tablek.getValueAt(Selectedrow, 1);
            
            String query = "DELETE FROM tbl_karyawan where nip ='"+nip+"'";
            PreparedStatement p = con.prepareStatement(query);
            p.executeUpdate();
             
            con.close();
        } catch (Exception e) {
        }
    }
    
    public void mencaridata() {
        DefaultTableModel model =  new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("NIP");
        model.addColumn("Nama");
        model.addColumn("Jabatan");
        model.addColumn("Gaji");
        
        try {
            int runningNum = 1;
            int SelectRow = tablek.getSelectedRow();
            String nip = nipcari.getText();
            String query = "Select * from tbl_karyawan where nip = '"+nip+"'";
            con = DBconfig.sambungkan();
            stm = con.createStatement();
            ResultSet res = stm.executeQuery(query);
            
            while (res.next()){
                model.addRow(new Object[]{
                    runningNum++,
                    res.getString("nip"),
                    res.getString("nama"),
                    res.getString("jabatan"),
                    res.getString("gaji"),
                        
                });
            }
        
            tablek.setModel(model);
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.err.println(e.getMessage());
        }
    }
    
    void iconkembali(){
        ClassLoader classLoader = getClass().getClassLoader();
        URL imageUrl = classLoader.getResource("aset/back.png");
        ImageIcon imageIcon = new ImageIcon(imageUrl);
        Image mm = imageIcon.getImage();
        Image img4 = mm.getScaledInstance(40, 40,Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(img4);
        icontabelkaryawan.setIcon(image);
    }
    
    void backgron(){
        ClassLoader classLoader = getClass().getClassLoader();
        URL imageUrl = classLoader.getResource("aset/background.jpg");
        ImageIcon imageIcon = new ImageIcon(imageUrl);
        Image mm = imageIcon.getImage();
        Image img2 = mm.getScaledInstance(820, 630,Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(img2);
        jlabelbg.setIcon(image);
    }
    
    void icontbk(){
        ClassLoader classLoader = getClass().getClassLoader();
        URL imageUrl = classLoader.getResource("aset/png-transparent-wolf-logo-symbol-removebg-preview.png");
        ImageIcon imageIcon = new ImageIcon(imageUrl);
        Image mm = imageIcon.getImage();
        Image img3 = mm.getScaledInstance(50, 50,Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(img3);
        icontabelk.setIcon(image);
    }
    

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablek = new javax.swing.JTable();
        jtextnama = new javax.swing.JTextField();
        jtextnip = new javax.swing.JTextField();
        jtextjabatan = new javax.swing.JTextField();
        jtextgaji = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jbuttontambah = new javax.swing.JButton();
        jbuttonhapus = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        nipcari = new javax.swing.JTextField();
        jbuttoncaridata = new javax.swing.JButton();
        refreshdata = new javax.swing.JButton();
        refreshdata2 = new javax.swing.JButton();
        icontabelkaryawan = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        icontabelk = new javax.swing.JLabel();
        jlabelbg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablek.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "No", "NIP", "Nama", "Jabatan", "Gaji"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tablek);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 660, 260));

        jtextnama.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jPanel1.add(jtextnama, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, 160, -1));

        jtextnip.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jPanel1.add(jtextnip, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, 160, -1));
        jPanel1.add(jtextjabatan, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 160, 160, -1));

        jtextgaji.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jPanel1.add(jtextgaji, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 200, 160, -1));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("Nama");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("NIP");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 37, -1));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("Jabatan");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 160, -1, -1));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setText("Gaji");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 200, 37, -1));

        jbuttontambah.setBackground(new java.awt.Color(204, 255, 204));
        jbuttontambah.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jbuttontambah.setText("Tambah");
        jbuttontambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttontambahActionPerformed(evt);
            }
        });
        jPanel1.add(jbuttontambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 160, -1, -1));

        jbuttonhapus.setBackground(new java.awt.Color(204, 255, 204));
        jbuttonhapus.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jbuttonhapus.setText("Hapus");
        jbuttonhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonhapusActionPerformed(evt);
            }
        });
        jPanel1.add(jbuttonhapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 330, -1, -1));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel1.setText("Masukkan NIP");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, -1, -1));

        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel6.setText("Input Data Karyawan");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 110, -1, -1));

        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel7.setText("Mencari Data Karyawan");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 260, -1, -1));

        nipcari.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jPanel1.add(nipcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 290, 164, -1));

        jbuttoncaridata.setBackground(new java.awt.Color(204, 255, 204));
        jbuttoncaridata.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jbuttoncaridata.setText("Cari Data");
        jbuttoncaridata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttoncaridataActionPerformed(evt);
            }
        });
        jPanel1.add(jbuttoncaridata, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 330, -1, -1));

        refreshdata.setBackground(new java.awt.Color(204, 255, 204));
        refreshdata.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        refreshdata.setText("Refresh");
        refreshdata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshdataActionPerformed(evt);
            }
        });
        jPanel1.add(refreshdata, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 330, -1, -1));

        refreshdata2.setBackground(new java.awt.Color(204, 255, 204));
        refreshdata2.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        refreshdata2.setText("Refresh");
        refreshdata2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshdata2ActionPerformed(evt);
            }
        });
        jPanel1.add(refreshdata2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 200, 80, -1));

        icontabelkaryawan.setText("iconkembali");
        icontabelkaryawan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        icontabelkaryawan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                icontabelkaryawanMouseClicked(evt);
            }
        });
        jPanel1.add(icontabelkaryawan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 40, 40));

        jLabel12.setFont(new java.awt.Font("Goudy Old Style", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 204, 0));
        jLabel12.setText("Copyright. Kelompok 3");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 330, -1, -1));

        jPanel2.setBackground(new java.awt.Color(102, 255, 204));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 2, true));

        jLabel8.setFont(new java.awt.Font("Garamond", 1, 24)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Tabel Karyawan Perusahaan Walmart");

        icontabelk.setText("jLabel9");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(icontabelk, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(109, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(icontabelk, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 660, 90));
        jPanel1.add(jlabelbg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 660, 630));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 659, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbuttonhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonhapusActionPerformed
        // TODO add your handling code here:
        int z;
        z = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin menghapus data ?", sql, JOptionPane.YES_NO_OPTION);
        if (z == JOptionPane.YES_OPTION){
            hapusdata();
        }
        tampiltabel();
    }//GEN-LAST:event_jbuttonhapusActionPerformed

    private void jbuttoncaridataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttoncaridataActionPerformed
        // TODO add your handling code here:
        mencaridata();
    }//GEN-LAST:event_jbuttoncaridataActionPerformed

    private void refreshdataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshdataActionPerformed
        // TODO add your handling code here:
        nipcari.setText("");
        tampiltabel();
    }//GEN-LAST:event_refreshdataActionPerformed

    private void icontabelkaryawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icontabelkaryawanMouseClicked
        // TODO add your handling code here:
        new projek1().setVisible(true);
        dispose();
    }//GEN-LAST:event_icontabelkaryawanMouseClicked

    private void jbuttontambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttontambahActionPerformed
        // TODO add your handling code here:
        tambahdata();
        tampiltabel();
    }//GEN-LAST:event_jbuttontambahActionPerformed

    private void refreshdata2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshdata2ActionPerformed
        // TODO add your handling code here:
        jtextnama.setText("");
        jtextnip.setText("");
        jtextjabatan.setText("");
        jtextgaji.setText("");
    }//GEN-LAST:event_refreshdata2ActionPerformed

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
            java.util.logging.Logger.getLogger(TabelKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TabelKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TabelKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TabelKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TabelKaryawan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel icontabelk;
    private javax.swing.JLabel icontabelkaryawan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton jbuttoncaridata;
    private javax.swing.JButton jbuttonhapus;
    private javax.swing.JButton jbuttontambah;
    private javax.swing.JLabel jlabelbg;
    private javax.swing.JTextField jtextgaji;
    private javax.swing.JTextField jtextjabatan;
    private javax.swing.JTextField jtextnama;
    private javax.swing.JTextField jtextnip;
    private javax.swing.JTextField nipcari;
    private javax.swing.JButton refreshdata;
    private javax.swing.JButton refreshdata2;
    private javax.swing.JTable tablek;
    // End of variables declaration//GEN-END:variables
}
