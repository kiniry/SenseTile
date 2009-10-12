# -*- coding: utf-8 -*-

import unittest,os, shutil
from sensetile import backup_etc
import time
from time import strftime

class BackupEtcTestCase(unittest.TestCase):
    
    def test_create(self):
        #Check if instance of backup is created.
        currentBackup = backup_etc.BackupEtc("zeta.ucd.ie",None)
        self.failIfEqual(None, currentBackup)
            
   
    def test__create_backup_file_name(self):
        # test validity of backup file name
        currentBackup = backup_etc.BackupEtc("zeta.ucd.ie","")
        built_name = currentBackup._BackupEtc__create_backup_file_name()
        expected_name = "zeta_etc_backup-" +strftime("%Y_%m_%d_%H_%M") 
        self.failUnlessEqual(expected_name,built_name)
        print built_name
        
    def test__prepare_command_current_dir(self):
        # test command for current dir        
        currentBackup = backup_etc.BackupEtc("zeta.ucd.ie",None)
        built_command = currentBackup._BackupEtc__prepare_command()
        expected_command = ["rsync",
                            "-aHISzv", 
                            "--numeric-ids",
                            "--rsh=\'ssh -x -o UserKnownHostsFile=/dev/null -o StrictHostKeyChecking=no -o BatchMode=yes -l root\'",
                            "zeta.ucd.ie:/etc",
                            os.getcwd() + "/zeta_etc_backup-" +strftime("%Y_%m_%d_%H_%M")]
        self.failUnlessEqual(expected_command, built_command)
        print built_command
        
    def test__prepare_command_existing_dir(self):
        # test command for existing dir
        currentBackup = backup_etc.BackupEtc("zeta.ucd.ie","Project/svn_sensetile")
        built_command = currentBackup._BackupEtc__prepare_command()
        expected_command = ["rsync",
                            "-aHISzv", 
                            "--numeric-ids",
                            "--rsh=\'ssh -x -o UserKnownHostsFile=/dev/null -o StrictHostKeyChecking=no -o BatchMode=yes -l root\'",
                            "zeta.ucd.ie:/etc",
                            os.getcwd() + "Project/svn_sensetile/zeta_etc_backup-" +strftime("%Y_%m_%d_%H_%M")]
        self.failUnlessEqual(expected_command, built_command)
        print built_command


def suite():
    return unittest.makeSuite([BackupEtcTestCase])

if __name__ == '__main__':
    unittest.main()
