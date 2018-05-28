package com.itland.employer.entities;

import com.itland.employer.abstracts.AbstractEntity;

/**
 * Created by Saad on 5/2/2018.
 */

public class Vacancy extends AbstractEntity {
/*
    {
        "Id": 0,
            "Title": "string",
            "WorkingCountry": "string",
            "WorkingCity": "string",
            "ViewsCount": 0,
            "ApplicationsCount": 0,
            "Status": "string",
            "PostedSince": 0,
            "ExpiresIn": 0,
            "ExpiredSince": 0,
            "AddedSince": 0,
            "IsOk": true,
            "Message": {
        "Type": "string",
                "Content": "string"
    }
    }
*/
    public Integer Id;
    public String Title;
    public String WorkingCountry;
    public String WorkingCity;
    public Integer ViewsCount;
    public Integer ApplicationsCount;
    public String Status;
    public Integer PostedSince;
    public Integer ExpiresIn;
    public Integer ExpiredSince;
    public Integer AddedSince;

    public boolean isActive()
    {
        return Status.equals("Active");
    }

    public boolean isInactive()
    {
        return Status.equals("Inactive");
    }

    public boolean isExpired()
    {
        return Status.equals("Expired");
    }
}
