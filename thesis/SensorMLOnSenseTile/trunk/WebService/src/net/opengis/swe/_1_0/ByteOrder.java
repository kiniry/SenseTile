//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.02.01 at 05:02:44 PM GMT 
//


package net.opengis.swe._1_0;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for byteOrder.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="byteOrder">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="bigEndian"/>
 *     &lt;enumeration value="littleEndian"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "byteOrder")
@XmlEnum
public enum ByteOrder {

    @XmlEnumValue("bigEndian")
    BIG_ENDIAN("bigEndian"),
    @XmlEnumValue("littleEndian")
    LITTLE_ENDIAN("littleEndian");
    private final String value;

    ByteOrder(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ByteOrder fromValue(String v) {
        for (ByteOrder c: ByteOrder.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}