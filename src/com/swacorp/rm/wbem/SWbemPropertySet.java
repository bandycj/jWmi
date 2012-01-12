/**
 * 
 */
package com.swacorp.rm.wbem;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIString;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;


/**
 * @author <a href="mailto:e83800@wnco.com">Chris Bandy</a>
 * Created on: Jan 10, 2012
 */
public class SWbemPropertySet extends AbstractWbemSet<SWbemProperty>{
	private final IJIDispatch wbemPropertySet;
	public SWbemPropertySet(IJIDispatch wbemPropertySet) throws JIException{
		super(wbemPropertySet);
		this.wbemPropertySet = wbemPropertySet;
	}
	
	public SWbemProperty add(String strName, int iCIMType, boolean bIsArray){
		Object[] iParams= new Object[]{
			new JIString(strName),
			new JIVariant(new Integer(iCIMType)),
			bIsArray
		};
		try {
			JIVariant[] result = wbemPropertySet.callMethodA("Add", iParams);
			return new SWbemProperty(DispatchUtils.getDispatch(result[0]));
		} catch (JIException | ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			return null;
		}
	}
}
