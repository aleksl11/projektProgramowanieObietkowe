import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Opcje{

    public static int wyborOpcji(){
        Scanner input = new Scanner(System.in);
        while(true) {
            System.out.println("Podaj numer opcji: ");
            String choice = input.next();
            try {
                return Integer.parseInt(choice);
            } catch (NumberFormatException e) {
                System.out.println("Wybor musi byc liczba.");
            }
        }
    }
    public static int wyborID(){
        Scanner input = new Scanner(System.in);
        while(true) {
            System.out.println("Podaj numer ID: ");
            String choice = input.next();
            try {
                return Integer.parseInt(choice);
            } catch (NumberFormatException e) {
                System.out.println("ID musi byc liczba.");
            }
        }
    }
    public static void start(){
        while (true) {
            System.out.println("--------");
            System.out.println("1.Zaloguj\n" +
                    "2.Wyjdz");
            int choice=wyborOpcji();
            if (choice==1){
                login();
            }
            else if(choice==2)break;
            else System.out.println("Niepoprawny wybor");
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
            if(login.equals("admin") && haslo.equals("admin")){
                menuPraconik();
                break;
            }
            else{
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
    }



    public static void menu(Klient klient){
        System.out.println("Witaj "+klient.imie+"!");
        while (true) {
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
                    System.out.println("Brak karnetow w bazie danych");
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
                        System.out.println("Brak grup w bazie danych");
                    }
                    if(grupaList.isEmpty()){
                        System.out.println("Brak wolnych miejsc w grupach");

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
                        "2.Haslo");
                int choice2=wyborOpcji();
                Scanner input=new Scanner(System.in);
                if(choice2==1){
                    String nazwa = input.next();
                    try {
                        ResultSet result=QueryExecutor.executeSelect("select * from klienci where username=\"" + nazwa + "\"");
                        result.next();
                        if(result.getString("username").equals(nazwa))  System.out.println("Nazwa uzytkownika zajeta");

                    } catch (SQLException e){
                        if(nazwa.equals("admin")) System.out.println("Nazwa uzytkownika zajeta");
                        else klient.nazwaUzytkownika=nazwa;
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
        dbList.wypiszKontaktPracownikow();
        while (true){
            System.out.println("1.Wroc do menu");
            int choice = wyborOpcji();
            if (choice == 1) break;
        }

    }

    public static void menuPraconik(){
        System.out.println("Panel pracownika");
        while (true) {
            System.out.println("--------");
            System.out.println("--MENU--\n" +
                    "--------\n" +
                    "1.Wypisz klientow\n" +
                    "2.Wypisz pracownikow\n" +
                    "3.Wyeksportuj dane\n" +
                    "4.Zaimportuj dane\n" +
                    "5.Usun klienta\n" +
                    "6.Usun pracownika\n"+
                    "7.Wyloguj\n");
            int choice=wyborOpcji();
            if(choice==1) dbList.wypiszKlientow();
            else if(choice==2) dbList.wypiszPracownikow();
            else if(choice==3) eksportExcel();
            else if(choice==4) importExcel();
            else if(choice==5){
                System.out.println("Podaj numer ID klienta");
                int choice2=wyborID();
                dbDelete.deleteKlient(choice2);
            }
            else if(choice==6){
                System.out.println("Podaj numer ID pracownika");
                int choice2=wyborID();
                dbDelete.deletePracownik(choice2);
            }
            else if(choice==7)break;
            else System.out.println("Niepoprawny wybor.");
        }
    }
    public static void eksportExcel(){
        System.out.println("1.Eksportuj pracownikow\n" +
                "2.Eksportuj klientow");
        int choice=wyborOpcji();
        if (choice==1) Excel.eksportPracownicy();
        else if(choice==2) Excel.eksportKlienci();
        else System.out.println("Niepoprawny wybor");
    }

    public static void importExcel(){
        System.out.println("1.Importuj pracownikow\n" +
                "2.Importuj klientow");
        int choice=wyborOpcji();
        if (choice==1) Excel.importPracownicy();
        else if(choice==2) Excel.importKlienci();
        else System.out.println("Niepoprawny wybor");
    }
}
