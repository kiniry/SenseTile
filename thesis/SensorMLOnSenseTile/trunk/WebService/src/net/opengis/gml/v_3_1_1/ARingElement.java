//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.02.01 at 05:02:44 PM GMT 
//


package net.opengis.gml.v_3_1_1;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

public class ARingElement
    extends JAXBElement<RingType>
{

    protected final static QName NAME = new QName("http://www.opengis.net/gml", "Ring");

    public ARingElement(RingType value) {
        super(NAME, ((Class) RingType.class), null, value);
    }

    public ARingElement() {
        super(NAME, ((Class) RingType.class), null, null);
    }

}