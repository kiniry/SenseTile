//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.02.01 at 05:02:44 PM GMT 
//


package net.opengis.sos._1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for responseModeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="responseModeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="inline"/>
 *     &lt;enumeration value="attached"/>
 *     &lt;enumeration value="out-of-band"/>
 *     &lt;enumeration value="resultTemplate"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "responseModeType")
@XmlEnum
public enum ResponseModeType {

    @XmlEnumValue("inline")
    INLINE("inline"),
    @XmlEnumValue("attached")
    ATTACHED("attached"),
    @XmlEnumValue("out-of-band")
    OUT_OF_BAND("out-of-band"),
    @XmlEnumValue("resultTemplate")
    RESULT_TEMPLATE("resultTemplate");
    private final String value;

    ResponseModeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ResponseModeType fromValue(String v) {
        for (ResponseModeType c: ResponseModeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}