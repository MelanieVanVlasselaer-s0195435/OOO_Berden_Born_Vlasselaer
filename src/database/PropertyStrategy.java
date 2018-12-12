package database;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

public class PropertyStrategy implements DBStrategy {
    Properties prop = new Properties();
    InputStream input = null;

    @Override
    public ArrayList load() {
        try {

            //input = new FileInputStream("bsldkfjevaluation.properties");
            InputStream input = new FileInputStream("testDatabase/evalution.properties");

            prop.load(input);

            System.out.println(prop.getProperty("evaluation.mode"));


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
        return null;
    }

    @Override
    public void save(ArrayList<String> elementen) {

    }
}
