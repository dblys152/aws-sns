package com.ys.firstbenefit.adapter.in;

import com.ys.firstbenefit.adapter.in.model.ApiResponse;
import com.ys.firstbenefit.adapter.in.model.FirstBenefitModel;
import com.ys.firstbenefit.application.port.in.GetFirstBenefitQuery;
import com.ys.firstbenefit.domain.FirstBenefits;
import com.ys.refs.user.domain.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/first-benefits",
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class FirstBenefitQueryController {

    private final GetFirstBenefitQuery getFirstBenefitQuery;

    @GetMapping("/users/{userId}")
    public ResponseEntity getFirstBenefits(
            @PathVariable("userId") String userId) {

        FirstBenefits firstBenefits = getFirstBenefitQuery.getAllByUserId(UserId.of(userId));

        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(firstBenefits.getItems().stream()
                        .map(FirstBenefitModel::fromDomain)
                        .toList()));
    }
}
