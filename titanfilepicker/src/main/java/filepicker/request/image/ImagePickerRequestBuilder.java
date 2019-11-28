package filepicker.request.image;

import filepicker.base.BaseRequestBuilder;

/**
 * Created by VINOD KUMAR on 1/9/2019.
 */
@SuppressWarnings("unused")
public class ImagePickerRequestBuilder extends BaseRequestBuilder<ImageFilePicker, ImagePickerRequestBuilder> {

    public static final int REQUEST_CODE_IMAGE_SELECT = 234;
    public static final String RESULT_IMAGE_ARRAY_LIST = "RESULT_IMAGE_ARRAY_LIST";

    public ImagePickerRequestBuilder() {
        super(new ImageMediator());
    }

    public ImagePickerRequestBuilder setMaxImageCount(int maxImageCount) {
        mediator.getClientRequest().setMaxImageCount(maxImageCount);
        return this;
    }

    @Override
    public ImageFilePicker build() {
        return (ImageMediator) mediator;
    }
}
