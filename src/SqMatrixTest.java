import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class SqMatrixTest {

	@Test
	void testConstructScale() {
		SqMatrix m = new SqMatrix(2);
		assertEquals(m.toString(), "[INF,\tINF]\n[INF,\tINF]\n");
		m.scale_up();
		assertEquals(m.toString(), "[INF,\tINF,\tINF]\n[INF,\tINF,\tINF]\n[INF,\tINF,\tINF]\n");
		
	}
	
	@Test
	void testSetGet() {
		SqMatrix m = new SqMatrix(2);
		m.set(1, 2, 3f);
		m.set(2, 1, 2f);
		assertEquals(m.toString(), "[INF,\t3.0]\n[2.0,\tINF]\n");
		m.scale_up();
		assertEquals(m.toString(), "[INF,\t3.0,\tINF]\n[2.0,\tINF,\tINF]\n[INF,\tINF,\tINF]\n");
		assertEquals(m.get(2,1), 2f);
	}
	
	@Test
	void testMaxArray() {
		SqMatrix m = new SqMatrix(2);
		m.set(1, 2, 3f);
		m.set(2, 1, 2f);
		m.set(1, 1, 1f);
		assertEquals(m.maxArray().get(0), 2f);
		assertEquals(m.argmin(), 1);
	}

}
