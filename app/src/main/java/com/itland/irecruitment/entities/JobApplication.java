package com.itland.irecruitment.entities;

import com.itland.irecruitment.abstracts.AbstractEntity;

/**
 * Created by Saad on 5/10/2018.
 */

public class JobApplication extends AbstractEntity {
/*
    {
        "Id": 0,
            "VacancyTitle": "string",
            "VacancyWorkingCountry": "string",
            "VacancyWorkingCity": "string",
            "SeekerFullName": "string",
            "AppliedSince": 0,
            "ApplicationReadStatus": "string",
            "IsOk": true,
            "Message": {
        "Type": "string",
                "Content": "string"
    }
    }
*/
    public Integer Id;
    public String VacancyTitle;
    public String VacancyWorkingCountry;
    public String VacancyWorkingCity;
    public String SeekerFullName;
    public Integer AppliedSince;
    public String ApplicationReadStatus;
}
