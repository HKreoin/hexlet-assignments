package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// BEGIN
public class Validator {
    public static List<String> validate(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        var list = new ArrayList<String>();
        Object value = null;
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                value = field.get(object);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                System.out.println("Ошибка считывания значения приватной переменной");
            }
            if (field.isAnnotationPresent(NotNull.class) && value == null) {
                list.add(field.getName());
            }
        }
        return list;
    }

    public static Map<String, List<String>> advancedValidate(Object object) {
        var fields = object.getClass().getDeclaredFields();
        var map = new HashMap<String, List<String>>();
        Object value = null;
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                value = field.get(object);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                System.out.println("Ошибка считывания значения приватной переменной");
            }
            var listOfErrors = new ArrayList<String>();

            if (field.isAnnotationPresent(NotNull.class) && value == null) {
                listOfErrors.add("can not be null");
            } else if (field.isAnnotationPresent(MinLength.class)) {
                var minLength = field.getAnnotation(MinLength.class).minLength();
                if (value.toString().length() < minLength) {
                    listOfErrors.add("length less than " + minLength);
                }
            }
            if (!listOfErrors.isEmpty()) {
                map.put(field.getName(), listOfErrors);
            }
        }
        return map;
    }
}
// END
