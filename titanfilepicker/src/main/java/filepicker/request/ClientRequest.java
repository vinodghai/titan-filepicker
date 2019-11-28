package filepicker.request;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by VINOD KUMAR on 1/9/2019.
 */
public class ClientRequest implements Serializable {

    private String title;
    private boolean isRequestImage;
    private int maxImageCount = -1;
    private Map<String, String[]> fileDetails = new HashMap<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isRequestImage() {
        return isRequestImage;
    }

    public void setRequestImage(boolean requestImage) {
        isRequestImage = requestImage;
    }

    public int getMaxImageCount() {
        return maxImageCount;
    }

    public void setMaxImageCount(int maxImageCount) {
        this.maxImageCount = maxImageCount;
    }

    public Map<String, String[]> getFileDetails() {
        return fileDetails;
    }

    public void addFileDetails(String title, String[] extensionArray) {
        this.fileDetails.put(title, extensionArray);
    }
}
