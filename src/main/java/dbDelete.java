import java.sql.ResultSet;
import java.sql.SQLException;

public class dbDelete extends QueryExecutor{
    public static void deletePracownik(int id){
        String delete = "delete from pracownicy where idPracownika="+id;
        try {
            ResultSet result=executeSelect("select * from pracownicy where idPracownika= "+id+ " and idPracownika in(select idPracownika from Grupy)");
            if (result.next()) System.out.println("Pracownik prowadzi grupe i nie moze byc usuniety");
            else {
                try {
                    ResultSet result2=executeSelect("select * from pracownicy where idPracownika= "+id);
                    if(result2.next()) {
                        executeQuery(delete);
                        System.out.println("Pracownik "+id+" zostal usuniety za bazy danych");
                    }
                    else System.out.println("Nie ma takiego id praconika w bazie danych");
                } catch (SQLException e){
                    System.out.println("Usuwanie nie powiodlo sie");
                }

            }
        } catch (SQLException e){
            System.out.println("Usuwanie nie powiodlo sie");
        }

    }
    public static void deleteKlient(int id){
        String delete = "delete from klienci where idKlienta="+id;
        try {
            ResultSet result=executeSelect("select * from klienci where idKlienta= "+id);
            if(result.next()) {
                executeQuery(delete);
                System.out.println("Klient " + id + " zostal usuniety za bazy danych");
            }
            else System.out.println("Nie ma takiego id klienta w bazie danych");
        } catch (SQLException e){
            System.out.println("Usuwanie nie powiodlo sie");
        }

    }
}
