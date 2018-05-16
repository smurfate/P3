package com.itland.employer.Responses;

import com.itland.employer.abstracts.AbstractEntity;
import com.itland.employer.entities.City;

import java.util.List;

/**
 * Created by Saad on 5/10/2018.
 */

public class CitiesListResponse extends AbstractEntity {
/*
    {
        "Items": [
        {
            "Id": 0,
                "ArName": "string",
                "EnName": "string",
                "IsActive": true,
                "CountryName": "string"
        }
  ],
        "IsOk": true,
            "Message": {
        "Type": "string",
                "Content": "string"
    }
    }
*/
    public List<City> Items;
}
