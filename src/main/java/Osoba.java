public class Osoba {
    int id;
    String imie,nazwisko,dataUr,nrTel;

    public Osoba(int id, String imie, String nazwisko, String dataUr, String nrTel) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.dataUr = dataUr;
        this.nrTel = nrTel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getDataUr() {
        return dataUr;
    }

    public void setDataUr(String dataUr) {
        this.dataUr = dataUr;
    }

    public String getNrTel() {
        return nrTel;
    }

    public void setNrTel(String nrTel) {
        this.nrTel = nrTel;
    }
}
