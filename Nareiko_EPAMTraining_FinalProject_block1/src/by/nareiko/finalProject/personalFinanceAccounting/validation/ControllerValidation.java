package by.nareiko.finalProject.personalFinanceAccounting.validation;

import by.nareiko.finalProject.personalFinanceAccounting.bean.Expenditure;
import by.nareiko.finalProject.personalFinanceAccounting.bean.User;

import java.util.Map;

public class ControllerValidation {

    public static boolean checkRequest(String request) {
        if (request == null || request.isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean checkCommandName(String name) {
        if (name == null || name.isEmpty()) {
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

    public static boolean checkMap(Map<Long, Expenditure> map) {
        if (map ==  null) {
            return true;
        }
        return false;
    }
}
