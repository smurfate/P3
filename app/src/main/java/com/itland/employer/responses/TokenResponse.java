package com.itland.employer.responses;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Saad on 5/12/2018.
 */

public class TokenResponse {
/*
    {
        "access_token": "yV092riyxh_nXIF2x5oFlt3cuf6WGhNRmmQEnfi3MTGl70rVpWCi4j7LjvahWjK9or4wfMNGM5zG1upAuuTiy-xcy6OCGgZOYLnRAv95s4KE3v91GS-CIazfNeCxn889Pw2icpa5hNqpqryJc4B3tk1-iyeEwGawc-QzbeNsNN3peaD7O_4_SCQP073pSA7YNlSXYxX4waEd9ZuxiYyxQMSTjH5cI3jIdJxmHLE5xmGEgVs6YvktJNLEcZr-5Hed81vA6CjD1j0xD6o6iaRDcKh2bUwhbVQdDRaHKVZ1VD-R7-EtoNZk8aksGPS1np2n6NDOopZObr1XwMd5elUDZLHT9Oa-ZTteVL3RNZlkdIZPkW_ndD2fUFWkt2dnj8Jdsw2DJ4wGqP9DJagc8fPes5gnb3EtbeB5a0TVFgEsCiiZt_AfWOeygsrMLMjEKD9DFWIuOjoHIilXXR9LqZwS2KmOjw0q_es0rPx9SEHvZYrqK2LoEr4dYu0k3tdmw_B1yDBIOqhdOxmFSR_PKDBvew",
            "token_type": "bearer",
            "expires_in": 1209599,
            "userName": "JobSeeker0000000000",
            "Id": "fa0c8774-3e77-481a-bbf9-f43a276e0d27",
            ".issued": "Sat, 12 May 2018 08:34:20 GMT",
            ".expires": "Sat, 26 May 2018 08:34:20 GMT"
    }
*/
    public String access_token;
    public String token_type;
    public String expires_in;
    public String userName;
    public String Id;

    @SerializedName(".issued")
    public String issued;

    @SerializedName(".expires")
    public String expires;

}
