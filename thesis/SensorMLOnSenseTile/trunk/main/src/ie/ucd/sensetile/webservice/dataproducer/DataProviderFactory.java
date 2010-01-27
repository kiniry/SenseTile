package ie.ucd.sensetile.webservice.dataproducer;

import ie.ucd.sensetile.webservice.dataproducer.DataProviderProxyRMI;

public class DataProviderFactory {

    private static DataProviderProxyRMI proxy = null;

    public static DataProviderProxyIf getProxy() {
        if (proxy == null) {
            proxy = new DataProviderProxyRMI();
        }
        return proxy;
    }
}
