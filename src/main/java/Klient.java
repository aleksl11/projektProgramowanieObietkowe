import java.sql.ResultSet;
import java.sql.SQLException;

public class Klient extends Osoba{
    String dataDolaczenia;
    int idGr, wplaty,dlKarnetu;
    String nazwaUzytkownika, haslo;

    public Klient(int id, String imie, String nazwisko, String dataUr, String nrTel, String dataDolaczenia, int idGr, int wplaty, String nazwaUzytkownika, String haslo, int dlKarnetu) {
        super(id, imie, nazwisko, dataUr, nrTel);
        this.dataDolaczenia = dataDolaczenia;
        this.idGr = idGr;
        this.wplaty = wplaty;
        this.nazwaUzytkownika = nazwaUzytkownika;
        this.haslo = haslo;
        this.dlKarnetu = dlKarnetu;
    }

    @Override
    public String toString() {
        return "Dane klienta: \n" +
                "ID: " + id + '\n' +
                "Imie: " + imie + '\n' +
                "Nazwisko: " + nazwisko + '\n' +
                "Data urodzenia: " + dataUr + '\n' +
                "Data dolaczenia do silowni: " + dataDolaczenia + '\n' +
                "Nazwa uzytkownika: " + nazwaUzytkownika + '\n' +
                "Haslo: " + haslo + '\n' +
                "Numer telefonu: " + nrTel + '\n' +
                "Numer grupy cwiczeniowej: " + idGr + '\n' +
                "Dlugosc karnetu: " + dlKarnetu + " dni\n" +
                "Do zaplaty: " + wplaty +" zl";
    }


}
