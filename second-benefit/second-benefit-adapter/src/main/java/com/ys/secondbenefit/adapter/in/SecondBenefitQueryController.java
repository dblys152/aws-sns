package com.ys.secondbenefit.adapter.in;

import com.ys.infra.utils.ApiResponse;
import com.ys.secondbenefit.adapter.in.model.SecondBenefitModel;
import com.ys.secondbenefit.application.port.in.GetSecondBenefitParams;
import com.ys.secondbenefit.application.port.in.GetSecondBenefitQuery;
import com.ys.secondbenefit.domain.SecondBenefits;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/v1/second-benefits",
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class SecondBenefitQueryController {

    private final GetSecondBenefitQuery getSecondBenefitQuery;

    @GetMapping("")
    public ResponseEntity getSecondBenefits(
            @RequestParam(value = "userId", required = false) String userId) {

        GetSecondBenefitParams params = GetSecondBenefitParams.builder()
                .userId(userId)
                .build();
        SecondBenefits secondBenefits = getSecondBenefitQuery.getAllByParams(params);

        if (secondBenefits.isEmpty()) {
            throw new NoSuchElementException(String.format("데이터를 찾을 수 없습니다. %s", params.toString()));
        }

        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(HttpStatus.OK.value(), secondBenefits.getItems().stream()
                        .map(SecondBenefitModel::fromDomain)
                        .toList()));
    }
}
