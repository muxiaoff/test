package com.baomidou.mybatisplus.generator.config;

import com.baomidou.mybatisplus.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.converts.OracleTypeConvert;
import com.baomidou.mybatisplus.generator.config.converts.PostgreSqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.converts.SqlServerTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.querys.OracleQuery;
import com.baomidou.mybatisplus.generator.config.querys.PostgreSqlQuery;
import com.baomidou.mybatisplus.generator.config.querys.SqlServerQuery;
import com.baomidou.mybatisplus.generator.config.rules.DbType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSourceConfig {
    private IDbQuery dbQuery;
    private DbType dbType;
    private String schemaname = "public";
    private ITypeConvert typeConvert;
    private String url;
    private String driverName;
    private String username;
    private String password;

    public DataSourceConfig() {
    }

    public IDbQuery getDbQuery() {
        if(null == this.dbQuery) {
            switch(getDbType()) {
                case ORACLE:
                    dbQuery = new OracleQuery();
                    break;
                case SQL_SERVER:
                    dbQuery = new SqlServerQuery();
                    break;
                case POSTGRE_SQL:
                    dbQuery = new PostgreSqlQuery();
                    break;
                default:
                    // 默认 MYSQL
                    dbQuery = new MySqlQuery();
                    break;
            }
        }

        return this.dbQuery;
    }

    public DataSourceConfig setDbQuery(IDbQuery dbQuery) {
        this.dbQuery = dbQuery;
        return this;
    }

    public DbType getDbType() {
        if(null == this.dbType) {
            if(this.driverName.contains("mysql")) {
                this.dbType = DbType.MYSQL;
            } else if(this.driverName.contains("oracle")) {
                this.dbType = DbType.ORACLE;
            } else {
                if(!this.driverName.contains("postgresql")) {
                    throw new MybatisPlusException("Unknown type of database!");
                }

                this.dbType = DbType.POSTGRE_SQL;
            }
        }

        return this.dbType;
    }

    public DataSourceConfig setDbType(DbType dbType) {
        this.dbType = dbType;
        return this;
    }

    public String getSchemaname() {
        return this.schemaname;
    }

    public void setSchemaname(String schemaname) {
        this.schemaname = schemaname;
    }

    public ITypeConvert getTypeConvert() {
        if(null == this.typeConvert) {
            switch(getDbType()) {
                case ORACLE:
                    typeConvert = new OracleTypeConvert();
                    break;
                case SQL_SERVER:
                    typeConvert = new SqlServerTypeConvert();
                    break;
                case POSTGRE_SQL:
                    typeConvert = new PostgreSqlTypeConvert();
                    break;
                default:
                    // 默认 MYSQL
                    typeConvert = new MySqlTypeConvert();
                    break;
            }
        }

        return this.typeConvert;
    }

    public DataSourceConfig setTypeConvert(ITypeConvert typeConvert) {
        this.typeConvert = typeConvert;
        return this;
    }

    public Connection getConn() {
        Connection conn = null;

        try {
            Class.forName(this.driverName);
            conn = DriverManager.getConnection(this.url, this.username, this.password);
        } catch (SQLException | ClassNotFoundException var3) {
            var3.printStackTrace();
        }

        return conn;
    }

    public String getUrl() {
        return this.url;
    }

    public DataSourceConfig setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getDriverName() {
        return this.driverName;
    }

    public DataSourceConfig setDriverName(String driverName) {
        this.driverName = driverName;
        return this;
    }

    public String getUsername() {
        return this.username;
    }

    public DataSourceConfig setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return this.password;
    }

    public DataSourceConfig setPassword(String password) {
        this.password = password;
        return this;
    }
}
