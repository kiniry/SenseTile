
package ie.ucd.sensetile.sensorml.generator;

import java.math.BigDecimal;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:element xmlns:xs="http://www.w3.org/2001/XMLSchema" name="SensorData">
 *   &lt;xs:complexType>
 *     &lt;xs:attribute type="xs:string" use="required" name="InputType"/>
 *     &lt;xs:attribute type="xs:string" use="required" name="Method"/>
 *     &lt;xs:attribute type="xs:string" use="required" name="OutPutType"/>
 *     &lt;xs:attribute type="xs:string" use="required" name="SensorName"/>
 *     &lt;xs:attribute type="xs:decimal" use="required" name="Version"/>
 *   &lt;/xs:complexType>
 * &lt;/xs:element>
 * </pre>
 */
public class SensorData
{
    private String inputType;
    private String method;
    private String outPutType;
    private String sensorName;
    private BigDecimal version;

    /** 
     * Get the 'InputType' attribute value.
     * 
     * @return value
     */
    public String getInputType() {
        return inputType;
    }

    /** 
     * Set the 'InputType' attribute value.
     * 
     * @param inputType
     */
    public void setInputType(String inputType) {
        this.inputType = inputType;
    }

    /** 
     * Get the 'Method' attribute value.
     * 
     * @return value
     */
    public String getMethod() {
        return method;
    }

    /** 
     * Set the 'Method' attribute value.
     * 
     * @param method
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /** 
     * Get the 'OutPutType' attribute value.
     * 
     * @return value
     */
    public String getOutPutType() {
        return outPutType;
    }

    /** 
     * Set the 'OutPutType' attribute value.
     * 
     * @param outPutType
     */
    public void setOutPutType(String outPutType) {
        this.outPutType = outPutType;
    }

    /** 
     * Get the 'SensorName' attribute value.
     * 
     * @return value
     */
    public String getSensorName() {
        return sensorName;
    }

    /** 
     * Set the 'SensorName' attribute value.
     * 
     * @param sensorName
     */
    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    /** 
     * Get the 'Version' attribute value.
     * 
     * @return value
     */
    public BigDecimal getVersion() {
        return version;
    }

    /** 
     * Set the 'Version' attribute value.
     * 
     * @param version
     */
    public void setVersion(BigDecimal version) {
        this.version = version;
    }
}
