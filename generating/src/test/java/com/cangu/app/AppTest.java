package com.cangu.app;

import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.cangu.app.generating.config.TempConfig;
import com.cangu.app.generating.engine.TemplateEngine;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * beetl模板引擎 代码生成
     */
    @Test
    public void BeetlTemplateEngine() {

        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("888888QWE.");
        dataSourceConfig.setUrl("jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf8&serverTimezone=UTC");

        TempConfig config = new TempConfig();
        config.setPrePackageName("com.cangu.app.business");   // 包前缀
        config.setModuleName("test"); // 模块名称
        config.setTemplatePath("template/app");   // 模板路径
        config.setCover(true);  // 覆盖原有文件

//        生成customer
        configTable(config);

        configTemp(config);
        // 配置导入的包
        addImport(config);
        // TODO 需要更换当前项目中的路径
//        config.getServiceImports().add("hans.generating.code.framework.util.BeanUtils");
//        config.getEntityImports().add("hans.generating.code.framework.util.BeanUtils");

        TemplateEngine templateEngine = new TemplateEngine(dataSourceConfig, config);
        // 开始生成
        templateEngine.doHansGenerating(false);
    }

    /**
     * 配置customer模块
     * @param config
     */
    public static void configTable(TempConfig config) {
        // 代码生成路径
        config.setProjectPath("/Users/doublefei/cangu/test");
        // 配置需要生成的表  若不配置默认生成所有
        config.getTableList().add("test_user");
    }



    public static void configTemp(TempConfig config) {
        /** 配置生成的模板 */
        config.getTempMap().put("Controller.java.btl", "web");
        config.getTempMap().put("Service.java.btl", "service");
        config.getTempMap().put("ServiceImpl.java.btl", "service");
        config.getTempMap().put("Entity.java.btl", "entity");
        config.getTempMap().put("Vo.java.btl", "dto.vo");
        config.getTempMap().put("Insert.java.btl", "dto.arg");
        config.getTempMap().put("Update.java.btl", "dto.arg");
        config.getTempMap().put("Search.java.btl", "dto.arg");
        config.getTempMap().put("Dao.java.btl", "dao");
        config.getTempMap().put("Dao.xml.btl", "");
    }

    /**
     * 添加需要导入的包
     * @param config
     */
    public static void addImport(TempConfig config) {
        // Controller import配置
        config.getControllerImports().add("com.cangu.app.exception.ParameterMissException");
        config.getControllerImports().add("com.cangu.app.persistence.Res");
        config.getControllerImports().add("com.cangu.app.persistence.dto.BaseDto");
        config.getControllerImports().add("com.cangu.app.persistence.dto.IdsDto");
        config.getControllerImports().add("com.cangu.app.persistence.web.BaseController");
        config.getControllerImports().add("com.cangu.app.aspect.Log");
        config.getControllerImports().add("com.cangu.app.persistence.entity.PageVo");
        // Service import配置
        config.getServiceImports().add("com.cangu.app.persistence.Res");
        config.getServiceImports().add("com.cangu.app.persistence.service.CrudService");

        // Entity import配置
        config.getEntityImports().add("com.cangu.app.persistence.entity.DataEntity");

        // Vo import配置
        config.getVoImports().add("com.cangu.app.persistence.dto.BaseVo");
        // insert import配置
        config.getInsertImports().add("javax.validation.constraints.NotBlank");
        config.getInsertImports().add("javax.validation.constraints.NotNull");
        config.getInsertImports().add("org.hibernate.validator.constraints.Length");

        // update import配置
        config.getUpdateImports().add("com.cangu.app.persistence.dto.BaseDto");
        config.getUpdateImports().add("javax.validation.constraints.NotBlank");
        config.getUpdateImports().add("javax.validation.constraints.NotNull");
        config.getUpdateImports().add("org.hibernate.validator.constraints.Length");

        // Search import配置
        config.getSearchImports().add("com.cangu.app.persistence.dto.BasePageDto");

        // Dao import配置

        config.getDaoImports().add("com.cangu.app.persistence.dao.CrudDao");
        config.getDaoImports().add("org.springframework.format.annotation.DateTimeFormat");
    }
}
