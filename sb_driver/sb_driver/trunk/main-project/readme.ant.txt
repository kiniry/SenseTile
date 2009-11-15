
main-project/build-all
ant make file used to invokes all the targets in all the projects

the targets are self explanatory, some of the targets comes with a description 
(just to be sure) 


<project>/build.xml
every project has a build.xml ant make file, generated automatically by 
Eclipse


<project>/build-<purpose>.xml
custom user ant make files, included automatically by Eclipse, if starting 
with <?eclipse.ant.import?>

actually the custom purposes are: 
 - jml: jml2 tools targets
 - checkstyle: checkstyle targets
 - release: release targets


main-project/configuration/build-checkstyle-common.xml
ant make file containing common definitions to be used by custom 
<project>/build-checkstyle.xml files


main-project/configuration/checkstyle.SenseTile.xml
general checkstyle configuration


main-project/configuration/checkstyle.SenseTile-Test.xml
general checkstyle for tests configuration


<project>/build-release.xml
the release targets are used to assemble the release, the release is located into 
main-project


main-project/release/build-release-common.xml 
ant make file containing common definitions to be used by custom 
<project>/build-release.xml files


