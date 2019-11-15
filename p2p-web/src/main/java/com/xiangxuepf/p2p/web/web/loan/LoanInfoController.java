package com.xiangxuepf.p2p.web.web.loan;

import com.xiangxuepf.p2p.exterface.model.loan.LoanInfo;
import com.xiangxuepf.p2p.exterface.service.loan.BidInfoService;
import com.xiangxuepf.p2p.exterface.service.loan.LoanInfoService;
import com.xiangxuepf.p2p.exterface.vo.BidInfoVO;
import com.xiangxuepf.p2p.exterface.vo.PaginationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mhw
 * @create 2019-11-14 18:23
 */
@Controller
public class LoanInfoController {
    @Autowired
    private LoanInfoService loanInfoService;
    @Autowired
    private BidInfoService bidInfoService;

    /*
    * 关于RequestMapping映射有规范； //@
	/类名，一般就是包名/方法名
	*/
    @RequestMapping("/loan/loan")
    public String loan(
            HttpServletRequest request,
            @RequestParam(value = "ptype",required = false) Integer ptype,
            @RequestParam(value = "currentPage",required = false) Integer currentPage,
            Model model

    ){
        //判断当前页码是否为空；为空，则默认是第1页；
        if (null == currentPage) {
            currentPage = 1;
        }
        //准备分页查询参数；
        Map<String,Object> paramMap = new HashMap<String,Object>();
        //产品类型；
        if (null != ptype) {
            paramMap.put("productType",ptype);
        }
        int pageSize = 9;
        //起启下标，从第几条开始查，查多少条；
        paramMap.put("currentPage",(currentPage-1)*pageSize);
        //截取的长度，每页显示条数；
        paramMap.put("pageSize",pageSize);
        /*分页查询产品信息列表；参数，产品类型，页码，每页显示数；返回值：总记录条数，
         当前页要显示的数据；通常会返回一个分页模型对象(自定义bean)或者map;*/
        PaginationVO<LoanInfo> paginationVO = loanInfoService.queryLoanInfoByPage(paramMap);
        //计算总页数；
        int totalPage = paginationVO.getTotal().intValue()/pageSize;
            //再次求余；
        int mod = paginationVO.getTotal().intValue()%pageSize;
        if(mod > 0){
            totalPage = totalPage + 1;
        }
        //总记录数
        model.addAttribute("totalRows",paginationVO.getTotal());
        //总页数；
        model.addAttribute("totalPage",totalPage);
        //每页显示的数据
        model.addAttribute("loanInfoList",paginationVO.getDataList());
        //当前页码；
        model.addAttribute("currentPage",currentPage);
        //产品类型；
        if (null != ptype) {
            model.addAttribute("ptype",ptype);
        }

//        String contextPath = request.getContextPath();

        //TODO
        //用户投资排行榜

        return "loan";
    }

    @RequestMapping("/loan/loanInfo")
    public String loanInfo(
            HttpServletRequest request,
            Model model,
            @RequestParam(value = "id",required = true) Integer id
    ){
        //根据产品标识获取产品的详情；
        LoanInfo loanInfo = loanInfoService.queryLoanInfoById(id);
        //根据产品标识获取该产品的所有投资记录；
        List<BidInfoVO> bidInfoVOList = bidInfoService.queryBidInfoListByLoanId(id);
        model.addAttribute("loanInfo",loanInfo);
        model.addAttribute("bidInfoVOList",bidInfoVOList);
        //TODO
        //获取当前用户的账号可用余额；


        return "loanInfo";
    }
}
