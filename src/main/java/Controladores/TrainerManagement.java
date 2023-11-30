package Controladores;

import Clases.Trainer;
import Clases.TrainerDb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;



public class TrainerManagement implements TrainerDb {
    public String query;
    public PreparedStatement ps = null;
    public ResultSet rs = null;

    @Override
    public boolean Create(Connection link, Trainer trainer) {
         query="INSERT INTO Trainer (userName,password,email,gender,age,specialty) VALUES (?,?,?,?,?,?)";
        try{
            ps= link.prepareStatement(query);
            ps.setString(1,trainer.getName());
            ps.setString(2,trainer.getPassword());
            ps.setString(3,trainer.getEmail());
            ps.setString(4,trainer.getGender());
            ps.setInt(5,trainer.getAge());
            ps.setString(6,trainer.getSpecialty());
            
            ps.execute();
            return true;
            
        }catch (SQLException ex) {
            Logger.getLogger(Conn.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    @Override
    public boolean Update(Connection link, Trainer trainer) {
        query="UPDATE Trainer set username=?, password= ?, email= ?,gender= ?, age= ?,specialty= ? WHERE trainerID = ?";
        try{
            ps= link.prepareStatement(query);
            ps.setString(1,trainer.getName());
            ps.setString(2,trainer.getPassword());
            ps.setString(3,trainer.getEmail());
            ps.setString(4,trainer.getGender());
            ps.setInt(5,trainer.getAge());
            ps.setString(6,trainer.getSpecialty());
            ps.setInt(7,trainer.getID());
            ps.execute();
            return true;
            
        }catch (SQLException ex) {
            Logger.getLogger(Conn.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    @Override
    public boolean Delete(Connection link, String userName) {
         try {
             
             
            String deleteTrainingQuery = "DELETE FROM training WHERE trainerID IN (SELECT trainerID FROM Trainer WHERE userName = ?)";
            ps = link.prepareStatement(deleteTrainingQuery);
            ps.setString(1, userName);
            ps.executeUpdate();
            
            String deleteTrainerQuery = "DELETE FROM Trainer WHERE userName = ?";
            ps = link.prepareStatement(deleteTrainerQuery);
            ps.setString(1, userName);
            ps.executeUpdate();
            
            return true;
            
        }catch (SQLException ex) {
            Logger.getLogger(Conn.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public ArrayList<Trainer> Read(Connection link) {
        try{
            Statement s = link.createStatement();
            query="select * from Trainer";
            ResultSet rs=s.executeQuery(query);
            while (rs.next()){
               Trainer trainer=new Trainer();
               trainer.setName(rs.getString("userName"));
               trainer.setPassword(rs.getString("password"));
               trainer.setEmail(rs.getString("email"));
               trainer.setGender(rs.getString("gender"));
               trainer.setAge(rs.getInt("age"));
               trainer.setSpecialty(rs.getString("specialty"));
               
               trainerList.add(trainer);
               
            }
            return trainerList;
        }catch (SQLException ex) {
            Logger.getLogger(Conn.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Trainer Search(Connection link, String userName) {
        Trainer trainer=new Trainer();
        query="select * from Trainer where username= ?";
        try {
            ps= link.prepareStatement(query);
            ps.setString(1, userName);
            rs=ps.executeQuery();
            while (rs.next()){
               trainer.setID(rs.getInt("trainerID"));
               trainer.setName(rs.getString("userName"));
               trainer.setPassword(rs.getString("password"));
               trainer.setEmail(rs.getString("email"));
               trainer.setGender(rs.getString("gender"));
               trainer.setAge(rs.getInt("age"));
               trainer.setSpecialty(rs.getString("specialty"));
                
            }
            return trainer;
  
            
        } catch (SQLException ex) {
            Logger.getLogger(Conn.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int Validate(Connection link, String userName) {
        
        query="SELECT count(userName) FROM Trainer WHERE userName = ?";
        
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
        query="SELECT count(username) FROM Trainer WHERE userName = ? AND password = ?";
        
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
