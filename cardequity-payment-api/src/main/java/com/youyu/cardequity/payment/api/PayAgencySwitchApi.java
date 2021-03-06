package com.youyu.cardequity.payment.api;

import com.youyu.cardequity.payment.dto.PayAgencySwitchDto;
import com.youyu.cardequity.payment.dto.PayAgencySwitchResponseDto;
import com.youyu.common.api.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author panqingqing
 * @version v1.0
 * @date 2018年12月12日 下午10:00:00
 * @work 支付机构开关Api定义
 */
@Api(tags = "支付机构开关Api")
@FeignClient(name = "cardequity-payment")
@RequestMapping(path = "/payAgencySwitch")
public interface PayAgencySwitchApi {

    /**
     * 根据支付机构代码查询支付机构开关信息
     *
     * @param payAgencySwitchDto
     * @return
     */
    @ApiOperation(value = "根据支付机构代码查询支付机构开关信息")
    @PostMapping(value = "/getPayAgencySwitch")
    Result<PayAgencySwitchResponseDto> getPayAgencySwitch(@RequestBody PayAgencySwitchDto payAgencySwitchDto);
}