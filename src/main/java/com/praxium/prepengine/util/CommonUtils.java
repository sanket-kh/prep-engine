package com.praxium.prepengine.util;

import com.google.common.base.Strings;
import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.Map;

@UtilityClass
public class CommonUtils {

    // ---------- STRING ----------
    public static boolean isEmpty(String str) {
        return Strings.isNullOrEmpty(str.trim());
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    // ---------- COLLECTION ----------
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    // ---------- MAP ----------
    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    // ---------- ARRAY ----------
    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isNotEmpty(Object[] array) {
        return !isEmpty(array);
    }

    // ---------- GENERIC OBJECT ----------
    public static boolean isEmpty(Object obj) {
        if (obj == null) return true;
        if (obj instanceof String) return isEmpty((String) obj);
        if (obj instanceof Collection) return isEmpty((Collection<?>) obj);
        if (obj instanceof Map) return isEmpty((Map<?, ?>) obj);
        if (obj instanceof Object[]) return isEmpty((Object[]) obj);
        return false;
    }

    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    // ---------- MULTI ----------
    public static boolean isAnyEmpty(Object... objects) {
        for (Object obj : objects) {
            if (isEmpty(obj)) return true;
        }
        return false;
    }

    public static boolean isAllEmpty(Object... objects) {
        for (Object obj : objects) {
            if (isNotEmpty(obj)) return false;
        }
        return true;
    }

    public static boolean isAllNotEmpty(Object... objects) {
        for (Object obj : objects) {
            if (isEmpty(obj)) return false;
        }
        return true;
    }

    // ---------- BOOLEAN ----------
    public static boolean isTrue(Boolean value) {
        return Boolean.TRUE.equals(value);
    }

    public static boolean isFalse(Boolean value) {
        return !Boolean.TRUE.equals(value);
    }

    // ---------- NUMBER ----------
    public static boolean isZeroOrNull(Number number) {
        return number == null || number.doubleValue() == 0.0;
    }

    // ---------- THROW IF EMPTY ----------
    public static void throwIfEmpty(Object obj, String message) {
        if (isEmpty(obj)) {
            throw new IllegalArgumentException(message);
        }
    }
}
