package com.example.final_project.controller;

import com.example.final_project.entity.DeliveryRequest;
import com.example.final_project.entity.User;
import com.example.final_project.service.AddressService;
import com.example.final_project.service.DeliveryRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/deliveryRequests/edit")
public class DeliveryRequestRestController {

    @Autowired
    DeliveryRequestService deliveryRequestService;

    @Autowired
    AddressService addressService;


    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DeliveryRequest>> getAllDeliveryRequests() {
        List<DeliveryRequest> deliveryRequests = deliveryRequestService.findAll();
        return deliveryRequests.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(deliveryRequests,HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeliveryRequest> getDeliveryRequest(@PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return deliveryRequestService.findById(id).isPresent()
                ? new ResponseEntity<>(deliveryRequestService.findById(id).get(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<DeliveryRequest> saveDeliveryRequest(@RequestBody @Valid DeliveryRequest deliveryRequest,
                                                               BindingResult bindingResult,
                                                               @AuthenticationPrincipal User user1) {

        deliveryRequest.setAddress(addressService.getById(deliveryRequest.getAddress().getId()));
        DeliveryRequest deliveryRequest1 = DeliveryRequest.builder()
                .type_en(deliveryRequest.getType_en())
                .weight(deliveryRequest.getWeight())
                .length(deliveryRequest.getLength())
                .width(deliveryRequest.getWidth())
                .height(deliveryRequest.getHeight())
                .address(addressService.getById(deliveryRequest.getAddress().getId()))
                .dateOfArrival(deliveryRequestService.newDateOfArrival(deliveryRequest))
                .volume(deliveryRequestService.calculateVolume(
                        deliveryRequest.getLength(), deliveryRequest.getHeight(), deliveryRequest.getWidth()))
                .type_uk(deliveryRequest.getType_en().equals("Cargo") ? "Вантаж" : "Палета")
                .user(user1)
                .build();

        deliveryRequestService.save(deliveryRequest1);
        return new ResponseEntity<>(deliveryRequest1,HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<DeliveryRequest> updateDeliveryRequest(@PathVariable("id") Long id,
                                                                 @RequestBody @Valid DeliveryRequest deliveryRequest,
                                                                 @AuthenticationPrincipal User user1) {
        DeliveryRequest deliveryRequestFromDB = deliveryRequestService.findById(id).orElseThrow(() -> new RuntimeException("Not Found!"));
        deliveryRequestFromDB.setType_en(deliveryRequest.getType_en());
        deliveryRequestFromDB.setType_uk(deliveryRequest.getType_en().equals("Cargo") ? "Вантаж" : "Палета");
        deliveryRequestFromDB.setWeight(deliveryRequest.getWeight());
        deliveryRequestFromDB.setLength(deliveryRequest.getLength());
        deliveryRequestFromDB.setWidth(deliveryRequest.getWidth());
        deliveryRequestFromDB.setHeight(deliveryRequest.getHeight());
        deliveryRequestFromDB.setAddress(addressService.getById(deliveryRequest.getAddress().getId()));
        deliveryRequestFromDB.setVolume(deliveryRequestService.calculateVolume(
                deliveryRequest.getLength(), deliveryRequest.getHeight(), deliveryRequest.getWidth()));
        deliveryRequestFromDB.setDateOfArrival(deliveryRequestService.newDateOfArrival(deliveryRequestFromDB));
        deliveryRequestService.save(deliveryRequestFromDB);
        return new ResponseEntity<>(deliveryRequestFromDB, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeliveryRequest> deleteDeliveryRequest(@PathVariable("id") Long id) {
        Optional<DeliveryRequest> deliveryRequest = deliveryRequestService.findById(id);
        if (!deliveryRequest.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        deliveryRequestService.delete(deliveryRequest.get());
        return new ResponseEntity<>(deliveryRequest.get(), HttpStatus.OK);
    }
}
