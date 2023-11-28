package Clases;




public class Training {
    private int ID;
    private String trainingName;


    public Training(){
    }
    public Training(String trainingName, int ID){
        this.ID = ID;
        this.trainingName = trainingName;
    }

    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }
    public String getTrainingName() {
        return trainingName;
    }
}