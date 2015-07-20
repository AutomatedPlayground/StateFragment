package pl.automatedplayground.myloader.loader.listeners;

/**
 * Created by adrian on 20.07.15.
 */
public interface ResponseListener<T> {
    public void onResponseReceiver(T response);

    public void onError(Object cause);
}
