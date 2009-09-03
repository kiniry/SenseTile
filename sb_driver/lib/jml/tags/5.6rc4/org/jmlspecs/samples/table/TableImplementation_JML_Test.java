// This file was generated by jmlunit on Mon Mar 16 13:17:10 EDT 2009.

package org.jmlspecs.samples.table;

import java.util.Enumeration;
import java.util.Hashtable;
import org.jmlspecs.models.JMLType;

/** Automatically-generated test driver for JML and JUnit based
 * testing of TableImplementation. The superclass of this class should be edited
 * to supply test data. However it's best not to edit this class
 * directly; instead use the command
 * <pre>
 *  jmlunit TableImplementation.java
 * </pre>
 * to regenerate this class whenever TableImplementation.java changes.
 */
public class TableImplementation_JML_Test
     extends TableImplementation_JML_TestData
{
    /** Initialize this class. */
    public TableImplementation_JML_Test(java.lang.String name) {
        super(name);
    }

    /** Run the tests. */
    public static void main(java.lang.String[] args) {
        org.jmlspecs.jmlunit.JMLTestRunner.run(suite());
        // You can also use a JUnit test runner such as:
        // junit.textui.TestRunner.run(suite());
    }

    /** Test to see if the code for class TableImplementation
     * has been compiled with runtime assertion checking (i.e., by jmlc).
     * Code that is not compiled with jmlc would not make an effective test,
     * since no assertion checking would be done. */
    public void test$IsRACCompiled() {
        junit.framework.Assert.assertTrue("code for class TableImplementation"
                + " was not compiled with jmlc"
                + " so no assertions will be checked!",
            org.jmlspecs.jmlrac.runtime.JMLChecker.isRACCompiled(TableImplementation.class)
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
        TableImplementation_JML_Test testobj
            = new TableImplementation_JML_Test("TableImplementation_JML_Test");
        junit.framework.TestSuite testsuite = testobj.overallTestSuite();
        // Add instances of Test found by the reflection mechanism.
        testsuite.addTestSuite(TableImplementation_JML_Test.class);
        testobj.addTestSuiteForEachMethod(testsuite);
        return testsuite;
    }

    /** A JUnit test object that can run a single test method.  This
     * is defined as a nested class solely for convenience; it can't
     * be defined once and for all because it must subclass its
     * enclosing class.
     */
    protected static abstract class OneTest extends TableImplementation_JML_Test {

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
     * TableImplementation.  The framework will then run them.
     * @param overallTestSuite$ The suite accumulating all of the tests
     * for this driver class.
     */
    //@ requires overallTestSuite$ != null;
    public void addTestSuiteForEachMethod
        (junit.framework.TestSuite overallTestSuite$)
    {
        try {
            this.addTestSuiteFor$TestTableImplementation(overallTestSuite$);
        } catch (java.lang.Throwable ex) {
            overallTestSuite$.addTest
                (new org.jmlspecs.jmlunit.strategies.ConstructorFailed(ex));
        }
        try {
            this.addTestSuiteFor$TestIsUsedIndex(overallTestSuite$);
        } catch (java.lang.Throwable ex) {
            overallTestSuite$.addTest
                (new org.jmlspecs.jmlunit.strategies.ConstructorFailed(ex));
        }
        try {
            this.addTestSuiteFor$TestAddEntry(overallTestSuite$);
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
            this.addTestSuiteFor$TestMapTo(overallTestSuite$);
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

    /** Add tests for the TableImplementation contructor
     * to the overall test suite. */
    private void addTestSuiteFor$TestTableImplementation
        (junit.framework.TestSuite overallTestSuite$)
    {
        junit.framework.TestSuite methodTests$
            = this.emptyTestSuiteFor("TableImplementation");
        try {
            methodTests$.addTest
                (new TestTableImplementation());
        } catch (org.jmlspecs.jmlunit.strategies.TestSuiteFullException e$) {
            // methodTests$ doesn't want more tests
        }
        overallTestSuite$.addTest(methodTests$);
    }

    /** Test for the TableImplementation contructor. */
    protected static class TestTableImplementation extends OneTest {

        /** Initialize this instance. */
        public TestTableImplementation() {
            super("TableImplementation");
        }

        protected void doCall() throws java.lang.Throwable {
            new TableImplementation();
        }

        protected java.lang.String failMessage
            (org.jmlspecs.jmlrac.runtime.JMLAssertionError e$)
        {
            java.lang.String msg = "\n\tContructor 'TableImplementation'";
            return msg;
        }
    }

    /** Add tests for the isUsedIndex method
     * to the overall test suite. */
    private void addTestSuiteFor$TestIsUsedIndex
        (junit.framework.TestSuite overallTestSuite$)
    {
        junit.framework.TestSuite methodTests$
            = this.emptyTestSuiteFor("isUsedIndex");
        try {
            org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                receivers$iter
                = new org.jmlspecs.jmlunit.strategies.NonNullIteratorDecorator
                    (this.vorg_jmlspecs_samples_table_TableImplementationIter("isUsedIndex", 1));
            this.check_has_data
                (receivers$iter,
                 "new NonNullIteratorDecorator(this.vorg_jmlspecs_samples_table_TableImplementationIter(\"isUsedIndex\", 1))");
            while (!receivers$iter.atEnd()) {
                org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                    vorg_jmlspecs_models_JMLType$1$iter
                    = this.vorg_jmlspecs_models_JMLTypeIter("isUsedIndex", 0);
                this.check_has_data
                    (vorg_jmlspecs_models_JMLType$1$iter,
                     "this.vorg_jmlspecs_models_JMLTypeIter(\"isUsedIndex\", 0)");
                while (!vorg_jmlspecs_models_JMLType$1$iter.atEnd()) {
                    final org.jmlspecs.samples.table.TableImplementation receiver$
                        = (org.jmlspecs.samples.table.TableImplementation) receivers$iter.get();
                    final org.jmlspecs.models.JMLType d
                        = (org.jmlspecs.models.JMLType) vorg_jmlspecs_models_JMLType$1$iter.get();
                    methodTests$.addTest
                        (new TestIsUsedIndex(receiver$, d));
                    vorg_jmlspecs_models_JMLType$1$iter.advance();
                }
                receivers$iter.advance();
            }
        } catch (org.jmlspecs.jmlunit.strategies.TestSuiteFullException e$) {
            // methodTests$ doesn't want more tests
        }
        overallTestSuite$.addTest(methodTests$);
    }

    /** Test for the isUsedIndex method. */
    protected static class TestIsUsedIndex extends OneTest {
        /** The receiver */
        private org.jmlspecs.samples.table.TableImplementation receiver$;
        /** Argument d */
        private org.jmlspecs.models.JMLType d;

        /** Initialize this instance. */
        public TestIsUsedIndex(org.jmlspecs.samples.table.TableImplementation receiver$, org.jmlspecs.models.JMLType d) {
            super("isUsedIndex"+ ":" + (d==null? "null" :"{org.jmlspecs.models.JMLType}"));
            this.receiver$ = receiver$;
            this.d = d;
        }

        protected void doCall() throws java.lang.Throwable {
            receiver$.isUsedIndex(d);
        }

        protected java.lang.String failMessage
            (org.jmlspecs.jmlrac.runtime.JMLAssertionError e$)
        {
            java.lang.String msg = "\n\tMethod 'isUsedIndex' applied to";
            msg += "\n\tReceiver: " + this.receiver$;
            msg += "\n\tArgument d: " + this.d;
            return msg;
        }
    }

    /** Add tests for the addEntry method
     * to the overall test suite. */
    private void addTestSuiteFor$TestAddEntry
        (junit.framework.TestSuite overallTestSuite$)
    {
        junit.framework.TestSuite methodTests$
            = this.emptyTestSuiteFor("addEntry");
        try {
            org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                receivers$iter
                = new org.jmlspecs.jmlunit.strategies.NonNullIteratorDecorator
                    (this.vorg_jmlspecs_samples_table_TableImplementationIter("addEntry", 1));
            this.check_has_data
                (receivers$iter,
                 "new NonNullIteratorDecorator(this.vorg_jmlspecs_samples_table_TableImplementationIter(\"addEntry\", 1))");
            while (!receivers$iter.atEnd()) {
                org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                    vorg_jmlspecs_samples_table_Entry$1$iter
                    = this.vorg_jmlspecs_samples_table_EntryIter("addEntry", 0);
                this.check_has_data
                    (vorg_jmlspecs_samples_table_Entry$1$iter,
                     "this.vorg_jmlspecs_samples_table_EntryIter(\"addEntry\", 0)");
                while (!vorg_jmlspecs_samples_table_Entry$1$iter.atEnd()) {
                    final org.jmlspecs.samples.table.TableImplementation receiver$
                        = (org.jmlspecs.samples.table.TableImplementation) receivers$iter.get();
                    final org.jmlspecs.samples.table.Entry e
                        = (org.jmlspecs.samples.table.Entry) vorg_jmlspecs_samples_table_Entry$1$iter.get();
                    methodTests$.addTest
                        (new TestAddEntry(receiver$, e));
                    vorg_jmlspecs_samples_table_Entry$1$iter.advance();
                }
                receivers$iter.advance();
            }
        } catch (org.jmlspecs.jmlunit.strategies.TestSuiteFullException e$) {
            // methodTests$ doesn't want more tests
        }
        overallTestSuite$.addTest(methodTests$);
    }

    /** Test for the addEntry method. */
    protected static class TestAddEntry extends OneTest {
        /** The receiver */
        private org.jmlspecs.samples.table.TableImplementation receiver$;
        /** Argument e */
        private org.jmlspecs.samples.table.Entry e;

        /** Initialize this instance. */
        public TestAddEntry(org.jmlspecs.samples.table.TableImplementation receiver$, org.jmlspecs.samples.table.Entry e) {
            super("addEntry"+ ":" + (e==null? "null" :"{org.jmlspecs.samples.table.Entry}"));
            this.receiver$ = receiver$;
            this.e = e;
        }

        protected void doCall() throws java.lang.Throwable {
            receiver$.addEntry(e);
        }

        protected java.lang.String failMessage
            (org.jmlspecs.jmlrac.runtime.JMLAssertionError e$)
        {
            java.lang.String msg = "\n\tMethod 'addEntry' applied to";
            msg += "\n\tReceiver: " + this.receiver$;
            msg += "\n\tArgument e: " + this.e;
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
                    (this.vorg_jmlspecs_samples_table_TableImplementationIter("removeEntry", 1));
            this.check_has_data
                (receivers$iter,
                 "new NonNullIteratorDecorator(this.vorg_jmlspecs_samples_table_TableImplementationIter(\"removeEntry\", 1))");
            while (!receivers$iter.atEnd()) {
                org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                    vorg_jmlspecs_models_JMLType$1$iter
                    = this.vorg_jmlspecs_models_JMLTypeIter("removeEntry", 0);
                this.check_has_data
                    (vorg_jmlspecs_models_JMLType$1$iter,
                     "this.vorg_jmlspecs_models_JMLTypeIter(\"removeEntry\", 0)");
                while (!vorg_jmlspecs_models_JMLType$1$iter.atEnd()) {
                    final org.jmlspecs.samples.table.TableImplementation receiver$
                        = (org.jmlspecs.samples.table.TableImplementation) receivers$iter.get();
                    final org.jmlspecs.models.JMLType d
                        = (org.jmlspecs.models.JMLType) vorg_jmlspecs_models_JMLType$1$iter.get();
                    methodTests$.addTest
                        (new TestRemoveEntry(receiver$, d));
                    vorg_jmlspecs_models_JMLType$1$iter.advance();
                }
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
        private org.jmlspecs.samples.table.TableImplementation receiver$;
        /** Argument d */
        private org.jmlspecs.models.JMLType d;

        /** Initialize this instance. */
        public TestRemoveEntry(org.jmlspecs.samples.table.TableImplementation receiver$, org.jmlspecs.models.JMLType d) {
            super("removeEntry"+ ":" + (d==null? "null" :"{org.jmlspecs.models.JMLType}"));
            this.receiver$ = receiver$;
            this.d = d;
        }

        protected void doCall() throws java.lang.Throwable {
            receiver$.removeEntry(d);
        }

        protected java.lang.String failMessage
            (org.jmlspecs.jmlrac.runtime.JMLAssertionError e$)
        {
            java.lang.String msg = "\n\tMethod 'removeEntry' applied to";
            msg += "\n\tReceiver: " + this.receiver$;
            msg += "\n\tArgument d: " + this.d;
            return msg;
        }
    }

    /** Add tests for the mapTo method
     * to the overall test suite. */
    private void addTestSuiteFor$TestMapTo
        (junit.framework.TestSuite overallTestSuite$)
    {
        junit.framework.TestSuite methodTests$
            = this.emptyTestSuiteFor("mapTo");
        try {
            org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                receivers$iter
                = new org.jmlspecs.jmlunit.strategies.NonNullIteratorDecorator
                    (this.vorg_jmlspecs_samples_table_TableImplementationIter("mapTo", 1));
            this.check_has_data
                (receivers$iter,
                 "new NonNullIteratorDecorator(this.vorg_jmlspecs_samples_table_TableImplementationIter(\"mapTo\", 1))");
            while (!receivers$iter.atEnd()) {
                org.jmlspecs.jmlunit.strategies.IndefiniteIterator
                    vorg_jmlspecs_models_JMLType$1$iter
                    = this.vorg_jmlspecs_models_JMLTypeIter("mapTo", 0);
                this.check_has_data
                    (vorg_jmlspecs_models_JMLType$1$iter,
                     "this.vorg_jmlspecs_models_JMLTypeIter(\"mapTo\", 0)");
                while (!vorg_jmlspecs_models_JMLType$1$iter.atEnd()) {
                    final org.jmlspecs.samples.table.TableImplementation receiver$
                        = (org.jmlspecs.samples.table.TableImplementation) receivers$iter.get();
                    final org.jmlspecs.models.JMLType d
                        = (org.jmlspecs.models.JMLType) vorg_jmlspecs_models_JMLType$1$iter.get();
                    methodTests$.addTest
                        (new TestMapTo(receiver$, d));
                    vorg_jmlspecs_models_JMLType$1$iter.advance();
                }
                receivers$iter.advance();
            }
        } catch (org.jmlspecs.jmlunit.strategies.TestSuiteFullException e$) {
            // methodTests$ doesn't want more tests
        }
        overallTestSuite$.addTest(methodTests$);
    }

    /** Test for the mapTo method. */
    protected static class TestMapTo extends OneTest {
        /** The receiver */
        private org.jmlspecs.samples.table.TableImplementation receiver$;
        /** Argument d */
        private org.jmlspecs.models.JMLType d;

        /** Initialize this instance. */
        public TestMapTo(org.jmlspecs.samples.table.TableImplementation receiver$, org.jmlspecs.models.JMLType d) {
            super("mapTo"+ ":" + (d==null? "null" :"{org.jmlspecs.models.JMLType}"));
            this.receiver$ = receiver$;
            this.d = d;
        }

        protected void doCall() throws java.lang.Throwable {
            receiver$.mapTo(d);
        }

        protected java.lang.String failMessage
            (org.jmlspecs.jmlrac.runtime.JMLAssertionError e$)
        {
            java.lang.String msg = "\n\tMethod 'mapTo' applied to";
            msg += "\n\tReceiver: " + this.receiver$;
            msg += "\n\tArgument d: " + this.d;
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
                    (this.vorg_jmlspecs_samples_table_TableImplementationIter("toString", 0));
            this.check_has_data
                (receivers$iter,
                 "new NonNullIteratorDecorator(this.vorg_jmlspecs_samples_table_TableImplementationIter(\"toString\", 0))");
            while (!receivers$iter.atEnd()) {
                final org.jmlspecs.samples.table.TableImplementation receiver$
                    = (org.jmlspecs.samples.table.TableImplementation) receivers$iter.get();
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
        private org.jmlspecs.samples.table.TableImplementation receiver$;

        /** Initialize this instance. */
        public TestToString(org.jmlspecs.samples.table.TableImplementation receiver$) {
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
