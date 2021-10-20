package shopservice.shopping.repository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface Repository<T> {

    public <T> T save(T t);

    public <T> List<T> getAll();

    public <T> Optional<T> getById(Long id);

    public <T> void delete(Long id);

    public <T>  T update();

}
