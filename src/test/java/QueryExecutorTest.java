import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class QueryExecutorTest {

    @Test
    public void testExecuteSelect_validQuery() {
        String selectQuery = "SELECT * FROM Klienci";
        ResultSet result = QueryExecutor.executeSelect(selectQuery);
        assertNotNull(result);
    }

    @Test
    public void testExecuteSelect_invalidQuery(){

        assertThrows(RuntimeException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                String selectQuery = "SELECT * FROM Stanowiska";
                ResultSet result = QueryExecutor.executeSelect(selectQuery);
            }
        });
    }

    @Test
    public void testExecuteSelect_nullQuery() {
        assertThrows(RuntimeException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                String selectQuery = null;
                ResultSet result = QueryExecutor.executeSelect(selectQuery);
                assertNull(result);
            }
        });
    }

    @Test
    public void testExecuteQuery_validInsert() throws SQLException {
        //Arrange
        String insertQuery = "INSERT INTO Klienci (imie,nazwisko,dataUrodzenia,dataDolaczenia,nrTelefonu,wplaty,username, haslo) VALUES ('Ola','Lenart','2001-11-22','2020-06-15','+48 000 000 000',0,'testuser', 'testhaslo')";

        //Act
        QueryExecutor.executeQuery(insertQuery);

        //Assert
        String selectQuery = "SELECT * FROM Klienci WHERE username = 'testuser' AND haslo = 'testhaslo'";
        ResultSet result = QueryExecutor.executeSelect(selectQuery);
        assertTrue(result.next());
    }

    @Test
    public void testExecuteQuery_invalidQuery() {
        assertThrows(RuntimeException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                String query = "INVALID SQL QUERY";
                QueryExecutor.executeQuery(query);
            }
        });
    }

    @Test
    public void testExecuteQuery_validUpdate() throws SQLException {
        String updateQuery = "UPDATE Klienci SET haslo = 'nowehaslo' WHERE username = 'user10'";
        String selectQuery = "SELECT haslo FROM Klienci WHERE username = 'user10'";

        QueryExecutor.executeQuery(updateQuery);
        ResultSet result = QueryExecutor.executeSelect(selectQuery);
        result.next();

        //Assert
        String password = result.getString("haslo");
        assertEquals("nowehaslo", password);
    }

    @Test
    public void testExecuteQuery_validDelete() throws SQLException {
        //Arrange
        String deleteQuery = "DELETE FROM Klienci WHERE idKlienta = 37";
        String selectQuery = "SELECT * FROM Klienci WHERE idKlienta = 37";

        //Act
        QueryExecutor.executeQuery(deleteQuery);
        ResultSet result = QueryExecutor.executeSelect(selectQuery);

        //Assert
        assertFalse(result.next());
    }
}