//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.02.01 at 05:02:44 PM GMT 
//


package net.opengis.sos._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="AssignedSensorId" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
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
    "assignedSensorId"
})
@XmlRootElement(name = "RegisterSensorResponse")
public class RegisterSensorResponse {

    @XmlElement(name = "AssignedSensorId", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String assignedSensorId;

    /**
     * Gets the value of the assignedSensorId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssignedSensorId() {
        return assignedSensorId;
    }

    /**
     * Sets the value of the assignedSensorId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssignedSensorId(String value) {
        this.assignedSensorId = value;
    }

    public boolean isSetAssignedSensorId() {
        return (this.assignedSensorId!= null);
    }

}
