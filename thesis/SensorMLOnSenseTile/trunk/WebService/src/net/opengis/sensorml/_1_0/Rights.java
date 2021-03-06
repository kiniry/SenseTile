//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.02.01 at 05:02:44 PM GMT 
//


package net.opengis.sensorml._1_0;

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
 *         &lt;element ref="{http://www.opengis.net/sensorML/1.0.1}documentation"/>
 *       &lt;/sequence>
 *       &lt;attribute ref="{http://www.opengis.net/gml}id"/>
 *       &lt;attribute name="privacyAct" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="intellectualPropertyRights" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="copyRights" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "documentation"
})
@XmlRootElement(name = "Rights")
public class Rights {

    @XmlElement(required = true)
    protected Documentation documentation;
    @XmlAttribute(namespace = "http://www.opengis.net/gml")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;
    @XmlAttribute
    protected Boolean privacyAct;
    @XmlAttribute
    protected Boolean intellectualPropertyRights;
    @XmlAttribute
    protected Boolean copyRights;

    /**
     * Gets the value of the documentation property.
     * 
     * @return
     *     possible object is
     *     {@link Documentation }
     *     
     */
    public Documentation getDocumentation() {
        return documentation;
    }

    /**
     * Sets the value of the documentation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Documentation }
     *     
     */
    public void setDocumentation(Documentation value) {
        this.documentation = value;
    }

    public boolean isSetDocumentation() {
        return (this.documentation!= null);
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
     * Gets the value of the privacyAct property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isPrivacyAct() {
        return privacyAct;
    }

    /**
     * Sets the value of the privacyAct property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPrivacyAct(boolean value) {
        this.privacyAct = value;
    }

    public boolean isSetPrivacyAct() {
        return (this.privacyAct!= null);
    }

    public void unsetPrivacyAct() {
        this.privacyAct = null;
    }

    /**
     * Gets the value of the intellectualPropertyRights property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIntellectualPropertyRights() {
        return intellectualPropertyRights;
    }

    /**
     * Sets the value of the intellectualPropertyRights property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIntellectualPropertyRights(boolean value) {
        this.intellectualPropertyRights = value;
    }

    public boolean isSetIntellectualPropertyRights() {
        return (this.intellectualPropertyRights!= null);
    }

    public void unsetIntellectualPropertyRights() {
        this.intellectualPropertyRights = null;
    }

    /**
     * Gets the value of the copyRights property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isCopyRights() {
        return copyRights;
    }

    /**
     * Sets the value of the copyRights property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCopyRights(boolean value) {
        this.copyRights = value;
    }

    public boolean isSetCopyRights() {
        return (this.copyRights!= null);
    }

    public void unsetCopyRights() {
        this.copyRights = null;
    }

}
