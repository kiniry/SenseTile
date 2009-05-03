# -*- coding: utf-8 -*-

import unittest
from sensetile import executor

class ExecutorTestCase(unittest.TestCase):
    """
    """
    
    def test_create(self):
        e = executor.Executor("ls")
        self.failIfEqual(None, e)
    
    def test_run_command(self):
        e = executor.Executor(["ls", "-l", "-A"])
        result = e.run()
        self.failIfEqual(None, result)
        self.failUnlessEqual(3, len(result))
    
    def test_run_successful_command(self):
        e = executor.Executor(["ls", "-l", "-A"])
        result = e.run()
        [errorcode, out_str, error_str] = result
        self.failUnlessEqual(0, errorcode)
        self.failIfEqual("", out_str)
        self.failUnlessEqual("", error_str)
    
    def test_run_failure_command(self):
        e = executor.Executor(["ls", "--lxzzz", "-A"])
        result = e.run()
        [errorcode, out_str, error_str] = result
        self.failIfEqual(0, errorcode)
        self.failUnlessEqual("", out_str)
        self.failIfEqual("", error_str)

    def test_run_nonexistent_command(self):
        e = executor.Executor(["lsxxzzss", "--lxzzz", "-A"])
        self.assertRaises(OSError, e.run)

    def test_run_checked_failure_command(self):
        e = executor.Executor(["ls", "--lxzzz", "-A"])
        self.assertRaises(executor.CommandFailedError, e.run, check = True)

    def test_run_checked_nonexistent_command(self):
        e = executor.Executor(["lsxxzzss", "--lxzzz", "-A"])
        self.assertRaises(executor.ExecutionError, e.run, check = True)

    def test_concatenate(self):
        e = executor.Executor([])
        self.assertEqual("abba b", e._Executor__concatenate(["abba", "b"]))
    
    def test__concatenate_empty_list(self):
        e = executor.Executor([])
        self.assertEqual("", e._Executor__concatenate([]))
    
    def test__concatenate_empty_parameter(self):
        e = executor.Executor([])
        self.assertEqual("a b", e._Executor__concatenate(["a", "", "b"]))
        
    def test__build_ssh_command(self):
        e = executor.Executor(["-firstParameter","-secondParameter"])
        self.assertEqual(["ssh","root@alpha.ucd.ie","-firstParameter -secondParameter"], e._Executor__build_ssh_command("alpha.ucd.ie", "root"))

def suite():
    return unittest.makeSuite([ExecutorTestCase])

if __name__ == '__main__':
    unittest.main()
