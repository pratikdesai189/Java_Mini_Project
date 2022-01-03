package com.fresco;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class TreemapHandson {

    public  TreeMap<Integer,String> createPlayerPositionMap(String cricketDataset)
    {
        String[] cricketDataset_list=cricketDataset.split("\\|");
        TreeMap<Integer,String> cricket_map=new TreeMap<Integer,String>(); 
        for(String temp:cricketDataset_list)
        {
            String[] temp1=temp.split("\\,");
            cricket_map.put(Integer.parseInt(temp1[0]),temp1[1]);
        }
        return cricket_map;
    }


    public  TreeMap<String,Integer> createPlayerScoreMap(String cricketDataset)
    {
        String[] cricketDataset_list=cricketDataset.split("\\|");
        TreeMap<String, Integer> cricket_map=new TreeMap<String, Integer>(); 
        for(String temp:cricketDataset_list)
        {
            String[] temp1=temp.split("\\,");
            cricket_map.put(temp1[1],Integer.parseInt(temp1[2]));
        }
        return cricket_map;

    }


    public  TreeMap<String,String> createMatchesMap(String cricketDataset)
    {
        String[] list_line=cricketDataset.split("\n");
//      TreeMap<String,List<Integer>> cricket_map=new TreeMap<String,List<Integer>>(); 
        TreeMap<String,String> cricket_map=new TreeMap<String,String>();
        for(String temp:list_line) {
            String[] list_dash=temp.split("\\|");
            for(String temp1:list_dash) {
                String[] list_comma=temp1.split("\\,");
                if(temp1.charAt(0)=='1') {
                    if(cricket_map.containsKey(list_comma[1])) {
//                      cricket_map.put(list_comma[1],cricket_map.get(list_comma[1]).add(Integer.parseInt(list_comma[2])));
                        cricket_map.put(list_comma[1],cricket_map.get(list_comma[1])+" "+list_comma[2]);
                    }
                    else {
//                      cricket_map.put(list_comma[1],new ArrayList<Integer>(Arrays.asList(Integer.parseInt(list_comma[2]))));
                        cricket_map.put(list_comma[1],list_comma[2]);
                    }
                    
                }               
            }
        }
        return cricket_map;
    }


    public  String getQuery(String cricketDataset,String query)
    {   

        String[] query_split=query.split(" ");
        if(query_split[0].equals("1")){
            String res="";
            int start=Integer.parseInt(query_split[1]);
            int end=Integer.parseInt(query_split[2]);;
            TreeMap<Integer,String> method_player=createPlayerPositionMap(cricketDataset);
            TreeMap<Integer,String> res_query1=new TreeMap<Integer,String>();
            for(Map.Entry<Integer, String>
            entry : method_player.entrySet()){
                if(entry.getKey()>=start && entry.getKey()<=end){
                    res_query1.put(entry.getKey(),entry.getValue());
                }
            }
            for(Map.Entry<Integer, String>
            entry : res_query1.entrySet()){
                res+=entry.getKey().toString()+" "+entry.getValue()+"\n";
            }
            return res;
        }
        else if(query_split[0].equals("2")) {
            String res="";
            int threshold=Integer.parseInt(query_split[1]);
            TreeMap<String,Integer> method_score=createPlayerScoreMap(cricketDataset);
            TreeMap<Integer,String> res_query2=new TreeMap<Integer,String>();
            TreeMap<Integer,String> method_player=createPlayerPositionMap(cricketDataset);
            for(Map.Entry<String,Integer>
            entry : method_score.entrySet()) {
                if(entry.getValue()>threshold) {
                    for(Map.Entry<Integer,String>
                    entry1 : method_player.entrySet()) {
                        if(entry.getKey().equals(entry1.getValue())) {
                            res_query2.put(entry1.getKey(), entry.getKey());
                        }
                    }
                }
            }
            for(Map.Entry<Integer, String>
            entry : res_query2.entrySet()){
                res+=entry.getKey().toString()+" "+entry.getValue()+"\n";
            }
            return res;
        }
        else if(query_split[0].equals("3")) {
            TreeMap<String,String> res_query3=createMatchesMap(cricketDataset);
//          System.out.println(res_query3);
            String player="";
            int store_avg=0;
            for(Map.Entry<String,String>
            entry : res_query3.entrySet()) {
                
//              Entry<Integer, String> entry1 = null;
//              Entry<String, String> entry;
                String[] score_list=entry.getValue().split(" ");
                int temp_avg=0;
                
                for(String temp_score:score_list) {
                    temp_avg+=Integer.parseInt(temp_score);
                }
                temp_avg=temp_avg/score_list.length;
//              System.out.println(temp_avg+" "+store_avg);
                if(temp_avg>store_avg) {
                    player=entry.getKey();
                    store_avg=temp_avg;
                }
                temp_avg=0;
            }
            return "The Efficient Opener is "+ player;
        }
        return null;
}
}
