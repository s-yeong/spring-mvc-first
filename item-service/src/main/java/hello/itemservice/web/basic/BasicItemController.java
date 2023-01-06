package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    // 상품 목록
    @GetMapping
    public String items(Model model) {
        // 모든 상품 조회
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);

        // 뷰 템플릿 호출
        return "basic/items";
    }

    // 상품 상세
    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }
    
    // 상품 등록 폼
    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    // 상품 등록 처리
    // POST - HTML Form 형식 (메시지 바디에 쿼리 파라미터 형식으로 전달)
//    @PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                            @RequestParam int price,
                            @RequestParam Integer quantity,
                            Model model) {
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);

        model.addAttribute("item", item);
        
        // 상품 상세에서 사용한 item.html 뷰 템플릿 재활용 (저장 결과 보여주기)
        return "basic/item";
    }

    // 상품 등록 처리 @ModelAttribute
//    @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item) {

        itemRepository.save(item);

        // 모델에 @ModelAttribute로 지정한 객체를 자동으로 넣어줌
//        model.addAttribute("item", item);

        // 상품 상세에서 사용한 item.html 뷰 템플릿 재활용 (저장 결과 보여주기)
        return "basic/item";
    }

    // 상품 등록 처리 @ModelAttribute 이름 생략
    // 이름 생략시 model에 저장된 name은 클래스명 첫글자만 소문자로 등록
//    @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item) {

        itemRepository.save(item);

        // 모델에 @ModelAttribute로 지정한 객체를 자동으로 넣어줌
//        model.addAttribute("item", item);

        // 상품 상세에서 사용한 item.html 뷰 템플릿 재활용 (저장 결과 보여주기)
        return "basic/item";
    }

    // ModelAttribute 전체 생략
    @PostMapping("/add")
    public String addItemV4(Item item) {

        itemRepository.save(item);

        // 모델에 @ModelAttribute로 지정한 객체를 자동으로 넣어줌
//        model.addAttribute("item", item);

        // 상품 상세에서 사용한 item.html 뷰 템플릿 재활용 (저장 결과 보여주기)
        return "basic/item";
    }

    // 상품 수정 폼
    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    // 상품 수정 개발
    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }



    /**
     * 테스트용 데이터
     */

    @PostConstruct
    public void init() {
        itemRepository.save(new Item("testA", 10000, 10));
        itemRepository.save(new Item("testB", 20000, 20));
    }
}
