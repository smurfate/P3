package com.itland.irecruitment.Responses;

import com.itland.irecruitment.entities.AbstractEntity;
import com.itland.irecruitment.entities.Zone;

import java.util.List;

/**
 * Created by Saad on 5/9/2018.
 */

public class ListZonesResponse extends AbstractEntity {



/*
    {
        "Items": [
        {
            "Id": 0,
                "Name": "string",
                "Parent": "string",
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
    public List<Zone> Items;


}
