import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

public class Excel extends dbCreateObject{
    private static final String DELIMITER = ",";
    private static final String SEPARATOR = "\n";

    public static void eksportKlienci(){

        FileWriter file = null;
        String HEADER = "ID,Imie,Nazwisko,Data Urodzenia,Data dolaczenia,Nazwa uzytkownika,Haslo,Numer telefonu,ID grupy,Dlugosc karnetu, wplaty";
        try
        {
            file = new FileWriter("Klienci.csv");
            file.append(HEADER);
            file.append(SEPARATOR);
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
                file.append(String.valueOf(k.id));
                file.append(DELIMITER);
                file.append(k.imie);
                file.append(DELIMITER);
                file.append(k.nazwisko);
                file.append(DELIMITER);
                file.append(k.dataUr);
                file.append(DELIMITER);
                file.append(k.dataDolaczenia);
                file.append(DELIMITER);
                file.append(k.nazwaUzytkownika);
                file.append(DELIMITER);
                file.append(k.haslo);
                file.append(DELIMITER);
                file.append(k.nrTel);
                file.append(DELIMITER);
                file.append(String.valueOf(k.idGr));
                file.append(DELIMITER);
                file.append(String.valueOf(k.dlKarnetu));
                file.append(DELIMITER);
                file.append(String.valueOf(k.wplaty));
                file.append(SEPARATOR);
            }
            file.close();
            System.out.println("Zapisano plik .csv z danymi klientow");
        }
        catch(Exception e)
        {
            System.out.println("Wystapil blad z zapisem pliku");
        }
    }

    public static void eksportPracownicy(){

        FileWriter file = null;
        String HEADER = "ID,Imie,Nazwisko,Data Urodzenia,Data zatrudnienia,Wynagrodzenie,Numer telefonu,e-mail,Stanowisko";
        try
        {
            file = new FileWriter("Pracownicy.csv");
            file.append(HEADER);
            file.append(SEPARATOR);
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
                file.append(String.valueOf(p.id));
                file.append(DELIMITER);
                file.append(p.imie);
                file.append(DELIMITER);
                file.append(p.nazwisko);
                file.append(DELIMITER);
                file.append(p.dataUr);
                file.append(DELIMITER);
                file.append(p.dataZatrudnienia);
                file.append(DELIMITER);
                file.append(String.valueOf(p.wynagrodzenie));
                file.append(DELIMITER);
                file.append(p.nrTel);
                file.append(DELIMITER);
                file.append(p.eMail);
                file.append(DELIMITER);
                file.append(p.stanowisko);
                file.append(DELIMITER);
                file.append(SEPARATOR);
            }
            file.close();
            System.out.println("Zapisano plik .csv z danymi pracownikow");
        }
        catch(Exception e)
        {
            System.out.println("Wystapil blad z zapisem pliku");
        }
    }
    public static void importKlienci(){
        try {
            String insert = "insert into Klienci (imie,nazwisko,username,haslo,dataUrodzenia,dataDolaczenia,nrTelefonu,idGrupy,wplaty,dlKarnetu) values (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = connect().prepareStatement(insert);
            BufferedReader lineReader=new BufferedReader(new FileReader("nowiKlienci.csv"));
            String lineText=null;

            lineReader.readLine();
            while((lineText=lineReader.readLine())!=null){
                String[] data=lineText.split(",");
                String imie=data[0];
                String nazwisko=data[1];
                String username=data[2];
                try {
                    ResultSet result=QueryExecutor.executeSelect("select * from klienci where username=\"" + username + "\"");
                    result.next();
                    if(result.getString("username").equals(username))  System.out.println("Nazwa uzytkownika jedego z klientow jest jux zajeta");
                    break;
                } catch (SQLException e){
                    if(username.equals("admin")) {
                        System.out.println("Nazwa uzytkownika jedego z klientow jest juz zajeta");
                        break;
                    }
                }
                String haslo=data[3];
                String dataUrodzenia=data[4];
                String dataDolaczenia=data[5];
                String nrTelefonu=data[6];
                int idGrupy;
                if(!data[7].equals("")){
                    idGrupy=Integer.parseInt(data[7]);
                    statement.setInt(8,idGrupy);
                }
                else statement.setNull(8,java.sql.Types.INTEGER);
                int wplaty=Integer.parseInt(data[8]);
                if(wplaty<0)wplaty=0;
                int dlKarnetu=Integer.parseInt(data[9]);
                if(dlKarnetu<0)dlKarnetu=0;

                statement.setString(1,imie);
                statement.setString(2,nazwisko);
                statement.setString(3,username);
                statement.setString(4,haslo);
                statement.setString(5,dataUrodzenia);
                statement.setString(6,dataDolaczenia);
                statement.setString(7,nrTelefonu);

                statement.setInt(9,wplaty);
                statement.setInt(10,dlKarnetu);

                statement.addBatch();
                statement.executeBatch();

            }
            lineReader.close();
            statement.executeBatch();
            System.out.println("Nowi klienci dodani do bazy");
        }catch(Exception e){
            System.out.println("Blad przy wczytywaniu danych do bazy");
        }
    }
    public static void importPracownicy(){
        try{
            String insert = "insert into Pracownicy (imie,nazwisko,dataUrodzenia,dataZatrudnienia,wynagrodzenie,nrTelefonu,eMail,stanowisko) values (?,?,?,?,?,?,?,?)";
            PreparedStatement statement = connect().prepareStatement(insert);
            BufferedReader lineReader=new BufferedReader(new FileReader("nowiPracownicy.csv"));
            String lineText=null;

            lineReader.readLine();
            while((lineText=lineReader.readLine())!=null){
                String[] data=lineText.split(",");
                String imie=data[0];
                String nazwisko=data[1];
                String dataUrodzenia=data[2];
                String dataZatrudnienia=data[3];
                int wynagrodzenie=Integer.parseInt(data[4]);
                String nrTelefonu=data[5];
                String eMail=data[6];
                String stanowisko=data[7];

                statement.setString(1,imie);
                statement.setString(2,nazwisko);
                statement.setString(3,dataUrodzenia);
                statement.setString(4,dataZatrudnienia);
                statement.setInt(5,wynagrodzenie);
                statement.setString(6,nrTelefonu);
                statement.setString(7,eMail);
                statement.setString(8,stanowisko);

                statement.addBatch();
                statement.executeBatch();

            }
            lineReader.close();
            statement.executeBatch();
            System.out.println("Nowi pracownicy dodani do bazy");
        }catch(Exception e){
            System.out.println("Blad przy wczytywaniu danych do bazy");
        }
    }
}
