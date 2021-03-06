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
 * Vector/SquareMatrix is a data-type so usually appears "by value" rather than by reference.
 * 
 * <p>Java class for VectorOrSquareMatrixPropertyType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VectorOrSquareMatrixPropertyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element ref="{http://www.opengis.net/swe/1.0.1}Vector"/>
 *         &lt;element ref="{http://www.opengis.net/swe/1.0.1}SquareMatrix"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VectorOrSquareMatrixPropertyType", propOrder = {
    "vector",
    "squareMatrix"
})
public class VectorOrSquareMatrixPropertyType {

    @XmlElement(name = "Vector")
    protected VectorType vector;
    @XmlElement(name = "SquareMatrix")
    protected SquareMatrixType squareMatrix;

    /**
     * Gets the value of the vector property.
     * 
     * @return
     *     possible object is
     *     {@link VectorType }
     *     
     */
    public VectorType getVector() {
        return vector;
    }

    /**
     * Sets the value of the vector property.
     * 
     * @param value
     *     allowed object is
     *     {@link VectorType }
     *     
     */
    public void setVector(VectorType value) {
        this.vector = value;
    }

    public boolean isSetVector() {
        return (this.vector!= null);
    }

    /**
     * Gets the value of the squareMatrix property.
     * 
     * @return
     *     possible object is
     *     {@link SquareMatrixType }
     *     
     */
    public SquareMatrixType getSquareMatrix() {
        return squareMatrix;
    }

    /**
     * Sets the value of the squareMatrix property.
     * 
     * @param value
     *     allowed object is
     *     {@link SquareMatrixType }
     *     
     */
    public void setSquareMatrix(SquareMatrixType value) {
        this.squareMatrix = value;
    }

    public boolean isSetSquareMatrix() {
        return (this.squareMatrix!= null);
    }

}
