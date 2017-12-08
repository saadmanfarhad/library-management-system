/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lms;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sunef
 */
public class LMS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        // TODO code application logic here
        Login login=new Login();
        login.setVisible(true);
    }
}
