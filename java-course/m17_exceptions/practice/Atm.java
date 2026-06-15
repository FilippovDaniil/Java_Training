package m17_exceptions.practice;

public class Atm {

    private int balance;

    public Atm(int balance) {
        this.balance = balance;
    }

    public void withdraw(int amount){
        try{
            if ((balance - amount) < 0 ){
                throw new IllegalArgumentException("No money. Balance is: " + balance);
            }else if (amount == 0){
                System.out.println("Finish");

            }else if(amount < 0){
                throw new InsufficientFundsException("No possible operation");
            }
            else{
                balance = balance - amount;
                System.out.println("You get: " + amount + " Balance is: " + balance);
            }
        }catch(InsufficientFundsException e){
            System.out.println(e.getMessage());
        }
    }
}
