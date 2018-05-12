package com.itland.irecruitment.Requests;

/**
 * Created by Saad on 5/10/2018.
 */

public class EditActiveVacancyRequest {
/*
    {
        "Id": 0,
            "MinEducationDegreeId": 0,
            "PhotoIsRequired": true,
            "RequiredCVLanguageId": 0,
            "IsNameHidden": true,
            "FieldOfWorkId": 0,
            "IsActive": true
    }
*/
    public Integer Id;
    public Integer MinEducationDegreeId;
    public Boolean PhotoIsRequired;
    public Integer RequiredCVLanguageId;
    public Boolean IsNameHidden;
    public Integer FieldOfWorkId;
    public Boolean IsActive;
}
