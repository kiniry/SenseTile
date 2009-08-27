#!/bin/bash

#JAVA_HOME=/usr/local/lib/jvm/java-1.4.2-sun-1.4.2.18
#JAVA_HOME=/usr/lib/jvm/java-6-sun-1.6.0.14
JAVA_HOME=/usr/lib/jvm/ia32-java-6-sun-1.6.0.14

JML_HOME=/usr/local/JML/5.5
#JML_HOME=/usr/local/JML/5.6_rc4

PATH=${JML_HOME}/bin:${JAVA_HOME}/bin:${PATH}
export PATH


source="src"
#model_libs="${JML_HOME}/bin/jmlmodelsnonrac.jar"
#model_libs="${JML_HOME}/bin/jmlmodels.jar"
libs="../main-project/lib/ftd2xxj.jar:../main-project/lib/javax.util.property.jar"

debug="--debug"
assignable="--assignable"
recursive="--recursive"
source_code="--source 1.3"
destination="--destination jml-compiled"
source_path="--sourcepath src"
classpath="--classpath ${libs}:${model_libs}"

arguments="${debug} ${source_code} ${assignable} ${recursive} ${classpath} ${source_path} ${destination} ${source}/" 

echo ${arguments}

${JML_HOME}/bin/jmlc-unix ${arguments}
