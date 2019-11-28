package filepicker.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import filepicker.docviewer.model.MediaDocument;
import filepicker.imageviewer.model.MediaImage;

/**
 * Created by VINOD KUMAR on 3/27/2019.
 */
public final class MediaUtil {

    public static final String PDF_EXT = ".pdf";
    public static final String TXT_EXT = ".txt";
    public static final String DOCX_EXT = ".docx";
    public static final String DOC_EXT = ".doc";
    private static final String EXTERNAL_VOLUME = "external";

    private MediaUtil() {
    }

    @NonNull
    public static List<MediaImage> getImages(@NonNull Context context) {
        // which image properties are we querying
        String[] projection = new String[]{
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.DATE_ADDED,
                MediaStore.Images.Media.DISPLAY_NAME
        };

        // content:// style URI for the "primary" external storage volume
        Uri images = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String sortOrder = MediaStore.Images.Media.DATE_ADDED + " DESC";

        // Make the query.
        Cursor cursor = context.getContentResolver().query(images,
                projection, // Which columns to return
                null,       // Which rows to return (all rows)
                null,       // Selection arguments (none)
                sortOrder        // Ordering
        );

        List<MediaImage> imageList = new ArrayList<>();

        if (cursor == null)
            return imageList;

        if (cursor.getCount() > 0 && cursor.moveToFirst()) {

            int nameColumn = cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME);
            int pathColumn = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
            do {
                String name = cursor.getString(nameColumn);
                String path = cursor.getString(pathColumn);
                imageList.add(new MediaImage(name, path));

            } while (cursor.moveToNext());
        }

        cursor.close();

        return imageList;
    }

    @NonNull
    public static List<MediaDocument> getDocuments(@NonNull Context context, @NonNull String[] extensionArray) {

        List<MediaDocument> documents = new ArrayList<>();

        if (extensionArray.length < 1)
            return documents;

        String[] projection = new String[]{
                MediaStore.Files.FileColumns.DATA,
                MediaStore.Files.FileColumns.DATE_ADDED,
                MediaStore.Files.FileColumns.TITLE,
                MediaStore.Files.FileColumns.SIZE
        };

        Uri documentsUri = MediaStore.Files.getContentUri(EXTERNAL_VOLUME);

        String selection = "";

        if (extensionArray.length > 1) {
            for (int i = 0; i < extensionArray.length; i++) {
                String extension = extensionArray[i];
                selection = selection.concat("_data LIKE '%" + extension + "'");

                if (i + 1 < extensionArray.length)
                    selection = selection.concat(" or ");
            }
        } else
            selection = "_data LIKE '%" + extensionArray[0] + "'";

        selection = selection.trim();

        String sortOrder = MediaStore.Files.FileColumns.DATE_ADDED + " DESC";

        Cursor cursor = context.getContentResolver().query(documentsUri,
                projection, // Which columns to return
                selection,       // Which rows to return (all rows)
                null,       // Selection arguments (none)
                sortOrder        // Ordering
        );

        if (cursor == null)
            return documents;

        if (cursor.getCount() > 0 && cursor.moveToFirst()) {
            int nameColumn = cursor.getColumnIndex(MediaStore.Files.FileColumns.TITLE);
            int pathColumn = cursor.getColumnIndex(MediaStore.Files.FileColumns.DATA);
            int sizeColumn = cursor.getColumnIndex(MediaStore.Files.FileColumns.SIZE);

            do {
                String name = cursor.getString(nameColumn);
                String path = cursor.getString(pathColumn);
                String size = cursor.getString(sizeColumn);
                documents.add(new MediaDocument(name, path, size));

            } while (cursor.moveToNext());
        }

        cursor.close();

        return documents;
    }
}
