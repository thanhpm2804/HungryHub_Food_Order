/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filter;

import java.security.MessageDigest;
import org.apache.tomcat.util.codec.binary.Base64;

/**
 *
 * @author lenovo
 */
public class MaHoa {
    
    public static String toSHA1(String str){
        String salt = "sjkfaklsdjfklajsdfklsdgvcvdsg";
        String result = null ;
        
        str = str + salt ;
        try {
            byte[] dataBytes = str.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            result = Base64.encodeBase64String(dataBytes) ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;       
    }
    public static void main(String[] args) {
        System.out.println(toSHA1("123"));//MTIzc2prZmFrbHNkamZrbGFqc2Rma2xzZGd2Y3Zkc2c=
        if(toSHA1("123").equalsIgnoreCase("MTIzc2prZmFrbHNkamZrbGFqc2Rma2xzZGd2Y3Zkc2c=") ){
            System.out.println("dung");
        }
    }
}
