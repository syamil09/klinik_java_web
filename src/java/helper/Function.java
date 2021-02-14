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
        public String generateId(String id) {
            System.out.println("Last ID : "+id);
            int beginNum = id.replaceAll("[^a-zA-Z]+", "").length();
            int number = Integer.valueOf(id.substring(beginNum));
            String newId = "";
            for (int  i = 0; i < id.length(); i++) {
                   if (id.charAt(i) != '0' && !Character.isAlphabetic(id.charAt(i))) {
                        number += 1;
                        int newLengthNumber = Integer.toString(number).length();
                        if (newLengthNumber > id.substring(i).length()) {
                            newId = id.substring(0, newLengthNumber > id.substring(beginNum).length() ? beginNum : i-1);
                        } else {
                            newId = id.substring(0, i);
                        }
                        newId += Integer.toString(number);
                        break;
                   }
                   
            }
            return newId;   
        }
        
        public String makeHash(String originalPassword) {
            String generatedSecuredPasswordHash = BCrypt.hashpw(originalPassword, BCrypt.gensalt(12));
            System.out.println(generatedSecuredPasswordHash);
            
            boolean matched = BCrypt.checkpw(originalPassword, generatedSecuredPasswordHash);
            System.out.println(matched);
            return generatedSecuredPasswordHash;
        }
}
