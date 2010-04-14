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


/**
 * Complex Type for all generic components (soft typed inputs/outputs/parameters)
 * 
 * <p>Java class for AbstractComponentType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AbstractComponentType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/sensorML/1.0.1}AbstractDerivableComponentType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/sensorML/1.0.1}inputs" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/sensorML/1.0.1}outputs" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/sensorML/1.0.1}parameters" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractComponentType")
@XmlSeeAlso({
    ComponentType.class,
    SystemType.class
})
public abstract class AbstractComponentType
    extends AbstractDerivableComponentType
{


}