//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.02.01 at 05:02:44 PM GMT 
//


package net.opengis.gml.v_3_1_1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * A 1D coordinate reference system used for the recording of time. 
 * 
 * <p>Java class for TemporalCRSType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TemporalCRSType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractReferenceSystemType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml}usesTemporalCS"/>
 *         &lt;element ref="{http://www.opengis.net/gml}usesTemporalDatum"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TemporalCRSType", propOrder = {
    "usesTemporalCS",
    "usesTemporalDatum"
})
public class TemporalCRSType
    extends AbstractReferenceSystemType
{

    @XmlElement(required = true)
    protected TemporalCSRefType usesTemporalCS;
    @XmlElement(required = true)
    protected TemporalDatumRefType usesTemporalDatum;

    /**
     * Gets the value of the usesTemporalCS property.
     * 
     * @return
     *     possible object is
     *     {@link TemporalCSRefType }
     *     
     */
    public TemporalCSRefType getUsesTemporalCS() {
        return usesTemporalCS;
    }

    /**
     * Sets the value of the usesTemporalCS property.
     * 
     * @param value
     *     allowed object is
     *     {@link TemporalCSRefType }
     *     
     */
    public void setUsesTemporalCS(TemporalCSRefType value) {
        this.usesTemporalCS = value;
    }

    public boolean isSetUsesTemporalCS() {
        return (this.usesTemporalCS!= null);
    }

    /**
     * Gets the value of the usesTemporalDatum property.
     * 
     * @return
     *     possible object is
     *     {@link TemporalDatumRefType }
     *     
     */
    public TemporalDatumRefType getUsesTemporalDatum() {
        return usesTemporalDatum;
    }

    /**
     * Sets the value of the usesTemporalDatum property.
     * 
     * @param value
     *     allowed object is
     *     {@link TemporalDatumRefType }
     *     
     */
    public void setUsesTemporalDatum(TemporalDatumRefType value) {
        this.usesTemporalDatum = value;
    }

    public boolean isSetUsesTemporalDatum() {
        return (this.usesTemporalDatum!= null);
    }

}
