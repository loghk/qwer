package qwe;

import java.io.*;

class Test4{
    public static void main(String[] args) throws IOException
    {
        FileInputStream in = new FileInputStream("D:\\sxz_java\\qwer\\src\\qwe/1.png");
        FileOutputStream out = new FileOutputStream("D:\\sxz_java\\qwer\\src\\qwe/2.png");
        int temp = 0;
        while ((temp = in.read())!=-1){
            temp ^= temp;
            out.write(temp);
        }
        in.close();
        out.close();
        FileInputStream in1 = new FileInputStream("D:\\sxz_java\\qwer\\src\\qwe/2.png");
        FileOutputStream out1 = new FileOutputStream("D:\\sxz_java\\qwer\\src\\qwe/3.png");
        while ((temp = in1.read())!=-1){
            temp ^= temp;
            out1.write(temp);
        }
        in1.close();
        out1.close();
        System.out.println("over");
    }
}