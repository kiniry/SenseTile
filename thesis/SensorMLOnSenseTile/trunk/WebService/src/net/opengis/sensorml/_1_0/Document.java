//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.02.01 at 05:02:44 PM GMT 
//


package net.opengis.sensorml._1_0;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import net.opengis.gml.v_3_1_1.StringOrRefType;


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
 *         &lt;element ref="{http://www.opengis.net/gml}description"/>
 *         &lt;element name="date" type="{http://www.opengis.net/swe/1.0.1}timeIso8601" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/sensorML/1.0.1}contact" minOccurs="0"/>
 *         &lt;element name="format" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/sensorML/1.0.1}onlineResource" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute ref="{http://www.opengis.net/gml}id"/>
 *       &lt;attribute name="version" type="{http://www.w3.org/2001/XMLSchema}token" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "description",
    "date",
    "contact",
    "format",
    "onlineResource"
})
@XmlRootElement(name = "Document")
public class Document {

    @XmlElement(namespace = "http://www.opengis.net/gml", required = true)
    protected StringOrRefType description;
    protected String date;
    protected Contact contact;
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String format;
    protected List<OnlineResource> onlineResource;
    @XmlAttribute(namespace = "http://www.opengis.net/gml")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String version;

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link StringOrRefType }
     *     
     */
    public StringOrRefType getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link StringOrRefType }
     *     
     */
    public void setDescription(StringOrRefType value) {
        this.description = value;
    }

    public boolean isSetDescription() {
        return (this.description!= null);
    }

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDate(String value) {
        this.date = value;
    }

    public boolean isSetDate() {
        return (this.date!= null);
    }

    /**
     * Person who is responsible for the document
     * 
     * @return
     *     possible object is
     *     {@link Contact }
     *     
     */
    public Contact getContact() {
        return contact;
    }

    /**
     * Sets the value of the contact property.
     * 
     * @param value
     *     allowed object is
     *     {@link Contact }
     *     
     */
    public void setContact(Contact value) {
        this.contact = value;
    }

    public boolean isSetContact() {
        return (this.contact!= null);
    }

    /**
     * Gets the value of the format property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormat() {
        return format;
    }

    /**
     * Sets the value of the format property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormat(String value) {
        this.format = value;
    }

    public boolean isSetFormat() {
        return (this.format!= null);
    }

    /**
     * Points to the actual document corresponding to that version Gets the value of the onlineResource property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the onlineResource property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOnlineResource().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OnlineResource }
     * 
     * 
     */
    public List<OnlineResource> getOnlineResource() {
        if (onlineResource == null) {
            onlineResource = new ArrayList<OnlineResource>();
        }
        return this.onlineResource;
    }

    public boolean isSetOnlineResource() {
        return ((this.onlineResource!= null)&&(!this.onlineResource.isEmpty()));
    }

    public void unsetOnlineResource() {
        this.onlineResource = null;
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

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

    public boolean isSetVersion() {
        return (this.version!= null);
    }

}
