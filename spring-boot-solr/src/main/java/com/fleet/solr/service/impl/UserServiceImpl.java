package com.fleet.solr.service.impl;

import com.fleet.solr.entity.User;
import com.fleet.solr.service.UserService;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author April Han
 */
@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Resource
    SolrClient solrClient;

    @Override
    public void insert(User user) {
        try {
            solrClient.addBean(user);
            solrClient.commit();
        } catch (IOException | SolrServerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        try {
            solrClient.deleteById(id);
            solrClient.commit();
        } catch (IOException | SolrServerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletes() {
        try {
            solrClient.deleteByQuery("*:*");
            solrClient.commit();
        } catch (IOException | SolrServerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        try {
            solrClient.addBean(user);
            solrClient.commit();
        } catch (IOException | SolrServerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User get(String id) {
        try {
            SolrDocument document = solrClient.getById(id);
            Gson gson = new Gson();
            String gsonString = gson.toJson(document);
            return gson.fromJson(gsonString, User.class);
        } catch (IOException | SolrServerException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> list(Map<String, String> map) {
        List<User> userList = new ArrayList<>();
        SolrQuery query = new SolrQuery();
        if (map != null) {
            List<String> queryList = new ArrayList<>();
            for (String key : map.keySet()) {
                queryList.add(key + ":*" + map.get(key) + "*");
            }
            query.setQuery(StringUtils.join(queryList, " or "));
        }
        try {
            QueryResponse queryResponse = solrClient.query(query);
            if (queryResponse != null) {
                userList = queryResponse.getBeans(User.class);
            }
        } catch (IOException | SolrServerException e) {
            e.printStackTrace();
        }
        return userList;
    }
}
