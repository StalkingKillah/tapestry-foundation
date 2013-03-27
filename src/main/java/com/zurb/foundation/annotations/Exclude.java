/**
 * 
 */
package com.zurb.foundation.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author trsvax
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Exclude {

	public String mode() default "ALL";
	public String[] stylesheet();
	
}
