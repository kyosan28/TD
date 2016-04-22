import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

/**
 * Created by parapapapa on 06.03.2016.
 */
public class Main {


    public static String portal;
    public static String fortress;
    public static String tower;
    public static String enemy;
    public static String scenario;
    public static String bg;
    public static String log;

    public static void main(String args[]){
        try {
            configReader(null);
            new Window();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public static void configReader(String config_file) throws Exception {
        if(config_file==null)config_file="Configs/default.properties";
        File conf = new File(config_file);
        Properties properties=new Properties();
        InputStream propReader=null;
        Unit.textures=new ImageIcon[128];
        Unit.created=0;
        if(!conf.exists())
            propReader=new Object().getClass().getResourceAsStream("/Configs/default.properties");
        else propReader = new FileInputStream(conf);
        properties.load(propReader);

        log=properties.getProperty("log",null);
        FileWriter f=null;
        if(log.length()>0)
             f=new FileWriter(new File(log));
        if(f!=null){
            portal = properties.getProperty("portals");
            f.write("Portals config "+portal+"\n");
            fortress = properties.getProperty("fortress");
            f.write("Fortress config "+fortress+"\n");
            tower   = properties.getProperty("towers");
            f.write("Tower config "+tower+"\n");
            enemy = properties.getProperty("units");
            f.write("Enemy config "+enemy+"\n");
            scenario = properties.getProperty("scenario");
            f.write("Scenario config "+scenario+"\n");
            bg = properties.getProperty("background");
            f.write("Background config "+bg+"\n");
        } else {
            portal = properties.getProperty("portals");
            fortress = properties.getProperty("fortress");
            tower = properties.getProperty("towers");
            enemy = properties.getProperty("units");
            scenario = properties.getProperty("scenario");
            bg = properties.getProperty("background");
        }


        Fortress.loadConfig(fortress, f);
        Spawner.loadConfig(portal,f);
        Tower.loadConfig (tower,f);
        Enemy.loadConfig(enemy, f);
        int[][][][] template=readScenario(scenario);
        Game.newGame(template);
        for(int[][][] a:template){
            for(int[][] b:a){
                for(int[] c:b){
                    for(int d:c)
                        f.write(d + " ");
                }
                f.write("\n");
            }
            f.write("\n-------------------------------------\n");
        }
        f.write("Config loaded");
        f.close();
    }

    static int[][][][] readScenario(String filename) throws FileNotFoundException {
        int results[][][][]=new int[5][1][][];
        File sc=new File(filename);
        if(!sc.exists())return results;
        Scanner reader=new Scanner(new FileInputStream(sc));
        String line= reader.nextLine();
        while(reader.hasNextLine()){
            if(line.startsWith("#") || line.startsWith("//")) {
                line = reader.nextLine();
                //System.out.println(line);
                continue;
            }
            if(line.startsWith("@")){
                line=line.substring(1);

                //System.out.println(line);
                if(line.startsWith("spawner") )results[0][0]= Spawner.loadScenario(reader,line);
                if( line.startsWith("fort"))   results[1][0]=Spawner.loadScenario(reader,line);
                //line.startsWith("fort")Fortress.loadScenario(reader,line);
                if(line.startsWith("towerplace"))results[2][0]=Tower.loadScenario(reader,line);
                if(line.startsWith("path") )results[3]=Path.loadScenario(reader,line);
                if( line.startsWith("waves"))results[4]=Path.loadScenario(reader,line);
            }

            line = reader.nextLine();
        }
        return results;
    }


    static ArrayList <String> readConfig(String str, FileWriter logger) throws IOException {

        Integer buffer[]=new Integer[1024];
        Scanner f_reader;
        File f_file=new File(str);
        ArrayList <String> result=new ArrayList<String>(3);
        if(!f_file.exists()&& logger!=null){
            logger.write("File not exists: "+f_file.getName());
            System.exit(1);
        }
        f_reader=new Scanner(new FileInputStream(f_file));
        String line= f_reader.nextLine();
        while(f_reader.hasNextLine() ) {
            if (line.startsWith("#"))
                result.add(line.substring(1));
            line = f_reader.nextLine();
        }
        return result;
    }


}

