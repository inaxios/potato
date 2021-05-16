package bikini.potato.stars;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainStarsTest {

    @Test
    public void testCalculateHowManyWeNeed() {
        assertEquals(1, MainStars.calculateHowManyWeNeed(0));
        assertEquals(2, MainStars.calculateHowManyWeNeed(1));
        assertEquals(32, MainStars.calculateHowManyWeNeed(5));
    }

    @Test
    public void testGetStars() {
        assertEquals("*", MainStars.getStars(0));
        assertEquals("****", MainStars.getStars(2));
        assertEquals(32, MainStars.getStars(5).length());
    }
}
