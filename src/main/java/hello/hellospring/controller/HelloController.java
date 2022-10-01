package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello");

        // hello.html 파일명과 아래 return은 동일해야 함
        return "hello";
    }

    @GetMapping("hello-mvc")
    // @RequestParam => 외부에서 파라미터를 받을 때 사용
    // model에 담은 것을 보내면 View에서 사용
    // http://localhost:8080/hello-mvc?name=spring~ 이런식으로 파라미터 전달
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);

        // hello.html 파일명과 아래 return은 동일해야 함
        return "hello-template.html";
    }

    @GetMapping("hello-string")
    // @ResponseBody => HTTP Body에 값을 담겠다는 의미
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // "hello spring"
    }

    // api식 사용은 아래의 방식이 많이 사용 됨
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        // command + enter로 getter, setter 생성
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
