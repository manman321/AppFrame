package in.srain.cube.image.iface;

import in.srain.cube.image.ImageLoadRequest;

public interface NameGenerator {

    /**
     * @param request
     * @return
     */
    String generateIdentityUrlFor(ImageLoadRequest request);
}
