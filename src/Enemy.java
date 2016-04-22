import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by parapapapa on 06.03.2016.
 */
public class Enemy extends Unit{
    static ArrayList<Enemy> pattern;
    int health;
    int speed;
    int damage;

    /*
    * Загрузка шаблонов из конфигурационного файла
    * */
    static void loadConfig( String propName, FileWriter logger) throws IOException {
        ArrayList<String> enemies= Main.readConfig(propName,logger);
        Properties properties=new Properties();
        if(!new File(propName).exists())
            properties.load(new Object().getClass().getResourceAsStream("/" + propName));
        else properties.load(new FileInputStream(propName));
        Enemy.pattern=new ArrayList<Enemy>(3);
        for(String t:enemies){
            Enemy.pattern.add(
                    new Enemy(0,0,
                            new Dimension(Integer.parseInt( properties.getProperty(t+".width")),
                                          Integer.parseInt(properties.getProperty(t+".height"))),
                            Integer.parseInt(properties.getProperty(t+".health")),
                            Integer.parseInt(properties.getProperty(t+".speed")),
                            Integer.parseInt(properties.getProperty(t+".damage")),
                            Unit.created)
            );
            if(new File(properties.getProperty(t+".icon")).exists())
                Unit.textures[Unit.created]=new ImageIcon(ImageIO.read(new File(properties.getProperty(t + ".icon"))));
            else
                Unit.textures[Unit.created] = new ImageIcon(ImageIO.read(new Object().getClass().getResourceAsStream("/"+properties.getProperty(t+".icon"))));
            //System.out.println(properties.getProperty(t + ".icon")+" "+created );
            Unit.created+=1;
        }
    }
    public Enemy(int x,int y,Dimension size,int hp, int ms,int dmg,int texture){
        this.texture=texture;
        this.position=new Point(x,y);
        damage =dmg;
        speed=ms;
        health=hp;
        this.size=size;
    }

    public Enemy(int x,int y){
        this.position=new Point(x,y);
        damage =1;
        speed=1;
        health=1;
        size=new Dimension(20,20);
    }

    public int getDamage() {
        return damage;
    }

    public int getHealth() {
        return health;
    }

    public int getSpeed() {
        return speed;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
