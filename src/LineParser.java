import java.util.Scanner;

/**
 * Created by parapapapa on 18.04.2016.
 */
public class LineParser {
    Scanner scanner;
    int numbers[];
    int index;


    public LineParser(Scanner arg1){
        scanner = arg1;
    }
    public int nextInt(){
        if (numbers==null || numbers.length - 1 < index){
            boolean valid=false;
            while(!valid) {
                String line = scanner.nextLine();
                if(line.startsWith("#") || line.startsWith("//"))continue;
                numbers = parseIntegers(line);
                if(numbers.length==0)continue;
                valid=true;
                index=0;
            }
        }
        return numbers[index++];
    }
    static int[] parseIntegers(String str){

        int numbers[];
        int N=0;
        int i=-1;
        boolean integer=false;
        for(int k=0;k<str.length();k++)
            if(str.charAt(k)>='0' && str.charAt(k)<='9' ){
                if( !integer) {
                    integer = true;
                    i = k;
                }
            }
            else{
                if(i!=-1)
                    N++;
                i=-1;
                integer=false;
            }
        if(i!=-1)N++;
        numbers=new int[N];

        i=-1;
        N=0;
        integer=false;
        for(int k=0;k<str.length();k++)
            if(str.charAt(k)>='0' && str.charAt(k)<='9') {
                if(!integer){
                    integer=true;
                    i = k;
                }
            }
            else{
                if(i!=-1){
                    numbers[N++]=Integer.parseInt(str.substring(i, k));
                    integer=false;
                }
                i=-1;
            }
        if(i!=-1)numbers[N++]=Integer.parseInt(str.substring(i));
       // System.out.print("Parsing \"" + str + "\" to ");
        //for(int f:numbers)System.out.print(f+"  ");
       // System.out.println();
        return numbers;
    }
}
