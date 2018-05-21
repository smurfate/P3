package com.itland.employer.requests;

/**
 * Created by Saad on 5/10/2018.
 */

public class EditInactiveVacancyRequest {
/*
    {
        "Title": "string",
            "CityId": 0,
            "RequiredYearsOfExperienceId": 0,
            "MinEducationDegreeId": 0,
            "JobDescription": "string",
            "PreRequisites": "string",
            "FieldOfWorkId": 0,
            "JobTypeId": 0,
            "MilitaryServiceId": 0,
            "SalaryId": 0,
            "Benefits": "string",
            "RequiredEmployeesCount": 0,
            "PhotoIsRequired": true,
            "RequiredCVLanguageId": 0,
            "IsNameHidden": true,
            "About": "string"
    }
*/
    public String Title;
    public Integer CityId;
    public String SkillsNeeded;
    public Integer RequiredYearsOfExperienceId;
    public Integer MinEducationDegreeId;
    public String JobDescription;
    public String PreRequisites;
    public Integer FieldOfWorkId;
    public Integer JobTypeId;
    public Integer MilitaryServiceId;
    public Integer SalaryId;
    public String Benefits;
    public Integer RequiredEmployeesCount;
    public Boolean PhotoIsRequired;
    public Integer RequiredCVLanguageId;
    public Boolean IsNameHidden;
    public String About;
}
