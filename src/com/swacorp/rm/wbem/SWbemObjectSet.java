/**
 * 
 */
package com.swacorp.rm.wbem;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.impls.automation.IJIDispatch;

/**
 * @author <a href="mailto:e83800@wnco.com">Chris Bandy</a>
 * Created on: Jan 10, 2012
 */
public class SWbemObjectSet extends AbstractWbemSet<SWbemObject> {
	
	public SWbemObjectSet(IJIDispatch wbemObjectSet) throws JIException{
		super(wbemObjectSet);
	}
}
