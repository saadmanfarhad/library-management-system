/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lms;
import java.util.*;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Sunef
 */
public class Open_Account extends javax.swing.JFrame {

    public String name;
    public int id;
    public int count=0;
    public String dept;
    public String desig;
    public String contact;
    public String email;
    public String address;
    public String u_name;
    public String p_word;
    public String cp_word;
    /**
     * Creates new form Admin_Add_Members
     */
    public Open_Account() {
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Name = new javax.swing.JLabel();
        ID = new javax.swing.JLabel();
        Department = new javax.swing.JLabel();
        Designation = new javax.swing.JLabel();
        Address = new javax.swing.JLabel();
        Contact_No = new javax.swing.JLabel();
        Email = new javax.swing.JLabel();
        UName_Sign_In = new javax.swing.JLabel();
        Password_Sign_In = new javax.swing.JLabel();
        Name_value = new javax.swing.JTextField();
        ID_value = new javax.swing.JTextField();
        Address_value = new javax.swing.JTextField();
        Contact_No_value = new javax.swing.JTextField();
        Email_value = new javax.swing.JTextField();
        UName_Sign_In_val = new javax.swing.JTextField();
        Button_Sign_In = new javax.swing.JButton();
        Button_Cancel = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        Password_Sign_In_val = new javax.swing.JPasswordField();
        Department_value = new javax.swing.JTextField();
        Designation_value = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(280, 120));
        setMinimumSize(new java.awt.Dimension(800, 480));
        setPreferredSize(new java.awt.Dimension(800, 480));
        setResizable(false);

