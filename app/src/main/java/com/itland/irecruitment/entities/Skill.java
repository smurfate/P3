package com.itland.irecruitment.entities;

import com.itland.irecruitment.abstracts.AbstractEntity;
import com.itland.irecruitment.abstracts.AbstractFragment;

/**
 * Created by Saad on 5/14/2018.
 */

public class Skill extends AbstractEntity {
/*
    {
        "Id": 0,
            "Name": "string",
            "Level": "string",
            "YearsOfExperiences": 0,
            "LastUsedYear": 0,
            "LastUsedMonth": "string",
            "IsOk": true,
            "Message": {
        "Type": "string",
                "Content": "string"
    }
    }
*/
    public Integer Id;
    public String Name;
    public String Level;
    public Integer YearsOfExperiences;
    public Integer LastUsedYear;
    public String LastUsedMonth;

}
