import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayInputStream;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.Class;

class BattleGameTest {
	Class<BattleGrid> cls;
	private Constructor<BattleGrid> ctor;
	Object[] argarray;
	final String[] initializeArgs = {"12", "7", "5"};
	
	@BeforeEach
	void setup() throws NoSuchMethodException, SecurityException {
		cls = BattleGrid.class;
		ctor = cls.getDeclaredConstructor(String[].class);
	    ctor.setAccessible(true);
	    // create an argument array to fill with the arguments (the only argument is a String[])
	    argarray = new Object[1];
	    argarray[0] = initializeArgs;
	}
	
	@Test
	void testProcessLine() {
		BattleGame.reset();
		// () -> BattleGame.processLine("r1") means create a lambda function and pass it no parameters ()
		Assertions.assertDoesNotThrow(() -> BattleGame.processLine("r1"));
		Assertions.assertEquals(1, BattleGame.getGuessRow());
		Assertions.assertEquals(0,  BattleGame.getGuessCol()); // default

		Assertions.assertDoesNotThrow(() -> BattleGame.processLine("c123"));
		Assertions.assertEquals(1, BattleGame.getGuessRow()); // from the last operation
		Assertions.assertEquals(123,  BattleGame.getGuessCol());

		Assertions.assertDoesNotThrow(() -> BattleGame.processLine("r7 c23"));
		Assertions.assertEquals(7, BattleGame.getGuessRow());
		Assertions.assertEquals(23,  BattleGame.getGuessCol());

		Assertions.assertDoesNotThrow(() -> BattleGame.processLine("c678 r456"));
		Assertions.assertEquals(456, BattleGame.getGuessRow());
		Assertions.assertEquals(678,  BattleGame.getGuessCol());
	}
	
	@Test
	void testPlay() throws NoSuchMethodException, 
							java.lang.reflect.InvocationTargetException,
							IllegalAccessException,
	                        InstantiationException {
	    BattleGrid grid = ctor.newInstance(argarray);
	    BattleGame.reset();
	    
	    // quit should quit the game
        ByteArrayInputStream testIn = new ByteArrayInputStream("quit".getBytes());
	    System.setIn(testIn);
	    BattleGame.play(grid);
	    Assertions.assertEquals(0, BattleGame.getGuessCol());
	    Assertions.assertEquals(0,  BattleGame.getGuessRow());
	    
	    System.out.println("\n\n");
	    
	    // grid should print the empty grid, then quit should quit the game
	    System.setIn(new ByteArrayInputStream("grid\nquit".getBytes()));
	    BattleGame.play(grid);

	    Assertions.assertEquals(0, BattleGame.getGuessCol());
	    Assertions.assertEquals(0,  BattleGame.getGuessRow());

	    System.out.println("\n\n");
	    
	    // quit should print one X
	    System.setIn(new ByteArrayInputStream("r1 c1\ngrid\nquit".getBytes()));
	    BattleGame.play(grid);

	    Assertions.assertEquals(1, BattleGame.getGuessCol());
	    Assertions.assertEquals(1,  BattleGame.getGuessRow());
	}
	
	@Test
	void testProcessGuess() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		BattleGrid grid = ctor.newInstance(argarray);
		BattleGame.reset();
		BattleGame.find_columns(grid);
		
		BattleGame.setGuessCol(5);
		BattleGame.setGuessRow(5);
		Assertions.assertFalse(BattleGame.processGuess(grid));

		BattleGame.setGuessCol(15);
		BattleGame.setGuessRow(20);
		Assertions.assertFalse(BattleGame.processGuess(grid));

		BattleGame.setGuessCol(5);
		BattleGame.setGuessRow(7);
		Assertions.assertTrue(BattleGame.processGuess(grid));
	}
	
	@Test
	void testFindColumns() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		BattleGrid grid = ctor.newInstance(argarray);
		BattleGame.reset();
		BattleGame.find_columns(grid);
		
		Assertions.assertEquals(12, BattleGame.getGridSize());
	}
	
}
