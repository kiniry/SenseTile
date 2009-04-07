# -*- coding: utf-8 -*-

import unittest
import st_upgrader

class UpgraderTestCase(unittest.TestCase):
    """
    """

    def test_create(self):
        """
        """
        
        upgrader = st_upgrader.Upgrader("zeta.ucd.ie")
        self.failIfEqual(None, upgrader)
    
    def test_update(self):
        """
        """
       
        upgrader = st_upgrader.Upgrader("zeta.ucd.ie")
        upgrader.update()
    
    def test_available_upgrade(self):
        """
        """
       
        upgrader = st_upgrader.Upgrader("zeta.ucd.ie")
        available_upgrade_report = upgrader.available_upgrade()
        self.failIfEqual(None, available_upgrade_report)
    
    def test__prepare_command(self):
        """
        """
        
        upgrader = st_upgrader.Upgrader("zeta.ucd.ie")
        built_command = upgrader._Upgrader__prepare_command("ls -l")
        expected_command = ["ssh", "root@zeta.ucd.ie", "ls -l"]
        self.failUnlessEqual(expected_command, built_command)


def suite():
    return unittest.makeSuite([UpgraderTestCase])

if __name__ == '__main__':
    unittest.main()
