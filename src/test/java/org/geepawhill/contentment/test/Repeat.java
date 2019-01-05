package org.geepawhill.contentment.test;

import static java.lang.annotation.ElementType.*;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(
{
		METHOD,
		ANNOTATION_TYPE
})
public @interface Repeat
{
	int value() default 1;
}