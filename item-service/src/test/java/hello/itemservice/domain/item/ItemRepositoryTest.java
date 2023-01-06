package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    // 스프링 없이 테스트 - 기능을 when 절에!
    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    public void afterEach() {
        itemRepository.clearStore();
    }

    // 저장
    @Test
    public void save() {
        //given
        Item itemA = new Item("itemA", 10000, 100);

        //when
        Item savedItem = itemRepository.save(itemA);

        //then
        Item findItem = itemRepository.findById(itemA.getId());
        assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    void findAll() {
        //given
        Item itemA = new Item("itemA", 10000, 100);
        Item itemB = new Item("itemB", 20000, 400);

        itemRepository.save(itemA);
        itemRepository.save(itemB);

        //when
        List<Item> result = itemRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(itemA, itemB);
    }

    @Test
    void update() {
        //given
        Item itemA = new Item("itemA", 10000, 100);
        Item savedItem = itemRepository.save(itemA);
        Long itemId = itemA.getId();

        //when
        Item updateParam = new Item("itemB", 40000, 800);
        itemRepository.update(itemId, updateParam);

        //then
        Item findItem = itemRepository.findById(itemId);
        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());
    }
}