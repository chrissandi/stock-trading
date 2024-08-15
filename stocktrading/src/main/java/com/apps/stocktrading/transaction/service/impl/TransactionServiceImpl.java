package com.apps.stocktrading.transaction.service.impl;

import com.apps.stocktrading.profile.dto.ProfileDTO;
import com.apps.stocktrading.profile.service.ProfileService;
import com.apps.stocktrading.stock.dto.StockDTO;
import com.apps.stocktrading.stock.service.StockService;
import com.apps.stocktrading.transaction.dto.ProfileHoldingDTO;
import com.apps.stocktrading.transaction.dto.SimpleTransactionDTO;
import com.apps.stocktrading.transaction.dto.TransactionDTO;
import com.apps.stocktrading.transaction.entity.ProfileHoldingEntity;
import com.apps.stocktrading.transaction.entity.QTransactionEntity;
import com.apps.stocktrading.transaction.entity.TransactionEntity;
import com.apps.stocktrading.transaction.repository.ProfileHoldingRepository;
import com.apps.stocktrading.transaction.repository.TransactionRepository;
import com.apps.stocktrading.transaction.repository.mapper.ProfileHoldingEntityMapper;
import com.apps.stocktrading.transaction.repository.mapper.TransactionEntityMapper;
import com.apps.stocktrading.transaction.service.TransactionService;
import com.apps.stocktrading.transaction.web.request.TransactionFilterRequest;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.ComparableExpressionBase;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@Service
public class TransactionServiceImpl implements TransactionService {
    private static final Logger logger = Logger.getLogger(TransactionServiceImpl.class.getName());
    private final TransactionRepository transactionRepository;
    private final ProfileHoldingRepository profileHoldingRepository;
    private final TransactionEntityMapper transactionEntityMapper;
    private final ProfileHoldingEntityMapper profileHoldingEntityMapper;
    private final StockService stockService;
    private final ProfileService profileService;
    private final JPAQueryFactory jpaQueryFactory;
    private final QTransactionEntity qTransactionEntity = QTransactionEntity.transactionEntity;

