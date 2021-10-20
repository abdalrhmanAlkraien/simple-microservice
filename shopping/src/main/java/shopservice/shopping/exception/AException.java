package shopservice.shopping.exception;

public abstract class AException extends RuntimeException {


    public AException(String shopName){
        super("Run Time Exception here at ".concat(shopName));
    }

}
