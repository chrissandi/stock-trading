package com.apps.stocktrading.stock.service.impl;

import com.apps.stocktrading.base.util.PageableUtil;
import com.apps.stocktrading.stock.dto.StockDTO;
import com.apps.stocktrading.stock.entity.QStockEntity;
import com.apps.stocktrading.stock.entity.StockEntity;
import com.apps.stocktrading.stock.web.request.StockFilterRequest;
import com.apps.stocktrading.stock.web.request.StockUpdateRequest;
import com.apps.stocktrading.stock.repository.StockRepository;
import com.apps.stocktrading.stock.repository.mapper.StockEntityMapper;
import com.apps.stocktrading.stock.service.StockService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.ComparableExpressionBase;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class StockServiceImpl implements StockService {
    private static final Logger logger = Logger.getLogger(StockServiceImpl.class.getName());
    private final StockRepository stockRepository;
    private final StockEntityMapper mapper;
    private final JPAQueryFactory jpaQueryFactory;
    private final QStockEntity qStockEntity = QStockEntity.stockEntity;

    public StockServiceImpl(StockRepository stockRepository, StockEntityMapper mapper, JPAQueryFactory jpaQueryFactory) {
        this.stockRepository = stockRepository;
        this.mapper = mapper;
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public StockDTO createStock(StockDTO stock) {
        try{
            validateStockCreation(stock);
        }
        catch (IllegalArgumentException err){
            logger.info(err.getMessage());
        }
        String stockCodeUpper = stock.getStockCode().toUpperCase();
        stock.setStockCode(stockCodeUpper);
        return mapper.toDto(stockRepository.saveAndFlush(mapper.toEntity(stock)));
    }

    @Override
    public StockDTO getStockByStockCode(String stockCode) {
        Optional<StockEntity> stockOptional = stockRepository.findByStockCode(stockCode);
        return mapper.toDto(stockOptional.get());
    }

    @Override
    public StockDTO updateStock(StockUpdateRequest request) {
        StockDTO result = new StockDTO();
        if(validateStockUpdate(request)){
            Optional<StockEntity> optionalStockEntity = stockRepository.findByStockCode(request.getStockCode());
            StockEntity stockEntity = optionalStockEntity.get();
            if(!request.getCompanyName().isEmpty()){
                stockEntity.setCompanyName(request.getCompanyName());
            }
            if(!request.getIndustry().isEmpty()){
                stockEntity.setIndustry(request.getIndustry());
            }
            if(!request.getSector().isEmpty()){
                stockEntity.setSector(request.getSector());
            }
            if(request.getCurrentPrice()!=0){
                stockEntity.setCurrentPrice(request.getCurrentPrice());
            }
            if(request.getMarketCap().longValue()!=0){
                stockEntity.setMarketCap(request.getMarketCap());
            }
            if(request.getStockDescription()!=null){
                stockEntity.setStockDescription(request.getStockDescription());
            }

            result = mapper.toDto(stockRepository.saveAndFlush(stockEntity));

        }

        return result;
    }

    @Override
    public void deleteStock(String stockCode) {
        Optional<StockEntity> stockOptional = stockRepository.findByStockCode(stockCode);
        if(stockOptional.isPresent()){
            StockEntity stockEntity = stockOptional.get();
            stockEntity.setDeletedDate(ZonedDateTime.now());
            stockEntity.setDeletedFlag(true);
            stockRepository.saveAndFlush(stockEntity);
        }
        else{
            throw new IllegalArgumentException("No matching stock.");
        }
    }

    @Override
    public List<StockDTO> filterStock(StockFilterRequest request) {
        JPAQuery<StockEntity> query = jpaQueryFactory
                .select(qStockEntity)
                .from(qStockEntity)
                .where(getFilterRequest(request))
                .orderBy(getSortCondition(request.getSort(),request.isSortAscFlag()));
        List<StockDTO> result = mapper.toDto(query.fetch());
        return result;
    }

    private boolean validateStockCreation(StockDTO stock) throws IllegalArgumentException{
        Optional<StockEntity> stockCodeOptional = stockRepository.findByStockCode(stock.getStockCode());
        if(stockCodeOptional.isPresent()){
            throw new IllegalArgumentException("Can't create, Stock already exists.!");
        }
        return true;
    }

    private boolean validateStockUpdate(StockUpdateRequest request) throws IllegalArgumentException{
        Optional<StockEntity> stockUsernameNameOptional = stockRepository.findByStockCode(request.getStockCode());
        if(!stockUsernameNameOptional.isPresent()){
            throw new IllegalArgumentException("Stock not found!");
        }
        return true;
    }

    private BooleanBuilder getFilterRequest(StockFilterRequest request){
        BooleanBuilder builder = new BooleanBuilder();
        if(Objects.nonNull(request.getStockCode())){
            builder.and(qStockEntity.stockCode.containsIgnoreCase(request.getStockCode()));
        }
        if(Objects.nonNull(request.getCompanyName())){
            builder.and(qStockEntity.companyName.containsIgnoreCase(request.getCompanyName()));
        }
        if(Objects.nonNull(request.getCurrentStartPrice())&&Objects.nonNull(request.getCurrentEndPrice())){
            builder.and(qStockEntity.currentPrice.between(request.getCurrentStartPrice(),request.getCurrentEndPrice()));
        }
        if(Objects.nonNull(request.getPreviousStartPrice())&&Objects.nonNull(request.getPreviousEndPrice())){
            builder.and(qStockEntity.previousPrice.between(request.getPreviousStartPrice(),request.getPreviousEndPrice()));
        }
        if(Objects.nonNull(request.getMarketStartCap())&&Objects.nonNull(request.getMarketEndCap())){
            builder.and(qStockEntity.marketCap.between(request.getMarketStartCap(),request.getMarketEndCap()));
        }
        if(Objects.nonNull(request.getSector())){
            builder.and(qStockEntity.sector.in(request.getSector()));
        }
        if(Objects.nonNull(request.getIndustry())){
            builder.and(qStockEntity.industry.containsIgnoreCase(request.getIndustry()));
        }
        return builder;

    }

    private OrderSpecifier<?> getSortCondition(String sort, Boolean sortAscFlag){
        Boolean sortAscendingFlag = Objects.nonNull(sortAscFlag) ? sortAscFlag : false;
        if(Objects.isNull(sort)){
            sort = "empty";
        }
        switch (sort){
            case "stockCode":
                return discernSortOrder(qStockEntity.stockCode, sortAscendingFlag);
            case "companyName":
                return discernSortOrder(qStockEntity.companyName, sortAscendingFlag);
            case "currentPrice":
                return discernSortOrder(qStockEntity.currentPrice, sortAscendingFlag);
            case "previousPrice":
                return discernSortOrder(qStockEntity.previousPrice, sortAscendingFlag);
            case "marketCap":
                return discernSortOrder(qStockEntity.marketCap, sortAscendingFlag);
            case "sector":
                return discernSortOrder(qStockEntity.sector, sortAscendingFlag);
            case "industry":
                return discernSortOrder(qStockEntity.industry, sortAscendingFlag);
            case "stockDescription":
                return discernSortOrder(qStockEntity.stockDescription, sortAscendingFlag);
            default:
                return discernSortOrder(qStockEntity.companyName, sortAscendingFlag);
        }
    }

    private OrderSpecifier discernSortOrder(ComparableExpressionBase path, Boolean sortAscendingFlag) {
        if(sortAscendingFlag){
            return path.asc();
        }else{
            return path.desc();
        }
    }
}
