package com.example.final_project.controller.command;

import com.example.final_project.model.entity.DeliveryRequest;
import com.example.final_project.model.service.DeliveryRequestService;
import com.example.final_project.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class Manager implements Command{
    private DeliveryRequestService deliveryRequestService = new DeliveryRequestService();
    private UserService userService= new UserService();
    @Override
    public synchronized String execute(HttpServletRequest request) {
        Locale locale = new Locale(request.getParameter("locale")==null ? "en" : request.getParameter("locale"));
        String role = Optional.ofNullable(userService.getUserByUsername((String) request.getSession().getAttribute("userName")).getRole())
                .orElseThrow(()-> new RuntimeException("FORBIDDEN"));

        if (!role.equals("MANAGER")){
            return "redirect:/logout";
        }

        List<DeliveryRequest> deliveryRequestList = deliveryRequestService.getAllDeliveryRequests();

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
        List<DeliveryRequest> list = deliveryRequestService.getPagesDeliveryRequests((page-1)*recordsPerPage
                , recordPerPage);

        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);

        request.setAttribute("deliveryRequestList", list);
        return "/WEB-INF/manager/manager.jsp";
    }
}
