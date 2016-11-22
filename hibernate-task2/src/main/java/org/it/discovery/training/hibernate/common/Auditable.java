package org.it.discovery.training.hibernate.common;

import javax.interceptor.InterceptorBinding;
import java.lang.annotation.*;

/**
 * Created by Шарипов on 21.11.2016.
 */

@Inherited
@InterceptorBinding
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Auditable {
}
