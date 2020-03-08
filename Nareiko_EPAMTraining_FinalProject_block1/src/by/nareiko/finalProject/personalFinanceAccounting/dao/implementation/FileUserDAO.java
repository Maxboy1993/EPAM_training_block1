package by.nareiko.finalProject.personalFinanceAccounting.dao.implementation;

import by.nareiko.finalProject.personalFinanceAccounting.bean.User;
import by.nareiko.finalProject.personalFinanceAccounting.dao.UserDAO;
import by.nareiko.finalProject.personalFinanceAccounting.dao.exception.DAOException;
import by.nareiko.finalProject.personalFinanceAccounting.service.CurrentUserHolder;
import by.nareiko.finalProject.personalFinanceAccounting.validation.DAOValidation;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileUserDAO implements UserDAO {
 private static   Map<String, User> userReadMap = new HashMap<>();

    public static Map<String, User> getUserReadMap() {
        return userReadMap;
    }

    @Override
    public void signIn(String login, String password) throws DAOException, IOException {
        boolean validation = DAOValidation.checkLogin(login);
        if (validation){
            throw new DAOException("Wrong login!");
        }
        validation = DAOValidation.checkPassword(password);
        if (validation){
            throw new DAOException("Wrong password!");
        }
        BufferedReader reader = new BufferedReader(new FileReader("User.txt"));
        while (reader.ready() == true){
            String tempData = reader.readLine();
            String[] strings = tempData.split(" ");
            User user = new User(strings[0], strings[1], Boolean.parseBoolean(strings[2]));
            userReadMap.put(user.getLogin(), user);
        }
        if (!userReadMap.containsKey(login)){
                throw new DAOException("User not found!");
            }
        CurrentUserHolder currentUserHolder = CurrentUserHolder.getInstance();
            currentUserHolder.setActiveUser(userReadMap.get(login));
        }

    @Override
    public void registration(User user) throws DAOException, IOException {
        boolean validation = DAOValidation.checkUser(user);
        if (validation){
            throw new DAOException("Object user is empty!");
        }
        BufferedReader reader = new BufferedReader(new FileReader("User.txt"));
        while (reader.ready() == true){
            String tempData = reader.readLine();
            String[] strings = tempData.split(" ");
            User checkUser = new User(strings[0], strings[1], Boolean.parseBoolean(strings[2]));
            userReadMap.put(user.getLogin(), user);
        }
        reader.close();
        for (Map.Entry<String, User> pair:userReadMap.entrySet()) {
            boolean marker = user.isAdmin() == pair.getValue().isAdmin();
            if (marker){
                throw new  DAOException("Admin exists already! You cannot be admin!");
            }
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter("User.txt", true));
        String tempData = user.getLogin() + " " + user.getPassword() + " " + user.isAdmin();
        writer.write(tempData);
        writer.newLine();
        writer.close();
        CurrentUserHolder currentUserHolder = CurrentUserHolder.getInstance();
        currentUserHolder.setActiveUser(user);

    }
}
