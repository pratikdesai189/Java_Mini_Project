package com.fresco;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.*;

public class LambdaFn {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        long a=301;
        long b=400;
        List<String> listOfIntegers = new ArrayList<>();
        for(long i=a;i<=b;i++)
        {
            listOfIntegers.add(i+"");
        }
//        System.out.println(listOfIntegers);
        List<Long> actual = functionalProgramming(listOfIntegers);
        System.out.println(actual);
    }
    public  List<Long> functionalProgramming(List<String> listOfIntegers)
    {
        
        List<Long> outputList = Collections.emptyList();
        outputList=listOfIntegers.stream()
        .map(s->Long.parseLong(s))
        .filter(s->{
            // count the number of digits
            long cnt = 0;
            long dup = s;
            long dup1 = s;
            while (dup>0) {
                cnt+=1;
                dup=dup/10;
            }
            long sum = 0;
         
            
            while (dup1>0)
            {
                sum += Math.pow(dup1 % 10, cnt);
                dup1 /= 10;
            }
         
            return (s == sum);
        }).collect(Collectors.toList());
//        System.out.println(outputList);
        return outputList;
    }




// important link for functional programming
// https://javarevisited.blogspot.com/2018/05/java-8-filter-map-collect-stream-example.html#axzz72YUGiEQM
