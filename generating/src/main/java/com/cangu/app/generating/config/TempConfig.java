package com.cangu.app.generating.config;

import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * Created by sai on 2018/9/4.
 */
@Getter
@Setter
public class TempConfig {

    /** 包前缀 */
    private String prePackageName = "com.generating.test";
    /** 模块名称 */
    private String moduleName = null;
    /** 项目文件生成路径 */
    private String projectPath = "C:\\Users\\doublefei\\Desktop\\test\\";
    /** 数据源 */
    private DataSourceConfig dataSourceConfig;
    /** 模板路径 模板名称 */
    private String templatePath = "";
    /** 作者 */
    private String author = "ZhengFeiFei";
    /** 是否原有文件   默认覆盖 */
    private boolean isCover = true;
    /** 表名分隔符 */
    private String tableNameSplit;

    /** 去掉的表前缀 */
    private String tablePrefix = "";

    // 不需要配置
    private String javaFilePath = "/src/main";
    private String pagePrePath = "/webapp/";
    private String javaPrePath = "/java/";
    private String xmlPrePath = "/resources/mapper/";
    /** 业务名称 */
    private String chName;
    /** 业务英文名称 */
    private String enName;
    /** 业务英文名称(大写) */
    private String enBigName;

    private String url = "";

    /** 模板 */
    private Map<String, String> tempMap = Maps.newHashMap();

    private List<String> controllerImports = Lists.newArrayList();
    private List<String> serviceImports = Lists.newArrayList();
    private List<String> daoImports = Lists.newArrayList();
    private List<String> entityImports = Lists.newArrayList();
    private List<String> insertImports = Lists.newArrayList();
    private List<String> searchImports = Lists.newArrayList();
    private List<String> updateImports = Lists.newArrayList();
    private List<String> voImports = Lists.newArrayList();

    private List<String> baseFileds = Lists.newArrayList();

    /** 若配置生成的表则按照配置生成 若不配置 默认生成所有 */
    private List<String> tableList = Lists.newArrayList();
    /** 表注释 */
    private Map<String,String> tableComment = Maps.newHashMap();
}
