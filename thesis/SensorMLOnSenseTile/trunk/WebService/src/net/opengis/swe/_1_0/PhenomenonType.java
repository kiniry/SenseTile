//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.02.01 at 05:02:44 PM GMT 
//


package net.opengis.swe._1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import net.opengis.gml.v_3_1_1.DefinitionType;


/**
 * Basic Phenomenon definition, and head of substitution group of specialized phenomenon defs. 
 * 			gml:description may be used for a more extensive description of the semantics, with a link to a definitive version (if available).  
 * 			gml:name should be used for the name or label.
 * 			
 * 			Note: In GML 3.2 the gml:identifier element should be used for the identifier assigned by or preferred by the data provider. 
 * 			This identifier will typically be in the form of a URN, for example following the OGC URN scheme
 * 			e.g. urn:x-ogc:def:phenomenon:OGC:Age
 * 			
 * 
 * <p>Java class for PhenomenonType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PhenomenonType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}DefinitionType">
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PhenomenonType")
@XmlSeeAlso({
    ConstrainedPhenomenonType.class,
    CompoundPhenomenonType.class
})
public class PhenomenonType
    extends DefinitionType
{


}