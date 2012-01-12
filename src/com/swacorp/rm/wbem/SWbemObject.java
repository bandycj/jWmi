/**
 * 
 */
package com.swacorp.rm.wbem;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JICallBuilder;
import org.jinterop.dcom.core.JIString;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;


/**
 * @author <a href="mailto:e83800@wnco.com">Chris Bandy</a> Created on: Jan 10,
 *         2012
 */
public class SWbemObject extends AbstractWbemThing<SWbemObject> {

	public SWbemObject(IJIDispatch wbemObject) {
		super(wbemObject);
	}

	public SWbemObject(JIVariant variant) throws JIException {
		super(DispatchUtils.getDispatch(variant));
	}

	public SWbemObject(IJIDispatch dispatch, String property) throws JIException {
		super(DispatchUtils.getDispatch(dispatch.get(property)));
	}

	public SWbemObject SpawnInstance_() throws JIException {
		return new SWbemObject(getDispatch().callMethodA("SpawnInstance_"));
	}

	public JIVariant ExecMethod_(String strMethodName, SWbemObject objwbemInParams) throws JIException {
		
		Object[] iParams = new Object[]{
				JIVariant.makeVariant(objwbemInParams.getDispatch(),true)
				};
		JIVariant[] result = getDispatch().callMethodA(strMethodName, iParams);
		System.out.println(result);
		return null;
	}

	public JIVariant ExecMethod_(JICallBuilder call) throws JIException {
		Object result = getDispatch().call(call);
		System.out.println(result);
		return null;
	}
	
	public JIVariant[] ExecMethod_(String strMethodName, Object[] objwbemInParams) throws JIException {
		return getDispatch().callMethodA(strMethodName, objwbemInParams);
	}

	public SWbemPropertySet Properties_() throws JIException {
		return new SWbemPropertySet(DispatchUtils.getDispatch(getDispatch().get("Properties_")));
	}

	public String GetObjectText_() throws JIException {
		Object[] iParams = new Object[] {
			JIVariant.OPTIONAL_PARAM()
		};
		JIVariant[] servicesSet = getDispatch().callMethodA("GetObjectText_", iParams);
		JIString string = (JIString) servicesSet[0].getObjectAsString();
		return string.toString();
	}

	public SWbemMethodSet Methods_() throws JIException {
		JIVariant variant = getDispatch().get("Methods_");
		return new SWbemMethodSet(DispatchUtils.getDispatch(variant));
	}

	public SWbemMethod Methods_(String methodName) throws JIException {
		return Methods_().item(methodName);
	}

	@Override
	public String toString() {
		try {
			return this.GetObjectText_();
		} catch (JIException e) {
			e.printStackTrace();
			return "";
		}
	}
}
