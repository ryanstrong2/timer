package org.ryanstrong.models.data;

import org.ryanstrong.models.Dater;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ryanstrong on 8/27/17.
 */
@Repository
@Transactional
public interface DaterDao
        extends CrudRepository<Dater, Integer>
{
}
