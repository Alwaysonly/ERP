package generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

/**
 * @Author Z.xichao
 * @Create 2018-6-7
 * @Comments
 */
public class GeneratorServiceEntity {

    @Test
    public void generateCode() {
        String packageName = "com.huige.erp.common";
        boolean serviceNameStartWithI = false;//user -> UserService, 设置成true: user -> IUserService
        generateByTables(serviceNameStartWithI, packageName, "t_common_log");
    }

    private void generateByTables(boolean serviceNameStartWithI, String packageName, String... tableNames) {
        GlobalConfig config = new GlobalConfig();
//        String dbUrl = "jdbc:mysql://localhost:3306/huige_erp_db";
        String dbUrl = "jdbc:mysql://localhost:3306/huige_erp_db";
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setUsername("root")
                .setPassword("root")
                .setDriverName("com.mysql.jdbc.Driver");
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setSuperControllerClass("com.huige.erp.common.base.BaseController")
                .setSuperMapperClass("com.huige.erp.common.base.IBaseMapper")
                .setSuperServiceClass("com.huige.erp.common.base.IBaseService")
                .setSuperServiceImplClass("com.huige.erp.common.base.BaseServiceImpl")
                .setCapitalMode(true)
                .setEntityLombokModel(false)
                .setDbColumnUnderline(true)
                .setNaming(NamingStrategy.underline_to_camel)
                .setInclude(tableNames);//修改替换成你需要的表名，多个表名传数组
        config.setActiveRecord(false)
                .setAuthor("Mr.Zhang")
                .setOutputDir("d:\\codeGen")
                .setEnableCache(false)
                .setFileOverride(true);
        if (!serviceNameStartWithI) {
            config.setServiceName("%sService");
        }
        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(packageName)
                                .setController("apis.controller")
                                .setService("apis.service")
                                .setServiceImpl("apis.service.impl")
                                .setXml("apis.mapper")
                                .setMapper("apis.dao")
                                .setEntity("pojo.po")
                ).execute();
    }

}
