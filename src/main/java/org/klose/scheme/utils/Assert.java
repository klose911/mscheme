package org.klose.scheme.utils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.helpers.MessageFormatter;

import java.util.Collection;
import java.util.Map;

public abstract class Assert {
	//TODO add: isNumber,isInteger,isDate
	/**
	 * Assert a boolean expression, throwing <code>IllegalArgumentException</code>
	 * if the test result is <code>false</code>.
	 * <pre class="code">Assert.isTrue(i &gt; 0, "The value must be greater than zero");</pre>
	 * @param expression a boolean expression
	 * @param message the exception message to use if the assertion fails
	 * @throws IllegalArgumentException if expression is <code>false</code>
	 */
	public static void isTrue(boolean expression, String message , Object... messageParas) {
		if (!expression) {
			throwException(message,messageParas);
		}
	}
	

	/**
	 * @see isTrue(boolean, String)
	 */
	public static void isTrue(boolean expression) {
		isTrue(expression, "[Assertion failed] - this expression must be true");
	}
	/**
	 * Assert a boolean expression, throwing <code>IllegalArgumentException</code>
	 * if the test result is <code>false</code>.
	 * <pre class="code">Assert.isFalse(i &gt; 0, "The value must be greater than zero");</pre>
	 * @param expression a boolean expression
	 * @param message the exception message to use if the assertion fails
	 * @throws IllegalArgumentException if expression is <code>false</code>
	 */
	public static void isFalse(boolean expression, String message,Object... messageParas) {
		if (expression) {
			throwException(message,messageParas);
		}
	}

	/**
	 * @see isFalse(boolean,String) 
	 */
	public static void isFalse(boolean expression) {
		isFalse(expression, "[Assertion failed] - this expression must be false");
	}
	/**
	 * Assert that an object is <code>null</code> .
	 * <pre class="code">Assert.isNull(value, "The value must be null");</pre>
	 * @param object the object to check
	 * @param message the exception message to use if the assertion fails
	 * @throws IllegalArgumentException if the object is not <code>null</code>
	 */
	public static void isNull(Object object, String message,Object... messageParas) {
		if (object != null) {
			throwException(message,messageParas);
		}
	}

	/**
	 *@see isNull(Object, String) 
	 */
	public static void isNull(Object object) {
		isNull(object, "[Assertion failed] - the object argument must be null");
	}

	/**
	 * Assert that an object is not <code>null</code> .
	 * <pre class="code">Assert.isNotNull(clazz, "The class must not be null");</pre>
	 * @param object the object to check
	 * @param message the exception message to use if the assertion fails
	 * @throws IllegalArgumentException if the object is <code>null</code>
	 */
	public static void isNotNull(Object object, String message,Object... messageParas) {
		if (object == null) {
			throwException(message,messageParas);
		}
	}


	public static void isNotNull(Object object) {
		isNotNull(object, "[Assertion failed] - this argument is required; it must not be null");
	}



	/**
	 * Assert that the given String has valid text content; that is, it must not
	 * be <code>null</code> and must contain at least one non-whitespace character.
	 * <pre class="code">Assert.isNotEmpty(name, "'name' must not be empty");</pre>
	 * Test Data:
	 * <pre class="code">
	 *   isBlank(null)      : fail;
	 *   isBlank("")        : fail;
	 *   isBlank(" ")       : fail;
	 *   isBlank("bob")     : pass;
	 *   isBlank("  bob  ") : pass;</pre>
	 * @param text the String to check
	 * @param message the exception message to use if the assertion fails
	 * @see StringUtils#isNotBlank
	 */
	public static void isNotEmpty(String text, String message,Object... messageParas) {
		if (!StringUtils.isNotBlank(text)) {
			throwException(message,messageParas);
		}
	}

	/**
	 *@see isNotEmpty(String, String)
	 */
	public static void isNotEmpty(String text) {
		isNotEmpty(text,
				"[Assertion failed] - this String argument must have text; it must not be null, empty, or blank");
	}

