import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        while (true) {
            System.out.println("--------");
            System.out.println("1. Zaloguj\n" +
                                "2. Wyjdz");
            int choice=wyborOpcji();
            if (choice==1){
                login();
            }
            else if(choice==2)break;
            else System.out.println("Niepoprawny wybor");
        }
    }

    public static int wyborOpcji(){
        Scanner input = new Scanner(System.in);
        System.out.println("Podaj numer opcji: ");
        String choice = input.next();
        try {
            return Integer.parseInt(choice);
        } catch (NumberFormatException e){
            System.out.println("Wybor musi byc liczba.");
            return 0;
        }
    }

    public static void login(){
        while (true){
            Scanner input=new Scanner(System.in);
            String login, haslo;
            System.out.println("nazwa uzytkownika: ");
            login=input.nextLine();
            System.out.println("haslo: ");
            haslo=input.nextLine();

            String select="select * from Klienci where username=\""+login+"\"";
            try{
                    ResultSet result = QueryExecutor.executeSelect(select);
                    result.next();
                    String hasloDb = result.getString("haslo");

                    if (hasloDb.equals(haslo)) {
                        Klient klient=dbCreateObject.dbKlient(login);
                        if(klient==null)break;
                        menu(klient);
                        break;
                    }

            }catch(SQLException e){
                //e.printStackTrace();
            }
            System.out.println("Niepoprawne dane logowania");
            System.out.println("1.Sprobuj ponownie\n" +
                    "2.Wyjdz");
            int choice=wyborOpcji();
            if (choice==2)break;
        }
    }
    public static void menu(Klient klient){

        while (true) {
            System.out.println("Witaj "+klient.imie+"!");
            System.out.println("--------");
            System.out.println("--MENU--\n" +
                    "--------\n" +
                    "1.Karnet\n" +
                    "2.Grupy\n" +
                    "3.Dane uzytkownika\n" +
                    "4.Lista pracownikow\n" +
                    "5.Wyloguj\n");
            int choice=wyborOpcji();
            if(choice==1)karnet(klient);
            else if(choice==2)grupy(klient);
            else if(choice==3)dane(klient);
            else if(choice==4)pracownicy();
            else if(choice==5)break;
            else System.out.println("Niepoprawny wybor.");
        }
    }
    public static void karnet(Klient klient){
        while (true){
            System.out.println("--------");
            System.out.println("Pozostała dlugosc karnetu: "+ klient.dlKarnetu+" dni");
            System.out.println("1.Przedluz karnet\n" +
                                "2.Wroc do menu");
            int choice= wyborOpcji();
            if(choice==1){
                List<Karnet> karnetList = new ArrayList<>();
                try {
                    ResultSet result = QueryExecutor.executeSelect("select * from karnety");
                    while (result.next()) {
                        karnetList.add(dbCreateObject.dbKarnet(result.getInt(1)));
                    }
                } catch (SQLException e) {
                    System.out.println("Brak dostepnych karnetow");
                }
                if(karnetList.isEmpty()){
                    System.out.println("Brak dostepnych karnetow");

                }
                else {
                    for (Karnet k : karnetList) {
                        k.opis();
                    }
                    int choice2 = wyborOpcji();
                    for (Karnet k : karnetList) {
                        if(k.id==choice2){
                            klient.dlKarnetu+=k.dlugosc;
                            klient.wplaty+=k.cena;
                            dbUpdate.updateKarnet(klient);
                        }
                    }
                }
            }
            else if(choice==2)break;
            else System.out.println("Niepoprawny wybor.");
        }
    }
    public static void grupy(Klient klient){
        while (true){
            int choice;
            if (klient.idGr==0) {
                System.out.println("Nie nalezysz do zadnej grupy\n" +
                        "1.Dolacz do grupy\n" +
                        "2.Wroc do menu");
                choice = wyborOpcji();
                if (choice == 1) {
                    List<Grupa> grupaList = new ArrayList<>();
                    try {
                        ResultSet result = QueryExecutor.executeSelect("select * from grupy");
                        while (result.next()) {
                            Grupa g=dbCreateObject.dbGrupa(result.getInt(1));
                            if(!g.pelna())grupaList.add(g);
                        }
                    } catch (SQLException e) {
                        System.out.println("Brak dostepnych grup");
                    }
                    if(grupaList.isEmpty()){
                        System.out.println("Brak dostepnych grup");

                    }
                    else {
                        for (Grupa g : grupaList) {
                            g.opis();
                        }
                        int choice2 = wyborOpcji();
                        for (Grupa g : grupaList) {
                            if(g.id==choice2){
                                klient.idGr=g.id;
                                klient.wplaty+=g.cena;
                                dbUpdate.updateGrupa(klient);
                            }
                        }
                    }
                }
                else if (choice == 2) break;
                else System.out.println("Niepoprawny wybor.");
            }
            else {
                System.out.println("Nalezysz do grupy: ");
                System.out.println("1.Opsusc grupe\n" +
                                    "2.Wroc do menu");
                choice=wyborOpcji();
                if (choice == 1) {
                    klient.idGr=0;
                    QueryExecutor.executeQuery("update klienci set idGrupy=null where idKlienta="+klient.id);
                } else if (choice == 2) break;
                else System.out.println("Niepoprawny wybor.");
            }
        }
    }
    public static void dane(Klient klient){
        while (true){
            System.out.println("--------");
            System.out.println(klient.toString());
            System.out.println("--------");
            System.out.println("1.Zmien dane logowania\n" +
                                "2.Wyjdz i zapisz zmiany\n"+
                                "3.Wyjdz bez zapisywania");
            int choice= wyborOpcji();
            if(choice==1){
                System.out.println("--------");
                System.out.println("1.Nazwa Uzytkownika\n" +
                                    "2. Haslo");
                int choice2=wyborOpcji();
                Scanner input=new Scanner(System.in);
                if(choice2==1){
                    String nazwa = input.next();
                    try {
                        ResultSet result=QueryExecutor.executeSelect("select * from klienci where username=\"" + nazwa + "\"");
                        result.next();
                        if(result.getString("username").equals(nazwa))  System.out.println("Nazwa uzytkownika zajeta");

                    } catch (SQLException e){
                        klient.nazwaUzytkownika=nazwa;
                    }

                }
                else if(choice2==2){
                    klient.haslo=input.next();
                }
                else System.out.println("Niepoprawny wybor");
            }
            else if(choice==2){
                dbUpdate.updateKlient(klient);
                break;}
            else if(choice==3)break;
            else System.out.println("Niepoprawny wybor.");
        }
    }
    public static void pracownicy(){
        List<Pracownik> pracownikList = new ArrayList<>();
        try {
            ResultSet result = QueryExecutor.executeSelect("select idPracownika from pracownicy");
            while (result.next()) {
                pracownikList.add(dbCreateObject.dbPracownik(result.getInt(1)));
            }
        } catch (SQLException e) {
            System.out.println("Brak pracownikow");
        }
        for (Pracownik p : pracownikList) {
            p.daneKontaktowe();
            System.out.println();
        }
        while (true){
            System.out.println("1.Wroc do menu");
            int choice = wyborOpcji();
            if (choice == 1) break;
        }

    }
}
