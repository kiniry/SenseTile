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
import net.opengis.ogc.IdCapabilitiesType;
import net.opengis.ogc.ScalarCapabilitiesType;
import net.opengis.ogc.SpatialCapabilitiesType;
import net.opengis.ogc.TemporalCapabilitiesType;


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
 *         &lt;element ref="{http://www.opengis.net/ogc}Spatial_Capabilities"/>
 *         &lt;element ref="{http://www.opengis.net/ogc}Temporal_Capabilities"/>
 *         &lt;element ref="{http://www.opengis.net/ogc}Scalar_Capabilities"/>
 *         &lt;element ref="{http://www.opengis.net/ogc}Id_Capabilities"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "spatialCapabilities",
    "temporalCapabilities",
    "scalarCapabilities",
    "idCapabilities"
})
@XmlRootElement(name = "Filter_Capabilities")
public class FilterCapabilities {

    @XmlElement(name = "Spatial_Capabilities", namespace = "http://www.opengis.net/ogc", required = true)
    protected SpatialCapabilitiesType spatialCapabilities;
    @XmlElement(name = "Temporal_Capabilities", namespace = "http://www.opengis.net/ogc", required = true)
    protected TemporalCapabilitiesType temporalCapabilities;
    @XmlElement(name = "Scalar_Capabilities", namespace = "http://www.opengis.net/ogc", required = true)
    protected ScalarCapabilitiesType scalarCapabilities;
    @XmlElement(name = "Id_Capabilities", namespace = "http://www.opengis.net/ogc", required = true)
    protected IdCapabilitiesType idCapabilities;

    /**
     * Gets the value of the spatialCapabilities property.
     * 
     * @return
     *     possible object is
     *     {@link SpatialCapabilitiesType }
     *     
     */
    public SpatialCapabilitiesType getSpatialCapabilities() {
        return spatialCapabilities;
    }

    /**
     * Sets the value of the spatialCapabilities property.
     * 
     * @param value
     *     allowed object is
     *     {@link SpatialCapabilitiesType }
     *     
     */
    public void setSpatialCapabilities(SpatialCapabilitiesType value) {
        this.spatialCapabilities = value;
    }

    public boolean isSetSpatialCapabilities() {
        return (this.spatialCapabilities!= null);
    }

    /**
     * Gets the value of the temporalCapabilities property.
     * 
     * @return
     *     possible object is
     *     {@link TemporalCapabilitiesType }
     *     
     */
    public TemporalCapabilitiesType getTemporalCapabilities() {
        return temporalCapabilities;
    }

    /**
     * Sets the value of the temporalCapabilities property.
     * 
     * @param value
     *     allowed object is
     *     {@link TemporalCapabilitiesType }
     *     
     */
    public void setTemporalCapabilities(TemporalCapabilitiesType value) {
        this.temporalCapabilities = value;
    }

    public boolean isSetTemporalCapabilities() {
        return (this.temporalCapabilities!= null);
    }

    /**
     * Gets the value of the scalarCapabilities property.
     * 
     * @return
     *     possible object is
     *     {@link ScalarCapabilitiesType }
     *     
     */
    public ScalarCapabilitiesType getScalarCapabilities() {
        return scalarCapabilities;
    }

    /**
     * Sets the value of the scalarCapabilities property.
     * 
     * @param value
     *     allowed object is
     *     {@link ScalarCapabilitiesType }
     *     
     */
    public void setScalarCapabilities(ScalarCapabilitiesType value) {
        this.scalarCapabilities = value;
    }

    public boolean isSetScalarCapabilities() {
        return (this.scalarCapabilities!= null);
    }

    /**
     * Gets the value of the idCapabilities property.
     * 
     * @return
     *     possible object is
     *     {@link IdCapabilitiesType }
     *     
     */
    public IdCapabilitiesType getIdCapabilities() {
        return idCapabilities;
    }

    /**
     * Sets the value of the idCapabilities property.
     * 
     * @param value
     *     allowed object is
     *     {@link IdCapabilitiesType }
     *     
     */
    public void setIdCapabilities(IdCapabilitiesType value) {
        this.idCapabilities = value;
    }

    public boolean isSetIdCapabilities() {
        return (this.idCapabilities!= null);
    }

}
