// This file was generated by jmlunit on Wed Jan 09 22:24:58 EST 2008.

package org.jmlspecs.samples.digraph;

import org.jmlspecs.models.*;

/** Automatically-generated test driver for JML and JUnit based
 * testing of NodeType. The superclass of this class should be edited
 * to supply test data. However it's best not to edit this class
 * directly; instead use the command
 * <pre>
 *  jmlunit NodeType.java
 * </pre>
 * to regenerate this class whenever NodeType.java changes.
 */
public class NodeType_JML_Test
     extends NodeType_JML_TestData
{
    /** Initialize this class. */
    public NodeType_JML_Test(java.lang.String name) {
        super(name);
    }

    /** Run the tests. */
    public static void main(java.lang.String[] args) {
        org.jmlspecs.jmlunit.JMLTestRunner.run(suite());
        // You can also use a JUnit test runner such as:
        // junit.textui.TestRunner.run(suite());
    }

    /** Test to see if the code for interface NodeType
     * has been compiled with runtime assertion checking (i.e., by jmlc).
     * Code that is not compiled with jmlc would not make an effective test,
     * since no assertion checking would be done. */
    public void test$IsRACCompiled() {
        junit.framework.Assert.assertTrue("code for interface NodeType"
                + " was not compiled with jmlc"
                + " so no assertions will be checked!",
            org.jmlspecs.jmlrac.runtime.JMLChecker.isRACCompiled(NodeType.class)
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
        NodeType_JML_Test testobj
            = new NodeType_JML_Test("NodeType_JML_Test");
        junit.framework.TestSuite testsuite = testobj.overallTestSuite();
        // Add instances of Test found by the reflection mechanism.
        testsuite.addTestSuite(NodeType_JML_Test.class);
        testobj.addTestSuiteForEachMethod(testsuite);
        return testsuite;
    }

    /** A JUnit test object that can run a single test method.  This
     * is defined as a nested class solely for convenience; it can't
     * be defined once and for all because it must subclass its
     * enclosing class.
     */
    protected static abstract class OneTest extends NodeType_JML_Test {

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
     * NodeType.  The framework will then run them.
     * @param overallTestSuite$ The suite accumulating all of the tests
     * for this driver class.
     */
    //@ requires overallTestSuite$ != null;
    public void addTestSuiteForEachMethod
        (junit.framework.TestSuite overallTestSuite$)
    {
        try {
            this.addTestSuiteFor$TestEquals(overallTestSuite$);
        } catch (java.lang.Throwable ex) {
            overallTestSuite$.addTest
                (new org.jmlspecs.jmlunit.strategies.ConstructorFailed(ex));
        }
        try {
            this.addTestSuiteFor$TestHashCode(overallTestSuite$);
        } catch (java.lang.Throwable ex) {
            overallTestSuite$.addTest
                (new org.jmlspecs.jmlunit.strategies.ConstructorFailed(ex));
        }
        try {
            this.addTestSuiteFor$TestClone(overallTestSuite$);
        } catch (java.lang.Throwable ex) {
            overallTestSuite$.addTest
                (new org.jmlspecs.jmlunit.strategies.ConstructorFailed(ex));
        }
    }

    /** Add tests for the equals method
     * to the overall test suite. */
    private void addTestSuiteFor$TestEquals
        (junit.framework.TestSuite overallTestSuite$)
    {
        junit.framework.TestSuite methodTests$
            = this.emptyTestSuiteFor("equals");
        try {
            org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                receivers$iter
                = new org.jmlspecs.jmlunit.strategies.NonNullIteratorDecorator
                    (this.vorg_jmlspecs_samples_digraph_NodeTypeIter("equals", 1));
            this.check_has_data
                (receivers$iter,
                 "new NonNullIteratorDecorator(this.vorg_jmlspecs_samples_digraph_NodeTypeIter(\"equals\", 1))");
            while (!receivers$iter.atEnd()) {
                org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                    vjava_lang_Object$1$iter
                    = this.vjava_lang_ObjectIter("equals", 0);
                this.check_has_data
                    (vjava_lang_Object$1$iter,
                     "this.vjava_lang_ObjectIter(\"equals\", 0)");
                while (!vjava_lang_Object$1$iter.atEnd()) {
                    final org.jmlspecs.samples.digraph.NodeType receiver$
                        = (org.jmlspecs.samples.digraph.NodeType) receivers$iter.get();
                    final java.lang.Object o
                        = (java.lang.Object) vjava_lang_Object$1$iter.get();
                    methodTests$.addTest
                        (new TestEquals(receiver$, o));
                    vjava_lang_Object$1$iter.advance();
                }
                receivers$iter.advance();
            }
        } catch (org.jmlspecs.jmlunit.strategies.TestSuiteFullException e$) {
            // methodTests$ doesn't want more tests
        }
        overallTestSuite$.addTest(methodTests$);
    }

    /** Test for the equals method. */
    protected static class TestEquals extends OneTest {
        /** The receiver */
        private org.jmlspecs.samples.digraph.NodeType receiver$;
        /** Argument o */
        private java.lang.Object o;

        /** Initialize this instance. */
        public TestEquals(org.jmlspecs.samples.digraph.NodeType receiver$, java.lang.Object o) {
            super("equals"+ ":" + (o==null? "null" :"{java.lang.Object}"));
            this.receiver$ = receiver$;
            this.o = o;
        }

        protected void doCall() throws java.lang.Throwable {
            receiver$.equals(o);
        }

        protected java.lang.String failMessage
            (org.jmlspecs.jmlrac.runtime.JMLAssertionError e$)
        {
            java.lang.String msg = "\n\tMethod 'equals' applied to";
            msg += "\n\tReceiver: " + this.receiver$;
            msg += "\n\tArgument o: " + this.o;
            return msg;
        }
    }

    /** Add tests for the hashCode method
     * to the overall test suite. */
    private void addTestSuiteFor$TestHashCode
        (junit.framework.TestSuite overallTestSuite$)
    {
        junit.framework.TestSuite methodTests$
            = this.emptyTestSuiteFor("hashCode");
        try {
            org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                receivers$iter
                = new org.jmlspecs.jmlunit.strategies.NonNullIteratorDecorator
                    (this.vorg_jmlspecs_samples_digraph_NodeTypeIter("hashCode", 0));
            this.check_has_data
                (receivers$iter,
                 "new NonNullIteratorDecorator(this.vorg_jmlspecs_samples_digraph_NodeTypeIter(\"hashCode\", 0))");
            while (!receivers$iter.atEnd()) {
                final org.jmlspecs.samples.digraph.NodeType receiver$
                    = (org.jmlspecs.samples.digraph.NodeType) receivers$iter.get();
                methodTests$.addTest
                    (new TestHashCode(receiver$));
                receivers$iter.advance();
            }
        } catch (org.jmlspecs.jmlunit.strategies.TestSuiteFullException e$) {
            // methodTests$ doesn't want more tests
        }
        overallTestSuite$.addTest(methodTests$);
    }

    /** Test for the hashCode method. */
    protected static class TestHashCode extends OneTest {
        /** The receiver */
        private org.jmlspecs.samples.digraph.NodeType receiver$;

        /** Initialize this instance. */
        public TestHashCode(org.jmlspecs.samples.digraph.NodeType receiver$) {
            super("hashCode");
            this.receiver$ = receiver$;
        }

        protected void doCall() throws java.lang.Throwable {
            receiver$.hashCode();
        }

        protected java.lang.String failMessage
            (org.jmlspecs.jmlrac.runtime.JMLAssertionError e$)
        {
            java.lang.String msg = "\n\tMethod 'hashCode' applied to";
            msg += "\n\tReceiver: " + this.receiver$;
            return msg;
        }
    }

    /** Add tests for the clone method
     * to the overall test suite. */
    private void addTestSuiteFor$TestClone
        (junit.framework.TestSuite overallTestSuite$)
    {
        junit.framework.TestSuite methodTests$
            = this.emptyTestSuiteFor("clone");
        try {
            org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                receivers$iter
                = new org.jmlspecs.jmlunit.strategies.NonNullIteratorDecorator
                    (this.vorg_jmlspecs_samples_digraph_NodeTypeIter("clone", 0));
            this.check_has_data
                (receivers$iter,
                 "new NonNullIteratorDecorator(this.vorg_jmlspecs_samples_digraph_NodeTypeIter(\"clone\", 0))");
            while (!receivers$iter.atEnd()) {
                final org.jmlspecs.samples.digraph.NodeType receiver$
                    = (org.jmlspecs.samples.digraph.NodeType) receivers$iter.get();
                methodTests$.addTest
                    (new TestClone(receiver$));
                receivers$iter.advance();
            }
        } catch (org.jmlspecs.jmlunit.strategies.TestSuiteFullException e$) {
            // methodTests$ doesn't want more tests
        }
        overallTestSuite$.addTest(methodTests$);
    }

    /** Test for the clone method. */
    protected static class TestClone extends OneTest {
        /** The receiver */
        private org.jmlspecs.samples.digraph.NodeType receiver$;

        /** Initialize this instance. */
        public TestClone(org.jmlspecs.samples.digraph.NodeType receiver$) {
            super("clone");
            this.receiver$ = receiver$;
        }

        protected void doCall() throws java.lang.Throwable {
            receiver$.clone();
        }

        protected java.lang.String failMessage
            (org.jmlspecs.jmlrac.runtime.JMLAssertionError e$)
        {
            java.lang.String msg = "\n\tMethod 'clone' applied to";
            msg += "\n\tReceiver: " + this.receiver$;
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