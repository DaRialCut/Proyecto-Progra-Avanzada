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
        String query;
         try {
            Training t = Buscar(link, name);

            query = "UPDATE user SET trainingID = NULL WHERE trainingID = ?";
            ps = link.prepareStatement(query);
            ps.setInt(1, t.getID());
            ps.executeUpdate();
            
            query = "DELETE FROM excer_training WHERE trainingID = ?";
            ps = link.prepareStatement(query);
            ps.setInt(1, t.getID());
            ps.executeUpdate();

            query = "DELETE FROM Training WHERE nameT = ?";
            ps = link.prepareStatement(query);
            ps.setString(1, name);
            ps.executeUpdate();
            
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
        query="select * from Training where nameT= ?";
        try {
            ps= link.prepareStatement(query);
            ps.setString(1, name);
            rs=ps.executeQuery();
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
    public ArrayList<Object[]> getTraining(Connection link){
        ArrayList<Object[]> arr = new ArrayList<Object[]>();
        query="""
          Select t.trainingID,t.nameT, tr.username, tr.specialty FROM training t
          JOIN trainer tr
          USING(trainerID)
        """;
        try{
            ps= link.prepareStatement(query);
            rs= ps.executeQuery();
            while(rs.next()){
                Object[]data = new Object[]{rs.getInt("trainingID"),rs.getString("nameT"),rs.getString("username"),rs.getString("specialty")};
                arr.add(data);
            }
            return arr;
        
        }catch(SQLException ex){
            Logger.getLogger(Conn.class.getName()).log(Level.SEVERE,null,ex);
        }
        return null;
    }
    
    public boolean addExcercise(Connection link, int trID, int exID){
        query = "INSERT INTO excer_training (trainingID, exID) VALUES (?,?)";
        //System.out.println("PRUEBA  "+ exID +"  "+ trID);
        try{
            ps = link.prepareStatement(query);
            ps.setInt(1, trID);
            ps.setInt(2, exID);
            ps.execute();
            return true;
        }catch(SQLException ex){
            Logger.getLogger(Conn.class.getName()).log(Level.SEVERE,null,ex);
        }
        return false;
    }
    
    public int Validate(Connection link, String nameT){
        query = "SELECT count(nameT) FROM TRAINING WHERE nameT = ?";
        
        try{
            ps = link.prepareStatement(query);
            ps.setString(1, nameT);
            rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        }catch (SQLException ex) {
            Logger.getLogger(Conn.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
    }
    public String getTraining(Connection link,int trainingID){
        query="SELECT nameT FROM training WHERE trainingID = ?";
        try{
            ps= link.prepareStatement(query);
            ps.setInt(1,trainingID);
            rs= ps.executeQuery();
            if(rs.next()){
                return rs.getString(1);
            }
            
        }catch(SQLException ex){
            Logger.getLogger(Conn.class.getName()).log(Level.SEVERE,null,ex);
        }
        return null;
    }
}