package by.nareiko.finalProject.personalFinanceAccounting.service;

import by.nareiko.finalProject.personalFinanceAccounting.bean.Expenditure;
import by.nareiko.finalProject.personalFinanceAccounting.bean.User;
import by.nareiko.finalProject.personalFinanceAccounting.dao.exception.DAOException;
import by.nareiko.finalProject.personalFinanceAccounting.service.exception.ServiceException;

import java.io.IOException;
import java.util.Map;

public interface ExpenditureService {
    void addNewExpenditure(Expenditure expenditure) throws ServiceException, IOException;
    void addEditedExpenditure(Expenditure expenditure) throws ServiceException, IOException;
    void deleteExpenditure(long id) throws ServiceException, IOException;
    Map<Long, Expenditure> readExpenditure() throws ServiceException, IOException;
}
