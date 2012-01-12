/**
 * 
 */
package com.swacorp.rm.wbem;

import java.io.IOException;
import java.util.logging.Level;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.common.JISystem;
import org.jinterop.dcom.core.JIClsid;
import org.jinterop.dcom.core.JIComServer;
import org.jinterop.dcom.core.JISession;
import org.jinterop.dcom.core.JIString;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.JIObjectFactory;
import org.jinterop.dcom.impls.automation.IJIDispatch;

/**
 * @author <a href="mailto:e83800@wnco.com">Chris Bandy</a> Created on: Jan 6,
 *         2012
 */
public class SWbemLocator {
	private static JISession session;
	private static IJIDispatch wbemLocator;
	
	static{
		try {
			session = configAndConnectDCom();
			// This is the CLSID of WbemScripting.SWbemLocator
			JIComServer wbemLocatorCom = new JIComServer(JIClsid.valueOf("76a64158-cb41-11d1-8b02-00600806d9b6"), session);
			wbemLocator = (IJIDispatch) JIObjectFactory.narrowObject(wbemLocatorCom.createInstance().queryInterface(IJIDispatch.IID));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private SWbemLocator() {}
	
	private static JISession configAndConnectDCom() throws Exception {
		JISystem.getLogger().setUseParentHandlers(true);
		JISystem.getLogger().setLevel(Level.FINEST);

		try {
			JISystem.setInBuiltLogHandler(false);
		} catch (IOException ignored) {
			;
		}

		JISystem.setAutoRegisteration(true);

		JISession dcomSession = JISession.createSession();
		dcomSession.useSessionSecurity(true);
		dcomSession.useNTLMv2(true);
		return dcomSession;
	}
	
	public static SWbemServices ConnectServer(String strServer, String strNamespace) throws JIException{
		Object[] iParams = new Object[] {
			new JIString(strServer),
			new JIString(strNamespace),
			JIVariant.OPTIONAL_PARAM(),
			JIVariant.OPTIONAL_PARAM(),
			JIVariant.OPTIONAL_PARAM(),
			JIVariant.OPTIONAL_PARAM(),
			new Integer(0),
			JIVariant.OPTIONAL_PARAM()
		};
		JIVariant results[] = wbemLocator.callMethodA("ConnectServer", iParams);
		 
        return new SWbemServices((IJIDispatch) JIObjectFactory.narrowObject(results[0].getObjectAsComObject()));
	}
	
	public static void shutdown() throws JIException{
		JISession.destroySession(session);
	}
}
