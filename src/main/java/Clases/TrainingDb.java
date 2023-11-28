
package Clases;
import java.sql.Connection;
import java.util.ArrayList;




public interface TrainingDb {
    ArrayList<Training> trainingList= new ArrayList<Training>();
    public boolean Crear(Connection link,Training training);
    public boolean Actualizar(Connection link, Training training);
    public boolean Eliminar(Connection link, String name);
    public ArrayList<Training> Leer(Connection link);
    public Training Buscar(Connection link,String name);
}
