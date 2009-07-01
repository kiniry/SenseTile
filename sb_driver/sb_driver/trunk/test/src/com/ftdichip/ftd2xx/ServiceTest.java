package com.ftdichip.ftd2xx;

import static org.junit.Assert.*;

import org.junit.Test;

public class ServiceTest {

  @Test
  public void testListDevices() throws FTD2xxException {
    assertTrue(Service.listDevices().length >= 0);
  }
  
//  @Test
//  public void testStartInputTask() {
//    fail("Not yet implemented");
//  }
//
//  @Test
//  public void testStopInputTask() {
//    fail("Not yet implemented");
//  }

}
