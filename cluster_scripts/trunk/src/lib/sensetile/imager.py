# -*- coding: utf-8 -*-

import string

from sensetile import executor

IMAGE_INCREMENTAL_UPDATE_COMMAND = \
    ["si_updateclient", string.Template('--server ${server}'), "--yes"]
IMAGE_COMPLETE_UPDATE_COMMAND = \
    ["si_updateclient", string.Template('--server ${server}'), "--yes", "--autoinstall"]
PUSH_OVERRIDES_COMMAND = ["si_pushoverrides", string.Template('${target}')]
UPDATE_GRUB_MENU_COMMAND = [
    "unset UCF_FORCE_CONFFOLD; " + 
    "export UCF_FORCE_CONFFNEW=YES; " + 
    "ucf --purge /var/run/grub/menu.lst; " + 
    "update-grub"]
REBOOT_COMMAND = ["shutdown", "-r", "now"]

class Imager():
    """
    """
    
    def __init__(self, image_server_name, executor_class = executor.Executor):
        """
        """
        
        self.image_server_name = image_server_name
        self.executor_class = executor_class
    
    def image(self, target_name, autoinstall = False, reboot = False):
        """
        image the target machine
        """
        
        if autoinstall:
            self._image_complete(target_name)
        else:
            self._image_incremental(target_name, reboot)
    
    def _image_incremental(self, target_name, reboot):
        self._image_incremental_update(target_name)
        self._push_overrides(target_name)
        self._update_grub_menu(target_name)
        if reboot:
            self._reboot(target_name)
    
    def _image_complete(self, target_name):
        self._image_complete_update(target_name)
        self._reboot(target_name)
    
    def _image_complete_update(self, target_name):
        command = IMAGE_COMPLETE_UPDATE_COMMAND[:]
        command[1] = command[1].substitute( server = _remove_domain(self.image_server_name) )
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

def _remove_domain(target_name):
    try:
        hostname = target_name[0:string.index(target_name, ".")]
    except ValueError:
        hostname = target_name
    return hostname
