package by.nareiko.finalProject.personalFinanceAccounting.controller.command.implementation;

import by.nareiko.finalProject.personalFinanceAccounting.controller.command.Command;
import by.nareiko.finalProject.personalFinanceAccounting.controller.exception.ControllerException;
import by.nareiko.finalProject.personalFinanceAccounting.service.ClientService;
import by.nareiko.finalProject.personalFinanceAccounting.service.exception.ServiceException;
import by.nareiko.finalProject.personalFinanceAccounting.service.factory.ServiceFactory;
import by.nareiko.finalProject.personalFinanceAccounting.validation.ControllerValidation;

public class SignIn implements Command {
    @Override
    public String execute(String request) throws ControllerException {
        boolean validation = ControllerValidation.checkRequest(request);
        if (validation){
            throw new ControllerException("Wrong request!");
        }
        String response = null;
        String[] stringArray = request.split(" ");
        String login = stringArray[0];
        String password = stringArray[1];
        try {
            ServiceFactory serviceFactory = ServiceFactory.getServiceFactory();
            ClientService clientService = serviceFactory.getClientService();
            clientService.singIn(login, password);
            response = "Welcome";
        } catch (ServiceException e) {
            response = "Error duiring login procedure";
        }
        return response;
    }
}

