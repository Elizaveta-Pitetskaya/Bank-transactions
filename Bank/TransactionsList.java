import java.util.UUID;
interface TransactionsList{
    public void addTransaction(Transaction transaction);
    public void removeTransactionById(UUID id);
    public Transaction[] transformIntoArray();
}



