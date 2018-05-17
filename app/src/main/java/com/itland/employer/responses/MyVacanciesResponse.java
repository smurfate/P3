package com.itland.employer.responses;

import com.itland.employer.abstracts.AbstractEntity;
import com.itland.employer.entities.Vacancy;

import java.util.List;

/**
 * Created by Saad on 5/10/2018.
 */

public class MyVacanciesResponse extends AbstractEntity {
    public List<Vacancy> Items;
}
