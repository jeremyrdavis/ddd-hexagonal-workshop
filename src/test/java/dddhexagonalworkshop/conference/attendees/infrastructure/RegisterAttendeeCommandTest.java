package dddhexagonalworkshop.conference.attendees.infrastructure;

import dddhexagonalworkshop.conference.attendees.domain.valueobjects.Name;
import dddhexagonalworkshop.conference.attendees.domain.valueobjects.TShirtSize;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterAttendeeCommandTest {

    @Test
    public void testTShirtSize() {
        try{
            TShirtSize tShirtSize = new TShirtSize("LARGE");
            assertNotNull(tShirtSize);
            assertEquals(TShirtSize.LARGE, tShirtSize);
        }catch (Exception e){
            assertNull(e);
        }
    }

    @Test
    public void testFullName() {
        try{
            Name sam = new Name("Samwise", "Gamgee");
            assertNotNull(sam);
            assertEquals("Samwise Gamgee", sam.getFullName());

            Name edwin = new Name("Edwin", "van der Saar");
            assertNotNull(edwin);
            assertEquals("Edwin van der Saar", edwin.getFullName());
        }catch (Exception e){
            assertNull(e);
        }
    }
}
