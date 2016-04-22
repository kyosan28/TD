import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.Position;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

/**
 * Created by parapapapa on 06.03.2016.
 */
public class Fortress extends Unit{
    static ArrayList<Fortress> pattern;
    int health;



    /*
    * Загрузка шаблонов из конфигурационного файла
    * */
    static void loadConfig(String propName, FileWriter logger) throws IOException {
        ArrayList<String> forts= Main.readConfig(propName,logger);
        Properties properties=new Properties();
        if(!new File(propName).exists())
            properties.load(new Object().getClass().getResourceAsStream("/" + propName));
        else properties.load(new FileInputStream(propName));
        Fortress.pattern=new ArrayList<Fortress>(1);
        for(String t:forts){
            Fortress.pattern.add(
                    new Fortress(
                            Integer.parseInt(properties.getProperty(t + ".health")),
                            new Dimension(Integer.parseInt( properties.getProperty(t+".width")),
                                    Integer.parseInt(properties.getProperty(t+".height"))
                            ),0,0,Unit.created)  );
            if(new File(properties.getProperty(t + ".icon")).exists())
                Unit.textures[Unit.created]=new ImageIcon(ImageIO.read(new File(properties.getProperty(t+".icon"))));
            else
                Unit.textures[Unit.created] = new ImageIcon(ImageIO.read(new Object().getClass().getResourceAsStream("/"+properties.getProperty(t+".icon"))));

            //System.out.println(properties.getProperty(t + ".icon")+" "+created );
            Unit.created+=1;
        }
    }




    public Fortress(int h,Dimension sz,int x,int y,int texture){
        this.texture=texture;
        health=h;
        size=sz;
        position=new Point(x,y);
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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}

