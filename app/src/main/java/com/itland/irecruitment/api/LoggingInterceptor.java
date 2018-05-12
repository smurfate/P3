package com.itland.irecruitment.api;

import android.util.Log;

import com.itland.irecruitment.api.ApiCalls;

import java.io.IOException;
import java.util.logging.Logger;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.GzipSource;
import okio.Okio;

/**
 * Created by Saad on 4/12/2016.
 */
public class LoggingInterceptor implements Interceptor {
    private static String TAG = "Debug:";
    private static final Logger logger = Logger.getLogger(ApiCalls.class.getName());

    @Override
    public Response intercept(Chain chain) throws IOException {
        long t1 = System.nanoTime();
        Request request = chain.request();
        logger.info(String.format("Sending request %s on %s%n%s",
                request.url(), chain.connection(), request.headers()));
        Log.d(TAG, String.format("Sending request %s on %s%n%s",
                request.url(), chain.connection(), request.headers()));

        if(request.body()!=null){
            Buffer requestBuffer = new Buffer();
            request.body().writeTo(requestBuffer);
            Log.d(TAG, requestBuffer.readUtf8());
        }

        Response response = chain.proceed(request);

        long t2 = System.nanoTime();
        logger.info(String.format("Received response for %s in %.1fms%n%s",
                request.url(), (t2 - t1) / 1e6d, response.headers()));
        Log.d(TAG,String.format("Received response for %s in %.1fms%n%s",
                request.url(), (t2 - t1) / 1e6d, response.headers()));

        String rawJson = response.body().string();

        Log.d(TAG, String.format("JSON: %s", rawJson));
//        if(response.body()!=null)
//        {
//            MediaType contentType = response.body().contentType();
//            BufferedSource buffer = Okio.buffer(new GzipSource(response.body().source()));
//            String content = buffer.readUtf8();
//            Log.d(TAG, content);
//
//            ResponseBody wrappedBody = ResponseBody.create(contentType, content);
//            return response.newBuilder().body(wrappedBody).build();
//
//        }
        return response.newBuilder()
                .body(ResponseBody.create(response.body().contentType(), rawJson)).build();
//        return response;
    }
}
