package root.modules.binance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import root.modules.binance.entities.CurrenciesRate;
import root.modules.binance.services.BinanceAdapter;

import java.util.List;

@RequestMapping("binance_api")
@RestController
public class BinanceController {

    private BinanceAdapter binanceAdapter;

    @Autowired
    public BinanceController(BinanceAdapter binanceAdapter) {
        this.binanceAdapter = binanceAdapter;
    }

    @RequestMapping("/conversion/filter")
    @GetMapping
    public CurrenciesRate getRate(@RequestParam("pair") String filter) {
        return binanceAdapter.getRate(filter);
    }

    @RequestMapping("/conversion/all")
    @GetMapping
    public List<CurrenciesRate> getAllConversionPairs() {
        return binanceAdapter.getAllConversionPairs();
    }
}
