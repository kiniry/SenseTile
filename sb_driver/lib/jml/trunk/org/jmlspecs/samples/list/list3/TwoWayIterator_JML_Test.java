// This file was generated by jmlunit on Mon Mar 16 13:16:48 EDT 2009.

package org.jmlspecs.samples.list.list3;

import org.jmlspecs.models.JMLObjectSequence;
import org.jmlspecs.samples.list.node.OneWayNode;
import org.jmlspecs.samples.list.node.TwoWayNode;
import org.jmlspecs.samples.list.iterator.RestartableIterator;

/** Automatically-generated test driver for JML and JUnit based
 * testing of TwoWayIterator. The superclass of this class should be edited
 * to supply test data. However it's best not to edit this class
 * directly; instead use the command
 * <pre>
 *  jmlunit TwoWayIterator.java
 * </pre>
 * to regenerate this class whenever TwoWayIterator.java changes.
 */
public class TwoWayIterator_JML_Test
     extends TwoWayIterator_JML_TestData
{
    /** Initialize this class. */
    public TwoWayIterator_JML_Test(java.lang.String name) {
        super(name);
    }

    /** Run the tests. */
    public static void main(java.lang.String[] args) {
        org.jmlspecs.jmlunit.JMLTestRunner.run(suite());
        // You can also use a JUnit test runner such as:
        // junit.textui.TestRunner.run(suite());
    }

    /** Test to see if the code for class TwoWayIterator
     * has been compiled with runtime assertion checking (i.e., by jmlc).
     * Code that is not compiled with jmlc would not make an effective test,
     * since no assertion checking would be done. */
    public void test$IsRACCompiled() {
        junit.framework.Assert.assertTrue("code for class TwoWayIterator"
                + " was not compiled with jmlc"
                + " so no assertions will be checked!",
            org.jmlspecs.jmlrac.runtime.JMLChecker.isRACCompiled(TwoWayIterator.class)
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
        TwoWayIterator_JML_Test testobj
            = new TwoWayIterator_JML_Test("TwoWayIterator_JML_Test");
        junit.framework.TestSuite testsuite = testobj.overallTestSuite();
        // Add instances of Test found by the reflection mechanism.
        testsuite.addTestSuite(TwoWayIterator_JML_Test.class);
        testobj.addTestSuiteForEachMethod(testsuite);
        return testsuite;
    }

    /** A JUnit test object that can run a single test method.  This
     * is defined as a nested class solely for convenience; it can't
     * be defined once and for all because it must subclass its
     * enclosing class.
     */
    protected static abstract class OneTest extends TwoWayIterator_JML_Test {

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
     * TwoWayIterator.  The framework will then run them.
     * @param overallTestSuite$ The suite accumulating all of the tests
     * for this driver class.
     */
    //@ requires overallTestSuite$ != null;
    public void addTestSuiteForEachMethod
        (junit.framework.TestSuite overallTestSuite$)
    {
        try {
            this.addTestSuiteFor$TestTwoWayIterator(overallTestSuite$);
        } catch (java.lang.Throwable ex) {
            overallTestSuite$.addTest
                (new org.jmlspecs.jmlunit.strategies.ConstructorFailed(ex));
        }
        try {
            this.addTestSuiteFor$TestFirst(overallTestSuite$);
        } catch (java.lang.Throwable ex) {
            overallTestSuite$.addTest
                (new org.jmlspecs.jmlunit.strategies.ConstructorFailed(ex));
        }
        try {
            this.addTestSuiteFor$TestNext(overallTestSuite$);
        } catch (java.lang.Throwable ex) {
            overallTestSuite$.addTest
                (new org.jmlspecs.jmlunit.strategies.ConstructorFailed(ex));
        }
        try {
            this.addTestSuiteFor$TestIsDone(overallTestSuite$);
        } catch (java.lang.Throwable ex) {
            overallTestSuite$.addTest
                (new org.jmlspecs.jmlunit.strategies.ConstructorFailed(ex));
        }
        try {
            this.addTestSuiteFor$TestCurrentItem(overallTestSuite$);
        } catch (java.lang.Throwable ex) {
            overallTestSuite$.addTest
                (new org.jmlspecs.jmlunit.strategies.ConstructorFailed(ex));
        }
        try {
            this.addTestSuiteFor$TestLast(overallTestSuite$);
        } catch (java.lang.Throwable ex) {
            overallTestSuite$.addTest
                (new org.jmlspecs.jmlunit.strategies.ConstructorFailed(ex));
        }
        try {
            this.addTestSuiteFor$TestPrevious(overallTestSuite$);
        } catch (java.lang.Throwable ex) {
            overallTestSuite$.addTest
                (new org.jmlspecs.jmlunit.strategies.ConstructorFailed(ex));
        }
        try {
            this.addTestSuiteFor$TestIsAtFront(overallTestSuite$);
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

    /** Add tests for the TwoWayIterator contructor
     * to the overall test suite. */
    private void addTestSuiteFor$TestTwoWayIterator
        (junit.framework.TestSuite overallTestSuite$)
    {
        junit.framework.TestSuite methodTests$
            = this.emptyTestSuiteFor("TwoWayIterator");
        try {
            org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                vorg_jmlspecs_samples_list_node_TwoWayNode$1$iter
                = this.vorg_jmlspecs_samples_list_node_TwoWayNodeIter("TwoWayIterator", 0);
            this.check_has_data
                (vorg_jmlspecs_samples_list_node_TwoWayNode$1$iter,
                 "this.vorg_jmlspecs_samples_list_node_TwoWayNodeIter(\"TwoWayIterator\", 0)");
            while (!vorg_jmlspecs_samples_list_node_TwoWayNode$1$iter.atEnd()) {
                final org.jmlspecs.samples.list.node.TwoWayNode link
                    = (org.jmlspecs.samples.list.node.TwoWayNode) vorg_jmlspecs_samples_list_node_TwoWayNode$1$iter.get();
                methodTests$.addTest
                    (new TestTwoWayIterator(link));
                vorg_jmlspecs_samples_list_node_TwoWayNode$1$iter.advance();
            }
        } catch (org.jmlspecs.jmlunit.strategies.TestSuiteFullException e$) {
            // methodTests$ doesn't want more tests
        }
        overallTestSuite$.addTest(methodTests$);
    }

    /** Test for the TwoWayIterator contructor. */
    protected static class TestTwoWayIterator extends OneTest {
        /** Argument link */
        private org.jmlspecs.samples.list.node.TwoWayNode link;

        /** Initialize this instance. */
        public TestTwoWayIterator(org.jmlspecs.samples.list.node.TwoWayNode link) {
            super("TwoWayIterator"+ ":" + (link==null? "null" :"{org.jmlspecs.samples.list.node.TwoWayNode}"));
            this.link = link;
        }

        protected void doCall() throws java.lang.Throwable {
            new TwoWayIterator(link);
        }

        protected java.lang.String failMessage
            (org.jmlspecs.jmlrac.runtime.JMLAssertionError e$)
        {
            java.lang.String msg = "\n\tContructor 'TwoWayIterator' applied to";
            msg += "\n\tArgument link: " + this.link;
            return msg;
        }
    }

    /** Add tests for the first method
     * to the overall test suite. */
    private void addTestSuiteFor$TestFirst
        (junit.framework.TestSuite overallTestSuite$)
    {
        junit.framework.TestSuite methodTests$
            = this.emptyTestSuiteFor("first");
        try {
            org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                receivers$iter
                = new org.jmlspecs.jmlunit.strategies.NonNullIteratorDecorator
                    (this.vorg_jmlspecs_samples_list_list3_TwoWayIteratorIter("first", 0));
            this.check_has_data
                (receivers$iter,
                 "new NonNullIteratorDecorator(this.vorg_jmlspecs_samples_list_list3_TwoWayIteratorIter(\"first\", 0))");
            while (!receivers$iter.atEnd()) {
                final org.jmlspecs.samples.list.list3.TwoWayIterator receiver$
                    = (org.jmlspecs.samples.list.list3.TwoWayIterator) receivers$iter.get();
                methodTests$.addTest
                    (new TestFirst(receiver$));
                receivers$iter.advance();
            }
        } catch (org.jmlspecs.jmlunit.strategies.TestSuiteFullException e$) {
            // methodTests$ doesn't want more tests
        }
        overallTestSuite$.addTest(methodTests$);
    }

    /** Test for the first method. */
    protected static class TestFirst extends OneTest {
        /** The receiver */
        private org.jmlspecs.samples.list.list3.TwoWayIterator receiver$;

        /** Initialize this instance. */
        public TestFirst(org.jmlspecs.samples.list.list3.TwoWayIterator receiver$) {
            super("first");
            this.receiver$ = receiver$;
        }

        protected void doCall() throws java.lang.Throwable {
            receiver$.first();
        }

        protected java.lang.String failMessage
            (org.jmlspecs.jmlrac.runtime.JMLAssertionError e$)
        {
            java.lang.String msg = "\n\tMethod 'first' applied to";
            msg += "\n\tReceiver: " + this.receiver$;
            return msg;
        }
    }

    /** Add tests for the next method
     * to the overall test suite. */
    private void addTestSuiteFor$TestNext
        (junit.framework.TestSuite overallTestSuite$)
    {
        junit.framework.TestSuite methodTests$
            = this.emptyTestSuiteFor("next");
        try {
            org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                receivers$iter
                = new org.jmlspecs.jmlunit.strategies.NonNullIteratorDecorator
                    (this.vorg_jmlspecs_samples_list_list3_TwoWayIteratorIter("next", 0));
            this.check_has_data
                (receivers$iter,
                 "new NonNullIteratorDecorator(this.vorg_jmlspecs_samples_list_list3_TwoWayIteratorIter(\"next\", 0))");
            while (!receivers$iter.atEnd()) {
                final org.jmlspecs.samples.list.list3.TwoWayIterator receiver$
                    = (org.jmlspecs.samples.list.list3.TwoWayIterator) receivers$iter.get();
                methodTests$.addTest
                    (new TestNext(receiver$));
                receivers$iter.advance();
            }
        } catch (org.jmlspecs.jmlunit.strategies.TestSuiteFullException e$) {
            // methodTests$ doesn't want more tests
        }
        overallTestSuite$.addTest(methodTests$);
    }

    /** Test for the next method. */
    protected static class TestNext extends OneTest {
        /** The receiver */
        private org.jmlspecs.samples.list.list3.TwoWayIterator receiver$;

        /** Initialize this instance. */
        public TestNext(org.jmlspecs.samples.list.list3.TwoWayIterator receiver$) {
            super("next");
            this.receiver$ = receiver$;
        }

        protected void doCall() throws java.lang.Throwable {
            receiver$.next();
        }

        protected java.lang.String failMessage
            (org.jmlspecs.jmlrac.runtime.JMLAssertionError e$)
        {
            java.lang.String msg = "\n\tMethod 'next' applied to";
            msg += "\n\tReceiver: " + this.receiver$;
            return msg;
        }
    }

    /** Add tests for the isDone method
     * to the overall test suite. */
    private void addTestSuiteFor$TestIsDone
        (junit.framework.TestSuite overallTestSuite$)
    {
        junit.framework.TestSuite methodTests$
            = this.emptyTestSuiteFor("isDone");
        try {
            org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                receivers$iter
                = new org.jmlspecs.jmlunit.strategies.NonNullIteratorDecorator
                    (this.vorg_jmlspecs_samples_list_list3_TwoWayIteratorIter("isDone", 0));
            this.check_has_data
                (receivers$iter,
                 "new NonNullIteratorDecorator(this.vorg_jmlspecs_samples_list_list3_TwoWayIteratorIter(\"isDone\", 0))");
            while (!receivers$iter.atEnd()) {
                final org.jmlspecs.samples.list.list3.TwoWayIterator receiver$
                    = (org.jmlspecs.samples.list.list3.TwoWayIterator) receivers$iter.get();
                methodTests$.addTest
                    (new TestIsDone(receiver$));
                receivers$iter.advance();
            }
        } catch (org.jmlspecs.jmlunit.strategies.TestSuiteFullException e$) {
            // methodTests$ doesn't want more tests
        }
        overallTestSuite$.addTest(methodTests$);
    }

    /** Test for the isDone method. */
    protected static class TestIsDone extends OneTest {
        /** The receiver */
        private org.jmlspecs.samples.list.list3.TwoWayIterator receiver$;

        /** Initialize this instance. */
        public TestIsDone(org.jmlspecs.samples.list.list3.TwoWayIterator receiver$) {
            super("isDone");
            this.receiver$ = receiver$;
        }

        protected void doCall() throws java.lang.Throwable {
            receiver$.isDone();
        }

        protected java.lang.String failMessage
            (org.jmlspecs.jmlrac.runtime.JMLAssertionError e$)
        {
            java.lang.String msg = "\n\tMethod 'isDone' applied to";
            msg += "\n\tReceiver: " + this.receiver$;
            return msg;
        }
    }

    /** Add tests for the currentItem method
     * to the overall test suite. */
    private void addTestSuiteFor$TestCurrentItem
        (junit.framework.TestSuite overallTestSuite$)
    {
        junit.framework.TestSuite methodTests$
            = this.emptyTestSuiteFor("currentItem");
        try {
            org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                receivers$iter
                = new org.jmlspecs.jmlunit.strategies.NonNullIteratorDecorator
                    (this.vorg_jmlspecs_samples_list_list3_TwoWayIteratorIter("currentItem", 0));
            this.check_has_data
                (receivers$iter,
                 "new NonNullIteratorDecorator(this.vorg_jmlspecs_samples_list_list3_TwoWayIteratorIter(\"currentItem\", 0))");
            while (!receivers$iter.atEnd()) {
                final org.jmlspecs.samples.list.list3.TwoWayIterator receiver$
                    = (org.jmlspecs.samples.list.list3.TwoWayIterator) receivers$iter.get();
                methodTests$.addTest
                    (new TestCurrentItem(receiver$));
                receivers$iter.advance();
            }
        } catch (org.jmlspecs.jmlunit.strategies.TestSuiteFullException e$) {
            // methodTests$ doesn't want more tests
        }
        overallTestSuite$.addTest(methodTests$);
    }

    /** Test for the currentItem method. */
    protected static class TestCurrentItem extends OneTest {
        /** The receiver */
        private org.jmlspecs.samples.list.list3.TwoWayIterator receiver$;

        /** Initialize this instance. */
        public TestCurrentItem(org.jmlspecs.samples.list.list3.TwoWayIterator receiver$) {
            super("currentItem");
            this.receiver$ = receiver$;
        }

        protected void doCall() throws java.lang.Throwable {
            receiver$.currentItem();
        }

        protected java.lang.String failMessage
            (org.jmlspecs.jmlrac.runtime.JMLAssertionError e$)
        {
            java.lang.String msg = "\n\tMethod 'currentItem' applied to";
            msg += "\n\tReceiver: " + this.receiver$;
            return msg;
        }
    }

    /** Add tests for the last method
     * to the overall test suite. */
    private void addTestSuiteFor$TestLast
        (junit.framework.TestSuite overallTestSuite$)
    {
        junit.framework.TestSuite methodTests$
            = this.emptyTestSuiteFor("last");
        try {
            org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                receivers$iter
                = new org.jmlspecs.jmlunit.strategies.NonNullIteratorDecorator
                    (this.vorg_jmlspecs_samples_list_list3_TwoWayIteratorIter("last", 0));
            this.check_has_data
                (receivers$iter,
                 "new NonNullIteratorDecorator(this.vorg_jmlspecs_samples_list_list3_TwoWayIteratorIter(\"last\", 0))");
            while (!receivers$iter.atEnd()) {
                final org.jmlspecs.samples.list.list3.TwoWayIterator receiver$
                    = (org.jmlspecs.samples.list.list3.TwoWayIterator) receivers$iter.get();
                methodTests$.addTest
                    (new TestLast(receiver$));
                receivers$iter.advance();
            }
        } catch (org.jmlspecs.jmlunit.strategies.TestSuiteFullException e$) {
            // methodTests$ doesn't want more tests
        }
        overallTestSuite$.addTest(methodTests$);
    }

    /** Test for the last method. */
    protected static class TestLast extends OneTest {
        /** The receiver */
        private org.jmlspecs.samples.list.list3.TwoWayIterator receiver$;

        /** Initialize this instance. */
        public TestLast(org.jmlspecs.samples.list.list3.TwoWayIterator receiver$) {
            super("last");
            this.receiver$ = receiver$;
        }

        protected void doCall() throws java.lang.Throwable {
            receiver$.last();
        }

        protected java.lang.String failMessage
            (org.jmlspecs.jmlrac.runtime.JMLAssertionError e$)
        {
            java.lang.String msg = "\n\tMethod 'last' applied to";
            msg += "\n\tReceiver: " + this.receiver$;
            return msg;
        }
    }

    /** Add tests for the previous method
     * to the overall test suite. */
    private void addTestSuiteFor$TestPrevious
        (junit.framework.TestSuite overallTestSuite$)
    {
        junit.framework.TestSuite methodTests$
            = this.emptyTestSuiteFor("previous");
        try {
            org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                receivers$iter
                = new org.jmlspecs.jmlunit.strategies.NonNullIteratorDecorator
                    (this.vorg_jmlspecs_samples_list_list3_TwoWayIteratorIter("previous", 0));
            this.check_has_data
                (receivers$iter,
                 "new NonNullIteratorDecorator(this.vorg_jmlspecs_samples_list_list3_TwoWayIteratorIter(\"previous\", 0))");
            while (!receivers$iter.atEnd()) {
                final org.jmlspecs.samples.list.list3.TwoWayIterator receiver$
                    = (org.jmlspecs.samples.list.list3.TwoWayIterator) receivers$iter.get();
                methodTests$.addTest
                    (new TestPrevious(receiver$));
                receivers$iter.advance();
            }
        } catch (org.jmlspecs.jmlunit.strategies.TestSuiteFullException e$) {
            // methodTests$ doesn't want more tests
        }
        overallTestSuite$.addTest(methodTests$);
    }

    /** Test for the previous method. */
    protected static class TestPrevious extends OneTest {
        /** The receiver */
        private org.jmlspecs.samples.list.list3.TwoWayIterator receiver$;

        /** Initialize this instance. */
        public TestPrevious(org.jmlspecs.samples.list.list3.TwoWayIterator receiver$) {
            super("previous");
            this.receiver$ = receiver$;
        }

        protected void doCall() throws java.lang.Throwable {
            receiver$.previous();
        }

        protected java.lang.String failMessage
            (org.jmlspecs.jmlrac.runtime.JMLAssertionError e$)
        {
            java.lang.String msg = "\n\tMethod 'previous' applied to";
            msg += "\n\tReceiver: " + this.receiver$;
            return msg;
        }
    }

    /** Add tests for the isAtFront method
     * to the overall test suite. */
    private void addTestSuiteFor$TestIsAtFront
        (junit.framework.TestSuite overallTestSuite$)
    {
        junit.framework.TestSuite methodTests$
            = this.emptyTestSuiteFor("isAtFront");
        try {
            org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                receivers$iter
                = new org.jmlspecs.jmlunit.strategies.NonNullIteratorDecorator
                    (this.vorg_jmlspecs_samples_list_list3_TwoWayIteratorIter("isAtFront", 0));
            this.check_has_data
                (receivers$iter,
                 "new NonNullIteratorDecorator(this.vorg_jmlspecs_samples_list_list3_TwoWayIteratorIter(\"isAtFront\", 0))");
            while (!receivers$iter.atEnd()) {
                final org.jmlspecs.samples.list.list3.TwoWayIterator receiver$
                    = (org.jmlspecs.samples.list.list3.TwoWayIterator) receivers$iter.get();
                methodTests$.addTest
                    (new TestIsAtFront(receiver$));
                receivers$iter.advance();
            }
        } catch (org.jmlspecs.jmlunit.strategies.TestSuiteFullException e$) {
            // methodTests$ doesn't want more tests
        }
        overallTestSuite$.addTest(methodTests$);
    }

    /** Test for the isAtFront method. */
    protected static class TestIsAtFront extends OneTest {
        /** The receiver */
        private org.jmlspecs.samples.list.list3.TwoWayIterator receiver$;

        /** Initialize this instance. */
        public TestIsAtFront(org.jmlspecs.samples.list.list3.TwoWayIterator receiver$) {
            super("isAtFront");
            this.receiver$ = receiver$;
        }

        protected void doCall() throws java.lang.Throwable {
            receiver$.isAtFront();
        }

        protected java.lang.String failMessage
            (org.jmlspecs.jmlrac.runtime.JMLAssertionError e$)
        {
            java.lang.String msg = "\n\tMethod 'isAtFront' applied to";
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
                    (this.vorg_jmlspecs_samples_list_list3_TwoWayIteratorIter("toString", 0));
            this.check_has_data
                (receivers$iter,
                 "new NonNullIteratorDecorator(this.vorg_jmlspecs_samples_list_list3_TwoWayIteratorIter(\"toString\", 0))");
            while (!receivers$iter.atEnd()) {
                final org.jmlspecs.samples.list.list3.TwoWayIterator receiver$
                    = (org.jmlspecs.samples.list.list3.TwoWayIterator) receivers$iter.get();
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
        private org.jmlspecs.samples.list.list3.TwoWayIterator receiver$;

        /** Initialize this instance. */
        public TestToString(org.jmlspecs.samples.list.list3.TwoWayIterator receiver$) {
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
