package database;

import java.io.File;
import java.io.FileNotFoundException;
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
                scannerLijn.useDelimiter("/");
                String question = scannerLijn.next();
                scoreList.add(question);
                String category = scannerLijn.next();
                scoreList.add(category);
                String feedback = scannerLijn.next();
                scoreList.add(feedback);
                String statemants = scannerLijn.next();
                scoreList.add(statemants);
            }
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("Fout bij het inlezen", ex);
        }
        return scoreList;
    }

    @Override
    public void save(ArrayList<String> elementen) {
        
    }
}
