import java.util.HashMap;
import java.util.Scanner;

public class App {
    public static final String characters = "abcdefghijklmnopqrstuvwxyz0123456789()*+,-./";   
   
    public static String encode(String inputStr, int shiftKey)  
    {
    String encodeText = ""; 
    for (int i = 0; i < inputStr.length(); i++)   
        {   
            inputStr = inputStr.toLowerCase();
            //Ignore spaces
            if (inputStr.charAt(i) == ' ') {
                encodeText += ' ';
                continue;
            }
        //Position of character (A=0,B=1...)
        int position = characters.indexOf(inputStr.charAt(i)); 
        //Position after encode(B shift -1 would be A)
        int encryptPos = (shiftKey + position) % 44;  //44 characters available to shift
        if(encryptPos<0)
        {
            encryptPos = (shiftKey + position) % 44 + 44;  // Wrap-around(this prevents index error)
        }
        char encryptChar = characters.charAt(encryptPos);   
        //Add on the string
        encodeText += encryptChar;          
    }      
     return encodeText; 
    }
    public static String decode(String inputStr, int shiftKey)   
    {     
        inputStr = inputStr.toLowerCase();   
        String decodeText = "";   
        for (int i = 0; i < inputStr.length(); i++)   
        {   
            //Ignore spaces
            if (inputStr.charAt(i) == ' ') {
                decodeText += ' ';
                continue;
            }
            int position = characters.indexOf(inputStr.charAt(i));   
            int decodePos = (position - shiftKey) % 44; //44 characters available to shift
            
            if (decodePos < 0){   
                decodePos = characters.length() + decodePos;   
            }   
            char decryptChar = characters.charAt(decodePos);   
            //Add on the string  
            decodeText += decryptChar;   
        }   
        return decodeText;   
    }
    public static void main(String[] args)
    {
        //Store character with corresponding shift value
        HashMap<String, Integer> offsetValue = new HashMap<String, Integer>();
        offsetValue.put("a",0);
        offsetValue.put("b",-1);
        offsetValue.put("c",-2);
        offsetValue.put("d",-3);
        offsetValue.put("e",-4);
        offsetValue.put("f",-5);
        offsetValue.put("g",-6);
        offsetValue.put("h",-7);
        offsetValue.put("i",-8);
        offsetValue.put("j",-9);
        offsetValue.put("k",-10);
        offsetValue.put("l",-11);
        offsetValue.put("m",-12);
        offsetValue.put("n",-13);
        offsetValue.put("o",-14);
        offsetValue.put("p",-15);
        offsetValue.put("q",-16);
        offsetValue.put("r",-17);
        offsetValue.put("s",-18);
        offsetValue.put("t",-19);
        offsetValue.put("u",-20);
        offsetValue.put("v",-21);
        offsetValue.put("w",-22);
        offsetValue.put("x",-23);
        offsetValue.put("y",-24);
        offsetValue.put("z",-25);
        offsetValue.put("0",-26);
        offsetValue.put("1",-27);
        offsetValue.put("2",-28);
        offsetValue.put("3",-29);
        offsetValue.put("4",-30);
        offsetValue.put("5",-31);
        offsetValue.put("6",-32);
        offsetValue.put("7",-33);
        offsetValue.put("8",-34);
        offsetValue.put("9",-35);
        offsetValue.put("(",-36);
        offsetValue.put(")",-37);
        offsetValue.put("*",-38);
        offsetValue.put("+",-39);
        offsetValue.put(",",-40);
        offsetValue.put("-",-41);
        offsetValue.put(".",-42);
        offsetValue.put("/",-43);

        Scanner sc = new Scanner(System.in);   
        System.out.println("Enter text to be encoded:");   
        String inputStr = sc.nextLine();   
        System.out.println("Enter offset character:");   
        String shiftKey = String.valueOf(sc.nextLine());   
        int shiftValue =  offsetValue.get(shiftKey);
        System.out.println("Encoded text:"+""+shiftKey+encode(inputStr, shiftValue));   
        System.out.println("Decoded text:"+decode(encode(inputStr, shiftValue), shiftValue));   

        sc.close();   
    }
}