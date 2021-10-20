package shopservice.shopping.exception;

public class ShopException extends AException {
    private Long shopId;


    public ShopException(Long shopId)
    {
        super(shopId.toString());
        this.shopId=shopId;

    }

}
