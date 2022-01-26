package biblioteca;

public class Main {
    public static void main(String[] args) {
        Library centrala= new Library();
        Library periferica= new Library();

        Book book=new Book("Arghezi","prima carte de la Arghezi",1,"drama");
        Book book1=new Book("Eminescu","prima carte de la Eminescu",1,"poezii");
        Book book2=new Book("Slavici","prima carte a lui Slavici",1,"nuvele");
        Book book3=new Book("Toparceanu","prima carte a lui Toparceanu",1,"poiezii");
        Book book4=new Book("Eminescu","a doua carte a lui Eminescu",2,"poiezii");
        Book book5=new Book("Cosbuc","prima carte a lui Cosbuc",1,"poezii");

        centrala.addBooksOnRaft(book);
        centrala.addBooksOnRaft(book1);
        centrala.addBooksOnRaft(book3);
        centrala.addBooksOnRaft(book4);
        centrala.addBooksOnRaft(book5);
        periferica.addBooksOnRaft(book);
        periferica.addBooksOnRaft(book1);
        periferica.addBooksOnRaft(book2);
        periferica.addBooksOnRaft(book3);
        System.out.println();

    }
}
