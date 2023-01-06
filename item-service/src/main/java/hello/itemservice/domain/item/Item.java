package hello.itemservice.domain.item;

import lombok.Data;

@Data   // 위험!, 예측하지 못한 경우가 발생할 수 있음 (교육용이니까 사용)
public class Item {

    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;
    // price와 quantity가 안들어 갈 수도 있어서 Integer형으로 선언(Null)

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
