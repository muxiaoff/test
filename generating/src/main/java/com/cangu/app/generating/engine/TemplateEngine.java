package com.cangu.app.generating.engine;

import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.cangu.app.generating.util.BaseFieldUtil;
import com.cangu.app.generating.util.StrKit;
import com.cangu.app.generating.util.ToolUtil;
import com.google.common.collect.Lists;
import com.sun.javafx.PlatformUtil;
import org.apache.commons.lang3.StringUtils;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.core.resource.WebAppResourceLoader;
import com.cangu.app.generating.config.TempConfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 模板引擎工具类
 * @author sai on 2018/9/4.
 */
public class TemplateEngine {

    private static GroupTemplate groupTemplate;
    private static List<TableInfo> tableInfoList;
    private static  TempConfig tempConfig;
    private static String dbType;

    public TemplateEngine(DataSourceConfig dataSourceConfig, TempConfig config) {
        initBeetlEngine();
        initTableList(dataSourceConfig);
        tempConfig = config;
    }

    protected void initTableList(DataSourceConfig dataSourceConfig) {
        ConfigBuilder configBuilder = new ConfigBuilder(new PackageConfig(),dataSourceConfig,new StrategyConfig(), new TemplateConfig(),new GlobalConfig());
        tableInfoList = configBuilder.getTableInfoList();
        dbType = dataSourceConfig.getDbType().getValue();
    }

