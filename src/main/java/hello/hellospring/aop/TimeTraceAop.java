package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// ComponentScan을 사용해도 되지만, AOP와 같이 공통으로 사용되는 기능은 Spring Bean에 등록(Spring Config에 추가)하여 사용하는 것이 권장됨
@Component
@Aspect
public class TimeTraceAop {

    // @Around Annotation으로 타겟팅을 해줌 (보통은 패키지 레벨로 설정함)
    @Around("execution(* hello.hellospring..*(..))")
    // ProceedingJoinPoint에는 여러 기능이 존재하여 찾아보며 사용하면 됨
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString()+ " " + timeMs + "ms");
        }
    }
}

