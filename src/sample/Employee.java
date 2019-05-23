package sample;

public class Employee {
    private boolean hired;
    private int price, salary;
    private boolean isManager = false;


    public Employee(boolean hired, int number){
        this.hired = hired;
        this.price = number * 200;
        this.salary = number *50;
    }



    public Employee(boolean hired, int number, boolean isManager){
        this.hired = hired;
        this.isManager = true;
        this.price = 500*number;
        this.salary = number*150;
    }

    public int getPrice() {
        return price;
    }


    public boolean isHired() {
        return hired;
    }

public void setHired(boolean isHired){
        this.hired = isHired;
}

    public int getSalary() {
        return salary;
    }
}

