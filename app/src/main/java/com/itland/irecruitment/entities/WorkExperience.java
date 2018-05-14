package com.itland.irecruitment.entities;

import com.itland.irecruitment.abstracts.AbstractEntity;

/**
 * Created by Saad on 5/14/2018.
 */

public class WorkExperience extends AbstractEntity {
/*
    {
        "Id": 0,
            "CompanyName": "string",
            "JobTitle": "string",
            "StartDate": "string",
            "EndDate": "string",
            "FieldOfWork": "string",
            "YearsOfExperience": 0,
            "IsOk": true,
            "Message": {
        "Type": "string",
                "Content": "string"
    }
    }
*/
    public Integer Id;
    public String CompanyName;
    public String JobTitle;
    public String StartDate;
    public String EndDate;
    public String FieldOfWork;
    public Integer YearsOfExperience;
}
