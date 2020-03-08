package by.nareiko.finalProject.personalFinanceAccounting.controller.command.implementation;

import by.nareiko.finalProject.personalFinanceAccounting.bean.User;
import by.nareiko.finalProject.personalFinanceAccounting.controller.command.Command;
import by.nareiko.finalProject.personalFinanceAccounting.controller.exception.ControllerException;
import by.nareiko.finalProject.personalFinanceAccounting.service.ClientService;
import by.nareiko.finalProject.personalFinanceAccounting.service.exception.ServiceException;
import by.nareiko.finalProject.personalFinanceAccounting.service.factory.ServiceFactory;
import by.nareiko.finalProject.personalFinanceAccounting.validation.ControllerValidation;

public class Registration implements Command {
    private final char paramDelimeter = ' ';
    @Override
    public String execute(String request) throws ControllerException {
        boolean validation = ControllerValidation.checkRequest(request);
        if (validation){
            throw new ControllerException("Wrong request!");
        }
            String response = null;
            boolean isAdmin;
            String[] stringArray = request.split(" ");
            String login = stringArray[0];
            String password = stringArray[1];
            String admin = stringArray[2];
            if (admin.equals("true")){
                isAdmin =true;
            }else if (admin.equals("false")){
                isAdmin = false;
            }else {
                throw new  ControllerException("Wrong admin determination! Should be true or false");
            }
            User user = new User(login, password, isAdmin);
            try {
                ServiceFactory serviceFactory = ServiceFactory.getServiceFactory();
                ClientService clientService = serviceFactory.getClientService();
                clientService.registration(user);

                response = "Welcome, registration finished!";
            } catch (ServiceException e) {
                response = "Error duiring registration";
            }
            return response;
        }
    }

