package shopservice.shopping.model;

import lombok.Value;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Value
public class Cart {
    private Map<Long,Item> longItemMap=new HashMap<>();
    private User user;
}
