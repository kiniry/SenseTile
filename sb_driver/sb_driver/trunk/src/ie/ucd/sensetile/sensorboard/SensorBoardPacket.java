package ie.ucd.sensetile.sensorboard;

import java.util.Date;

public interface SensorBoardPacket {
  
  // indexes
  
  Date getDate();
  
  int getIndex();
  
  // sensors
  
  int getTemperature();
  
  int getTemperatureRaw();
  
  int getPressure();

  int getPressureRaw();

  int getLightLevel();

  int getLighLevelRaw();

  int getAccelerometerX();

  int getAccelerometerXRaw();

  int getAccelerometerY();

  int getAccelerometerYRaw();

  int getAccelerometerZ();

  int getAccelerometerZRaw();
  
  // supply

  int getSupplyVoltage();

  int getSupplyVoltageRaw();

  int getSupplyCurrent();

  int getSupplyCurrentRaw();
  
  // ADC channels fragments
  
  
  
}