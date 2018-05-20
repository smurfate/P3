package com.itland.employer.responses;

import com.itland.employer.abstracts.AbstractEntity;
import com.itland.employer.entities.Country;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Saad on 5/20/2018.
 */

public class CountyListResponse extends AbstractEntity {
/*
    {
        "Items": [
        {
            "Id": 0,
                "Name": "string",
                "IsActive": true
        }
  ],
        "IsOk": true,
            "Message": {
        "Type": "string",
                "Content": "string"
    }
    }
*/
    public List<Country> Items;
    
    public List<Country> getActiveCounties()
    {
        List<Country> activeCountries = new ArrayList<>();
        for (Country c : Items) {
            if (c.IsActive) activeCountries.add(c);
        }
        return activeCountries;

    }

}
