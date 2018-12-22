package database.TXT;

import database.DBStrategy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public  class TxtDBStrategy implements DBStrategy {

    //in voorbeeld in les plaatst Fox de scanner /scannerfile etc in deze methoden, wij een niveau lager
    //hij plaatst elk gescant object in een arraylist van objecten
    //=> hij gaat uiteindelijk in het niveau van CatagoryTxt & VraagTXT de elementen omzetten naar vragen/categoriën => niet MVC?
    //-TB

    File categoryFile;
    File questionFile;

    public TxtDBStrategy(){
        categoryFile  = new File("testDatabase\\categoryList.txt");
        if (categoryFile.length() == 0) {
            categoryFile  = new File("testDatabase/categoryList.txt");
        }

        questionFile  = new File("testDatabase\\questionList.txt");

        if (questionFile.length() == 0) {
            questionFile  = new File("testDatabase/questionList.txt");
        }

    }


    //Dit kan misschien een interface worden - TB
    public ArrayList load(){
        ArrayList<ArrayList<String>> testinformatie = new ArrayList<>();
        testinformatie.add(0,this.loadCategories());
        testinformatie.add(1,this.loadQuestions());
        return testinformatie;

        //hier maak ik Linkedlist van Arraylisten (2) (1 met categorie-elementen en 1 met categorie vragen)
        //LinkedList.add(load categories)
        //linkedList.add(load questions)

    }


    public void save(ArrayList elementen){
        ArrayList<ArrayList<String>> testElementen = (ArrayList<ArrayList<String>>) elementen;
        this.saveCategories(testElementen.get(0));
        this.saveQuestions(testElementen.get(1));

        //hier geef ik Linkedlist van Arraylisten (2) (1 met categorie-elementen en 1 met categorie vragen)
        //this.saveCategories(linklist.get(0))
        //this.saveQuestions(linklist.get(1))

    }




    // Hier plaats ik de functies uit de oude klassen (uit de TXT strategy voor vraag en categorie met de functies saveCategories, saveQuestions, loadCategories,LoadQuestions
    // in totaal dus 4 methoden (2 x load, 2x save)

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

    //=> DEZE methode doen we ook voor ExcelDBSTrategy, maar dan uiteraard geen Scanner/writer voor TXt
        //+ EXCELDBSTrategy zal ook adapter zijn om aan het DBStrategy te voldoen


}
