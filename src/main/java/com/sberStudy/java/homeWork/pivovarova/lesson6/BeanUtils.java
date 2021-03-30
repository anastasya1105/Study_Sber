package com.sberStudy.java.homeWork.pivovarova.lesson6;

import com.sberStudy.java.homeWork.pivovarova.lesson6.Task2_3_4.Example;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.lang.reflect.Method;
import java.util.stream.Stream;

public class BeanUtils {
    /**
     * Scans object "from" for all getters. If object "to"
     * contains correspondent setter, it will invoke it
     * to set property value for "to" which equals to the property
     * of "from".
     * <p/>
     * The type in setter should be compatible to the value returned
     * by getter (if not, no invocation performed).
     * Compatible means that parameter type in setter should
     * be the same or be superclass of the return type of the getter.
     * <p/>
     * The method takes care only about public methods.
     *  @param to   Object which properties will be set.
     * @param from Object which properties will be used to get values.
     * @return
     */

    public static void assign(Object to, Object from) {
        Example example = new Example(2568, 28, "Ivan", "Russia", "Rostov-on-Don");
        if (to == null || from == null) {
            throw new IllegalArgumentException("Input arguments shouldn't be null");
        }
        Set<String> fromGetterSet = Stream.of(from.getClass().getMethods())
                .filter(m -> m.getName().matches("^get[A-Z].*$")
                        && m.getParameterCount() == 0)
                .map(m -> m.getName())
                .collect(Collectors.toSet());
        Map<String, Class<?>> toSetter = Arrays.stream(to.getClass().getMethods())
                .filter( m -> m.getName().matches("^set[A-Z].*$"))
                .collect(Collectors.toMap(key -> key.getName(), value -> value.getParameterTypes()[0]));
        toWork(to, from, toSetter, fromGetterSet);
    }
    private static void toWork(Object from, Object to, Map toSetter, Set<String> fromGetter) {
        for (String methodGet : fromGetter) {
            String setter = "s" + methodGet.substring(1);
            if (toSetter.containsKey(setter) &&
                    methodGet.getClass().getSimpleName().getClass().equals(toSetter.get(setter))) {
                try {
                    Method methodSetter = to.getClass().getMethod(setter, methodGet.getClass().getSimpleName().getClass());
                    Object parametr = from.getClass().getMethod(methodGet).invoke(from);
                    methodSetter.invoke(to, parametr);
                } catch (ClassCastException | IllegalAccessException| NoSuchMethodException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
