package Clases;


public class Trainer extends Person{
    private String specialty;

    public Trainer(){
    }
    public Trainer(String name, String password, String email, String gender, int age, int ID, String specialty) {
        super(name, password, email, gender, age, ID);
        this.specialty = specialty;
    }

    public String getSpecialty() {
        return specialty;
    }
    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }


}