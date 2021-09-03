package com.example.final_project.controller.command;

import com.example.final_project.model.entity.DeliveryRequest;
import com.example.final_project.model.service.DeliveryRequestService;
import com.example.final_project.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
/**
 * DirectionReport is a controller we'll be using to receive requests and send a response to the direction report page
 * Please see the {@link com.example.final_project.controller.command.Command} class for true identity
 */
public class DirectionReport implements Command{
    DeliveryRequestService deliveryRequestService= new DeliveryRequestService();
    UserService userService = new UserService();
    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {


        List<DeliveryRequest> deliveryRequestList = deliveryRequestService.getDirectionReport(
                new String(request.getParameter("city").getBytes("ISO-8859-1"), "UTF-8"));
        int page = 1;
        int recordsPerPage = 3;
        int noOfRecords = deliveryRequestList.size();

        if (noOfRecords == 0) {
            throw new RuntimeException("Not found");
        }

        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        if (recordsPerPage > noOfRecords)
            recordsPerPage = noOfRecords;

        if (request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));

        int recordPerPage = page * recordsPerPage;
        if ((noOfRecords % recordsPerPage) != 0 && page == noOfPages) {
            recordPerPage = (noOfRecords % recordsPerPage)+((page-1)*recordsPerPage);
        }
        List<DeliveryRequest> list = deliveryRequestService.getDirectionReports((page-1)*recordsPerPage,
        recordPerPage, new String(request.getParameter("city").getBytes("ISO-8859-1"), "UTF-8"));

        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("city", new String(request.getParameter("city").getBytes("ISO-8859-1"), "UTF-8"));
        request.setAttribute("directionReport", list);
        return "/WEB-INF/manager/directionReport.jsp";
    }
}
