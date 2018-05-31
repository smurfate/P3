package com.itland.employer.entities;

import com.itland.employer.abstracts.AbstractEntity;

/**
 * Created by Saad on 5/30/2018.
 */

public class ProfileInfo extends AbstractEntity {
/*
    {
        "Id": "string",
            "FirstName": "string",
            "LastName": "string",
            "Email": "string",
            "GsmCountryCode": "string",
            "PhoneNumber": "string",
            "ImageURL": "string",
            "Username": "string",
            "IsOk": true,
            "Message": {
        "Type": "string",
                "Content": "string"
    }
    }
*/
    public String Id;
    public String FirstName;
    public String LastName;
    public String Email;
    public String GsmCountryCode;
    public String PhoneNumber;
    public String ImageURL;
    public String Username;
}
