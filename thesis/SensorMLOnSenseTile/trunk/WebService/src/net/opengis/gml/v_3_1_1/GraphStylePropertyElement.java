//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.02.01 at 05:02:44 PM GMT 
//


package net.opengis.gml.v_3_1_1;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

public class GraphStylePropertyElement
    extends JAXBElement<GraphStylePropertyType>
{

    protected final static QName NAME = new QName("http://www.opengis.net/gml", "graphStyle");

    public GraphStylePropertyElement(GraphStylePropertyType value) {
        super(NAME, ((Class) GraphStylePropertyType.class), null, value);
    }

    public GraphStylePropertyElement() {
        super(NAME, ((Class) GraphStylePropertyType.class), null, null);
    }

}