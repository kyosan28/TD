import javax.imageio.ImageIO;
import javax.swing.*;
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
public class Tower extends Unit{

    static ArrayList<Tower> pattern;
    // 1/speed = freq
    double speed;
    // damage for one shoot
    int damage;
    // range of shooting
    int range;

    int price;
    int sellPrice;


    /*
    * Загрузка шаблонов из конфигурационного файла
    * */
    static void loadConfig( String propName, FileWriter logger) throws IOException {
        ArrayList<String> towers= Main.readConfig(propName,logger);
        Properties properties=new Properties();
        if(!new File(propName).exists())
            properties.load(new Object().getClass().getResourceAsStream("/" + propName));
        else properties.load(new FileInputStream(propName));
        Tower.pattern=new ArrayList<Tower>(3);
        for(String t:towers){
            Tower.pattern.add(
                    new Tower(Integer.parseInt(properties.getProperty(t+".cost")),
                            Integer.parseInt(properties.getProperty(t+".sell_cost")),
                            Integer.parseInt(properties.getProperty(t+".range")),
                            Integer.parseInt(properties.getProperty(t+".damage")),
                            Integer.parseInt(properties.getProperty(t+".speed")),
                            new Dimension(Integer.parseInt( properties.getProperty(t+".width")),
                                    Integer.parseInt(properties.getProperty(t+".height"))
                            ),0,0,Unit.created)  );
            if(new File(properties.getProperty(t+".icon")).exists())
                Unit.textures[Unit.created]=new ImageIcon(ImageIO.read(new File(properties.getProperty(t + ".icon"))));
            else
                Unit.textures[Unit.created] = new ImageIcon(ImageIO.read(new Object().getClass().getResourceAsStream("/"+properties.getProperty(t+".icon"))));
            //System.out.println(properties.getProperty(t + ".icon")+" "+created );

            Unit.created+=1;
        }
    }
    static int[][] loadScenario(Scanner sc,String line){
        int result[][];
        LineParser lp=new LineParser(sc);
        line=line.substring(line.lastIndexOf('=')+1);
        result=new int[LineParser.parseIntegers(line)[0]][2];
        int now=0;
        for(int i=0;i<result.length;i++)
            for(int j=0;j<result[i].length;j++)
                result[i][j] = lp.nextInt();
        return result;
    }

    public Tower(int arg1, int arg2, int arg3,int arg4,double arg5,Dimension size,int x, int y,int texture){
        this.texture=texture;
        speed=arg5;
        damage=arg4;
        range=arg3;
        price=arg1;
        sellPrice=arg2;
        position=new Point(x,y);
        this.size=size;
    }


}
