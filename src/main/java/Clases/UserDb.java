package Clases;

import java.sql.Connection;
import java.util.ArrayList;



public interface UserDb {
    ArrayList<User> userList= new ArrayList<User>();
    public boolean Create(Connection link,User user);
    public boolean Update(Connection link, User user);
    public boolean Delete(Connection link, String userName);
    public ArrayList<User> Read(Connection link);
    public User Search(Connection link,String userName);
}
