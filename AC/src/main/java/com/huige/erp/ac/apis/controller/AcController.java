package com.huige.erp.ac.apis.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.huige.erp.ac.apis.service.*;
import com.huige.erp.ac.pojo.dto.BeanUserInfoList;
import com.huige.erp.ac.pojo.dto.tree.BeanTreePermission;
import com.huige.erp.ac.pojo.po.*;
import com.huige.erp.common.aop.AppControllerLog;
import com.huige.erp.common.base.BaseController;
import com.huige.erp.common.constants.LogTypes;
import com.huige.erp.common.dto.BaseResponseResult;
import com.huige.erp.common.dto.ResponseResult;
import com.huige.erp.common.dto.ResponseResultWithListAll;
import com.huige.erp.common.exception.AppParmMissException;
import com.huige.erp.common.validator.AppConfigGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Author Z.xichao
 * @Create 2018-9-4
 * @Comments
 */
@RestController
@RequestMapping("/page/ac")
@Api(tags = "1.用户中心")
public class AcController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(AcController.class);

    @Autowired
    private TAcPermissionInfoService tAcPermissionInfoService;
    @Autowired
    private TAcPositionInfoService tAcPositionInfoService;
    @Autowired
    private TAcDepartmentInfoService tAcDepartmentInfoService;
    @Autowired
    private TAcRoleInfoService tAcRoleInfoService;
    @Autowired
    private TAcRolePermissionService tAcRolePermissionService;
    @Autowired
    private TAcUserInfoService tAcUserInfoService;
    @Autowired
    private TAcUserRoleService tAcUserRoleService;
    @Autowired
    private ShiroService shiroService;

    /**
     * 权限管理view
     *
     * @return
     */
    @GetMapping("/permission")
    @AppControllerLog(description = "权限管理", moduleType = LogTypes.moduleType.AC, operateValue = LogTypes.operateValue.page)
    @ApiOperation("权限管理")
    public ModelAndView permissionView(ServletRequest request) {
        ModelAndView mav = new ModelAndView("page/ac/permission");
        List<TAcPositionInfo> acPositionInfos = tAcPositionInfoService.selectList(null);
        List<TAcPermissionInfo> acPermissionInfos = tAcPermissionInfoService.selectList(null);
        mav.addObject("positionInfos", acPositionInfos);
        mav.addObject("permissionInfos", acPermissionInfos);
        return mav;
    }

    /**
     * 权限列表
     *
     * @return
     */
    @GetMapping("/permissions/list")
    @AppControllerLog(description = "权限列表", moduleType = LogTypes.moduleType.AC, operateValue = LogTypes.operateValue.select)
    @ApiOperation("权限列表")
    public ResponseResultWithListAll<List<TAcPermissionInfo>> permissionList(ServletRequest request) {
        List<TAcPermissionInfo> acPermissionInfos = tAcPermissionInfoService.selectList(new EntityWrapper<TAcPermissionInfo>().orderBy("t_ac_permission_info.order", true));
        return new ResponseResultWithListAll<>(acPermissionInfos.size(), acPermissionInfos);
    }

    /**
     * 新增权限
     *
     * @param acPermissionInfo
     * @return
     */
    @PostMapping("/permissions/add")
    @AppControllerLog(description = "新增权限", moduleType = LogTypes.moduleType.AC, operateValue = LogTypes.operateValue.add)
    @ApiOperation("新增权限")
    public ResponseResult permissionAdd(ServletRequest request,@Validated(AppConfigGroup.Add.class) TAcPermissionInfo acPermissionInfo) {
        if (tAcPermissionInfoService.insert(acPermissionInfo)) {
            shiroService.updatePermission();
            return new ResponseResult();
        }
        return new ResponseResult(BaseResponseResult.FAILURE, "保存失败");
    }

    /**
     * 更新权限
     *
     * @param acPermissionInfo
     * @return
     */
    @PostMapping("/permissions/update")
    @AppControllerLog(description = "修改权限", moduleType = LogTypes.moduleType.AC, operateValue = LogTypes.operateValue.update)
    @ApiOperation("新增权限")
    public ResponseResult permissionUpdate(ServletRequest request,@Validated(AppConfigGroup.Update.class) TAcPermissionInfo acPermissionInfo) {
        acPermissionInfo.setUpdateTime(new Date());
        if (tAcPermissionInfoService.updateById(acPermissionInfo)) {
            shiroService.updatePermission();
            return new ResponseResult();
        }
        return new ResponseResult(BaseResponseResult.FAILURE, "更新失败");
    }

    /**
     * 删除权限
     *
     * @param id
     * @return
     */
    @GetMapping("/permissions/delete/{id}")
    @Transactional
    public ResponseResult permissionDelete(ServletRequest request,@PathVariable("id") Long id) {
        if (tAcPermissionInfoService.safelyDelete(id)) {
            shiroService.updatePermission();
            return new ResponseResult();
        }
        return new ResponseResult(BaseResponseResult.FAILURE, "更新失败");
    }


    /**
     * 职位管理view
     *
     * @return
     */
    @GetMapping("/position")
    @AppControllerLog(description = "职位管理", moduleType = LogTypes.moduleType.AC, operateValue = LogTypes.operateValue.view)
    @ApiOperation("职位管理")
    public ModelAndView positionView(ServletRequest request) {
        ModelAndView mav = new ModelAndView("page/ac/position");
        return mav;
    }

    /**
     * 职位信息列表分页
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/positions/list")
    @AppControllerLog(description = "职位列表", moduleType = LogTypes.moduleType.AC, operateValue = LogTypes.operateValue.select)
    @ApiOperation("职位列表")
    public ResponseResultWithListAll<List<TAcPositionInfo>> positionList(ServletRequest request,@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        List<TAcPositionInfo> tAcPositionInfos = tAcPositionInfoService.selectPage(new Page<>(page, limit)).getRecords();
        return new ResponseResultWithListAll<>(tAcPositionInfos.size(), tAcPositionInfos);
    }

    /**
     * 职位新增
     *
     * @param tAcPositionInfo
     * @return
     */
    @PostMapping("/positions/add")
    @AppControllerLog(description = "职位新增", moduleType = LogTypes.moduleType.AC, operateValue = LogTypes.operateValue.add)
    @ApiOperation("职位新增")
    public ResponseResult positionsAdd(ServletRequest request,@Validated(AppConfigGroup.Add.class) TAcPositionInfo tAcPositionInfo) {
        if (tAcPositionInfoService.insert(tAcPositionInfo)) {
            return new ResponseResult();
        }
        return new ResponseResult(BaseResponseResult.FAILURE, "新增失败");
    }

    /**
     * 职位修改
     *
     * @param tAcPositionInfo
     * @return
     */
    @PostMapping("/positions/update")
    @AppControllerLog(description = "职位修改", moduleType = LogTypes.moduleType.AC, operateValue = LogTypes.operateValue.update)
    @ApiOperation("职位修改")
    public ResponseResult positionsUpdate(ServletRequest request,@Validated(AppConfigGroup.Update.class) TAcPositionInfo tAcPositionInfo) {
        tAcPositionInfo.setUpdateTime(new Date());
        if (tAcPositionInfoService.updateById(tAcPositionInfo)) {
            return new ResponseResult();
        }
        return new ResponseResult(BaseResponseResult.FAILURE, "修改失败");
    }

    /**
     * 职位删除
     *
     * @param id
     * @return
     */
    @GetMapping("/positions/delete/{id}")
    @Transactional
    @AppControllerLog(description = "职位删除", moduleType = LogTypes.moduleType.AC, operateValue = LogTypes.operateValue.delete)
    @ApiOperation("职位删除")
    public ResponseResult positionsDelete(ServletRequest request,@PathVariable("id") Long id) {
        if (tAcPositionInfoService.safelyDelete(id)) {
            return new ResponseResult();
        }
        return new ResponseResult(BaseResponseResult.FAILURE, "删除失败");
    }


    /**
     * 部门管理view
     *
     * @return
     */
    @GetMapping("/department")
    @AppControllerLog(description = "部门管理", moduleType = LogTypes.moduleType.AC, operateValue = LogTypes.operateValue.view)
    @ApiOperation("部门管理")
    public ModelAndView departmentView(ServletRequest request) {
        ModelAndView mav = new ModelAndView("page/ac/department");
        return mav;
    }

    /**
     * 部门信息列表分页
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/departments/list")
    @AppControllerLog(description = "部门列表", moduleType = LogTypes.moduleType.AC, operateValue = LogTypes.operateValue.select)
    @ApiOperation("部门列表")
    public ResponseResultWithListAll<List<TAcDepartmentInfo>> departmentsList(ServletRequest request,@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        List<TAcDepartmentInfo> tAcDepartmentInfos = tAcDepartmentInfoService.selectPage(new Page<>(page, limit)).getRecords();
        return new ResponseResultWithListAll<>(tAcDepartmentInfos.size(), tAcDepartmentInfos);
    }

    /**
     * 部门新增
     *
     * @param tAcDepartmentInfo
     * @return
     */
    @PostMapping("/departments/add")
    @AppControllerLog(description = "部门新增", moduleType = LogTypes.moduleType.AC, operateValue = LogTypes.operateValue.add)
    @ApiOperation("部门新增")
    public ResponseResult departmentsAdd(ServletRequest request,@Validated(AppConfigGroup.Add.class) TAcDepartmentInfo tAcDepartmentInfo) {
        if (tAcDepartmentInfoService.insert(tAcDepartmentInfo)) {
            return new ResponseResult();
        }
        return new ResponseResult(BaseResponseResult.FAILURE, "新增失败");
    }

    /**
     * 部门修改
     *
     * @param tAcDepartmentInfo
     * @return
     */
    @PostMapping("/departments/update")
    @AppControllerLog(description = "部门修改", moduleType = LogTypes.moduleType.AC, operateValue = LogTypes.operateValue.update)
    @ApiOperation("部门修改")
    public ResponseResult departmentsUpdate(ServletRequest request,@Validated(AppConfigGroup.Update.class) TAcDepartmentInfo tAcDepartmentInfo) {
        tAcDepartmentInfo.setUpdateTime(new Date());
        if (tAcDepartmentInfoService.updateById(tAcDepartmentInfo)) {
            return new ResponseResult();
        }
        return new ResponseResult(BaseResponseResult.FAILURE, "修改失败");
    }

    /**
     * 部门删除
     *
     * @param id
     * @return
     */
    @GetMapping("/departments/delete/{id}")
    @Transactional
    @AppControllerLog(description = "部门删除", moduleType = LogTypes.moduleType.AC, operateValue = LogTypes.operateValue.delete)
    @ApiOperation("部门删除")
    public ResponseResult departmentsDelete(ServletRequest request,@PathVariable("id") Long id) {
        if (tAcDepartmentInfoService.safelyDelete(id)) {
            return new ResponseResult();
        }
        return new ResponseResult(BaseResponseResult.FAILURE, "删除失败");
    }

    /**
     * 角色管理view
     *
     * @return
     */
    @GetMapping("/role")
    @AppControllerLog(description = "角色管理", moduleType = LogTypes.moduleType.AC, operateValue = LogTypes.operateValue.view)
    @ApiOperation("角色管理")
    public ModelAndView roleView(ServletRequest request) {
        ModelAndView mav = new ModelAndView("page/ac/role");
        return mav;
    }

    /**
     * 角色信息列表分页
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/roles/list")
    @AppControllerLog(description = "角色列表", moduleType = LogTypes.moduleType.AC, operateValue = LogTypes.operateValue.select)
    @ApiOperation("角色管理")
    public ResponseResultWithListAll<List<TAcRoleInfo>> rolesList(ServletRequest request,@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        List<TAcRoleInfo> tAcRoleInfos = tAcRoleInfoService.selectPage(new Page<>(page, limit)).getRecords();
        return new ResponseResultWithListAll<>(tAcRoleInfos.size(), tAcRoleInfos);
    }

    /**
     * 角色新增
     *
     * @param tAcRoleInfo
     * @return
     */
    @PostMapping("/roles/add")
    @AppControllerLog(description = "角色新增", moduleType = LogTypes.moduleType.AC, operateValue = LogTypes.operateValue.add)
    @ApiOperation("角色新增")
    public ResponseResult rolesAdd(ServletRequest request,@Validated(AppConfigGroup.Add.class) TAcRoleInfo tAcRoleInfo) {
        if (tAcRoleInfoService.insert(tAcRoleInfo)) {
            return new ResponseResult();
        }
        return new ResponseResult(BaseResponseResult.FAILURE, "新增失败");
    }

    /**
     * 角色修改
     *
     * @param tAcRoleInfo
     * @return
     */
    @PostMapping("/roles/update")
    @AppControllerLog(description = "角色修改", moduleType = LogTypes.moduleType.AC, operateValue = LogTypes.operateValue.update)
    @ApiOperation("角色修改")
    public ResponseResult rolesUpdate(ServletRequest request,@Validated(AppConfigGroup.Update.class) TAcRoleInfo tAcRoleInfo) {
        tAcRoleInfo.setUpdateTime(new Date());
        if (tAcRoleInfoService.updateById(tAcRoleInfo)) {
            return new ResponseResult();
        }
        return new ResponseResult(BaseResponseResult.FAILURE, "修改失败");
    }

    /**
     * 角色删除
     *
     * @param id
     * @return
     */
    @GetMapping("/roles/delete/{id}")
    @Transactional
    @AppControllerLog(description = "角色删除", moduleType = LogTypes.moduleType.AC, operateValue = LogTypes.operateValue.delete)
    @ApiOperation("角色删除")
    public ResponseResult rolesDelete(ServletRequest request,@PathVariable("id") Long id) {
        if (tAcRoleInfoService.safelyDelete(id)) {
            return new ResponseResult();
        }
        return new ResponseResult(BaseResponseResult.FAILURE, "删除失败");
    }

    /**
     * 获取权限树
     *
     * @param roleId
     * @return
     */
    @GetMapping("/roles/editPermission/getTree")
    @AppControllerLog(description = "获取权限树", moduleType = LogTypes.moduleType.AC, operateValue = LogTypes.operateValue.select)
    @ApiOperation("获取权限树")
    public ResponseResult permissionTree(ServletRequest request,@RequestParam("roleId") Long roleId) {
        if (roleId == null) throw new AppParmMissException("权限ID不能为空");
        List<BeanTreePermission> permissions = tAcRoleInfoService.getPermissionTreeByRoleId(roleId);
        return new ResponseResult(permissions);
    }

    /**
     * 更新权限
     *
     * @param authids
     * @return
     */
    @PostMapping("/roles/editPermission/update")
    @Transactional
    @AppControllerLog(description = "更新权限", moduleType = LogTypes.moduleType.AC, operateValue = LogTypes.operateValue.update)
    @ApiOperation("更新权限")
    public ResponseResult permissionTreeUpdate(ServletRequest request,@RequestParam(value = "authids[]") Integer[] authids, @RequestParam("roleId") Long roleId) {
        tAcRolePermissionService.updatePermissionByRoleId(authids, roleId);
        return new ResponseResult();
    }


    /**
     * 用户管理view
     *
     * @return
     */
    @GetMapping("/user")
    @AppControllerLog(description = "用户管理", moduleType = LogTypes.moduleType.AC, operateValue = LogTypes.operateValue.view)
    @ApiOperation("用户管理")
    public ModelAndView userView(ServletRequest request) {
        ModelAndView mav = new ModelAndView("page/ac/user");
        List<TAcRoleInfo> tAcRoleInfos = tAcRoleInfoService.selectList(null);
        List<TAcPositionInfo> tAcPositionInfos = tAcPositionInfoService.selectList(null);
        List<TAcDepartmentInfo> tAcDepartmentInfos = tAcDepartmentInfoService.selectList(null);
        mav.addObject("roleInfos", tAcRoleInfos);
        mav.addObject("positionInfos", tAcPositionInfos);
        mav.addObject("departmentInfos", tAcDepartmentInfos);
        return mav;
    }

    /**
     * 用户信息列表分页
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/users/list")
    @AppControllerLog(description = "用户信息列表", moduleType = LogTypes.moduleType.AC, operateValue = LogTypes.operateValue.select)
    @ApiOperation("用户信息列表")
    public ResponseResultWithListAll<List<BeanUserInfoList>> usersList(ServletRequest request,@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "limit", defaultValue = "10") Integer limit, @RequestParam(value = "userAccount", required = false) String userAccount) {
        List<BeanUserInfoList> tAcUserInfos = tAcUserInfoService.selectUserInfoList(new Page<>(page, limit), userAccount).getRecords();
        return new ResponseResultWithListAll<>(tAcUserInfos.size(), tAcUserInfos);
    }

    /**
     * 用户新增
     *
     * @param tAcUserInfo
     * @return
     */
    @PostMapping("/users/add")
    @Transactional
    @AppControllerLog(description = "用户新增", moduleType = LogTypes.moduleType.AC, operateValue = LogTypes.operateValue.add)
    @ApiOperation("用户新增")
    public ResponseResult usersAdd(ServletRequest request,@Validated(AppConfigGroup.Add.class) TAcUserInfo tAcUserInfo, @Validated @NotNull(message = "权限角色不能为空") Long userRole) {
        tAcUserInfo.setVersion(UUID.randomUUID().toString());
        tAcUserInfo.setUserPassword(DigestUtils.sha256Hex(tAcUserInfo.getUserPassword()));
        if (tAcUserInfoService.insertUserInfoWithUserRole(tAcUserInfo, userRole)) return new ResponseResult();
        return new ResponseResult(BaseResponseResult.FAILURE, "新增失败");
    }

    /**
     * 用户修改
     *
     * @param tAcUserInfo
     * @return
     */
    @PostMapping("/users/update")
    @Transactional
    @AppControllerLog(description = "用户修改", moduleType = LogTypes.moduleType.AC, operateValue = LogTypes.operateValue.update)
    @ApiOperation("用户修改")
    public ResponseResult usersUpdate(ServletRequest request,@Validated(AppConfigGroup.Update.class) TAcUserInfo tAcUserInfo, @Validated @NotNull(message = "权限角色不能为空") Long userRole) {
        tAcUserInfo.setUpdateTime(new Date());
        Wrapper<TAcUserRole> wrapper = new EntityWrapper<TAcUserRole>().eq("t_ac_user_role.user_id", tAcUserInfo.getId());
        TAcUserRole acUserRole = tAcUserRoleService.selectOne(wrapper);
        if (acUserRole != null) {
            if (acUserRole.getRoleId() != userRole) {
                tAcUserRoleService.delete(wrapper);
                TAcUserRole obj = new TAcUserRole();
                obj.setUserId(tAcUserInfo.getId());
                obj.setRoleId(userRole);
                tAcUserRoleService.insert(obj);
            }
        } else {
            TAcUserRole obj = new TAcUserRole();
            obj.setUserId(tAcUserInfo.getId());
            obj.setRoleId(userRole);
            tAcUserRoleService.insert(obj);
        }
        if (tAcUserInfoService.updateById(tAcUserInfo)) {
            return new ResponseResult();
        }
        return new ResponseResult(BaseResponseResult.FAILURE, "修改失败");
    }

    /**
     * 用户删除
     *
     * @param id
     * @return
     */
    @GetMapping("/users/delete/{id}")
    @Transactional
    @AppControllerLog(description = "用户删除", moduleType = LogTypes.moduleType.AC, operateValue = LogTypes.operateValue.delete)
    @ApiOperation("用户删除")
    public ResponseResult usersDelete(ServletRequest request,@PathVariable("id") Long id) {
        tAcUserRoleService.delete(new EntityWrapper<TAcUserRole>().eq("t_ac_user_role.user_id", id));
        if (tAcUserInfoService.deleteById(id)) {
            return new ResponseResult();
        }
        return new ResponseResult(BaseResponseResult.FAILURE, "删除失败");
    }



//    @GetMapping("/test")
//    @Transactional
//    public ResponseResult aa() {
//        TAcRolePermission tAcRolePermission = new TAcRolePermission();
//        tAcRolePermission.setRoleId(1L);
//        tAcRolePermission.setPermissionId(1L);
//        if (tAcRolePermissionService.insert(tAcRolePermission)) {
//            throw new AppParmMissException("sadasd");
//        }
//        TAcUserRole tAcUserRole = new TAcUserRole();
//        tAcUserRole.setRoleId(1L);
//        tAcUserRole.setUserId(1L);
//        tAcUserRoleService.insert(tAcUserRole);
//        return new ResponseResult();
//    }
}
