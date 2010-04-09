//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.02.01 at 05:02:44 PM GMT 
//


package net.opengis.swe._1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SquareMatrixType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SquareMatrixType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/swe/1.0.1}AbstractMatrixType">
 *       &lt;sequence>
 *         &lt;element name="elementType" type="{http://www.opengis.net/swe/1.0.1}QuantityPropertyType"/>
 *         &lt;group ref="{http://www.opengis.net/swe/1.0.1}EncodedValuesGroup" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SquareMatrixType", propOrder = {
    "elementType",
    "encoding",
    "values"
})
public class SquareMatrixType
    extends AbstractMatrixType
{

    @XmlElement(required = true)
    protected QuantityPropertyType elementType;
    protected BlockEncodingPropertyType encoding;
    protected DataValuePropertyType values;

    /**
     * Gets the value of the elementType property.
     * 
     * @return
     *     possible object is
     *     {@link QuantityPropertyType }
     *     
     */
    public QuantityPropertyType getElementType() {
        return elementType;
    }

    /**
     * Sets the value of the elementType property.
     * 
     * @param value
     *     allowed object is
     *     {@link QuantityPropertyType }
     *     
     */
    public void setElementType(QuantityPropertyType value) {
        this.elementType = value;
    }

    public boolean isSetElementType() {
        return (this.elementType!= null);
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
     * Gets the value of the values property.
     * 
     * @return
     *     possible object is
     *     {@link DataValuePropertyType }
     *     
     */
    public DataValuePropertyType getValues() {
        return values;
    }

    /**
     * Sets the value of the values property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataValuePropertyType }
     *     
     */
    public void setValues(DataValuePropertyType value) {
        this.values = value;
    }

    public boolean isSetValues() {
        return (this.values!= null);
    }

}
