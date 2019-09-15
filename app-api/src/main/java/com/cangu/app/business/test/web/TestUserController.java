package com.cangu.app.business.test.web;

import com.cangu.app.aspect.Log;
import com.cangu.app.business.test.dto.arg.TestUserInsert;
import com.cangu.app.business.test.dto.arg.TestUserSearch;
import com.cangu.app.business.test.dto.arg.TestUserUpdate;
import com.cangu.app.business.test.dto.vo.TestUserVo;
import com.cangu.app.business.test.entity.TestUser;
import com.cangu.app.business.test.service.TestUserService;
import com.cangu.app.exception.ParameterMissException;
import com.cangu.app.persistence.Res;
import com.cangu.app.persistence.dto.BaseDto;
import com.cangu.app.persistence.dto.IdsDto;
import com.cangu.app.persistence.entity.PageVo;
import com.cangu.app.persistence.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 系统用户Controller
 * @author ZhengFeiFei
 * @Date 2019-09-14 16:59:48
 */
@RestController
@RequestMapping("/testUser")
@Api(tags = "系统用户", description = "系统用户", produces= MediaType.APPLICATION_JSON_VALUE)
public class TestUserController extends BaseController {

    private final String PREFIX = "/testUser/";
    private final String CN_PREFIX = "系统用户";

    @Resource
    private TestUserService testUserService;

    /**
     * 分页查询
     * @url /testUser/page
     * @param testUserSearch
     * @return
     */
    @GetMapping(value = page)
    @ApiOperation(value = page_Ch, httpMethod = GET, notes = auth_yes)
    @Log(description = CN_PREFIX + page_Ch, url = PREFIX + page)
    public Res<PageVo<TestUser,TestUserVo>> page(@Valid TestUserSearch testUserSearch) {

        return Res.ok(testUserService.page(testUserSearch.getPageNo(), testUserSearch.getPageSize(), testUserSearch.toTestUser()));
    }

    /**
     * 列表查询
     * @url /testUser/list
     * @param testUserSearch
     * @return
     */
    @GetMapping(value = list)
    @ApiOperation(value = list_Ch, httpMethod = GET, notes = auth_yes)
    @Log(description = CN_PREFIX + list_Ch, url = PREFIX + list)
    public Res<List<TestUserVo>> list(@Valid TestUserSearch testUserSearch) {

        return Res.ok(testUserService.list(testUserSearch.toTestUser()));
    }

    /**
     * 单条查询
     * @url /testUser/get
     * @param baseDto
     * @param bindingResult
     * @return
     * @throws ParameterMissException
     */
    @GetMapping(value = single)
    @ApiOperation(value = single_Ch, httpMethod = GET, notes = auth_yes)
    @Log(description = CN_PREFIX + single_Ch, url = PREFIX + single)
    public Res<TestUserVo> single(@Valid BaseDto baseDto, BindingResult bindingResult) throws ParameterMissException{
        checkBinding(bindingResult);
        return Res.ok(testUserService.get(baseDto.getId()));
    }

    /**
     * 新增
     * @url /testUser/insert
     * @param testUserInsert
     * @param bindingResult
     * @return
     * @throws ParameterMissException
     */
    @PostMapping(value = insert)
    @ApiOperation(value = insert_Ch, httpMethod = POST, notes = auth_yes)
    @Log(description = CN_PREFIX + insert_Ch, logType = log_insert, url = PREFIX + insert)
    public Res<Integer> insert(@RequestBody @Valid TestUserInsert testUserInsert, BindingResult bindingResult) throws ParameterMissException{
        checkBinding(bindingResult);
        return Res.ok(testUserService.insert(testUserInsert.toTestUser()));
    }

    /**
     * 修改
     * @url /testUser/update
     * @param testUserUpdate
     * @param bindingResult
     * @return
     * @throws ParameterMissException
     */
    @PostMapping(value = update)
    @ApiOperation(value = update_Ch, httpMethod = POST, notes = auth_yes)
    @Log(description = CN_PREFIX + update_Ch, logType = log_update, url = PREFIX + update)
    public Res<Integer> update(@RequestBody @Valid TestUserUpdate testUserUpdate, BindingResult bindingResult) throws ParameterMissException{
        checkBinding(bindingResult);
        return Res.ok(testUserService.update(testUserUpdate.toTestUser()));
    }

    /**
     * 删除
     * @url /testUser/delete
     * @param idsDto
     * @param bindingResult
     * @return
     * @throws ParameterMissException
     */
    @PostMapping(value = delete)
    @ApiOperation(value = delete_Ch, httpMethod = POST, notes = auth_yes)
    @Log(description = CN_PREFIX + delete_Ch, logType = log_delete, url = PREFIX + delete)
    public Res<Integer> delete(@RequestBody @Valid IdsDto idsDto, BindingResult bindingResult) throws ParameterMissException{
        checkBinding(bindingResult);
        return Res.ok(testUserService.delete(idsDto.getIds()));
    }

}
