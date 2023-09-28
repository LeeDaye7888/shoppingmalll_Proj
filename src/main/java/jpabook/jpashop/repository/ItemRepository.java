package jpabook.jpashop.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import jpabook.jpashop.domain.item.Item;
import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {
        if (item.getId() == null) { // 아이디가 없으면 새거로 추정하고
            em.persist(item); //item 저장
        } else { //이미 db에 등록되어있음
            em.merge(item); //업데이트 비슷
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

}
