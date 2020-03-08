package by.nareiko.finalProject.personalFinanceAccounting.dao;

import by.nareiko.finalProject.personalFinanceAccounting.bean.User;
import by.nareiko.finalProject.personalFinanceAccounting.dao.exception.DAOException;

import java.io.IOException;


public interface UserDAO {
    void signIn(String login, String password) throws DAOException, IOException;
    void registration(User user) throws DAOException, IOException;
}
