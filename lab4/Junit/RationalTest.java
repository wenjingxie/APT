import junit.framework.TestCase;

public class RationalTest extends TestCase {

    protected Rational HALF;

    protected void setUp() {
      HALF = new Rational( 1, 2 );
    }

    // Create new test
    public RationalTest (String name) {
        super(name);
    }

    public void testEquality() {
        assertEquals(new Rational(1,3), new Rational(1,3));
        assertEquals(new Rational(1,3), new Rational(2,6));
        assertEquals(new Rational(3,3), new Rational(1,1));
        assertEquals(new Rational(0,1), new Rational(0,-2));
        assertEquals(new Rational(-1,1), new Rational(2,-2));
        assertEquals(new Rational(-5,-3), new Rational(10,6));


    }

    // Test for nonequality
    public void testNonEquality() {
        assertFalse(new Rational(2,0).equals(null));
        assertFalse(new Rational(2,0).equals(2));  
        assertFalse(new Rational(2,3).equals(new Rational(1,3)));
        assertFalse(new Rational(2,0).equals(new Rational(1,0)));
  
    }

    public void testAccessors() {
    	assertEquals(new Rational(2,3).numerator(), 2);
    	assertEquals(new Rational(2,3).denominator(), 3);
    }

    public void testRoot() {
        Rational s = new Rational( 1, 4 );
        Rational sRoot = null;
        try {
            sRoot = s.root();
        } catch (IllegalArgumentToSquareRootException e) {
            e.printStackTrace();
        }
        assertTrue( sRoot.isLessThan( HALF.plus( Rational.getTolerance() ) ) 
                        && HALF.minus( Rational.getTolerance() ).isLessThan( sRoot ) );



    }

    public void testRootArgument() {
        Rational s = new Rational(-1, 4);
        Rational sRoot = null;
        try {
            sRoot = s.root();
        } catch (IllegalArgumentToSquareRootException e) {
            e.printStackTrace();
        }
    }

    public void testPlus() {
        assertEquals(new Rational(0,1), new Rational(-1,-2).plus(new Rational(2,4)));
        assertEquals(new Rational(1,1), new Rational(1,2).plus(new Rational(2,4)));
        assertEquals(new Rational(0,1), new Rational(3,7).plus(new Rational(-9,21)));
        assertEquals(new Rational(-6,3), new Rational(-6,4).plus(new Rational(-1,2)));
        assertFalse(new Rational(2,0).equals(new Rational(1,0).plus(new Rational(0,1))));
        // invoke / by zero error
        //assertEquals(new Rational(0,3), new Rational(0,4).plus(new Rational(0,2)));

    }

    public void testTimes() {
        assertEquals(new Rational(0,3), new Rational(0,4).times(new Rational(0,2)));
        assertEquals(new Rational(0,3), new Rational(0,4).times(new Rational(3,2)));
        assertEquals(new Rational(1,3), new Rational(3,-1).times(new Rational(-2,18)));
        assertEquals(new Rational(-1,3), new Rational(3,1).times(new Rational(-2,18)));
        assertFalse(new Rational(2,0).equals(new Rational(1,0).times(new Rational(1,0))));
    }

    public void testMinus() {
        assertEquals(new Rational(1,1), new Rational(2,2).minus(new Rational(0,4)));
        assertEquals(new Rational(0,1), new Rational(3,7).minus(new Rational(-6,-14)));
        assertEquals(new Rational(-3,3), new Rational(-6,4).minus(new Rational(-1,2)));
        assertFalse(new Rational(2,0).equals(new Rational(1,0).minus(new Rational(0,1))));
        // invoke / by zero error
        //assertEquals(new Rational(0,3), new Rational(0,4).minus(new Rational(0,2)));
    }

    public void testDivides() {
        assertEquals(new Rational(0,3), new Rational(0,4).divides(new Rational(1,2)));
        assertEquals(new Rational(1,3), new Rational(1,4).divides(new Rational(-9,-12)));
        assertEquals(new Rational(27,1), new Rational(3,-1).divides(new Rational(-2,18)));
        assertEquals(new Rational(-27,1), new Rational(3,1).divides(new Rational(-2,18)));
        assertFalse(new Rational(2,0).equals(new Rational(1,0).divides(new Rational(0,1))));  
    }

    public void testLessThan() {
        assertTrue(new Rational(2,1).isLessThan(new Rational(4,1)));
        assertTrue(new Rational(-2,1).isLessThan(new Rational(0,1)));
        assertTrue(new Rational(-2,1).isLessThan(new Rational(4,-3)));
        assertTrue(new Rational(0,1).isLessThan(new Rational(4,1)));
        assertFalse(new Rational(0,1).isLessThan(new Rational(-4,1)));
        assertFalse(new Rational(2,1).isLessThan(new Rational(0,1)));
        assertFalse(new Rational(0,1).isLessThan(new Rational(4,1)));
    }


    public void testAbs() {
        assertTrue(new Rational(2,1).equals(new Rational(4,-2).abs())); 
        assertTrue(new Rational(0,1).equals(new Rational(0,-2).abs())); 
        assertTrue(new Rational(1,1).equals(new Rational(2,2).abs())); 
        assertTrue(new Rational(2,1).equals(new Rational(-4,-2).abs())); 
        assertFalse(new Rational(2,0).equals(new Rational(1,0).abs()));  
    
    }

    public void testTolerance() {
        Rational s = new Rational(1, 4);
        s.setTolerance(new Rational(1, 1000));
        assertEquals(new Rational(1,1000), s.getTolerance());       
    }

    public static void main(String args[]) {
        String[] testCaseName = 
            { RationalTest.class.getName() };
        // junit.swingui.TestRunner.main(testCaseName);
        junit.textui.TestRunner.main(testCaseName);
    }
}
