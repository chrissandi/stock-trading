package com.apps.ohlc.calculator.service.Impl;

import com.apps.ohlc.calculator.dto.OhlcDTO;
import com.apps.ohlc.calculator.dto.TransactionDTO;
import com.apps.ohlc.calculator.service.CalculatorService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class CalculatorServiceImpl implements CalculatorService {
    private final RedisTemplate<String, OhlcDTO> redisTemplate;

    public CalculatorServiceImpl(RedisTemplate<String, OhlcDTO> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public OhlcDTO getStockSummary(String stockCode) {
        return getRedisValue(stockCode);
    }

    @Override
    public OhlcDTO addTransaction(TransactionDTO transactionDTO) {
        OhlcDTO ohlcDTO = getRedisValue("OHLC:"+transactionDTO.getStockCode());
        if(transactionDTO.getQuantity()==0){
            ohlcDTO.setPreviousPrice(transactionDTO.getPrice());
        }
        else {
            if(ohlcDTO.getOpenPrice()==0){
                ohlcDTO.setOpenPrice(transactionDTO.getPrice());
            }
            if(ohlcDTO.getHighestPrice()<transactionDTO.getPrice()){
                ohlcDTO.setHighestPrice(transactionDTO.getPrice());
            }
            if (ohlcDTO.getLowestPrice()==0||ohlcDTO.getLowestPrice()>transactionDTO.getPrice()){
                ohlcDTO.setLowestPrice(transactionDTO.getPrice());
            }
            ohlcDTO.setClosePrice(transactionDTO.getPrice());
            ohlcDTO.setVolume(ohlcDTO.getVolume()+transactionDTO.getQuantity());
            ohlcDTO.setValue(ohlcDTO.getValue()+(transactionDTO.getQuantity()*transactionDTO.getPrice()));
            ohlcDTO.setAvgPrice((ohlcDTO.getAvgPrice()+transactionDTO.getPrice())/2);
        }
        System.out.println("Stock Code "+transactionDTO.getStockCode()+" O:"+ohlcDTO.getOpenPrice()+ " H:"+ohlcDTO.getHighestPrice()+
                " L:"+ohlcDTO.getLowestPrice()+" C:"+ohlcDTO.getClosePrice());
        saveRedisKey("OHLC:"+transactionDTO.getStockCode(), ohlcDTO);
        return ohlcDTO;
    }

    private void saveRedisKey(String stockCode, OhlcDTO dto) {
        redisTemplate.opsForValue().set(stockCode, dto);
    }
    private OhlcDTO getRedisValue(String key) {
        OhlcDTO ohlcDTO = new OhlcDTO();
        if(redisTemplate.hasKey(key)){
            ohlcDTO = (OhlcDTO) redisTemplate.opsForValue().get(key);
        }
        return ohlcDTO;
    }
}
