package com.itland.irecruitment.Responses;

import com.itland.irecruitment.entities.AbstractEntity;
import com.itland.irecruitment.entities.Banner;

import java.util.List;

/**
 * Created by Saad on 5/9/2018.
 */

public class BannersListResponse extends AbstractEntity {
/*
    {
        "Items": [
        {
            "ImageUrl": "string",
                "IsOk": true,
                "Message": {
            "Type": "string",
                    "Content": "string"
        }
        }
  ],
        "IsOk": true,
            "Message": {
        "Type": "string",
                "Content": "string"
    }
    }
*/
    public List<Banner> Items;
}
