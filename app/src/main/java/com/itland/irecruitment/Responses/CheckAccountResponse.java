package com.itland.irecruitment.Responses;

import com.itland.irecruitment.abstracts.AbstractEntity;

/**
 * Created by Saad on 5/9/2018.
 */

public class CheckAccountResponse extends AbstractEntity {

/*
    {
        "IsFound": true,
            "IsActive": true,
            "IsOk": true,
            "Message": {
        "Type": "string",
                "Content": "string"
            }
    }
*/

    public Boolean IsFound;
    public Boolean IsActive;
}
