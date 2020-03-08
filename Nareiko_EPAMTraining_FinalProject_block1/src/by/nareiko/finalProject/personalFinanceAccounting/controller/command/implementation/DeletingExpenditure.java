package by.nareiko.finalProject.personalFinanceAccounting.controller.command.implementation;

import by.nareiko.finalProject.personalFinanceAccounting.bean.Expenditure;
import by.nareiko.finalProject.personalFinanceAccounting.bean.User;
import by.nareiko.finalProject.personalFinanceAccounting.controller.command.Command;
import by.nareiko.finalProject.personalFinanceAccounting.controller.exception.ControllerException;
import by.nareiko.finalProject.personalFinanceAccounting.dao.exception.DAOException;
import by.nareiko.finalProject.personalFinanceAccounting.service.CurrentUserHolder;
import by.nareiko.finalProject.personalFinanceAccounting.service.ExpenditureService;
import by.nareiko.finalProject.personalFinanceAccounting.service.exception.ServiceException;
import by.nareiko.finalProject.personalFinanceAccounting.service.factory.ServiceFactory;
import by.nareiko.finalProject.personalFinanceAccounting.validation.ControllerValidation;

import java.io.IOException;
import java.util.IllegalFormatException;

public class DeletingExpenditure implements Command {
    @Override
    public String execute(String request) throws ControllerException {
        boolean validation = ControllerValidation.checkRequest(request);
        if (validation){
            throw new ControllerException("Wrong request!");
        }
        CurrentUserHolder currentUserHolder = CurrentUserHolder.getInstance();
        User user = currentUserHolder.getActiveUser();
        if (user == null) {
            throw new ControllerException("User is empty!");
        }
        if (user.isAdmin() == false) {
            throw new ControllerException("Simple user have not access to this operation! Only admin can do it.");
        }
        String response = null;
        String[] stringArray = request.split(" ");
        String login = CurrentUserHolder.getInstance().getActiveUser().getLogin();
        try {
            ServiceFactory serviceFactory = ServiceFactory.getServiceFactory();
            ExpenditureService expenditureService = serviceFactory.getExpenditureService();
            long id = Long.parseLong(stringArray[0]);
            expenditureService.deleteExpenditure(id);
            response = "Expenditure deleted.";
        } catch (ServiceException | IOException | IllegalFormatException e) {
            response = "Error duiring operation. Expenditure is not deleted!";
        }
        return  response;
    }
}
