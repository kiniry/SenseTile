//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.02.01 at 05:02:44 PM GMT 
//


package net.opengis.swe._1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for DataBlockDefinitionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DataBlockDefinitionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="components" type="{http://www.opengis.net/swe/1.0.1}DataComponentPropertyType"/>
 *         &lt;element name="encoding" type="{http://www.opengis.net/swe/1.0.1}BlockEncodingPropertyType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataBlockDefinitionType", propOrder = {
    "components",
    "encoding"
})
public class DataBlockDefinitionType {

    @XmlElement(required = true)
    protected DataComponentPropertyType components;
    @XmlElement(required = true)
    protected BlockEncodingPropertyType encoding;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Gets the value of the components property.
     * 
     * @return
     *     possible object is
     *     {@link DataComponentPropertyType }
     *     
     */
    public DataComponentPropertyType getComponents() {
        return components;
    }

    /**
     * Sets the value of the components property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataComponentPropertyType }
     *     
     */
    public void setComponents(DataComponentPropertyType value) {
        this.components = value;
    }

    public boolean isSetComponents() {
        return (this.components!= null);
    }

    /**
     * Gets the value of the encoding property.
     * 
     * @return
     *     possible object is
     *     {@link BlockEncodingPropertyType }
     *     
     */
    public BlockEncodingPropertyType getEncoding() {
        return encoding;
    }

    /**
     * Sets the value of the encoding property.
     * 
     * @param value
     *     allowed object is
     *     {@link BlockEncodingPropertyType }
     *     
     */
    public void setEncoding(BlockEncodingPropertyType value) {
        this.encoding = value;
    }

    public boolean isSetEncoding() {
        return (this.encoding!= null);
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    public boolean isSetId() {
        return (this.id!= null);
    }

}