package Clases;

public class User extends Person{
    private double weight; //kilogramos
    private double height; //metros
    private double bmi;
    private int currentTraining;

    // Constructores
    public User(){
    }
    public User(String name, String password, String email, String gender, int age, double weight, double height, int ID){
        super(name, password, email, gender, age, ID);
        this.weight=weight;
        this.height=height;
        if(height != 0) { this.bmi = weight / (height*height); }
    }

    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }
    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }
    public double getBmi() {
        return bmi;
    }
    public void setBmi(double bmi) {
        this.bmi =bmi;
    }
    public int getCurrentTraining() {
        return currentTraining;
    }
    public void setCurrentTraining(int currentTraining) {
        this.currentTraining = currentTraining;
    }
    


}
