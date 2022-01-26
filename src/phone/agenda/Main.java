package phone.agenda;

public class Main {
    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        System.out.println(" ===== Adding agenda content =====");

        Person p1=new Person("Alex","07451");
        Person p2=new Person("Ionut","07452");
        Person p3=new Person("Bogdan","07544");
        Person p4=new Person("Rares","07235");

        agenda.addNumberInAgenda(p1);
        agenda.addNumberInAgenda(p2);
        agenda.addNumberInAgenda(p3);
        agenda.addNumberInAgenda(p4);


        agenda.removeNumberFromAgenda(p3);

        agenda.updatePhoneNumberInAgenda("Alex","00000");

        agenda.updateNameOfPersonFromAgenda("07235","Ionel");

        for (int i=0; i<agenda.getAgendaContent().size();i++){
            System.out.println(agenda.getAgendaContent().get(i));
        }


        System.out.println(agenda.getAgendaSortedByName());


    }
}