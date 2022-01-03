
package com.fresco;


import java.util.*;


public class ScoreList<Type extends Number> {
    
    //Write your code
    public ArrayList<String> studentData;
    public ScoreList(ArrayList<String> score_list){
        this.studentData=score_list;
    }
    public void addElement(){

    }
    public void removeElement(){

    }
    public Type getElement(){
        return null;

    }
    public double averageValues(){
        double avg=0;
        double sum=0;
        double size=studentData.size();
        
        for(String num:studentData) {
            
            double num1=Double.parseDouble(num);
            sum+=num1;
        }
        avg=sum/size;
        return avg;
    }

}
