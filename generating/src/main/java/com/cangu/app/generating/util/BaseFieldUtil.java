package com.cangu.app.generating.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by sai on 2018/9/4.
 */
public class BaseFieldUtil {

    public static Boolean getIsNotBaseField(String fileName){
        fileName = ToolUtil.firstLetterToLower(fileName);
        return !StringUtils.equals(fileName, "id")
                && !StringUtils.equals(fileName, "remarks")
                && !StringUtils.equals(fileName, "createBy")
                && !StringUtils.equals(fileName, "createByName")
                && !StringUtils.equals(fileName, "createDate")
                && !StringUtils.equals(fileName, "updateBy")
                && !StringUtils.equals(fileName, "updateByName")
                && !StringUtils.equals(fileName, "updateDate")
                && !StringUtils.equals(fileName, "delFlag")
                && !StringUtils.equals(fileName, "deleteBy")
                && !StringUtils.equals(fileName, "deleteByName")
                && !StringUtils.equals(fileName, "deleteDate")
                && !StringUtils.equals(fileName, "isDelete")

                && !StringUtils.equals(fileName, "remark")
                && !StringUtils.equals(fileName, "createUserId")
                && !StringUtils.equals(fileName, "createUserName")
                && !StringUtils.equals(fileName, "createTime")
                && !StringUtils.equals(fileName, "updateUserId")
                && !StringUtils.equals(fileName, "updateUserName")
                && !StringUtils.equals(fileName, "updateTime")
                && !StringUtils.equals(fileName, "deleteUserId")
                && !StringUtils.equals(fileName, "deleteUserName")
                && !StringUtils.equals(fileName, "deleteTime")
                && !StringUtils.equals(fileName, "isDeleted")

                // 宝贝爱蓝天
                && !StringUtils.equals(fileName, "createuserid")
                && !StringUtils.equals(fileName, "createtime")
                && !StringUtils.equals(fileName, "updateuserid")
                && !StringUtils.equals(fileName, "updatetime")
                && !StringUtils.equals(fileName, "createUserid")
                && !StringUtils.equals(fileName, "updateUserid")
                && !StringUtils.equals(fileName, "isdeleted");

    }
}