    public TransactionServiceImpl(TransactionRepository transactionRepository, ProfileHoldingRepository profileHoldingRepository,
                                  TransactionEntityMapper transactionEntityMapper, ProfileHoldingEntityMapper profileHoldingEntityMapper,
                                  StockService stockService, ProfileService profileService, JPAQueryFactory jpaQueryFactory) {
        this.transactionRepository = transactionRepository;
        this.profileHoldingRepository = profileHoldingRepository;
        this.transactionEntityMapper = transactionEntityMapper;
        this.profileHoldingEntityMapper = profileHoldingEntityMapper;
        this.stockService = stockService;
        this.profileService = profileService;
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public TransactionDTO createTransaction(TransactionDTO transactionDTO) throws IllegalArgumentException{
        TransactionDTO result = new TransactionDTO();
        ProfileDTO profile = profileService.getProfileByUsername(transactionDTO.getUsername());
        StockDTO stock = stockService.getStockByStockCode(transactionDTO.getStockCode());
        BigDecimal totalTransaction = transactionDTO.getQuantity().multiply(BigDecimal.valueOf(transactionDTO.getPrice()));
        try{
            if(validateTransaction(profile, stock, totalTransaction.longValue())){
                ProfileHoldingEntity profileHoldingEntity = profileHoldingRepository.findByProfileIdAndStockId(profile.getUsername(),stock.getStockCode()).get();
                ProfileHoldingDTO profileHoldingDTO = profileHoldingEntityMapper.toDto(profileHoldingEntity);
                if(Objects.nonNull(profileHoldingEntity)){
                    SimpleTransactionDTO simpleTransactionDTO = profileHoldingEntityMapper.toSimpleTransactionDto(transactionDTO);
                    profileHoldingDTO.getSimpleTransactionList().add(simpleTransactionDTO);
                    double newAveragePurchasePrice = (profileHoldingDTO.getAveragePurchasePrice()+transactionDTO.getPrice()+transactionDTO.getPrice())/2;
                    profileHoldingDTO.setAveragePurchasePrice(newAveragePurchasePrice);
                    BigDecimal newTotalInvestmentValue = profileHoldingDTO.getTotalInvestmentValue().add(transactionDTO.getTotalTransactionValue());
                    profileHoldingDTO.setTotalInvestmentValue(newTotalInvestmentValue);
                    BigDecimal newTotalQuantity = profileHoldingDTO.getTotalQuantity().add(transactionDTO.getQuantity());
                    profileHoldingDTO.setTotalQuantity(newTotalQuantity);
                }
                else{
                    profileHoldingEntity = new ProfileHoldingEntity();
                    profileHoldingEntity.setCreatedDate(ZonedDateTime.now());
                    profileHoldingEntity.setDeletedFlag(false);
                    profileHoldingEntity.setUsername(transactionDTO.getUsername());
                    profileHoldingEntity.setStockCode(transactionDTO.getStockCode());
                    profileHoldingEntity.setTotalInvestmentValue(totalTransaction);
                    profileHoldingEntity.setAveragePurchasePrice(transactionDTO.getPrice());
                    profileHoldingEntity.setTotalQuantity(transactionDTO.getQuantity());

                }
                profileHoldingRepository.saveAndFlush(profileHoldingEntityMapper.toEntity(profileHoldingDTO));
                transactionDTO.setTotalTransactionValue(totalTransaction);
                result = transactionEntityMapper.toDto(transactionRepository.saveAndFlush(transactionEntityMapper.toEntity(transactionDTO)));
            }
        }
        catch (IllegalArgumentException err){
            logger.info(err.getMessage());
        }
        return result;
    }

    @Override
    public List<TransactionDTO> filterTransaction(TransactionFilterRequest request) {
        JPAQuery<TransactionEntity> query = jpaQueryFactory
                .select(qTransactionEntity)
                .from(qTransactionEntity)
                .where(getFilterRequest(request))
                .orderBy(getSortCondition(request.getSort(),request.isSortAscFlag()));
        List<TransactionDTO> result = transactionEntityMapper.toDto(query.fetch());
        return result;
    }

    private boolean validateTransaction(ProfileDTO profile, StockDTO stock, long totalTransaction) throws IllegalArgumentException{
        if(Objects.nonNull(profile)&&Objects.nonNull(stock)){
            long profileBalance = profile.getAccountBalance();
            if(profileBalance<totalTransaction){
                throw new IllegalArgumentException("Insufficient balance. Your current balance is not enough to perform this operation.");
            }
            return true;
        }
        else{
            if(Objects.isNull(profile)){
                throw new IllegalArgumentException("Profile not found!");
            }
            else{
                throw new IllegalArgumentException("Stock not found!");
            }
        }
    }

    private BooleanBuilder getFilterRequest(TransactionFilterRequest request){
        BooleanBuilder builder = new BooleanBuilder();
        if(Objects.nonNull(request.getUsername())){
            builder.and(qTransactionEntity.username.containsIgnoreCase(request.getUsername()));
        }
        if(Objects.nonNull(request.getStockCode())){
            builder.and(qTransactionEntity.stockCode.containsIgnoreCase(request.getStockCode()));
        }
        if(Objects.nonNull(request.getOrderNumber())){
            builder.and(qTransactionEntity.orderNumber.containsIgnoreCase(request.getOrderNumber()));
        }
        if(Objects.nonNull(request.getOrderVerb())){
            builder.and(qTransactionEntity.orderVerb.containsIgnoreCase(request.getOrderVerb()));
        }
        if(Objects.nonNull(request.getStartQuantity())&&Objects.nonNull(request.getEndQuantity())){
            builder.and(qTransactionEntity.quantity.between(request.getStartQuantity(),request.getEndQuantity()));
        }
        if(Objects.nonNull(request.getStartPrice())&&Objects.nonNull(request.getEndPrice())){
            builder.and(qTransactionEntity.price.between(request.getStartPrice(),request.getEndPrice()));
        }
        if(Objects.nonNull(request.getStartTotalTransactionValue())&&Objects.nonNull(request.getEndTotalTransactionValue())){
            builder.and(qTransactionEntity.totalTransactionValue.between(request.getStartTotalTransactionValue(),request.getEndTotalTransactionValue()));
        }
        if(Objects.nonNull(request.getCreatedStartDate())&&Objects.nonNull(request.getCreatedEndDate())){
            builder.and(qTransactionEntity.price.between(request.getStartPrice(),request.getEndPrice()));
        }
        return builder;

    }

    private OrderSpecifier<?> getSortCondition(String sort, Boolean sortAscFlag){
        Boolean sortAscendingFlag = Objects.nonNull(sortAscFlag) ? sortAscFlag : false;
        if(Objects.isNull(sort)){
            sort = "empty";
        }
        switch (sort){
            case "username":
                return discernSortOrder(qTransactionEntity.username, sortAscendingFlag);
            case "stockCode":
                return discernSortOrder(qTransactionEntity.stockCode, sortAscendingFlag);
            case "orderNumber":
                return discernSortOrder(qTransactionEntity.orderNumber, sortAscendingFlag);
            case "orderVerb":
                return discernSortOrder(qTransactionEntity.orderVerb, sortAscendingFlag);
            case "quantity":
                return discernSortOrder(qTransactionEntity.quantity, sortAscendingFlag);
            case "price":
                return discernSortOrder(qTransactionEntity.price, sortAscendingFlag);
            case "totalTransactionValue":
                return discernSortOrder(qTransactionEntity.totalTransactionValue, sortAscendingFlag);
            case "createdDate":
                return discernSortOrder(qTransactionEntity.createdDate, sortAscendingFlag);
            default:
                return discernSortOrder(qTransactionEntity.createdDate, sortAscendingFlag);
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
