package by.nareiko.finalProject.personalFinanceAccounting.service.implementation;

import by.nareiko.finalProject.personalFinanceAccounting.bean.User;
import by.nareiko.finalProject.personalFinanceAccounting.dao.UserDAO;
import by.nareiko.finalProject.personalFinanceAccounting.dao.exception.DAOException;
import by.nareiko.finalProject.personalFinanceAccounting.dao.factory.DAOFactory;
import by.nareiko.finalProject.personalFinanceAccounting.service.ClientService;
import by.nareiko.finalProject.personalFinanceAccounting.service.CurrentUserHolder;
import by.nareiko.finalProject.personalFinanceAccounting.service.exception.ServiceException;
import by.nareiko.finalProject.personalFinanceAccounting.validation.ServiceValidation;

import java.io.IOException;

public class ClientServiceImpl implements ClientService {
    @Override
    public void singIn(String login, String password) throws ServiceException {
        boolean validation = ServiceValidation.checkLogin(login);
        if(validation){
            throw new ServiceException("Incorrect login");
        }
        validation = ServiceValidation.checkPassword(password);
        if(validation){
            throw new ServiceException("Incorrect password");
        }
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoFactory.getUserDAO();
            userDAO.signIn(login, password);
        }catch (DAOException | IOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public void singOut(String login) throws ServiceException {
        boolean validation = ServiceValidation.checkLogin(login);
        if(validation){
            throw new ServiceException("Incorrect login");
        }
        CurrentUserHolder currentUserHolder = CurrentUserHolder.getInstance();
        currentUserHolder.clearActiveUser();
    }

    @Override
    public void registration(User user)  throws ServiceException{
        boolean validation = ServiceValidation.checkUser(user);
        if(validation){
            throw new ServiceException("User is empty!");
        }
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoFactory.getUserDAO();
            userDAO.registration(user);
        }catch (DAOException | IOException e){
            throw new ServiceException(e);
        }
    }
}
