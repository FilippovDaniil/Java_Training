package m11_objects_constructors.practice.task07;

class BankAccount {
    // TODO: private поля, конструктор, геттеры, deposit, withdraw, toString
    private String owner;
    private double balance;

    public BankAccount(String owner, double balance) {
        this.owner = owner;
        if (balance < 0.00){
            System.out.println("Balance has to be more than 0");
            return;
        }else{
            this.balance = balance;
        }
    }

    public BankAccount() {
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        if (balance < 0.00){
            System.out.println("Balance has to be more than 0");
            return;
        } else {
            this.balance = balance;
        }
    }

    public void deposit(double amount){
        if (amount < 0.00){
            System.out.println("Summa has to be more than 0");
            return;
        } else {
            this.balance += amount;
            System.out.println("Deposit bil popolnen na: " + amount);
            System.out.println("Seichas balance: " + this.balance);
        }
    }

    public void withdraw(double amount){
        if (amount < 0.00){
            System.out.println("Summa has to be more than 0");
            return;
        } else {
            if (this.balance - amount < 0){
                System.out.println("Balance ne doljen uiti v nol");
                return;
            }else{
                this.balance -= amount;
                System.out.println("Deposit bil umenshen na: " + amount);
                System.out.println("Seichas balance: " + this.balance);
            }
        }
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "owner='" + owner + '\'' +
                ", balance=" + balance +
                '}';
    }
}
