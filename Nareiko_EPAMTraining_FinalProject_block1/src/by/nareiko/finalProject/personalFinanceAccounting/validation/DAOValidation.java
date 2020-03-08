package by.nareiko.finalProject.personalFinanceAccounting.validation;

import by.nareiko.finalProject.personalFinanceAccounting.bean.Expenditure;
import by.nareiko.finalProject.personalFinanceAccounting.bean.User;
import by.nareiko.finalProject.personalFinanceAccounting.dao.exception.DAOException;

public class DAOValidation {
    public static boolean checkUser(User user) {
        if (user == null) {
            return true;
        }
        return false;
    }

    public static boolean checkLogin(String login) {
        if (login == null || login.isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean checkPassword(String password) {
        if (password == null || password.isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean checkExpenditure(Expenditure expenditure) {
        if (expenditure == null) {
            return true;
        }
        return false;
    }

    public static boolean checkId(long id) {
        if (id <= 0) {
            return true;
        }
        return false;
    }
}
