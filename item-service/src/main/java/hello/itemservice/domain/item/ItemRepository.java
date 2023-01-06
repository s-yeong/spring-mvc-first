package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    // static 사용시 new로 많아도 딱 하나만 사용 -> 싱글톤 사용시 static 생략 가능
    private static final Map<Long, Item> store = new HashMap<>();
    private static long sequence = 0L;

    // 저장
    public Item save(Item item) {

        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    // 조회
    public Item findById(Long id) {
        return store.get(id);
    }

    // 전체 조회
    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    // 수정
    public void update(Long itemId, Item updateParam) {
        Item findItem = findById(itemId);

        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());

        // 원래는 별도의 업데이트 객체를 만드는게 맞다! (ex: ItemParamDto - 여기에 ItemName, Price, Quantity가 들어가야함)
    }

    // 테스트용
    public void clearStore() {
        store.clear();
    }
}
