import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

class OpcjeTest {
    @Test
    public void testWyborOpcji_validInput() {
        String input = "1\n";
        System.setIn(new java.io.ByteArrayInputStream(input.getBytes()));
        int result = Opcje.wyborOpcji();
        assertEquals(1, result);
    }

    @Test
    public void testWyborOpcji_invalidInput() {
        String input = "abc\n1\n";
        System.setIn(new java.io.ByteArrayInputStream(input.getBytes()));
        int result = Opcje.wyborOpcji();
        assertEquals(1, result);
    }

}