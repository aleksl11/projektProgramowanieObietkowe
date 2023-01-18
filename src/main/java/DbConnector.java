import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
    private static String dbUrl="jdbc:mysql://localhost:3306/silownia";
    private static String username="root";
    private static String password="";

    public static Connection connect(){
        Connection connection=null;
        try{
            connection = DriverManager.getConnection(dbUrl,username,password);

        }catch(SQLException e){
            System.out.println("Brak polaczenia z baza");
        }
        return connection;
    }
}
