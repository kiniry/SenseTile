# -*- coding: utf-8 -*-

import subprocess
import dir_helper
import os
import time
from time import strftime

from sensetile import executor

#backup parameters needed in process of command building.
BACKUP_PARAMETERS = ["-aHISzv",
                     "--numeric-ids" ,
                     "--rsh=\'ssh -x -o UserKnownHostsFile=/dev/null -o StrictHostKeyChecking=no -o BatchMode=yes -l root\'"]

#date and time representation YY_MM_DD_HH_mm
FORMAT_DATE_TIME = "%Y_%m_%d_%H_%M"

#defered class Backup

class BackupEtc():
    """
    This class can be used to create backup copies 
    of files of etc directory to desired directory path.    
    """

    def __init__(self, server_name, path, executor_class = executor.Executor):
        """
        Main constructor.
        Input parameter: server_name (for example pi.ucd.ie).
        Input parameter: path        (for example test/test1/test2 ).
        """
        self.server_name = server_name
        self.executor_class = executor_class
        self.path = path

        self.dir_helper = dir_helper.DirectoryHelper()# see DirectoryHelper
  
    def mk_dir(self):
        return self.dir_helper.mk_dir(self.path)        
   
    def perform_backup_etc(self):
        """
        Perform backup_etc operation
        """
        e = self.executor_class(self.__prepare_command())
        return e.run(check = True)[1]


    def __create_backup_file_name(self):
        """
        Create backup file name. 
        """
        
        formated_date_time_part = strftime(FORMAT_DATE_TIME)
        server_name_part = self.server_name.split('.')[0]
        return server_name_part +"_etc_backup-"+ formated_date_time_part
                    
    
    def __prepare_command(self):
        """
         Prepare command for execution.
        """
        
        return ["rsync",BACKUP_PARAMETERS[0],
                        BACKUP_PARAMETERS[1],
                        BACKUP_PARAMETERS[2],
                        self.server_name + ":/etc",
                        self.dir_helper.get_dir_path(self.path) + "/" +self.__create_backup_file_name()]
