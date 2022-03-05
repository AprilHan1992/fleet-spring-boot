package com.fleet.influxdb.util;

import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author April Han
 */
@Component
public class InfluxDbUtils<T> {

    private static final Logger logger = LoggerFactory.getLogger(InfluxDbUtils.class);

    @Resource
    InfluxDB influxDB;

    /**
     * 添加
     */
    public void add(Object obj) {
        Point point = Point.measurementByPOJO(obj.getClass())
                .addFieldsFromPOJO(obj)
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .build();
        influxDB.write(point);
        influxDB.close();
    }

    /**
     * 列表
     */
    public List<T> list(String sql, Class<T> clazz) {
        QueryResult queryResult = influxDB.query(new Query(sql));
        influxDB.close();
        InfluxDBResultMapper mapper = new InfluxDBResultMapper();
        return mapper.toPOJO(queryResult, clazz);
    }
}
