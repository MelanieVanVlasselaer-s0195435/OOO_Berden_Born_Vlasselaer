package database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ScoreStrategy implements  DBStrategy {
    File scoreFile;

    ArrayList<String> scoreList= new ArrayList<>();
    public ScoreStrategy() {
        scoreFile  = new File("testDatabase\\scoreList.txt");

        if (scoreFile.length() == 0) {
            scoreFile  = new File("testDatabase/scoreList.txt");
        }
    }
    @Override
    public ArrayList load() {
        try {
            Scanner scannerFile = new Scanner(scoreFile);
            while (scannerFile.hasNextLine()) {
                Scanner scannerLijn = new Scanner(scannerFile.nextLine());
                scannerLijn.useDelimiter("///");
                String x = scannerLijn.next();

                scoreList.add(x);

            }
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("Fout bij het inlezen", ex);
        }
        return scoreList;
    }

    @Override
    public void save(ArrayList<String> elementen) {
        try {
            PrintWriter writer = new PrintWriter(scoreFile);
            for (String x: elementen) {
                writer.println(x);
            }
            writer.close ();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("Fout bij het wegschrijven", ex);
        }
    }
}
