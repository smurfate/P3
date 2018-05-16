package com.itland.employer.entities;

import com.itland.employer.abstracts.AbstractEntity;

/**
 * Created by Saad on 5/14/2018.
 */

public class Reference extends AbstractEntity {
/*
    {
        "Id": 0,
            "FullName": "string",
            "CompanyName": "string",
            "JobTitle": "string",
            "Phone": "string",
            "OfficeNumber": "string",
            "Email": "string",
            "IsOk": true,
            "Message": {
        "Type": "string",
                "Content": "string"
    }
    }
*/
    public Integer Id;
    public String FullName;
    public String CompanyName;
    public String JobTitle;
    public String Phone;
    public String OfficeNumber;
    public String Email;
}
