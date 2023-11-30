
package Controladores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;



public class Conn {
    private String db;
    private String url;
    private String user;
    private String pass;
    private Connection link ;
    
    public Conn(){
        this.db="profidatabase";
        this.url = "jdbc:mysql://localhost:3306/"+db;
        this.user="root";
        this.pass="";
        this.link=null;
    }
    
    public Connection Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.link = DriverManager.getConnection(this.url, this.user, this.pass);
            System.out.println("Conectado.....");
            
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
        return link;
    }
    public void CerrarConexion(){
        
        try {
            link.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conn.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
   
    
    
}
