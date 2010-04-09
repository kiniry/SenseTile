//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.02.01 at 05:02:44 PM GMT 
//


package net.opengis.gml.v_3_1_1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import net.opengis.swe._1_0.PhenomenonType;


/**
 * A definition, which can be included in or referenced by a dictionary. In this extended type, the inherited "description" optional element can hold the definition whenever only text is needed. The inherited "name" elements can provide one or more brief terms for which this is the definition. The inherited "metaDataProperty" elements can be used to reference or include more information about this definition.  
 * The gml:id attribute is required - it must be possible to reference this definition using this handle.  
 * 
 * <p>Java class for DefinitionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DefinitionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.opengis.net/gml}AbstractGMLType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml}metaDataProperty" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/gml}description" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/gml}name" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute ref="{http://www.opengis.net/gml}id use="required""/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DefinitionType")
@XmlSeeAlso({
    TimeCalendarEraType.class,
    TimeOrdinalEraType.class,
    DefinitionProxyType.class,
    DictionaryType.class,
    AbstractTimeReferenceSystemType.class,
    UnitDefinitionType.class,
    AbstractReferenceSystemBaseType.class,
    EllipsoidBaseType.class,
    PrimeMeridianBaseType.class,
    AbstractGeneralOperationParameterType.class,
    CoordinateSystemAxisBaseType.class,
    AbstractCoordinateOperationBaseType.class,
    AbstractCoordinateSystemBaseType.class,
    AbstractDatumBaseType.class,
    OperationMethodBaseType.class,
    PhenomenonType.class
})
public class DefinitionType
    extends AbstractGMLType
{


}