	/**
	 * Assert that the given String doesn't have valid text content; that is, it must 
	 * be <code>null</code> or must not contain any non-whitespace character.
	 * <pre class="code">Assert.isNotEmpty(name, "'name' must not be empty");</pre>
	 * Test Data:
	 * <pre class="code">
	 *   isBlank(null)      : pass;
	 *   isBlank("")        : pass;
	 *   isBlank(" ")       : pass;
	 *   isBlank("bob")     : fail;
	 *   isBlank("  bob  ") : fail;</pre>
	 * @param text the String to check
	 * @param message the exception message to use if the assertion fails
	 * @see StringUtils#isNotBlank
	 */
	public static void isEmpty(String text, String message,Object... messageParas) {		
		if (!StringUtils.isBlank(text)) {
			throwException(message,messageParas);
		}
	}

	/**
	 * @see isEmpty(String, String)
	 */
	public static void isEmpty(String text) {
		isEmpty(text,
				"[Assertion failed] - this String argument must have text; it must not be null, empty, or blank");
	}

	

	/**
	 * Assert that an array has elements; that is, it must not be
	 * <code>null</code> and must have at least one element.
	 * <pre class="code">Assert.isNotEmpty(array, "The array must have elements");</pre>
	 * @param array the array to check
	 * @param message the exception message to use if the assertion fails
	 * @throws IllegalArgumentException if the object array is <code>null</code> or has no elements
	 */
	public static void isNotEmpty(Object[] array, String message,Object... messageParas) {
		if (!ArrayUtils.isNotEmpty(array)) {
			throwException(message,messageParas);
		}
	}

	/**
	 * @see isNotEmpty(Object[], String)
	 */
	public static void isNotEmpty(Object[] array) {
		isNotEmpty(array, "[Assertion failed] - this array must not be empty: it must contain at least 1 element");
	}

	/**
	 * Assert that a Collection has elements; that is, it must not be
	 * <code>null</code> and must have at least one element.
	 * <pre class="code">Assert.isNotEmpty(collection, "The Collection must have elements");</pre>
	 * @param collection the collection to check
	 * @param message the exception message to use if the assertion fails
	 * @throws IllegalArgumentException if the collection is <code>null</code> or has no elements
	 */
	@SuppressWarnings("rawtypes")
	public static void isNotEmpty(Collection collection, String message,Object... messageParas) {
		if (!CollectionUtils.isNotEmpty(collection)) {
			throwException(message,messageParas);
		}
	}

	/**
	 * @see isNotEmpty(Collection, String)
	 */
	@SuppressWarnings("rawtypes")
	public static void isNotEmpty(Collection collection) {
		isNotEmpty(collection, "[Assertion failed] - this array must not be empty: it must contain at least 1 element");
	}

	/**
	 * Assert that a Map has elements; that is, it must not be
	 * <code>null</code> and must have at least one element.
	 * <pre class="code">Assert.isNotEmpty(map, "The map must have elements");</pre>
	 * @param map the array to check
	 * @param message the exception message to use if the assertion fails
	 * @throws IllegalArgumentException if the map is <code>null</code> or has no elements
	 */
	@SuppressWarnings("rawtypes")
	public static void isNotEmpty(Map map, String message,Object... messageParas) {
		if (!MapUtils.isNotEmpty(map)) {
			throwException(message,messageParas);
		}

	}

	/**
	 * @see isNotEmpty(Map, String)
	 */
	@SuppressWarnings("rawtypes")
	public static void isNotEmpty(Map map) {
		isNotEmpty(map, "[Assertion failed] - this array must not be empty: it must contain at least 1 element");
	}


	/**
	 * Assert that an array doesn't elements; that is, it must be
	 * <code>null</code> or must not have any  element.
	 * <pre class="code">Assert.isEmpty(array, "The array must not have elements");</pre>
	 * @param array the array to check
	 * @param message the exception message to use if the assertion fails
	 * @throws IllegalArgumentException if the object array is <code>not null</code> and has elements
	 */
	public static void isEmpty(Object[] array, String message,Object... messageParas) {
		if (!ArrayUtils.isEmpty(array)) {
			throwException(message,messageParas);
		}

	}

	/**
	 * @see isEmpty(Object[], String)
	 */
	public static void isEmpty(Object[] array) {
		isEmpty(array, "[Assertion failed] - this array must not be empty: it must contain at least 1 element");
	}

	/**
	 * Assert that a collection doesn't elements; that is, it must be
	 * <code>null</code> or must not have any  element.
	 * <pre class="code">Assert.isEmpty(collection, "The collection must not have elements");</pre>
	 * @param collection the collection to check
	 * @param message the exception message to use if the assertion fails
	 * @throws IllegalArgumentException if the object collection is not <code>null</code> and has elements
	 */
	@SuppressWarnings("rawtypes")
	public static void isEmpty(Collection collection, String message,Object... messageParas) {
		if (!CollectionUtils.isEmpty(collection)) {
			throwException(message,messageParas);
		}
	}

