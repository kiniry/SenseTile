// This file was generated by jmlunit on Wed Jan 09 22:25:10 EST 2008.

package org.jmlspecs.samples.list.list1;

import org.jmlspecs.samples.list.list1.node.SLNode;

/** Automatically-generated test driver for JML and JUnit based
 * testing of SLList. The superclass of this class should be edited
 * to supply test data. However it's best not to edit this class
 * directly; instead use the command
 * <pre>
 *  jmlunit SLList.java
 * </pre>
 * to regenerate this class whenever SLList.java changes.
 */
public class SLList_JML_Test
     extends SLList_JML_TestData
{
    /** Initialize this class. */
    public SLList_JML_Test(java.lang.String name) {
        super(name);
    }

    /** Run the tests. */
    public static void main(java.lang.String[] args) {
        org.jmlspecs.jmlunit.JMLTestRunner.run(suite());
        // You can also use a JUnit test runner such as:
        // junit.textui.TestRunner.run(suite());
    }

    /** Test to see if the code for class SLList
     * has been compiled with runtime assertion checking (i.e., by jmlc).
     * Code that is not compiled with jmlc would not make an effective test,
     * since no assertion checking would be done. */
    public void test$IsRACCompiled() {
        junit.framework.Assert.assertTrue("code for class SLList"
                + " was not compiled with jmlc"
                + " so no assertions will be checked!",
            org.jmlspecs.jmlrac.runtime.JMLChecker.isRACCompiled(SLList.class)
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
        SLList_JML_Test testobj
            = new SLList_JML_Test("SLList_JML_Test");
        junit.framework.TestSuite testsuite = testobj.overallTestSuite();
        // Add instances of Test found by the reflection mechanism.
        testsuite.addTestSuite(SLList_JML_Test.class);
        testobj.addTestSuiteForEachMethod(testsuite);
        return testsuite;
    }

    /** A JUnit test object that can run a single test method.  This
     * is defined as a nested class solely for convenience; it can't
     * be defined once and for all because it must subclass its
     * enclosing class.
     */
    protected static abstract class OneTest extends SLList_JML_Test {

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
     * SLList.  The framework will then run them.
     * @param overallTestSuite$ The suite accumulating all of the tests
     * for this driver class.
     */
    //@ requires overallTestSuite$ != null;
    public void addTestSuiteForEachMethod
        (junit.framework.TestSuite overallTestSuite$)
    {
        try {
            this.addTestSuiteFor$TestSLList(overallTestSuite$);
        } catch (java.lang.Throwable ex) {
            overallTestSuite$.addTest
                (new org.jmlspecs.jmlunit.strategies.ConstructorFailed(ex));
        }
        try {
            this.addTestSuiteFor$TestFirstEntry(overallTestSuite$);
        } catch (java.lang.Throwable ex) {
            overallTestSuite$.addTest
                (new org.jmlspecs.jmlunit.strategies.ConstructorFailed(ex));
        }
        try {
            this.addTestSuiteFor$TestIncrementCursor(overallTestSuite$);
        } catch (java.lang.Throwable ex) {
            overallTestSuite$.addTest
                (new org.jmlspecs.jmlunit.strategies.ConstructorFailed(ex));
        }
        try {
            this.addTestSuiteFor$TestIsOffFront(overallTestSuite$);
        } catch (java.lang.Throwable ex) {
            overallTestSuite$.addTest
                (new org.jmlspecs.jmlunit.strategies.ConstructorFailed(ex));
        }
        try {
            this.addTestSuiteFor$TestIsOffEnd(overallTestSuite$);
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
            this.addTestSuiteFor$TestRemoveEntry(overallTestSuite$);
        } catch (java.lang.Throwable ex) {
            overallTestSuite$.addTest
                (new org.jmlspecs.jmlunit.strategies.ConstructorFailed(ex));
        }
        try {
            this.addTestSuiteFor$TestReplaceEntry(overallTestSuite$);
        } catch (java.lang.Throwable ex) {
            overallTestSuite$.addTest
                (new org.jmlspecs.jmlunit.strategies.ConstructorFailed(ex));
        }
        try {
            this.addTestSuiteFor$TestInsertAfterCursor(overallTestSuite$);
        } catch (java.lang.Throwable ex) {
            overallTestSuite$.addTest
                (new org.jmlspecs.jmlunit.strategies.ConstructorFailed(ex));
        }
        try {
            this.addTestSuiteFor$TestInsertBeforeCursor(overallTestSuite$);
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
            this.addTestSuiteFor$TestToString(overallTestSuite$);
        } catch (java.lang.Throwable ex) {
            overallTestSuite$.addTest
                (new org.jmlspecs.jmlunit.strategies.ConstructorFailed(ex));
        }
    }

    /** Add tests for the SLList contructor
     * to the overall test suite. */
    private void addTestSuiteFor$TestSLList
        (junit.framework.TestSuite overallTestSuite$)
    {
        junit.framework.TestSuite methodTests$
            = this.emptyTestSuiteFor("SLList");
        try {
            methodTests$.addTest
                (new TestSLList());
        } catch (org.jmlspecs.jmlunit.strategies.TestSuiteFullException e$) {
            // methodTests$ doesn't want more tests
        }
        overallTestSuite$.addTest(methodTests$);
    }

    /** Test for the SLList contructor. */
    protected static class TestSLList extends OneTest {

        /** Initialize this instance. */
        public TestSLList() {
            super("SLList");
        }

        protected void doCall() throws java.lang.Throwable {
            new SLList();
        }

        protected java.lang.String failMessage
            (org.jmlspecs.jmlrac.runtime.JMLAssertionError e$)
        {
            java.lang.String msg = "\n\tContructor 'SLList'";
            return msg;
        }
    }

    /** Add tests for the firstEntry method
     * to the overall test suite. */
    private void addTestSuiteFor$TestFirstEntry
        (junit.framework.TestSuite overallTestSuite$)
    {
        junit.framework.TestSuite methodTests$
            = this.emptyTestSuiteFor("firstEntry");
        try {
            org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                receivers$iter
                = new org.jmlspecs.jmlunit.strategies.NonNullIteratorDecorator
                    (this.vorg_jmlspecs_samples_list_list1_SLListIter("firstEntry", 0));
            this.check_has_data
                (receivers$iter,
                 "new NonNullIteratorDecorator(this.vorg_jmlspecs_samples_list_list1_SLListIter(\"firstEntry\", 0))");
            while (!receivers$iter.atEnd()) {
                final org.jmlspecs.samples.list.list1.SLList receiver$
                    = (org.jmlspecs.samples.list.list1.SLList) receivers$iter.get();
                methodTests$.addTest
                    (new TestFirstEntry(receiver$));
                receivers$iter.advance();
            }
        } catch (org.jmlspecs.jmlunit.strategies.TestSuiteFullException e$) {
            // methodTests$ doesn't want more tests
        }
        overallTestSuite$.addTest(methodTests$);
    }

    /** Test for the firstEntry method. */
    protected static class TestFirstEntry extends OneTest {
        /** The receiver */
        private org.jmlspecs.samples.list.list1.SLList receiver$;

        /** Initialize this instance. */
        public TestFirstEntry(org.jmlspecs.samples.list.list1.SLList receiver$) {
            super("firstEntry");
            this.receiver$ = receiver$;
        }

        protected void doCall() throws java.lang.Throwable {
            receiver$.firstEntry();
        }

        protected java.lang.String failMessage
            (org.jmlspecs.jmlrac.runtime.JMLAssertionError e$)
        {
            java.lang.String msg = "\n\tMethod 'firstEntry' applied to";
            msg += "\n\tReceiver: " + this.receiver$;
            return msg;
        }
    }

    /** Add tests for the incrementCursor method
     * to the overall test suite. */
    private void addTestSuiteFor$TestIncrementCursor
        (junit.framework.TestSuite overallTestSuite$)
    {
        junit.framework.TestSuite methodTests$
            = this.emptyTestSuiteFor("incrementCursor");
        try {
            org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                receivers$iter
                = new org.jmlspecs.jmlunit.strategies.NonNullIteratorDecorator
                    (this.vorg_jmlspecs_samples_list_list1_SLListIter("incrementCursor", 0));
            this.check_has_data
                (receivers$iter,
                 "new NonNullIteratorDecorator(this.vorg_jmlspecs_samples_list_list1_SLListIter(\"incrementCursor\", 0))");
            while (!receivers$iter.atEnd()) {
                final org.jmlspecs.samples.list.list1.SLList receiver$
                    = (org.jmlspecs.samples.list.list1.SLList) receivers$iter.get();
                methodTests$.addTest
                    (new TestIncrementCursor(receiver$));
                receivers$iter.advance();
            }
        } catch (org.jmlspecs.jmlunit.strategies.TestSuiteFullException e$) {
            // methodTests$ doesn't want more tests
        }
        overallTestSuite$.addTest(methodTests$);
    }

    /** Test for the incrementCursor method. */
    protected static class TestIncrementCursor extends OneTest {
        /** The receiver */
        private org.jmlspecs.samples.list.list1.SLList receiver$;

        /** Initialize this instance. */
        public TestIncrementCursor(org.jmlspecs.samples.list.list1.SLList receiver$) {
            super("incrementCursor");
            this.receiver$ = receiver$;
        }

        protected void doCall() throws java.lang.Throwable {
            receiver$.incrementCursor();
        }

        protected java.lang.String failMessage
            (org.jmlspecs.jmlrac.runtime.JMLAssertionError e$)
        {
            java.lang.String msg = "\n\tMethod 'incrementCursor' applied to";
            msg += "\n\tReceiver: " + this.receiver$;
            return msg;
        }
    }

    /** Add tests for the isOffFront method
     * to the overall test suite. */
    private void addTestSuiteFor$TestIsOffFront
        (junit.framework.TestSuite overallTestSuite$)
    {
        junit.framework.TestSuite methodTests$
            = this.emptyTestSuiteFor("isOffFront");
        try {
            org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                receivers$iter
                = new org.jmlspecs.jmlunit.strategies.NonNullIteratorDecorator
                    (this.vorg_jmlspecs_samples_list_list1_SLListIter("isOffFront", 0));
            this.check_has_data
                (receivers$iter,
                 "new NonNullIteratorDecorator(this.vorg_jmlspecs_samples_list_list1_SLListIter(\"isOffFront\", 0))");
            while (!receivers$iter.atEnd()) {
                final org.jmlspecs.samples.list.list1.SLList receiver$
                    = (org.jmlspecs.samples.list.list1.SLList) receivers$iter.get();
                methodTests$.addTest
                    (new TestIsOffFront(receiver$));
                receivers$iter.advance();
            }
        } catch (org.jmlspecs.jmlunit.strategies.TestSuiteFullException e$) {
            // methodTests$ doesn't want more tests
        }
        overallTestSuite$.addTest(methodTests$);
    }

    /** Test for the isOffFront method. */
    protected static class TestIsOffFront extends OneTest {
        /** The receiver */
        private org.jmlspecs.samples.list.list1.SLList receiver$;

        /** Initialize this instance. */
        public TestIsOffFront(org.jmlspecs.samples.list.list1.SLList receiver$) {
            super("isOffFront");
            this.receiver$ = receiver$;
        }

        protected void doCall() throws java.lang.Throwable {
            receiver$.isOffFront();
        }

        protected java.lang.String failMessage
            (org.jmlspecs.jmlrac.runtime.JMLAssertionError e$)
        {
            java.lang.String msg = "\n\tMethod 'isOffFront' applied to";
            msg += "\n\tReceiver: " + this.receiver$;
            return msg;
        }
    }

    /** Add tests for the isOffEnd method
     * to the overall test suite. */
    private void addTestSuiteFor$TestIsOffEnd
        (junit.framework.TestSuite overallTestSuite$)
    {
        junit.framework.TestSuite methodTests$
            = this.emptyTestSuiteFor("isOffEnd");
        try {
            org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                receivers$iter
                = new org.jmlspecs.jmlunit.strategies.NonNullIteratorDecorator
                    (this.vorg_jmlspecs_samples_list_list1_SLListIter("isOffEnd", 0));
            this.check_has_data
                (receivers$iter,
                 "new NonNullIteratorDecorator(this.vorg_jmlspecs_samples_list_list1_SLListIter(\"isOffEnd\", 0))");
            while (!receivers$iter.atEnd()) {
                final org.jmlspecs.samples.list.list1.SLList receiver$
                    = (org.jmlspecs.samples.list.list1.SLList) receivers$iter.get();
                methodTests$.addTest
                    (new TestIsOffEnd(receiver$));
                receivers$iter.advance();
            }
        } catch (org.jmlspecs.jmlunit.strategies.TestSuiteFullException e$) {
            // methodTests$ doesn't want more tests
        }
        overallTestSuite$.addTest(methodTests$);
    }

    /** Test for the isOffEnd method. */
    protected static class TestIsOffEnd extends OneTest {
        /** The receiver */
        private org.jmlspecs.samples.list.list1.SLList receiver$;

        /** Initialize this instance. */
        public TestIsOffEnd(org.jmlspecs.samples.list.list1.SLList receiver$) {
            super("isOffEnd");
            this.receiver$ = receiver$;
        }

        protected void doCall() throws java.lang.Throwable {
            receiver$.isOffEnd();
        }

        protected java.lang.String failMessage
            (org.jmlspecs.jmlrac.runtime.JMLAssertionError e$)
        {
            java.lang.String msg = "\n\tMethod 'isOffEnd' applied to";
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
                    (this.vorg_jmlspecs_samples_list_list1_SLListIter("getEntry", 0));
            this.check_has_data
                (receivers$iter,
                 "new NonNullIteratorDecorator(this.vorg_jmlspecs_samples_list_list1_SLListIter(\"getEntry\", 0))");
            while (!receivers$iter.atEnd()) {
                final org.jmlspecs.samples.list.list1.SLList receiver$
                    = (org.jmlspecs.samples.list.list1.SLList) receivers$iter.get();
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
        private org.jmlspecs.samples.list.list1.SLList receiver$;

        /** Initialize this instance. */
        public TestGetEntry(org.jmlspecs.samples.list.list1.SLList receiver$) {
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

    /** Add tests for the removeEntry method
     * to the overall test suite. */
    private void addTestSuiteFor$TestRemoveEntry
        (junit.framework.TestSuite overallTestSuite$)
    {
        junit.framework.TestSuite methodTests$
            = this.emptyTestSuiteFor("removeEntry");
        try {
            org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                receivers$iter
                = new org.jmlspecs.jmlunit.strategies.NonNullIteratorDecorator
                    (this.vorg_jmlspecs_samples_list_list1_SLListIter("removeEntry", 0));
            this.check_has_data
                (receivers$iter,
                 "new NonNullIteratorDecorator(this.vorg_jmlspecs_samples_list_list1_SLListIter(\"removeEntry\", 0))");
            while (!receivers$iter.atEnd()) {
                final org.jmlspecs.samples.list.list1.SLList receiver$
                    = (org.jmlspecs.samples.list.list1.SLList) receivers$iter.get();
                methodTests$.addTest
                    (new TestRemoveEntry(receiver$));
                receivers$iter.advance();
            }
        } catch (org.jmlspecs.jmlunit.strategies.TestSuiteFullException e$) {
            // methodTests$ doesn't want more tests
        }
        overallTestSuite$.addTest(methodTests$);
    }

    /** Test for the removeEntry method. */
    protected static class TestRemoveEntry extends OneTest {
        /** The receiver */
        private org.jmlspecs.samples.list.list1.SLList receiver$;

        /** Initialize this instance. */
        public TestRemoveEntry(org.jmlspecs.samples.list.list1.SLList receiver$) {
            super("removeEntry");
            this.receiver$ = receiver$;
        }

        protected void doCall() throws java.lang.Throwable {
            receiver$.removeEntry();
        }

        protected java.lang.String failMessage
            (org.jmlspecs.jmlrac.runtime.JMLAssertionError e$)
        {
            java.lang.String msg = "\n\tMethod 'removeEntry' applied to";
            msg += "\n\tReceiver: " + this.receiver$;
            return msg;
        }
    }

    /** Add tests for the replaceEntry method
     * to the overall test suite. */
    private void addTestSuiteFor$TestReplaceEntry
        (junit.framework.TestSuite overallTestSuite$)
    {
        junit.framework.TestSuite methodTests$
            = this.emptyTestSuiteFor("replaceEntry");
        try {
            org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                receivers$iter
                = new org.jmlspecs.jmlunit.strategies.NonNullIteratorDecorator
                    (this.vorg_jmlspecs_samples_list_list1_SLListIter("replaceEntry", 1));
            this.check_has_data
                (receivers$iter,
                 "new NonNullIteratorDecorator(this.vorg_jmlspecs_samples_list_list1_SLListIter(\"replaceEntry\", 1))");
            while (!receivers$iter.atEnd()) {
                org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                    vjava_lang_Object$1$iter
                    = this.vjava_lang_ObjectIter("replaceEntry", 0);
                this.check_has_data
                    (vjava_lang_Object$1$iter,
                     "this.vjava_lang_ObjectIter(\"replaceEntry\", 0)");
                while (!vjava_lang_Object$1$iter.atEnd()) {
                    final org.jmlspecs.samples.list.list1.SLList receiver$
                        = (org.jmlspecs.samples.list.list1.SLList) receivers$iter.get();
                    final java.lang.Object newEntry
                        = (java.lang.Object) vjava_lang_Object$1$iter.get();
                    methodTests$.addTest
                        (new TestReplaceEntry(receiver$, newEntry));
                    vjava_lang_Object$1$iter.advance();
                }
                receivers$iter.advance();
            }
        } catch (org.jmlspecs.jmlunit.strategies.TestSuiteFullException e$) {
            // methodTests$ doesn't want more tests
        }
        overallTestSuite$.addTest(methodTests$);
    }

    /** Test for the replaceEntry method. */
    protected static class TestReplaceEntry extends OneTest {
        /** The receiver */
        private org.jmlspecs.samples.list.list1.SLList receiver$;
        /** Argument newEntry */
        private java.lang.Object newEntry;

        /** Initialize this instance. */
        public TestReplaceEntry(org.jmlspecs.samples.list.list1.SLList receiver$, java.lang.Object newEntry) {
            super("replaceEntry"+ ":" + (newEntry==null? "null" :"{java.lang.Object}"));
            this.receiver$ = receiver$;
            this.newEntry = newEntry;
        }

        protected void doCall() throws java.lang.Throwable {
            receiver$.replaceEntry(newEntry);
        }

        protected java.lang.String failMessage
            (org.jmlspecs.jmlrac.runtime.JMLAssertionError e$)
        {
            java.lang.String msg = "\n\tMethod 'replaceEntry' applied to";
            msg += "\n\tReceiver: " + this.receiver$;
            msg += "\n\tArgument newEntry: " + this.newEntry;
            return msg;
        }
    }

    /** Add tests for the insertAfterCursor method
     * to the overall test suite. */
    private void addTestSuiteFor$TestInsertAfterCursor
        (junit.framework.TestSuite overallTestSuite$)
    {
        junit.framework.TestSuite methodTests$
            = this.emptyTestSuiteFor("insertAfterCursor");
        try {
            org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                receivers$iter
                = new org.jmlspecs.jmlunit.strategies.NonNullIteratorDecorator
                    (this.vorg_jmlspecs_samples_list_list1_SLListIter("insertAfterCursor", 1));
            this.check_has_data
                (receivers$iter,
                 "new NonNullIteratorDecorator(this.vorg_jmlspecs_samples_list_list1_SLListIter(\"insertAfterCursor\", 1))");
            while (!receivers$iter.atEnd()) {
                org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                    vjava_lang_Object$1$iter
                    = this.vjava_lang_ObjectIter("insertAfterCursor", 0);
                this.check_has_data
                    (vjava_lang_Object$1$iter,
                     "this.vjava_lang_ObjectIter(\"insertAfterCursor\", 0)");
                while (!vjava_lang_Object$1$iter.atEnd()) {
                    final org.jmlspecs.samples.list.list1.SLList receiver$
                        = (org.jmlspecs.samples.list.list1.SLList) receivers$iter.get();
                    final java.lang.Object newEntry
                        = (java.lang.Object) vjava_lang_Object$1$iter.get();
                    methodTests$.addTest
                        (new TestInsertAfterCursor(receiver$, newEntry));
                    vjava_lang_Object$1$iter.advance();
                }
                receivers$iter.advance();
            }
        } catch (org.jmlspecs.jmlunit.strategies.TestSuiteFullException e$) {
            // methodTests$ doesn't want more tests
        }
        overallTestSuite$.addTest(methodTests$);
    }

    /** Test for the insertAfterCursor method. */
    protected static class TestInsertAfterCursor extends OneTest {
        /** The receiver */
        private org.jmlspecs.samples.list.list1.SLList receiver$;
        /** Argument newEntry */
        private java.lang.Object newEntry;

        /** Initialize this instance. */
        public TestInsertAfterCursor(org.jmlspecs.samples.list.list1.SLList receiver$, java.lang.Object newEntry) {
            super("insertAfterCursor"+ ":" + (newEntry==null? "null" :"{java.lang.Object}"));
            this.receiver$ = receiver$;
            this.newEntry = newEntry;
        }

        protected void doCall() throws java.lang.Throwable {
            receiver$.insertAfterCursor(newEntry);
        }

        protected java.lang.String failMessage
            (org.jmlspecs.jmlrac.runtime.JMLAssertionError e$)
        {
            java.lang.String msg = "\n\tMethod 'insertAfterCursor' applied to";
            msg += "\n\tReceiver: " + this.receiver$;
            msg += "\n\tArgument newEntry: " + this.newEntry;
            return msg;
        }
    }

    /** Add tests for the insertBeforeCursor method
     * to the overall test suite. */
    private void addTestSuiteFor$TestInsertBeforeCursor
        (junit.framework.TestSuite overallTestSuite$)
    {
        junit.framework.TestSuite methodTests$
            = this.emptyTestSuiteFor("insertBeforeCursor");
        try {
            org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                receivers$iter
                = new org.jmlspecs.jmlunit.strategies.NonNullIteratorDecorator
                    (this.vorg_jmlspecs_samples_list_list1_SLListIter("insertBeforeCursor", 1));
            this.check_has_data
                (receivers$iter,
                 "new NonNullIteratorDecorator(this.vorg_jmlspecs_samples_list_list1_SLListIter(\"insertBeforeCursor\", 1))");
            while (!receivers$iter.atEnd()) {
                org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                    vjava_lang_Object$1$iter
                    = this.vjava_lang_ObjectIter("insertBeforeCursor", 0);
                this.check_has_data
                    (vjava_lang_Object$1$iter,
                     "this.vjava_lang_ObjectIter(\"insertBeforeCursor\", 0)");
                while (!vjava_lang_Object$1$iter.atEnd()) {
                    final org.jmlspecs.samples.list.list1.SLList receiver$
                        = (org.jmlspecs.samples.list.list1.SLList) receivers$iter.get();
                    final java.lang.Object newEntry
                        = (java.lang.Object) vjava_lang_Object$1$iter.get();
                    methodTests$.addTest
                        (new TestInsertBeforeCursor(receiver$, newEntry));
                    vjava_lang_Object$1$iter.advance();
                }
                receivers$iter.advance();
            }
        } catch (org.jmlspecs.jmlunit.strategies.TestSuiteFullException e$) {
            // methodTests$ doesn't want more tests
        }
        overallTestSuite$.addTest(methodTests$);
    }

    /** Test for the insertBeforeCursor method. */
    protected static class TestInsertBeforeCursor extends OneTest {
        /** The receiver */
        private org.jmlspecs.samples.list.list1.SLList receiver$;
        /** Argument newEntry */
        private java.lang.Object newEntry;

        /** Initialize this instance. */
        public TestInsertBeforeCursor(org.jmlspecs.samples.list.list1.SLList receiver$, java.lang.Object newEntry) {
            super("insertBeforeCursor"+ ":" + (newEntry==null? "null" :"{java.lang.Object}"));
            this.receiver$ = receiver$;
            this.newEntry = newEntry;
        }

        protected void doCall() throws java.lang.Throwable {
            receiver$.insertBeforeCursor(newEntry);
        }

        protected java.lang.String failMessage
            (org.jmlspecs.jmlrac.runtime.JMLAssertionError e$)
        {
            java.lang.String msg = "\n\tMethod 'insertBeforeCursor' applied to";
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
                    (this.vorg_jmlspecs_samples_list_list1_SLListIter("clone", 0));
            this.check_has_data
                (receivers$iter,
                 "new NonNullIteratorDecorator(this.vorg_jmlspecs_samples_list_list1_SLListIter(\"clone\", 0))");
            while (!receivers$iter.atEnd()) {
                final org.jmlspecs.samples.list.list1.SLList receiver$
                    = (org.jmlspecs.samples.list.list1.SLList) receivers$iter.get();
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
        private org.jmlspecs.samples.list.list1.SLList receiver$;

        /** Initialize this instance. */
        public TestClone(org.jmlspecs.samples.list.list1.SLList receiver$) {
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
                    (this.vorg_jmlspecs_samples_list_list1_SLListIter("toString", 0));
            this.check_has_data
                (receivers$iter,
                 "new NonNullIteratorDecorator(this.vorg_jmlspecs_samples_list_list1_SLListIter(\"toString\", 0))");
            while (!receivers$iter.atEnd()) {
                final org.jmlspecs.samples.list.list1.SLList receiver$
                    = (org.jmlspecs.samples.list.list1.SLList) receivers$iter.get();
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
        private org.jmlspecs.samples.list.list1.SLList receiver$;

        /** Initialize this instance. */
        public TestToString(org.jmlspecs.samples.list.list1.SLList receiver$) {
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
