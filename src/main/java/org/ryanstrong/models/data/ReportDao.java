package org.ryanstrong.models.data;

import org.ryanstrong.models.Report;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by ryanstrong on 8/5/17.
 */
@Repository
@Transactional
public interface ReportDao extends CrudRepository <Report, Integer> {
}
