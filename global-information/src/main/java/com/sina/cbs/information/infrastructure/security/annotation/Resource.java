package com.sina.cbs.information.infrastructure.security.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Resource {
   public String name() default "";
}
