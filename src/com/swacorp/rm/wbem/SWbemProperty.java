/**
 * 
 */
package com.swacorp.rm.wbem;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

/**
 * @author <a href="mailto:e83800@wnco.com">Chris Bandy</a> Created on: Jan 10,
 *         2012
 */
public class SWbemProperty extends AbstractWbemThing<SWbemProperty>{
	public SWbemProperty(IJIDispatch wbemProperty) {
		super(wbemProperty);
	}
	public Object Value(){
		try {
			return getDispatch().get("Value").getObject();
		} catch (JIException e) {
			e.printStackTrace();
			return null;
		}
	}
	public void Value(Object value){
		try {
			getDispatch().put("Value", JIVariant.makeVariant(value));
		} catch (JIException e) {
			e.printStackTrace();
		}
	}
	
	public int getCIMType() {
		try {
			return getDispatch().get("CIMType").getObjectAsInt();
		} catch (JIException e) {
			return -1;
		}
	}

	public Boolean isArray() {
		try {
			return getDispatch().get("IsArray").getObjectAsBoolean();
		} catch (JIException e) {
			return null;
		}
	}

	public Boolean isLocal() {
		try {
			return getDispatch().get("IsLocal").getObjectAsBoolean();
		} catch (JIException e) {
			return null;
		}
	}

	public String getName() {
		try {
			return getDispatch().get("Name").getObjectAsString2();
		} catch (JIException e) {
			return "";
		}
	}

	public Object getValue() {
		try {
			return getDispatch().get("Value").getObject();
		} catch (JIException e) {
			return null;
		}
	}
	
	@Override
	public String toString(){
		return this.getName();
	}
}
