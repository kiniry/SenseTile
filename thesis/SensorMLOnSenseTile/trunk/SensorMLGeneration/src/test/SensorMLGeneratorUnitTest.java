package test;

import ie.ucd.sensetile.sensorml.generator.SensorData;
import ie.ucd.sensetile.sensorml.generator.SensorMLGenerator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;
import org.junit.Before;
import org.junit.Test;



public class SensorMLGeneratorUnitTest {
    
    private String LIGHTSENSOR ="/LightSensorConvertor.xml";
    private SensorData sensorData;
    private SensorMLGenerator smlgen;

    @Before
    public void setUp() throws Exception {
        smlgen = new SensorMLGenerator();
    }

    @Test
    public void createLightComponent() {
        IBindingFactory bfact;
        try {
            bfact = BindingDirectory.getFactory(SensorData.class);

            IUnmarshallingContext uctx;

            uctx = bfact.createUnmarshallingContext();
            System.out.println(this.getClass().getResource(LIGHTSENSOR).getFile());

            sensorData = (SensorData)uctx.unmarshalDocument
                (new FileInputStream(this.getClass().getResource(LIGHTSENSOR).getFile()), null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JiBXException e) {
            e.printStackTrace();
        }
        
        smlgen.createComponent(sensorData);
        smlgen.createInput(sensorData);
        smlgen.marshal("fred.xml",smlgen.getSensorML());

    }
}
