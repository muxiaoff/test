package com.cangu.app.persistence.web;

import com.cangu.app.exception.ParameterMissException;
import org.springframework.validation.BindingResult;

/**
 * @author ZhengFeiFei on 2018/10/22.
 */
public class BaseController {

    protected final static String insert = "insert";
    protected final static String insert_Ch = "新增";

    protected final static String update = "update";
    protected final static String update_Ch = "修改";

    protected final static String delete = "delete";
    protected final static String delete_Ch = "删除";

    protected final static String single = "get";
    protected final static String single_Ch = "查询单条";

    protected final static String list = "list";
    protected final static String list_Ch = "列表查询";

    protected final static String page = "page";
    protected final static String page_Ch = "分页查询";



    /** 日志 1增加2修改3删除4=查询 */
    protected final static int log_insert = 1;
    protected final static int log_update = 2;
    protected final static int log_delete = 3;
    protected final static int log_select = 4;

    /** 是否鉴权 */
    protected final static String auth_yes = "鉴权: 是";
    protected final static String auth_no = "鉴权: 否";

    protected final static String POST = "POST";
    protected final static String GET = "GET";


    /**
     * 参数校验 @valid
     * @param bindingResult
     * @throws ParameterMissException
     */
    protected void checkBinding(BindingResult bindingResult) throws ParameterMissException {
        if (bindingResult.hasErrors()) {
            throw new ParameterMissException(bindingResult.getFieldError().getDefaultMessage());
        }
    }
}
