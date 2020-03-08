package by.nareiko.finalProject.personalFinanceAccounting.controller;

import by.nareiko.finalProject.personalFinanceAccounting.controller.command.Command;
import by.nareiko.finalProject.personalFinanceAccounting.controller.command.implementation.*;
import by.nareiko.finalProject.personalFinanceAccounting.controller.exception.ControllerException;
import by.nareiko.finalProject.personalFinanceAccounting.validation.ControllerValidation;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private final Map<CommandName, Command> repository = new HashMap<>();
    CommandProvider(){
        repository.put(CommandName.SIGN_IN, new SignIn());
        repository.put(CommandName.SING_OUT, new SingOut());
        repository.put(CommandName.REGISTRATION, new Registration());
        repository.put(CommandName.EDIT_EXPENDITURE, new AddingEditedExpenditure());
        repository.put(CommandName.ADD_EXPENDITURE, new AddingNewExpenditure());
        repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
        repository.put(CommandName.DELETE_EXPENDITURE, new DeletingExpenditure());
    }
    Command getCommand(String name) throws ControllerException {
        boolean validation = ControllerValidation.checkCommandName(name);
        if (validation){
            throw new ControllerException("Wrong CommandName!");
        }
        CommandName commandName =null;
        Command command = null;
        try{
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        }catch(IllegalArgumentException | NullPointerException e){
            command = repository.get(CommandName.WRONG_REQUEST);
        }
        return command;
    }
}


