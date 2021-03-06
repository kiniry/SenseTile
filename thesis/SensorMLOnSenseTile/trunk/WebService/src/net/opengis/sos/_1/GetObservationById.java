//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.02.01 at 05:02:44 PM GMT 
//


package net.opengis.sos._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/sos/1.0}RequestBaseType">
 *       &lt;sequence>
 *         &lt;element name="ObservationId" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *         &lt;element name="responseFormat" type="{http://www.opengis.net/ows/1.1}MimeType" minOccurs="0"/>
 *         &lt;element name="resultModel" type="{http://www.w3.org/2001/XMLSchema}QName" minOccurs="0"/>
 *         &lt;element name="responseMode" type="{http://www.opengis.net/sos/1.0}responseModeType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="srsName" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "observationId",
    "responseFormat",
    "resultModel",
    "responseMode"
})
@XmlRootElement(name = "GetObservationById")
public class GetObservationById
    extends RequestBaseType
{

    @XmlElement(name = "ObservationId", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String observationId;
    protected String responseFormat;
    protected QName resultModel;
    protected ResponseModeType responseMode;
    @XmlAttribute
    @XmlSchemaType(name = "anyURI")
    protected String srsName;

    /**
     * Gets the value of the observationId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObservationId() {
        return observationId;
    }

    /**
     * Sets the value of the observationId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObservationId(String value) {
        this.observationId = value;
    }

    public boolean isSetObservationId() {
        return (this.observationId!= null);
    }

    /**
     * Gets the value of the responseFormat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponseFormat() {
        return responseFormat;
    }

    /**
     * Sets the value of the responseFormat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponseFormat(String value) {
        this.responseFormat = value;
    }

    public boolean isSetResponseFormat() {
        return (this.responseFormat!= null);
    }

    /**
     * Gets the value of the resultModel property.
     * 
     * @return
     *     possible object is
     *     {@link QName }
     *     
     */
    public QName getResultModel() {
        return resultModel;
    }

    /**
     * Sets the value of the resultModel property.
     * 
     * @param value
     *     allowed object is
     *     {@link QName }
     *     
     */
    public void setResultModel(QName value) {
        this.resultModel = value;
    }

    public boolean isSetResultModel() {
        return (this.resultModel!= null);
    }

    /**
     * Gets the value of the responseMode property.
     * 
     * @return
     *     possible object is
     *     {@link ResponseModeType }
     *     
     */
    public ResponseModeType getResponseMode() {
        return responseMode;
    }

    /**
     * Sets the value of the responseMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseModeType }
     *     
     */
    public void setResponseMode(ResponseModeType value) {
        this.responseMode = value;
    }

    public boolean isSetResponseMode() {
        return (this.responseMode!= null);
    }

    /**
     * Gets the value of the srsName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSrsName() {
        return srsName;
    }

    /**
     * Sets the value of the srsName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSrsName(String value) {
        this.srsName = value;
    }

    public boolean isSetSrsName() {
        return (this.srsName!= null);
    }

}
