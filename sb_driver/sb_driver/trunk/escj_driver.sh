#!/bin/bash

JAVA_HOME="/usr/local/lib/jvm/java-1.4.2-sun"
CLASSPATH="/usr/local/KindSoftware/ESCJava/2.0.5/specs:spec:src"
PATH="${JAVA_HOME}/bin:${PATH}"

#escj -Specs /usr/local/KindSoftware/ESCJava/2.0.5/specs:spec -ClassPath src src/ie/ucd/sensetile/sensorboard/PacketInputStream.java

#escj -Specs /usr/local/KindSoftware/ESCJava/2.0.5/specs:spec -ClassPath src spec/ie/ucd/sensetile/sensorboard/PacketInputStream.jml

#escj -Specs /usr/local/KindSoftware/ESCJava/2.0.5/specs:spec -ClassPath src src/ie/ucd/sensetile/sensorboard/PacketInputStream.java

#escj -ClassPath /usr/local/KindSoftware/ESCJava/2.0.5/specs:spec:src spec/ie/ucd/sensetile/sensorboard/PacketInputStream.jml

#escj -Specs spec/lib/specs-java1.4:spec/src -ClassPath spec/lib:spec/src:src src/ie/ucd/sensetile/sensorboard/PacketInputStream.java
escj -Specs spec/lib/specs-java1.4:spec/src -ClassPath spec/lib:spec/src:src src/ie/ucd/sensetile/sensorboard/driver/InputStreamPacketInputStream.java
