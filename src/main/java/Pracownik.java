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
