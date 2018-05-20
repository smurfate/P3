package com.itland.employer.entities;

import com.itland.employer.abstracts.AbstractEntity;

/**
 * Created by Saad on 5/15/2018.
 */

public class VacancyDetails extends AbstractEntity {
/*
    {
            "Id": 0,
            "Title": "string",
            "CompanyLogoUrl": "string",
            "CompanyName": "string",
            "CompanyIndustry": "string",
            "AboutCompany": "string",
            "MinRequiredEducationLevel": "string",
            "ExperienceNeededYears": 0,
            "WorkCountry": "string",
            "WorkCity": "string",
            "Type": "string",
            "FieldOfWork": "string",
            "MilitaryService": "string",
            "JobDescription": "string",
            "PreRequisites": "string",
            "SkillsNeeded": "string",
            "Salary": 0,
            "Currency": "string",
            "Benefits": "string",
            "AvailablePositions": 0,
            "IsRequiredPhoto": true,
            "RequestedCVLanguage": "string",
            "ViewsCount": 0,
            "ApplicationsCount": 0,
            "Status": "string",
            "PostedSince": 0,
            "ExpiredSince": 0,
            "ExpiresIn": 0,
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
    public String CompanyLogoUrl;
    public String CompanyName;
    public String CompanyIndustry;
    public String AboutCompany;
    public String MinRequiredEducationLevel;
    public String ExperienceNeededYears;
    public String WorkCountry;
    public String WorkCity;
    public String Type;
    public String FieldOfWork;
    public String MilitaryService;
    public String JobDescription;
    public String PreRequisites;
    public String SkillsNeeded;
    public Integer Salary;
    public String Currency;
    public String Benefits;
    public Integer AvailablePositions;
    public Boolean IsRequiredPhoto;
    public String RequestedCVLanguage;
    public Integer ViewsCount;
    public Integer ApplicationsCount;
    public String Status;
    public Integer PostedSince;
    public Integer ExpiredSince;
    public Integer ExpiresIn;
    public Integer AddedSince;


    public boolean isActive()
    {
        return Status.equals("Active");
    }

    public boolean isInactive()
    {
        return Status.equals("InActive");
    }

    public boolean isExpired()
    {
        return Status.equals("Expired");
    }


}
