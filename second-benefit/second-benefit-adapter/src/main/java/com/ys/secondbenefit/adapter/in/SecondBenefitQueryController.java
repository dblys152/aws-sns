package com.ys.secondbenefit.adapter.in;

import com.ys.refs.user.domain.UserId;
import com.ys.secondbenefit.adapter.in.model.ApiResponse;
import com.ys.secondbenefit.adapter.in.model.SecondBenefitModel;
import com.ys.secondbenefit.application.port.in.GetSecondBenefitQuery;
import com.ys.secondbenefit.domain.SecondBenefits;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/second-benefits",
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class SecondBenefitQueryController {

    private final GetSecondBenefitQuery getSecondBenefitQuery;

    @GetMapping("/users/{userId}")
    public ResponseEntity getFirstBenefits(
            @PathVariable("userId") String userId) {

        SecondBenefits secondBenefits = getSecondBenefitQuery.getAllByUserId(UserId.of(userId));

        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(secondBenefits.getItems().stream()
                        .map(SecondBenefitModel::fromDomain)
                        .toList()));
    }
}
