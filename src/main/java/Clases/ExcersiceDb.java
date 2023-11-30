package Clases;

import java.sql.Connection;
import java.util.ArrayList;

public interface ExcersiceDb {
    ArrayList<Excercise> excerciseList= new ArrayList<Excercise>();
    public boolean Crear(Connection link,Excercise excercise);
    public boolean Actualizar(Connection link, Excercise excercise);
    public boolean Eliminar(Connection link, int id);
    public ArrayList<Excercise> Leer(Connection link);
    public Excercise Buscar(Connection link,int id);
}
