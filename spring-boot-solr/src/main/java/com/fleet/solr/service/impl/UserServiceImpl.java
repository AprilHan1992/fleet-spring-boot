package com.fleet.solr.service.impl;

import com.fleet.solr.entity.User;
import com.fleet.solr.service.UserService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
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
        SolrInputDocument document = new SolrInputDocument();
        document.setField("id", user.getId());
        document.setField("name", user.getName());
        try {
            solrClient.add(document);
            solrClient.commit();
        } catch (IOException | SolrServerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try {
            solrClient.deleteById(String.valueOf(id));
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
    public User get(Long id) {
        User user = new User();
        user.setId(id);
        try {
            SolrDocument document = solrClient.getById(String.valueOf(id));
            if (document != null) {
                user.setName(document.get("name").toString());
            }
        } catch (IOException | SolrServerException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> list(Map<String, String> map) {
        List<User> userList = new ArrayList<>();
        SolrQuery query = new SolrQuery();
        if (map != null) {
            for (String key : map.keySet()) {
                query.set(key, map.get(key));
            }
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
