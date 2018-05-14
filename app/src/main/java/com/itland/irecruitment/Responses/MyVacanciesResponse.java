package com.itland.irecruitment.Responses;

import com.itland.irecruitment.abstracts.AbstractEntity;
import com.itland.irecruitment.entities.Vacancy;

import java.util.List;

/**
 * Created by Saad on 5/10/2018.
 */

public class MyVacanciesResponse extends AbstractEntity {
    public List<Vacancy> Items;
}
