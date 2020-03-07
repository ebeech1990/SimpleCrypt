import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.Character.isLowerCase;
import static java.lang.Character.isUpperCase;
import static java.lang.Character.toLowerCase;
public class ROT13 {

   private char[] alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
   private Integer diff;

    ROT13(Character cs, Character cf) {
        diff = cf.compareTo(cs);
    }

    ROT13() {

    }

    public Integer getAlphaIndex(char c){
        Integer index = -1;
        for (int i = 0; i < alpha.length; i++) {
            if(String.valueOf(c).toUpperCase().equals(String.valueOf(alpha[i]))){
                index = i;
                break;
            }
        }
        return index;
    }

    public Boolean isAlpha(Character c){
        return String.valueOf(c).matches("[A-Za-z]");
    }

    public Boolean isUpper(Character c){
        return String.valueOf(c).matches("[A-Z]");
    }


    public Character toUpperOrLower(Character a, Character b){
        String bString = b.toString();
        if(isUpper(a)){
            b = bString.toUpperCase().charAt(0);
        }
        else{
            b = bString.toLowerCase().charAt(0);
        }
        return b;
    }

    public String crypt(String text) throws UnsupportedOperationException {
        diff = 13;
        return encrypt(text);
    }

    public String encrypt(String text) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            Integer index = getAlphaIndex(text.charAt(i))+diff;
            Character charAtIndex;
            if(!isAlpha(text.charAt(i))){
                charAtIndex = text.charAt(i);
            }
            else{
                if(index < alpha.length){
                    charAtIndex = alpha[index];
                }
                else{
                    charAtIndex= alpha[index-alpha.length];
                }
            }

            result.append(toUpperOrLower(text.charAt(i),charAtIndex));

        }
        return result.toString();
    }

    public String decrypt(String text) {
        diff = alpha.length-diff;
        return encrypt(text);
    }

    public  String rotate(String s, Character c) {
        StringBuilder result = new StringBuilder();
        diff = c.compareTo(s.charAt(0));
        for (int i = 0; i < s.length(); i++) {
            char index;
            if(i+diff<s.length()){
                index = s.charAt(i+diff);
            }
            else{
                index = s.charAt(i+diff - s.length());
            }
            result.append(index);
        }

        return result.toString();
    }

    public static void main(String[] args) {
        ROT13 cipher = new ROT13('a', 'n');
        String Q1 = "Why did the chicken cross the road?";
        System.out.println(cipher.crypt(cipher.crypt(Q1)));


    }
}
