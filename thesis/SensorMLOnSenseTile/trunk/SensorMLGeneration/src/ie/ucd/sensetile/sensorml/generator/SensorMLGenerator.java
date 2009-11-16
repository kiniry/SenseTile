package ie.ucd.sensetile.sensorml.generator;

import ie.ucd.sensetile.sensorml.process.Connection;
import ie.ucd.sensetile.sensorml.process.Inputs;
import ie.ucd.sensetile.sensorml.process.IoComponentPropertyType;
import ie.ucd.sensetile.sensorml.process.Member;
import ie.ucd.sensetile.sensorml.process.Method;
import ie.ucd.sensetile.sensorml.process.SensorML;
import ie.ucd.sensetile.swe.Count;

import java.awt.Component;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.JiBXException;


    /* Generates SensorML
     *     * ,SensorMLGenerator,none,School of Computer Science and Informatics, UCD,2009/10/29
     * @author Ciaran Palmer.
     * @version Revision: 1.00.
     */
public /*@ nullable_by_default @*/ class SensorMLGenerator  {

     private SensorML sensorml;
     private Member member;
     private IoComponentPropertyType input;
     
     public /*@ non_null @*/ Inputs getInputs;
     public /*@ non_null @*/ Method getMethod;
     public /*@ non_null @*/ Component GetComponent;
     public SensorML getSensorML(){
         return this.sensorml;
     }

     //@ assignable smlcomponent;
     //@ ensures smlcomponent / =Void;
     public void createComponent(final/*@ non_null @*/ SensorData sensorData) {
              member = new Member();
              sensorml = new SensorML(member);
     }

     //@ assignable input;
     //@ ensures input / =Void;
     public void createInput(final/*@ non_null @*/ SensorData sensorData) {
         if (sensorData.equals("Count")){
             input = new IoComponentPropertyType("input",new Count());
         }
     }

     //@ assignable method;
     //@ ensures method / =Void;
     public void createMethod(final/*@ non_null @*/ SensorData sensorData) {}

     public void marshal(final/*@ non_null @*/ String outPutFile, SensorML sensorMLroot){
         try {
             IBindingFactory bfact =
                 BindingDirectory.getFactory(SensorML.class);
             IMarshallingContext mctx = bfact.createMarshallingContext();
             try {
                 mctx.marshalDocument(sensorMLroot, "UTF-8", null,
                     new FileOutputStream(outPutFile));
             } catch (FileNotFoundException e) {
                 e.printStackTrace();
             }

         } catch (JiBXException e) {
             e.printStackTrace();
         }
     }
}
