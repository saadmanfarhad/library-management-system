/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lms;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sunef
 */
public class ViewMembers extends javax.swing.JFrame {
    
    public String[] Columns={"ID","NAME","DEPARTMENT","ADDRESS","EMAIL_ID","CONTACT_NO","MEMBER_TYPE"};
    public DefaultTableModel dtm;
    
    /**
     * Creates new form ViewMembers
     */
    public ViewMembers() {
        initComponents();
        getdata();
    }
    public void getdata(){
        String[][] info=DB_Connect.getMemberInfo();
        dtm= new DefaultTableModel();
        dtm.setColumnIdentifiers(Columns);
        if(info.length>0){
            for(int row=0;row<info.length;row++){
                String[] rows={ info[row][0],info[row][1],info[row][2],info[row][3],info[row][4],info[row][5],info[row][6]};
                dtm.addRow(rows);//gid,title,author,copy,pubname
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"No records to view!","Empty Record", JOptionPane.INFORMATION_MESSAGE);
        }
        Table_Members.setModel(dtm);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table_Members = new javax.swing.JTable();
        Button_Back = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(280, 120));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Lucida Calligraphy", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("        Welcome to IUT Library Management System!");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(88, 29, 624, -1));

        Table_Members.setBackground(new java.awt.Color(51, 51, 51));
        Table_Members.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        Table_Members.setForeground(new java.awt.Color(255, 255, 255));
        Table_Members.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        Table_Members.setEnabled(false);
        jScrollPane1.setViewportView(Table_Members);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 780, 293));

        Button_Back.setBackground(new java.awt.Color(204, 0, 0));
        Button_Back.setForeground(new java.awt.Color(255, 255, 255));
        Button_Back.setMnemonic('b');
        Button_Back.setText("Back");
        Button_Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_BackActionPerformed(evt);
            }
        });
        jPanel1.add(Button_Back, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 436, 106, -1));

        jLabel3.setForeground(new java.awt.Color(0, 204, 204));
        jLabel3.setText("Members Information");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 70, 140, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lms/Login.jpg"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 490));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Button_BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_BackActionPerformed
        // TODO add your handling code here:
        this.dispose();
        Home_Admin home_admin=new Home_Admin();
        home_admin.setVisible(true);
    }//GEN-LAST:event_Button_BackActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Button_Back;
    private javax.swing.JTable Table_Members;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
