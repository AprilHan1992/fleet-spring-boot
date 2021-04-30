package com.fleet.redisson.delayqueue.entity;

/**
 * @author April Han
 */
public class Order {

    /**
     * id
     */
    private String id;

    /**
     * 商品
     */
    private String goods;

    /**
     * 购买数量
     */
    private Integer amount;

    /**
     * 支付状态（支付中，支付完成，未支付）
     */
    private String paid;

    /**
     * 评价（默认好评）
     */
    private String evaluate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }
}
