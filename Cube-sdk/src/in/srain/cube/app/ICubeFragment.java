package in.srain.cube.app;

/**
 * provide some method to make Fragment act like Activity in BackStack
 * <p/>
 * When a Fragment becomes invisible totally, {@link #onLeave} will be called.
 * <p/>
 * When a Fragment becomes visible from totally invisible, {@link #onBack()} or {@link #onBackWithData(Object)} will be called.
 * 
 */
public interface ICubeFragment {

    /**
     * pass the data from {@link CubeFragmentActivity#pushFragmentToBackStack(Class, Object)}to this fragment
     *
     * @param data
     */
    void onEnter(Object data);

    void onLeave();

    void onBack();

    void onBackWithData(Object data);

    /**
     * process the return back logic
     * return true if back pressed event has been processed and should stay in current view
     * 当前的fragment需要处理 返回事件时,并且处理完之后任然停留在当前页面的返回true
     *
     * @return
     */
    boolean processBackPressed();
}