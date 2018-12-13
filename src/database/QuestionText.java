package database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class QuestionText extends TxtDBStrategy {
    File questionFile;

    ArrayList<String> questionElementen = new ArrayList<>();
    public QuestionText() {
        //voor mac en windows gebruiker in de groep hun verschillende pathnames te ondersteunen -FB
        questionFile  = new File("testDatabase\\questionList.txt");

        if (questionFile.length() == 0) {
            questionFile  = new File("testDatabase/questionList.txt");
        }
    }
    @Override
    public ArrayList load() {
        try {
            Scanner scannerFile = new Scanner(questionFile);
            while (scannerFile.hasNextLine()) {
                Scanner scannerLijn = new Scanner(scannerFile.nextLine());
                scannerLijn.useDelimiter("/");
                String question = scannerLijn.next();
                questionElementen.add(question);
                String category = scannerLijn.next();
                questionElementen.add(category);
                String feedback = scannerLijn.next();
                questionElementen.add(feedback);
                String statemants = scannerLijn.next();
                questionElementen.add(statemants);
            }
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("Fout bij het inlezen", ex);
        }
        return questionElementen;
    }


    @Override
    public void save(ArrayList<String> questionElements) {
        File testFile = new File ("testDatabase\\TestList.txt");
        try {
            PrintWriter writer = new PrintWriter(testFile);
            for(int i = 0; i<questionElements.size()-3; i =i+4){
                writer.println (questionElements.get(i) + "/" + questionElements.get(i+1) + "/" + questionElements.get(i+2) + "/" + questionElements.get(i+3));
            }
            writer.close ();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("Fout bij het wegschrijven", ex);
        }

    }
    //abstracte klassen implementeren
}
