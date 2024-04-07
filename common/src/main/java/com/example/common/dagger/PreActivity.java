package com.example.common.dagger;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * @Author winiymissl
 * @Date 2024-04-04 19:07
 * @Version 1.0
 */
@Documented
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PreActivity {
}
