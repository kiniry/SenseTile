
/**
 * SenseTileServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.1  Built on : Oct 19, 2009 (10:59:00 EDT)
 */

    package ie.ucd.sensetile.sos.dataprovider.service;

    /**
     *  SenseTileServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class SenseTileServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public SenseTileServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public SenseTileServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for getCapabilities method
            * override this method for handling normal response from getCapabilities operation
            */
           public void receiveResultgetCapabilities(
                    ie.ucd.sensetile.sos.dataprovider.service.SenseTileServiceStub.GetCapabilitiesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getCapabilities operation
           */
            public void receiveErrorgetCapabilities(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for describeSensor method
            * override this method for handling normal response from describeSensor operation
            */
           public void receiveResultdescribeSensor(
                    ie.ucd.sensetile.sos.dataprovider.service.SenseTileServiceStub.DescribeSensorResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from describeSensor operation
           */
            public void receiveErrordescribeSensor(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getObservation method
            * override this method for handling normal response from getObservation operation
            */
           public void receiveResultgetObservation(
                    ie.ucd.sensetile.sos.dataprovider.service.SenseTileServiceStub.GetObservationResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getObservation operation
           */
            public void receiveErrorgetObservation(java.lang.Exception e) {
            }
                
               // No methods generated for meps other than in-out
                


    }
    