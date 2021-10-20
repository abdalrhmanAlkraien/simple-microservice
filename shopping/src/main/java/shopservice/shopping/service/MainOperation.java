package shopservice.shopping.service;

import shopservice.shopping.model.Shop;

import java.util.List;
import java.util.Optional;

public interface MainOperation<T> {
    public <T> T save(T t);

    public <T> T getById(Long id);

    public <T> List<T> getAll();

    public void delete(Long id);

    public <T> T update();
}
