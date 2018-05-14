package com.itland.irecruitment.entities;

import com.itland.irecruitment.abstracts.AbstractEntity;

/**
 * Created by Saad on 5/14/2018.
 */

public class PersonalDetails extends AbstractEntity {
/*
    {
        "ProfilePictureUrl": "string",
            "FirstName": "string",
            "LastName": "string",
            "IsMale": true,
            "Nationality": "string",
            "MilitaryServiceStatus": "string",
            "IsSingle": true,
            "Address": "string",
            "NumberOfDependencies": 0,
            "DateOfBirth": "string",
            "Email": "string",
            "Country": "string",
            "City": "string",
            "PhoneNumber": "string",
            "MobilePhone": "string",
            "DrivingLicenceType": "string",
            "DrivingLicenceExpiryDate": "string",
            "ProfilePrivacy": "string",
            "UploadedResumeUrl": "string",
            "MotherLanguage": "string",
            "IsOk": true,
            "Message": {
        "Type": "string",
                "Content": "string"
    }
    }
*/
    public String ProfilePictureUrl;
    public String FirstName;
    public String LastName;
    public Boolean IsMale;
    public String Nationality;
    public String MilitaryServiceStatus;
    public Boolean IsSingle;
    public String Address;
    public Integer NumberOfDependencies;
    public String DateOfBirth;
    public String Email;
    public String Country;
    public String City;
    public String PhoneNumber;
    public String MobilePhone;
    public String DrivingLicenceType;
    public String DrivingLicenceExpiryDate;
    public String ProfilePrivacy;
    public String UploadedResumeUrl;
    public String MotherLanguage;
}
