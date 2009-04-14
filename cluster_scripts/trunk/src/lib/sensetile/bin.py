# -*- coding: utf-8 -*-

import optparse
from sensetile import upgrader,backup_etc

def call_upgrade(args):
    parser = optparse.OptionParser()
    parser.add_option(
        '--target', 
        action = 'store' , 
        dest = 'target', 
        help='target machine')
    parser.add_option(
        '--action', 
        action = 'store' , 
        dest = 'action', 
        default = 'available_upgrade', 
        help='action to be performed')
    (options, args) = parser.parse_args(args)
    
    upg = upgrader.Upgrader(options.target)
    if options.action == 'available_upgrade':
        upg.update()
        print upg.available_upgrade()
    elif options.action == 'upgrade':
        upg.update()
        upg.upgrade()
    else:
        print parser.print_help()
        
def call_backup_etc(args):
    parser = optparse.OptionParser()
    parser.add_option(
        '--target', 
        action = 'store' , 
        dest = 'target', 
        help='target machine for backup')
    parser.add_option(
        '--d', 
        action = 'store' , 
        dest = 'd', 
        help='directory for backup')
    (options, args) = parser.parse_args(args)
    bcp = backup_etc.BackupEtc(options.target, options.d)
    bcp.mk_dir()
    bcp.perform_backup_etc()
    
    
        
