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
        query="UPDATE user set username=?,password= ?, email= ?,gender= ?, age= ?, weight= ?, height=?,bmi= ? WHERE userID= ?";
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
            ps.setInt(9, user.getID());
            ps.execute();
            return true;
            
        }catch (SQLException ex) {
            Logger.getLogger(Conn.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    @Override
    public boolean Delete(Connection link, int userID) {
         query="DELETE FROM user WHERE `user`.`userID` = ?";
         try {
            //aqui hay que buscar si se encuentra 
            ps= link.prepareStatement(query);
            ps.setInt(1,userID);
            ps.execute();
            
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
               user.setID(rs.getInt("userID"));
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
               user.setID(rs.getInt("userID"));
               user.setName(rs.getString("userName"));
               user.setPassword(rs.getString("password"));
               user.setEmail(rs.getString("email"));
               user.setGender(rs.getString("gender"));
               user.setAge(rs.getInt("age"));
               user.setWeight(rs.getDouble("weight"));
               user.setHeight(rs.getDouble("height"));
               user.setBmi(rs.getDouble("bmi"));
               user.setCurrentTraining(rs.getInt("trainingID"));
                
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
    
    public boolean setTraining(Connection link, int trainingId,String user){
        query="UPDATE user set trainingID = ? WHERE username = ?";
        try{
            ps=link.prepareStatement(query);
            ps.setInt(1,trainingId);
            ps.setString(2, user);
            ps.execute();
            return true;
            
        }catch(SQLException ex){
            Logger.getLogger(Conn.class.getName()).log(Level.SEVERE,null,ex);
        }
        return false;
    }
    
    public ArrayList<Object[]> getTraining(Connection link,int trainingID){
        ArrayList<Object[]> arr =  new ArrayList<Object[]>();
        query="""
          Select e.nameE,e.burntCal,e.difficulty,e.reps FROM excer_training et
          JOIN excercise e
          USING(exID)
          WHERE et.trainingID = ?  
        """;
        try{
            ps= link.prepareStatement(query);
            ps.setInt(1,trainingID);
            rs=ps.executeQuery();
            
            while(rs.next()){
                Object[] data= new Object[]{rs.getString("nameE"),rs.getInt("burntCal"),rs.getString("difficulty"),rs.getInt("reps")};
                arr.add(data);
                
            }
            return arr;
        }catch(SQLException ex){
            Logger.getLogger(Conn.class.getName()).log(Level.SEVERE,null,ex);
        }
        return null;
    }
    
   

}

