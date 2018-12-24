package database;

import database.DBStrategy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public  class TxtDBStrategy implements DBStrategy {

    //File categoryFile;
    //File questionFile;
    InputStream categoryFile;
    InputStream questionFile;

    public TxtDBStrategy(){
        /*
        categoryFile  = new File("testDatabase\\categoryList.txt");
        if (categoryFile.length() == 0) {
            categoryFile  = new File("testDatabase/categoryList.txt");
        }

        questionFile  = new File("testDatabase\\questionList.txt");

        if (questionFile.length() == 0) {
            questionFile  = new File("testDatabase/questionList.txt");
        }
        */
        categoryFile = this.getClass().getResourceAsStream("categoryList.txt");
        questionFile = this.getClass().getResourceAsStream("questionList.txt");


    }


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
        return categorieElements;
    }

    private ArrayList loadQuestions() {
        ArrayList<String> questionElements = new ArrayList<>();
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
        return questionElements;
    }


    private void saveCategories(ArrayList <String> categorieElementen) {
        /*
        try {
            PrintWriter writer = new PrintWriter(categoryFile);
            for(int i = 0; i<categorieElementen.size()-2; i =i+3){
                writer.println (categorieElementen.get(i) + "/" + categorieElementen.get(i+1) + "/" + categorieElementen.get(i+2));
            }
            writer.close ();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("Fout bij het wegschrijven", ex);
        }
        */
    }

    private void saveQuestions(ArrayList <String> questionElements) {
        /*
        try {
            PrintWriter writer = new PrintWriter(questionFile);
            for(int i = 0; i<questionElements.size()-3; i =i+4){
                writer.println (questionElements.get(i) + "/" + questionElements.get(i+1) + "/" + questionElements.get(i+2) + "/" + questionElements.get(i+3));
            }
            writer.close ();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("Fout bij het wegschrijven", ex);
        }
        */
    }

}
