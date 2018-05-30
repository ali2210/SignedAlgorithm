/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signuture;

import java.util.Random;
import javax.swing.JOptionPane;


/**
 *
 * @author Ali Hassan
 */
public class Generator {

    // these are private variables  
    private Random rand = null;
     private static Generator generator = new Generator();
     
     private Generator(){
         // constructor 
     }
     
     public static Generator getInstance(){
         
        return generator;
     }
     
     public int Generate(int no){
           rand = new Random();
           return rand.nextInt((2 * no)^100);
     }
     
     public int Privatekey(){
            rand = new Random();
            return rand.nextInt(2^1000);
     }
     
     public int RandomKey(){
          String message = JOptionPane.showInputDialog(null, "Enter any Number:");
          return Integer.parseInt(message);
          
     }
     
     // String convert into character array
     public char[] messageToBytes(String message){
         char[] conversion;
         conversion = message.toCharArray();
         return conversion;
     }
     
     // compute Hash Function
     public int[] HashFunction(char[] message , int sign){
         int[] hash;
         hash = new int[message.length];
         for(int i =0; i < message.length; i++){
             hash[i] = (int)message[i] + sign;
         }
         return hash;
     }
     
     //compute signuture 
     public int[] signDoc(int[] message, int r, int k){
         int[] sign;
         sign = new int[message.length];
         
         for (int i = 0; i < message.length; i++) {
             
             sign[i] = k - sign[i] * r;
         }
         return sign;
     }
     
      // compute Hash Function
     public int[] HashFunctionforVerification(char[] message , int[] sign){
         int[] hash;
         hash = new int[message.length];
         for(int i =0; i < message.length; i++){
             hash[i] = (int)message[i] + sign[i];
         }
         return hash;
     }
     
     public boolean AuthVerfication(int [] hash, int hash2[]){
         Boolean run = false;
         int i =0;
         while (!run) {             
             run = !(hash[i] == hash2[i] && i <= hash.length);
             i++;
         }
         return run;
         
     }
     
     public int[] DecryptNumbers(char[] message , int[] sign){
         int[] hash;
         hash = new int[message.length];
         for(int i =0; i < message.length; i++){
             hash[i] = (int)message[i] - sign[i];
         }
         return hash;
     }
     public int[] _signDoc(int[] message, int r, int k){
         int[] sign;
         sign = new int[message.length];
         
         for (int i = 0; i < message.length; i++) {
             
             sign[i] = k + sign[i] / r;
         }
         return sign;
     }
     
      public int[] DecryptFunction(char[] message , int sign){
         int[] hash;
         hash = new int[message.length];
         for(int i =0; i < message.length; i++){
             hash[i] = (int)message[i] - sign;
         }
         return hash;
     }
     
     
    }
     
     
     
      
    
     
     
     
     
     
     
    
     
     

