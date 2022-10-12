package hello.hellospring.domain;

import javax.persistence.*;

// ORM 설정 - JPA가 관리하는 Entity임을 선언
@Entity
public class Member {
    // PK 맵핑
    // GenerationType.IDENTITY : DB가 ID를 자동 생성해 주는 것
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column(name = "username") : 컬럼명을 username으로 맵핑
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
