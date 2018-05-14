package com.itland.irecruitment.entities;

import com.itland.irecruitment.abstracts.AbstractEntity;

/**
 * Created by Saad on 5/14/2018.
 */

public class JobTarget extends AbstractEntity {
/*
    {
        "Id": 0,
            "CareerObjectives": "string",
            "Title": "string",
            "JobType": "string",
            "Category": "string",
            "CareerLevel": "string",
            "City": "string",
            "NoticePeriod": "string",
            "DesiredNetSalary": 0,
            "DesiredNetSalaryCurrency": "string",
            "LastMonthSalary": 0,
            "LastMonthSalaryCurrency": "string",
            "IsOk": true,
            "Message": {
        "Type": "string",
                "Content": "string"
    }
    }
*/
    public Integer Id;
    public String CareerObjectives;
    public String Title;
    public String JobType;
    public String Category;
    public String CareerLevel;
    public String City;
    public String NoticePeriod;
    public Integer DesiredNetSalary;
    public String DesiredNetSalaryCurrency;
    public Integer LastMonthSalary;
    public String  LastMonthSalaryCurrency;
}
