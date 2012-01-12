/**
 * 
 */
package com.swacorp.rm.wbem;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.impls.automation.IJIDispatch;

/**
 * @author <a href="mailto:e83800@wnco.com">Chris Bandy</a> Created on: Jan 10,
 *         2012
 */
public class SWbemMethod extends AbstractWbemThing<SWbemMethod>{

	public SWbemMethod(IJIDispatch wbemMethod) {
		super(wbemMethod);
	}

	public SWbemObject InParameters() {
		try {
			return new SWbemObject(getDispatch(), "InParameters");
		} catch (JIException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String Name() {
		try {
			return getDispatch().get("Name").getObjectAsString().getString();
		} catch (JIException e) {
			e.printStackTrace();
			return null;
		}
	}

	public SWbemObject OutParameters() {
		try {
			return new SWbemObject(getDispatch(), "OutParameters");
		} catch (JIException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String toString() {
		return this.Name();
	}
}
