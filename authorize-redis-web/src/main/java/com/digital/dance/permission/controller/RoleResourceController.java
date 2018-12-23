//package com.digital.dance.permission.controller;
//
//
//import com.digital.dance.permission.bo.*;
//import com.digital.dance.permission.service.*;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Scope;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//
//import com.digital.dance.common.controller.BaseController;
//
//import com.digital.dance.common.utils.*;
//
///**
// * Created by hpe on 2017/11/24.
// */
//@RestController
////@Controller
//@Scope(value="prototype")
//@RequestMapping("roleResource")
//public class RoleResourceController extends BaseController {
//
//    @Autowired
//    RoleResourceService roleResourceService;
//
//    static Class thisClass = RoleResourceController.class;
//
//    /**
//     *
//     * @param record
//     * @return
//     */
//    @RequestMapping(value="entity",method=RequestMethod.PUT)
//    @ResponseBody
//    public ResponseVo insert(@RequestBody RoleResourceBo record, HttpServletRequest request){
//        ResponseVo retVo = new ResponseVo();
//        try {
//            retVo.setResult(roleResourceService.insert(record));
//
//            retVo.setCode(Constants.ReturnCode.SUCCESS.Code());
//            retVo.setMsg("成功");
//
//        } catch (Exception e) {
//            retVo.setCode(Constants.ReturnCode.FAILURE.Code());
//            retVo.setMsg("添加失败，请刷新后再试！");
//
//            LoggerUtils.fmtError(thisClass, e, "添加Branch Resource报错。orgId:[%s]-resourceId:[%s]",record.getRoleId(), record.getResourceId());
//        }
//        return retVo;
//    }
//
//    /**
//     *
//     * @return
//     */
//    @RequestMapping(value="all",method=RequestMethod.GET)
//    @ResponseBody
//    public ResponseVo selectAll(){
//        ResponseVo retVo = new ResponseVo();
//        try {
//            retVo.setResult(roleResourceService.selectAll());
//            retVo.setCode(Constants.ReturnCode.SUCCESS.Code());
//            retVo.setMsg("成功");
//
//        }catch (Exception ex ){
//            retVo.setCode(Constants.ReturnCode.FAILURE.Code());
//            retVo.setMsg("失败，请刷新后再试！");
//            LoggerUtils.fmtError(thisClass, ex, ex.getMessage());
//        }
//        return retVo;
//    }
//
//    /**
//     *
//     * @param roleId
//     * @param resourceId
//     * @return
//     */
//    @RequestMapping(value="primarykey",method=RequestMethod.DELETE)
//    @ResponseBody
//    public ResponseVo deleteByPrimaryKey( Long roleId, Long resourceId,HttpServletRequest request){
//        ResponseVo retVo = new ResponseVo();
//        try {
//            retVo.setResult(roleResourceService.deleteByPrimaryKey(roleId, resourceId));
//
//            retVo.setCode(Constants.ReturnCode.SUCCESS.Code());
//            retVo.setMsg("成功");
//
//        } catch (Exception e) {
//            retVo.setCode(Constants.ReturnCode.FAILURE.Code());
//            retVo.setMsg("失败，请刷新后再试！");
//            LoggerUtils.fmtError(thisClass, e, "删除 role resource报错。roleId:[%s]-resourceId:[%s]",roleId, resourceId);
//        }
//        return retVo;
//    }
//}
