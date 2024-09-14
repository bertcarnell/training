import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MediaLibraryTest {

	@Test
	void testAddMediaFrom() throws Exception {
		MediaLibrary ml = new MediaLibrary();
		ml.addMediaFrom("media.txt");
		
		ml.addMedia(new Book("TitleA", 1000, "AuthorA", "PublisherA"));
		ml.addMedia(new Video("TitleB", 1001, "DirectorB", "DVD"));
		
		assertEquals(1001, ml.getMaxID());
		
		// throw for non unique id
		Assertions.assertThrows(Exception.class, ()->{
			ml.addMedia(new Video("", 1001, "", "DVD"));
		});
		// throw for bad type
		Assertions.assertThrows(Exception.class, ()->{
			ml.addMedia(new Video("", 1002, "", "blah"));
		});
		
		ml.deleteMedia(1000);
		ml.deleteMedia(1001);
		assertEquals(7, ml.getMaxID());
	}

	@Test
	void testWriteMedia() throws Exception {
		MediaLibrary ml = new MediaLibrary();
		ml.addMediaFrom("media.txt");
		
		ml.writeMedia("media_test_output.txt");
		
		MediaLibrary ml2 = new MediaLibrary();
		ml2.addMediaFrom("media_test_output.txt");
		
		for (int i = 0; i < ml2.getMediaList().size(); i++) {
			assertEquals(ml.getMediaList().get(i).getUniqueID(), ml2.getMediaList().get(i).getUniqueID());
		}
	}

}
