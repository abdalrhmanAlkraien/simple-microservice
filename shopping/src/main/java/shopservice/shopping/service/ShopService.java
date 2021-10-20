package shopservice.shopping.service;

import org.springframework.stereotype.Service;
import shopservice.shopping.exception.AException;
import shopservice.shopping.exception.ShopException;
import shopservice.shopping.model.Shop;
import shopservice.shopping.repository.Repository;
import shopservice.shopping.repository.implement.ShopInMemory;

import java.util.List;
import java.util.Optional;

@Service
public class ShopService implements MainOperation <Shop> {

    private final Repository repository;

    public ShopService(ShopInMemory shopInMemory){
        this.repository=shopInMemory;
    }


    @Override
    public <T> T save(T t) {

        return (T) repository.save((Shop) t);
    }

    @Override
    public <Shop> Shop getById(Long id) throws ShopException{
        return (Shop) repository.getById(id)
                .orElse( new ShopException(1L));
    }

    @Override
    public <T> List<T> getAll() {
        return repository.getAll();
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public <T> T update() {
        return null;
    }

    /* @Override
    public <T> save(T t){
        Shop shop=(Shop)t;
        return (Shop) repository.save(shop);
    }
    @Override

    public void getById()
    {

    }
    @Override
    public List<Shop> getAll(){
        return repository.getAll();
    }
    @Override
    public void delete(){}
    @Override
    public void update(){}*/
}
