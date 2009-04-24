# -*- coding: utf-8 -*-

import string
import time

from sensetile import executor

IMAGE_CREATE_GET_COMMAND = [
    "si_getimage", 
    string.Template('--golden-client ${target}'), 
    string.Template('--image ${image}'),
    "--quiet", 
    "--update-script YES", 
    string.Template('--ip-assignment ${ip_assignment}'), # DHCP | REPLICANT
    "--post-install reboot"] 
IMAGE_CREATE_PREPARE_COMMAND = [
    "si_prepareclient", 
    string.Template('--server ${server}'), 
    "--yes"]
IMAGE_INCREMENTAL_UPDATE_COMMAND = [
    "si_updateclient", 
    string.Template('--server ${server}'), 
    "--yes"]
IMAGE_COMPLETE_UPDATE_COMMAND = [
    "si_updateclient", 
    string.Template('--server ${server}'), 
    "--yes", 
    "--autoinstall", 
    string.Template('--flavor ${flavor}') ]
PUSH_OVERRIDES_COMMAND = [
    "si_pushoverrides", 
    string.Template('${target}')]
UPDATE_GRUB_MENU_COMMAND = [
    "unset UCF_FORCE_CONFFOLD; " + 
    "export UCF_FORCE_CONFFNEW=YES; " + 
    "ucf --purge /var/run/grub/menu.lst; " + 
    "update-grub"]
REBOOT_COMMAND = ["shutdown", "-r", "now"]

FORMAT_DATE_TIME = "%Y_%m_%d_%H_%M"


class Imager():
    """
    """
    
    def __init__(self, image_server_name, executor_class = executor.Executor):
        """
        """
        
        self.image_server_name = image_server_name
        self.executor_class = executor_class
    
    def image(self, target_name, reboot = False, autoinstall = False, flavor = None):
        """
        install image in the target machine
        """
        
        if autoinstall:
            self._image_complete(target_name, flavor)
        else:
            self._image_incremental(target_name, reboot)
    
    def create_image(self, target_name, image_name = None, backup = False):
        """
        create image from the target machine
        """
        
        if not image_name:
            image_name = self._create_default_image_name(_remove_domain(target_name))
        if backup:
            ip_assignment = "REPLICANT"
        else:
            ip_assignment = "DHCP"
        self._prepare_image(target_name)
        self._get_image(target_name, image_name, ip_assignment)
    
    def _create_default_image_name(self, target_name, time_tuple = time.localtime()):
        return target_name + '.' + time.strftime(FORMAT_DATE_TIME, time_tuple)
    
    def _image_incremental(self, target_name, reboot):
        self._image_incremental_update(target_name)
        self._push_overrides(target_name)
        self._update_grub_menu(target_name)
        if reboot:
            self._reboot(target_name)
    
    def _image_complete(self, target_name, flavor):
        self._image_complete_update(target_name, flavor)
        self._reboot(target_name)
    
    def _image_complete_update(self, target_name, flavor):
        command = IMAGE_COMPLETE_UPDATE_COMMAND[:]
        command[1] = command[1].substitute( server = _remove_domain(self.image_server_name) )
        command[4] = command[4].substitute( flavor = flavor )
        e = self.executor_class(command)
        e.ssh_run(target_name, "root", check = True)
    
    def _image_incremental_update(self, target_name):
        command = IMAGE_INCREMENTAL_UPDATE_COMMAND[:]
        command[1] = command[1].substitute( server = _remove_domain(self.image_server_name) )
        e = self.executor_class(command)
        e.ssh_run(target_name, "root", check = True)
    
    def _push_overrides(self, target_name):
        command = PUSH_OVERRIDES_COMMAND[:]
        command[1] = command[1].substitute( target = _remove_domain(target_name) )
        e = self.executor_class(command)
        e.ssh_run(self.image_server_name, "root", check = True)
    
    def _update_grub_menu(self, target_name):
        command = UPDATE_GRUB_MENU_COMMAND[:]
        e = self.executor_class(command)
        e.ssh_run(target_name, "root", check = True)
    
    def _reboot(self, target_name):
        command = REBOOT_COMMAND[:]
        e = self.executor_class(command)
        e.ssh_run(target_name, "root", check = True)
    
    def _prepare_image(self, target_name):
        command = IMAGE_CREATE_PREPARE_COMMAND[:]
        command[1] = command[1].substitute( server = _remove_domain(self.image_server_name) )
        e = self.executor_class(command)
        e.ssh_run(target_name, "root", check = True)
    
    def _get_image(self, target_name, image_name, ip_assignment):
        command = IMAGE_CREATE_GET_COMMAND[:]
        command[1] = command[1].substitute( target = _remove_domain(target_name) )
        command[2] = command[2].substitute( image = image_name )
        command[5] = command[5].substitute( ip_assignment = ip_assignment )
        e = self.executor_class(command)
        e.ssh_run(self.image_server_name, "root", check = True)

def _remove_domain(target_name):
    try:
        hostname = target_name[0:string.index(target_name, ".")]
    except ValueError:
        hostname = target_name
    return hostname



# si_prepare
 #--config FILE
    #Where FILE contains all the settings necessary for the client to set
    #its hostname and configure its networking information without DHCP.
    #This file is copied to /local.cfg into the initrd.img.  See
    #/usr/share/doc/systemimager-server-4.1.6/local.cfg
    #for a well commented example.

# si_getimage
 #--overrides LIST
    #Where LIST is the comma separated list of the overrides that will be
    #transferred to the clients when they will be imaged.
 #--directory PATH
    #The full path and directory name where you want this image to be stored.
    #The directory bearing the image name itself will be placed inside the
    #directory specified here.
 #--exclude PATH
    #Don't pull the contents of PATH from the golden client. PATH must be
    #absolute (starting with a "/").  
    #To exclude a single file use:
        #--exclude /directoryname/filename
    #To exclude a directory and it's contents use:
        #--exclude /directoryname/
    #To exclude the contents of a directory, but pull the directory itself use:
        #--exclude "/directoryname/*"
 #--exclude-file FILE
    #Don't pull the PATHs specified in FILE from the golden client.
 #--autodetect-disks
    #Try to detect available disks on the client when installing instead of
    #using devices specified in autoinstallscript.conf.
