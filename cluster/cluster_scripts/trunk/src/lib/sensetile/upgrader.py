# -*- coding: utf-8 -*-
from sensetile import executor

UPDATE_COMMAND = ["apt-get","update"]
AVAILABLE_UPGRADE_COMMAND = ["apt-get","upgrade","--assume-yes","--simulate"]
UPGRADE_COMMAND = ["apt-get","upgrade","--assume-yes"]


class Upgrader():
    """
    Check or install available upgrades on Ubuntu boxes.
    Prerequisite: ssh able to access to root with key authentication.
    """
    
    def __init__(self, server_name, executor_class = executor.Executor):
        """
        """
        self.server_name = server_name
        self.executor_class = executor_class

    def update(self):
        """
        """
        e = self.executor_class(UPDATE_COMMAND)
        e.ssh_run(self.server_name, "root", check = True)

    def available_upgrade(self):
        """
        """
        e = self.executor_class(AVAILABLE_UPGRADE_COMMAND)
        return e.ssh_run(self.server_name, "root", check = True)[1]

    def upgrade(self):
        """
        """
        e = self.executor_class(UPGRADE_COMMAND)
        e.ssh_run(self.server_name, "root", check = True)
