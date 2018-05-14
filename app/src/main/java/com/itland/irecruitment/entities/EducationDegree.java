package com.itland.irecruitment.entities;

import com.itland.irecruitment.abstracts.AbstractEntity;

/**
 * Created by Saad on 5/14/2018.
 */

public class EducationDegree extends AbstractEntity {
/*
    {
        "Id": 0,
            "Name": "string",
            "EducationInstitution": "string",
            "DegreeLevel": "string",
            "Major": "string",
            "Rate": 0,
            "Country": "string",
            "City": "string",
            "Description": "string",
            "GraduationYear": 0,
            "IsOk": true,
            "Message": {
        "Type": "string",
                "Content": "string"
    }
    }
*/
    public Integer Id;
    public String Name;
    public String EducationInstitution;
    public String DegreeLevel;
    public String Major;
    public Float Rate;
    public String Country;
    public String City;
    public String Description;
    public Integer GraduationYear;

}
