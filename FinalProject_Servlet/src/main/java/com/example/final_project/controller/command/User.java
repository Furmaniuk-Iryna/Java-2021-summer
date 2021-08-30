package com.example.final_project.controller.command;

import com.example.final_project.model.entity.DeliveryRequest;
import com.example.final_project.model.service.DeliveryRequestService;
import com.example.final_project.model.service.ReceiptService;
import com.example.final_project.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class User implements Command{
    UserService userService = new UserService();
    DeliveryRequestService deliveryRequestService=new DeliveryRequestService();
    ReceiptService receiptService= new ReceiptService();
    @Override
    public String execute(HttpServletRequest request) {
     String role = Optional.ofNullable(userService.getUserByUsername((String) request.getSession().getAttribute("userName")).getRole())
             .orElseThrow(()-> new RuntimeException("FORBIDDEN"));
           if (!role.equals("USER")){
               return "redirect:/logout";
           }
if(Boolean.parseBoolean(request.getParameter("recharge"))){
    userService.recharge(userService.getUserByUsername((String) request.getSession().getAttribute("userName")),
            Integer.parseInt(request.getParameter("sum")));
}
           if(request.getParameter("receipt")!=null){
               request.setAttribute("paid",receiptService.checkPay(userService.getUserByUsername((String) request.getSession().getAttribute("userName")),
                       receiptService.findReceiptByRequest(Long.parseLong(request.getParameter("receipt")))));
           }
        List<DeliveryRequest> deliveryRequestList = deliveryRequestService.findByUser(userService.getUserByUsername((String) request.getSession().getAttribute("userName")).getIdUser());

        int page = 1;
        int recordsPerPage = 3;//const

        int noOfRecords = deliveryRequestList.size();
        if(noOfRecords==0){
            throw new RuntimeException("Not found");
        }
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        if (recordsPerPage>noOfRecords)
            recordsPerPage=noOfRecords;

        if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));

        int recordPerPage = page*recordsPerPage;
        if((noOfRecords%recordsPerPage)!=0 && page==noOfPages){
            recordPerPage = (noOfRecords % recordsPerPage)+((page-1)*recordsPerPage);
        }
        List<DeliveryRequest> list = deliveryRequestService.getReportsForUser((page-1)*recordsPerPage,
                recordPerPage,userService.getUserByUsername((String) request.getSession().getAttribute("userName")).getIdUser());
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("day",request.getParameter("day"));
        request.setAttribute("requests",list);
        request.setAttribute("receipts",receiptService.findAllReceipts());
        request.setAttribute("balance",userService.getUserByUsername((String) request.getSession().getAttribute("userName")).getBalance());
        return "/WEB-INF/user/user.jsp";
    }
}