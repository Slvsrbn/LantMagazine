package phone.agenda;

import java.util.ArrayList;

public class Agenda {
    private ArrayList<Person> agendaContent = new ArrayList<>();

    /*
    this methdod should add a created person in agenda
    minimal checks should be done
        1. p has name
        2. p has phone number
    If one field is not defined, a message should be displayed and person is not added in agenda.
    Before adding the person in agenda, check that the number and name does not exists

     */


    public void addNumberInAgenda(Person p) {

        if (isPersonPopulated(p)) {
            if (alreadyExists(p)) {
                System.out.println("Deja exista");
            } else {
                this.agendaContent.add(p);
            }

        }
    }

    private boolean isPersonPopulated(Person p) {
        return p.getName() != null && p.getPhoneNumber() != null;
    }

    private boolean alreadyExists(Person p) {
        boolean found = false;
        for (int i = 0; i < agendaContent.size(); i++) {
            if (p.getName().equals(agendaContent.get(i).getName()) ||
                    p.getPhoneNumber().equals(agendaContent.get(i).getPhoneNumber())) {
                found = true;
            }
        }
        return found;
    }


    /*
    Remove a person from agenda
    Minimal checks made for the person to be removed
        1. p has name
        2. p has phone number
     */
    public void removeNumberFromAgenda(Person p) {
        for (int i = 0; i < agendaContent.size(); i++) {
            if (p.getName().equals(agendaContent.get(i).getName()) && p.getPhoneNumber().equals(agendaContent.get(i).getPhoneNumber())) {
                agendaContent.remove(p);
                break;
            }
        }
    }

    public void updatePhoneNumberInAgenda(String nume, String phoneNumber) {
        for (int i = 0; i < agendaContent.size(); i++) {
            if (nume.equals(agendaContent.get(i).getName())) {
                agendaContent.get(i).setPhoneNumber(phoneNumber);
                break;
            }
        }
    }

    public void updateNameOfPersonFromAgenda(String phoneNumber, String nume) {
        for (int i = 0; i < agendaContent.size(); i++) {
            if (phoneNumber.equals(agendaContent.get(i).getPhoneNumber())) {
                agendaContent.get(i).setName(nume);
                break;
            }
        }
    }

    /*
    Return a NEW array list, that contains the agenda sorted by name
     */
    public ArrayList<Person> getAgendaSortedByName() {
        Person k;
        for (int i = 0; i < agendaContent.size(); i++) {
            for (int j=i+1; j<agendaContent.size();j++) {
                if (agendaContent.get(i).getPhoneNumber().compareTo(agendaContent.get(j).getPhoneNumber()) > 0) {
                    k = agendaContent.get(i);
                    agendaContent.set(i, agendaContent.get(j));
                    agendaContent.set(j, k);
                }
            }
        }
        return this.agendaContent;
    }

    public ArrayList<Person> getAgendaContent() {
        return this.agendaContent;

    }
}