        jPanel1.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        jPanel1.setMinimumSize(new java.awt.Dimension(1652, 1000));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 480));
        jPanel1.setRequestFocusEnabled(false);
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Lucida Calligraphy", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Sign In");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(363, 35, 75, 25);

        Name.setForeground(new java.awt.Color(255, 255, 255));
        Name.setText("Name");
        jPanel1.add(Name);
        Name.setBounds(39, 93, 70, 16);

        ID.setForeground(new java.awt.Color(255, 255, 255));
        ID.setText("ID No.");
        ID.setToolTipText("Enter Student_ID if you are a student, else enter your Employee_ID");
        jPanel1.add(ID);
        ID.setBounds(39, 128, 70, 16);

        Department.setForeground(new java.awt.Color(255, 255, 255));
        Department.setText("Department");
        jPanel1.add(Department);
        Department.setBounds(39, 164, 70, 16);

        Designation.setForeground(new java.awt.Color(255, 255, 255));
        Designation.setText("Designation");
        jPanel1.add(Designation);
        Designation.setBounds(39, 200, 70, 16);

        Address.setForeground(new java.awt.Color(255, 255, 255));
        Address.setText("Address");
        jPanel1.add(Address);
        Address.setBounds(39, 236, 64, 16);

        Contact_No.setForeground(new java.awt.Color(255, 255, 255));
        Contact_No.setText("Contact No.");
        jPanel1.add(Contact_No);
        Contact_No.setBounds(39, 272, 64, 16);

        Email.setForeground(new java.awt.Color(255, 255, 255));
        Email.setText("E-Mail ID");
        jPanel1.add(Email);
        Email.setBounds(39, 308, 64, 16);

        UName_Sign_In.setForeground(new java.awt.Color(255, 255, 255));
        UName_Sign_In.setText("Username");
        jPanel1.add(UName_Sign_In);
        UName_Sign_In.setBounds(430, 230, 59, 16);

        Password_Sign_In.setForeground(new java.awt.Color(255, 255, 255));
        Password_Sign_In.setText("Password");
        jPanel1.add(Password_Sign_In);
        Password_Sign_In.setBounds(430, 270, 56, 16);

        Name_value.setOpaque(true);
        Name_value.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Name_valueActionPerformed(evt);
            }
        });
        jPanel1.add(Name_value);
        Name_value.setBounds(127, 87, 232, 28);

        ID_value.setOpaque(true);
        ID_value.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ID_valueActionPerformed(evt);
            }
        });
        jPanel1.add(ID_value);
        ID_value.setBounds(127, 122, 232, 28);

        Address_value.setOpaque(true);
        Address_value.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Address_valueActionPerformed(evt);
            }
        });
        jPanel1.add(Address_value);
        Address_value.setBounds(127, 229, 232, 28);

        Contact_No_value.setOpaque(true);
        Contact_No_value.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Contact_No_valueActionPerformed(evt);
            }
        });
        jPanel1.add(Contact_No_value);
        Contact_No_value.setBounds(127, 265, 232, 28);

        Email_value.setOpaque(true);
        Email_value.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Email_valueActionPerformed(evt);
            }
        });
        jPanel1.add(Email_value);
        Email_value.setBounds(127, 302, 232, 28);

        UName_Sign_In_val.setOpaque(true);
        UName_Sign_In_val.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UName_Sign_In_valActionPerformed(evt);
            }
        });
        jPanel1.add(UName_Sign_In_val);
        UName_Sign_In_val.setBounds(510, 220, 200, 28);

        Button_Sign_In.setBackground(new java.awt.Color(0, 153, 255));
        Button_Sign_In.setForeground(new java.awt.Color(255, 255, 255));
        Button_Sign_In.setMnemonic('r');
        Button_Sign_In.setText("Create");
        Button_Sign_In.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_Sign_InActionPerformed(evt);
            }
        });
        jPanel1.add(Button_Sign_In);
        Button_Sign_In.setBounds(267, 410, 125, 28);

        Button_Cancel.setBackground(new java.awt.Color(204, 0, 0));
        Button_Cancel.setForeground(new java.awt.Color(255, 255, 255));
        Button_Cancel.setMnemonic('c');
        Button_Cancel.setText("Cancel");
        Button_Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_CancelActionPerformed(evt);
            }
        });
        jPanel1.add(Button_Cancel);
        Button_Cancel.setBounds(442, 410, 125, 28);
        jPanel1.add(jSeparator1);
        jSeparator1.setBounds(0, 66, 830, 2);

        jLabel8.setForeground(new java.awt.Color(255, 0, 0));
        jLabel8.setText("*");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(720, 220, 29, 30);

        jLabel9.setForeground(new java.awt.Color(255, 0, 0));
        jLabel9.setText("*");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(720, 260, 29, 30);

        Password_Sign_In_val.setOpaque(true);
        Password_Sign_In_val.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Password_Sign_In_valActionPerformed(evt);
            }
        });
        jPanel1.add(Password_Sign_In_val);
        Password_Sign_In_val.setBounds(510, 260, 200, 28);

        Department_value.setOpaque(true);
        Department_value.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Department_valueActionPerformed(evt);
            }
        });
        jPanel1.add(Department_value);
        Department_value.setBounds(130, 160, 232, 28);

        Designation_value.setOpaque(true);
        Designation_value.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Designation_valueActionPerformed(evt);
            }
        });
        jPanel1.add(Designation_value);
        Designation_value.setBounds(127, 194, 232, 28);

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lms/Login.jpg"))); // NOI18N
        jLabel12.setText("jLabel12");
        jLabel12.setMinimumSize(new java.awt.Dimension(800, 480));
        jLabel12.setPreferredSize(new java.awt.Dimension(800, 480));
        jPanel1.add(jLabel12);
        jLabel12.setBounds(-40, 0, 830, 480);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 829, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Button_CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_CancelActionPerformed
        // TODO add your handling code here:
        this.dispose();
        Login log=new Login();
        log.setVisible(true);
    }//GEN-LAST:event_Button_CancelActionPerformed

    @SuppressWarnings("deprecation")
    private void Button_Sign_InActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_Sign_InActionPerformed
        // TODO add your handling code here:
        getValue();
        DB_Connect.SignIn(id,name,dept,address,email,contact,desig,u_name,p_word);
        this.dispose();
        Home_User home_user=new Home_User();
        home_user.setVisible(true);
    }//GEN-LAST:event_Button_Sign_InActionPerformed
    @SuppressWarnings("deprecation")
    public void getValue(){
        dept=Department_value.getText();
        desig=Designation_value.getText();
        address=Address_value.getText();
        contact=Contact_No_value.getText();
        email=Email_value.getText();
        u_name=UName_Sign_In_val.getText();
        p_word=Password_Sign_In_val.getText();
        id=Integer.parseInt(ID_value.getText());
        name=Name_value.getText(); 
    }
    @SuppressWarnings("deprecation")
    public void getPassword(){
        p_word=Password_Sign_In_val.getText();
      
    }
    private void Department_valueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Department_valueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Department_valueActionPerformed

    private void Designation_valueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Designation_valueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Designation_valueActionPerformed

    private void Address_valueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Address_valueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Address_valueActionPerformed

    private void Contact_No_valueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Contact_No_valueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Contact_No_valueActionPerformed

    private void Email_valueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Email_valueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Email_valueActionPerformed

    private void UName_Sign_In_valActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UName_Sign_In_valActionPerformed
        // TODO add your handling code here: 
    }//GEN-LAST:event_UName_Sign_In_valActionPerformed

    @SuppressWarnings("deprecation")
    private void Password_Sign_In_valActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Password_Sign_In_valActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Password_Sign_In_valActionPerformed

    private void ID_valueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ID_valueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ID_valueActionPerformed

    private void Name_valueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Name_valueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Name_valueActionPerformed

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Address;
    private javax.swing.JTextField Address_value;
    private javax.swing.JButton Button_Cancel;
    private javax.swing.JButton Button_Sign_In;
    private javax.swing.JLabel Contact_No;
    private javax.swing.JTextField Contact_No_value;
    private javax.swing.JLabel Department;
    private javax.swing.JTextField Department_value;
    private javax.swing.JLabel Designation;
    private javax.swing.JTextField Designation_value;
    private javax.swing.JLabel Email;
    private javax.swing.JTextField Email_value;
    private javax.swing.JLabel ID;
    private javax.swing.JTextField ID_value;
    private javax.swing.JLabel Name;
    private javax.swing.JTextField Name_value;
    private javax.swing.JLabel Password_Sign_In;
    private javax.swing.JPasswordField Password_Sign_In_val;
    private javax.swing.JLabel UName_Sign_In;
    private javax.swing.JTextField UName_Sign_In_val;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}