package com.example.final_project.controller.command;

import com.example.final_project.model.entity.DeliveryRequest;
import com.example.final_project.model.service.DeliveryRequestService;
import com.example.final_project.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
/**
 * ReportByDays is a controller we'll be using to receive requests and send a response to the report by days page
 * Please see the {@link com.example.final_project.controller.command.Command} class for true identity
 */
public class ReportByDays implements Command {
    private final DeliveryRequestService deliveryRequestService = new DeliveryRequestService();
    private final UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {


        List<DeliveryRequest> deliveryRequestList = deliveryRequestService.getReportByDay(LocalDate.parse(request.getParameter("day")));

        int page = 1;
        int recordsPerPage = 3;
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
        List<DeliveryRequest> list = deliveryRequestService.getReportByDays((page-1)*recordsPerPage,
                recordPerPage,LocalDate.parse(request.getParameter("day")));

        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("day", request.getParameter("day"));
        request.setAttribute("reportByDays", list);

        return "/WEB-INF/manager/reportByDays.jsp";
    }}
