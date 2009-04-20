# -*- coding: utf-8 -*-

import optparse
from sensetile import upgrader
from sensetile import backup_etc
from sensetile import imager

def call_upgrade(args):
    parser = optparse.OptionParser()
    parser.add_option(
        '--target', 
        action = 'store' , 
        dest = 'target',
        help = 'target machine for upgrade, required')
    parser.add_option(
        '--action', 
        action = 'store' , 
        dest = 'action', 
        default = 'available_upgrade', 
        help = 'action to be performed: {available_upgrade, upgrade}, default = available_upgrade')
    (options, args) = parser.parse_args(args)
    if (not options.target):
        print parser.print_help()
        exit(-1)
    
    upg = upgrader.Upgrader(options.target)
    if options.action == 'available_upgrade':
        upg.update()
        print upg.available_upgrade()
    elif options.action == 'upgrade':
        upg.update()
        upg.upgrade()
    else:
        print parser.print_help()
        exit(-1)

def call_backup_etc(args):
    parser = optparse.OptionParser()
    parser.add_option(
        '--target', 
        action = 'store' , 
        dest = 'target', 
        help = 'target machine for backup, required')
    parser.add_option(
        '--directory', 
        action = 'store' , 
        dest = 'directory', 
        help = 'directory for backup, required')
    (options, args) = parser.parse_args(args)
    if ((not options.target) or (not options.directory)):
        print parser.print_help()
        exit(-1)

    bcp = backup_etc.BackupEtc(options.target, options.directory)
    bcp.mk_dir()
    bcp.perform_backup_etc()

def call_image(args):
    parser = optparse.OptionParser()
    parser.add_option(
        '--server', 
        action = 'store' , 
        dest = 'server', 
        help = 'image server, required')
    parser.add_option(
        '--target', 
        action = 'store' , 
        dest = 'target', 
        help = 'machine to be imaged, required')
    parser.add_option(
        '--reboot', 
        action = 'store_true' , 
        dest = 'reboot', 
        default = False, 
        help = 'reboot after image, default = False')
    (options, args) = parser.parse_args(args)
    if ((not options.server) or (not options.target)):
        print parser.print_help()
        exit(-1)
    
    try:
        img = imager.Imager(options.server)
        img.image(options.target, reboot = options.reboot)
    except Exception, exception:
        print exception.err_str
        exit(exception.errorcode)
        
