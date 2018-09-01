package com.song.attence.base;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Desc {

    /**
     * 字段描述
     */
    String value() default "";

    /**
     * 是否必须，默认不必须
     */
    boolean isNeed() default false;

}
