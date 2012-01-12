/**
 * 
 */
package com.swacorp.rm.wbem;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;


/**
 * @author <a href="mailto:e83800@wnco.com">Chris Bandy</a> Created on: Jan 10,
 *         2012
 */
public abstract class AbstractWbemThing<T> {
	
	private final IJIDispatch dispatch;
	public AbstractWbemThing(IJIDispatch dispatch){
		this.dispatch = dispatch;
	}
	
	public IJIDispatch getDispatch(){
		return this.dispatch;
	}
	
	public static <T> T create(Class<T> _class, IJIDispatch dispatch) {
		try {
			Constructor<T> constructor = _class.getConstructor(IJIDispatch.class);
			return constructor.newInstance(dispatch);
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static <T> T create(Class<T> _class, JIVariant variant) {
		return create(_class, DispatchUtils.getDispatch(variant));
	}
}
