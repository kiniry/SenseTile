//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.02.01 at 05:02:44 PM GMT 
//


package net.opengis.gml.v_3_1_1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * A cone is a gridded surface given as a
 *    family of conic sections whose control points vary linearly.
 *    NOTE! A 5-point ellipse with all defining positions identical
 *    is a point. Thus, a truncated elliptical cone can be given as a
 *    2x5 set of control points
 *    ((P1, P1, P1, P1, P1), (P2, P3, P4, P5, P6)). P1 is the apex 
 *    of the cone. P2, P3,P4, P5 and P6 are any five distinct points
 *    around the base ellipse of the cone. If the horizontal curves
 *    are circles as opposed to ellipses, the a circular cone can
 *    be constructed using ((P1, P1, P1),(P2, P3, P4)). The apex most     
 *    not coinside with the other plane.
 * 
 * <p>Java class for ConeType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConeType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractGriddedSurfaceType">
 *       &lt;attribute name="horizontalCurveType" type="{http://www.opengis.net/gml}CurveInterpolationType" fixed="circularArc3Points" />
 *       &lt;attribute name="verticalCurveType" type="{http://www.opengis.net/gml}CurveInterpolationType" fixed="linear" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConeType")
public class ConeType
    extends AbstractGriddedSurfaceType
{

    /**
     * 
     * 
     */
    @XmlAttribute(name = "horizontalCurveType")
    public final static CurveInterpolationType HORIZONTAL_CURVE_TYPE = CurveInterpolationType.CIRCULAR_ARC_3_POINTS;
    /**
     * 
     * 
     */
    @XmlAttribute(name = "verticalCurveType")
    public final static CurveInterpolationType VERTICAL_CURVE_TYPE = CurveInterpolationType.LINEAR;

}