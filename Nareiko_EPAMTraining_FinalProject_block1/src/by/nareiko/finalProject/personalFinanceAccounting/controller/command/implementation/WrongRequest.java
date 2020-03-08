package by.nareiko.finalProject.personalFinanceAccounting.controller.command.implementation;

import by.nareiko.finalProject.personalFinanceAccounting.controller.command.Command;
import by.nareiko.finalProject.personalFinanceAccounting.controller.exception.ControllerException;
import by.nareiko.finalProject.personalFinanceAccounting.validation.ControllerValidation;

public class WrongRequest implements Command {
    @Override
    public String execute(String request) throws ControllerException {
        boolean validation = ControllerValidation.checkRequest(request);
        if (validation){
            throw new ControllerException("Wrong request!");
        }
        return "Wrong login or password! Try again";
    }
}
