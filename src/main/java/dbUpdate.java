public class dbUpdate extends QueryExecutor{
    public static void updateKlient(Klient klient){
        String update = "update klienci set username='"+klient.nazwaUzytkownika+"' where idKlienta="+klient.id;
        executeQuery(update);
        update = "update klienci set haslo='"+klient.haslo+"' where idKlienta="+klient.id;
        executeQuery(update);
    }
    public static void updateGrupa(Klient klient){
        String update = "update klienci set idGrupy="+klient.idGr+" where idKlienta="+klient.id;
        executeQuery(update);
        update = "update klienci set wplaty="+klient.wplaty+" where idKlienta="+klient.id;
        executeQuery(update);
    }

    public static void updateKarnet(Klient klient){
        String update = "update klienci set dlKarnetu="+klient.dlKarnetu+" where idKlienta="+klient.id;
        executeQuery(update);
        update = "update klienci set wplaty="+klient.wplaty+" where idKlienta="+klient.id;
        executeQuery(update);
    }
}
