/**
 * 
 */
package com.swacorp.rm.wbem;

/**
 * @author <a href="mailto:e83800@wnco.com">Chris Bandy</a> Created on: Jan 10,
 *         2012
 */
public class WbemCimTypeEnum {
	/**
	 * Signed 16-bit integer
	 */
	static final int wbemCimtypeSint16 = 0x2;

	/**
	 * Signed 32-bit integer
	 */
	static final int wbemCimtypeSint32 = 0x3;

	/**
	 * 32-bit real number
	 */
	static final int wbemCimtypeReal32 = 0x4;

	/**
	 * 64-bit real number
	 */
	static final int wbemCimtypeReal64 = 0x5;

	/**
	 * String
	 */
	static final int wbemCimtypeString = 0x8;

	/**
	 * Boolean value
	 */
	static final int wbemCimtypeBoolean = 0xB;

	/**
	 * CIM object
	 */
	static final int wbemCimtypeObject = 0xD;

	/**
	 * Signed 8-bit integer
	 */
	static final int wbemCimtypeSint8 = 0x10;

	/**
	 * Unsigned 8-bit integer
	 */
	static final int wbemCimtypeUint8 = 0x11;

	/**
	 * Unsigned 16-bit integer
	 */
	static final int wbemCimtypeUint16 = 0x12;

	/**
	 * Unsigned 32-bit integer
	 */
	static final int wbemCimtypeUint32 = 0x13;

	/**
	 * Signed 64-bit integer
	 */
	static final int wbemCimtypeSint64 = 0x14;

	/**
	 * Unsigned 64-bit integer
	 */
	static final int wbemCimtypeUint64 = 0x15;

	/**
	 * Date/time value
	 */
	static final int wbemCimtypeDatetime = 0x65;

	/**
	 * Reference to a CIM object
	 */
	static final int wbemCimtypeReference = 0x66;

	/**
	 * 16-bit character
	 */
	static final int wbemCimtypeChar16 = 0x67;
}
