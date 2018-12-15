package database;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

public class PropertyStrategy implements DBStrategy {
    Properties prop = new Properties();
    InputStream input = null;
    OutputStream output;
    ArrayList <String> elementen = new ArrayList<>();
    @Override
    public ArrayList load() {
        try {
            InputStream input = new FileInputStream("testDatabase/evalution.properties");
            prop.load(input);
            elementen.add(prop.getProperty("evaluation.mode"));
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
    public void save(ArrayList<String> strategies) {
        String strategy = strategies.get(0);
        try {
            OutputStream output = new FileOutputStream("testDatabase/evalution.properties");
            prop.setProperty("evaluation.mode",strategy);
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
