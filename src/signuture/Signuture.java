/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signuture;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author Ali Hassan
 */
public class Signuture {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       Random rand = new Random();
       Generator g = Generator.getInstance();
       
        // Range : 0 to 1000 </ 
       int G = g.Generate(rand.nextInt(1000));
       int a = g.Privatekey();
       int A = ((G^a) % G);
       int k = g.RandomKey();
       int r = ((G ^ k)% G);
       
       
       String message = JOptionPane.showInputDialog(null, "ENTER Any text");
       System.out.println("Cipher Text .....");
       
       char cipherText[] = g.messageToBytes(message);
        
       for (int i = 0; i < cipherText.length; i++) {
            System.out.print(cipherText[i]);
        }
        System.out.println("");
       
        //Hash function => E = H(m|r)
        System.out.println("Hash Function...");
       int hash[] = g.HashFunction(cipherText, r);
       
       for (int i =0; i < hash.length; i++){
           System.out.print(hash[i]);
       }
       System.out.println("");
       // Sign Documents => S = k-E*a
       System.out.println("Sign Documents.....");
       int sign[] = g.signDoc(hash, r, a);
       for (int i =0; i < hash.length; i++){
           System.out.print(sign[i]);
       }
       System.out.println("");
       
       // Verification rv = G^S * A ^ E
       System.out.println("Verification....");
       int[] verify;
       verify = new int[sign.length];
        
       for (int i = 0; i < sign.length; i++) {   
           verify[i] = (g.Generate(sign[i]) * A ^ hash[i]);
           System.out.print("" + verify[i]);
        } 
        System.out.println("");
        int[] encrypt = g.HashFunctionforVerification(cipherText, verify);
        
        
        if(g.AuthVerfication(hash, encrypt)){
            System.out.println("" + Arrays.toString(hash) + Arrays.toString(encrypt));
        }else{
            System.err.println("Encrption didn't perform well");
            System.exit(-1);
        }
        
        // Dencrpytion 
        System.out.println("Decryption.....");
         int[] decrypt = g.DecryptNumbers(cipherText, verify);
         
         for (int i = 0; i < decrypt.length; i++) {
             System.out.println("" + Arrays.toString(decrypt));
          }
         System.out.println("");
       
       int[] verify_de;
       verify_de = new int[sign.length];
        
       for (int i = 0; i < sign.length; i++) {   
           verify_de[i] = (g.Generate(sign[i]) / A ^ hash[i]);
           System.out.print("" + verify_de[i]);
        } 
        System.out.println("");
        
        int design[] = g._signDoc(verify_de, r, a);
       for (int i =0; i < design.length; i++){
           System.out.print(design[i]);
       }
       System.out.println("");
    
    }
    
}
