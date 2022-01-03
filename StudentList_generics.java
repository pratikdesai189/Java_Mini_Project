
package com.fresco;


import java.util.*;

public class StudentList<Type> {
    
    //Write your code
    public ArrayList<Type> studentData;
    public StudentList(ArrayList<Type> studentData){
        this.studentData=studentData;
    }
    public void addElement(){

    }
    public void removeElement(){

    }
    public Type getElement(){
        return null;

    }

    public ArrayList<Type> beginsWith(String begin_char){
        ArrayList<Type> res1 = new ArrayList<Type>();
        for(Type temp:studentData){
            String temp1 = ((String) temp).toLowerCase();
            if(temp1.startsWith(begin_char)) {
            res1.add(temp);
            
           }
        }
        return res1;
      
    }
    public ArrayList<Type> bloodGroupOf(String[] blood_groups,String find_blood_group){
        ArrayList<Type> res2 = new ArrayList<Type>();
        int i=0;
        while(i<blood_groups.length) {
            if(blood_groups[i].equals(find_blood_group)) {
                res2.add(studentData.get(i));
                
            }
            i+=1;
        }
        
        return res2;

    }
    public String thresholdScore(int threshold){
        ArrayList<Type> res3 = new ArrayList<Type>();
        int count=0;
        for(Type temp:studentData) {
            int temp1=Integer.parseInt((String) temp);
            if(temp1>=threshold) {
                count+=1;
            }
        }
        return String.format("%d students scored %d above",count,threshold);

    }
    
    
}
