import java.util.UUID;
class TransactionsService{
    UsersList usersList = new UsersArrayList();
//    public static TransactionsList tranList = new TransactionsLinkedList();
    void addUser(User user){
        usersList.addUser(user);
    }

    Integer retrieveUsersBalance(User user){
        return user.getBalance();
    }

    void performingTransaction(User sender, User recipient, Integer amount){
        if(sender.getBalance() < amount || amount < 0 || sender.getIdentifier().equals(recipient.getIdentifier()))
            throw new IllegalTransactionException();
        Transaction transaction1 = new Transaction(sender, recipient, Transaction.category.credits, -amount);
        Transaction transaction2 = new Transaction(sender, recipient, Transaction.category.debits, amount);
        transaction2.setIdentifier(transaction1.getIdentifier());
        sender.gettList().addTransaction(transaction1);
        recipient.gettList().addTransaction(transaction2);
        sender.setBalance(sender.getBalance() - amount);
        recipient.setBalance(recipient.getBalance() + amount);
    }

    Transaction [] retrievingTransfers(User user){
        return user.gettList().transformIntoArray();
    }

    Transaction [] checkValidityTransactions(){
        TransactionsLinkedList allTransactions = new TransactionsLinkedList();
        TransactionsLinkedList invalid = new TransactionsLinkedList();
        for(int i = 0; i != usersList.retrieveNumberOfUsers(); i++){
            User iter = usersList.retrieveUserIndex(i);
            for(int j = 0; j != iter.gettList().getSize(); j++){
                allTransactions.addTransaction(iter.gettList().transformIntoArray()[j]);
            }
        } // записали все транзакции всех пользователей
        if(allTransactions.transformIntoArray() != null){
            for(int i = 0; i != allTransactions.getSize(); i ++){
                int count = 0;
                Transaction current = allTransactions.transformIntoArray()[i];
                for(int j = 0; j != allTransactions.getSize(); j ++){
                    if(current.getIdentifier().equals(allTransactions.transformIntoArray()[j].getIdentifier()))
                        count ++;
                }
                if(count != 2){
                    invalid.addTransaction(current);
                }
            }
        }
        return invalid.transformIntoArray();
    }

    void removeTransactionById(UUID id, Integer userID){
        usersList.retrieveUserID(userID).gettList().removeTransactionById(id);
    }

    public static void main(String args[]){
//        User u1 = new User("Mike", 500);
//        User u2 = new User("John", 589);
//        TransactionsService t = new TransactionsService();
//        //-----------------------------------------------Проверка добавления пользователя-----------------------------
//        t.addUser(u1);
//        t.addUser(u2);
//        System.out.println(t.usersList.retrieveUserIndex(0).getName());
//        System.out.println(t.retrieveUsersBalance(u1));
//        //-----------------------------------------------Проверка представления транзакций----------------------------
//        t.performingTransaction(u1, u2, 400);
//        TransactionsLinkedList st1 = u1.gettList();
//        TransactionsLinkedList st2 = u2.gettList();
//        Transaction s1[] = st1.transformIntoArray();
//        Transaction s2[] = st2.transformIntoArray();
//        System.out.println("First");
//        for(int i = 0; i != s1.length; i ++){
//            System.out.println(s1[i].getSender().getName() + s1[i].getRecipient().getName() + s1[i].getTransferCategory());
//        }
//        System.out.println("Second");
//        for(int i = 0; i != s2.length; i ++){
//            System.out.println(s2[i].getSender().getName() + s2[i].getRecipient().getName() + s2[i].getTransferCategory());
//        }
//        //----------------------------------------------------------Получение транзакций пользователя-----------------------
//        System.out.println("Get List");
//        Transaction a[] = t.retrievingTransfers(u1);
//        for(int i = 0; i != a.length; i ++){
//            System.out.println(a[i].getSender().getName() + a[i].getRecipient().getName() + a[i].getTransferCategory());
//        }
//        //----------------------------------------------------------Получение непарных транзакций---------------------------
//        System.out.println("Get error transactions");
//        Transaction neww = new Transaction(u1, u2, Transaction.category.debits, 100);
//        u1.gettList().addTransaction(neww);
//        tranList.addTransaction(neww);
//
//        Transaction er[] = t.checkValidityTransactions();
//
//        for(int i = 0; i != er.length; i ++){
//            System.out.println(er[i].getSender().getName() + er[i].getTransferCategory() + er[i].getTransferAmount());
//        }
////-------------------------------------------------------Удаление транзакции-----------------------------------------
//        t.removeTransactionById(neww.getIdentifier(), u1.getIdentifier());
//        er = t.checkValidityTransactions();
//        System.out.println("After removing transaction");
//        for(int i = 0; i != er.length; i ++){
//            System.out.println(er[i].getSender().getName() + er[i].getTransferCategory() + er[i].getTransferAmount());
//        }
    }
}




