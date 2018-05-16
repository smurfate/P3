package com.itland.employer.Responses;

import com.itland.employer.abstracts.AbstractEntity;
import com.itland.employer.entities.Resume;

import java.util.List;

/**
 * Created by Saad on 5/10/2018.
 */

public class FilterJobSeekerResponse extends AbstractEntity {


/*
    {
        "Items": [
        {
            "Id": 0,
                "SeekerFullName": "string",
                "JobTitle": "string",
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
    public List<Resume> Items;


}
