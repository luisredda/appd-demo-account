package com.appd.demo;

import com.appd.demo.models.AccountVO;
import com.appd.demo.responses.BalanceResponse;
import com.appd.demo.responses.TransferResponse;
import com.appd.demo.models.TransferVO;
import com.appd.demo.services.BalanceService;
import com.appd.demo.services.TransferService;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by dlopes on 5/20/18.
 */

@RestController
public class AccountController {


    @RequestMapping(value = "/api/transfer", produces = "application/json")
    public ResponseEntity<TransferResponse> transfer(@RequestBody String body) throws Exception{

        Gson gson = new Gson();
        TransferVO transferVO = gson.fromJson(body, TransferVO.class);


        TransferResponse transferResponse = new TransferService(transferVO).execute();

        if (transferResponse.isSuccess()) {
            return ResponseEntity.ok(transferResponse);
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(transferResponse);
        }

    }

    @RequestMapping(value = "/api/balance", produces = "application/json")
    public ResponseEntity<BalanceResponse> balance(@RequestBody String body) throws Exception{

        Gson gson = new Gson();
        AccountVO accountVO = gson.fromJson(body, AccountVO.class);

        BalanceResponse balanceResponse = new BalanceService(accountVO).execute();

        if (balanceResponse.isSuccess()) {
            return ResponseEntity.ok(balanceResponse);
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(balanceResponse);
        }

    }
}