    protected void initBeetlEngine() {
        Properties properties = new Properties();
        properties.put("RESOURCE.root", "");
        properties.put("DELIMITER_STATEMENT_START", "<%");
        properties.put("DELIMITER_STATEMENT_END", "%>");
        properties.put("HTML_TAG_FLAG", "##");
        Configuration cfg = null;
        try {
            cfg = new Configuration(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (PlatformUtil.isWindows()) {
            // windows 使用
            ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader();
            groupTemplate = new GroupTemplate(resourceLoader, cfg);
        } else {
            // ubuntu 使用
            WebAppResourceLoader resourceLoader = new WebAppResourceLoader();
            String courseFile = "";
            try {
                // 获取当前项目路径 参数为空
                File directory = new File("");
                courseFile = directory.getCanonicalPath();
                courseFile = courseFile + "/src/main/resources/";
            }catch (Exception e) {
                e.printStackTrace();
            }
            resourceLoader.setRoot(courseFile);
            groupTemplate = new GroupTemplate(resourceLoader, cfg);
        }

        groupTemplate.registerFunctionPackage("tool", new ToolUtil());
        groupTemplate.registerFunctionPackage("strKit", new StrKit());
        groupTemplate.registerFunctionPackage("fieldUtil", new BaseFieldUtil());
    }

    /**
     * 生成代码
     */
    public void doGenerating() {

        if (StringUtils.isBlank(tempConfig.getTemplatePath())) {
            System.err.println("生成失败 -- templatePath模板资源路径不能为空");
        }
        if (tempConfig.isCover()) {
            System.out.println("覆盖原有的生成文件");
        } else {
            System.out.println("不覆盖原有的生成文件");
        }
        for (TableInfo tableInfo : tableInfoList) {

            // 重置实体类导入的包
            resetImportPackages(tableInfo);
            String comment = tempConfig.getTableComment().get(tableInfo.getName());
            if (StringUtils.isNotBlank(comment)) {
                tempConfig.setChName(comment);
            } else {
                tempConfig.setChName(tableInfo.getComment());
            }


            // 去掉表前缀
            String enName = "";
            if (StringUtils.isNotBlank(tempConfig.getTablePrefix())) {
                enName = StrKit.toCamelCase(tableInfo.getEntityName().replace(tempConfig.getTablePrefix(), ""));
            } else {
                enName = StrKit.toCamelCase(tableInfo.getEntityName());
            }

            tempConfig.setEnName(ToolUtil.firstLetterToLower(enName));
            tempConfig.setEnBigName(ToolUtil.firstLetterToUpper(enName));

            // 若配置生成的表则按照配置生成 若不配置 默认生成所有
            if (null != tempConfig.getTableList() && tempConfig.getTableList().size() > 0) {
                if (!tempConfig.getTableList().contains(tableInfo.getName())) {
                    continue;
                }
            }
            writeToPathFile(tableInfo, false);
        }

    }


    /**
     * 界面版 代码生成器
     * @param
     */
    public void doHansGenerating(Boolean isModularity) {

        if (StringUtils.isBlank(tempConfig.getTemplatePath())) {
            System.err.println("生成失败 -- templatePath模板资源路径不能为空");
        }
        if (tempConfig.isCover()) {
            System.out.println("覆盖原有的生成文件");
        } else {
            System.out.println("不覆盖原有的生成文件");
        }
        for (TableInfo tableInfo : tableInfoList) {
            // 重置实体类导入的包
            resetImportPackages(tableInfo);
            String comment = tempConfig.getTableComment().get(tableInfo.getName());
            if (StringUtils.isNotBlank(comment)) {
                tempConfig.setChName(comment);
            } else {
                tempConfig.setChName(tableInfo.getComment());
            }


//            tempConfig.setEnName(ToolUtil.firstLetterToLower(tableInfo.getEntityName().replace("_","")));
//            tempConfig.setEnBigName(ToolUtil.firstLetterToUpper(tableInfo.getEntityName().replace("_","")));
            tempConfig.setEnName(ToolUtil.firstLetterToLower(StrKit.toCamelCase(tableInfo.getEntityName())));
            tempConfig.setEnBigName(ToolUtil.firstLetterToUpper(StrKit.toCamelCase(tableInfo.getEntityName())));

            /** 表名分隔符为空 使用moduleName */
            if(StringUtils.isNotBlank(tempConfig.getTableNameSplit())) {
                /** 表名分隔符不为空  使用标名分隔符切分  第一个为moduleName*/
                String[] tableName = tableInfo.getEntityName().split("_");
                tempConfig.setModuleName(tableName[0].toLowerCase());
            }

            tempConfig.setUrl(tempConfig.getModuleName() + "/" + tempConfig.getEnName());
            // 若配置生成的表则按照配置生成 若不配置 默认生成所有
            if (null != tempConfig.getTableList() && tempConfig.getTableList().size() > 0) {
                if (!tempConfig.getTableList().contains(tableInfo.getName())) {
                    continue;
                }
            }
            writeToPathFile(tableInfo, isModularity);
        }

    }

    /**
     * 组装生成路径 写入到文件
     * @param tableInfo
     */
    private void writeToPathFile(TableInfo tableInfo, boolean isModularity) {
        /**
         * 按照字段不同的业务进行重组
         */
        tableInfo.setVoFields(Lists.newArrayList());
        tableInfo.setAddFields(Lists.newArrayList());
        tableInfo.setUpdateFields(Lists.newArrayList());
        tableInfo.setSearchFields(Lists.newArrayList());
        tableInfo.getFields().forEach(tableField -> {
            if (tableField.isVo()) {
                tableInfo.getVoFields().add(tableField);
            }
            if (tableField.isAdd()) {
                tableInfo.getAddFields().add(tableField);
            }
            if (tableField.isUpdate()) {
                tableInfo.getUpdateFields().add(tableField);
            }
            if (tableField.isSearch()) {
                tableInfo.getSearchFields().add(tableField);
            }
        });

        System.out.println("正在生成 -- " + tempConfig.getChName() + " -- " + tempConfig.getEnBigName());
        for (Map.Entry<String, String> entry : tempConfig.getTempMap().entrySet()) {
            String path;

            if (entry.getKey().contains("java")) {
                // java 代码路径
                /**
                 * 是否分模块化生成代码
                 */
                if (isModularity) {
                    String prePath = tempConfig.getProjectPath();
                    switch (entry.getKey()) {
                        case "Controller.java.btl":
                            path = prePath + "/web-api" + tempConfig.getJavaFilePath() + tempConfig.getJavaPrePath() + tempConfig.getPrePackageName().replace(".", "/") + "/web/" + tempConfig.getModuleName() + "/"  + tempConfig.getEnBigName() + entry.getKey().replace(".btl", "").replace("Entity","");
                            break;
                        case "Dao.java.btl":
                            path = prePath + "/mapper" + tempConfig.getJavaFilePath() + tempConfig.getJavaPrePath() + tempConfig.getPrePackageName().replace(".", "/") + "/dao/" + tempConfig.getModuleName() + "/" + tempConfig.getEnBigName() + entry.getKey().replace(".btl", "").replace("Entity","");;
                            break;
                        case "Entity.java.btl":
                            path = prePath + "/entity" + tempConfig.getJavaFilePath() + tempConfig.getJavaPrePath() + tempConfig.getPrePackageName().replace(".", "/") + "/entity/" + tempConfig.getModuleName() + "/" + tempConfig.getEnBigName() + entry.getKey().replace(".btl", "").replace("Entity","");;
                            break;
                        case "Service.java.btl":
                            path = prePath + "/service" + tempConfig.getJavaFilePath() + tempConfig.getJavaPrePath() + tempConfig.getPrePackageName().replace(".", "/") + "/service/" + tempConfig.getModuleName() + "/" + tempConfig.getEnBigName() + entry.getKey().replace(".btl", "").replace("Entity","");;
                            break;
                        case "ServiceImpl.java.btl":
                            path = prePath + "/service" + tempConfig.getJavaFilePath() + tempConfig.getJavaPrePath() + tempConfig.getPrePackageName().replace(".", "/") + "/service/" + tempConfig.getModuleName() + "/impl/" + tempConfig.getEnBigName() + entry.getKey().replace(".btl", "").replace("Entity","");;
                            break;
                        case "Vo.java.btl":
                            path = prePath + "/service" + tempConfig.getJavaFilePath() + tempConfig.getJavaPrePath() + tempConfig.getPrePackageName().replace(".", "/") + "/service/" + tempConfig.getModuleName() + "/"  + entry.getValue().replace(".", "/") +"/" + tempConfig.getEnBigName() + entry.getKey().replace(".btl", "").replace("Entity","");;
                            break;
                        case "Search.java.btl":
                            path = prePath + "/web-api" + tempConfig.getJavaFilePath() + tempConfig.getJavaPrePath() + tempConfig.getPrePackageName().replace(".", "/") + "/web/" + tempConfig.getModuleName() + "/"  + entry.getValue().replace(".", "/") +"/" + tempConfig.getEnBigName() + entry.getKey().replace(".btl", "").replace("Entity","");;
                            break;
                        case "Insert.java.btl":
                            path = prePath + "/web-api" + tempConfig.getJavaFilePath() + tempConfig.getJavaPrePath() + tempConfig.getPrePackageName().replace(".", "/") + "/web/" + tempConfig.getModuleName() + "/"  + entry.getValue().replace(".", "/") +"/" + tempConfig.getEnBigName() + entry.getKey().replace(".btl", "").replace("Entity","");;
                            break;
                        case "Update.java.btl":
                            path = prePath + "/web-api" + tempConfig.getJavaFilePath() + tempConfig.getJavaPrePath() + tempConfig.getPrePackageName().replace(".", "/") + "/web/" + tempConfig.getModuleName() + "/"  + entry.getValue().replace(".", "/") +"/" + tempConfig.getEnBigName() + entry.getKey().replace(".btl", "").replace("Entity","");;
                            break;
                        default:
                            path = tempConfig.getProjectPath() + tempConfig.getJavaFilePath() + tempConfig.getJavaPrePath() + tempConfig.getPrePackageName().replace(".", "/") + "/" + tempConfig.getModuleName() + "/" + entry.getValue().replace(".", "/") +"/" + tempConfig.getEnBigName() + entry.getKey().replace(".btl", "").replace("Entity","");
                            break;
                    }
                } else {
                    if ("ServiceImpl.java.btl".equals(entry.getKey())) {
                        path = tempConfig.getProjectPath() + tempConfig.getJavaFilePath() + tempConfig.getJavaPrePath() + tempConfig.getPrePackageName().replace(".", "/") + "/" + tempConfig.getModuleName() + "/" + entry.getValue().replace(".", "/") +"/impl/" + tempConfig.getEnBigName() + entry.getKey().replace(".btl", "").replace("Entity","");
                    } else {
                        path = tempConfig.getProjectPath() + tempConfig.getJavaFilePath() + tempConfig.getJavaPrePath() + tempConfig.getPrePackageName().replace(".", "/") + "/" + tempConfig.getModuleName() + "/" + entry.getValue().replace(".", "/") +"/" + tempConfig.getEnBigName() + entry.getKey().replace(".btl", "").replace("Entity","");
                    }
                }
            } else if (entry.getKey().contains("md")) {
                // beetl sql 路径
                path = tempConfig.getProjectPath() + tempConfig.getJavaFilePath() + "/resources/sql/" + tempConfig.getModuleName() + "/" + entry.getValue() +"/" + tempConfig.getEnBigName() + entry.getKey().replace(".btl", "");
            } else if (entry.getKey().contains("page")) {
                // 页面生成路径
                path = tempConfig.getProjectPath() + tempConfig.getJavaFilePath() + tempConfig.getPagePrePath() + tempConfig.getModuleName() + "/" + entry.getValue() +"/" + tempConfig.getEnBigName() + entry.getKey().replace(".btl", "");
            } else if (entry.getKey().contains("xml")) {
                // xml 生成路径
                if (isModularity) {
                    path = tempConfig.getProjectPath() + "/mapper"+ tempConfig.getJavaFilePath() + tempConfig.getXmlPrePath() + tempConfig.getModuleName() + "/" + entry.getValue() +"/" + tempConfig.getEnBigName() + entry.getKey().replace(".btl", "");
                } else {
                    path = tempConfig.getProjectPath() + tempConfig.getJavaFilePath() + tempConfig.getXmlPrePath() + tempConfig.getModuleName() + "/" + entry.getValue() +"/" + tempConfig.getEnBigName() + entry.getKey().replace(".btl", "");
                }
            }else if (entry.getKey().contains("sql")) {
                // sql存储过程 生成路径
                path = tempConfig.getProjectPath() + tempConfig.getJavaFilePath() + "/resources/sqlScript/" + tempConfig.getModuleName() +"/" + tempConfig.getEnBigName() + entry.getKey().replace(".btl", "");
            }else if (entry.getKey().contains("vue")) {
                // sql存储过程 生成路径
                path = tempConfig.getProjectPath() + "/vue/pages/" + tempConfig.getModuleName() +"/" + tempConfig.getEnName() + entry.getKey().replace(".btl", "");
            }else if (entry.getKey().contains("js")) {
                // sql存储过程 生成路径
                path = tempConfig.getProjectPath() + "/vue/js/" + tempConfig.getModuleName() +"/" + tempConfig.getEnName() + entry.getKey().replace(".btl", "");
            } else {
                System.err.println("生成失败 -- 生成模板配置错误 -- " + entry.getKey());
                continue;
            }

            if (!tempConfig.isCover()) {
                File file = new File(path);
                if (file.exists()) {
                    System.err.println("文件已存在 -- 路径 = " + path);
                    continue;
                }
            }

            File file = new File(path.substring(0,path.lastIndexOf('/')));
            if(!file.exists()){
                file.mkdirs();
            }
            File f1=new File(path);
            try {
                f1.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String temp = tempConfig.getTemplatePath() + "/" + entry.getKey();
            generateFile(temp, path, tempConfig, tableInfo);
            System.out.println("生成成功 -- 路径 = " + path);
        }
    }

    /**
     * 重置实体类导入的包
     * @param tableInfo
     * @return
     */
    private List<String> resetImportPackages(TableInfo tableInfo) {
        List<String> ImportPackages = tableInfo.getImportPackages();
        ImportPackages.clear();
        tableInfo.getFields().forEach(tableField -> {
            if (BaseFieldUtil.getIsNotBaseField(tableField.getName())) {
                if (tableField.getColumnType() == DbColumnType.DATE) {
                    ImportPackages.add("com.fasterxml.jackson.annotation.JsonFormat");
                }
                if (StringUtils.isNotBlank(tableField.getColumnType().getPkg())) {
                    ImportPackages.add(tableField.getColumnType().getPkg());
                }
            }
        });
        return ImportPackages;
    }
    /**
     * 添加配置
     * @param template
     * @param config
     * @param tableInfo
     */
    protected static void configTemplate(Template template,TempConfig config, TableInfo tableInfo) {
        template.binding("config", config);
        template.binding("tableInfo", tableInfo);
        template.binding("dbType", dbType);
    }


    /**
     * 生成文件
     * @param template
     * @param filePath
     * @param config
     * @param tableInfo
     */
    protected static void generateFile(String template, String filePath,TempConfig config, TableInfo tableInfo) {
        Template pageTemplate = groupTemplate.getTemplate(template);

        configTemplate(pageTemplate,config, tableInfo);

        if (PlatformUtil.isWindows()) {
            filePath = filePath.replaceAll("/+|\\\\+", "\\\\");
        } else {
            filePath = filePath.replaceAll("/+|\\\\+", "/");
        }
        File file = new File(filePath);
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            pageTemplate.renderTo(fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
