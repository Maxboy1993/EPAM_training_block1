package by.nareiko.finalProject.personalFinanceAccounting.controller.command;

import by.nareiko.finalProject.personalFinanceAccounting.controller.exception.ControllerException;

public interface Command {
    public String execute(String request) throws ControllerException;
}

