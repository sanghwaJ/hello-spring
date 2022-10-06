package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

// @Controller Annotation이 붙는 순간 스프링 컨테이너에 MemberController 객체를 생성하여 넣어 줌
// 이를 스프링 컨테이너에서 스프링 빈을 관리한다고 함
@Controller
public class MemberController {

    // new로 객체를 계속해서 생성하는 것은 비효율적
    // 따라서 스프링 컨테이너에 등록된 것을 사용해야 함
    private final MemberService memberService;

    // @Autowired Annotation을 사용하면 아래와 같이 스프링 컨테이너에 등록된 객체를 가져다가 씀
    // 즉 Controller와 Service를 연결시켜  => DI (의존성 주입, Dependency Injection)
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
