package by.nareiko.finalProject.personalFinanceAccounting.service.factory;

import by.nareiko.finalProject.personalFinanceAccounting.service.ClientService;
import by.nareiko.finalProject.personalFinanceAccounting.service.ExpenditureService;
import by.nareiko.finalProject.personalFinanceAccounting.service.implementation.ClientServiceImpl;
import by.nareiko.finalProject.personalFinanceAccounting.service.implementation.ExpenditureServiceImpl;

public final  class ServiceFactory{
    private static ServiceFactory serviceFactory;
    private ClientService clientService;
    private ExpenditureService expenditureService;

    private ServiceFactory(){}

    public static ServiceFactory getServiceFactory(){
        if (serviceFactory == null){
            serviceFactory = new ServiceFactory();
        }
        return serviceFactory;
    }

    public ClientService getClientService(){
        if (clientService == null){
            clientService = new ClientServiceImpl();
            }
        return clientService;
    }

    public ExpenditureService getExpenditureService(){
        if (expenditureService == null){
            expenditureService = new ExpenditureServiceImpl();
        }
        return expenditureService;
    }
}
