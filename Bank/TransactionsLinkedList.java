import java.util.UUID;
class TransactionsLinkedList implements TransactionsList{
    private int size = 0;
    private Node first;
    private Node last;

    private class Node{
        Transaction item;
        Node prev;
        Node next;
        Node(Node pr, Transaction element, Node n){
            prev = pr;
            item = element;
            next = n;
        }
    }


    public void addTransaction(Transaction transaction){
        Node newNode = new Node(last, transaction, null);
        if(size == 0){
            first = newNode;
        } else {
            last.next = newNode;
        }
        last = newNode;
        size ++;
    }

    public void removeTransactionById(UUID id) {
        if(id == null || last == null){
            throw new TransactionNotFoundException();
        }
        for (Node i = first; i != null; i = i.next) {
            if (i.item.getIdentifier() == id) {
                Node next_element = i.next;
                Node prev_element = i.prev;
                if (prev_element == null) {
                    first = next_element;
                } else {
                    prev_element.next = next_element;
                    i.prev = null;
                }
                if (next_element == null) {
                    last = next_element;
                } else {
                    next_element.prev = prev_element;
                    i.next = null;
                }
                i.item = null;
                size --;
                return;
            }
        }
        throw new TransactionNotFoundException();
    }

    public Transaction[] transformIntoArray(){
        int j = 0;
        Transaction [] result = new Transaction[size];
        for(Node i = first; i != null; i = i.next){
            result[j++] = i.item;
        }
        return result;
    }

    public int getSize(){
        return size;
    }
}









