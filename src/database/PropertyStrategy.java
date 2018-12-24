package database;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

public class PropertyStrategy implements DBStrategy {
    Properties prop = new Properties();
    InputStream input;
    OutputStream output;
    ArrayList <String> elementen = new ArrayList<>();
    @Override
    public ArrayList load() {
        try {
            input = new FileInputStream("testDatabase/game.properties");
            prop.load(input);
            elementen.add(prop.getProperty("evaluation.mode"));
            elementen.add(prop.getProperty("source.mode"));
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return elementen;
    }

    @Override
    public void save(ArrayList elementen) {
        ArrayList<String> strategies = (ArrayList<String>) elementen;
        String strategy = strategies.get(0);
        String source = strategies.get(1);
        try {
            output = new FileOutputStream("testDatabase/game.properties");
            prop.setProperty("evaluation.mode",strategy);
            prop.setProperty("source.mode",source);
            prop.store(output, null);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
