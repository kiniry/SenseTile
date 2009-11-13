
<project>/build.xml
every project has a build.xml ant make file, generated automatically by 
Eclipse


<project>/build-<purpose>.xml
custom user ant make files, included automatically by Eclipse, if starting 
with <?eclipse.ant.import?>

actually the custom purposes are: 
 - jml: targets of jml2 tools
 - release: release targets


<project>/build-release.xml
the release targets are used to assemble the release, the release is located into 
main-project


main-project/release/build-release-common.xml 
ant make file containing common definitions to be used by custom 
<project>/build-release.xml files


main-project/build-all
ant make file used to invokes all the targets in all the projects

