package com.fresco;
import java.util.*;
class Passanger
{
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getFare() {
        return fare;
    }

    public void setFare(float fare) {
        this.fare = fare;
    }
    float fare;
    public Passanger(int id, float fare)
    {
        this.id=id;
        this.fare=fare;
    }
}
public class BusProb {
    public static void main(String[] args) {
        
        List<String> listOfInputs=new ArrayList<>();
        int capacity=10;
        int stops=4;
        listOfInputs.add("+2501 +2502 +2503 +2504");
        listOfInputs.add("-2501 -2504 +2505 +2506 +2507 +2509");
        listOfInputs.add("+2501 +2511 -2502 -2505");
        listOfInputs.add("+2513 -2507 -2503 -2511 -2509");
        String query="1";
        System.out.println(output(capacity,stops,listOfInputs,query));
//      System.out.println();
    }

    public static String output(int capacity, int stops, List<String> listOfInputStrings, String query)
    {
        List<Passanger> details=new ArrayList<Passanger>();
        int query1_in=0;
        int query1_out=0;
        double present=0;
        for(String temp:listOfInputStrings) {
            String[] temp_str=temp.split(" ");
            double in=0;
            float capacity1=capacity;
            double out=0;
            
            float fare=0;
            for(String temp2:temp_str) {
                if(temp2.contains("+")) {
                    in+=1;
                }
                else {
                    out+=1;
                }
            }
            query1_in+=in;
            query1_out+=out;
            present+=in-out;
//          System.out.println(present);
            if(present<=Math.ceil(capacity1*0.25)) {
                fare=(float) (capacity1+capacity1*0.6);
                for(String temp2:temp_str) {
                    if(temp2.contains("+")){
                    String temp3=temp2.substring(1);
//                  System.out.println(temp3);
                    int id=Integer.parseInt(temp3);
                    Passanger obj=new Passanger(id,fare);
                    details.add(obj);
                    }
                }
            }
            else if(present<=Math.ceil(capacity1*0.50)) {
                fare=(float) (capacity1+capacity1*0.3);
                for(String temp2:temp_str) {
                    if(temp2.contains("+")){
                    String temp3=temp2.substring(1);
                    int id=Integer.parseInt(temp3);
                    Passanger obj=new Passanger(id,fare);
                    details.add(obj);
                    }
                }
            }
            else if(present>Math.ceil(capacity1*0.50)){
                fare=(float) capacity1;
                for(String temp2:temp_str) {
                    if(temp2.contains("+")){
                    String temp3=temp2.substring(1);
                    int id=Integer.parseInt(temp3);
                    Passanger obj=new Passanger(id,fare);
                    details.add(obj);
                    }
                }
            }
            
//          System.out.println(details);
        }
        query=query.replace(" ", "");
     String[] query_split=query.split("\\,");
    if(query_split[0].equals("1")){ 
       return query1_in+" passengers got on the bus and "+query1_out+" passengers got out of the bus";
        }
    else if(query_split[0].equals("2")){
        int query2_1=0;
        int query2_2=0;
        int query2_3=0;
        float fare1=(float) (capacity+capacity*0.6);
        float fare2=(float) (capacity+capacity*0.3);
        float fare3=(float) capacity;
        
        for(Passanger p:details) {
            if(p.getFare()==fare1) {
                query2_1+=1;
            }
            else if(p.getFare()==fare2) {
                query2_2+=1;
            }
            else {
                query2_3+=1;
            }
        }
        return query2_1+" passengers traveled with a fare of "+
        fare1+", "+query2_2+" passengers traveled with a fare of "+
        fare2+" and "+query2_3+" passengers traveled with a fare of "+
        fare3;
    }
    else if(query_split[0].equals("3")) {
        int given_id=Integer.parseInt(query_split[1]);
        float total_fare=0;
        for(Passanger p:details) {
            if(p.getId()==given_id) {
                total_fare+=p.getFare();
            }
        }
        return "Passenger "+given_id+" spent a total fare of "+total_fare;
    }
    else if(query_split[0].equals("4")) {
        int given_id=Integer.parseInt(query_split[1]);
        int count=0;
        for(Passanger p:details) {
            if(p.getId()==given_id) {
                count+=1;
            }
        }
        return "Passenger "+given_id+" has got on the bus "
                + "for "+count+" times";
    }
    else if(query_split[0].equals("5")) {
        int given_id=Integer.parseInt(query_split[1]);
        String status="";
        int count_in=0;
        int count_out=0;
        for(String temp:listOfInputStrings) {
            String[] temp_str=temp.split(" ");
            
            for(String temp2:temp_str) {
                int temp3=Integer.parseInt(temp2.substring(1));
                 if(temp3==given_id && temp.charAt(0)=='+') {
                     count_in+=1;
                 }
                 else if(temp3==given_id && temp.charAt(0)=='-') {
                     count_out+=1;
                 }
                 else {
                     status="not inside ";
                 }
            }
            
        }
        System.out.println(count_in);
        System.out.println(count_out);
        int ispresent=count_in-count_out;
        if(ispresent%2==0) {
            status="not inside ";
        }
        else {
            status="inside ";
        }
        return "Passenger "+given_id+" was "+status+"the bus at the end of the trip";
    } 
    return null;
}
}
