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
 *         &lt;element name="ResultName" type="{http://www.w3.org/2001/XMLSchema}QName"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "resultName"
})
@XmlRootElement(name = "DescribeResultModel")
public class DescribeResultModel
    extends RequestBaseType
{

    @XmlElement(name = "ResultName", required = true)
    protected QName resultName;

    /**
     * Gets the value of the resultName property.
     * 
     * @return
     *     possible object is
     *     {@link QName }
     *     
     */
    public QName getResultName() {
        return resultName;
    }

    /**
     * Sets the value of the resultName property.
     * 
     * @param value
     *     allowed object is
     *     {@link QName }
     *     
     */
    public void setResultName(QName value) {
        this.resultName = value;
    }

    public boolean isSetResultName() {
        return (this.resultName!= null);
    }

}