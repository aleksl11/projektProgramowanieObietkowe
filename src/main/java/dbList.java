import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class dbList extends dbCreateObject{
    public static void wypiszKontaktPracownikow(){
        List<Pracownik> pKontaktList = new ArrayList<>();
        try {
            ResultSet result = executeSelect("select idPracownika from pracownicy");
            while (result.next()) {
                pKontaktList.add(dbPracownik(result.getInt(1)));
            }
        } catch (SQLException e) {
            System.out.println("Brak pracownikow");
        }
        for (Pracownik p : pKontaktList) {
            p.daneKontaktowe();
            System.out.println();
        }
    }

    public static void wypiszPracownikow(){
        List<Pracownik> pracownikList = new ArrayList<>();
        try {
            ResultSet result = executeSelect("select idPracownika from pracownicy");
            while (result.next()) {
                pracownikList.add(dbPracownik(result.getInt(1)));
            }
        } catch (SQLException e) {
            System.out.println("Brak pracownikow");
        }
        for (Pracownik p : pracownikList) {
            System.out.println(p);
            System.out.println();
        }
    }

    public static void wypiszKlientow(){
        List<Klient> klientList = new ArrayList<>();
        try {
            ResultSet result = executeSelect("select username from klienci");
            while (result.next()) {
                klientList.add(dbKlient(result.getString(1)));
            }
        } catch (SQLException e) {
            System.out.println("Brak klientow");
        }
        for (Klient k : klientList) {
            System.out.println(k);
            System.out.println();
        }
    }

}
