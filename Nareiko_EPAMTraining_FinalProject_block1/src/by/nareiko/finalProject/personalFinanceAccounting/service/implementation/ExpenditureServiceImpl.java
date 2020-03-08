package by.nareiko.finalProject.personalFinanceAccounting.service.implementation;

import by.nareiko.finalProject.personalFinanceAccounting.bean.Expenditure;
import by.nareiko.finalProject.personalFinanceAccounting.bean.User;
import by.nareiko.finalProject.personalFinanceAccounting.dao.ExpenditureDAO;
import by.nareiko.finalProject.personalFinanceAccounting.dao.exception.DAOException;
import by.nareiko.finalProject.personalFinanceAccounting.dao.factory.DAOFactory;
import by.nareiko.finalProject.personalFinanceAccounting.dao.implementation.FileExpenditureDAO;
import by.nareiko.finalProject.personalFinanceAccounting.service.ExpenditureService;
import by.nareiko.finalProject.personalFinanceAccounting.service.exception.ServiceException;
import by.nareiko.finalProject.personalFinanceAccounting.validation.ServiceValidation;

import java.io.IOException;
import java.util.Map;

public class ExpenditureServiceImpl implements ExpenditureService {
       @Override
    public void addNewExpenditure(Expenditure expenditure) throws ServiceException {
           boolean validatin = ServiceValidation.checkExpenditure(expenditure);
           if(validatin){
               throw new ServiceException("Incorrect Expenditure");
           }
           try {
               DAOFactory daoFactory = DAOFactory.getInstance();
               ExpenditureDAO expenditureDAO = daoFactory.getExpenditureDAO();
               expenditureDAO.addExpenditure(expenditure);
           }catch (DAOException | IOException e){
               throw new ServiceException(e);
           }
    }

    @Override
    public void addEditedExpenditure(Expenditure expenditure) throws ServiceException {
        boolean validatin = ServiceValidation.checkExpenditure(expenditure);
        if(validatin){
            throw new ServiceException("Incorrect Expenditure");
        }
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            ExpenditureDAO expenditureDAO = daoFactory.getExpenditureDAO();
            expenditureDAO.exchangeExpenditure(expenditure);
        }catch (DAOException | IOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteExpenditure( long id) throws ServiceException {
        boolean validatin = ServiceValidation.checkId(id);
        if(validatin){
            throw new ServiceException("Incorrect id");
        }
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            ExpenditureDAO expenditureDAO = daoFactory.getExpenditureDAO();
            expenditureDAO.deleteExpenditure(id);
        }catch (DAOException | IOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public Map<Long, Expenditure> readExpenditure() throws ServiceException, IOException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            ExpenditureDAO expenditureDAO = daoFactory.getExpenditureDAO();
            expenditureDAO.readExpenditure();
        }catch (DAOException | IOException e){
            throw new ServiceException(e);
        }
        Map<Long, Expenditure> map = FileExpenditureDAO.getExpenditureReadMap();
        return map;
    }
}
