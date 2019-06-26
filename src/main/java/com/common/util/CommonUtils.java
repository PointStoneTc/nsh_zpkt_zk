package com.common.util;

import java.math.BigDecimal;

/**
 * 通用工具类
 * 
 * @author zhaoqi
 *
 */
public class CommonUtils {
    /**
     * 对象转为BigDecimal
     * @param obj
     * @return
     */
    public static BigDecimal obcToBigDecimal(Object obj) {
        if (obj == null)
            return null;

        if (obj instanceof String || obj instanceof Integer || obj instanceof Double || obj instanceof Long || obj instanceof Short) {
            try {
                return new BigDecimal(String.valueOf(obj));
            } catch (Exception e) {
                return null;
            }
        } else {
            return null;
        }
    }
}
