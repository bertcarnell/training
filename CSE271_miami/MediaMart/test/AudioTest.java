import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.Serializable;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AudioTest {
    /**
     * Checks to ensure the parent of a given class is consistent with
     * expectations to validate OO design.
     * 
     * @param child      The child class whose parent is to be checked.
     * @param parentName The expected parent class.
     */
    private static void checkParent(Class<?> child, String parentName) {
        final String parName = child.getSuperclass().getSimpleName();
        assertEquals(parName, parentName);
    }
    
    /**
     * Helper method to create an Audio object for testing.
     * 
     * @param upc The music's upc
     * @param title The track's title
     * @param year The year for the music
     * @param rank The billboard ranking
     * @param isOpera Boolean flag to indicate if it is an opera
     * @param price The price
     * @return The newly created music object.
     */
    private Audio createMusic(String upc, String title, int year, int rank,
            boolean isOpera, float price) {
        // Create a video for testing
        Audio song = new Audio(upc, title, year);
        song.setBillboardRank(rank);
        song.setOperatic(isOpera);
        song.setPrice(price);
        return song;
    }
    
    /**
     * Helper method to check the properties of a music created for testing.
     * 
     * @praam song The song to be used for testing.
     * @param upc The song's upc to verify
     * @param name The songs' title to verify
     * @param year The year for the song to check
     * @param isOpera Flag to indicate if this song is operatic.
     * @param mpaa Its MPAA rating to be checked
     * @param price The price To be checked.
     */
    private void checkMusic(Audio song, String upc, String name, int year, 
            int rank, boolean isOpera, float price) {
        // Verify the getter methods are returning correct values.
        assertEquals(song.getUpc(), upc);
        assertEquals(song.getTitle(), name);
        assertEquals(song.getPrice(), price, 0.001f);
        assertEquals(song.getYear(), year);
        assertSame(song.isOperatic(), isOpera); 
    }

    @Test
    public void test1_checkHierarchy() {
        checkParent(Audio.class, "Media");
        checkParent(Media.class, "Object");
        if (!Serializable.class.isAssignableFrom(Audio.class)) {
            fail("Audio doesn't implement Serializable interface");
        }
        if (!Comparable.class.isAssignableFrom(Audio.class)) {
            fail("Audio doesn't implement Comparable<Audio> interface");
        }
    }

    /**
     * A JUnit test case to test the constructor and getter methods.
     */
    @Test
    public void test2_constructorsAndGetters() {
        Audio sng1 = createMusic("upc1", "test", 2022, 3, true, 1.75f);
        checkMusic(sng1, "upc1", "test", 2022, 3, true, 1.75f);
        
        // Create another video for testing
        Audio sng2 = createMusic("blah", "blah blah", 1955, 5, false, 0.99f);
        checkMusic(sng2, "blah", "blah blah", 1955, 5, false, 0.99f);
    }
    
    @Test
    public void test3_toString() {
        String expStr1 = "Audio\tupc3\tcheck\t1900\t1.75\t5\ttrue";
        Audio sng1 = createMusic("upc3", "check", 1900, 5, true, 1.75f);
        assertEquals(sng1.toString(), expStr1);
        
        // Use another example to test.
        String expStr2 = "Audio\ttest\tcheck to string\t2000\t2.25\t-1\tfalse";
        Audio sng2 = createMusic("test", "check to string", 2000, 
                -1, false, 2.25f);
        assertEquals(sng2.toString(), expStr2);
    }
    
    @Test
    public void test4_testLoad() {
        String str1 = "Audio\tupc3\tcheck\t1900\t1567\t4\ttrue";
        Audio sng1 = Audio.load(str1);
        checkMusic(sng1, "upc3", "check", 1900, 4, true, 1567f);

        // Use another example to test.
        String str2 = "Audio\ttest\tcheck to string\t2000\t0.0\t-1\tfalse";
        Audio sng2 = Audio.load(str2);
        checkMusic(sng2, "test", "check to string", 2000, -1, false, 0f);
    }
   
    @Test
    public void test5_testCompareTo() {
        Audio sng1 = createMusic("upc3", "check", 1900, 500, false, 1.75f);
        // Check equality
        assertTrue(sng1.compareTo(sng1) == 0);

        // Check comparison based on billboard rank
        Audio sng2 = createMusic("upc4", "check", 1900, 499, false, 1.75f);
        assertTrue(sng1.compareTo(sng2) > 0);
        assertTrue(sng2.compareTo(sng1) < 0);
        
        // Compare 2 videos with same ranking to see if price is used.
        Audio sng3 = createMusic("upc5", "check", 1900, 500, false, 1.5f);
        assertTrue(sng1.compareTo(sng3) > 0);
        assertTrue(sng3.compareTo(sng1) < 0);
        
        // Compare 2 videos with same ranking & price to see name is used.
        Audio sng4 = createMusic("upc6", "duck", 1900, 500, false, 1.75f);
        assertTrue(sng1.compareTo(sng4) < 0);
        assertTrue(sng4.compareTo(sng1) > 0);

        // Check equality
        assertTrue(sng3.compareTo(sng3) == 0);
        assertTrue(sng4.compareTo(sng4) == 0);
    }
}
