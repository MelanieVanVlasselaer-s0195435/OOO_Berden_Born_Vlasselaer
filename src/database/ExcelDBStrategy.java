package database;

import database.DBStrategy;
import excel.ExcelPlugin;
import jxl.read.biff.BiffException;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


//Opmerking: hier worden de vragen uit de excelLijst enkel ingelezen, maar niet weggeschreven. Dit luktte niet omdat de excelPluggin geen write functie
// heeft. We hebben hier wel de basis gelegd voor hoe dit zou moeten, maar de laatste stap ontbreekt dus wel.

public class ExcelDBStrategy implements DBStrategy {

    File excelFile;

    public ExcelDBStrategy(){
        excelFile  = new File("testDatabase\\testExcel.xls");
        if (excelFile.length() == 0) {
            excelFile  = new File("testDatabase/testExcel.xls");
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
            ArrayList<ArrayList<String>> excelCat = ExcelPlugin.read(excelFile,0);
            for (ArrayList<String> category : excelCat){
                for (String catElement : category){
                    categorieElements.add(catElement);
                }
            }
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return categorieElements;
    }

    private ArrayList loadQuestions() {
        ArrayList<String> questionElements = new ArrayList<>();
        try {
            ArrayList<ArrayList<String>> excelCat = ExcelPlugin.read(excelFile,1);
            for (ArrayList<String> question : excelCat){
                for (String queElement : question){
                    questionElements.add(queElement);
                }
            }
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questionElements;
    }


    private void saveCategories(ArrayList <String> categorieElementen) {
        ArrayList<ArrayList<String>> toWriteCat = new ArrayList<>();
        for(int i = 0; i<categorieElementen.size()-2; i =i+3){
            ArrayList<String> cat = new ArrayList();
            cat.add(categorieElementen.get(i));
            cat.add(categorieElementen.get(i+1));
            cat.add(categorieElementen.get(i+2));
            toWriteCat.add(cat);
        }
        //Excelplugin.write(toWriteCategories,Excelfile,0)
        JOptionPane.showMessageDialog(null,"Aanpassingen kunnen niet worden opgeslagen in Excel modus doordat de wegschrijf functie ontbrak in de plugin");
    }

    private void saveQuestions(ArrayList <String> questionElements) {
        ArrayList<ArrayList<String>> toWriteQuestions = new ArrayList<>();
        for(int i = 0; i<questionElements.size()-3; i =i+4){
            ArrayList<String> cat = new ArrayList();
            cat.add(questionElements.get(i));
            cat.add(questionElements.get(i+1));
            cat.add(questionElements.get(i+2));
            cat.add(questionElements.get(i+3));
            toWriteQuestions.add(cat);
        }
        //Excelplugin.write(toWriteQuestions,Excelfile,1)
    }

}
