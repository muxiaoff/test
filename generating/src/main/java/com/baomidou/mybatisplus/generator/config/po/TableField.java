package com.baomidou.mybatisplus.generator.config.po;

import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import java.util.Map;

public class TableField {
    private boolean convert;
    private boolean keyFlag;
    private boolean keyIdentityFlag;
    private String name;
    private String type;
    private String propertyName;
    private DbColumnType columnType;
    private String comment;
    private String fill;
    private Map<String, Object> customMap;

    /** 不可为空 */
    private boolean nullable;
    /** varchar 长度 */
    private String maxLength;
    /** 是否搜索条件 */
    private boolean isSearch = true;
    /** 是否添加参数 */
    private boolean isAdd = true;
    /** 是否修改参数 */
    private boolean isUpdate = true;
    /** 是否列表展示 */
    private boolean isVo = true;
    /** 表单类型 */
    private String formType = "text";

    public TableField() {
    }

    public boolean isConvert() {
        return this.convert;
    }

    protected void setConvert(StrategyConfig strategyConfig) {
        if(strategyConfig.isCapitalModeNaming(this.name)) {
            this.convert = false;
        } else if(StrategyConfig.DB_COLUMN_UNDERLINE) {
            if(StringUtils.containsUpperCase(this.name)) {
                this.convert = true;
            }
        } else if(!this.name.equals(this.propertyName)) {
            this.convert = true;
        }

    }

    public String getFormType() {
        return formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }

    public boolean isSearch() {
        return isSearch;
    }

    public void setSearch(boolean search) {
        isSearch = search;
    }

    public boolean isAdd() {
        return isAdd;
    }

    public void setAdd(boolean add) {
        isAdd = add;
    }

    public boolean isUpdate() {
        return isUpdate;
    }

    public void setUpdate(boolean update) {
        isUpdate = update;
    }

    public boolean isVo() {
        return isVo;
    }

    public void setVo(boolean vo) {
        isVo = vo;
    }

    public String getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(String maxLength) {
        this.maxLength = maxLength;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public void setConvert(boolean convert) {
        this.convert = convert;
    }

    public boolean isKeyFlag() {
        return this.keyFlag;
    }

    public void setKeyFlag(boolean keyFlag) {
        this.keyFlag = keyFlag;
    }

    public boolean isKeyIdentityFlag() {
        return this.keyIdentityFlag;
    }

    public void setKeyIdentityFlag(boolean keyIdentityFlag) {
        this.keyIdentityFlag = keyIdentityFlag;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPropertyName() {
        return this.propertyName;
    }

    public void setPropertyName(StrategyConfig strategyConfig, String propertyName) {
        this.propertyName = propertyName;
        this.setConvert(strategyConfig);
    }

    public DbColumnType getColumnType() {
        return this.columnType;
    }

    public void setColumnType(DbColumnType columnType) {
        this.columnType = columnType;
    }

    public String getPropertyType() {
        return null != this.columnType?this.columnType.getType():null;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCapitalName() {
        if(this.propertyName.length() <= 1) {
            return this.propertyName.toUpperCase();
        } else {
            String setGetName = this.propertyName;
            if(DbColumnType.BASE_BOOLEAN.getType().equalsIgnoreCase(this.columnType.getType())) {
                setGetName = StringUtils.removeIsPrefixIfBoolean(setGetName, Boolean.class);
            }

            String firstChar = setGetName.substring(0, 1);
            return Character.isLowerCase(firstChar.toCharArray()[0]) && Character.isUpperCase(setGetName.substring(1, 2).toCharArray()[0])?firstChar.toLowerCase() + setGetName.substring(1):firstChar.toUpperCase() + setGetName.substring(1);
        }
    }

    public String getFill() {
        return this.fill;
    }

    public void setFill(String fill) {
        this.fill = fill;
    }

    public Map<String, Object> getCustomMap() {
        return this.customMap;
    }

    public void setCustomMap(Map<String, Object> customMap) {
        this.customMap = customMap;
    }
}