package c1.net;

import c1.ui.C1EmptyImage;
import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.URLImage;
import x.app.Registry;
import x.uiwidget.XImage;
import x.util.Strings;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

final class NetworkCacheEntry {

    final URI uri;
    final String fileName;
    final long timeStamp;

    static NetworkCacheEntry newEntryFor(URI url) {
        return new NetworkCacheEntry(url,"u_" + Integer.toHexString(url.hashCode()),System.currentTimeMillis());
    }

    private NetworkCacheEntry(URI url, String fileName, long timeStamp) {
        this.uri = url;
        this.fileName = fileName;
        this.timeStamp = timeStamp;
    }

    boolean blockingDownloadToStorageWhichReturnsTrueOnSuccess() {
        boolean showProgress = true;
        return Util.downloadUrlToStorage(uri.toString(), fileName, showProgress);
    }

    InputStream getStreamFromStorage() throws IOException {
        return getStorage().createInputStream(fileName);  
    }
    
    private Storage getStorage() {
        return Registry.get(Storage.class);
    }
    
    @Override
    public String toString() {
        return uri + " " + fileName + " " + timeStamp;
    }

    static NetworkCacheEntry parse(String string) {
        try {
            String[] parts = Strings.split(string," ");
            URI uri = new URI(parts[0]);
            String fileName = parts[1];
            long timeStamp = Long.parseLong(parts[2]);
            return new NetworkCacheEntry(uri,fileName,timeStamp);
        } catch (URISyntaxException e) {
            throw new RuntimeException();
        }
    }

    XImage createImageToStorage() {
        return createImageToStorage(71,71);
    }

    XImage createImageToStorage(int w, int h) {
        EncodedImage placeholder = new C1EmptyImage(w, h);
        URLImage image = URLImage.createToStorage(placeholder, fileName, uri.toString(), URLImage.RESIZE_SCALE_TO_FILL);
        image.fetch();
        //return image;
        return null;
    }

}
