package by.nareiko.finalProject.personalFinanceAccounting.bean;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;

public class Expenditure {
   private String login;
   private String expenditureName;
   private  double amount;
   private  long id;
   private String date;

    public Expenditure(String login, String expenditureName, double amount, long id, String date) {
        this.login = login;
        this.expenditureName = expenditureName;
        this.amount = amount;
        this.id = id;
        this.date = date;
    }
    public Expenditure(String login, String expenditureName, double amount, long id) {
        this.login = login;
        this.expenditureName = expenditureName;
        this.amount = amount;
        this.id = id;
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, d MMMM yyyy");
        Calendar calendar = new GregorianCalendar();
        this.date = dateFormat.format(calendar.getTime());
    }

    public Expenditure(){
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getExpenditureName() {
        return expenditureName;
    }

    public void setExpenditureName(String expenditureName) {
        this.expenditureName = expenditureName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expenditure that = (Expenditure) o;
        return amount == that.amount && id == that.id &&
                Objects.equals(login, that.login) &&
                Objects.equals(expenditureName, that.expenditureName) &&  Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime*result + ((login == null) ? 0 : 1);
        result = prime*result + ((expenditureName == null) ? 0 :  1);
        result = prime*result + (int)amount;
        result = prime*result + (int)id;
        result = prime*result + ((date == null) ? 0 :  1);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getName() +
                "login='" + login + '\'' +
                ", expenditureName='" + expenditureName + '\'' +
                ", amount=" + amount + '\'' +
                ", id=" + id + '\'' +
                ", date=" + date +
                '}';
    }
}
