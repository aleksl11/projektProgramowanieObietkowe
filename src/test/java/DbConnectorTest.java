import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class DbConnectorTest {

    @Test
    public void testConnect() {
        //Act
        Connection connection = DbConnector.connect();

        //Assert
        assertNotNull(connection);
    }
}