package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member) { //JPA가 저장
        em.persist(member); //persist -> 영속성 컨텍스트에 member객체 넣음 -> 트랜잭션이 커밋되는 시점에 디비에 반영
    }

    public Member findOne(Long id) { //조회
        return em.find(Member.class, id); //JPA가 제공하는 find메소드
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class) //createQuery(JPQL, 반환타입)
                .getResultList();
    }

    public List<Member> findByName(String name) { //이름으로 회원 검색
        return em.createQuery("select m from Member m where m.name = :name",
                        Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
