

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * @Author 李景磊
 * @Description
 * @Date 2019/2/8 0:37
 * @ModifiedBy：
 */
public class Sound {
    public static void main(String[] args) {
change(235);
    }
    public static void change(int num) {
        String string = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //余数
        int i=num%26-1;
        int j=(num-i)/26;
        int k=j/26;
        System.out.println(i);
        System.out.println(j);
        System.out.println(k);
        StringBuffer s=new StringBuffer();
        s.append(string.charAt(i));
        if (j>0){
            s.append(string.charAt(j));
            if (k>0){
                s.append(string.charAt(k));
            }
        }else if (k>0){
            s.append(string.charAt(k));
        }
        System.out.println(s.reverse().toString());
}}


