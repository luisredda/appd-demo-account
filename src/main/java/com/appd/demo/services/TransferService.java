package com.appd.demo.services;

import com.appd.demo.responses.TransferResponse;
import com.appd.demo.models.TransferVO;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 * Created by dlopes on 5/20/18.
 */

public class TransferService extends HystrixCommand<TransferResponse> {

    private TransferVO transferVO;

    public TransferService(TransferVO transferVO){
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("AccountGroup"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().
                        withMetricsRollingStatisticalWindowInMilliseconds(60000)));

        this.transferVO = transferVO;

    }

    @Override
    protected TransferResponse run() throws InterruptedException{

        TransferResponse transferResponse = new TransferResponse();

        Thread.sleep(100 + (long) (Math.random() * 800));
        transferResponse.setMessage(String.format( "%s transferiu R$%.2f para %s", transferVO.getFrom(), transferVO.getAmount(), transferVO.getTo()));
        transferResponse.setSuccess(true);

        return transferResponse;

    }

    @Override
    protected TransferResponse getFallback() {
        TransferResponse transferResponse = new TransferResponse();

        transferResponse.setMessage("Falha na transferencia!");
        transferResponse.setSuccess(false);
        return transferResponse;
    }

}

