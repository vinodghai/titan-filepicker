package filepicker.base;

/**
 * Created by VINOD KUMAR on 3/29/2019.
 */
@SuppressWarnings("unchecked")
public abstract class BaseRequestBuilder<T extends BaseFilePicker, T1 extends BaseRequestBuilder> {

    protected BaseMediator mediator;

    public BaseRequestBuilder(BaseMediator mediator) {
        this.mediator = mediator;
    }

    public T1 setTitle(String tile) {
        this.mediator.getClientRequest().setTitle(tile);
        return (T1) this;
    }

    public abstract T build();
}
