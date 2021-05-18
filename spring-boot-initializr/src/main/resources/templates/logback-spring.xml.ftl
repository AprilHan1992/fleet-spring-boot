<?xml version="1.0" encoding="UTF-8"?>
<configuration scan="true" scanPeriod="60 seconds" debug="false">

    <property name="FILE_PATH" value="./applog/logback"/>
    <property name="LOG_PATTERN" value="%d{HH:mm:ss.SSS} [%p] %c.%M\\(%F:%L\\)%n%m%n"/>

    <appender name="console" class="ch.qos.logback.core.ConsoleAppender">
        <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
            <level>INFO</level>
        </filter>
        <encoder>
            <pattern><#noparse>${LOG_PATTERN}</#noparse></pattern>
            <charset>UTF-8</charset>
        </encoder>
    </appender>

    <appender name="debug" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file><#noparse>${FILE_PATH}/debug.log</#noparse></file>
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>DEBUG</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
        <encoder>
            <pattern><#noparse>${LOG_PATTERN}</#noparse></pattern>
            <charset>UTF-8</charset>
        </encoder>
        <rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
            <FileNamePattern><#noparse>${FILE_PATH}/%d{yyyy-MM-dd}/debug-%i.log</#noparse></FileNamePattern>
            <MaxHistory>365</MaxHistory>
            <MaxFileSize>1024MB</MaxFileSize>
        </rollingPolicy>
    </appender>

    <appender name="info" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file><#noparse>${FILE_PATH}/info.log</#noparse></file>
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>INFO</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
        <encoder>
            <pattern><#noparse>${LOG_PATTERN}</#noparse></pattern>
            <charset>UTF-8</charset>
        </encoder>
        <rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
            <FileNamePattern><#noparse>${FILE_PATH}/%d{yyyy-MM-dd}/info-%i.log</#noparse></FileNamePattern>
            <MaxHistory>365</MaxHistory>
            <MaxFileSize>1024MB</MaxFileSize>
        </rollingPolicy>
    </appender>

    <appender name="error" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file><#noparse>${FILE_PATH}/error.log</#noparse></file>
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>ERROR</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
        <encoder>
            <pattern><#noparse>${LOG_PATTERN}</#noparse></pattern>
            <charset>UTF-8</charset>
        </encoder>
        <rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
            <FileNamePattern><#noparse>${FILE_PATH}/%d{yyyy-MM-dd}/error-%i.log</#noparse></FileNamePattern>
            <MaxHistory>365</MaxHistory>
            <MaxFileSize>1024MB</MaxFileSize>
        </rollingPolicy>
    </appender>

    <springProfile name="dev">
        <root level="debug">
            <appender-ref ref="console"/>
            <appender-ref ref="debug"/>
        </root>
    </springProfile>

    <springProfile name="test">
        <root level="info">
            <appender-ref ref="console"/>
            <appender-ref ref="info"/>
        </root>
    </springProfile>

    <springProfile name="pro">
        <root level="error">
            <appender-ref ref="console"/>
            <appender-ref ref="error"/>
        </root>
    </springProfile>
</configuration>
