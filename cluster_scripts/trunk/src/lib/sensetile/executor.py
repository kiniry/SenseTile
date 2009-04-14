# -*- coding: utf-8 -*-

import subprocess


class ExecutorError(Exception):
    pass

class CommandFailedError(ExecutorError):
    
    def __init__(self, errorcode, err_str, message = ""):
        self.errorcode = errorcode
        self.err_str = err_str
        self.message = message
    
    def __str__(self):
        return str(self.errorcode) + " : " + self.err_str + "\n" + self.message

class ExecutionError(ExecutorError):
    
    def __init__(self, exception, message = ""):
        self.exception = exception
        self.message = message
    
    def __str__(self):
        return str(self.exception) + "\n" + self.message


class Executor():
    
    def __init__(self, command_and_parameters):
        """
        Prepare a command to be executed.
        The command should be given as a list of str: the first element is the command, the 
        subsequent elements are the paramenters.
        """
        
        self.command_and_parameters = command_and_parameters
    
    def run(self, check = False):
        """
        Execute the command. Returns a tuple: [error_code, output_str, error_str].
        Note that output_str and error_str are str.
        If check = True checks for error and raises ExecutorError if any erro is found.
        """
        
        return self.__run(check)
    
    def __run(self, check):
        
        try:
            p = subprocess.Popen(
                self.command_and_parameters, 
                shell = False, 
                stdout = subprocess.PIPE, stderr = subprocess.PIPE)
            (out, err) = p.communicate()
            returncode = p.returncode
        except Exception, exception:
            if check:
                raise ExecutionError, exception
            else:
                raise exception
        if check and (returncode != 0):
            raise CommandFailedError, (returncode, err)
        return (returncode, out, err)
