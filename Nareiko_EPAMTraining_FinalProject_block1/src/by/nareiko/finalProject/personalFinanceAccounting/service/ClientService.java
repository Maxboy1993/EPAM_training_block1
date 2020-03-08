package by.nareiko.finalProject.personalFinanceAccounting.service;

import by.nareiko.finalProject.personalFinanceAccounting.bean.User;
import by.nareiko.finalProject.personalFinanceAccounting.service.exception.ServiceException;

public interface ClientService {
    void singIn(String login, String password) throws ServiceException;
    void singOut(String login) throws ServiceException;
    void registration(User user) throws ServiceException;
}
