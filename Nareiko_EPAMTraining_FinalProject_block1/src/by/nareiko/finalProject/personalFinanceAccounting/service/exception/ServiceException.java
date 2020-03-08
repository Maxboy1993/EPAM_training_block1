package by.nareiko.finalProject.personalFinanceAccounting.service.exception;

public class ServiceException extends Exception {
    //    private static final long serialVersionUID = 1L; What is it?
    public ServiceException(){
        super();
    }
    public ServiceException(String message){
        super(message);
    }
    public ServiceException(Exception e){
        super(e);
    }
    public ServiceException(String message, Exception e){
        super(message, e);
    }
}
