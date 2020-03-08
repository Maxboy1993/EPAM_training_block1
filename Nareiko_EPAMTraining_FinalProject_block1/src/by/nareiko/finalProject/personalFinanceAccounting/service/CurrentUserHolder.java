package by.nareiko.finalProject.personalFinanceAccounting.service;

import by.nareiko.finalProject.personalFinanceAccounting.bean.User;

public class CurrentUserHolder {
    private static CurrentUserHolder instance;

    private User activeUser;

    private CurrentUserHolder(){}

    public static CurrentUserHolder getInstance(){
        if (instance == null){
            instance = new CurrentUserHolder();
        }
        return instance;
    }

    public void setActiveUser(User user){
        activeUser = user;
    }

    public User getActiveUser(){
        return activeUser;
    }

    public void clearActiveUser(){
        activeUser = null;
    }

}
