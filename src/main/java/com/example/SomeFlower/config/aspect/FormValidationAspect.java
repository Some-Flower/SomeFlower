package com.example.SomeFlower.config.aspect;

import com.example.SomeFlower.domain.Validatable;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Aspect
@Component
public class FormValidationAspect {

    @Around("@annotation(com.example.SomeFlower.config.annotation.Validation)")
    public Object proceed(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Arrays.stream(proceedingJoinPoint.getArgs())
                .filter(arg -> arg instanceof Validatable)
                .map(arg -> (Validatable) arg)
                .forEach(Validatable::validate);
        return proceedingJoinPoint.proceed();
    }


}
