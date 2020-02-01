package com.wiprodemoandroidapp.di.module;

import android.content.Context;
import com.ict.assetmanagement.common.network.NetworkConnectivityProviderContract;
import com.wiprodemoandroidapp.R;
import java.io.IOException;
import javax.inject.Inject;
import okhttp3.Interceptor;
import okhttp3.Response;

public class NetworkConnectivityInterceptor implements Interceptor {

  private final NetworkConnectivityProviderContract networkConnectivityProvider;
  private Context context;

  @Inject
  public NetworkConnectivityInterceptor(
      NetworkConnectivityProviderContract networkConnectivityProvider, Context context) {
    this.networkConnectivityProvider = networkConnectivityProvider;
    this.context = context;
  }

  @Override
  public Response intercept(Chain chain) throws IOException {
    if (networkConnectivityProvider.isInternetConnected()) {
      return chain.proceed(chain.request());
    }
    throw new NoNetworkConnectivityException(context.getString(R.string.msg_error_no_network));
  }

  /** An exception indicating that the User is not connected to internet. */
  public static class NoNetworkConnectivityException extends IOException {

    public NoNetworkConnectivityException(String message) {
      super(message);
    }
  }
}
