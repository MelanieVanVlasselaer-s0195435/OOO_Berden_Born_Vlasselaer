package database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ScoreStrategy implements  DBStrategy {
    //File scoreFile;
    InputStream scoreFile;

    ArrayList<String> scoreList= new ArrayList<>();
    public ScoreStrategy() {
        /*
        scoreFile  = new File("testDatabase\\scoreList.txt");

        if (scoreFile.length() == 0) {
            scoreFile  = new File("testDatabase/scoreList.txt");
        }
        */
        scoreFile = this.getClass().getResourceAsStream("scoreList.txt");
    }
    @Override
    public ArrayList load() {
        Scanner scannerFile = new Scanner(scoreFile);
        while (scannerFile.hasNextLine()) {
            Scanner scannerLijn = new Scanner(scannerFile.nextLine());
            scannerLijn.useDelimiter("///");
            String x = scannerLijn.next();

            scoreList.add(x);

        }
        return scoreList;
    }

    @Override
    public void save(ArrayList elementen) {
        /*
        ArrayList<String> scores = (ArrayList<String>) elementen;
        try {
            PrintWriter writer = new PrintWriter(scoreFile);
            for (String x: scores) {
                writer.println(x);
            }
            writer.close ();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("Fout bij het wegschrijven", ex);
        }
        */
    }

}
