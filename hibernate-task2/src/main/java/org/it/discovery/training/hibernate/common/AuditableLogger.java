package org.it.discovery.training.hibernate.common;

import com.google.common.base.Stopwatch;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.logging.Logger;

/**
 * Created by Шарипов on 21.11.2016.
 */

@Interceptor
@Auditable
public class AuditableLogger {
    private static final Logger LOGGER = Logger.getLogger(AuditableLogger.class.getName());

    @AroundInvoke
    public Object log(InvocationContext context) throws Exception {
        Stopwatch stopwatch = Stopwatch.createStarted();
        try {
            return context.proceed();
        } finally {
            LOGGER.info(() -> String.format("%s::%s completed in %s.", context.getTarget().getClass().getSimpleName(),
                    context.getMethod().getName(), stopwatch));
        }
    }
}
