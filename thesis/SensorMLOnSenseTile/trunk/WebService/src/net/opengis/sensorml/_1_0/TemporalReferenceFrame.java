//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.02.01 at 05:02:44 PM GMT 
//


package net.opengis.sensorml._1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import net.opengis.gml.v_3_1_1.TemporalCRSType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml}TemporalCRS"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "temporalCRS"
})
@XmlRootElement(name = "temporalReferenceFrame")
public class TemporalReferenceFrame {

    @XmlElement(name = "TemporalCRS", namespace = "http://www.opengis.net/gml", required = true)
    protected TemporalCRSType temporalCRS;

    /**
     * Gets the value of the temporalCRS property.
     * 
     * @return
     *     possible object is
     *     {@link TemporalCRSType }
     *     
     */
    public TemporalCRSType getTemporalCRS() {
        return temporalCRS;
    }

    /**
     * Sets the value of the temporalCRS property.
     * 
     * @param value
     *     allowed object is
     *     {@link TemporalCRSType }
     *     
     */
    public void setTemporalCRS(TemporalCRSType value) {
        this.temporalCRS = value;
    }

    public boolean isSetTemporalCRS() {
        return (this.temporalCRS!= null);
    }

}
