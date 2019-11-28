package filepicker.request.document;

import filepicker.base.BaseRequestBuilder;

/**
 * Created by VINOD KUMAR on 3/28/2019.
 */
public class DocumentPickerRequestBuilder extends BaseRequestBuilder<DocumentFilePicker, DocumentPickerRequestBuilder> {
    public static final int REQUEST_CODE_DOCUMENT_SELECT = 189;
    public static final String RESULT_DOCUMENT_ARRAY_LIST = "RESULT_DOCUMENT_ARRAY_LIST";

    public DocumentPickerRequestBuilder() {
        super(new DocumentMediator());
    }

    @Override
    public DocumentFilePicker build() {
        return (DocumentMediator) mediator;
    }

    public DocumentPickerRequestBuilder addFileSupport(String title, String[] extensionArray) {
        this.mediator.getClientRequest().addFileDetails(title, extensionArray);
        return this;
    }

}
