import java.util.Scanner;

/**
 * Created by parapapapa on 06.03.2016.
 */
public class Path {
    Point[] p;

    static int[][][] loadScenario(Scanner sc,String line){
        int result[][][];
        LineParser lp=new LineParser(sc);
        line=line.substring(line.lastIndexOf('=')+1);
        result=new int[LineParser.parseIntegers(line)[0]][][];
        int now=0;
        for(int i=0;i<result.length;i++){
            result[i]=new int[lp.nextInt()][2];
            for(int j=0;j<result[i].length;j++)
                for(int k=0;k< result[i][j].length;k++)
                    result[i][j][k] = lp.nextInt();
        }
        return result;
    }
}
