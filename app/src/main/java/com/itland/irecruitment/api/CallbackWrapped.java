package com.itland.irecruitment.api;

/**
 * Created by Saad on 5/14/2018.
 */

public interface CallbackWrapped<T> {
    public void onResponse(T response);
    public void onFailure(ErrorMessage errorMessage);
}
