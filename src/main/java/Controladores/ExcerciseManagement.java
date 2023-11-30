package Controladores;
import Clases.Excercise;
import Clases.ExcersiceDb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExcerciseManagement implements ExcersiceDb{
    public String query;
    public PreparedStatement ps = null;
    public ResultSet rs = null;
    
    @Override
    public boolean Crear(Connection link, Excercise excercise) {
        query="INSERT INTO excercise (nameE,burntCal,difficulty,reps) VALUES (?,?,?,?)";
        try{
            ps= link.prepareStatement(query);
            ps.setString(1,excercise.getExcerciseName());
            ps.setInt(2,excercise.getBurntCalories());
            ps.setString(3, excercise.getDifficulty());
            ps.setInt(4, excercise.getReps());
            
            ps.execute();
            return true;
            
        }catch (SQLException ex) {
            Logger.getLogger(Conn.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    @Override
    public boolean Actualizar(Connection link, Excercise excercise) {
        query="UPDATE excercise set nameE= ?, burntCal = ?, difficulty = ?, reps = ?";
        try{
            ps= link.prepareStatement(query);
            ps.setString(1, excercise.getExcerciseName());
            ps.setInt(2,excercise.getBurntCalories());
            ps.setString(3, excercise.getDifficulty());
            ps.setInt(4, excercise.getReps());
            ps.execute();
            return true;
            
        }catch (SQLException ex) {
            Logger.getLogger(Conn.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean Eliminar(Connection link, int ID){
        try {
            String deleteExcerTrainingQuery = "DELETE FROM excer_training WHERE exID = ?";
            ps = link.prepareStatement(deleteExcerTrainingQuery);
            ps.setInt(1, ID);
            ps.executeUpdate();

            String deleteExcerciseQuery = "DELETE FROM excercise WHERE exID = ?";
            ps = link.prepareStatement(deleteExcerciseQuery);
            ps.setInt(1, ID);
            ps.executeUpdate();
            
            return true;
        }catch (SQLException ex) {
            Logger.getLogger(Conn.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    @Override
    public ArrayList<Excercise> Leer(Connection link) {
        try{
            Statement s = link.createStatement();
            query="select * from Excercise";
            ResultSet rs=s.executeQuery(query);
            while (rs.next()){
               Excercise excercise=new Excercise();
               
               excercise.setID(rs.getInt("exID"));
               excercise.setExcerciseName(rs.getString("nameE"));
               excercise.setBurntCalories(rs.getInt("burnCal"));
               excercise.setDifficulty(rs.getString("difficulty"));
               excercise.setReps(rs.getInt("reps"));
               
               excerciseList.add(excercise);
            }
            return excerciseList;
        }catch (SQLException ex) {
            Logger.getLogger(Conn.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Excercise Buscar(Connection link, int ID) {
        Excercise excercise = new Excercise();
        query = "SELECT * FROM Excercise WHERE exID = ?";

        try {
            ps = link.prepareStatement(query);
            ps.setInt(1, ID);
            rs = ps.executeQuery(); 

            while (rs.next()) {
                excercise.setID(rs.getInt("exID"));
                excercise.setExcerciseName(rs.getString("nameE"));
                excercise.setBurntCalories(rs.getInt("burntCal"));
                excercise.setDifficulty(rs.getString("difficulty"));
                excercise.setReps(rs.getInt("reps"));
            }

            return excercise;

        } catch (SQLException ex) {
            Logger.getLogger(Conn.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    /*
    public Excercise Buscar(Connection link, int ID) {
        Excercise excercise=new Excercise();
        query="select * from Excercise where exID = ?";
        try {
            ps= link.prepareStatement(query);
            ps.setInt(1, ID);
            rs=ps.executeQuery(query);
            while (rs.next()){
               excercise.setID(rs.getInt("exID"));
               excercise.setExcerciseName(rs.getString("nameE"));
               excercise.setBurntCalories(rs.getInt("burntCal"));
               excercise.setDifficulty(rs.getString("difficulty"));
               excercise.setReps(rs.getInt("reps"));
            }
            return excercise;
  
        } catch (SQLException ex) {
            Logger.getLogger(Conn.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    */
    public ArrayList<Object[]> getExcercise(Connection link){
        ArrayList<Object[]> arr = new ArrayList<Object[]>();
        query="""
          Select * FROM Excercise
        """;
        try{
            ps= link.prepareStatement(query);
            rs= ps.executeQuery();
            while(rs.next()){
                Object[]data = new Object[]{rs.getInt("exID"),rs.getString("nameE"),rs.getString("difficulty"),rs.getInt("burntCal")};
                arr.add(data);
            }
            return arr;
        
        }catch(SQLException ex){
            Logger.getLogger(Conn.class.getName()).log(Level.SEVERE,null,ex);
        }
        return null;
    }
    
    
}
