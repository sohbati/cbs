package com.sina.cbs.information.infrastructure.security.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ActionScope {
   public String scope() default "";
}
