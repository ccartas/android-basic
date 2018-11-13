package training.luxoft.ro.myapp.models;

import java.io.Serializable;

public class ToDoItem implements Serializable {

    private String taskName;
    private String priority;
    private int duration;
    private boolean isDone;

    public ToDoItem(){

    }

    public ToDoItem(String name, String priority, int duration, boolean isDone){
        this.taskName = name;
        this.priority = priority;
        this.duration = duration;
        this.isDone = isDone;
    }

    public void setTaskName(String name){
        this.taskName = name;
    }
    public void setPriority(String priority){
        this.priority = priority;
    }
    public void setDuration(int duration){
        this.duration = duration;
    }
    public void setIsDone(boolean isDone){
        this.isDone = isDone;
    }

    public String getTaskName(){
        return this.taskName;
    }
    public String getPriority(){
        return this.priority;
    }
    public int getDuration(){
        return this.duration;
    }
    public boolean getIsDone(){
        return this.isDone;
    }
}
