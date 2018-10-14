package com.appd.demo.services;

import com.appd.demo.models.AccountVO;
import com.appd.demo.models.BalanceVO;
import com.appd.demo.models.TransferVO;
import com.appd.demo.responses.BalanceResponse;
import com.appd.demo.responses.TransferResponse;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

import java.text.DecimalFormat;

public class BalanceService extends HystrixCommand<BalanceResponse> {

    private AccountVO accountVO;

    public BalanceService(AccountVO accountVO){
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("AccountGroup"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().
                        withMetricsRollingStatisticalWindowInMilliseconds(60000)));

        this.accountVO = accountVO;

    }

    @Override
    protected BalanceResponse run() throws InterruptedException{

        BalanceResponse balanceResponse = new BalanceResponse();

        // The balance is a number between -500 and 10000
        this.accountVO.getBalance().setAmount(-500 + Math.random() * 10500);

        // Artificial delay
        Thread.sleep(100 + (long) (Math.random() * 800));

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        balanceResponse.setMessage("Balance is R$" + df.format(this.accountVO.getBalance().getAmount()));
        balanceResponse.setSuccess(true);

        return balanceResponse;

    }

    @Override
    protected BalanceResponse getFallback() {
        BalanceResponse balanceResponse = new BalanceResponse();

        balanceResponse.setMessage("Falha na consulta de saldo!");
        balanceResponse.setSuccess(false);
        return balanceResponse;
    }

}

