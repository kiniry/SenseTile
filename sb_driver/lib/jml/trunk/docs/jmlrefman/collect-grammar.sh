#!/bin/sh
# @(#) $Id: collect-grammar.sh 1199 2009-02-17 19:42:32Z smshaner $

sed -e '1,/^\@chapter Lexical Conventions/d' "$@" |
 sed -n -e '
	/^\@chapter/p
	/^\@iftex/p
	/^\@end iftex/p
	/^\@ifinfo/p
	/^\@end ifinfo/p
	/^\@var.*::=/p
	/^      *\[ /p
	/^      *\@var/p
	/^ *| /p' |
 awk 'BEGIN {print "@appendixsec Lexical Conventions\n@display\n"}
      {if ($1 == "@chapter") {
           $1 = "@appendixsec";
	   print "@end display\n";
	   print;
	   print "@display\n"; 
       }
       else
	   print 
      }
      END {print "@end display\n"}
     ' | sed -e '/^\@appendixsec Grammar Summary/,$d'
