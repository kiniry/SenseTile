# -*- coding: utf-8 -*-

import unittest, os, shutil
from sensetile import dir_helper

class DirectoryHelperTestCase(unittest.TestCase):
    
    def test_create(self):
        #Check if instance of helper is created.
        dirHelper = dir_helper.DirectoryHelper()
        self.failIfEqual(None, dirHelper)
            
    def test__existing_dir(self):
        # test make existing dir
        dirHelper = dir_helper.DirectoryHelper()
        dirHelper.mk_dir("home")
        self.assertFalse(os.path.isdir(os.getcwd() + "home")) 
    
    def test__current_dir(self):
        # test make current dir        
        dirHelper = dir_helper.DirectoryHelper()
        dirHelper.mk_dir(".")
        built_path = dirHelper.get_dir_path(".")
        expected_path = os.getcwd()
        self.failUnlessEqual(expected_path,built_path)
    
    def test__new_dir(self):
        # test command for new created dir
        dirHelper = dir_helper.DirectoryHelper()
        dirHelper.mk_dir("mika/testGAGA")
        self.assertTrue(os.path.isdir(os.getcwd() + "/mika/testGAGA")) 
        # remove created directory from current test directory
        dirHelper.rm_dir("mika");        
        
    
    def test_malicious_dir(self):
        # test malicious dir - actually this is a file "bin/bash"
        dirHelper = dir_helper.DirectoryHelper()
        self.assertRaises(OSError,dirHelper.mk_dir,"bin/bash")
        


def suite():
    return unittest.makeSuite([DirectoryHelperTestCase])

if __name__ == '__main__':
    unittest.main()
