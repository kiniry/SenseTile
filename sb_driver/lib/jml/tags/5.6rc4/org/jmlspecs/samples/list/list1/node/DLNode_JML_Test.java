// This file was generated by jmlunit on Mon Mar 16 13:16:33 EDT 2009.

package org.jmlspecs.samples.list.list1.node;

/** Automatically-generated test driver for JML and JUnit based
 * testing of DLNode. The superclass of this class should be edited
 * to supply test data. However it's best not to edit this class
 * directly; instead use the command
 * <pre>
 *  jmlunit DLNode.java
 * </pre>
 * to regenerate this class whenever DLNode.java changes.
 */
public class DLNode_JML_Test
     extends DLNode_JML_TestData
{
    /** Initialize this class. */
    public DLNode_JML_Test(java.lang.String name) {
        super(name);
    }

    /** Run the tests. */
    public static void main(java.lang.String[] args) {
        org.jmlspecs.jmlunit.JMLTestRunner.run(suite());
        // You can also use a JUnit test runner such as:
        // junit.textui.TestRunner.run(suite());
    }

    /** Test to see if the code for class DLNode
     * has been compiled with runtime assertion checking (i.e., by jmlc).
     * Code that is not compiled with jmlc would not make an effective test,
     * since no assertion checking would be done. */
    public void test$IsRACCompiled() {
        junit.framework.Assert.assertTrue("code for class DLNode"
                + " was not compiled with jmlc"
                + " so no assertions will be checked!",
            org.jmlspecs.jmlrac.runtime.JMLChecker.isRACCompiled(DLNode.class)
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
        DLNode_JML_Test testobj
            = new DLNode_JML_Test("DLNode_JML_Test");
        junit.framework.TestSuite testsuite = testobj.overallTestSuite();
        // Add instances of Test found by the reflection mechanism.
        testsuite.addTestSuite(DLNode_JML_Test.class);
        testobj.addTestSuiteForEachMethod(testsuite);
        return testsuite;
    }

    /** A JUnit test object that can run a single test method.  This
     * is defined as a nested class solely for convenience; it can't
     * be defined once and for all because it must subclass its
     * enclosing class.
     */
    protected static abstract class OneTest extends DLNode_JML_Test {

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
     * DLNode.  The framework will then run them.
     * @param overallTestSuite$ The suite accumulating all of the tests
     * for this driver class.
     */
    //@ requires overallTestSuite$ != null;
    public void addTestSuiteForEachMethod
        (junit.framework.TestSuite overallTestSuite$)
    {
        try {
            this.addTestSuiteFor$TestDLNode(overallTestSuite$);
        } catch (java.lang.Throwable ex) {
            overallTestSuite$.addTest
                (new org.jmlspecs.jmlunit.strategies.ConstructorFailed(ex));
        }
        try {
            this.addTestSuiteFor$TestInsertAfter(overallTestSuite$);
        } catch (java.lang.Throwable ex) {
            overallTestSuite$.addTest
                (new org.jmlspecs.jmlunit.strategies.ConstructorFailed(ex));
        }
        try {
            this.addTestSuiteFor$TestRemoveNextNode(overallTestSuite$);
        } catch (java.lang.Throwable ex) {
            overallTestSuite$.addTest
                (new org.jmlspecs.jmlunit.strategies.ConstructorFailed(ex));
        }
        try {
            this.addTestSuiteFor$TestGetPrevNode(overallTestSuite$);
        } catch (java.lang.Throwable ex) {
            overallTestSuite$.addTest
                (new org.jmlspecs.jmlunit.strategies.ConstructorFailed(ex));
        }
        try {
            this.addTestSuiteFor$TestInsertBefore(overallTestSuite$);
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
        try {
            this.addTestSuiteFor$TestGetEntry(overallTestSuite$);
        } catch (java.lang.Throwable ex) {
            overallTestSuite$.addTest
                (new org.jmlspecs.jmlunit.strategies.ConstructorFailed(ex));
        }
        try {
            this.addTestSuiteFor$TestSetEntry(overallTestSuite$);
        } catch (java.lang.Throwable ex) {
            overallTestSuite$.addTest
                (new org.jmlspecs.jmlunit.strategies.ConstructorFailed(ex));
        }
        try {
            this.addTestSuiteFor$TestGetNextNode(overallTestSuite$);
        } catch (java.lang.Throwable ex) {
            overallTestSuite$.addTest
                (new org.jmlspecs.jmlunit.strategies.ConstructorFailed(ex));
        }
        try {
            this.addTestSuiteFor$TestToString(overallTestSuite$);
        } catch (java.lang.Throwable ex) {
            overallTestSuite$.addTest
                (new org.jmlspecs.jmlunit.strategies.ConstructorFailed(ex));
        }
    }

    /** Add tests for the DLNode contructor
     * to the overall test suite. */
    private void addTestSuiteFor$TestDLNode
        (junit.framework.TestSuite overallTestSuite$)
    {
        junit.framework.TestSuite methodTests$
            = this.emptyTestSuiteFor("DLNode");
        try {
            org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                vjava_lang_Object$1$iter
                = this.vjava_lang_ObjectIter("DLNode", 0);
            this.check_has_data
                (vjava_lang_Object$1$iter,
                 "this.vjava_lang_ObjectIter(\"DLNode\", 0)");
            while (!vjava_lang_Object$1$iter.atEnd()) {
                final java.lang.Object ent
                    = (java.lang.Object) vjava_lang_Object$1$iter.get();
                methodTests$.addTest
                    (new TestDLNode(ent));
                vjava_lang_Object$1$iter.advance();
            }
        } catch (org.jmlspecs.jmlunit.strategies.TestSuiteFullException e$) {
            // methodTests$ doesn't want more tests
        }
        overallTestSuite$.addTest(methodTests$);
    }

    /** Test for the DLNode contructor. */
    protected static class TestDLNode extends OneTest {
        /** Argument ent */
        private java.lang.Object ent;

        /** Initialize this instance. */
        public TestDLNode(java.lang.Object ent) {
            super("DLNode"+ ":" + (ent==null? "null" :"{java.lang.Object}"));
            this.ent = ent;
        }

        protected void doCall() throws java.lang.Throwable {
            new DLNode(ent);
        }

        protected java.lang.String failMessage
            (org.jmlspecs.jmlrac.runtime.JMLAssertionError e$)
        {
            java.lang.String msg = "\n\tContructor 'DLNode' applied to";
            msg += "\n\tArgument ent: " + this.ent;
            return msg;
        }
    }

    /** Add tests for the insertAfter method
     * to the overall test suite. */
    private void addTestSuiteFor$TestInsertAfter
        (junit.framework.TestSuite overallTestSuite$)
    {
        junit.framework.TestSuite methodTests$
            = this.emptyTestSuiteFor("insertAfter");
        try {
            org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                receivers$iter
                = new org.jmlspecs.jmlunit.strategies.NonNullIteratorDecorator
                    (this.vorg_jmlspecs_samples_list_list1_node_DLNodeIter("insertAfter", 1));
            this.check_has_data
                (receivers$iter,
                 "new NonNullIteratorDecorator(this.vorg_jmlspecs_samples_list_list1_node_DLNodeIter(\"insertAfter\", 1))");
            while (!receivers$iter.atEnd()) {
                org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                    vjava_lang_Object$1$iter
                    = this.vjava_lang_ObjectIter("insertAfter", 0);
                this.check_has_data
                    (vjava_lang_Object$1$iter,
                     "this.vjava_lang_ObjectIter(\"insertAfter\", 0)");
                while (!vjava_lang_Object$1$iter.atEnd()) {
                    final org.jmlspecs.samples.list.list1.node.DLNode receiver$
                        = (org.jmlspecs.samples.list.list1.node.DLNode) receivers$iter.get();
                    final java.lang.Object newEntry
                        = (java.lang.Object) vjava_lang_Object$1$iter.get();
                    methodTests$.addTest
                        (new TestInsertAfter(receiver$, newEntry));
                    vjava_lang_Object$1$iter.advance();
                }
                receivers$iter.advance();
            }
        } catch (org.jmlspecs.jmlunit.strategies.TestSuiteFullException e$) {
            // methodTests$ doesn't want more tests
        }
        overallTestSuite$.addTest(methodTests$);
    }

    /** Test for the insertAfter method. */
    protected static class TestInsertAfter extends OneTest {
        /** The receiver */
        private org.jmlspecs.samples.list.list1.node.DLNode receiver$;
        /** Argument newEntry */
        private java.lang.Object newEntry;

        /** Initialize this instance. */
        public TestInsertAfter(org.jmlspecs.samples.list.list1.node.DLNode receiver$, java.lang.Object newEntry) {
            super("insertAfter"+ ":" + (newEntry==null? "null" :"{java.lang.Object}"));
            this.receiver$ = receiver$;
            this.newEntry = newEntry;
        }

        protected void doCall() throws java.lang.Throwable {
            receiver$.insertAfter(newEntry);
        }

        protected java.lang.String failMessage
            (org.jmlspecs.jmlrac.runtime.JMLAssertionError e$)
        {
            java.lang.String msg = "\n\tMethod 'insertAfter' applied to";
            msg += "\n\tReceiver: " + this.receiver$;
            msg += "\n\tArgument newEntry: " + this.newEntry;
            return msg;
        }
    }

    /** Add tests for the removeNextNode method
     * to the overall test suite. */
    private void addTestSuiteFor$TestRemoveNextNode
        (junit.framework.TestSuite overallTestSuite$)
    {
        junit.framework.TestSuite methodTests$
            = this.emptyTestSuiteFor("removeNextNode");
        try {
            org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                receivers$iter
                = new org.jmlspecs.jmlunit.strategies.NonNullIteratorDecorator
                    (this.vorg_jmlspecs_samples_list_list1_node_DLNodeIter("removeNextNode", 0));
            this.check_has_data
                (receivers$iter,
                 "new NonNullIteratorDecorator(this.vorg_jmlspecs_samples_list_list1_node_DLNodeIter(\"removeNextNode\", 0))");
            while (!receivers$iter.atEnd()) {
                final org.jmlspecs.samples.list.list1.node.DLNode receiver$
                    = (org.jmlspecs.samples.list.list1.node.DLNode) receivers$iter.get();
                methodTests$.addTest
                    (new TestRemoveNextNode(receiver$));
                receivers$iter.advance();
            }
        } catch (org.jmlspecs.jmlunit.strategies.TestSuiteFullException e$) {
            // methodTests$ doesn't want more tests
        }
        overallTestSuite$.addTest(methodTests$);
    }

    /** Test for the removeNextNode method. */
    protected static class TestRemoveNextNode extends OneTest {
        /** The receiver */
        private org.jmlspecs.samples.list.list1.node.DLNode receiver$;

        /** Initialize this instance. */
        public TestRemoveNextNode(org.jmlspecs.samples.list.list1.node.DLNode receiver$) {
            super("removeNextNode");
            this.receiver$ = receiver$;
        }

        protected void doCall() throws java.lang.Throwable {
            receiver$.removeNextNode();
        }

        protected java.lang.String failMessage
            (org.jmlspecs.jmlrac.runtime.JMLAssertionError e$)
        {
            java.lang.String msg = "\n\tMethod 'removeNextNode' applied to";
            msg += "\n\tReceiver: " + this.receiver$;
            return msg;
        }
    }

    /** Add tests for the getPrevNode method
     * to the overall test suite. */
    private void addTestSuiteFor$TestGetPrevNode
        (junit.framework.TestSuite overallTestSuite$)
    {
        junit.framework.TestSuite methodTests$
            = this.emptyTestSuiteFor("getPrevNode");
        try {
            org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                receivers$iter
                = new org.jmlspecs.jmlunit.strategies.NonNullIteratorDecorator
                    (this.vorg_jmlspecs_samples_list_list1_node_DLNodeIter("getPrevNode", 0));
            this.check_has_data
                (receivers$iter,
                 "new NonNullIteratorDecorator(this.vorg_jmlspecs_samples_list_list1_node_DLNodeIter(\"getPrevNode\", 0))");
            while (!receivers$iter.atEnd()) {
                final org.jmlspecs.samples.list.list1.node.DLNode receiver$
                    = (org.jmlspecs.samples.list.list1.node.DLNode) receivers$iter.get();
                methodTests$.addTest
                    (new TestGetPrevNode(receiver$));
                receivers$iter.advance();
            }
        } catch (org.jmlspecs.jmlunit.strategies.TestSuiteFullException e$) {
            // methodTests$ doesn't want more tests
        }
        overallTestSuite$.addTest(methodTests$);
    }

    /** Test for the getPrevNode method. */
    protected static class TestGetPrevNode extends OneTest {
        /** The receiver */
        private org.jmlspecs.samples.list.list1.node.DLNode receiver$;

        /** Initialize this instance. */
        public TestGetPrevNode(org.jmlspecs.samples.list.list1.node.DLNode receiver$) {
            super("getPrevNode");
            this.receiver$ = receiver$;
        }

        protected void doCall() throws java.lang.Throwable {
            receiver$.getPrevNode();
        }

        protected java.lang.String failMessage
            (org.jmlspecs.jmlrac.runtime.JMLAssertionError e$)
        {
            java.lang.String msg = "\n\tMethod 'getPrevNode' applied to";
            msg += "\n\tReceiver: " + this.receiver$;
            return msg;
        }
    }

    /** Add tests for the insertBefore method
     * to the overall test suite. */
    private void addTestSuiteFor$TestInsertBefore
        (junit.framework.TestSuite overallTestSuite$)
    {
        junit.framework.TestSuite methodTests$
            = this.emptyTestSuiteFor("insertBefore");
        try {
            org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                receivers$iter
                = new org.jmlspecs.jmlunit.strategies.NonNullIteratorDecorator
                    (this.vorg_jmlspecs_samples_list_list1_node_DLNodeIter("insertBefore", 1));
            this.check_has_data
                (receivers$iter,
                 "new NonNullIteratorDecorator(this.vorg_jmlspecs_samples_list_list1_node_DLNodeIter(\"insertBefore\", 1))");
            while (!receivers$iter.atEnd()) {
                org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                    vjava_lang_Object$1$iter
                    = this.vjava_lang_ObjectIter("insertBefore", 0);
                this.check_has_data
                    (vjava_lang_Object$1$iter,
                     "this.vjava_lang_ObjectIter(\"insertBefore\", 0)");
                while (!vjava_lang_Object$1$iter.atEnd()) {
                    final org.jmlspecs.samples.list.list1.node.DLNode receiver$
                        = (org.jmlspecs.samples.list.list1.node.DLNode) receivers$iter.get();
                    final java.lang.Object newEntry
                        = (java.lang.Object) vjava_lang_Object$1$iter.get();
                    methodTests$.addTest
                        (new TestInsertBefore(receiver$, newEntry));
                    vjava_lang_Object$1$iter.advance();
                }
                receivers$iter.advance();
            }
        } catch (org.jmlspecs.jmlunit.strategies.TestSuiteFullException e$) {
            // methodTests$ doesn't want more tests
        }
        overallTestSuite$.addTest(methodTests$);
    }

    /** Test for the insertBefore method. */
    protected static class TestInsertBefore extends OneTest {
        /** The receiver */
        private org.jmlspecs.samples.list.list1.node.DLNode receiver$;
        /** Argument newEntry */
        private java.lang.Object newEntry;

        /** Initialize this instance. */
        public TestInsertBefore(org.jmlspecs.samples.list.list1.node.DLNode receiver$, java.lang.Object newEntry) {
            super("insertBefore"+ ":" + (newEntry==null? "null" :"{java.lang.Object}"));
            this.receiver$ = receiver$;
            this.newEntry = newEntry;
        }

        protected void doCall() throws java.lang.Throwable {
            receiver$.insertBefore(newEntry);
        }

        protected java.lang.String failMessage
            (org.jmlspecs.jmlrac.runtime.JMLAssertionError e$)
        {
            java.lang.String msg = "\n\tMethod 'insertBefore' applied to";
            msg += "\n\tReceiver: " + this.receiver$;
            msg += "\n\tArgument newEntry: " + this.newEntry;
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
                    (this.vorg_jmlspecs_samples_list_list1_node_DLNodeIter("clone", 0));
            this.check_has_data
                (receivers$iter,
                 "new NonNullIteratorDecorator(this.vorg_jmlspecs_samples_list_list1_node_DLNodeIter(\"clone\", 0))");
            while (!receivers$iter.atEnd()) {
                final org.jmlspecs.samples.list.list1.node.DLNode receiver$
                    = (org.jmlspecs.samples.list.list1.node.DLNode) receivers$iter.get();
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
        private org.jmlspecs.samples.list.list1.node.DLNode receiver$;

        /** Initialize this instance. */
        public TestClone(org.jmlspecs.samples.list.list1.node.DLNode receiver$) {
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

    /** Add tests for the getEntry method
     * to the overall test suite. */
    private void addTestSuiteFor$TestGetEntry
        (junit.framework.TestSuite overallTestSuite$)
    {
        junit.framework.TestSuite methodTests$
            = this.emptyTestSuiteFor("getEntry");
        try {
            org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                receivers$iter
                = new org.jmlspecs.jmlunit.strategies.NonNullIteratorDecorator
                    (this.vorg_jmlspecs_samples_list_list1_node_DLNodeIter("getEntry", 0));
            this.check_has_data
                (receivers$iter,
                 "new NonNullIteratorDecorator(this.vorg_jmlspecs_samples_list_list1_node_DLNodeIter(\"getEntry\", 0))");
            while (!receivers$iter.atEnd()) {
                final org.jmlspecs.samples.list.list1.node.DLNode receiver$
                    = (org.jmlspecs.samples.list.list1.node.DLNode) receivers$iter.get();
                methodTests$.addTest
                    (new TestGetEntry(receiver$));
                receivers$iter.advance();
            }
        } catch (org.jmlspecs.jmlunit.strategies.TestSuiteFullException e$) {
            // methodTests$ doesn't want more tests
        }
        overallTestSuite$.addTest(methodTests$);
    }

    /** Test for the getEntry method. */
    protected static class TestGetEntry extends OneTest {
        /** The receiver */
        private org.jmlspecs.samples.list.list1.node.DLNode receiver$;

        /** Initialize this instance. */
        public TestGetEntry(org.jmlspecs.samples.list.list1.node.DLNode receiver$) {
            super("getEntry");
            this.receiver$ = receiver$;
        }

        protected void doCall() throws java.lang.Throwable {
            receiver$.getEntry();
        }

        protected java.lang.String failMessage
            (org.jmlspecs.jmlrac.runtime.JMLAssertionError e$)
        {
            java.lang.String msg = "\n\tMethod 'getEntry' applied to";
            msg += "\n\tReceiver: " + this.receiver$;
            return msg;
        }
    }

    /** Add tests for the setEntry method
     * to the overall test suite. */
    private void addTestSuiteFor$TestSetEntry
        (junit.framework.TestSuite overallTestSuite$)
    {
        junit.framework.TestSuite methodTests$
            = this.emptyTestSuiteFor("setEntry");
        try {
            org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                receivers$iter
                = new org.jmlspecs.jmlunit.strategies.NonNullIteratorDecorator
                    (this.vorg_jmlspecs_samples_list_list1_node_DLNodeIter("setEntry", 1));
            this.check_has_data
                (receivers$iter,
                 "new NonNullIteratorDecorator(this.vorg_jmlspecs_samples_list_list1_node_DLNodeIter(\"setEntry\", 1))");
            while (!receivers$iter.atEnd()) {
                org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                    vjava_lang_Object$1$iter
                    = this.vjava_lang_ObjectIter("setEntry", 0);
                this.check_has_data
                    (vjava_lang_Object$1$iter,
                     "this.vjava_lang_ObjectIter(\"setEntry\", 0)");
                while (!vjava_lang_Object$1$iter.atEnd()) {
                    final org.jmlspecs.samples.list.list1.node.DLNode receiver$
                        = (org.jmlspecs.samples.list.list1.node.DLNode) receivers$iter.get();
                    final java.lang.Object arg1
                        = (java.lang.Object) vjava_lang_Object$1$iter.get();
                    methodTests$.addTest
                        (new TestSetEntry(receiver$, arg1));
                    vjava_lang_Object$1$iter.advance();
                }
                receivers$iter.advance();
            }
        } catch (org.jmlspecs.jmlunit.strategies.TestSuiteFullException e$) {
            // methodTests$ doesn't want more tests
        }
        overallTestSuite$.addTest(methodTests$);
    }

    /** Test for the setEntry method. */
    protected static class TestSetEntry extends OneTest {
        /** The receiver */
        private org.jmlspecs.samples.list.list1.node.DLNode receiver$;
        /** Argument arg1 */
        private java.lang.Object arg1;

        /** Initialize this instance. */
        public TestSetEntry(org.jmlspecs.samples.list.list1.node.DLNode receiver$, java.lang.Object arg1) {
            super("setEntry"+ ":" + (arg1==null? "null" :"{java.lang.Object}"));
            this.receiver$ = receiver$;
            this.arg1 = arg1;
        }

        protected void doCall() throws java.lang.Throwable {
            receiver$.setEntry(arg1);
        }

        protected java.lang.String failMessage
            (org.jmlspecs.jmlrac.runtime.JMLAssertionError e$)
        {
            java.lang.String msg = "\n\tMethod 'setEntry' applied to";
            msg += "\n\tReceiver: " + this.receiver$;
            msg += "\n\tArgument arg1: " + this.arg1;
            return msg;
        }
    }

    /** Add tests for the getNextNode method
     * to the overall test suite. */
    private void addTestSuiteFor$TestGetNextNode
        (junit.framework.TestSuite overallTestSuite$)
    {
        junit.framework.TestSuite methodTests$
            = this.emptyTestSuiteFor("getNextNode");
        try {
            org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                receivers$iter
                = new org.jmlspecs.jmlunit.strategies.NonNullIteratorDecorator
                    (this.vorg_jmlspecs_samples_list_list1_node_DLNodeIter("getNextNode", 0));
            this.check_has_data
                (receivers$iter,
                 "new NonNullIteratorDecorator(this.vorg_jmlspecs_samples_list_list1_node_DLNodeIter(\"getNextNode\", 0))");
            while (!receivers$iter.atEnd()) {
                final org.jmlspecs.samples.list.list1.node.DLNode receiver$
                    = (org.jmlspecs.samples.list.list1.node.DLNode) receivers$iter.get();
                methodTests$.addTest
                    (new TestGetNextNode(receiver$));
                receivers$iter.advance();
            }
        } catch (org.jmlspecs.jmlunit.strategies.TestSuiteFullException e$) {
            // methodTests$ doesn't want more tests
        }
        overallTestSuite$.addTest(methodTests$);
    }

    /** Test for the getNextNode method. */
    protected static class TestGetNextNode extends OneTest {
        /** The receiver */
        private org.jmlspecs.samples.list.list1.node.DLNode receiver$;

        /** Initialize this instance. */
        public TestGetNextNode(org.jmlspecs.samples.list.list1.node.DLNode receiver$) {
            super("getNextNode");
            this.receiver$ = receiver$;
        }

        protected void doCall() throws java.lang.Throwable {
            receiver$.getNextNode();
        }

        protected java.lang.String failMessage
            (org.jmlspecs.jmlrac.runtime.JMLAssertionError e$)
        {
            java.lang.String msg = "\n\tMethod 'getNextNode' applied to";
            msg += "\n\tReceiver: " + this.receiver$;
            return msg;
        }
    }

    /** Add tests for the toString method
     * to the overall test suite. */
    private void addTestSuiteFor$TestToString
        (junit.framework.TestSuite overallTestSuite$)
    {
        junit.framework.TestSuite methodTests$
            = this.emptyTestSuiteFor("toString");
        try {
            org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                receivers$iter
                = new org.jmlspecs.jmlunit.strategies.NonNullIteratorDecorator
                    (this.vorg_jmlspecs_samples_list_list1_node_DLNodeIter("toString", 0));
            this.check_has_data
                (receivers$iter,
                 "new NonNullIteratorDecorator(this.vorg_jmlspecs_samples_list_list1_node_DLNodeIter(\"toString\", 0))");
            while (!receivers$iter.atEnd()) {
                final org.jmlspecs.samples.list.list1.node.DLNode receiver$
                    = (org.jmlspecs.samples.list.list1.node.DLNode) receivers$iter.get();
                methodTests$.addTest
                    (new TestToString(receiver$));
                receivers$iter.advance();
            }
        } catch (org.jmlspecs.jmlunit.strategies.TestSuiteFullException e$) {
            // methodTests$ doesn't want more tests
        }
        overallTestSuite$.addTest(methodTests$);
    }

    /** Test for the toString method. */
    protected static class TestToString extends OneTest {
        /** The receiver */
        private org.jmlspecs.samples.list.list1.node.DLNode receiver$;

        /** Initialize this instance. */
        public TestToString(org.jmlspecs.samples.list.list1.node.DLNode receiver$) {
            super("toString");
            this.receiver$ = receiver$;
        }

        protected void doCall() throws java.lang.Throwable {
            receiver$.toString();
        }

        protected java.lang.String failMessage
            (org.jmlspecs.jmlrac.runtime.JMLAssertionError e$)
        {
            java.lang.String msg = "\n\tMethod 'toString' applied to";
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
