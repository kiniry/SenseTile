# -*- coding: utf-8 -*-

import subprocess

import executor

UPDATE_COMMAND = "apt-get update"
AVAILABLE_UPGRADE_COMMAND = "apt-get upgrade --assume-yes --simulate"
UPGRADE_COMMAND = "apt-get upgrade --assume-yes"


class Upgrader():
    """
    """
    
    def __init__(self, server_name, executor_class = executor.Executor):
        """
        """
        
        self.server_name = server_name
        self.executor_class = executor_class

    def update(self):
        """
        """
        
        e = self.executor_class(self.__prepare_command( UPDATE_COMMAND ))
        e.run(check = True)

    def available_upgrade(self):
        """
        """
        
        e = self.executor_class(self.__prepare_command( AVAILABLE_UPGRADE_COMMAND ))
        return e.run(check = True)[1]

    def upgrade(self):
        """
        """
        
        e = self.executor_class(self.__prepare_command( UPGRADE_COMMAND ))
        e.run(check = True)

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
