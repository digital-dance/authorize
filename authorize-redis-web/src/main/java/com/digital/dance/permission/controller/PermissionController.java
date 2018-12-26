package com.digital.dance.permission.controller;

import java.util.List;
import java.util.Map;

import com.digital.dance.core.shiro.service.impl.CacheInitializer;
import com.digital.dance.framework.sso.entity.LoginUserRole;
import com.digital.dance.permission.bo.ResourceBo;
import com.digital.dance.permission.bo.RoleBranchResourceBo;
import com.digital.dance.permission.bo.UserPrivilegeBo;
import com.digital.dance.permission.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.digital.dance.common.controller.BaseController;

import com.digital.dance.core.mybatis.page.Pagination;

import com.digital.dance.common.utils.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
//@Scope(value="prototype")
@RequestMapping("/permission")
public class PermissionController extends BaseController {
	
	@Autowired
	PermissionService uPermissionService;

	/**
	 * 权限添加
	 * @param psermission
	 * @return
	 */
	@RequestMapping(value="addPermission",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> addPermission(ResourceBo psermission){
		try {
			int entity = uPermissionService.insert(psermission);
			resultMap.put("status", 200);
			resultMap.put("entity", psermission);
		} catch (Exception e) {
			resultMap.put("status", 500);
			resultMap.put("message", "添加失败，请刷新后再试！");
			LoggerUtils.fmtError(getClass(), e, "添加权限报错。source[%s]", psermission.toString());
		}
		return resultMap;
	}

	/**
	 *
	 * @return
	 */
	@RequestMapping(value="index", method=RequestMethod.GET)
	@ResponseBody
	public ResponseVo selectPagedAll(ResourceBo resource){
		ResponseVo retVo = new ResponseVo();
		try {
			retVo.setResult(uPermissionService.selectPagedAll(resource));
			retVo.setCode(Constants.ReturnCode.SUCCESS.Code());
			retVo.setMsg("成功");

		}catch (Exception ex ){
			retVo.setCode(Constants.ReturnCode.FAILURE.Code());
			retVo.setMsg("失败，请刷新后再试！");
			LoggerUtils.fmtError(getClass(), ex, ex.getMessage());
		}
		return retVo;
	}


	/**
	 *http://localhost:8008/authorize-redis-war-0.0.2-SNAPSHOT/permission/resource?roleId=17&orgId=FB1&departmentId=FB1
	 * @return
	 */
	@RequestMapping(value="resource", method=RequestMethod.GET)
	@ResponseBody
	public ResponseVo listRoleBranchResourceByKey(HttpServletRequest request,
												  HttpServletResponse response, RoleBranchResourceBo loginUserRole){
		ResponseVo retVo = new ResponseVo();
		try {
			List<ResourceBo> resourceBos = CacheInitializer.listRoleBranchResourceByKey(loginUserRole.getRoleId()
					, loginUserRole.getOrgId(), loginUserRole.getDepartmentId());
			retVo.setResult( resourceBos );
			retVo.setCode(Constants.ReturnCode.SUCCESS.Code());
			retVo.setMsg("成功");

		}catch (Exception ex ){
			retVo.setCode(Constants.ReturnCode.FAILURE.Code());
			retVo.setMsg("失败，请刷新后再试！");
			LoggerUtils.fmtError(getClass(), ex, ex.getMessage());
		}
		return retVo;
	}

	/**
	 *http://localhost:8008/authorize-redis-war-0.0.2-SNAPSHOT/permission/resource/user?userId=1
	 * @return
	 */
	@RequestMapping(value="resource/user", method=RequestMethod.GET)
	@ResponseBody
	public ResponseVo listUserPrivilegeResourceByKey(HttpServletRequest request,
													 HttpServletResponse response, UserPrivilegeBo loginUserRole){
		ResponseVo retVo = new ResponseVo();
		try {
			List<ResourceBo> resourceBos = CacheInitializer.listUserPrivilegeResourceByKey( loginUserRole.getUserId() );
			retVo.setResult( resourceBos );
			retVo.setCode(Constants.ReturnCode.SUCCESS.Code());
			retVo.setMsg("成功");

		}catch (Exception ex ){
			retVo.setCode(Constants.ReturnCode.FAILURE.Code());
			retVo.setMsg("失败，请刷新后再试！");
			LoggerUtils.fmtError(getClass(), ex, ex.getMessage());
		}
		return retVo;
	}

	/**
	 *
	 * @param roleId
	 * @param resourceId
	 * @return
	 */
	@RequestMapping(value="primarykey",method=RequestMethod.GET)
	@ResponseBody
	public ResponseVo deleteByPrimaryKey( String roleId, String resourceId,HttpServletRequest request){
		ResponseVo retVo = new ResponseVo();
		try {
			retVo.setResult(uPermissionService.deleteByPrimaryKey(resourceId));

			retVo.setCode(Constants.ReturnCode.SUCCESS.Code());
			retVo.setMsg("成功");

		} catch (Exception e) {
			retVo.setCode(Constants.ReturnCode.FAILURE.Code());
			retVo.setMsg("失败，请刷新后再试！");
			LoggerUtils.fmtError(getClass(), e, "删除 role resource报错。roleId:[%s]-resourceId:[%s]",roleId, resourceId);
		}
		return retVo;
	}
}
