import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class MediaWarehouseTest {

	@Test
	void testFindMedia() throws ClassNotFoundException, IOException {
		MediaWarehouse mw = new MediaWarehouse();
		mw.addMediaFrom("90s_media.txt", true);
		Media test = mw.findMedia("m_90_1");
		
		assertEquals(1.99f, test.getPrice());
		
		test = mw.findMedia("dlsld");
		assertEquals(null, test);
	}

	@Test
	void testComputeTotalPrice() throws ClassNotFoundException, IOException {
		MediaWarehouse mw = new MediaWarehouse();
		mw.addMediaFrom("90s_media.txt", true);
		ArrayList<String> upcList = new ArrayList<String>();
		upcList.add("m_90_1");
		upcList.add("m_90_3");
		upcList.add("lo_92_1");
		upcList.add("lo_94_1");
		upcList.add("lo_95_1");
		upcList.add("lo_93_1");
		float test = mw.computeTotalPrice(upcList);
		
		assertTrue(Math.abs(7.95f - test) < 1E-6);
	}

}
