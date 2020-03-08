package by.nareiko.finalProject.personalFinanceAccounting.runner;

import by.nareiko.finalProject.personalFinanceAccounting.bean.Expenditure;
import by.nareiko.finalProject.personalFinanceAccounting.controller.Controller;
import by.nareiko.finalProject.personalFinanceAccounting.controller.command.implementation.AddingEditedExpenditure;
import by.nareiko.finalProject.personalFinanceAccounting.controller.command.implementation.CommandName;
import by.nareiko.scanner.DataScanner;

import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {

        System.out.println("Command names are available in application:");
        System.out.println("REGISTRATION " + "SIGN_IN " + "SING_OUT " + "ADD_EXPENDITURE " + "READ_EXPENDITURE\n"
        + "EDIT_EXPENDITURE - only for admin " + "DELETE_EXPENDITURE - only for admin");
        System.out.println("Every command requests the following parameters:\n" +
                "REGISTRATION - login, password, you are admin (true or false)\n" +
                "SIGN_IN - login, password\n" +
                "SING_OUT - login\n" +
                "ADD_EXPENDITURE - expenditure name, amount (nothing if it is profit, minus(-) if it is consumption)\n"
                + "EDIT_EXPENDITURE - proccessing of reading expenditure list, then enter " +
                "expenditure name, amount, id. Available only for admin\n" +
                "DELETE_EXPENDITURE - id. Available only for admin");

        Controller controller = new Controller();
        System.out.println("Please, enter EXIT to close application");

       String request = "";
// sign_in max 1234
//        EDIT_EXPENDITURE
        while (!request.equalsIgnoreCase("EXIT") ) {
            System.out.println("Enter requested data:");
            request = DataScanner.enterString();
            if (request.equalsIgnoreCase("EDIT_EXPENDITURE") || request.equalsIgnoreCase("DELETE_EXPENDITURE")){
                Map<Long, Expenditure> map = AddingEditedExpenditure.read();
                for (Map.Entry<Long, Expenditure> pair: map.entrySet()
                ) {
                    System.out.println("id = " + pair.getKey() + "/ login = " + pair.getValue().getLogin() + "/ expenditureName = " + pair.getValue().getExpenditureName() +
                            "/ amount = " + pair.getValue().getAmount() + "/ date = " + pair.getValue().getDate()   );
                }
                System.out.println("Enter extra data fo edding expenditure: expenditure name, amount, id, Or Jist enter id to delere expenditure");
                request += " " + DataScanner.enterString();
            }
            String result = controller.executeTask(request);
            System.out.println(result);
        }
    }
}
