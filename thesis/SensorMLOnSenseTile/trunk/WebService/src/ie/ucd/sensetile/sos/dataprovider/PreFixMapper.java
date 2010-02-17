package ie.ucd.sensetile.sos.dataprovider;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

public class PreFixMapper extends NamespacePrefixMapper {
    @Override
    public String getPreferredPrefix(String namespace, String suggestion, boolean requirePrefix) {
        String prefix = "";
        if (namespace.contains("http://www.opengis.net/gml")){
            prefix = "gml";
        }
        else if (namespace.contains("http://relaxng.org/ns/structure")){
            prefix = "rng";
        }       
        else if (namespace.contains("http://www.opengis.net/sensorML")){
            prefix = "sml";
        }
        else if (namespace.contains("http://www.opengis.net/swe")){
            prefix = "swe";
        }
        else if (namespace.contains("http://www.w3.org/1999/xlink")){
            prefix = "xlink";
        }
        else if (namespace.contains("http://www.opengis.net/sos/1.0")){
            prefix = "sos";
        }
        else if (namespace.contains("http://www.opengis.net/tml")){
            prefix = "tml";
        }
        else if (namespace.contains("http://www.w3.org/2001/XMLSchema-instance")){
            prefix = "xsi";
        }
        else if (namespace.contains("http://www.opengis.net/ows")){
            prefix = "ows";
        }
        else if (namespace.contains("http://www.opengis.net/ogc")){
            prefix = "ogc";
        }
        else if (namespace.contains("http://www.opengis.net/om")){
            prefix = "om";
        }

        return prefix;
    }
}