package com.itland.irecruitment.Responses;

import com.itland.irecruitment.entities.AbstractEntity;

/**
 * Created by Saad on 5/9/2018.
 */

public class ChangeAddressResponse extends AbstractEntity {
/*
    {
        "Id": 0,
            "Title": "string",
            "Street": "string",
            "Building": "string",
            "Floor": "string",
            "Apartment": "string",
            "Mobile": "string",
            "Landline": "string",
            "Notes": "string",
            "Longitude": 0,
            "Latitude": 0,
            "IsOk": true,
            "Message": {
        "Type": "string",
                "Content": "string"
    }
    }
*/

    public Integer Id;
    public String Title;
    public String Street;
    public String Building;
    public String Floor;
    public String Apartment;
    public String Mobile;
    public String Landline;
    public String Notes;
    public Double Longitude;
    public Double Latitude;
}
