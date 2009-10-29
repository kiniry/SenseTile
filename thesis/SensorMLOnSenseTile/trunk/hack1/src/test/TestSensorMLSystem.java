package test;

import ie.ucd.sensetile.sensorboard.simulator.ClonePacketBuilder;
import ie.ucd.sensetile.sensorboard.simulator.InstancePacket;
import ie.ucd.sensetile.sensorboard.simulator.SimulatorPacketInputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.vast.sensorML.*;
import org.vast.sensorML.system.SMLSystem;
import org.vast.xml.DOMHelper;
import org.vast.xml.DOMHelperException;
import org.apache.xerces.*;
import org.xml.*;
import org.vast.cdm.common.DataComponent;
import org.vast.data.DataBlockInt;
import org.vast.data.DataGroup;
import org.vast.data.DataValue;
import org.vast.process.*;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class TestSensorMLSystem {

	private DataProcess thermistor;
	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		TestSensorMLSystem tester =new TestSensorMLSystem();
		tester.init();
	}
	
	private void init(){
		SystemReaderV1 sysReaderv1 = new SystemReaderV1();
		//String senseTileSystem = "c://masters//tempproj//SensorML//Schemas//SenseTileSensorBoard.xml";
		
		DOMHelper domHelper;
		Element systemElement;
		try {
			//File f = new File();
			//FileInputStream inputxml = new FileInputStream(senseTileSystem);
			InputStream inputxml = this.getClass().getResourceAsStream("SenseTileSensorBoard.xml");
			
			domHelper = new DOMHelper(inputxml,true);
		    /*systemElement = domHelper.getRootElement();
		    System.out.println(domHelper.getFirstChildElement(systemElement).getLocalName());

			Element componentListElt = domHelper.getElement("member/System/components/ComponentList");
			NodeList components = domHelper.getAllChildElements(componentListElt);
			String compName;
			for (int i = 0; i <components.getLength(); i++) {
				Element component = (Element) components.item(i);
				compName = domHelper.getAttributeValue(component, "name");
				System.out.println("Component name = "+compName);


				if ("thermistor".equals(compName)) {
					
					this.thermistor = readProcessModel(domHelper, component,
							true);
					this.thermistor.init();
				}
			}*/
			
 			//d1.getInputList().getComponent("horizontalAngleOfView").getData().setDoubleValue(23);
			InstancePacket instancePacket1 = new InstancePacket();
			instancePacket1.setTemperature((short) 23);
			ClonePacketBuilder clonePacketBuilder = new ClonePacketBuilder(instancePacket1);
			SimulatorPacketInputStream inputStream = new SimulatorPacketInputStream(clonePacketBuilder);
			System.out.println("packet 1 "+inputStream.read().getTemperature());
			System.out.println("packet 2 "+inputStream.read().getTemperature());
			
		    //SMLUtils utils = new SMLUtils();
		    Element processElt = domHelper.getElement("member/*");
		    //System.out.println(processElt.getLocalName());
		    
		    SMLSystem smlsys= sysReaderv1.readSystem(domHelper, processElt);
		    System.out.println("System initialising");
			smlsys.init();
            System.out.println("txpacketTemp>>>> "+smlsys.getInputList()
            		.getComponent("txPacketTemperature").getClass());
            DataBlockInt pktemp = new DataBlockInt(1);
            pktemp.setIntValue(inputStream.read().getTemperature());
			smlsys.getInputList().getComponent("txPacketTemperature").setData(pktemp);
			
			smlsys.execute();
			System.out.println("result "+smlsys.getOutputList().getComponent("temperature").getData().getDoubleValue());


		    



		
		}
		 catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();

		
		}
	
	}
	
	private DataProcess readProcessModel(DOMHelper domHelper,
			Element component, boolean isExecutable) throws SMLException {

		// processModel or processChain
		if (domHelper.existElement(component, "SensorML/member/Component")) {
			System.out.println("components exist");

			Element processElt = domHelper
					.getElement(component, "SensorML/member/Component");

			String methodHRef = domHelper.getElement(processElt, "method")
					.getAttribute("xlink:href");

			if (methodHRef != null && !methodHRef.isEmpty()) {

				SMLUtils utils = new SMLUtils();
				utils.setCreateExecutableProcess(isExecutable);

				DataProcess process = utils.readProcess(domHelper, processElt);

				System.out.println("New process " + methodHRef + " read and initialized");

				return process;
			}
		}

		return null;
	}

}
