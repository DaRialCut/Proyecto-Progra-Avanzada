
package Controladores;
import Clases.Training;
import Clases.TrainingDb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TrainingManagement implements TrainingDb{
    public String query;
    public PreparedStatement ps = null;
    public ResultSet rs = null;

    @Override
    public boolean Crear(Connection link, Training training) {
        query="INSERT INTO Training (nameT,trainerID) VALUES (?,?)";
        try{
            ps= link.prepareStatement(query);
            ps.setString(1,training.getTrainingName());
            ps.setInt(2,training.getTrainerID());
            
            ps.execute();
            return true;
            
        }catch (SQLException ex) {
            Logger.getLogger(Conn.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    @Override
    public boolean Actualizar(Connection link, Training training) {
        query="UPDATE Training set nameT= ?, trainerID = ?";
        try{
            ps= link.prepareStatement(query);
            ps.setString(1,training.getTrainingName());
            ps.setInt(2,training.getTrainerID());
            ps.execute();
            return true;
            
        }catch (SQLException ex) {
            Logger.getLogger(Conn.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
        
    }

    @Override
    public boolean Eliminar(Connection link, String name) {
        query="delete * Training where nameT= ?";
         try {
            //aqui hay que buscar si se encuentra 
            ps.setString(1,name );
            rs= ps.executeQuery(query);
            return true;
            
        }catch (SQLException ex) {
            Logger.getLogger(Conn.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    @Override
    public ArrayList<Training> Leer(Connection link) {
        try{
            Statement s = link.createStatement();
            query="select * from Training";
            ResultSet rs=s.executeQuery(query);
            while (rs.next()){
               Training training=new Training();
               training.setID(rs.getInt("trainingID"));
               training.setTrainingName(rs.getString("nameT"));
               training.setTrainerID(rs.getInt("trainerID"));
               
               trainingList.add(training);
               
            }
            return trainingList;
        }catch (SQLException ex) {
            Logger.getLogger(Conn.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }

    @Override
    public Training Buscar(Connection link, String name) {
        Training training=new Training();
        query="select * from Training where userName= ?";
        try {
            ps= link.prepareStatement(query);
            ps.setString(1, name);
            rs=ps.executeQuery(query);
            while (rs.next()){
               training.setID(rs.getInt("trainingID"));
               training.setTrainingName(rs.getString("nameT"));
               training.setTrainerID(rs.getInt("trainerID"));
            }
            return training;
  
            
        } catch (SQLException ex) {
            Logger.getLogger(Conn.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    
}
