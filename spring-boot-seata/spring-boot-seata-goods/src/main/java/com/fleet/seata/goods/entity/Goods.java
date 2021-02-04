package com.fleet.seata.goods.entity;

/**
 * @author April Han
 */
public class Goods {

    /**
     * id
     */
    private Integer id;

    /**
     * 商品名称
     */
    private String title;

    /**
     * 商品数量
     */
    private Integer amount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
