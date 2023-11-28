package Clases;


public class Excercise{
    private int ID;
    private String excerciseName;
    private int burntCalories;
    private int reps;
    private String difficulty; //beginner, intermediate, expert

    public Excercise(){
    }
    public Excercise(String excerciseName, int cal, int reps, String difficulty,int ID){
        this.excerciseName = excerciseName;
        this.burntCalories = cal;
        this.reps = reps;
        this.difficulty = difficulty;
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public void setExcerciseName(String excerciseName){
        this.excerciseName = excerciseName;
    }
    public String getExcerciseName(){
        return excerciseName;
    }
    public void setBurntCalories(int burntCalories) {
        this.burntCalories = burntCalories;
    }
    public int getBurntCalories() {
        return burntCalories;
    }
    public void setReps(int reps) {
        this.reps = reps;
    }
    public int getReps() {
        return reps;
    }
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
    public String getDifficulty() {
        return difficulty;
    }

}