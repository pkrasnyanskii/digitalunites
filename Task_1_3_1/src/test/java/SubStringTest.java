import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.FileNotFoundException;

public class SubStringTest {
    @Test
    public void testMain() throws FileNotFoundException {
        int check = Main.main(new String[]{"input.txt", "lol"});
        assertEquals(-1, check);
    }
}
