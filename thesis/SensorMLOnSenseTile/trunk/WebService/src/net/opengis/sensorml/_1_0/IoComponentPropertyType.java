//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.02.01 at 05:02:44 PM GMT 
//


package net.opengis.sensorml._1_0;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import net.opengis.swe._1_0.AbstractDataArrayType;
import net.opengis.swe._1_0.AbstractDataRecordType;
import net.opengis.swe._1_0.Boolean;
import net.opengis.swe._1_0.Category;
import net.opengis.swe._1_0.ConditionalDataType;
import net.opengis.swe._1_0.ConditionalValueType;
import net.opengis.swe._1_0.Count;
import net.opengis.swe._1_0.CountRange;
import net.opengis.swe._1_0.CurveType;
import net.opengis.swe._1_0.DataArrayType;
import net.opengis.swe._1_0.DataRecordType;
import net.opengis.swe._1_0.EnvelopeType;
import net.opengis.swe._1_0.GeoLocationArea;
import net.opengis.swe._1_0.NormalizedCurveType;
import net.opengis.swe._1_0.ObservableProperty;
import net.opengis.swe._1_0.PositionType;
import net.opengis.swe._1_0.Quantity;
import net.opengis.swe._1_0.QuantityRange;
import net.opengis.swe._1_0.SimpleDataRecordType;
import net.opengis.swe._1_0.SquareMatrixType;
import net.opengis.swe._1_0.Text;
import net.opengis.swe._1_0.Time;
import net.opengis.swe._1_0.TimeRange;
import net.opengis.swe._1_0.VectorType;


