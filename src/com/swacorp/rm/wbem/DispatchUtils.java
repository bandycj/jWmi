/**
 * 
 */
package com.swacorp.rm.wbem;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.JIObjectFactory;
import org.jinterop.dcom.impls.automation.IJIDispatch;

/**
 * @author <a href="mailto:e83800@wnco.com">Chris Bandy</a> Created on: Jan 10,
 *         2012
 */
public class DispatchUtils {

	public static IJIDispatch getDispatch(JIVariant variant) {
		try {
			return (IJIDispatch) JIObjectFactory.narrowObject(variant.getObjectAsComObject());
		} catch (JIException e) {
			e.printStackTrace();
			return null;
		}
	}
}
