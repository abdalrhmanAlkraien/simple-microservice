package shopservice.shopping.repository.implement;

import org.springframework.stereotype.Service;
import shopservice.shopping.exception.ShopException;
import shopservice.shopping.model.Shop;
import shopservice.shopping.repository.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
public class ShopInMemory implements Repository<Shop> {
    private Map<Long, Shop> shopMap=new HashMap<>();
    @Override
    public <T> T save(T t) {
        Shop shop= (Shop) t;
        shopMap.put(shop.getId(), (Shop) t);
        return (T) shop;
    }


    @Override
    public <T> List<T> getAll() {
        return (List<T>)
                shopMap
                .entrySet()
                .stream()
                .map(x->x.getValue())
                .collect(Collectors.toList());
    }

    @Override
    public <T> Optional<T> getById(Long id) {
        return  (Optional<T>) shopMap
                .entrySet()
                .stream()
                .map(shopEntry->shopEntry.getValue())
                .filter(shopEntry -> shopEntry.getId().equals(id))
                .findFirst();

    }

    @Override
    public <T> void delete(Long id) {
        if(ifExist(id).equals(true))
        {
            shopMap.remove(id,getById(id));
            System.out.println("delete success");
        }
        else
        {
            new ShopException(id);
        }

    }

    public Boolean ifExist(Long id)
    {
        AtomicBoolean exist= new AtomicBoolean(false);
        if(shopMap.entrySet().isEmpty())
        {
            System.out.println("the map is empty");
            // throw own exception
            new ShopException(id);
            return exist.get();
        }
        else
        {
            shopMap
                    .entrySet()
                    .stream()
                    .map(shopEntry -> shopEntry.getValue())
                    .filter(shop -> shop.getId().equals(id))
                    .findFirst()
                    .ifPresentOrElse(x-> exist.set(true)
                    ,()-> exist.set(false));
            return exist.get();
        }
    }

    @Override
    public <T> T update() {
        return null;
    }
}
