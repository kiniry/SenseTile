// This file was generated by jmlunit on Wed Jan 09 22:25:34 EST 2008.

package org.jmlspecs.samples.prelimdesign;

/** Automatically-generated test driver for JML and JUnit based
 * testing of Point2D. The superclass of this class should be edited
 * to supply test data. However it's best not to edit this class
 * directly; instead use the command
 * <pre>
 *  jmlunit Point2D.java
 * </pre>
 * to regenerate this class whenever Point2D.java changes.
 */
public class Point2D_JML_Test
     extends Point2D_JML_TestData
{
    /** Initialize this class. */
    public Point2D_JML_Test(java.lang.String name) {
        super(name);
    }

    /** Run the tests. */
    public static void main(java.lang.String[] args) {
        org.jmlspecs.jmlunit.JMLTestRunner.run(suite());
        // You can also use a JUnit test runner such as:
        // junit.textui.TestRunner.run(suite());
    }

    /** Test to see if the code for class Point2D
     * has been compiled with runtime assertion checking (i.e., by jmlc).
     * Code that is not compiled with jmlc would not make an effective test,
     * since no assertion checking would be done. */
    public void test$IsRACCompiled() {
        junit.framework.Assert.assertTrue("code for class Point2D"
                + " was not compiled with jmlc"
                + " so no assertions will be checked!",
            org.jmlspecs.jmlrac.runtime.JMLChecker.isRACCompiled(Point2D.class)
            );
    }

    /** Return the test suite for this test class.  This will have
    * added to it at least test$IsRACCompiled(), and any test methods
    * written explicitly by the user in the superclass.  It will also
    * have added test suites for each testing each method and
    * constructor.
    */
    //@ ensures \result != null;
    public static junit.framework.Test suite() {
        Point2D_JML_Test testobj
            = new Point2D_JML_Test("Point2D_JML_Test");
        junit.framework.TestSuite testsuite = testobj.overallTestSuite();
        // Add instances of Test found by the reflection mechanism.
        testsuite.addTestSuite(Point2D_JML_Test.class);
        testobj.addTestSuiteForEachMethod(testsuite);
        return testsuite;
    }

    /** A JUnit test object that can run a single test method.  This
     * is defined as a nested class solely for convenience; it can't
     * be defined once and for all because it must subclass its
     * enclosing class.
     */
    protected static abstract class OneTest extends Point2D_JML_Test {

        /** Initialize this test object. */
        public OneTest(String name) {
            super(name);
        }

        /** The result object that holds information about testing. */
        protected junit.framework.TestResult result;

        //@ also
        //@ requires result != null;
        public void run(junit.framework.TestResult result) {
            this.result = result;
            super.run(result);
        }

        /* Run a single test and decide whether the test was
         * successful, meaningless, or a failure.  This is the
         * Template Method pattern abstraction of the inner loop in a
         * JML/JUnit test. */
        public void runTest() throws java.lang.Throwable {
            try {
                // The call being tested!
                doCall();
            }
            catch (org.jmlspecs.jmlrac.runtime.JMLEntryPreconditionError e) {
                // meaningless test input
                addMeaningless();
            } catch (org.jmlspecs.jmlrac.runtime.JMLAssertionError e) {
                // test failure
                int l = org.jmlspecs.jmlrac.runtime.JMLChecker.getLevel();
                org.jmlspecs.jmlrac.runtime.JMLChecker.setLevel
                    (org.jmlspecs.jmlrac.runtime.JMLOption.NONE);
                try {
                    java.lang.String failmsg = this.failMessage(e);
                    junit.framework.AssertionFailedError err
                        = new junit.framework.AssertionFailedError(failmsg);
                    err.setStackTrace(new java.lang.StackTraceElement[]{});
                    err.initCause(e);
                    result.addFailure(this, err);
                } finally {
                    org.jmlspecs.jmlrac.runtime.JMLChecker.setLevel(l);
                }
            } catch (java.lang.Throwable e) {
                // test success
            }
        }

        /** Call the method to be tested with the appropriate arguments. */
        protected abstract void doCall() throws java.lang.Throwable;

        /** Format the error message for a test failure, based on the
         * method's arguments. */
        protected abstract java.lang.String failMessage
            (org.jmlspecs.jmlrac.runtime.JMLAssertionError e);

        /** Inform listeners that a meaningless test was run. */
        private void addMeaningless() {
            if (result instanceof org.jmlspecs.jmlunit.JMLTestResult) {
                ((org.jmlspecs.jmlunit.JMLTestResult)result)
                    .addMeaningless(this);
            }
        }
    }

    /** Create the tests that are to be run for testing the class
     * Point2D.  The framework will then run them.
     * @param overallTestSuite$ The suite accumulating all of the tests
     * for this driver class.
     */
    //@ requires overallTestSuite$ != null;
    public void addTestSuiteForEachMethod
        (junit.framework.TestSuite overallTestSuite$)
    {
        try {
            this.addTestSuiteFor$TestPoint2D(overallTestSuite$);
        } catch (java.lang.Throwable ex) {
            overallTestSuite$.addTest
                (new org.jmlspecs.jmlunit.strategies.ConstructorFailed(ex));
        }
        try {
            this.addTestSuiteFor$TestPoint2D$1(overallTestSuite$);
        } catch (java.lang.Throwable ex) {
            overallTestSuite$.addTest
                (new org.jmlspecs.jmlunit.strategies.ConstructorFailed(ex));
        }
        try {
            this.addTestSuiteFor$TestGetX(overallTestSuite$);
        } catch (java.lang.Throwable ex) {
            overallTestSuite$.addTest
                (new org.jmlspecs.jmlunit.strategies.ConstructorFailed(ex));
        }
        try {
            this.addTestSuiteFor$TestGetY(overallTestSuite$);
        } catch (java.lang.Throwable ex) {
            overallTestSuite$.addTest
                (new org.jmlspecs.jmlunit.strategies.ConstructorFailed(ex));
        }
        try {
            this.addTestSuiteFor$TestMoveX(overallTestSuite$);
        } catch (java.lang.Throwable ex) {
            overallTestSuite$.addTest
                (new org.jmlspecs.jmlunit.strategies.ConstructorFailed(ex));
        }
        try {
            this.addTestSuiteFor$TestMoveY(overallTestSuite$);
        } catch (java.lang.Throwable ex) {
            overallTestSuite$.addTest
                (new org.jmlspecs.jmlunit.strategies.ConstructorFailed(ex));
        }
    }

    /** Add tests for the Point2D contructor
     * to the overall test suite. */
    private void addTestSuiteFor$TestPoint2D
        (junit.framework.TestSuite overallTestSuite$)
    {
        junit.framework.TestSuite methodTests$
            = this.emptyTestSuiteFor("Point2D");
        try {
            methodTests$.addTest
                (new TestPoint2D());
        } catch (org.jmlspecs.jmlunit.strategies.TestSuiteFullException e$) {
            // methodTests$ doesn't want more tests
        }
        overallTestSuite$.addTest(methodTests$);
    }

    /** Test for the Point2D contructor. */
    protected static class TestPoint2D extends OneTest {

        /** Initialize this instance. */
        public TestPoint2D() {
            super("Point2D");
        }

        protected void doCall() throws java.lang.Throwable {
            new Point2D();
        }

        protected java.lang.String failMessage
            (org.jmlspecs.jmlrac.runtime.JMLAssertionError e$)
        {
            java.lang.String msg = "\n\tContructor 'Point2D'";
            return msg;
        }
    }

    /** Add tests for the Point2D contructor
     * to the overall test suite. */
    private void addTestSuiteFor$TestPoint2D$1
        (junit.framework.TestSuite overallTestSuite$)
    {
        junit.framework.TestSuite methodTests$
            = this.emptyTestSuiteFor("Point2D");
        try {
            org.jmlspecs.jmlunit.strategies.DoubleIterator
                vdouble$1$iter
                = this.vdoubleIter("Point2D", 1);
            this.check_has_data
                (vdouble$1$iter,
                 "this.vdoubleIter(\"Point2D\", 1)");
            while (!vdouble$1$iter.atEnd()) {
                org.jmlspecs.jmlunit.strategies.DoubleIterator
                    vdouble$2$iter
                    = this.vdoubleIter("Point2D", 0);
                this.check_has_data
                    (vdouble$2$iter,
                     "this.vdoubleIter(\"Point2D\", 0)");
                while (!vdouble$2$iter.atEnd()) {
                    final double xc
                        = vdouble$1$iter.getDouble();
                    final double yc
                        = vdouble$2$iter.getDouble();
                    methodTests$.addTest
                        (new TestPoint2D$1(xc, yc));
                    vdouble$2$iter.advance();
                }
                vdouble$1$iter.advance();
            }
        } catch (org.jmlspecs.jmlunit.strategies.TestSuiteFullException e$) {
            // methodTests$ doesn't want more tests
        }
        overallTestSuite$.addTest(methodTests$);
    }

    /** Test for the Point2D contructor. */
    protected static class TestPoint2D$1 extends OneTest {
        /** Argument xc */
        private double xc;
        /** Argument yc */
        private double yc;

        /** Initialize this instance. */
        public TestPoint2D$1(double xc, double yc) {
            super("Point2D"+ ":" + xc+ "," +yc);
            this.xc = xc;
            this.yc = yc;
        }

        protected void doCall() throws java.lang.Throwable {
            new Point2D(xc, yc);
        }

        protected java.lang.String failMessage
            (org.jmlspecs.jmlrac.runtime.JMLAssertionError e$)
        {
            java.lang.String msg = "\n\tContructor 'Point2D' applied to";
            msg += "\n\tArgument xc: " + this.xc;
            msg += "\n\tArgument yc: " + this.yc;
            return msg;
        }
    }

    /** Add tests for the getX method
     * to the overall test suite. */
    private void addTestSuiteFor$TestGetX
        (junit.framework.TestSuite overallTestSuite$)
    {
        junit.framework.TestSuite methodTests$
            = this.emptyTestSuiteFor("getX");
        try {
            org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                receivers$iter
                = new org.jmlspecs.jmlunit.strategies.NonNullIteratorDecorator
                    (this.vorg_jmlspecs_samples_prelimdesign_Point2DIter("getX", 0));
            this.check_has_data
                (receivers$iter,
                 "new NonNullIteratorDecorator(this.vorg_jmlspecs_samples_prelimdesign_Point2DIter(\"getX\", 0))");
            while (!receivers$iter.atEnd()) {
                final org.jmlspecs.samples.prelimdesign.Point2D receiver$
                    = (org.jmlspecs.samples.prelimdesign.Point2D) receivers$iter.get();
                methodTests$.addTest
                    (new TestGetX(receiver$));
                receivers$iter.advance();
            }
        } catch (org.jmlspecs.jmlunit.strategies.TestSuiteFullException e$) {
            // methodTests$ doesn't want more tests
        }
        overallTestSuite$.addTest(methodTests$);
    }

    /** Test for the getX method. */
    protected static class TestGetX extends OneTest {
        /** The receiver */
        private org.jmlspecs.samples.prelimdesign.Point2D receiver$;

        /** Initialize this instance. */
        public TestGetX(org.jmlspecs.samples.prelimdesign.Point2D receiver$) {
            super("getX");
            this.receiver$ = receiver$;
        }

        protected void doCall() throws java.lang.Throwable {
            receiver$.getX();
        }

        protected java.lang.String failMessage
            (org.jmlspecs.jmlrac.runtime.JMLAssertionError e$)
        {
            java.lang.String msg = "\n\tMethod 'getX' applied to";
            msg += "\n\tReceiver: " + this.receiver$;
            return msg;
        }
    }

    /** Add tests for the getY method
     * to the overall test suite. */
    private void addTestSuiteFor$TestGetY
        (junit.framework.TestSuite overallTestSuite$)
    {
        junit.framework.TestSuite methodTests$
            = this.emptyTestSuiteFor("getY");
        try {
            org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                receivers$iter
                = new org.jmlspecs.jmlunit.strategies.NonNullIteratorDecorator
                    (this.vorg_jmlspecs_samples_prelimdesign_Point2DIter("getY", 0));
            this.check_has_data
                (receivers$iter,
                 "new NonNullIteratorDecorator(this.vorg_jmlspecs_samples_prelimdesign_Point2DIter(\"getY\", 0))");
            while (!receivers$iter.atEnd()) {
                final org.jmlspecs.samples.prelimdesign.Point2D receiver$
                    = (org.jmlspecs.samples.prelimdesign.Point2D) receivers$iter.get();
                methodTests$.addTest
                    (new TestGetY(receiver$));
                receivers$iter.advance();
            }
        } catch (org.jmlspecs.jmlunit.strategies.TestSuiteFullException e$) {
            // methodTests$ doesn't want more tests
        }
        overallTestSuite$.addTest(methodTests$);
    }

    /** Test for the getY method. */
    protected static class TestGetY extends OneTest {
        /** The receiver */
        private org.jmlspecs.samples.prelimdesign.Point2D receiver$;

        /** Initialize this instance. */
        public TestGetY(org.jmlspecs.samples.prelimdesign.Point2D receiver$) {
            super("getY");
            this.receiver$ = receiver$;
        }

        protected void doCall() throws java.lang.Throwable {
            receiver$.getY();
        }

        protected java.lang.String failMessage
            (org.jmlspecs.jmlrac.runtime.JMLAssertionError e$)
        {
            java.lang.String msg = "\n\tMethod 'getY' applied to";
            msg += "\n\tReceiver: " + this.receiver$;
            return msg;
        }
    }

    /** Add tests for the moveX method
     * to the overall test suite. */
    private void addTestSuiteFor$TestMoveX
        (junit.framework.TestSuite overallTestSuite$)
    {
        junit.framework.TestSuite methodTests$
            = this.emptyTestSuiteFor("moveX");
        try {
            org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                receivers$iter
                = new org.jmlspecs.jmlunit.strategies.NonNullIteratorDecorator
                    (this.vorg_jmlspecs_samples_prelimdesign_Point2DIter("moveX", 1));
            this.check_has_data
                (receivers$iter,
                 "new NonNullIteratorDecorator(this.vorg_jmlspecs_samples_prelimdesign_Point2DIter(\"moveX\", 1))");
            while (!receivers$iter.atEnd()) {
                org.jmlspecs.jmlunit.strategies.DoubleIterator
                    vdouble$1$iter
                    = this.vdoubleIter("moveX", 0);
                this.check_has_data
                    (vdouble$1$iter,
                     "this.vdoubleIter(\"moveX\", 0)");
                while (!vdouble$1$iter.atEnd()) {
                    final org.jmlspecs.samples.prelimdesign.Point2D receiver$
                        = (org.jmlspecs.samples.prelimdesign.Point2D) receivers$iter.get();
                    final double dx
                        = vdouble$1$iter.getDouble();
                    methodTests$.addTest
                        (new TestMoveX(receiver$, dx));
                    vdouble$1$iter.advance();
                }
                receivers$iter.advance();
            }
        } catch (org.jmlspecs.jmlunit.strategies.TestSuiteFullException e$) {
            // methodTests$ doesn't want more tests
        }
        overallTestSuite$.addTest(methodTests$);
    }

    /** Test for the moveX method. */
    protected static class TestMoveX extends OneTest {
        /** The receiver */
        private org.jmlspecs.samples.prelimdesign.Point2D receiver$;
        /** Argument dx */
        private double dx;

        /** Initialize this instance. */
        public TestMoveX(org.jmlspecs.samples.prelimdesign.Point2D receiver$, double dx) {
            super("moveX"+ ":" + dx);
            this.receiver$ = receiver$;
            this.dx = dx;
        }

        protected void doCall() throws java.lang.Throwable {
            receiver$.moveX(dx);
        }

        protected java.lang.String failMessage
            (org.jmlspecs.jmlrac.runtime.JMLAssertionError e$)
        {
            java.lang.String msg = "\n\tMethod 'moveX' applied to";
            msg += "\n\tReceiver: " + this.receiver$;
            msg += "\n\tArgument dx: " + this.dx;
            return msg;
        }
    }

    /** Add tests for the moveY method
     * to the overall test suite. */
    private void addTestSuiteFor$TestMoveY
        (junit.framework.TestSuite overallTestSuite$)
    {
        junit.framework.TestSuite methodTests$
            = this.emptyTestSuiteFor("moveY");
        try {
            org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                receivers$iter
                = new org.jmlspecs.jmlunit.strategies.NonNullIteratorDecorator
                    (this.vorg_jmlspecs_samples_prelimdesign_Point2DIter("moveY", 1));
            this.check_has_data
                (receivers$iter,
                 "new NonNullIteratorDecorator(this.vorg_jmlspecs_samples_prelimdesign_Point2DIter(\"moveY\", 1))");
            while (!receivers$iter.atEnd()) {
                org.jmlspecs.jmlunit.strategies.DoubleIterator
                    vdouble$1$iter
                    = this.vdoubleIter("moveY", 0);
                this.check_has_data
                    (vdouble$1$iter,
                     "this.vdoubleIter(\"moveY\", 0)");
                while (!vdouble$1$iter.atEnd()) {
                    final org.jmlspecs.samples.prelimdesign.Point2D receiver$
                        = (org.jmlspecs.samples.prelimdesign.Point2D) receivers$iter.get();
                    final double dy
                        = vdouble$1$iter.getDouble();
                    methodTests$.addTest
                        (new TestMoveY(receiver$, dy));
                    vdouble$1$iter.advance();
                }
                receivers$iter.advance();
            }
        } catch (org.jmlspecs.jmlunit.strategies.TestSuiteFullException e$) {
            // methodTests$ doesn't want more tests
        }
        overallTestSuite$.addTest(methodTests$);
    }

    /** Test for the moveY method. */
    protected static class TestMoveY extends OneTest {
        /** The receiver */
        private org.jmlspecs.samples.prelimdesign.Point2D receiver$;
        /** Argument dy */
        private double dy;

        /** Initialize this instance. */
        public TestMoveY(org.jmlspecs.samples.prelimdesign.Point2D receiver$, double dy) {
            super("moveY"+ ":" + dy);
            this.receiver$ = receiver$;
            this.dy = dy;
        }

        protected void doCall() throws java.lang.Throwable {
            receiver$.moveY(dy);
        }

        protected java.lang.String failMessage
            (org.jmlspecs.jmlrac.runtime.JMLAssertionError e$)
        {
            java.lang.String msg = "\n\tMethod 'moveY' applied to";
            msg += "\n\tReceiver: " + this.receiver$;
            msg += "\n\tArgument dy: " + this.dy;
            return msg;
        }
    }

    /** Check that the iterator is non-null and not empty. */
    private void
    check_has_data(org.jmlspecs.jmlunit.strategies.IndefiniteIterator iter,
                   String call)
    {
        if (iter == null) {
            junit.framework.Assert.fail(call + " returned null");
        }
        if (iter.atEnd()) {
            junit.framework.Assert.fail(call + " returned an empty iterator");
        }
    }

    /** Converts a char to a printable String for display */
    public static String charToString(char c) {
        if (c == '\n') {
            return "NL";
        } else if (c == '\r') {
            return "CR";
        } else if (c == '\t') {
            return "TAB";
        } else if (Character.isISOControl(c)) {
            int i = (int)c;
            return "\\u"
                    + Character.forDigit((i/2048)%16,16)
                    + Character.forDigit((i/256)%16,16)
                    + Character.forDigit((i/16)%16,16)
                    + Character.forDigit((i)%16,16);
        }
        return Character.toString(c);
    }
}
