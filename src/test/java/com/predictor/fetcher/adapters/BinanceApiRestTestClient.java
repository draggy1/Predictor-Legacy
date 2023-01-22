package com.predictor.fetcher.adapters;

import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.account.*;
import com.binance.api.client.domain.account.request.*;
import com.binance.api.client.domain.general.Asset;
import com.binance.api.client.domain.general.ExchangeInfo;
import com.binance.api.client.domain.market.*;
import com.binance.api.client.exception.BinanceApiException;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

public class BinanceApiRestTestClient implements BinanceApiRestClient {
    @Override
    public void ping() {

    }

    @Override
    public Long getServerTime() {
        return LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
    }

    @Override
    public ExchangeInfo getExchangeInfo() {
        return new ExchangeInfo();
    }

    @Override
    public List<Asset> getAllAssets() {
        return List.of();
    }

    @Override
    public OrderBook getOrderBook(String symbol, Integer limit) {
        return new OrderBook();
    }

    @Override
    public List<TradeHistoryItem> getTrades(String symbol, Integer limit) {
        return List.of();
    }

    @Override
    public List<TradeHistoryItem> getHistoricalTrades(String symbol, Integer limit, Long fromId) {
        return List.of();
    }

    @Override
    public List<AggTrade> getAggTrades(String symbol, String fromId, Integer limit, Long startTime, Long endTime) {
        return List.of();
    }

    @Override
    public List<AggTrade> getAggTrades(String symbol) {
        return List.of();
    }

    @Override
    public List<Candlestick> getCandlestickBars(String symbol, CandlestickInterval interval, Integer limit, Long startTime, Long endTime) {
        return List.of();
    }

    @Override
    public List<Candlestick> getCandlestickBars(String symbol, CandlestickInterval interval) {
        return List.of();
    }

    @Override
    public TickerStatistics get24HrPriceStatistics(String symbol) {
        if(symbol.equals("None")) {
            throw new BinanceApiException();
        }
        TickerStatistics tickerStatistics = new TickerStatistics();
        tickerStatistics.setLastPrice("12345.99");
        tickerStatistics.setSymbol("BTCUSDT");
        return tickerStatistics;
    }

    @Override
    public List<TickerStatistics> getAll24HrPriceStatistics() {
        return List.of();
    }

    @Override
    public List<TickerPrice> getAllPrices() {
        return List.of();
    }

    @Override
    public TickerPrice getPrice(String symbol) {
        return new TickerPrice();
    }

    @Override
    public List<BookTicker> getBookTickers() {
        return List.of();
    }

    @Override
    public NewOrderResponse newOrder(NewOrder order) {
        return new NewOrderResponse();
    }

    @Override
    public void newOrderTest(NewOrder order) {

    }

    @Override
    public Order getOrderStatus(OrderStatusRequest orderStatusRequest) {
        return new Order();
    }

    @Override
    public CancelOrderResponse cancelOrder(CancelOrderRequest cancelOrderRequest) {
        return new CancelOrderResponse();
    }

    @Override
    public List<Order> getOpenOrders(OrderRequest orderRequest) {
        return List.of();
    }

    @Override
    public List<Order> getAllOrders(AllOrdersRequest orderRequest) {
        return List.of();
    }

    @Override
    public Account getAccount(Long recvWindow, Long timestamp) {
        return new Account();
    }

    @Override
    public Account getAccount() {
        return new Account();
    }

    @Override
    public List<Trade> getMyTrades(String symbol, Integer limit, Long fromId, Long recvWindow, Long timestamp) {
        return List.of();
    }

    @Override
    public List<Trade> getMyTrades(String symbol, Integer limit) {
        return List.of();
    }

    @Override
    public List<Trade> getMyTrades(String symbol) {
        return List.of();
    }

    @Override
    public WithdrawResult withdraw(String asset, String address, String amount, String name, String addressTag) {
        return new WithdrawResult();
    }

    @Override
    public DepositHistory getDepositHistory(String asset) {
        return new DepositHistory();
    }

    @Override
    public WithdrawHistory getWithdrawHistory(String asset) {
        return new WithdrawHistory();
    }

    @Override
    public DepositAddress getDepositAddress(String asset) {
        return new DepositAddress();
    }

    @Override
    public String startUserDataStream() {
        return "null";
    }

    @Override
    public void keepAliveUserDataStream(String listenKey) {

    }

    @Override
    public void closeUserDataStream(String listenKey) {

    }
}
