package by.nareiko.finalProject.personalFinanceAccounting.controller.command.implementation;

import by.nareiko.finalProject.personalFinanceAccounting.bean.Expenditure;
import by.nareiko.finalProject.personalFinanceAccounting.bean.User;
import by.nareiko.finalProject.personalFinanceAccounting.controller.command.Command;
import by.nareiko.finalProject.personalFinanceAccounting.controller.exception.ControllerException;
import by.nareiko.finalProject.personalFinanceAccounting.dao.exception.DAOException;
import by.nareiko.finalProject.personalFinanceAccounting.dao.implementation.FileExpenditureDAO;
import by.nareiko.finalProject.personalFinanceAccounting.service.ClientService;
import by.nareiko.finalProject.personalFinanceAccounting.service.CurrentUserHolder;
import by.nareiko.finalProject.personalFinanceAccounting.service.ExpenditureService;
import by.nareiko.finalProject.personalFinanceAccounting.service.exception.ServiceException;
import by.nareiko.finalProject.personalFinanceAccounting.service.factory.ServiceFactory;
import by.nareiko.finalProject.personalFinanceAccounting.validation.ControllerValidation;

import java.io.IOException;
import java.util.IllegalFormatException;
import java.util.Map;

public class AddingNewExpenditure implements Command {
    @Override
    public String execute(String request) throws ControllerException {
        boolean validation = ControllerValidation.checkRequest(request);
        if (validation){
            throw new ControllerException("Wrong request!");
        }
        CurrentUserHolder currentUserHolder = CurrentUserHolder.getInstance();
        User user = currentUserHolder.getActiveUser();
        if (user == null){
            throw new ControllerException("User is empty!");
        }
        String response = null;
        String[] stringArray = request.split(" ");
        String login = user.getLogin();
        String expenditureName = stringArray[0];
        try {
            ServiceFactory serviceFactory = ServiceFactory.getServiceFactory();
            ExpenditureService expenditureService = serviceFactory.getExpenditureService();
            double amount = Double.parseDouble(stringArray[1]);
            long id = (long) (Math.random()*100000 + 1);

            for (Map.Entry<Long, Expenditure> pair: FileExpenditureDAO.getExpenditureReadMap().entrySet()) {
                while (pair.getKey() == id){
                    id = (long) (Math.random()*100000 + 1);
                }
            }
            Expenditure expenditure = new Expenditure(login, expenditureName, amount, id);
            expenditureService.addNewExpenditure(expenditure);
            response = "New expenditure added.";
        }catch (IllegalFormatException | ServiceException | IOException e) {
            response = "Error duiring login procedure";
        }
        return response;
    }
}