	/**
	 * @see isEmpty(Collection, String)
	 */
	@SuppressWarnings("rawtypes")
	public static void isEmpty(Collection collection) {
		isEmpty(collection, "[Assertion failed] - this array must not be empty: it must contain at least 1 element");
	}

	/**
	 * Assert that a map doesn't elements; that is, it must be
	 * <code>null</code> or must not have any  element.
	 * <pre class="code">Assert.isEmpty(map, "The map must not have elements");</pre>
	 * @param map the map to check
	 * @param message the exception message to use if the assertion fails
	 * @throws IllegalArgumentException if the map is not <code>null</code> and has elements
	 */
	@SuppressWarnings("rawtypes")
	public static void isEmpty(Map map, String message,Object... messageParas) {
		if (!MapUtils.isEmpty(map)) {
			throwException(message,messageParas);
		}
	}

	/**
	 * @see isEmpty(Map, String)
	 */
	@SuppressWarnings("rawtypes")
	public static void isEmpty(Map map) {
		isEmpty(map, "[Assertion failed] - this array must not be empty: it must contain at least 1 element");
	}

	/**
	 * Assert that an array has no null elements.
	 * Note: Does not complain if the array is empty!
	 * <pre class="code">Assert.noNullElements(array, "The array must have non-null elements");</pre>
	 * @param array the array to check
	 * @param message the exception message to use if the assertion fails
	 * @throws IllegalArgumentException if the object array contains a <code>null</code> element
	 */
	public static void noNullElements(Object[] array, String message,Object... messageParas) {
		if (array != null) {
			for (int i = 0; i < array.length; i++) {
				if (array[i] == null) {
					throwException(message,messageParas);
				}
			}
		}
	}

	/**
	 * @see noNullElements(Object[], String)
	 */
	public static void noNullElements(Object[] array) {
		noNullElements(array, "[Assertion failed] - this array must not contain any null elements");
	}

	/**
	 * Assert that a collection has no null elements.
	 * Note: Does not complain if the array is empty!
	 * <pre class="code">Assert.noNullElements(collection, "The collection must have non-null elements");</pre>
	 * @param collection the collection to check
	 * @param message the exception message to use if the assertion fails
	 * @throws IllegalArgumentException if the object array contains a <code>null</code> element
	 */
	@SuppressWarnings("rawtypes")
	public static void noNullElements(Collection collection, String message,Object... messageParas) {
		if (collection!=null) {
			for (Object obj:collection) {
				if (obj == null) {
					throwException(message,messageParas);
				}
			}
		}
	}

	/**
	 * @see noNullElements(Collection, String)
	 */
	@SuppressWarnings("rawtypes")
	public static void noNullElements(Collection collection) {
		noNullElements(collection, "[Assertion failed] - this collection must not contain any null elements");
	}
	
	/**
	 * Assert that a collection has no null elements.
	 * Note: Does not complain if the array is empty!
	 * <pre class="code">Assert.noNullElements(collection, "The collection must have non-null elements");</pre>
	 * @param map the map to check
	 * @param message the exception message to use if the assertion fails
	 * @throws IllegalArgumentException if the object array contains a <code>null</code> element
	 */
	@SuppressWarnings("rawtypes")
	public static void noNullElements(Map map, String message,Object... messageParas) {
		if (map!=null) {
			for (Object obj:map.values()) {
				if (obj == null) {
					throwException(message,messageParas);
				}
			}
		}
	}

	/**
	 * @see noNullElements(Map, String)
	 */
	@SuppressWarnings("rawtypes")
	public static void noNullElements(Map map) {
		noNullElements(map, "[Assertion failed] - this collection must not contain any null elements");
	}
	private static void throwException( String message,Object... messageParas){
		String finalMessage;
		if(message==null){
			message="assert failed";
		}
		if(messageParas==null||messageParas.length==0){
			finalMessage=message;
		}
		else{
			finalMessage=MessageFormatter.arrayFormat(message, messageParas).getMessage();
		}
		throw new IllegalArgumentException(finalMessage);
	}
}