/**
 * <p>Java class for IoComponentPropertyType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IoComponentPropertyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice minOccurs="0">
 *         &lt;group ref="{http://www.opengis.net/swe/1.0.1}AnyData"/>
 *         &lt;element ref="{http://www.opengis.net/swe/1.0.1}ObservableProperty"/>
 *       &lt;/choice>
 *       &lt;attGroup ref="{http://www.opengis.net/gml}AssociationAttributeGroup"/>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}token" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IoComponentPropertyType", propOrder = {
    "count",
    "quantity",
    "time",
    "_boolean",
    "category",
    "text",
    "quantityRange",
    "countRange",
    "timeRange",
    "abstractDataRecord",
    "abstractDataArray",
    "observableProperty"
})
public class IoComponentPropertyType {

    @XmlElement(name = "Count", namespace = "http://www.opengis.net/swe/1.0.1")
    protected Count count;
    @XmlElement(name = "Quantity", namespace = "http://www.opengis.net/swe/1.0.1")
    protected Quantity quantity;
    @XmlElement(name = "Time", namespace = "http://www.opengis.net/swe/1.0.1")
    protected Time time;
    @XmlElement(name = "Boolean", namespace = "http://www.opengis.net/swe/1.0.1")
    protected Boolean _boolean;
    @XmlElement(name = "Category", namespace = "http://www.opengis.net/swe/1.0.1")
    protected Category category;
    @XmlElement(name = "Text", namespace = "http://www.opengis.net/swe/1.0.1")
    protected Text text;
    @XmlElement(name = "QuantityRange", namespace = "http://www.opengis.net/swe/1.0.1")
    protected QuantityRange quantityRange;
    @XmlElement(name = "CountRange", namespace = "http://www.opengis.net/swe/1.0.1")
    protected CountRange countRange;
    @XmlElement(name = "TimeRange", namespace = "http://www.opengis.net/swe/1.0.1")
    protected TimeRange timeRange;
    @XmlElementRef(name = "AbstractDataRecord", namespace = "http://www.opengis.net/swe/1.0.1", type = JAXBElement.class)
    protected JAXBElement<? extends AbstractDataRecordType> abstractDataRecord;
    @XmlElementRef(name = "AbstractDataArray", namespace = "http://www.opengis.net/swe/1.0.1", type = JAXBElement.class)
    protected JAXBElement<? extends AbstractDataArrayType> abstractDataArray;
    @XmlElement(name = "ObservableProperty", namespace = "http://www.opengis.net/swe/1.0.1")
    protected ObservableProperty observableProperty;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String name;
    @XmlAttribute(namespace = "http://www.opengis.net/gml")
    @XmlSchemaType(name = "anyURI")
    protected String remoteSchema;
    /**
     * 
     * 
     */
    @XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
    public final static String TYPE = "simple";
    @XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
    @XmlSchemaType(name = "anyURI")
    protected String href;
    @XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
    @XmlSchemaType(name = "anyURI")
    protected String role;
    @XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
    @XmlSchemaType(name = "anyURI")
    protected String arcrole;
    @XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
    protected String title;
    @XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
    protected String show;
    @XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
    protected String actuate;

    /**
     * Gets the value of the count property.
     * 
     * @return
     *     possible object is
     *     {@link Count }
     *     
     */
    public Count getCount() {
        return count;
    }

    /**
     * Sets the value of the count property.
     * 
     * @param value
     *     allowed object is
     *     {@link Count }
     *     
     */
    public void setCount(Count value) {
        this.count = value;
    }

    public boolean isSetCount() {
        return (this.count!= null);
    }

    /**
     * Gets the value of the quantity property.
     * 
     * @return
     *     possible object is
     *     {@link Quantity }
     *     
     */
    public Quantity getQuantity() {
        return quantity;
    }

    /**
     * Sets the value of the quantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Quantity }
     *     
     */
    public void setQuantity(Quantity value) {
        this.quantity = value;
    }

    public boolean isSetQuantity() {
        return (this.quantity!= null);
    }

    /**
     * Gets the value of the time property.
     * 
     * @return
     *     possible object is
     *     {@link Time }
     *     
     */
    public Time getTime() {
        return time;
    }

    /**
     * Sets the value of the time property.
     * 
     * @param value
     *     allowed object is
     *     {@link Time }
     *     
     */
    public void setTime(Time value) {
        this.time = value;
    }

    public boolean isSetTime() {
        return (this.time!= null);
    }

    /**
     * Gets the value of the boolean property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getBoolean() {
        return _boolean;
    }

    /**
     * Sets the value of the boolean property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setBoolean(Boolean value) {
        this._boolean = value;
    }

    public boolean isSetBoolean() {
        return (this._boolean!= null);
    }

    /**
     * Gets the value of the category property.
     * 
     * @return
     *     possible object is
     *     {@link Category }
     *     
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Sets the value of the category property.
     * 
     * @param value
     *     allowed object is
     *     {@link Category }
     *     
     */
    public void setCategory(Category value) {
        this.category = value;
    }

    public boolean isSetCategory() {
        return (this.category!= null);
    }

    /**
     * Gets the value of the text property.
     * 
     * @return
     *     possible object is
     *     {@link Text }
     *     
     */
    public Text getText() {
        return text;
    }

    /**
     * Sets the value of the text property.
     * 
     * @param value
     *     allowed object is
     *     {@link Text }
     *     
     */
    public void setText(Text value) {
        this.text = value;
    }

    public boolean isSetText() {
        return (this.text!= null);
    }

    /**
     * Gets the value of the quantityRange property.
     * 
     * @return
     *     possible object is
     *     {@link QuantityRange }
     *     
     */
    public QuantityRange getQuantityRange() {
        return quantityRange;
    }

    /**
     * Sets the value of the quantityRange property.
     * 
     * @param value
     *     allowed object is
     *     {@link QuantityRange }
     *     
     */
    public void setQuantityRange(QuantityRange value) {
        this.quantityRange = value;
    }

    public boolean isSetQuantityRange() {
        return (this.quantityRange!= null);
    }

    /**
     * Gets the value of the countRange property.
     * 
     * @return
     *     possible object is
     *     {@link CountRange }
     *     
     */
    public CountRange getCountRange() {
        return countRange;
    }

    /**
     * Sets the value of the countRange property.
     * 
     * @param value
     *     allowed object is
     *     {@link CountRange }
     *     
     */
    public void setCountRange(CountRange value) {
        this.countRange = value;
    }

    public boolean isSetCountRange() {
        return (this.countRange!= null);
    }

    /**
     * Gets the value of the timeRange property.
     * 
     * @return
     *     possible object is
     *     {@link TimeRange }
     *     
     */
    public TimeRange getTimeRange() {
        return timeRange;
    }

    /**
     * Sets the value of the timeRange property.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeRange }
     *     
     */
    public void setTimeRange(TimeRange value) {
        this.timeRange = value;
    }

    public boolean isSetTimeRange() {
        return (this.timeRange!= null);
    }

    /**
     * Gets the value of the abstractDataRecord property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link VectorType }{@code >}
     *     {@link JAXBElement }{@code <}{@link NormalizedCurveType }{@code >}
     *     {@link JAXBElement }{@code <}{@link PositionType }{@code >}
     *     {@link JAXBElement }{@code <}{@link GeoLocationArea }{@code >}
     *     {@link JAXBElement }{@code <}{@link DataRecordType }{@code >}
     *     {@link JAXBElement }{@code <}{@link EnvelopeType }{@code >}
     *     {@link JAXBElement }{@code <}{@link ConditionalDataType }{@code >}
     *     {@link JAXBElement }{@code <}{@link AbstractDataRecordType }{@code >}
     *     {@link JAXBElement }{@code <}{@link ConditionalValueType }{@code >}
     *     {@link JAXBElement }{@code <}{@link SimpleDataRecordType }{@code >}
     *     
     */
    public JAXBElement<? extends AbstractDataRecordType> getAbstractDataRecord() {
        return abstractDataRecord;
    }

    /**
     * Sets the value of the abstractDataRecord property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link VectorType }{@code >}
     *     {@link JAXBElement }{@code <}{@link NormalizedCurveType }{@code >}
     *     {@link JAXBElement }{@code <}{@link PositionType }{@code >}
     *     {@link JAXBElement }{@code <}{@link GeoLocationArea }{@code >}
     *     {@link JAXBElement }{@code <}{@link DataRecordType }{@code >}
     *     {@link JAXBElement }{@code <}{@link EnvelopeType }{@code >}
     *     {@link JAXBElement }{@code <}{@link ConditionalDataType }{@code >}
     *     {@link JAXBElement }{@code <}{@link AbstractDataRecordType }{@code >}
     *     {@link JAXBElement }{@code <}{@link ConditionalValueType }{@code >}
     *     {@link JAXBElement }{@code <}{@link SimpleDataRecordType }{@code >}
     *     
     */
    public void setAbstractDataRecord(JAXBElement<? extends AbstractDataRecordType> value) {
        this.abstractDataRecord = ((JAXBElement<? extends AbstractDataRecordType> ) value);
    }

    public boolean isSetAbstractDataRecord() {
        return (this.abstractDataRecord!= null);
    }

    /**
     * Gets the value of the abstractDataArray property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link SquareMatrixType }{@code >}
     *     {@link JAXBElement }{@code <}{@link CurveType }{@code >}
     *     {@link JAXBElement }{@code <}{@link DataArrayType }{@code >}
     *     {@link JAXBElement }{@code <}{@link AbstractDataArrayType }{@code >}
     *     
     */
    public JAXBElement<? extends AbstractDataArrayType> getAbstractDataArray() {
        return abstractDataArray;
    }

    /**
     * Sets the value of the abstractDataArray property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link SquareMatrixType }{@code >}
     *     {@link JAXBElement }{@code <}{@link CurveType }{@code >}
     *     {@link JAXBElement }{@code <}{@link DataArrayType }{@code >}
     *     {@link JAXBElement }{@code <}{@link AbstractDataArrayType }{@code >}
     *     
     */
    public void setAbstractDataArray(JAXBElement<? extends AbstractDataArrayType> value) {
        this.abstractDataArray = ((JAXBElement<? extends AbstractDataArrayType> ) value);
    }

    public boolean isSetAbstractDataArray() {
        return (this.abstractDataArray!= null);
    }

    /**
     * Gets the value of the observableProperty property.
     * 
     * @return
     *     possible object is
     *     {@link ObservableProperty }
     *     
     */
    public ObservableProperty getObservableProperty() {
        return observableProperty;
    }

    /**
     * Sets the value of the observableProperty property.
     * 
     * @param value
     *     allowed object is
     *     {@link ObservableProperty }
     *     
     */
    public void setObservableProperty(ObservableProperty value) {
        this.observableProperty = value;
    }

    public boolean isSetObservableProperty() {
        return (this.observableProperty!= null);
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    public boolean isSetName() {
        return (this.name!= null);
    }

    /**
     * Gets the value of the remoteSchema property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemoteSchema() {
        return remoteSchema;
    }

    /**
     * Sets the value of the remoteSchema property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemoteSchema(String value) {
        this.remoteSchema = value;
    }

    public boolean isSetRemoteSchema() {
        return (this.remoteSchema!= null);
    }

    /**
     * Gets the value of the href property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHref() {
        return href;
    }

    /**
     * Sets the value of the href property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHref(String value) {
        this.href = value;
    }

    public boolean isSetHref() {
        return (this.href!= null);
    }

    /**
     * Gets the value of the role property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the value of the role property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRole(String value) {
        this.role = value;
    }

    public boolean isSetRole() {
        return (this.role!= null);
    }

    /**
     * Gets the value of the arcrole property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArcrole() {
        return arcrole;
    }

    /**
     * Sets the value of the arcrole property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArcrole(String value) {
        this.arcrole = value;
    }

    public boolean isSetArcrole() {
        return (this.arcrole!= null);
    }

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    public boolean isSetTitle() {
        return (this.title!= null);
    }

    /**
     * Gets the value of the show property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShow() {
        return show;
    }

    /**
     * Sets the value of the show property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShow(String value) {
        this.show = value;
    }

    public boolean isSetShow() {
        return (this.show!= null);
    }

    /**
     * Gets the value of the actuate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActuate() {
        return actuate;
    }

    /**
     * Sets the value of the actuate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActuate(String value) {
        this.actuate = value;
    }

    public boolean isSetActuate() {
        return (this.actuate!= null);
    }

}