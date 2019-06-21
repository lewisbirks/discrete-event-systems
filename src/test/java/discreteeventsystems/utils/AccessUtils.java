package discreteeventsystems.utils;

import java.lang.reflect.Field;

public class AccessUtils {

	public static Field getField(Class<?> clazz, String fieldName) throws NoSuchFieldException {
		Field field = clazz.getDeclaredField(fieldName);
		field.setAccessible(true);
		return field;
	}

}
