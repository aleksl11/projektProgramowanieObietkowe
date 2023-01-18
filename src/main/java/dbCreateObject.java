import java.sql.ResultSet;
import java.sql.SQLException;

public class dbCreateObject extends QueryExecutor{
    public static Klient dbKlient(String username){
        String select="select * from klienci where username='"+username+"'";
        try{
            ResultSet result = executeSelect(select);
            result.next();
            int idKlienta = result.getInt("idKlienta");
            String imieKlienta= result.getString("imie");
            String nazwiskoKlienta= result.getString("nazwisko");
            String dataUrodzenia= result.getString("dataUrodzenia");
            String nrTelefonu= result.getString("nrTelefonu");
            String dataDolaczenia= result.getString("dataDolaczenia");
            int idGrupy= result.getInt("idGrupy");
            int wplaty= result.getInt("wplaty");
            String haslo = result.getString("haslo");
            String nazwaUz= result.getString("username");
            int dlKarnetu= result.getInt("dlKarnetu");

            return new Klient(idKlienta,imieKlienta,nazwiskoKlienta,dataUrodzenia,nrTelefonu,dataDolaczenia,idGrupy,wplaty,nazwaUz,haslo,dlKarnetu);

        }catch(SQLException e){
            System.out.println("Klient nie znajduje sie w bazie danych");
            return null;
        }
    }
    public static Pracownik dbPracownik(int id){
        String select="select * from pracownicy where idPracownika="+id;
        try{
            ResultSet result = executeSelect(select);
            result.next();
            String imie= result.getString("imie");
            String nazwisko= result.getString("nazwisko");
            String dataUrodzenia= result.getString("dataUrodzenia");
            String nrTelefonu= result.getString("nrTelefonu");
            String dataZatrudnienia= result.getString("dataZatrudnienia");
            int wynagrodzenie= result.getInt("wynagrodzenie");
            String eMail = result.getString("eMail");
            String stanowisko= result.getString("stanowisko");

            return new Pracownik(id,imie,nazwisko,dataUrodzenia,nrTelefonu,dataZatrudnienia,wynagrodzenie,eMail,stanowisko);

        }catch(SQLException e){
            System.out.println("Pracownik nie znajduje sie w bazie danych");
            return null;
        }
    }

    public static Grupa dbGrupa(int id){
        String select="select * from grupy where idGrupy="+id;
        try{
            ResultSet result = executeSelect(select);
            result.next();
            String nazwa= result.getString("nazwa");
            int idTrener= result.getInt("idPracownika");
            int max= result.getInt("maxUczestnikow");
            int cena= result.getInt("cena");

            ResultSet resultTrener = executeSelect("select nazwisko from pracownicy where idPracownika="+idTrener);
            resultTrener.next();
            String trener= resultTrener.getString(1);

            ResultSet resultUczestnicy = executeSelect("select count(*) from klienci where idGrupy="+id);
            resultUczestnicy.next();
            int uczestnicy=resultUczestnicy.getInt(1);
            return new Grupa(cena,id,nazwa,trener,max,uczestnicy);

        }catch(SQLException e){
            System.out.println("Brak grupy");
            return null;
        }
    }

    public static Karnet dbKarnet(int id){
        String select="select * from karnety where idKarnetu="+id;
        try{
            ResultSet result = executeSelect(select);
            result.next();
            int dlugosc= result.getInt("dlugosc");
            int cena= result.getInt("cena");

            return new Karnet(cena,id,dlugosc);

        }catch(SQLException e){
            System.out.println("Brak karnetu");
            return null;
        }
    }
}
