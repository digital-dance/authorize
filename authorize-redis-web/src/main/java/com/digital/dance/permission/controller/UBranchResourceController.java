//package com.digital.dance.permission.controller;
//
//import javax.servlet.http.HttpServletRequest;
//
//import com.digital.dance.common.utils.LoggerUtils;
//import com.digital.dance.common.utils.ResponseVo;
//import com.digital.dance.permission.bo.BranchResourceBo;
//import com.digital.dance.permission.service.*;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Scope;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import com.digital.dance.common.controller.BaseController;
//import com.digital.dance.common.utils.*;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * Created by hpe on 2017/11/24.
// */
////@Controller
//@RestController
//@Scope(value="prototype")
//@RequestMapping("sbranchResource")
//public class UBranchResourceController extends BaseController {
//
//    @Autowired
//    SBranchResourceService sBranchResourceService;
//
//    static Class thisClass = UBranchResourceController.class;
//    /**
//     *
//     * @param record
//     * @return
//     */
//    @RequestMapping(value="entity",method=RequestMethod.PUT)
//    @ResponseBody
//    public ResponseVo insert(@RequestBody BranchResourceBo record, HttpServletRequest request){
//        ResponseVo retVo = new ResponseVo();
//        try {
//            retVo.setResult(sBranchResourceService.insert(record));
//            retVo.setCode(Constants.ReturnCode.SUCCESS.Code());
//            retVo.setMsg("成功");
//        } catch (Exception e) {
//            retVo.setCode(Constants.ReturnCode.FAILURE.Code());
//            retVo.setMsg("添加失败，请刷新后再试！");
//            LoggerUtils.fmtError(thisClass, e, "添加Branch Resource报错。orgId:[%s]-resourceId:[%s]",record.getOrgId(), record.getResourceId());
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
//            retVo.setResult(sBranchResourceService.selectAll());
//            retVo.setCode(Constants.ReturnCode.SUCCESS.Code());
//            retVo.setMsg("成功");
//        } catch (Exception e) {
//            retVo.setCode(Constants.ReturnCode.FAILURE.Code());
//            retVo.setMsg("失败，请刷新后再试!");
//            LoggerUtils.fmtError(thisClass, e, e.getMessage());
//        }
//        return retVo;
//
//    }
//
//    /**
//     *
//     * @param orgId
//     * @param resourceId
//     * @return
//     */
//    @RequestMapping(value="primarykey",method=RequestMethod.DELETE)
//    @ResponseBody
//    public ResponseVo deleteByPrimaryKey( String orgId, Long resourceId,HttpServletRequest request){
//        ResponseVo retVo = new ResponseVo();
//        try {
//            retVo.setResult(sBranchResourceService.deleteByPrimaryKey(orgId, resourceId));
//            retVo.setCode(Constants.ReturnCode.SUCCESS.Code());
//            retVo.setMsg("成功");
//        } catch (Exception e) {
//            retVo.setCode(Constants.ReturnCode.FAILURE.Code());
//            retVo.setMsg("失败，请刷新后再试！");
//            LoggerUtils.fmtError(getClass(), e, "删除Branch Resource报错。orgId:[%s]-resourceId:[%s]",orgId, resourceId);
//        }
//        return retVo;
//    }
//}
