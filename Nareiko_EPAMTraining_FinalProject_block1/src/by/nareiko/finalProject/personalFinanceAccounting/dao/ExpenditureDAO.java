package by.nareiko.finalProject.personalFinanceAccounting.dao;

import by.nareiko.finalProject.personalFinanceAccounting.bean.Expenditure;
import by.nareiko.finalProject.personalFinanceAccounting.bean.User;
import by.nareiko.finalProject.personalFinanceAccounting.dao.exception.DAOException;

import java.io.IOException;
import java.util.Map;

public interface ExpenditureDAO {
    void addExpenditure(Expenditure expenditure) throws DAOException, IOException;
    void deleteExpenditure(long id) throws DAOException, IOException;
    void exchangeExpenditure(Expenditure expenditure) throws DAOException, IOException;
    void readExpenditure() throws DAOException, IOException;
}
