package by.nareiko.finalProject.personalFinanceAccounting.bean;

import java.util.Objects;

public class User {
    private String login;
    private String password;
    private boolean admin;

    public User(){
    }
    public User(String login, String password, boolean admin){
        this.login = login;
        this.password = password;
        this.admin = admin;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return admin == user.admin &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime*result + ((login == null) ? 0 : login.hashCode());
        result = prime*result + ((password == null) ? 0 :  password.hashCode());
        result = prime*result + ((admin == true) ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getName() +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", admin=" + admin +
                '}';
    }
}
