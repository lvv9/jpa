package me.liuweiqiang.hibernate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class RequestCustomizedRepositoryImplement implements RequestCustomizedRepository {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void test() {
        logger.info("customized");
    }
}
