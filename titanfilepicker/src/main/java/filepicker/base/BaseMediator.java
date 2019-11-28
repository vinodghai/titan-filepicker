package filepicker.base;

import filepicker.request.ClientRequest;

/**
 * Created by VINOD KUMAR on 3/29/2019.
 */
public abstract class BaseMediator {

    protected ClientRequest clientRequest;

    public BaseMediator(ClientRequest clientRequest) {
        this.clientRequest = clientRequest;
    }

    public ClientRequest getClientRequest() {
        return clientRequest;
    }
}
