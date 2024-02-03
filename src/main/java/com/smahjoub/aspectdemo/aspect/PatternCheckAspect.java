package com.smahjoub.aspectdemo.aspect;


import com.smahjoub.aspectdemo.annotation.PatternCheck;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Aspect
public class PatternCheckAspect {

    @Before("execution(* *(.., @com.smahjoub.aspectdemo.annotation.PatternCheck (*), ..)) && args(.., *)")
    public void beforeMethodWithPatternCheckAnnotation(JoinPoint jp) {

        final MethodSignature signature = (MethodSignature) jp.getSignature();
        final Method method = signature.getMethod();
        final Annotation[][] methodAnnotations = method.getParameterAnnotations();

        Object[] args = jp.getArgs();

        // Access the method arguments and process them as needed
        for (int i = 0; i < args.length; i++) {
            final var arg = args[i];
            if (arg instanceof String target) {
                final var annotationsForArg = methodAnnotations[i];
                for (final Annotation annotation : annotationsForArg) {
                    if (annotation instanceof PatternCheck patternCheck) {
                        if (!target.matches(patternCheck.pattern())) {
                            System.out.println("Argument with annotation: " + arg);
                            throw new IllegalArgumentException("Argument does not match the expected format");
                        }
                    }
                }
            }
        }
    }
}