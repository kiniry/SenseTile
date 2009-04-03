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
    
#     def test_available_upgrade(self):
#         """
#         """
#        
#         upgrader = st_upgrader.Upgrader("zeta.ucd.ie")
#         available_upgrade_report = upgrader.available_upgrade()
#         self.failIfEqual(None, available_upgrade_report)
    
    def test__prepare_update_command(self):
        """
        """
        
        upgrader = st_upgrader.Upgrader("zeta.ucd.ie")
        built_command = upgrader._Upgrader__prepare_update_command()
        expected_command = ["ssh", "root@zeta.ucd.ie", "apt-get update"]
        self.failUnlessEqual(expected_command, built_command)

    def test__prepare_available_upgrade_command(self):
        """
        """
        
        upgrader = st_upgrader.Upgrader("zeta.ucd.ie")
        built_command = upgrader._Upgrader__prepare_available_upgrade_command()
        expected_command = ["ssh", "root@zeta.ucd.ie", "apt-get upgrade --assume-yes --simulate"]
        self.failUnlessEqual(expected_command, built_command)

    def test__prepare_upgrade_command(self):
        """
        """
        
        upgrader = st_upgrader.Upgrader("zeta.ucd.ie")
        built_command = upgrader._Upgrader__prepare_upgrade_command()
        expected_command = ["ssh", "root@zeta.ucd.ie", "apt-get upgrade --assume-yes"]
        self.failUnlessEqual(expected_command, built_command)


def suite():
    return unittest.makeSuite([UpgraderTestCase])

if __name__ == '__main__':
    unittest.main()
