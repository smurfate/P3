package com.itland.irecruitment.Responses;

import com.itland.irecruitment.abstracts.AbstractEntity;

/**
 * Created by Saad on 5/9/2018.
 */

public class ChangeProfileInfoResponse extends AbstractEntity {
/*
    {
        "Id": "string",
            "FirstName": "string",
            "LastName": "string",
            "Email": "string",
            "GsmCountryCode": "string",
            "PhoneNumber": "string",
            "ImageURL": "string",
            "CityId": 0,
            "BirthDate": "2018-05-09T15:45:15.764Z",
            "IsMale": true,
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
    public Integer CityId;
    public String BirthDate;
    public Boolean IsMale;
}
