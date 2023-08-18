package com.ys.firstbenefit.adapter.in;

import com.ys.firstbenefit.adapter.in.model.FirstBenefitModel;
import com.ys.firstbenefit.application.port.in.GetFirstBenefitParams;
import com.ys.firstbenefit.application.port.in.GetFirstBenefitQuery;
import com.ys.firstbenefit.domain.FirstBenefits;
import com.ys.infra.utils.ApiResponse;
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
@RequestMapping(value = "/v1/first-benefits",
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class FirstBenefitQueryController {

    private final GetFirstBenefitQuery getFirstBenefitQuery;

    @GetMapping("")
    public ResponseEntity getFirstBenefits(
            @RequestParam(value = "userId", required = false) String userId) {

        GetFirstBenefitParams params = GetFirstBenefitParams.builder()
                .userId(userId)
                .build();
        FirstBenefits firstBenefits = getFirstBenefitQuery.getAllByParams(params);

        if (firstBenefits.isEmpty()) {
            throw new NoSuchElementException(String.format("데이터를 찾을 수 없습니다. %s", params.toString()));
        }

        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(HttpStatus.OK.value(), firstBenefits.getItems().stream()
                        .map(FirstBenefitModel::fromDomain)
                        .toList()));
    }
}
