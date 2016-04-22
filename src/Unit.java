import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.Scanner;

/**
 * Created by parapapapa on 06.03.2016.
 */
public class Unit {
    static ImageIcon[] textures;
    static int created;

    Point position;
    Dimension size;
    int texture;


    /*
    * get and set
    * @waves = 1
      number of units
      [index][index of portal]
    * */


    boolean isOver(int x,int y){
        return ((x>position.x-size.width/2 && x<position.x+size.width/2)
                &&(y>position.y-size.height/2 && y< position.y+size.height/2));
    }

    public Dimension getSize() {
        return size;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public void setSize(Dimension size) {
        this.size = size;
    }

    public Image getTexture() {
        return textures[texture].getImage();
    }
    public ImageObserver getObserver(){
        return textures[texture].getImageObserver();
    };

    public void setTexture(int texture) {        this.texture = texture;    }

    public void draw(Graphics2D g){
        //System.out.println(texture + " " + Unit.created);
        g.drawImage(getTexture(),position.x-size.width/2,position.y-size.height/2,size.width,size.height,getObserver());
    }
}
