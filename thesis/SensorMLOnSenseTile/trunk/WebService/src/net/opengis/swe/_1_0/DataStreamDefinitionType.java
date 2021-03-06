//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.02.01 at 05:02:44 PM GMT 
//


package net.opengis.swe._1_0;

import java.util.ArrayList;
import java.util.List;
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
 * <p>Java class for DataStreamDefinitionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DataStreamDefinitionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="streamComponent" type="{http://www.opengis.net/swe/1.0.1}DataBlockDefinitionPropertyType" maxOccurs="unbounded"/>
 *         &lt;element name="streamEncoding" type="{http://www.opengis.net/swe/1.0.1}MultiplexedStreamFormatPropertyType"/>
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
@XmlType(name = "DataStreamDefinitionType", propOrder = {
    "streamComponent",
    "streamEncoding"
})
public class DataStreamDefinitionType {

    @XmlElement(required = true)
    protected List<DataBlockDefinitionPropertyType> streamComponent;
    @XmlElement(required = true)
    protected MultiplexedStreamFormatPropertyType streamEncoding;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Gets the value of the streamComponent property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the streamComponent property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStreamComponent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataBlockDefinitionPropertyType }
     * 
     * 
     */
    public List<DataBlockDefinitionPropertyType> getStreamComponent() {
        if (streamComponent == null) {
            streamComponent = new ArrayList<DataBlockDefinitionPropertyType>();
        }
        return this.streamComponent;
    }

    public boolean isSetStreamComponent() {
        return ((this.streamComponent!= null)&&(!this.streamComponent.isEmpty()));
    }

    public void unsetStreamComponent() {
        this.streamComponent = null;
    }

    /**
     * Gets the value of the streamEncoding property.
     * 
     * @return
     *     possible object is
     *     {@link MultiplexedStreamFormatPropertyType }
     *     
     */
    public MultiplexedStreamFormatPropertyType getStreamEncoding() {
        return streamEncoding;
    }

    /**
     * Sets the value of the streamEncoding property.
     * 
     * @param value
     *     allowed object is
     *     {@link MultiplexedStreamFormatPropertyType }
     *     
     */
    public void setStreamEncoding(MultiplexedStreamFormatPropertyType value) {
        this.streamEncoding = value;
    }

    public boolean isSetStreamEncoding() {
        return (this.streamEncoding!= null);
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
