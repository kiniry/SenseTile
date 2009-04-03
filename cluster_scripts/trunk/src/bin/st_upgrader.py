# -*- coding: utf-8 -*-

import subprocess

UPDATE_COMMAND = "apt-get update"
AVAILABLE_UPGRADE_COMMAND = "apt-get upgrade --assume-yes --simulate"
UPGRADE_COMMAND = "apt-get upgrade --assume-yes"


class Upgrader():
    """
    """
    
    def __init__(self, server_name):
        """
        """
        
        self.server_name = server_name

    def update(self):
        """
        """
        
        p = subprocess.Popen(self.__prepare_update_command(), shell = False, stdout = subprocess.PIPE, stderr = subprocess.PIPE)
        [out, err] = p.communicate()
        returncode = p.returncode
        if returncode != 0:
            print returncode
            print err
        print len(out)

    def available_upgrade(self):
        """
        """
        
        p = subprocess.Popen(self.__prepare_available_upgrade_command())
        [out, err] = p.communicate()
        returncode = p.returncode
        if returncode != 0:
            raise CalledProcessError, [returncode, err]
        return out

    def available_upgrade(self):
        """
        """
        
        p = subprocess.Popen(self.__prepare_upgrade_command())
        [out, err] = p.communicate()
        returncode = p.returncode
        if returncode != 0:
            raise CalledProcessError, [returncode, err]

    def __prepare_update_command(self):
        """
        """
        
        return self.__prepare_command( UPDATE_COMMAND )

    def __prepare_available_upgrade_command(self):
        """
        """
        
        return self.__prepare_command( AVAILABLE_UPGRADE_COMMAND )

    def __prepare_upgrade_command(self):
        """
        """
        
        return self.__prepare_command( UPGRADE_COMMAND )

    def __prepare_command(self, command):
        """
        """

        return ["ssh", "root@" + self.server_name, command]
