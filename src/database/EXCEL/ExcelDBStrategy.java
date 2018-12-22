package database.EXCEL;

import database.DBStrategy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

//Opmerking: hier simuleren wij waar de excel vragen en antwoorden dmv adapter zouden geimplimenteerd moeten worden
// Aangezien wij het omzetten van de excel-plug naar de functionaliteit van onze app als geen basiscompetentie van dit vak beschouwden,
// hebben wij gekozen om hier niet teveel tijd in te steken.
// Hier zou in theorie dus een adapter gebruikt worden voor de excel plugin waarbij wij de .write resultaten converteerden naar een lijst van Strings
// (zoals in de rest van de applicate gebruikt word)

public class ExcelDBStrategy implements DBStrategy {
    File categoryFile;
    File questionFile;

    public ExcelDBStrategy(){
        categoryFile  = new File("testDatabase\\categoryExcelList.txt");
        if (categoryFile.length() == 0) {
            categoryFile  = new File("testDatabase/categoryExcelList.txt");
        }

        questionFile  = new File("testDatabase\\questionExcelList.txt");

        if (questionFile.length() == 0) {
            questionFile  = new File("testDatabase/questionExcelList.txt");
        }

    }


    //Dit kan misschien een interface worden - TB
    public ArrayList load(){
        ArrayList<ArrayList<String>> testinformatie = new ArrayList<>();
        testinformatie.add(0,this.loadCategories());
        testinformatie.add(1,this.loadQuestions());
        return testinformatie;
    }


    public void save(ArrayList elementen){
        ArrayList<ArrayList<String>> testElementen = (ArrayList<ArrayList<String>>) elementen;
        this.saveCategories(testElementen.get(0));
        this.saveQuestions(testElementen.get(1));
    }

    private ArrayList<String> loadCategories() {
        ArrayList<String> categorieElements = new ArrayList<>();

        try {
            Scanner scannerFile = new Scanner(categoryFile);
            while (scannerFile.hasNextLine()) {
                Scanner scannerLijn = new Scanner(scannerFile.nextLine());
                scannerLijn.useDelimiter("/");
                String name = scannerLijn.next();
                categorieElements.add(name);
                String description = scannerLijn.next();
                categorieElements.add(description);
                String hoofdcategorie = scannerLijn.next();
                categorieElements.add(hoofdcategorie);
            }
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("Fout bij het inlezen", ex);
        }
        return categorieElements;
    }

    private ArrayList loadQuestions() {
        ArrayList<String> questionElements = new ArrayList<>();
        try {
            Scanner scannerFile = new Scanner(questionFile);
            while (scannerFile.hasNextLine()) {
                Scanner scannerLijn = new Scanner(scannerFile.nextLine());
                scannerLijn.useDelimiter("/");
                String question = scannerLijn.next();
                questionElements.add(question);
                String category = scannerLijn.next();
                questionElements.add(category);
                String feedback = scannerLijn.next();
                questionElements.add(feedback);
                String statemants = scannerLijn.next();
                questionElements.add(statemants);
            }
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("Fout bij het inlezen", ex);
        }
        return questionElements;
    }


    private void saveCategories(ArrayList <String> categorieElementen) {
        try {
            PrintWriter writer = new PrintWriter(categoryFile);
            for(int i = 0; i<categorieElementen.size()-2; i =i+3){
                writer.println (categorieElementen.get(i) + "/" + categorieElementen.get(i+1) + "/" + categorieElementen.get(i+2));
            }
            writer.close ();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("Fout bij het wegschrijven", ex);
        }
    }

    private void saveQuestions(ArrayList <String> questionElements) {
        try {
            PrintWriter writer = new PrintWriter(questionFile);
            for(int i = 0; i<questionElements.size()-3; i =i+4){
                writer.println (questionElements.get(i) + "/" + questionElements.get(i+1) + "/" + questionElements.get(i+2) + "/" + questionElements.get(i+3));
            }
            writer.close ();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("Fout bij het wegschrijven", ex);
        }
    }

}
