package Thread;


import java.io.*;

public class Test {
    public static void main(String[] args) throws IOException
    {
        try(
                OutputStream outputStream = new FileOutputStream("D:/1.txt");
                InputStream in = new FileInputStream("D:/1.txt");
                )
        {
            int num = 0;
            int zimu = 0;
            int other = 0;
            int n;
            while ((n = in.read())!=-1){
                if((n>='a'&&n<='z')||(n>='A'&&n<='Z')){
                    zimu++;
                }
                else if (n>='0'&&n<='9'){
                    num++;
                }
                else if (n=='\r'||n=='\n'){
                    System.out.println("r/n");
                }
                else other++;
            }
            System.out.println("数字:"+num+" 字母:"+zimu+" 其他:"+other);
        }
    }
}
