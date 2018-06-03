package com.higo.tinklabstest.http;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Gson序列化排斥当前字段
 * <p>
 * Created on 2018/1/10.
 *
 * @author sharkliu
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface MyExclus {
}
