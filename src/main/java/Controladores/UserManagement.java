
package Controladores;

import Clases.User;
import Clases.UserDb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UserManagement implements UserDb{

    public String query;
    public PreparedStatement ps = null;
    public ResultSet rs = null;
    
    
    public UserManagement(){}
    @Override
    public boolean Create(Connection link, User user) {
        query="INSERT INTO USER (userName,password,email,gender,age,weight,height,bmi) VALUES (?,?,?,?,?,?,?,?)";
        double bmi = Math.round(user.getWeight()/(user.getHeight()*user.getHeight()));
        try{
            ps= link.prepareStatement(query);
            ps.setString(1,user.getName());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getEmail());
            ps.setString(4,user.getGender());
            ps.setInt(5,user.getAge());
            ps.setDouble(6,user.getWeight());
            ps.setDouble(7,user.getHeight());
            ps.setDouble(8, bmi);
            ps.execute();
            return true;
            
        }catch (SQLException ex) {
            Logger.getLogger(Conn.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    @Override
    public boolean Update(Connection link, User user) {
        double bmi = Math.round(user.getWeight()/(user.getHeight()*user.getHeight()));
        query="UPDATE user set password= ?, email= ?,gender= ?, age= ?, weight= ?, height=?, bmi= ?";
        try{
            ps= link.prepareStatement(query);
            ps.setString(1,user.getName());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getEmail());
            ps.setString(4,user.getGender());
            ps.setInt(5,user.getAge());
            ps.setDouble(6,user.getWeight());
            ps.setDouble(7,user.getHeight());
            ps.setDouble(8,bmi);
            ps.execute();
            return true;
            
        }catch (SQLException ex) {
            Logger.getLogger(Conn.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    @Override
    public boolean Delete(Connection link, String userName) {
         query="delete * user where userName= ?";
         try {
            //aqui hay que buscar si se encuentra 
            ps.setString(1,userName);
            rs= ps.executeQuery(query);
            
            return true;
            
        }catch (SQLException ex) {
            Logger.getLogger(Conn.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    @Override
    //Revisar la query
    public ArrayList<User> Read(Connection link) {
        try{
            Statement s = link.createStatement();
            query="select * from user";
            ResultSet rs=s.executeQuery(query);
            while (rs.next()){
               User user=new User();
               user.setName(rs.getString("userName"));
               user.setPassword(rs.getString("password"));
               user.setEmail(rs.getString("email"));
               user.setGender(rs.getString("gender"));
               user.setAge(rs.getInt("age"));
               user.setWeight(rs.getDouble("weight"));
               user.setHeight(rs.getDouble("height"));
               user.setBmi(rs.getDouble("bmi"));
               
               userList.add(user);
                
            }
            
            return userList;
        }catch (SQLException ex) {
            Logger.getLogger(Conn.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    
    // Modificar la query
    public User Search(Connection link, String userName) {
        User user=new User();
        query="select * from user where userName=?";
        try {
            ps= link.prepareStatement(query);
            ps.setString(1,userName);
            rs= ps.executeQuery();
            while (rs.next()){
               user.setName(rs.getString("userName"));
               user.setPassword(rs.getString("password"));
               user.setEmail(rs.getString("email"));
               user.setGender(rs.getString("gender"));
               user.setAge(rs.getInt("age"));
               user.setWeight(rs.getDouble("weight"));
               user.setHeight(rs.getDouble("height"));
               user.setBmi(rs.getDouble("bmi"));
                
            }
            return user;   
        } catch (SQLException ex) {
            Logger.getLogger(Conn.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public int Validate(Connection link, String userName) {
        
        query="SELECT count(userName) FROM USER WHERE userName = ?";
        
        try{
            ps= link.prepareStatement(query);
            ps.setString(1,userName);
            rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
            
        }catch (SQLException ex) {
            Logger.getLogger(Conn.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 1;
    }
    public int Validate(Connection link ,String userName, String password){
        query="SELECT count(userName) FROM USER WHERE userName = ? AND password = ?";
        
        try{
            ps= link.prepareStatement(query);
            ps.setString(1,userName);
            ps.setString(2,password);
            rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
            
        }catch (SQLException ex) {
            Logger.getLogger(Conn.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 1;
    }
}
