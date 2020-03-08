package by.nareiko.finalProject.personalFinanceAccounting.dao.factory;

import by.nareiko.finalProject.personalFinanceAccounting.dao.ExpenditureDAO;
import by.nareiko.finalProject.personalFinanceAccounting.dao.UserDAO;
import by.nareiko.finalProject.personalFinanceAccounting.dao.implementation.FileExpenditureDAO;
import by.nareiko.finalProject.personalFinanceAccounting.dao.implementation.FileUserDAO;

import java.io.File;

public final class DAOFactory {

    private static DAOFactory daoFactory;
    private  UserDAO userDAO;
    private  ExpenditureDAO expenditureDAO;

    private DAOFactory(){}

    public static DAOFactory getInstance(){
        if (daoFactory == null){
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public ExpenditureDAO getExpenditureDAO(){
        if (expenditureDAO == null){
            expenditureDAO = new FileExpenditureDAO();
        }
        return expenditureDAO;
    }

    public UserDAO getUserDAO(){
        if (userDAO == null){
            userDAO = new FileUserDAO();
        }
        return userDAO;
    }
}
