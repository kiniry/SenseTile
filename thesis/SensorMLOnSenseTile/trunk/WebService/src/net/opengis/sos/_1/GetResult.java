//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.02.01 at 05:02:44 PM GMT 
//


package net.opengis.sos._1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import net.opengis.ogc.BinaryTemporalOpType;
import net.opengis.ogc.TemporalOpsType;


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
 *         &lt;element name="ObservationTemplateId" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *         &lt;element name="eventTime" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://www.opengis.net/ogc}temporalOps"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
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
    "observationTemplateId",
    "eventTime"
})
@XmlRootElement(name = "GetResult")
@XmlSeeAlso({ BinaryTemporalOpType.class }) 
public class GetResult
    extends RequestBaseType
{

    @XmlElement(name = "ObservationTemplateId", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String observationTemplateId;
    protected List<GetResult.EventTime> eventTime;

    /**
     * Gets the value of the observationTemplateId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObservationTemplateId() {
        return observationTemplateId;
    }

    /**
     * Sets the value of the observationTemplateId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObservationTemplateId(String value) {
        this.observationTemplateId = value;
    }

    public boolean isSetObservationTemplateId() {
        return (this.observationTemplateId!= null);
    }

    /**
     * Gets the value of the eventTime property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the eventTime property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEventTime().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GetResult.EventTime }
     * 
     * 
     */
    public List<GetResult.EventTime> getEventTime() {
        if (eventTime == null) {
            eventTime = new ArrayList<GetResult.EventTime>();
        }
        return this.eventTime;
    }

    public boolean isSetEventTime() {
        return ((this.eventTime!= null)&&(!this.eventTime.isEmpty()));
    }

    public void unsetEventTime() {
        this.eventTime = null;
    }


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
     *         &lt;element ref="{http://www.opengis.net/ogc}temporalOps"/>
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
        "temporalOps"
    })
    public static class EventTime {

        @XmlElementRef(name = "temporalOps", namespace = "http://www.opengis.net/ogc", type = JAXBElement.class)
        protected JAXBElement<? extends TemporalOpsType> temporalOps;

        /**
         * Gets the value of the temporalOps property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link BinaryTemporalOpType }{@code >}
         *     {@link JAXBElement }{@code <}{@link BinaryTemporalOpType }{@code >}
         *     {@link JAXBElement }{@code <}{@link BinaryTemporalOpType }{@code >}
         *     {@link JAXBElement }{@code <}{@link BinaryTemporalOpType }{@code >}
         *     {@link JAXBElement }{@code <}{@link BinaryTemporalOpType }{@code >}
         *     {@link JAXBElement }{@code <}{@link BinaryTemporalOpType }{@code >}
         *     {@link JAXBElement }{@code <}{@link BinaryTemporalOpType }{@code >}
         *     {@link JAXBElement }{@code <}{@link BinaryTemporalOpType }{@code >}
         *     {@link JAXBElement }{@code <}{@link BinaryTemporalOpType }{@code >}
         *     {@link JAXBElement }{@code <}{@link BinaryTemporalOpType }{@code >}
         *     {@link JAXBElement }{@code <}{@link BinaryTemporalOpType }{@code >}
         *     {@link JAXBElement }{@code <}{@link BinaryTemporalOpType }{@code >}
         *     {@link JAXBElement }{@code <}{@link BinaryTemporalOpType }{@code >}
         *     {@link JAXBElement }{@code <}{@link TemporalOpsType }{@code >}
         *     
         */
        public JAXBElement<? extends TemporalOpsType> getTemporalOps() {
            return temporalOps;
        }

        /**
         * Sets the value of the temporalOps property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link BinaryTemporalOpType }{@code >}
         *     {@link JAXBElement }{@code <}{@link BinaryTemporalOpType }{@code >}
         *     {@link JAXBElement }{@code <}{@link BinaryTemporalOpType }{@code >}
         *     {@link JAXBElement }{@code <}{@link BinaryTemporalOpType }{@code >}
         *     {@link JAXBElement }{@code <}{@link BinaryTemporalOpType }{@code >}
         *     {@link JAXBElement }{@code <}{@link BinaryTemporalOpType }{@code >}
         *     {@link JAXBElement }{@code <}{@link BinaryTemporalOpType }{@code >}
         *     {@link JAXBElement }{@code <}{@link BinaryTemporalOpType }{@code >}
         *     {@link JAXBElement }{@code <}{@link BinaryTemporalOpType }{@code >}
         *     {@link JAXBElement }{@code <}{@link BinaryTemporalOpType }{@code >}
         *     {@link JAXBElement }{@code <}{@link BinaryTemporalOpType }{@code >}
         *     {@link JAXBElement }{@code <}{@link BinaryTemporalOpType }{@code >}
         *     {@link JAXBElement }{@code <}{@link BinaryTemporalOpType }{@code >}
         *     {@link JAXBElement }{@code <}{@link TemporalOpsType }{@code >}
         *     
         */
        public void setTemporalOps(JAXBElement<? extends TemporalOpsType> value) {
            this.temporalOps = ((JAXBElement<? extends TemporalOpsType> ) value);
        }

        public boolean isSetTemporalOps() {
            return (this.temporalOps!= null);
        }

    }

}