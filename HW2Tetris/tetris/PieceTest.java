package tetris;

import static org.junit.Assert.*;
import java.util.*;

import org.junit.*;

/*
  Unit test for Piece class -- starter shell.
 */
public class PieceTest {
	// You can create data to be used in the your
	// test cases like this. For each run of a test method,
	// a new PieceTest object is created and setUp() is called
	// automatically by JUnit.
	// For example, the code below sets up some
	// pyramid and s pieces in instance variables
	// that can be used in tests.
	private Piece pyr1, pyr2, pyr3, pyr4;
	private Piece s, sRotated;
	
	private Piece[] pieces; //all pieces generated by Piece.getPieces()
	

	@Before
	public void setUp() throws Exception {
		
		pyr1 = new Piece(Piece.PYRAMID_STR);
		pyr2 = pyr1.computeNextRotation();
		pyr3 = pyr2.computeNextRotation();
		pyr4 = pyr3.computeNextRotation();
		
		s = new Piece(Piece.S1_STR);
		sRotated = s.computeNextRotation();
		
		
		
	}
	
	// Here are some sample tests to get you started
	
	@Test
	public void testSampleSize() {
		// Check size of pyr piece
		assertEquals(3, pyr1.getWidth());
		assertEquals(2, pyr1.getHeight());
		
		// Now try after rotation
		// Effectively we're testing size and rotation code here
		assertEquals(2, pyr2.getWidth());
		assertEquals(3, pyr2.getHeight());
		
		// Now try with some other piece, made a different way
		Piece l = new Piece(Piece.STICK_STR);
		assertEquals(1, l.getWidth());
		assertEquals(4, l.getHeight());
	}
	
	
	// Test the skirt returned by a few pieces
	@Test
	public void testSampleSkirt() {
		// Note must use assertTrue(Arrays.equals(... as plain .equals does not work
		// right for arrays.
		assertTrue(Arrays.equals(new int[] {0, 0, 0}, pyr1.getSkirt()));
		assertTrue(Arrays.equals(new int[] {1, 0, 1}, pyr3.getSkirt()));
		
		assertTrue(Arrays.equals(new int[] {0, 0, 1}, s.getSkirt()));
		assertTrue(Arrays.equals(new int[] {1, 0}, sRotated.getSkirt()));
	}
	
	// Test if equals work 
	@Test
	public void testEquals() {
		String string1 = "0 1	1 1  1 0  2 0";
		String string2 = "1 1  2 0  0 1  1 0";
		assertTrue(new Piece(string1).equals(new Piece(string2)));
		
		String string3 = "0 1	1 1  1 0  2 0";
		String string4 = "1 1  2 0  0 1  3 0";
		assertFalse(new Piece(string3).equals(new Piece(string4)));
		
		String string5 = "0 1	1 1  1 0  2 0";
		String string6 = "1 1  2 0  0 1";
		assertFalse(new Piece(string5).equals(new Piece(string6)));
		
		String string7 = "0 0	0 1  0 2  0 3";
		String string8 = "0 3  0 2  0 1  0 0";
		assertTrue(new Piece(string7).equals(new Piece(string8)));
		
		/*
		 * Test to check if full rotation of piece works right;
		 */
		Piece l = new Piece(Piece.STICK_STR);
		Piece lTurn1 = l.computeNextRotation();
		Piece lTurn2 = lTurn1.computeNextRotation();
		
		assertFalse(l.equals(lTurn1));		
		assertTrue(l.equals(lTurn2));
		
	}
	
	//test on how getPieces() works and fast rotation
	@Test 
	public void testGetPiecesAndFastRotation() {
		Piece[] pieces = Piece.getPieces(); 
		assertEquals("Size of pieces: " + pieces.length, 7, pieces.length);
		
		//fast rotation part
		//get random piece
		Random rd = new Random();
		Piece rdPiece = pieces[rd.nextInt(pieces.length)];
		
		//get fast rotated piece and slow rotated
		Piece fastRotated = rdPiece.fastRotation();
		Piece normalRotated = rdPiece.computeNextRotation();
		
		assertTrue(fastRotated.equals(normalRotated));
		
	}
	
	
	
}
