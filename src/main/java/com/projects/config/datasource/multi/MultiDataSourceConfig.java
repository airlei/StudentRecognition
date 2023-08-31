
package com.projects.config.datasource.multi;

import cn.stylefeng.roses.core.config.properties.DruidProperties;
import cn.stylefeng.roses.core.mutidatasource.aop.MultiSourceExAop;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * 多数据源配置<br/>
 * <p>
 * 注：由于引入多数据源，所以让spring事务的aop要在多数据源切换aop的后面

 */
@Configuration
@ConditionalOnProperty(prefix = "guns.muti-datasource", name = "open", havingValue = "true")
@MapperScan(basePackages = {"com.projects.modular.*.mapper"}, sqlSessionTemplateRef = "gunsSqlSessionTemplate")
public class MultiDataSourceConfig {

    /**
     * 默认主数据源配置
     */
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DruidProperties druidProperties() {
        return new DruidProperties();
    }

    /**
     * 多数据源配置
     */
    @Bean
    @ConfigurationProperties(prefix = "guns.muti-datasource")
    public DruidProperties mutiDataSourceProperties() {
        return new DruidProperties();
    }

    /**
     * 主数据源实例
     */
    @Primary
    @Bean
    public DataSource dataSourcePrimary(@Qualifier("druidProperties") DruidProperties druidProperties) {
        if (ToolUtil.isOneEmpty(druidProperties, druidProperties.getDataSourceName())) {
            throw new IllegalArgumentException("初始化OptionalSqlSessionTemplate错误！请设置spring.datasource.data-source-name属性的值！");
        }
        return createDataSource(druidProperties.getDataSourceName(), druidProperties);
    }

    /**
     * 第二个数据源实例
     */
    @Bean
    public DataSource dataSourceBusiness(@Qualifier("mutiDataSourceProperties") DruidProperties mutiDataSourceProperties) {
        if (ToolUtil.isOneEmpty(mutiDataSourceProperties, mutiDataSourceProperties.getDataSourceName())) {
            throw new IllegalArgumentException("初始化OptionalSqlSessionTemplate错误！请设置spring.muti-datasource.data-source-name属性的值！");
        }
        return createDataSource(mutiDataSourceProperties.getDataSourceName(), mutiDataSourceProperties);
    }

    /**
     * 多数据源切换的aop
     */
    @Bean
    public MultiSourceExAop multiSourceExAop() {
        return new MultiSourceExAop();
    }

    /**
     * 数据源创建模板
     */
    private static DataSource createDataSource(String dataSourceName, DruidProperties druidProperties) {
        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
        atomikosDataSourceBean.setUniqueResourceName(dataSourceName);
        atomikosDataSourceBean.setMaxPoolSize(20);
        atomikosDataSourceBean.setBorrowConnectionTimeout(60);
        atomikosDataSourceBean.setXaProperties(druidProperties.createProperties());
        return atomikosDataSourceBean;
    }
}
