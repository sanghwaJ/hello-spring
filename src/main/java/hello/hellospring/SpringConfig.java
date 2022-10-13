package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    /* JDBC
    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    */

    /* JPA
    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }
    */

    /* SpringData JPA */
    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /* JDBC, JPA
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
    */

    /* SpringData JPA는 interface에서 구현체를 만들어주기 때문에 따로 구현체를 만드는 코드를 짤 필요가 없음 */
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

    /* JDBC, JPA
    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }
    */

    /* ComponentScan을 사용해도 되지만, AOP와 같이 공통으로 사용되는 기능은
       Spring Bean에 등록(Spring Config에 추가)하여 사용하는 것이 권장됨 */
    /*
    @Bean
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }*/
}
