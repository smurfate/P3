package com.itland.irecruitment.entities;

import com.itland.irecruitment.abstracts.AbstractEntity;

/**
 * Created by Saad on 5/14/2018.
 */

public class JobApplicationDetails extends AbstractEntity {


/*
    {
        "VacancyTitle": "string",
            "CompanyName": "string",
            "Country": "string",
            "City": "string",
            "VacancyType": "string",
            "VacancyFieldOfWork": "string",
            "ViewsCount": 0,
            "ApplicationsCount": 0,
            "AppliedSince": 0,
            "VacancyStatus": "string",
            "ApplicationStatus": "string",
            "CoverLetter": "string",
            "ResumeId": 0,
            "IsOk": true,
            "Message": {
        "Type": "string",
                "Content": "string"
    }
    }
*/

    public String VacancyTitle;
    public String CompanyName;
    public String Country;
    public String City;
    public String VacancyType;
    public String VacancyFieldOfWork;
    public String ViewsCount;
    public String ApplicationsCount;
    public String AppliedSince;
    public String VacancyStatus;
    public String ApplicationStatus;
    public String CoverLetter;
    public String ResumeId;


}