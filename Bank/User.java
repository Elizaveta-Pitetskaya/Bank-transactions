public class User{
    private Integer Identifier;
    private String Name;
    private Integer Balance;
    private TransactionsLinkedList tList;

    public User(String name, Integer balance){
        if(balance < 0){
            balance = 0;
        }
        this.Identifier = UserIdsGenerator.getInstance().generateId();
        Name = name;
        Balance = balance;
        tList = new TransactionsLinkedList();
    }

    public Integer getIdentifier(){
        return Identifier;
    }

    public String getName(){
        return Name;
    }

    public Integer getBalance(){
        return Balance;
    }

    public TransactionsLinkedList gettList() {
        return tList;
    }

    public void setIdentifier(Integer id){
        Identifier = id;
    }

    public void setName(String name){
        Name = name;
    }

    public void setBalance(Integer balance){

        if(balance < 0)
            Balance = -1 * balance;
        else
            Balance = balance;
    }
}

