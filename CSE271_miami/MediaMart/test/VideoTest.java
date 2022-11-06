import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.Serializable;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VideoTest {
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
     * Helper method to create a video for testing.
     * 
     * @param upc The video's upc
     * @param name The video's title
     * @param year The year for the video
     * @param hdcp Is it it protected by hdcp
     * @param mpaa Its MPAA rating
     * @param price The price
     * @return The newly created video object.
     */
    private Video createVideo(String upc, String name, int year, boolean hdcp,
            String mpaa, float price) {
        // Create a video for testing
        Video vid1 = new Video(upc, name, year);
        vid1.setHdcp(hdcp);
        vid1.setMpaa(mpaa);
        vid1.setPrice(price);
        return vid1;
    }
    
    /**
     * Helper method to check the properties of a video creted for testing.
     * 
     * @praam vid The video to be used for testing.
     * @param upc The video's upc to verify
     * @param name The video's title to verify
     * @param year The year for the video to check
     * @param hdcp Is it it protected by hdcp to check
     * @param mpaa Its MPAA rating to be checked
     * @param price The price To be checked.
     */
    private void checkVideo(Video vid, String upc, String name, int year, 
            boolean hdcp, String mpaa, float price) {
        // Verify the getter methods are returning correct values.
        assertEquals(vid.getUpc(), upc);
        assertEquals(vid.getTitle(), name);
        assertEquals(vid.getMpaa(), mpaa);
        assertEquals(vid.getPrice(), price, 0.001f);
        assertEquals(vid.isHdcp(), hdcp); 
        assertEquals(vid.getYear(), year);
    }

    @Test
    public void test1_checkHierarchy() {
        checkParent(Video.class, "Media");
        checkParent(Media.class, "Object");
        if (!Serializable.class.isAssignableFrom(Video.class)) {
            fail("Video doesn't implement Serializable interface");
        }
        if (!Comparable.class.isAssignableFrom(Video.class)) {
            fail("Video doesn't implement Comparable<Video> interface");
        }
    }

    /**
     * A JUnit test case to test the constructor and getter methods.
     */
    @Test
    public void test2_constructorsAndGetters() {
        Video vid1 = createVideo("upc1", "test", 2022, true, "PG", 1.75f);
        checkVideo(vid1, "upc1", "test", 2022, true, "PG", 1.75f);
        
        // Create another video for testing
        Video vid2 = createVideo("blah", "blah blah", 1955, false, "R", 0.99f);
        checkVideo(vid2, "blah", "blah blah", 1955, false, "R", 0.99f);
    }
    
    @Test
    public void test3_toString() {
        String expStr1 = "Video\tupc3\tcheck\t1900\t1.75\tPG13\ttrue";
        Video vid1 = createVideo("upc3", "check", 1900, true, "PG13", 1.75f);
        assertEquals(vid1.toString(), expStr1);
        
        // Use another example to test.
        String expStr2 = "Video\ttest\tcheck to string\t2000\t2.25\tG\tfalse";
        Video vid2 = createVideo("test", "check to string", 2000, false, 
                "G", 2.25f);
        assertEquals(vid2.toString(), expStr2);
    }
    
    @Test
    public void test4_testLoad() {
        String str1 = "Video\tupc3\tcheck\t1900\t1.75\tPG13\ttrue";
        Video vid1 = Video.load(str1);
        checkVideo(vid1, "upc3", "check", 1900, true, "PG13", 1.75f);

        // Use another example to test.
        String str2 = "Video\ttest\tcheck to string\t2000\t2.25\tG\tfalse";
        Video  vid2 = Video.load(str2);
        checkVideo(vid2, "test", "check to string", 2000, false, 
                "G", 2.25f);
    }
   
    @Test
    public void test5_testCompareTo() {
        Video vid1 = createVideo("upc3", "check", 1900, true, "PG13", 1.75f);
        Video vid2 = createVideo("upc3", "check", 1900, true, "R", 1.75f);
        assertTrue(vid1.compareTo(vid2) < 0);
        assertTrue(vid2.compareTo(vid1) > 0);
        
        // Compare 2 videos with same mpaa rating to see if price is used.
        Video vid3 = createVideo("upc3", "check", 1900, true, "PG13", 1.5f);
        assertTrue(vid1.compareTo(vid3) > 0);
        assertTrue(vid3.compareTo(vid1) < 0);
        
        // Compare 2 videos with same mpaa rating & price to see name is used.
        Video vid4 = createVideo("upc3", "duck", 1900, true, "PG13", 1.75f);
        assertTrue(vid1.compareTo(vid4) < 0);
        assertTrue(vid4.compareTo(vid1) > 0);

        // Check equality
        assertTrue(vid1.compareTo(vid1) == 0);
        assertTrue(vid4.compareTo(vid4) == 0);
    }
}
