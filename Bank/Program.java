

class Program{
    public static void main(String args[]){
        if(args.length == 0)
            System.exit(-1);
        Menu menu = new Menu();
        menu.finishMenu(args);
    }
}


