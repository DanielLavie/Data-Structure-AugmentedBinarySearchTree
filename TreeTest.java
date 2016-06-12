import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TreeTest {
	Tree t;
	
	@Before
	public void setUp() throws Exception {
		t = new Tree();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGen() {
		assertEquals(null, t.find(5));
		t.insert(new TreeNode(5));
		assertNotEquals(null, t.find(5));
		
		t.insert(new TreeNode(1));
		t.insert(new TreeNode(3));
		t.insert(new TreeNode(2));
		t.insert(new TreeNode(4));
		t.insert(new TreeNode(6));
		t.insert(new TreeNode(13));
		t.insert(new TreeNode(14));
		t.insert(new TreeNode(21));
		t.insert(new TreeNode(-1));
		t.insert(new TreeNode(14));
		t.insert(new TreeNode(1));
		
		t.delete(t.find(5));
		assertEquals(null, t.find(5));
		assertNotEquals(null, t.find(1));
		t.delete(t.find(1));
		assertEquals(null, t.find(1));
		t.delete(t.find(1));
		assertEquals(null, t.find(1));
		
		assertNotEquals(null, t.find(-1));
		assertNotEquals(null, t.find(2));
		assertNotEquals(null, t.find(3));
		assertNotEquals(null, t.find(4));
		assertNotEquals(null, t.find(6));
		assertNotEquals(null, t.find(13));
		assertNotEquals(null, t.find(14));
		assertNotEquals(null, t.find(21));
		
		assertEquals(t.find(6), t.OSSelect(5));
		assertEquals(5, t.OSRank(t.find(6)));
		
		assertEquals(t.find(-1), t.OSSelect(1));
		assertEquals(1, t.OSRank(t.find(-1)));
		
		assertEquals(t.find(21), t.OSSelect(8));
		assertEquals(8, t.OSRank(t.find(21)));
		
		assertEquals(null, t.OSSelect(0));
		assertEquals(null, t.OSSelect(-1));
		assertEquals(null, t.OSSelect(9));
		
		t.insert(new TreeNode(-3));
		t.insert(new TreeNode(0));
		
		t.delete(t.find(3));
		t.delete(t.find(4));
		t.delete(t.find(2));
		
		assertEquals(0, t.find(-3).getHeight());
		assertEquals(1, t.find(-3).getSize());
		
		assertEquals(0, t.find(0).getHeight());
		assertEquals(1, t.find(0).getSize());
		
		assertEquals(1, t.find(-1).getHeight());
		assertEquals(3, t.find(-1).getSize());
		
		assertEquals(3, t.find(6).getHeight());
		assertEquals(7, t.find(6).getSize());
		
		assertEquals(2, t.find(13).getHeight());
		assertEquals(3, t.find(13).getSize());
		
		assertEquals(1, t.find(14).getHeight());
		assertEquals(2, t.find(14).getSize());
		
		assertEquals(0, t.find(21).getHeight());
		assertEquals(1, t.find(21).getSize());
	}
	
	@Test
	public void testNotBalanced() {
		t.insert(new TreeNode(1));
		t.insert(new TreeNode(2));
		t.insert(new TreeNode(3));
		t.insert(new TreeNode(4));
		t.insert(new TreeNode(5));
		t.insert(new TreeNode(6));
		t.insert(new TreeNode(7));
		
		assertFalse(t.isBalanced(t.find(1)));
	}
	
	@Test
	public void testBalanced() {
		t.insert(new TreeNode(2));
		t.insert(new TreeNode(1));
		t.insert(new TreeNode(3));
		t.insert(new TreeNode(4));
		
		assertTrue(t.isBalanced(t.find(2)));
	}

}
