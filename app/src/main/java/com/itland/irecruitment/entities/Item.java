package com.itland.irecruitment.entities;

import java.util.List;

/**
 * Created by Saad on 5/9/2018.
 */

public class Item extends AbstractEntity {

/*
    {
        "Id": 0,
            "CategoryName": "string",
            "Name": "string",
            "ImageUrl": "string",
            "CreateDate": "2018-05-09T15:45:15.996Z",
            "IsFavorite": true,
            "ReviewsCount": 0,
            "Rating": 0,
            "ReviewsDetails": [
        0
      ],
        "IsOk": true,
            "Message": {
        "Type": "string",
                "Content": "string"
    }
    }
*/

    public Integer Id;
    public String CategoryName;
    public String Name;
    public String ImageUrl;
    public String CreateDate;
    public Boolean IsFavorite;
    public Integer ReviewsCount;
    public Integer Rating;
    public List<Integer> ReviewsDetails;
}
