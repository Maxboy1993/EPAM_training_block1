package by.nareiko.finalProject.personalFinanceAccounting.controller.command.implementation;

import by.nareiko.finalProject.personalFinanceAccounting.bean.Expenditure;
import by.nareiko.finalProject.personalFinanceAccounting.bean.User;
import by.nareiko.finalProject.personalFinanceAccounting.controller.command.Command;
import by.nareiko.finalProject.personalFinanceAccounting.controller.exception.ControllerException;
import by.nareiko.finalProject.personalFinanceAccounting.service.CurrentUserHolder;
import by.nareiko.finalProject.personalFinanceAccounting.service.ExpenditureService;
import by.nareiko.finalProject.personalFinanceAccounting.service.exception.ServiceException;
import by.nareiko.finalProject.personalFinanceAccounting.service.factory.ServiceFactory;
import by.nareiko.finalProject.personalFinanceAccounting.validation.ControllerValidation;

import java.io.IOException;
import java.util.HashMap;
import java.util.IllegalFormatException;
import java.util.Map;

public class AddingEditedExpenditure implements Command {
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
        if (user.isAdmin() == false){
            throw new ControllerException("Simple user have not access to this operation! Only admin can do it.");
        }
        String response = null;

        String[] stringArray = request.split(" ");
        String login = CurrentUserHolder.getInstance().getActiveUser().getLogin();
        String expenditureName = stringArray[0];
        try {
            ServiceFactory serviceFactory = ServiceFactory.getServiceFactory();
            ExpenditureService expenditureService = serviceFactory.getExpenditureService();
            double amount = Double.parseDouble(stringArray[1]);
            long id = Long.parseLong(stringArray[2]);
            Expenditure expenditure = new Expenditure(login, expenditureName, amount, id);
            expenditureService.addEditedExpenditure(expenditure);
            response = "Expenditure edited.";
        }catch (ServiceException | IllegalFormatException | IOException e) {
            response = "Error duiring operation. Exprnditure is not edited!";
        }
        return response;
    }

    public static Map<Long, Expenditure> read() throws ControllerException {
        CurrentUserHolder currentUserHolder = CurrentUserHolder.getInstance();
        User user = currentUserHolder.getActiveUser();
        if (user == null){
            throw new ControllerException("User is empty!");
        }
        String login = CurrentUserHolder.getInstance().getActiveUser().getLogin();
        Map<Long, Expenditure> map;
        Map<Long, Expenditure> mapForParticularUser = new HashMap<>();
        try {
            ServiceFactory serviceFactory = ServiceFactory.getServiceFactory();
            ExpenditureService expenditureService = serviceFactory.getExpenditureService();
            map = expenditureService.readExpenditure();
        }catch (ServiceException | IllegalFormatException | IOException e) {
            throw new ControllerException("Error duiring operation. Exprnditure has not read!");
        }
        for (Map.Entry<Long, Expenditure> pair:map.entrySet()
             ) {
            if (pair.getValue().getLogin().equals(login)){
                mapForParticularUser.put(pair.getKey(), pair.getValue());
            }
        }
        return mapForParticularUser;
    }
}
