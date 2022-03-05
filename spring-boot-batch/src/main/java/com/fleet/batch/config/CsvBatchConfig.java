package com.fleet.batch.config;

import com.fleet.batch.entity.User;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class CsvBatchConfig {

    @Resource
    private DataSource dataSource;

    @Resource
    private JobRepository jobRepository;

    /**
     * simpleJobLauncher bean
     */
    @Bean
    public SimpleJobLauncher simpleJobLauncher() {
        SimpleJobLauncher simpleJobLauncher = new SimpleJobLauncher();
        // 设置 jobRepository
        simpleJobLauncher.setJobRepository(jobRepository);
        return simpleJobLauncher;
    }

    /**
     * job bean
     */
    @Bean
    public Job job(JobBuilderFactory jobs, Step step) {
        return jobs.get("csv_job")
                .incrementer(new RunIdIncrementer())
                .flow(step)
                .end()
                .listener(csvJobExecutionListener())
                .build();
    }

    /**
     * 注册 job 监听器
     */
    @Bean
    public CsvJobExecutionListener csvJobExecutionListener() {
        return new CsvJobExecutionListener();
    }

    /**
     * step bean 步骤包括 ItemReader->ItemProcessor->ItemWriter 即读取数据->处理校验数据->写入数据
     */
    @Bean
    public Step step(StepBuilderFactory stepBuilderFactory, ItemReader<User> reader, ItemProcessor<User, User> processor, ItemWriter<User> writer) {
        return stepBuilderFactory.get("step").<User, User>chunk(65000)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    /**
     * reader 定义：读取文件数据 + entity 映射
     */
    @Bean
    public ItemReader<User> reader() {
        FlatFileItemReader<User> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("user.csv"));
        reader.setLineMapper(new DefaultLineMapper<User>() {
            {
                setLineTokenizer(new DelimitedLineTokenizer() {
                    {
                        setNames("id", "name", "gender");
                    }
                });
                setFieldSetMapper(new BeanWrapperFieldSetMapper<User>() {
                    {
                        setTargetType(User.class);
                    }
                });
            }
        });
        return reader;
    }

    /**
     * processor bean 处理数据 + 校验数据
     */
    @Bean
    public ItemProcessor<User, User> processor() {
        CsvItemProcessor processor = new CsvItemProcessor();
        // 设置校验器
        processor.setValidator(new CsvValidator<>());
        return processor;
    }

    /**
     * writer bean 设置批量插入sql语句，写入数据库
     */
    @Bean
    public ItemWriter<User> writer() {
        JdbcBatchItemWriter<User> writer = new JdbcBatchItemWriter<>();
        writer.setDataSource(dataSource);
        // 设置有参数的sql语句
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setSql("insert into `user` values(:id,:name,:gender)");
        return writer;
    }
}
