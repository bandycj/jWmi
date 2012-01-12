/**
 * 
 */
package com.swacorp.rm.wbem;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.IJIComObject;
import org.jinterop.dcom.core.JIArray;
import org.jinterop.dcom.core.JIString;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.JIObjectFactory;
import org.jinterop.dcom.impls.automation.IJIDispatch;
import org.jinterop.dcom.impls.automation.IJIEnumVariant;


/**
 * @author <a href="mailto:e83800@wnco.com">Chris Bandy</a> Created on: Jan 10,
 *         2012
 * @param <D>
 */
public abstract class AbstractWbemSet<T extends AbstractWbemThing<T>> {
//	private final JIVariant[] array;
	private final Class<T> tClass;
	private final IJIDispatch swbemThing;
	
	@SuppressWarnings("unchecked")
	public AbstractWbemSet(IJIDispatch swbemThing) throws JIException {
		this.swbemThing = swbemThing;
		Type type = getClass().getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			ParameterizedType paramType = (ParameterizedType) type;
			this.tClass = (Class<T>) paramType.getActualTypeArguments()[0];
		} else {
			this.tClass = null;
		}
	}

	public List<T> getValues() throws JIException {
		ArrayList<T> returnList = new ArrayList<T>();
		JIVariant newEnumvariant = swbemThing.get("_NewEnum");
		IJIComObject enumComObject = newEnumvariant.getObjectAsComObject();
		IJIEnumVariant enumVariant = (IJIEnumVariant) JIObjectFactory.narrowObject(enumComObject.queryInterface(IJIEnumVariant.IID));

		Object[] elements = enumVariant.next(1);
		JIArray aJIArray = (JIArray) elements[0];

		JIVariant[] array = (JIVariant[]) aJIArray.getArrayInstance();
		for (JIVariant variant:array){
			returnList.add(T.create(tClass,DispatchUtils.getDispatch(variant)));
		}
		return returnList;
	}
	
	public T item(String name) throws JIException {
		Object[] iParams = new Object[]{new JIString(name)};
		IJIDispatch dispatch = DispatchUtils.getDispatch(swbemThing.callMethodA("Item", iParams)[0]);
		
		return T.create(tClass, dispatch);
	}
	
	public int Count() throws JIException{
		return swbemThing.get("Count").getObjectAsInt();
	}
}
