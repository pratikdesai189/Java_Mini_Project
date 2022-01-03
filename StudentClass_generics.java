package com.fresco;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

public class StudentClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String actual1 = getQuery("Ram Vijay Vinay Rahul Arun", "1,r");
		String actua2 = getQuery("Ram Vijay Vinay Rahul Arun", "2,B- AB- AB- O+ B-,AB-");
		String actual3 = getQuery("90 78 69 45 90 42 69", "3,70");
		String actual = getQuery("6.9 7.3 4.5", "5");
		System.out.println(actual);
		Field[] fields1 = StudentList.class.getDeclaredFields();
		for(Field field : fields1){
			System.out.println(field.getType().toString());
		}
	}
	public static String getQuery(String studentData,String query){
    	String[] query_split=query.split("\\,");
    	String q=query_split[0];
		String[] st_data=studentData.split(" ");
		ArrayList<String> st_list=new ArrayList<String>(Arrays.asList(st_data));
		StudentList<String> st_obj=new StudentList<String>(st_list);
    	if(q.equals("1")){
    		String begin_char=query_split[1];
    		ArrayList<String> res_list=st_obj.beginsWith(begin_char);
    		String res="";
    		for (String temp:res_list){
    			res+=temp+"\n";
    		}
    		return res;
    	}
    	else if(q.equals("2")) {
    		String[] blood_groups=query_split[1].split(" ");
    		String find_blood_group=query_split[2];
    		ArrayList<String> res_list=st_obj.bloodGroupOf(blood_groups,find_blood_group);
    		String res="";
    		for (String temp:res_list){
    			res+=temp+"\n";
    		}
    		return res;
    	}
    	else if(q.equals("3")) {
    		int threshold=Integer.parseInt(query_split[1]);
    		String res_threshold=st_obj.thresholdScore(threshold);
    		return res_threshold;
    	}
    	else if (q.equals("4") || q.equals("5") ) {
    		String[] score_data=studentData.split(" ");
    		ArrayList<String> score_list=new ArrayList<String>(Arrays.asList(st_data));
//    		ArrayList<Number> score_list=new ArrayList<Number>();
//    		for(String ele:score_data) {
//    			Number temp=Integer.valueOf(ele);
//    			score_list.add(temp);
//    			System.out.println(temp.getClass());
//    		}
//    		System.out.println(score_list);
    		
    		
			ScoreList<Number> score_obj=new ScoreList<Number>(score_list);
    		double res=score_obj.averageValues();
    		return String.format("%.2f",res);
    	}
        
		return null;
		
    }

}
