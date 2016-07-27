package in.srain.cube.photos;

public interface PhotoReadyHandler {

    int FROM_CAMERA = 1;
    int FROM_ALBUM = 2;
    int FROM_CROP = 3;

    void onPhotoReady(int from, String imgPath);
}
