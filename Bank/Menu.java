import java.util.UUID;
import java.util.Scanner;
class Menu{
    static TransactionsService service = new TransactionsService();
    static Scanner scanner = new Scanner(System.in);
    private static String mode = "";

    static void draw(){
        System.out.println("1. Add a user");
        System.out.println("2. View user balances");
        System.out.println("3. Perform a transfer");
        System.out.println("4. View all transactions for a specific user");
        System.out.println("5. DEV - remove a transfer by ID");
        System.out.println("6. DEV - check transfer validity");
        System.out.println("7. Finish execution");
    }

    private static Transaction checkTransaction(Transaction t[], UUID id){
        Transaction result = null;
        for(int i = 0; i != t.length; i ++)
        {
            if(t[i].getIdentifier().equals(id)){
                result = t[i];
            }
        }
        return result;
    }

    private static void pointOne(){
        try{
            System.out.println("Enter a user name and a balance");
            Integer balance = 0;
            String name = "";
            try {
                scanner.nextLine();
                String input = scanner.nextLine();
                String parts [] = input.split(" ");
                name = parts[0];
                balance = Integer.parseInt(parts[1]);
            } catch (Exception e) {
                System.out.println("Invalid input.");
                return;
            }
            User user = new User(name, balance);
            service.addUser(user);
            System.out.println("User with id = " + user.getIdentifier() + " is added");
        }catch (Exception e) {
            System.out.println(e);
        } finally{
            System.out.println("---------------------------------------------------------");
        }
    }
    private static void pointTwo(){
        try{
            int id = 0;
            try{
                System.out.println("Enter a user ID");
                scanner.nextLine();
                id = scanner.nextInt();
            }catch(Exception e){
                System.out.println("Invalid input.");
                return;
            }
            System.out.println(service.usersList.retrieveUserID(id).getName() + " - " + service.retrieveUsersBalance(service.usersList.retrieveUserID(id)));
        } catch(Exception e){
            System.out.println(e);
        } finally{
            System.out.println("---------------------------------------------------------");
        }
    }

    private static void pointThree(){
        try{
            System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
            int senderID = 0;
            int recipientID = 0;
            int amount = 0;
            try{
                scanner.nextLine();
                senderID = scanner.nextInt();
                recipientID = scanner.nextInt();
                amount = scanner.nextInt();
            } catch(Exception e){
                System.out.println("Invalid input.");
                return;
            }
            service.performingTransaction(service.usersList.retrieveUserID(senderID), service.usersList.retrieveUserID(recipientID), amount);
            System.out.println("The transfer is completed");
        } catch (Exception e){
            System.out.println(e);
        } finally{
            System.out.println("---------------------------------------------------------");
        }
    }



    private static void pointFour(){
        try{
            System.out.println("Enter a user ID");
            int id = 0;
            try{
                scanner.nextLine();
                id = scanner.nextInt();
            } catch (Exception e){
                System.out.println("Invalid input.");
                return;
            }
            Transaction transactions[] = service.retrievingTransfers(service.usersList.retrieveUserID(id));
            for(int i = 0; i != transactions.length; i ++){
                if(transactions[i].getTransferCategory().equals(Transaction.category.debits)){
                    System.out.print("From " + transactions[i].getRecipient().getName() + "(id = " + transactions[i].getRecipient().getIdentifier() + ") ");
                } else {
                    System.out.print("To " + transactions[i].getSender().getName() + "(id = " + transactions[i].getSender().getIdentifier() + ") ");
                }
                System.out.print(transactions[i].getTransferAmount());
                System.out.println(" with id = " + transactions[i].getIdentifier());
            }
        } catch (Exception e){
            System.out.println(e);
        } finally{
            System.out.println("---------------------------------------------------------");
        }
    }

    private static void pointFive(){
        try{
            if(mode.equals("--profile=production"))
                System.exit(-1);
            System.out.println("Enter a user ID and a transfer ID");
            Integer id = 0;
            UUID trID = UUID.randomUUID();
            try{
                scanner.nextLine();
                String input = scanner.nextLine();
                String parts[] = input.split(" ");
                id = Integer.parseInt(parts[0]);
                trID = UUID.fromString(parts[1]);
            } catch (Exception e){
                System.out.println("Invalid input.");
                return;
            }
            Transaction[] t = service.usersList.retrieveUserID(id).gettList().transformIntoArray();
            Transaction current = checkTransaction(t, trID);
            if(current == null){
                throw new TransactionNotFoundException();
            }
            service.removeTransactionById(current.getIdentifier(), id);
            if(current.getTransferCategory().equals(Transaction.category.credits)){
                System.out.print("Transfer To " + current.getSender().getName() + "(id = " + current.getSender().getIdentifier() + ") ");
            } else {
                System.out.print("Transfer From " + current.getRecipient().getName() + "(id = " + current.getRecipient().getIdentifier() + ") ");
            }
            System.out.println(((current.getTransferAmount() < 0) ? current.getTransferAmount() * (-1) : current.getTransferAmount()) + " removed");

        } catch (Exception e){
            System.out.println(e);
        } finally{
            System.out.println("---------------------------------------------------------");
        }
    }

    private static void pointSix(){
        try{
            Transaction result [] = service.checkValidityTransactions();
            if(result.length == 0){
                System.out.println("All transactions are correct!");
            } else {
                for(int i = 0; i != result.length; i ++){
                    if(result[i].getTransferCategory().equals(Transaction.category.debits)){
                        System.out.print(result[i].getSender().getName() + "(id = " + result[i].getSender().getIdentifier() + ") ");
                        System.out.print("has an unacknowledged transfer id = " + result[i].getIdentifier() + " from " + result[i].getRecipient().getName()  + "(id = " + result[i].getRecipient().getIdentifier() + ") ");
                        System.out.println(" for " + (result[i].getTransferAmount() < 0 ? result[i].getTransferAmount() * (-1) : result[i].getTransferAmount()));
                    } else {
                        System.out.print(result[i].getRecipient().getName() + "(id = " + result[i].getRecipient().getIdentifier() + ") ");
                        System.out.print("has an unacknowledged transfer id = " + result[i].getIdentifier() + " from " + result[i].getSender().getName()  + "(id = " + result[i].getSender().getIdentifier() + ") ");
                        System.out.println(" for " + (result[i].getTransferAmount() < 0 ? result[i].getTransferAmount() * (-1) : result[i].getTransferAmount()));
                    }

                }
            }
        } catch (Exception e){
            System.out.println(e);
        } finally{
            System.out.println("---------------------------------------------------------");
        }
    }

    public static void finishMenu(String args[]){
        mode = args[0];
        if(mode.equals("--profile=dev") || mode.equals("--profile=production")){
            while(true){
                draw();
                if(scanner.hasNextInt()){
                    int point = scanner.nextInt();
                    if(point == 7)
                        System.exit(-1);
                    if(point == 1){
                        pointOne();
                    } else if(point == 2){
                        pointTwo();
                    } else if(point == 3){
                        pointThree();
                    } else if(point == 4){
                        pointFour();
                    } else if(point == 5){
                        pointFive();
                    } else if(point == 6){
                        pointSix();
                    } else {
                        System.out.println("Incorrect data, try again");
                    }
                } else {
                    System.out.println("Invalid input.");
                    scanner.nextLine();
                }
            }
        } else {
            System.err.println("Invalid launch mode.");
            System.exit(-1);
        }
    }
}



