package com.itland.employer.Responses;

import com.itland.employer.abstracts.AbstractEntity;
import com.itland.employer.entities.JobApplication;

import java.util.List;

/**
 * Created by Saad on 5/10/2018.
 */

public class JobApplicationsListResponse extends AbstractEntity {


/*
    {
        "Items": [
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
  ],
        "IsOk": true,
            "Message": {
        "Type": "string",
                "Content": "string"
    }
    }
*/
    public List<JobApplication> Items;

}
