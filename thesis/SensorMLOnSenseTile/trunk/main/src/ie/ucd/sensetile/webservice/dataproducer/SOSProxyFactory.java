package ie.ucd.sensetile.webservice.dataproducer;

import ie.ucd.sensetile.webservice.dataproducer.SOSProxyRMI;

public class SOSProxyFactory {

    private static SOSProxyRMI proxy = null;

    public static SOSProxyIf getProxy() {
        if (proxy == null) {
            proxy = new SOSProxyRMI();
        }
        return proxy;
    }
}
