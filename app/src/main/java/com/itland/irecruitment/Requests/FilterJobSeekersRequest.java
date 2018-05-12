package com.itland.irecruitment.Requests;

/**
 * Created by Saad on 5/10/2018.
 */

public class FilterJobSeekersRequest {
/*
    {
        "CountryId": 0,
            "CityId": 0,
            "MinEducationLevelId": 0,
            "FieldOfWorkId": 0,
            "JobTypeId": 0,
            "SearchQuery": "string",
            "ResumeLanguageId": 0,
            "WithPhoto": true,
            "Page": 0,
            "PageLength": 0,
            "OrderBy": "string",
            "SortDirection": 0,
            "ElementsToBeSkipedCount": 0
    }
*/

    public Integer CountryId;
    public Integer CityId;
    public Integer MinEducationLevelId;
    public Integer FieldOfWorkId;
    public Integer JobTypeId;
    public String SearchQuery;
    public Integer ResumeLanguageId;
    public Boolean WithPhoto;
    public Integer Page;
    public Integer PageLength;
    public String OrderBy;
    public Integer SortDirection;
    public Integer ElementsToBeSkipedCount;
}
