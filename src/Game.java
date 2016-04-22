import javax.swing.*;
import java.awt.*;

/**
 * Created by parapapapa on 06.03.2016.
 */
public class Game {

    public static Fortress[] fortress;
    public static Spawner[] spawners;

    public static Enemy[][] enemies_to_spawn;
    public static boolean[] is_t_place_empty;
    public static int index_of_unit_in_wave;
    public static Point[] t_place;
    public static Path[] pathes;
    public static int wave;

    public static Enemy enemies[];
    public static Tower[] tower;




    public void action(Graphics2D g,int x,int y){
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



    public static void newGame(int[][][][] scenario) {
        spawners = new Spawner[scenario[0][0].length];
        for (int i = 0; i < spawners.length; i++) {
            int type = scenario[0][0][i][0];
            int x = scenario[0][0][i][1];
            int y = scenario[0][0][i][2];
            spawners[i] = new Spawner(x, y, Spawner.pattern.get(type).size, Spawner.pattern.get(type).texture);
        }

        fortress = new Fortress[scenario[1][0].length];
        for (int i = 0; i < fortress.length; i++) {
            int type = scenario[1][0][i][0];
            int x = scenario[1][0][i][1];
            int y = scenario[1][0][i][2];
            fortress[i] = new Fortress(Fortress.pattern.get(type).health, Fortress.pattern.get(type).size, x, y, Fortress.pattern.get(type).texture);
        }

        t_place = new Point[scenario[2][0].length];
        System.out.println(" "+t_place.length);
        is_t_place_empty = new boolean[t_place.length];
        for (int i = 0; i < t_place.length; i++) {
            int x = scenario[2][0][i][0];
            int y = scenario[2][0][i][1];
            t_place[i] = new Point(x, y);
            is_t_place_empty[i] = false;
        }

        enemies_to_spawn = new Enemy[scenario[4].length][];
       // System.out.println("len" + scenario[4].length);
        for (int i = 0; i < enemies_to_spawn.length; i++) {
           // System.out.println("len . "+scenario[4][i].length);
            enemies_to_spawn[i]=new Enemy[scenario[4][i].length];
            for (int j = 0; j < enemies_to_spawn[i].length; j++) {
                int unit = scenario[4][i][j][0];
                int spawner = scenario[4][i][j][1];

               // System.out.println("u "+unit+" s "+spawner);
                enemies_to_spawn[i][j] = new Enemy(
                        spawners[spawner].getPosition().getX(),
                        spawners[spawner].getPosition().getY(),
                        Enemy.pattern.get(unit).getSize(),
                        Enemy.pattern.get(unit).getHealth(),
                        Enemy.pattern.get(unit).getDamage(),
                        Enemy.pattern.get(unit).getSpeed(),
                        Enemy.pattern.get(unit).texture);
            }
            enemies=new Enemy[0];
            tower=new Tower[0];

        }

        pathes = new Path[scenario[3].length];
        for (int i = 0; i < pathes.length; i++) {
            pathes[i]=new Path();
            pathes[i].p=new Point[scenario[3][i].length];
            for (int j = 0; j <  pathes[i].p.length; j++) {
                int x = scenario[3][i][j][0];
                int y = scenario[3][i][j][1];
                pathes[i].p[j]=new Point(x,y);
            }
        }

    }


    public static void DrawGame(Graphics2D g){
        for(Spawner sp:spawners)sp.draw(g);
        for(Fortress f:fortress)f.draw(g);
        for(Enemy e:enemies)e.draw(g);
        for(Tower t:tower)t.draw(g);
        Paint p=g.getPaint();
        g.setPaint(Color.black);
        for(int i=0;i<t_place.length;i++)
            if(!is_t_place_empty[i]){
                g.fillOval(t_place[i].x - 12, t_place[i].y - 12, 24, 24);
                System.out.println(""+t_place[i].x);
            }
        g.setPaint(p);

    }

}
