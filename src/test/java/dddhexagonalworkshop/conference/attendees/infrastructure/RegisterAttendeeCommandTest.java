package dddhexagonalworkshop.conference.attendees.infrastructure;

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
}
