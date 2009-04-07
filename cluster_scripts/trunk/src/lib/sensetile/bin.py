# -*- coding: utf-8 -*-

import optparse
from sensetile import upgrader

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
    print options
    print args
    
    upg = upgrader.Upgrader(options.target)
    if options.action == 'available_upgrade':
        upg.update()
        print upg.available_upgrade()
    elif options.action == 'upgrade':
        upg.update()
        upg.upgrade()
    else:
        print parser.print_help()
