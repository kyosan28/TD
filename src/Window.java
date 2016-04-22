import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.ImagingOpException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by parapapapa on 06.03.2016.
 */
public class Window extends JFrame{


    public Window() throws IOException {
        setSize(600,400);
        setDefaultCloseOperation(3);
        Canvas c=new Canvas();
        add(c);
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        setVisible(true);
    }

    public void action(int x,int y){
        for(Tower tmp:Game.tower)
            if( tmp.isOver(x, y)){
                //TODO: action for tower
                return;
            }

        for(int i=0;i<Game.t_place.length;i++)
            if(!Game.is_t_place_empty[i]){
                //TODO: action for place
                return;
            }
    }

    class Canvas extends JComponent{
        ImageIcon bg;
        public Canvas() throws IOException {
            if(new File(Main.bg).exists())
                bg=new ImageIcon(ImageIO.read(new File(Main.bg)));
            else
                bg = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/"+Main.bg)));

        }
        @Override
        public void paintComponent(Graphics g){
            Graphics2D g2d=(Graphics2D)g;
            g2d.drawImage(bg.getImage(),0,0,bg.getImageObserver());
            Game.DrawGame(g2d);


        }

    }
}
