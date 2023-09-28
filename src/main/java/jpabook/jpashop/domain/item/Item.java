package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //==비즈니스 로직==//
    //Entity 자체가 해결할 수 있는 것들은 Entity 안에 비즈니스 로직을 넣는 것이 더 객체지향적임
    //stockQuantity같은 엔티티 안에 있는 데이터를 가지고 비즈니스 로직 만들기

    public void addStock(int quantity) { // 재고 증가
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity) { // 재고 감소
        int restStock = this.stockQuantity -= quantity; //현재 남은 재고 수량
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    } //removeStock 메소드에 대한 단위 테스트도 있는 것이 중요함
}
