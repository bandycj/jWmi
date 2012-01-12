/**
 * 
 */
package com.swacorp.rm.cimv2;

import java.util.HashMap;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIString;
import org.jinterop.dcom.core.JIVariant;

import com.swacorp.rm.wbem.SWbemObject;

/**
 * @author <a href="mailto:e83800@wnco.com">Chris Bandy</a>
 * Created on: Jan 11, 2012
 */
public class Win32_Process {
	public static HashMap<Integer,String> TERMINATE_RESULT = new HashMap<Integer,String>();
	
	static{
		TERMINATE_RESULT.put(0,"Successfully Terminated");
		TERMINATE_RESULT.put(2,"Access Denied");
		TERMINATE_RESULT.put(3,"Insufficient Privilege");
		TERMINATE_RESULT.put(8,"Unknown Failure");
		TERMINATE_RESULT.put(9,"Path Not Found");
		TERMINATE_RESULT.put(21,"Invalid Parameter");
	}
	
	private final SWbemObject swbemObject;
	
	public Win32_Process (SWbemObject swbemObject){
		this.swbemObject = swbemObject;
	}
	
	public int Create(String CommandLine,String CurrentDirectory){
		Object[] iParams = new Object[] {
				new JIString(CommandLine),
				new JIString(CurrentDirectory),
				JIVariant.OPTIONAL_PARAM(),
				new JIVariant(0, true)
		};
		try {
			JIVariant[] results = swbemObject.ExecMethod_("Create", iParams);
			if (results.length >1){
				return results[1].getObjectAsInt();
			}
		} catch (JIException e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
	public String Terminate(){
		try {
			JIVariant[] results = swbemObject.ExecMethod_("Terminate", new Object[]{});
			for (JIVariant result:results){
				return TERMINATE_RESULT.get(result.getObjectAsInt());
			}
		} catch (JIException e) {
			e.printStackTrace();
		}
		return "error";
	}
}
