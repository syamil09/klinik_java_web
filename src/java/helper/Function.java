/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;
/**
 *
 * @author syamil imdad
 */
public class Function {
    
        /*
        * generate increment id according last id given in parameter.
        * if there's alphabet in id, aphabhet must be at beginning of id
        * @param String
        * @return String
        */
//        public String generateId(String id) {
//            System.out.println("Last ID : "+id);
//            int beginNum = id.replaceAll("[^a-zA-Z]+", "").length();
//            int number = Integer.valueOf(id.substring(beginNum));
//            String newId = "";
//            for (int  i = 0; i < id.length(); i++) {
//                   if (id.charAt(i) != '0' && !Character.isAlphabetic(id.charAt(i))) {
//                        number += 1;
//                        int newLengthNumber = Integer.toString(number).length();
//                        if (newLengthNumber > id.substring(i).length()) {
//                            newId = id.substring(0, newLengthNumber > id.substring(beginNum).length() ? beginNum : i-1);
//                        } else {
//                            newId = id.substring(0, i);
//                        }
//                        newId += Integer.toString(number);
//                        break;
//                   }
//                   
//            }
//            return newId;   
//        }
        
        public String generateId(String id) {
            System.out.println("Last ID : "+id);
            String strId=id.substring(id.replaceAll("[^a-zA-Z]", "").length());
            int number = Integer.valueOf(strId) + 1;
            int lengthNum = String.valueOf(number).length();
            String zero = "";
            for (int i = 0;i < strId.length()-lengthNum;i++) {
                zero += "0";
            }  
            return id.substring(0,id.length()-strId.length())+zero+number;   
        }
        
        public String generateId(String alpha, String id) {
            System.out.println("Last ID : "+id);
            int number = Integer.valueOf(id) + 1;
            int endIndex = id.length() - String.valueOf(number).length();
            String depan = id.substring(0, endIndex >= 0 ? endIndex : 0);
            String belakang = String.valueOf(number);
  
            return depan+belakang;   
        }
        
        public String makeHash(String originalPassword) {
            String generatedSecuredPasswordHash = BCrypt.hashpw(originalPassword, BCrypt.gensalt(12));
            System.out.println(generatedSecuredPasswordHash);
            
            boolean matched = BCrypt.checkpw(originalPassword, generatedSecuredPasswordHash);
            System.out.println(matched);
            return generatedSecuredPasswordHash;
        }
}
