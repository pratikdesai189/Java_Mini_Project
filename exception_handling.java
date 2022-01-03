import java.util.Scanner;

class InvalidMessageException extends Exception{
    public InvalidMessageException(String message){
        super(message);
    }
    public String toString(){
        return "InvalidMessageException: Try again with valid message";
    }
}

/*
katty perry
*/

class Encrypter {
    public static String encryptMessage(String message){
        String res="";
      try{
        if(!Validator.validate(message)){
            throw new InvalidMessageException(message);
        }
        else{
        StringBuilder str=new StringBuilder(message);
        str=str.reverse();
        for(int i=0;i<str.length();i++){
         if(Character.isUpperCase(str.charAt(i))){
            str.setCharAt(i,Character.toLowerCase(str.charAt(i)));
            }
            else{
                continue;
            }
        }
            res=str.toString();
        }
        }
    catch(InvalidMessageException e){
        System.out.println(e.toString());
    }
    return res;
    }
}

class Validator {
    public static boolean validate(String message) {
        return message.matches("[A-Za-z0-9 ]+");
    }
}


public class exception_handling {
    private static final Scanner INPUT_READER = new Scanner(System.in);
    
    public static void main(String[] args) {
        String message = INPUT_READER.nextLine();
        
        try {
            String encrypted_message = Encrypter.encryptMessage(message);
            if(! encrypted_message.startsWith("InvalidMessageException"))
                System.out.println(encrypted_message);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}