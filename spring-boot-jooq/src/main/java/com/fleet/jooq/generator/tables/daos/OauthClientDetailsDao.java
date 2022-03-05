/*
 * This file is generated by jOOQ.
 */
package com.fleet.jooq.generator.tables.daos;


import com.fleet.jooq.generator.tables.OauthClientDetails;
import com.fleet.jooq.generator.tables.records.OauthClientDetailsRecord;

import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.12"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class OauthClientDetailsDao extends DAOImpl<OauthClientDetailsRecord, com.fleet.jooq.generator.tables.pojos.OauthClientDetails, String> {

    /**
     * Create a new OauthClientDetailsDao without any configuration
     */
    public OauthClientDetailsDao() {
        super(OauthClientDetails.OAUTH_CLIENT_DETAILS, com.fleet.jooq.generator.tables.pojos.OauthClientDetails.class);
    }

    /**
     * Create a new OauthClientDetailsDao with an attached configuration
     */
    public OauthClientDetailsDao(Configuration configuration) {
        super(OauthClientDetails.OAUTH_CLIENT_DETAILS, com.fleet.jooq.generator.tables.pojos.OauthClientDetails.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getId(com.fleet.jooq.generator.tables.pojos.OauthClientDetails object) {
        return object.getClientId();
    }

    /**
     * Fetch records that have <code>client_id IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.OauthClientDetails> fetchByClientId(String... values) {
        return fetch(OauthClientDetails.OAUTH_CLIENT_DETAILS.CLIENT_ID, values);
    }

    /**
     * Fetch a unique record that has <code>client_id = value</code>
     */
    public com.fleet.jooq.generator.tables.pojos.OauthClientDetails fetchOneByClientId(String value) {
        return fetchOne(OauthClientDetails.OAUTH_CLIENT_DETAILS.CLIENT_ID, value);
    }

    /**
     * Fetch records that have <code>resource_ids IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.OauthClientDetails> fetchByResourceIds(String... values) {
        return fetch(OauthClientDetails.OAUTH_CLIENT_DETAILS.RESOURCE_IDS, values);
    }

    /**
     * Fetch records that have <code>client_secret IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.OauthClientDetails> fetchByClientSecret(String... values) {
        return fetch(OauthClientDetails.OAUTH_CLIENT_DETAILS.CLIENT_SECRET, values);
    }

    /**
     * Fetch records that have <code>scope IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.OauthClientDetails> fetchByScope(String... values) {
        return fetch(OauthClientDetails.OAUTH_CLIENT_DETAILS.SCOPE, values);
    }

    /**
     * Fetch records that have <code>authorized_grant_types IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.OauthClientDetails> fetchByAuthorizedGrantTypes(String... values) {
        return fetch(OauthClientDetails.OAUTH_CLIENT_DETAILS.AUTHORIZED_GRANT_TYPES, values);
    }

    /**
     * Fetch records that have <code>web_server_redirect_uri IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.OauthClientDetails> fetchByWebServerRedirectUri(String... values) {
        return fetch(OauthClientDetails.OAUTH_CLIENT_DETAILS.WEB_SERVER_REDIRECT_URI, values);
    }

    /**
     * Fetch records that have <code>authorities IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.OauthClientDetails> fetchByAuthorities(String... values) {
        return fetch(OauthClientDetails.OAUTH_CLIENT_DETAILS.AUTHORITIES, values);
    }

    /**
     * Fetch records that have <code>access_token_validity IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.OauthClientDetails> fetchByAccessTokenValidity(Integer... values) {
        return fetch(OauthClientDetails.OAUTH_CLIENT_DETAILS.ACCESS_TOKEN_VALIDITY, values);
    }

    /**
     * Fetch records that have <code>refresh_token_validity IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.OauthClientDetails> fetchByRefreshTokenValidity(Integer... values) {
        return fetch(OauthClientDetails.OAUTH_CLIENT_DETAILS.REFRESH_TOKEN_VALIDITY, values);
    }

    /**
     * Fetch records that have <code>additional_information IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.OauthClientDetails> fetchByAdditionalInformation(String... values) {
        return fetch(OauthClientDetails.OAUTH_CLIENT_DETAILS.ADDITIONAL_INFORMATION, values);
    }

    /**
     * Fetch records that have <code>autoapprove IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.OauthClientDetails> fetchByAutoapprove(String... values) {
        return fetch(OauthClientDetails.OAUTH_CLIENT_DETAILS.AUTOAPPROVE, values);
    }
}
