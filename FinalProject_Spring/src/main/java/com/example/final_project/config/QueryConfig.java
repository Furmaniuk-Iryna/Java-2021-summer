package com.example.final_project.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:/queries.properties")
public class QueryConfig {
    @Value("${findByUser}")
    private String findByUser;
    @Value("${findAll}")
    private String findAll;
    @Value("${countFindAll}")
    private String countFindAll;
    @Value("${findByDirection}")
    private String findByDirection;
    @Value("${countFindByDirection}")
    private String countFindByDirection;
    @Value("${findByDate}")
    private String findByDate;
    @Value("${countFindByDate}")
    private String countFindByDate;
    @Value("${findForReceipt}")
    private String findForReceipt;
    @Value("${countFindForReceipt}")
    private String countFindForReceipt;
    @Value("${getById}")
    private String getById;
    @Value("${delete}")
    private String delete;
    @Value("${findForCache}")
    private String findForCache;


    public String getFindByUser() {
        return findByUser;
    }

    public String getFindAll() {
        return findAll;
    }

    public String getCountFindAll() {
        return countFindAll;
    }

    public String getFindByDirection() {
        return findByDirection;
    }

    public String getCountFindByDirection() {
        return countFindByDirection;
    }

    public String getFindByDate() {
        return findByDate;
    }

    public String getCountFindByDate() {
        return countFindByDate;
    }

    public String getFindForReceipt() {
        return findForReceipt;
    }

    public String getCountFindForReceipt() {
        return countFindForReceipt;
    }

    public String getGetById() {
        return getById;
    }

    public String getDelete() {
        return delete;
    }

    public String getFindForCache() {
        return findForCache;
    }
}
