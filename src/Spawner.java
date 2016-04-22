import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

/**
 * Created by parapapapa on 06.03.2016.
 */
public class Spawner extends Unit{
    //This is only bg unit
    static ArrayList<Spawner> pattern;

    /*
* Загрузка шаблонов из конфигурационного файла
* */
    static void loadConfig( String propName, FileWriter logger) throws IOException {
        ArrayList<String> spawners= Main.readConfig(propName,logger);
        Properties properties=new Properties();
        if(!new File(propName).exists())
            properties.load(new Object().getClass().getResourceAsStream("/" + propName));
        else properties.load(new FileInputStream(propName));
        Spawner.pattern=new ArrayList<Spawner>(3);
        for(String t:spawners){
            Spawner.pattern.add(
                    new Spawner(0,0,new Dimension(Integer.parseInt( properties.getProperty(t+".width")),
                                    Integer.parseInt(properties.getProperty(t+".height"))),Unit.created));
            if(new File(properties.getProperty(t+".icon")).exists())
                Unit.textures[Unit.created]=new ImageIcon(ImageIO.read(new File(properties.getProperty(t+".icon"))));
            else
                Unit.textures[Unit.created] = new ImageIcon(ImageIO.read(new Object().getClass().getResourceAsStream("/"+properties.getProperty(t+".icon"))));
            //System.out.println(properties.getProperty(t + ".icon")+" "+created );
            Unit.created+=1;
        }
    }
    public Spawner(int x,int y,Dimension size,int texture_number){
        this.texture=texture_number;
        position = new Point(x,y);
        this.size=size;
    }

    static int[][] loadScenario(Scanner sc,String line){
        int result[][];
        LineParser lp=new LineParser(sc);
        line=line.substring(line.lastIndexOf('=')+1);
        result=new int[LineParser.parseIntegers(line)[0]][3];
        int now=0;
        for(int i=0;i<result.length;i++)
            for(int j=0;j<result[i].length;j++)
                result[i][j] = lp.nextInt();
        return result;
    }
}
