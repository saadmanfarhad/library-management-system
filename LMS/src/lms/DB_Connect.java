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
import javax.swing.JOptionPane;

/**
 *
 * @author Sunef
 */
public class DB_Connect {
     public static Connection connection;
     
     public static PreparedStatement statement;
     public static ResultSet resultset;
     public static ResultSet resultset2;
     public static ResultSetMetaData meta;
     
     public static Statement statement2;
     
     public static boolean publisher_present=false;
     public static boolean booksgroup_present=false;
     public static int memID=1;
     public static int book_count=0;
     public static int max=5;
         
     public static Connection connectDB(){
        final String DB_URL="jdbc:oracle:thin:@localhost:1521:ORCL";	
        Connection conn=null;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn=DriverManager.getConnection(DB_URL,"SYS as SYSDBA","Sunef8121994");
           // return conn;
        }
        catch(Exception exception){
            System.out.println("Could not connect to database!!");
        }
        return conn;
    }
     
     public static void SignIn(int id,String name,String dept,String address,String email,String contact,String desig,String u_name,String p_word){
        String q1;
        String q2;
        String q3;
        
        try{
               q1="INSERT INTO LIB.MEMBERS(M_ID,M_NAME,M_DEPT,ADDRESS,EMAIL_ID,CONTACT_NO,MEMBER_TYPE) VALUES(?,?,?,?,?,?,?)";   
               
               connection=DB_Connect.connectDB();
               statement=connection.prepareStatement(q1);
               statement.setInt(1,id);
               statement.setString(2,name);
               statement.setString(3,dept);
               statement.setString(4,address);
               statement.setString(5,email);
               statement.setString(6,contact);               
               statement.setString(7,desig);
               
               int result=statement.executeUpdate();
               
               q1="INSERT INTO LIB.CLIENT(U_NAME,U_PASSWORD,U_ID) VALUES(?,?,?)";
               statement=connection.prepareStatement(q1);
               statement.setString(1,u_name);
               statement.setString(2,p_word);
               statement.setInt(3,id);
               statement.executeUpdate();
               JOptionPane.showMessageDialog(null,"Signed In Successfully!","Sign In",JOptionPane.INFORMATION_MESSAGE);
        }
        catch(Exception e){
               JOptionPane.showMessageDialog(null,e,"Error Signing In!",JOptionPane.ERROR_MESSAGE);
        }
        finally{
           try {        
                      statement.close();
                      connection.close();
               }
           catch (SQLException e) {
                JOptionPane.showMessageDialog(null,e,"Error!",JOptionPane.ERROR_MESSAGE);
           }
        }
        
     }
     public static void LogIn (String User_Type,String username,String password){
        boolean b=false;
        try{
              String query="select * from LIB."+User_Type;
              connection=DB_Connect.connectDB();
              statement2=connection.createStatement();
              resultset=statement2.executeQuery(query);
              meta=resultset.getMetaData();
              int col=meta.getColumnCount();
              
              int i;
              String[] col_name=new String[3];
              for(i=0;i<col;i++){
                      col_name[i]=meta.getColumnName(i+1);
              }
              while(resultset.next()){
                      if(username.equals(resultset.getString(col_name[0])) && password.equals(resultset.getString(col_name[1]))){
                             b=true;
                             break;
                      }
              }
              if(b==true){
                     if(User_Type.equals("Admin")){
                            Home_Admin home_admin=new Home_Admin(); 
                            home_admin.setVisible(true);
                      }
                      else{
                             memID=resultset.getInt("U_ID");
                             Home_User home=new Home_User();
                             home.setVisible(true);
                      }
              }
              else {
                      JOptionPane.showMessageDialog(null,"Invalid Information!","Login Error",JOptionPane.ERROR_MESSAGE);
               }
        }
        catch(Exception e){
               JOptionPane.showMessageDialog(null,"Invalid Information!","Login Error",JOptionPane.ERROR_MESSAGE);
        }
        finally{
           try {
                      resultset.close();
                      statement2.close();
                      connection.close();
               }
           catch (SQLException e) {
                   JOptionPane.showMessageDialog(null,e,"Error!",JOptionPane.ERROR_MESSAGE);
           }
        }
    }
     
     public static void AddBook(int PID,int BID,int BGID,int copies,String BName,String AName,String Edition){
        String q2;
        String q3;
        String test;
        int row_count=0;
     
        try{
                q3="INSERT INTO LIB.BOOKS(BOOK_ID,BOOK_GID,EDITION,ISSUED) VALUES(?,?,?,?)";  
                connection=DB_Connect.connectDB();
                int copies_present = 0,total;
                test="SELECT NO_OF_COPIES FROM LIB.BOOKS_GROUP WHERE G_ID="+BGID;
                statement2=connection.createStatement();
                resultset=statement2.executeQuery(test);
                meta=resultset.getMetaData();
                while(resultset.next()){
                    copies_present=resultset.getInt("NO_OF_COPIES");
                }

                total=copies+copies_present;
                q2="UPDATE LIB.BOOKS_GROUP SET NO_OF_COPIES=? WHERE G_ID="+BGID;

                statement=connection.prepareStatement(q2);
                statement.setInt(1, total);

              int result2=statement.executeUpdate();

              test="SELECT * FROM LIB.BOOKS";
              statement2=connection.createStatement();
              resultset=statement2.executeQuery(test);
              meta=resultset.getMetaData();

              while(resultset.next()){
                   row_count++;
                  }
              int last=row_count; 
              for(int i=1;i<=copies;i++){
                      statement=connection.prepareStatement(q3);
                      BID=last+i;
                      statement.setInt(1,BID);
                      statement.setInt(2,BGID);
                      statement.setString(3,Edition);
                      statement.setInt(4,0);
                      

                      result2=statement.executeUpdate();
                      statement.clearBatch();
                  }
              JOptionPane.showMessageDialog(null,"Books Added Successfully!","Add Books",JOptionPane.INFORMATION_MESSAGE);
        }
        catch(Exception e){
               JOptionPane.showMessageDialog(null,e,"Add Books",JOptionPane.ERROR_MESSAGE);
        }
        finally{
           try {
                      statement.close();
                      resultset.close();
                      statement2.close();
                      connection.close();
               }
           catch (SQLException ex) {
          
           }
        }
    }
     public static void AddNewBook(int BID,int PID,int BGID,String BName,String AName, int copies,String Edition){
        String q1;
        String test;
        String[] PInfo=null;
        int row_count=0; 
     
        try{
                connection=DB_Connect.connectDB();
                
                    
                test="SELECT * FROM LIB.BOOKS_GROUP";
                statement2=connection.createStatement();
                resultset=statement2.executeQuery(test);
                while(resultset.next()){
                    row_count++;
                  }
                BGID=row_count+1; 
                resultset.close();

                q1="INSERT INTO LIB.BOOKS_GROUP(G_ID,PUBLISHER_ID,TITLE,AUTHOR,NO_OF_COPIES) VALUES(?,?,?,?,?)";  
                statement=connection.prepareStatement(q1);
                statement.setInt(1, BGID);
                statement.setInt(2, PID);
                statement.setString(3, BName);
                statement.setString(4, AName);
                statement.setInt(5, copies);
                int result2=statement.executeUpdate();
                statement.clearBatch();

                test="SELECT * FROM LIB.BOOKS";
                statement2=connection.createStatement();
                resultset=statement2.executeQuery(test);
                
                row_count=0;
                while(resultset.next()){
                     row_count++;
                    }
                int last=row_count; 
                q1="INSERT INTO LIB.BOOKS(BOOK_ID,BOOK_GID,EDITION,ISSUED) VALUES(?,?,?,?)";  

                for(int i=1;i<=copies;i++){
                        statement=connection.prepareStatement(q1);
                        BID=last+i;
                        statement.setInt(1,BID);
                        statement.setInt(2,BGID);
                        statement.setString(3,Edition);
                        statement.setInt(4,0);

                        result2=statement.executeUpdate();
                        statement.clearBatch();
                    }
               JOptionPane.showMessageDialog(null,"Books Added Successfully!","Add Books",JOptionPane.INFORMATION_MESSAGE);
        }
        catch(Exception e){
               JOptionPane.showMessageDialog(null,e,"Add Books",JOptionPane.ERROR_MESSAGE);
        }
        finally{
           try {
                      statement.close();
                      resultset.close();
                      statement2.close();
                      connection.close();
               }
           catch (SQLException ex) {
                     
           }
        }
    }
     
     public static void AddPublisher(String PName,String Address,String Contact,String Email){
        String q1;
        String test;
        int row_count=0; 
     
        try{
                connection=DB_Connect.connectDB();
                
                test="SELECT * FROM LIB.PUBLISHER";
                statement2=connection.createStatement();
                resultset=statement2.executeQuery(test);
                while(resultset.next()){
                    row_count++;
                  }
                int PUBID=row_count+1; 
                resultset.close();
                
                q1="INSERT INTO LIB.PUBLISHER(P_ID,P_NAME,ADDRESS,CONTACT_NO,EMAIL_ID) VALUES(?,?,?,?,?)";  
                statement=connection.prepareStatement(q1);
                statement.setInt(1, PUBID);
                statement.setString(2,PName);
                statement.setString(3,Address);
                statement.setString(4,Contact);
                statement.setString(5,Email);
                int result2=statement.executeUpdate();
                statement.clearBatch();
                
                JOptionPane.showMessageDialog(null,"Publisher Added Successfully!","Add Publisher",JOptionPane.INFORMATION_MESSAGE);
        }
        catch(Exception e){
               JOptionPane.showMessageDialog(null,e,"Add Publisher",JOptionPane.ERROR_MESSAGE);
        }
        finally{
           try {
                      statement.close();
                      resultset.close();
                      statement2.close();
                      connection.close();
               }
           catch (SQLException ex) {
                      Logger.getLogger(DB_Connect.class.getName()).log(Level.SEVERE, null, ex);
               }
        }
    }
     public static int[] getPublisherID(){
               
         String q1="SELECT P_ID FROM LIB.PUBLISHER";
         int[] id=null;
         try{
            connection=DB_Connect.connectDB();
            statement2=connection.createStatement();
            resultset=statement2.executeQuery(q1);

            int rows=0;
            boolean b=false;
            while(resultset.next()){
                rows++;
            }
            resultset.close();
            resultset=statement2.executeQuery(q1);
            if(rows!=0){
                b=true;
                id=new int[rows];
            }
            if(b==true){
                int i=0;
                while(resultset.next()){
                    id[i]=resultset.getInt("P_ID");
                    i++;
                }
             }
         }
         catch(Exception e){
                JOptionPane.showMessageDialog(null,e,"Error",JOptionPane.ERROR_MESSAGE);
         }
         finally{
             try{
               resultset.close();
               statement2.close();
               connection.close();
             }
             catch (SQLException e) {
                         JOptionPane.showMessageDialog(null,e,"Error!",JOptionPane.ERROR_MESSAGE);
           }
         }
         return id;
     }
     
     public static int getPublisherID (int BGID){
               
         String q1="SELECT PUBLISHER_ID"
                 + " FROM LIB.BOOKS_GROUP"
                 +"  WHERE G_ID="+BGID;
         int id = 0;
         try{
            connection=DB_Connect.connectDB();
            statement2=connection.createStatement();
            resultset=statement2.executeQuery(q1);

            int i=0;
            while(resultset.next()){
                id=resultset.getInt("PUBLISHER_ID");
                i++;
            }
            return id;
         }
         catch(Exception e){
                JOptionPane.showMessageDialog(null,e,"Error",JOptionPane.ERROR_MESSAGE);
         }
         finally{
             try{
               resultset.close();
               statement2.close();
               connection.close();
             }
             catch (SQLException e) {
                      JOptionPane.showMessageDialog(null,e,"Error!",JOptionPane.ERROR_MESSAGE);
            }
         }
         return 0;
     }
     public static String getPublisherID_group (){
               
         String q1="SELECT PUBLISHER_ID"
                 + " FROM LIB.BOOKS_GROUP";
         String id = null;
         try{
            connection=DB_Connect.connectDB();
            statement2=connection.createStatement();
            resultset=statement2.executeQuery(q1);

            int i=0;
            while(resultset.next()){
                id=resultset.getString("PUBLISHER_ID");
                i++;
            }

         }
         catch(Exception e){
                JOptionPane.showMessageDialog(null,e,"Error",JOptionPane.ERROR_MESSAGE);
         }
         finally{
             try{
               resultset.close();
               statement2.close();
               connection.close();
             }
             catch (SQLException e) {
               JOptionPane.showMessageDialog(null,e,"Error!",JOptionPane.ERROR_MESSAGE);
              }
         }
         return id;
     }
     
     public static String[] getPublisherName(){
               
         String q1="SELECT P_NAME FROM LIB.PUBLISHER";
         String[] names=null;

         try{
            connection=DB_Connect.connectDB();
            statement2=connection.createStatement();
            resultset=statement2.executeQuery(q1);
            int rows=0;
            boolean b=false;
            while(resultset.next()){
                rows++;
            }
            resultset.close();
            resultset=statement2.executeQuery(q1);
            
            if(rows!=0){
                b=true;
                names=new String[rows];
            }
            if(b==true){
                int i=0;
                while(resultset.next()){
                    names[i]=resultset.getString("P_NAME");
                    i++;
                }
             }
         }
         catch(Exception e){
             JOptionPane.showMessageDialog(null,e,"Error",JOptionPane.ERROR_MESSAGE);
         }
         finally{
             try{
               resultset.close();
               statement2.close();
               connection.close();
             }
             catch (SQLException e) {
                  JOptionPane.showMessageDialog(null,e,"Error!",JOptionPane.ERROR_MESSAGE);
           }
         }            
       return names;
}
     public static String[] getPublisherInfo(int PID){
               
         String q1="SELECT * "
                 + "FROM LIB.PUBLISHER "
                 + "WHERE P_ID="+PID;
         String[] info=null;

         try{
            connection=DB_Connect.connectDB();
            statement2=connection.createStatement();
            resultset=statement2.executeQuery(q1);
            meta=resultset.getMetaData();
            int col=meta.getColumnCount();
            info=new String[col];
                while(resultset.next()){
                  for(int i=0;i<col;i++){
                      info[i]=resultset.getString(i+1).toString();
                  }
               }
            return info;
         }
         catch(Exception e){
             JOptionPane.showMessageDialog(null,e,"Error",JOptionPane.ERROR_MESSAGE);
         }
         finally{
             try{
               resultset.close();
               statement2.close();
               connection.close();
             }
             catch (SQLException e) {
                          JOptionPane.showMessageDialog(null,e,"Error!",JOptionPane.ERROR_MESSAGE);
           }
         }            
       return null;
}
    
     public static String[] getBookName(){

           String q1="SELECT TITLE FROM LIB.BOOKS_GROUP";
           String[] bnames=null;

           try{
              connection=DB_Connect.connectDB();
              statement2=connection.createStatement();
              resultset=statement2.executeQuery(q1);
              int rows=0;
              boolean b=false;
              while(resultset.next()){
                  rows++;
              }
              resultset.close();
              resultset=statement2.executeQuery(q1);

              if(rows!=0){
                  b=true;
                  bnames=new String[rows];
              }
              if(b==true){
                  int i=0;
                  while(resultset.next()){
                      bnames[i]=resultset.getString("TITLE");
                      System.out.println(bnames[i]);
                      i++;
                  }
               }
              return bnames;
           }
           catch(Exception e){
               JOptionPane.showMessageDialog(null,e,"Error",JOptionPane.ERROR_MESSAGE);
           }
           finally{
               try{
                 resultset.close();
                 statement2.close();
                 connection.close();
               }
               catch (SQLException e) {
                           JOptionPane.showMessageDialog(null,e,"Error!",JOptionPane.ERROR_MESSAGE);
            }
           }            
         return null;
  }
     public static String[][] getBookInfo(){
           String q1="SELECT * FROM LIB.BOOKS_GROUP";
           String[] pid=null;
           String[][] info=null;
           
           try{
              connection=DB_Connect.connectDB();
              statement2=connection.createStatement();
              resultset=statement2.executeQuery(q1);
              int rows=0;
              boolean b=false;
              while(resultset.next()){
                  rows++;
              }
              resultset.close();
              
              
                resultset=statement2.executeQuery(q1);
              if(rows!=0){
                  b=true;
                  pid=new String[rows];
                  info=new String[rows][5];
              }
              
              if(b==true){
                  int row=0;
                  
                  while(resultset.next()){
                         info[row][0]=resultset.getString("G_ID");
                         pid[row]=resultset.getString("PUBLISHER_ID");
                         info[row][2]=resultset.getString("TITLE");
                         info[row][3]=resultset.getString("AUTHOR");
                         info[row][4]=resultset.getString("NO_OF_COPIES");
                         row++;            
                   }
                   resultset.close();
                   String q2="SELECT P_ID,P_NAME"
                                 + " FROM LIB.PUBLISHER";
                  resultset=statement2.executeQuery(q2);
                  while(resultset.next()){
                      for(int i=0;i<pid.length;i++){
                          if(pid[i].equals(resultset.getString("P_ID"))){
                            info[i][1]=resultset.getString("P_NAME");
                          }
                      }
                  }
               }
           }
           catch(Exception e){
               JOptionPane.showMessageDialog(null,e,"Error",JOptionPane.ERROR_MESSAGE);
           }
           finally{
               try{
                 resultset.close();
                 statement2.close();
                 connection.close();
               }
               catch (SQLException e) {
               JOptionPane.showMessageDialog(null,e,"Error!",JOptionPane.ERROR_MESSAGE);
                }
           }            
         return info;
     }
     
     public static String[][] getVAL(String val,int choice){
       String q1="SELECT G_ID,TITLE,AUTHOR,PUBLISHER.P_NAME,NO_OF_COPIES FROM LIB.BOOKS_GROUP,LIB.PUBLISHER WHERE AUTHOR LIKE '%"+val+"%' AND PUBLISHER_ID=P_ID";
       String q2="SELECT G_ID,TITLE,AUTHOR,PUBLISHER.P_NAME,NO_OF_COPIES FROM LIB.BOOKS_GROUP,LIB.PUBLISHER WHERE  TITLE LIKE '%"+val+"%' AND PUBLISHER_ID=P_ID";
       String q3="SELECT G_ID,TITLE,AUTHOR,PUBLISHER.P_NAME,NO_OF_COPIES FROM LIB.BOOKS_GROUP,LIB.PUBLISHER WHERE P_NAME LIKE '%"+val+"%' AND PUBLISHER.P_ID=BOOKS_GROUP.PUBLISHER_ID";
           String[][] bnames=null;

           try{
              connection=DB_Connect.connectDB();
              statement2=connection.createStatement();
              switch(choice){
                  case 1:
                     resultset=statement2.executeQuery(q1);
                     break;
                  case 2:
                     resultset=statement2.executeQuery(q2);
                      break;
                  case 3:
                     resultset=statement2.executeQuery(q3);
                      break;
              }
               meta=resultset.getMetaData();
              int rows=0;
              boolean b=false;
              while(resultset.next()){
                  rows++;
              }
              resultset.close();
              switch(choice){
                  case 1:
                     resultset=statement2.executeQuery(q1);
                     break;
                  case 2:
                     resultset=statement2.executeQuery(q2);
                      break;
                  case 3:
                     resultset=statement2.executeQuery(q3);
                      break;
              }
              if(rows!=0){
                  b=true;
                  bnames=new String[rows][5];
              }
              if(b==true){
                  int i=0;
                  while(resultset.next()){
                      bnames[i][0]=resultset.getString("G_ID");
                      bnames[i][1]=resultset.getString("TITLE");
                      bnames[i][2]=resultset.getString("AUTHOR");
                      bnames[i][3]=resultset.getString("P_NAME");
                      bnames[i][4]=resultset.getString("NO_OF_COPIES");
                      i++;
                  }
               }
              
              return bnames;
           }
           catch(Exception e){
               JOptionPane.showMessageDialog(null,e,"Error",JOptionPane.ERROR_MESSAGE);
           }
           finally{
               try{
                 resultset.close();
                 statement2.close();
                 connection.close();
               }
               catch (SQLException e) {
                         JOptionPane.showMessageDialog(null,e,"Error!",JOptionPane.ERROR_MESSAGE);
            }
           }            
         return null;
  
     }
     public static String[][] getMemberInfo(){
           String q1="SELECT * FROM LIB.MEMBERS";
           String[][] info=null;
           
           try{
              connection=DB_Connect.connectDB();
              statement2=connection.createStatement();
              resultset=statement2.executeQuery(q1);
              int rows=0;
              boolean b=false;
              while(resultset.next()){
                  rows++;
              }
              resultset.close();
              resultset=statement2.executeQuery(q1);
              if(rows!=0){
                  b=true;
                  info=new String[rows][7];
              }
              
              if(b==true){
                  int row=0;
                  while(resultset.next()){
                         info[row][0]=resultset.getString("M_ID");
                         info[row][1]=resultset.getString("M_NAME");
                         info[row][2]=resultset.getString("M_DEPT");
                         info[row][3]=resultset.getString("ADDRESS");
                         info[row][4]=resultset.getString("EMAIL_ID");
                         info[row][5]=resultset.getString("CONTACT_NO");
                         info[row][6]=resultset.getString("MEMBER_TYPE");
                         row++;            
                   }
               }
              return info;
           }
           catch(Exception e){
               JOptionPane.showMessageDialog(null,e,"Error",JOptionPane.ERROR_MESSAGE);
           }
           finally{
               try{
                 resultset.close();
                 statement2.close();
                 connection.close();
               }
               catch (SQLException e) {
                         JOptionPane.showMessageDialog(null,e,"Error!",JOptionPane.ERROR_MESSAGE);
             }
           }            
         return null;
     }
     
     public static void Issue(String[] table_info){
         int i=0;
        
            String q1="SELECT NO_OF_COPIES FROM LIB.BOOKS_GROUP WHERE G_ID="+table_info[0];
            int copies=0;
            int BID=0;
            int check_group=0;
            try{
                connection=DB_Connect.connectDB();
                book_count=0;  
                String check="SELECT * FROM LIB.BORROWED_BY WHERE MEM_ID="+memID;
                statement2=connection.createStatement();
                resultset=statement2.executeQuery(check);
                
                while(resultset.next()){
                    ++book_count;
                } 
                resultset.close();
                if(book_count!=max){
                        check="SELECT * FROM LIB.BORROWED_BY WHERE MEM_ID="+memID+" AND BG_ID="+table_info[0];
                        statement2=connection.createStatement();
                        resultset=statement2.executeQuery(check);

                        if(resultset.next()){
                            JOptionPane.showMessageDialog(null,"You have already issued a book of group!!","Multiple book issue",JOptionPane.ERROR_MESSAGE);
                            resultset.close();
                        }

                        else{
                            String q2="SELECT BOOK_ID FROM LIB.BOOKS WHERE BOOK_GID="+table_info[0]+" AND ISSUED="+0;

                            statement2=connection.createStatement();
                            resultset=statement2.executeQuery(q1);

                            int rows=0;
                            if(resultset.next()){
                                copies=resultset.getInt("NO_OF_COPIES");
                            }
                            resultset.close();
                            if(copies>0){
                                --copies;
                                ++book_count;
                                statement2=connection.createStatement();
                                resultset=statement2.executeQuery(q2);

                                if(resultset.next()){
                                    BID=resultset.getInt("BOOK_ID");
                                }
                                System.out.println(BID);
                                q1="UPDATE LIB.BOOKS_GROUP SET NO_OF_COPIES=? WHERE G_ID=" +table_info[0];  
                                statement=connection.prepareStatement(q1);    
                                statement.setInt(1,copies);
                                statement.executeUpdate();
                                statement.close();
                                q1="UPDATE LIB.BOOKS SET ISSUED=? WHERE BOOK_GID="+table_info[0]+" AND BOOK_ID="+BID;
                                statement=connection.prepareStatement(q1); 
                                statement.setInt(1, 1);
                                statement.executeUpdate();
                                statement.close();
                                int gid=Integer.parseInt(table_info[0]);
                                q1="INSERT INTO LIB.BORROWED_BY(B_ID,MEM_ID,BG_ID) VALUES(?,?,?)";  
                                statement=connection.prepareStatement(q1);
                                statement.setInt(1, BID);
                                statement.setInt(2, memID);
                                statement.setInt(3,gid);
                                statement.executeUpdate();
                                
                                JOptionPane.showMessageDialog(null,"Book(s) issued successfully!","Issue Book",JOptionPane.INFORMATION_MESSAGE);
                   }
                   else{
                       JOptionPane.showMessageDialog(null,"Quantity of desired book is null","Book Unavailable",JOptionPane.ERROR_MESSAGE);
                   }    
                }
            }
                else {
                    JOptionPane.showMessageDialog(null,"You have reached your maximum limit for issuing books!","Error",JOptionPane.ERROR_MESSAGE);
                }        
         }
        catch(Exception e){
//                JOptionPane.showMessageDialog(null,e,"Error",JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        }
        finally{
            try{

              resultset.close();
              statement.close();
              statement2.close();
              connection.close();
            }
            catch (SQLException ex) {
                     
           }
        }
  }
     public static void Return(String[] table_info){
         int i=0;
        
            int copies=0;
            int BID=0;
            int check_group=0;
            try{
                connection=DB_Connect.connectDB();
                book_count=0;  
                String check="SELECT * FROM LIB.BORROWED_BY WHERE MEM_ID="+memID;
                statement2=connection.createStatement();
                resultset=statement2.executeQuery(check);
                
                while(resultset.next()){
                    ++book_count;
                } 
                resultset.close();
                if(book_count>0){
                            
                            String q2="SELECT NO_OF_COPIES FROM LIB.BOOKS_GROUP WHERE G_ID="+table_info[1];
                             statement2=connection.createStatement();
                             resultset=statement2.executeQuery(q2);
                             if(resultset.next()){
                                copies=resultset.getInt("NO_OF_COPIES");
                             }
                            resultset.close();
                            copies=copies+1;
                            String  q1="UPDATE LIB.BOOKS_GROUP SET NO_OF_COPIES=? WHERE G_ID=" +table_info[1];  
                            statement=connection.prepareStatement(q1);    
                            statement.setInt(1,copies);
                            statement.executeUpdate();
                            statement.close();

                            q1="UPDATE LIB.BOOKS SET ISSUED=? WHERE BOOK_GID="+table_info[1]+" AND BOOK_ID="+table_info[0];
                              statement=connection.prepareStatement(q1); 
                              statement.setInt(1, 0);
                              statement.executeUpdate();
                              statement.close();
                              book_count=book_count-1;
  
                
                            check="DELETE FROM LIB.BORROWED_BY WHERE MEM_ID="+memID+" AND B_ID="+table_info[0];
                            statement=connection.prepareStatement(check);
                            statement.executeUpdate();


                            JOptionPane.showMessageDialog(null,"Book(s) returned successfully!","Return Book",JOptionPane.INFORMATION_MESSAGE);
                   }
                   else{
                       JOptionPane.showMessageDialog(null,"No books to return!","Book Not Issued",JOptionPane.ERROR_MESSAGE);
                   }    
                }
        catch(Exception e){
//                JOptionPane.showMessageDialog(null,e,"Error",JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        }
        finally{
            try{

              resultset.close();
              statement.close();
              statement2.close();
              connection.close();
            }
            catch (SQLException ex) {
                     
           }
        }
  }
     public static String[][] View_Issued(){
         String q1="SELECT * FROM LIB.BORROWED_BY WHERE MEM_ID="+memID;
          
           
           try{
              connection=DB_Connect.connectDB();
              statement2=connection.createStatement();
              resultset=statement2.executeQuery(q1);
              int rows=0;
              boolean b=false;
              while(resultset.next()){
                  rows++;
              }
              resultset.close();
              statement2.close();
              if(rows!=0){
                  b=true;
              }
              int row=0;
              if(b==true){
                  String[][] info=new String[rows][7];

                  q1="SELECT B_ID,BG_ID,TITLE,AUTHOR,M_NAME,MEMBER_TYPE FROM LIB.BORROWED_BY,LIB.BOOKS_GROUP,LIB.MEMBERS WHERE G_ID=BG_ID AND MEM_ID="+memID+" AND M_ID="+memID;
                  statement2=connection.createStatement();
                  resultset=statement2.executeQuery(q1);
              
                  while(resultset.next()){
                         
                        info[row][0]=resultset.getString("B_ID");
                         info[row][5]=Integer.toString(memID);
                         info[row][1]=resultset.getString("BG_ID");
                         info[row][2]=resultset.getString("TITLE");
                         info[row][3]=resultset.getString("AUTHOR");
                         info[row][4]=resultset.getString("M_NAME");
                         info[row][6]=resultset.getString("MEMBER_TYPE");
                      
                         row++; 
                         
                     }
                    return info;
              }
           }
           catch(Exception e){
               JOptionPane.showMessageDialog(null,e,"Error!",JOptionPane.ERROR_MESSAGE);
           }
           finally{
               try{
                 resultset.close();
                 statement2.close();
                 connection.close();
               }
               catch (SQLException e) {
                        JOptionPane.showMessageDialog(null,e,"Error!",JOptionPane.ERROR_MESSAGE);
                }
           }           
         return null;
     }
  }