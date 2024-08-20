class UsersArrayList implements UsersList{
    private static int capacity = 2;
    private static User storeData[] = new User[capacity];
    private static int size = 0;
    public void addUser(User user){
        if(size == capacity){
            User tmpStore[] = new User[capacity];
            System.arraycopy(storeData, 0, tmpStore, 0, capacity);
            capacity = capacity * 2;
            storeData = new User[capacity];
            System.arraycopy(tmpStore, 0, storeData, 0, size);
        }
        storeData[size] = user;
        size ++;
    }

    public User retrieveUserIndex(Integer Index){
        if(Index < 0 || Index > size)
            throw new UserNotFoundException();
        return storeData[Index];
    }

    public Integer retrieveNumberOfUsers(){
        return size;
    }

    public User retrieveUserID(Integer id){
        for(int i = 0; i < size; i ++){
            if(storeData[i].getIdentifier() == id){
                return storeData[i];
            }
        }
        throw new UserNotFoundException();
    }

    public User[] getStore(){
        return storeData;
    }
     public int getSize(){
        return size;
     }



//    public static void main(String args[]){
//        User u1 = new User("John", 1000);
//        User u2 = new User("Mike", 2000);
//        User u3 = new User("Ivan", 100);
//        UsersArrayList k = new UsersArrayList() ;
////        System.out.println(size + " " + capacity);
//        k.addUser(u1);
//        k.addUser(u2);
//        k.addUser(u1);
//        k.addUser(u3);
//        for(int i = 0; i < 4; i ++){
//            System.out.println("Name = " + storeData[i].getName());
//        }
//        System.out.println((k.retrieveUserIndex(1)).getName());
//        System.out.println(k.retrieveNumberOfUsers());
//    }
}




