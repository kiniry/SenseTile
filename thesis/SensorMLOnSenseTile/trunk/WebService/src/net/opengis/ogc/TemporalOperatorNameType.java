//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.02.01 at 05:02:44 PM GMT 
//


package net.opengis.ogc;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TemporalOperatorNameType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TemporalOperatorNameType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="TM_After"/>
 *     &lt;enumeration value="TM_Before"/>
 *     &lt;enumeration value="TM_Begins"/>
 *     &lt;enumeration value="TM_BegunBy"/>
 *     &lt;enumeration value="TM_Contains"/>
 *     &lt;enumeration value="TM_During"/>
 *     &lt;enumeration value="TM_Equals"/>
 *     &lt;enumeration value="TM_Overlaps"/>
 *     &lt;enumeration value="TM_Meets"/>
 *     &lt;enumeration value="TM_OverlappedBy"/>
 *     &lt;enumeration value="TM_MetBy"/>
 *     &lt;enumeration value="TM_EndedBy"/>
 *     &lt;enumeration value="TM_Ends"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TemporalOperatorNameType")
@XmlEnum
public enum TemporalOperatorNameType {

    @XmlEnumValue("TM_After")
    TM_AFTER("TM_After"),
    @XmlEnumValue("TM_Before")
    TM_BEFORE("TM_Before"),
    @XmlEnumValue("TM_Begins")
    TM_BEGINS("TM_Begins"),
    @XmlEnumValue("TM_BegunBy")
    TM_BEGUN_BY("TM_BegunBy"),
    @XmlEnumValue("TM_Contains")
    TM_CONTAINS("TM_Contains"),
    @XmlEnumValue("TM_During")
    TM_DURING("TM_During"),
    @XmlEnumValue("TM_Equals")
    TM_EQUALS("TM_Equals"),
    @XmlEnumValue("TM_Overlaps")
    TM_OVERLAPS("TM_Overlaps"),
    @XmlEnumValue("TM_Meets")
    TM_MEETS("TM_Meets"),
    @XmlEnumValue("TM_OverlappedBy")
    TM_OVERLAPPED_BY("TM_OverlappedBy"),
    @XmlEnumValue("TM_MetBy")
    TM_MET_BY("TM_MetBy"),
    @XmlEnumValue("TM_EndedBy")
    TM_ENDED_BY("TM_EndedBy"),
    @XmlEnumValue("TM_Ends")
    TM_ENDS("TM_Ends");
    private final String value;

    TemporalOperatorNameType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TemporalOperatorNameType fromValue(String v) {
        for (TemporalOperatorNameType c: TemporalOperatorNameType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
