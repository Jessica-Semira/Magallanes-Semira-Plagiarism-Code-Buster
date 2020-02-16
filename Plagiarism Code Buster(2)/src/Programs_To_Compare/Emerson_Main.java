import java.io.*;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        File file = new File("C:\\Users\\11838000\\Desktop\\Codes\\Test 3.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        String code1 = "";
        String code2 = "";
        while((st=br.readLine())!=null)
        {
            System.out.println(st);
            code1=code1+st;
        }



        file = new File("C:\\Users\\11838000\\Desktop\\Codes\\Test 4.txt");
        br = new BufferedReader(new FileReader(file));
        while((st=br.readLine())!=null)
        {
            System.out.println(st);
            code2=code2+st;
        }

        code1=code1.replaceAll("\\s","");
        code2=code2.replaceAll("\\s","");

        System.out.println("CODE 1:"+code1);
        System.out.println("CODE 2:"+code2);

        CompareCode comp = new CompareCode();

        comp.compare(code1,code2);

    }
}
