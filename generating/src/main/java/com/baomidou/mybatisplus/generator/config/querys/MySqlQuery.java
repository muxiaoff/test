package com.baomidou.mybatisplus.generator.config.querys;

/**
 * Created by sai on 2019/2/27.
 */
import com.baomidou.mybatisplus.generator.config.rules.DbType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlQuery extends AbstractDbQuery {
    public MySqlQuery() {
    }
    @Override
    public DbType dbType() {
        return DbType.MYSQL;
    }
    @Override
    public String tablesSql() {
        return "show table status";
    }
    @Override
    public String tableFieldsSql() {
        return "show full fields from `%s`";
    }
    @Override
    public String tableName() {
        return "NAME";
    }
    @Override
    public String tableComment() {
        return "COMMENT";
    }
    @Override
    public String fieldName() {
        return "FIELD";
    }
    @Override
    public String fieldType() {
        return "TYPE";
    }
    @Override
    public String fieldComment() {
        return "COMMENT";
    }
    @Override
    public String fieldKey() {
        return "KEY";
    }
    @Override
    public boolean isKeyIdentity(ResultSet results) throws SQLException {
        return "auto_increment".equals(results.getString("Extra"));
    }
}