import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


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