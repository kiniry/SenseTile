# -*- coding: utf-8 -*-

import os.path, shutil

class DirectoryHelper():
    """
    Provides helper method(s) for directory manipulation.      
    """

    def __init__(self):
        pass
        
    def mk_dir(self, path):
        return self.__mk_dir(path)
    
    def __mk_dir(self, path):
        """
        Represents an create directory method. 
        - already exists, silently complete.
        - regular file in the way, raise an exception.
        - parent directory(ies) does not exist, make them as well.
        """
        
        if path == None:
            pass
        elif self.is_dir(path):
            pass
        elif self.is_file(path):
            raise OSError("A file with the same name as the desired " \
                      "dir, '%s', already exists." % path)
        else: # create directory
            head, tail = os.path.split(path)
            if head and not os.path.isdir(head):
                self.__mk_dir(head)
           
            if tail:
                os.mkdir(path)
                
    def is_dir(self, path):
        return os.path.isdir("/" + path)
    
    def is_file(self, path):
        return os.path.isfile("/" + path)
    
    def get_dir_path(self, path):
        """
         Returns dir path
        """
        temp_path = os.getcwd() #represents current working directory path
        
        if (path != None) and (path != "") and (path != "."):
            temp_path += path
            
        return temp_path
    
    def rm_dir(self, dir_name):
        # remove directory from current test directory
        shutil.rmtree(dir_name);
