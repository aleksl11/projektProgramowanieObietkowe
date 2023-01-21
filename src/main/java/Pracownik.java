public class Pracownik extends Osoba{
    String dataZatrudnienia;
    int wynagrodzenie;
    String eMail,stanowisko;

    public Pracownik(int id, String imie, String nazwisko, String dataUr, String nrTel, String dataZatrudnienia, int wynagrodzenie, String eMail, String stanowisko) {
        super(id, imie, nazwisko, dataUr, nrTel);
        this.dataZatrudnienia = dataZatrudnienia;
        this.wynagrodzenie = wynagrodzenie;
        this.eMail = eMail;
        this.stanowisko = stanowisko;
    }

    public String getDataZatrudnienia() {
        return dataZatrudnienia;
    }

    public void setDataZatrudnienia(String dataZatrudnienia) {
        this.dataZatrudnienia = dataZatrudnienia;
    }

    public int getWynagrodzenie() {
        return wynagrodzenie;
    }

    public void setWynagrodzenie(int wynagrodzenie) {
        this.wynagrodzenie = wynagrodzenie;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getStanowisko() {
        return stanowisko;
    }

    public void setStanowisko(String stanowisko) {
        this.stanowisko = stanowisko;
    }

    @Override
    public String toString() {
        return "Dane pracownika: \n" +
                "ID: " + id + '\n' +
                "Imie: " + imie + '\n' +
                "Nazwisko: " + nazwisko + '\n' +
                "Data urodzenia: " + dataUr + '\n' +
                "Data zatrudnienia: " + dataZatrudnienia + '\n' +
                "Wynagrodzenie: " + wynagrodzenie + '\n' +
                "Numer telefonu: " + nrTel + '\n' +
                "Adres e-mail: " + eMail + '\n' +
                "Stanowisko: " + stanowisko;
    }

    public void daneKontaktowe(){
        System.out.println(imie + " " + nazwisko + ", " + stanowisko+'\n' +
                "Numer telefonu: " + nrTel + '\n' +
                "Adres e-mail: " + eMail);
    }
}
