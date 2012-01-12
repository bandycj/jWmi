package com.swacorp.rm;

import com.swacorp.rm.cimv2.Win32_Process;
import com.swacorp.rm.wbem.SWbemLocator;
import com.swacorp.rm.wbem.SWbemObject;
import com.swacorp.rm.wbem.SWbemObjectSet;
import com.swacorp.rm.wbem.SWbemServices;

public class WmiBridge {

	public static void main(String[] args) {
		try {
			SWbemServices wbemServices = SWbemLocator.ConnectServer("localhost", "root\\cimv2");
			int megaBytes = 1024*1024;
			int counter = 0;
			Runtime runtime = Runtime.getRuntime();
			while (true) {
				Win32_Process win32Process = new Win32_Process(wbemServices.Get("Win32_Process"));
				int processId = win32Process.Create("calc.exe", "C:\\windows\\system32");
				
				SWbemObjectSet results = wbemServices.ExecQuery("SELECT * FROM Win32_Process WHERE ProcessId='" + processId + "'");
				for (SWbemObject result : results.getValues()) {
					Win32_Process process = new Win32_Process(result);
					process.Terminate();
				}
				counter++;
				System.out.println(counter);
				System.out.println("Used Memory: "  
			               +(runtime.totalMemory() - runtime.freeMemory())/megaBytes);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				SWbemLocator.shutdown();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

}