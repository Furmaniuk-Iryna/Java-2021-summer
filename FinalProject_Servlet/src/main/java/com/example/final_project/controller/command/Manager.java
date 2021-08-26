package com.example.final_project.controller.command;

import com.example.final_project.model.entity.DeliveryRequest;
import com.example.final_project.model.service.DeliveryRequestService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class Manager implements Command{
    private DeliveryRequestService deliveryRequestService = new DeliveryRequestService();
    List<DeliveryRequest> deliveryRequestList = deliveryRequestService.getAllDeliveryRequests();

    @Override
    public synchronized String execute(HttpServletRequest request) {
        int page = 1;
        int recordsPerPage = 2;
        int recordPerPage = page*recordsPerPage;

        int noOfRecords = deliveryRequestList.size();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        if (recordsPerPage>noOfRecords)
           recordsPerPage=noOfRecords;

        if(request.getParameter("page") != null)
          page = Integer.parseInt(request.getParameter("page"));

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
