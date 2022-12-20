package com.moskalyuk.clevertec.http.controller;

import com.moskalyuk.clevertec.service.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/check")
public class CheckRestController {

    private final CheckService checkService;

    @Autowired
    public CheckRestController(CheckService checkService) {
        this.checkService = checkService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, BigDecimal> getCheck(@RequestParam List<Integer> itemId, @RequestParam(name = "card") String card ){
        return checkService.getCheck(itemId,card);
    }
}
