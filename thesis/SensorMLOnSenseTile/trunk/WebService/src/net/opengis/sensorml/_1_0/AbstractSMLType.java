//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.02.01 at 05:02:44 PM GMT 
//


package net.opengis.sensorml._1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import net.opengis.gml.v_3_1_1.AbstractFeatureType;


/**
 * Main Abstract SensorML Object
 * 
 * <p>Java class for AbstractSMLType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AbstractSMLType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.opengis.net/gml}AbstractFeatureType">
 *       &lt;sequence>
 *         &lt;sequence>
 *           &lt;sequence>
 *             &lt;element ref="{http://www.opengis.net/gml}description" minOccurs="0"/>
 *             &lt;element ref="{http://www.opengis.net/gml}name" minOccurs="0"/>
 *             &lt;element ref="{http://www.opengis.net/gml}boundedBy" minOccurs="0"/>
 *           &lt;/sequence>
 *         &lt;/sequence>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractSMLType")
@XmlSeeAlso({
    AbstractProcessType.class
})
public abstract class AbstractSMLType
    extends AbstractFeatureType
{


}