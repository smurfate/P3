package com.itland.employer.responses;

import com.itland.employer.abstracts.AbstractEntity;

/**
 * Created by Saad on 5/20/2018.
 */

public class HomeResponse extends AbstractEntity {


/*
    {
        "CompanyName": "string",
            "ApplicationsCount": 0,
            "ActiveVacancies": 0,
            "InactiveVacancies": 0,
            "ExpiredVacancies": 0,
            "ViewsCount": 0,
            "IsOk": true,
            "Message": {
        "Type": "string",
                "Content": "string"
    }
    }
*/

    public String CompanyName;
    public Integer ApplicationsCount;
    public Integer ActiveVacancies;
    public Integer InactiveVacancies;
    public Integer ExpiredVacancies;
    public Integer ViewsCount;


}
