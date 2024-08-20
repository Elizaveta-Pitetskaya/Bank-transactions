import java.util.UUID;
class Transaction{
    public enum category{
        debits, credits
    }
    private UUID Identifier;
    private User Recipient;
    private User Sender;
    private category TransferCategory;
    private Integer TransferAmount;

    public Transaction(User recipient, User sender, category transferCategory, Integer transferAmount){
        if(transferCategory == category.credits && transferAmount >= 0){
            System.err.println("Error of credit transfer! The amount must be negative.");
            System.exit(-1);
        } else if(transferCategory == category.debits && transferAmount < 0) {
            System.err.println("Error of credit transfer! The amount must be positive.");
            System.exit(-1);
        } else if((transferCategory == category.credits && recipient.getBalance() < transferAmount * (-1)) || (transferCategory == category.debits && sender.getBalance() < transferAmount)){
            System.err.println("Insufficaient funds for transaction.");
            System.exit(-1);
        } else {
            Identifier = UUID.randomUUID();
            Recipient = recipient;
            Sender = sender;
            TransferCategory = transferCategory;
            TransferAmount = transferAmount;
//            if(transferAmount < 0){
//                transferAmount = transferAmount * (-1);
//            }
            Recipient.setBalance(Recipient.getBalance() + TransferAmount);
            Sender.setBalance(Sender.getBalance() - TransferAmount);
        }
    }

    public UUID getIdentifier(){
        return Identifier;
    }

    public User getRecipient(){
        return Recipient;
    }

    public User getSender(){
        return Sender;
    }

    public category getTransferCategory(){
        return TransferCategory;
    }

    public Integer getTransferAmount(){
        return TransferAmount;
    }

    public void setIdentifier(UUID id){
        Identifier = id;
    }

    public void setRecipient(User person){
        Recipient = person;
    }

    public void setSender(User person){
        Sender = person;
    }

    public void setTransferCategory(category transferCategory){
        TransferCategory = transferCategory;
    }

    public void setTransferAmount(Integer amount){
        TransferAmount = amount;
    }

}




