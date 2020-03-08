package by.nareiko.finalProject.personalFinanceAccounting.dao.implementation;

import by.nareiko.finalProject.personalFinanceAccounting.bean.Expenditure;
import by.nareiko.finalProject.personalFinanceAccounting.dao.ExpenditureDAO;
import by.nareiko.finalProject.personalFinanceAccounting.dao.exception.DAOException;
import by.nareiko.finalProject.personalFinanceAccounting.service.CurrentUserHolder;
import by.nareiko.finalProject.personalFinanceAccounting.validation.DAOValidation;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class FileExpenditureDAO implements ExpenditureDAO {
  private static   Map<Long, Expenditure> expenditureReadMap = new HashMap<>();

    public static Map<Long, Expenditure> getExpenditureReadMap() {
        if (CurrentUserHolder.getInstance().getActiveUser() != null) {
            return expenditureReadMap;
        }
        return null;
    }

    @Override
    public void addExpenditure(Expenditure expenditure) throws DAOException, IOException {
    boolean validation = DAOValidation.checkExpenditure(expenditure);
        if (validation){
    throw new DAOException("Expenditure is empty!");
    }
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, d MMMM yyyy");
        Calendar calendar = new GregorianCalendar();
        String date = dateFormat.format(calendar.getTime());
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Expenditure.txt", true))) {
            String tempData = expenditure.getId() + " " + expenditure.getLogin() + " " + expenditure.getExpenditureName() + " "
                    + expenditure.getAmount() + " " + expenditure.getDate();
            writer.write(tempData);
            writer.newLine();
        }catch (IOException e){
            throw new DAOException(e);
        }
//        for (Map.Entry<Long, Expenditure> pair: expenditureReadMap.entrySet()) {
//            if (!expenditureReadMap.containsKey(pair.getKey())){
//                expenditureReadMap.put(expenditure.getId(), expenditure);
//            }
//        }
    }

    @Override
    public void exchangeExpenditure(Expenditure expenditure) throws DAOException, IOException {
        boolean validation = DAOValidation.checkExpenditure(expenditure);
        if (validation){
            throw new DAOException("Expenditure is empty!");
        }
        String seachedLine = expenditure.getId() + "";
        String line=null;

        File sourceFile = new File("Expenditure.txt");
        File outputFile = new File("Expenditure2.txt");
        try(BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile)) ) {
            while (reader.ready() == true) {
                line = reader.readLine();
                if (!line.contains(seachedLine)){
                    writer.write(line);
                    writer.newLine();
        }
            }
        }catch (IOException e){
            throw new DAOException(e);
        }
        sourceFile.delete();
        outputFile.renameTo(sourceFile);
        try (BufferedWriter writerAdd = new BufferedWriter(new FileWriter("Expenditure.txt", true))) {
            String tempData = expenditure.getId() + " " + expenditure.getLogin() + " " + expenditure.getExpenditureName() + " "
                    + expenditure.getAmount() + " " + expenditure.getDate();
            writerAdd.write(tempData);
            writerAdd.newLine();
        }catch (IOException e){
            throw  new DAOException(e);
        }
    }


    @Override
    public void deleteExpenditure(long id) throws DAOException, IOException {
        boolean validation = DAOValidation.checkId(id);
        if (validation){
        throw  new DAOException("Wrong id!");
    }
        String seachedLine = id + "";
        String line=null;
        File sourceFile = new File("Expenditure.txt");
        File outputFile = new File("Expenditure2.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))){
            while ((reader.ready()) == true) {
                line = reader.readLine();
                if (!line.contains(seachedLine)){
                    writer.write(line);
                    writer.newLine();
                }
            }
        }
        sourceFile.delete();
        outputFile.renameTo(sourceFile);
    }

    @Override
    public void readExpenditure() throws DAOException {
        try(BufferedReader reader = new BufferedReader(new FileReader("Expenditure.txt"))){
           while (reader.ready() == true){
               String tempData = reader.readLine();
               String[] strings = tempData.split(" ");
               Expenditure expenditure = new Expenditure(strings[1], strings[2], Double.parseDouble(strings[3]), Long.parseLong(strings[0]), strings[4] + strings[5] + strings[6] + strings[7]);
               expenditureReadMap.put(expenditure.getId(), expenditure);
           }
       }catch (IOException e){
           throw  new DAOException(e);
       }
    }
}
