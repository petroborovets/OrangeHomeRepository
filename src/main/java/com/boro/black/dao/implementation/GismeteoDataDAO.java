package com.boro.black.dao.implementation;

import com.boro.black.entity.GismeteoData;
import com.boro.black.exception.NonUniqueElementException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * Created by petroborovets on 6/10/15.
 */
@Repository("gismeteoDataDAO")
public class GismeteoDataDAO extends ElementDAOImpl<GismeteoData> {
    public GismeteoDataDAO() {
        super(GismeteoData.class);
    }

    public GismeteoData checkForUnique(GismeteoData element, Session session) throws NonUniqueElementException {
        return element;
    }
}
