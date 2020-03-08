package by.nareiko.finalProject.personalFinanceAccounting.controller;

import by.nareiko.finalProject.personalFinanceAccounting.controller.command.Command;
import by.nareiko.finalProject.personalFinanceAccounting.controller.exception.ControllerException;
import by.nareiko.finalProject.personalFinanceAccounting.validation.ControllerValidation;

public class Controller {
    private final CommandProvider provider = new CommandProvider();
    private final char paramDelimeter = ' ';
    public String executeTask(String request) throws Exception {
        boolean validation = ControllerValidation.checkRequest(request);
        if (validation){
            throw new ControllerException("Wrong request!");
        }
        String commandName;
        Command executionCommand;
        commandName = request.substring(0, request.indexOf(paramDelimeter));
        int length = commandName.length();
        String requestToService = request.substring(length+1);
        executionCommand = provider.getCommand(commandName);
        String response;
        response = executionCommand.execute(requestToService);
        return response;
    }
    }

