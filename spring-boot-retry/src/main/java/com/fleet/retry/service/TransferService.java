package com.fleet.retry.service;

/**
 * @author April Han
 */
public interface TransferService {

    void transfer(String from, String to, Integer money);
}
