package lantmagazine;

public class Person {
    private String nume;
    private String prenume;
    private String id;

    public Person(String nume, String prenume, String id) {
        this.nume = nume;
        this.prenume = prenume;
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

