package com.baiyan.ddd.interaction.controller;

import com.baiyan.ddd.base.model.query.KeywordQuery;
import com.baiyan.ddd.base.model.result.BaseResult;
import com.baiyan.ddd.base.model.result.Page;
import com.baiyan.ddd.base.model.result.PageResult;
import com.baiyan.ddd.base.model.result.Result;
import com.baiyan.ddd.core.infrastructure.query.dto.UserPageDTO;
import com.baiyan.ddd.core.user.UserApplicationService;
import com.baiyan.ddd.core.user.UserQueryRepository;
import com.baiyan.ddd.core.user.command.CreateUserCommand;
import com.baiyan.ddd.core.user.command.UpdateUserCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 用户管理web接口
 *
 * @author baiyan
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserApplicationService userApplicationService;

    @Autowired
    UserQueryRepository userQueryRepository;

    @PostMapping
    public Result<Object> create(@RequestBody @Valid CreateUserCommand command){
        userApplicationService.create(command);
        return Result.ok(BaseResult.INSERT_SUCCESS);
    }

    @PutMapping
    public Result<Object> update(@RequestBody @Valid UpdateUserCommand command){
        userApplicationService.update(command);
        return Result.ok(BaseResult.UPDATE_SUCCESS);
    }

    @DeleteMapping("/{id}")
    public Result<Object> delete(@PathVariable Long id){
        userApplicationService.delete(id);
        return Result.ok(BaseResult.DELETE_SUCCESS);
    }

    @GetMapping
    public PageResult<UserPageDTO> query(KeywordQuery query){
        Page<UserPageDTO> users = userQueryRepository.userPage(query);
        return PageResult.ok(users);
    }

}
