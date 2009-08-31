#!/bin/bash

#JAVA_HOME=/usr/local/lib/jvm/java-1.4.2-sun-1.4.2.18
#JAVA_HOME=/usr/lib/jvm/java-6-sun-1.6.0.14
JAVA_HOME=/usr/lib/jvm/ia32-java-6-sun-1.6.0.14

JML_HOME=/usr/local/JML/5.5
#JML_HOME=/usr/local/JML/5.6_rc4

PATH=${JML_HOME}/bin:${JAVA_HOME}/bin:${PATH}
export PATH


source="src"
#jml_model_libs="${JML_HOME}/bin/jmlmodelsnonrac.jar"
#jml_model_libs="${JML_HOME}/bin/jmlmodels.jar"
#jml_runtime_libs="${JML_HOME}/bin/runtime.jar"
jml_release_libs="${JML_HOME}/bin/jml-release.jar"
jml_libs="${jml_release_libs}"

libs="../main-project/lib/ftd2xxj.jar:../main-project/lib/javax.util.property.jar"

compile="src/ie/ucd/sensetile/util/BytePattern.java src/ie/ucd/sensetile/sensorboard/Frame.java src/ie/ucd/sensetile/sensorboard/Driver.java src/ie/ucd/sensetile/sensorboard/Packet.java src/ie/ucd/sensetile/sensorboard/PacketInputStream.java src/ie/ucd/sensetile/sensorboard/SenseTileException.java src/ie/ucd/sensetile/sensorboard/Frame.refines-java src/ie/ucd/sensetile/sensorboard/Packet.refines-java"



debug="--debug"
assignable="--assignable"
recursive="--recursive"
source_code="--source 1.4"
warning="--warning 2"
print_source="--print"
destination="--destination jml-generated"

source_path="--sourcepath src"
classpath="--classpath ${libs}:${jml_libs}"

arguments="${debug} ${source_code} ${assignable} ${recursive} ${warning} ${print_source} ${classpath} ${source_path} ${destination} ${compile}" 

echo ${arguments}

${JML_HOME}/bin/jmlc-unix ${arguments}
