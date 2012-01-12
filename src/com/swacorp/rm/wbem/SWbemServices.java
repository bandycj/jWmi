/**
 * 
 */
package com.swacorp.rm.wbem;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIArray;
import org.jinterop.dcom.core.JIString;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;


/**
 * @author <a href="mailto:e83800@wnco.com">Chris Bandy</a> Created on: Jan 6,
 *         2012
 */
public class SWbemServices {
	private final int RETURN_IMMEDIATE = 0x10;
	private final int FORWARD_ONLY = 0x20;

	private final IJIDispatch swbemServices;

	public SWbemServices(IJIDispatch swbemServices) {
		this.swbemServices = swbemServices;
	}

	public SWbemObjectSet ExecQuery(String strQuery) throws JIException {

		Object[] iParams = new Object[] {
				new JIString(strQuery),
				JIVariant.OPTIONAL_PARAM(),
				new JIVariant(new Integer(RETURN_IMMEDIATE + FORWARD_ONLY))
		};
		JIVariant[] servicesSet = swbemServices.callMethodA("ExecQuery", iParams);

		return new SWbemObjectSet(DispatchUtils.getDispatch(servicesSet[0]));
	}

	public SWbemObject ExecMethod(String strObjectPath, String strMethodName, JIArray inputParams) throws JIException {
		Object[] iParams = new Object[] {
				new JIString(strObjectPath),
				new JIString(strMethodName),
				inputParams,
				new JIVariant(new Integer(RETURN_IMMEDIATE + FORWARD_ONLY)),
				JIVariant.OPTIONAL_PARAM()
		};

		JIVariant[] result = swbemServices.callMethodA("ExecMethod", iParams);

		return new SWbemObject(result[0]);
	}

	public SWbemObject Get(String strObjectPath) throws JIException {
		Object[] iParams = new Object[] {
				new JIString(strObjectPath),
				new JIVariant(0),
				JIVariant.OPTIONAL_PARAM()
		};
		
		JIVariant[] result = swbemServices.callMethodA("Get",iParams);
		
		return new SWbemObject(result[0]);
	}
}
