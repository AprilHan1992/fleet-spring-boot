package com.fleet.redisson.delayqueue.enums;

/**
 * @author April Han
 */
public enum DelayQueueEnum {

    ORDER_PAY_TIMEOUT("ORDER_PAY_TIMEOUT", "orderPayDelayQueue"),

    ORDER_EVALUATE_TIMEOUT("ORDER_EVALUATE_TIMEOUT", "orderEvaluateDelayQueue");

    /**
     * 延迟队列 Key
     */
    private String key;

    /**
     * Bean
     */
    private String beanName;

    DelayQueueEnum(String key, String beanName) {
        this.key = key;
        this.beanName = beanName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}
