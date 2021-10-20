package shopservice.shopping.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shopservice.shopping.model.Shop;
import shopservice.shopping.service.ShopService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShopController {

    private final ShopService shopService;
    public ShopController(ShopService shopService){
        this.shopService=shopService;
    }
    @PostMapping
    public ResponseEntity<Shop> save(@RequestBody Shop shop) throws URISyntaxException {
        return ResponseEntity.created(new URI("")).body(shopService.save(shop));
    }

    @GetMapping
    public ResponseEntity<List<Shop>> getAll(){
        return ResponseEntity.ok().body(shopService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id){
        return ResponseEntity.ok().body(shopService.getById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable Long id) throws URISyntaxException {
        shopService.delete(id);
        return ResponseEntity.noContent().location(new URI("/user/".concat(id.toString()))).build();
    }
}
