package Clases;

import java.sql.Connection;
import java.util.ArrayList;

public interface TrainerDb {
    ArrayList<Trainer> trainerList= new ArrayList<Trainer>();
    public boolean Create(Connection link,Trainer trainer);
    public boolean Update(Connection link, Trainer trainer);
    public boolean Delete(Connection link, String userName);
    public ArrayList<Trainer> Read(Connection link);
    public Trainer Search(Connection link,String userName);
}
    