 package com.fresco;

import java.util.HashMap;
import java.util.Objects;

class Library
{
    
    String bookName;
    String author;
    Library()
    {
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.bookName);
        hash = 83 * hash + Objects.hashCode(this.author);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Library other = (Library) obj;
        if (!Objects.equals(this.bookName, other.bookName)) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        return true;
    }
    
    
    Library(String bookName,String author)
    {
        this.bookName=bookName;
        this.author=author;
    }
    public HashMap<Integer,Library> createLibraryMap(String booksInLibrary)
    {
        String[] str=booksInLibrary.split("\\|");
        String[] temp_str=null;
       HashMap<Integer,Library> hs1=new HashMap<>();
        for(String temp:str) {
//          System.out.println(temp);
            temp_str=temp.split("\\,");
            hs1.put(Integer.parseInt(temp_str[0]),new Library(temp_str[1],temp_str[2]));
        }
        return hs1;
    }
    
    public HashMap<Integer,Integer> createUserMap(String borrowedUsers)
    {

        String[] str=borrowedUsers.split("\\|");
        String[] temp_str=null;
        HashMap<Integer,Integer> hs2=new HashMap<>();
        for(String temp:str) {
//          System.out.println(temp);
            temp_str=temp.split("\\,");
//          System.out.println(temp_str[0]);
            hs2.put(Integer.parseInt(temp_str[0]), Integer.parseInt(temp_str[1]));
        }
        
        
        return hs2;
    }

    
    public String getQuery(String booksInLibrary,String borrowedUsers,String query)
    {
        HashMap<Integer,Library> hs_book=createLibraryMap(booksInLibrary);
        HashMap<Integer,Integer> hs_user=createUserMap(borrowedUsers);
        String[] temp_str = query.split("\\,");
         int q=Integer.parseInt(temp_str[0]);
         if(q==1){
//           System.out.println(hs_book);
            int book_id=Integer.parseInt(temp_str[1]);
            if(hs_book.containsKey(book_id) && !hs_user.containsKey(book_id)){
                return "It is available\nAuthor is "+hs_book.get(book_id).author+"\n";
            }
            else{
                return "No Stock\nIt is owned by "+hs_user.get(book_id).toString()+"\n";
            }
         }
         else if(q==2){
             int user_id=Integer.parseInt(temp_str[1]);
             String str="";
             for (Integer key : hs_user.keySet()) {
                 if(hs_user.get( key ).equals( user_id )){
                     
                    str+=key+" "+hs_book.get(key).bookName+"\n";
                 }
             }
             return str;
          }
         else if(q==3){
             String book_name=temp_str[1];
             int count_in=0;
             int count_out=0;
             for (Integer key : hs_book.keySet()){
                 String temp_book_name=hs_book.get(key).bookName;
                
                 if(book_name.equals(temp_book_name)){
                     count_in+=1;
//                     System.out.println(key);
                     for(Integer key2 : hs_user.keySet()){
                         if(key2.equals(key)){
                             count_out+=1;
                         }
                     }
                 }
             }
             return count_out+" out\n"+(count_in-count_out)+" in\n";

          }
         else if(q==4) {
             String author_name=temp_str[1];
             String res="";
             for (Integer key : hs_book.keySet()) {
                 String temp_author_name=hs_book.get(key).author;
                 if(author_name.equals(temp_author_name)) {
                     res+=hs_book.get(key).bookName+"\n";
                 }
             }
             return res;
         }
         else if(q==5) {
             String search_key=temp_str[1];
             String res="";
             for (Integer key : hs_book.keySet()) {
                 String temp_book_name=hs_book.get(key).bookName.toLowerCase();
                 if(temp_book_name.contains(search_key)) {
                     res+=key+" "+hs_book.get(key).bookName+"\n";
                 }
             }
             return res;
         }
         
             return null;
         
         
    }
    
}

