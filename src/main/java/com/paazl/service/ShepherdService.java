package com.paazl.service;

import com.paazl.data.CurrentBalance;
import com.paazl.data.Sheep;
import com.paazl.data.State;
import com.paazl.data.repositories.CurrentBalanceRepository;
import com.paazl.data.repositories.SheepRepository;
import com.paazl.util.CurrentBalanceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Service
public class ShepherdService {

    @Value("${price_of_new_sheep}")
    private Integer priceOfSheep;

    @Autowired
    private SheepRepository sheepRepository;
    @Autowired
    private CurrentBalanceRepository currentBalanceRepository;

    public SheepStatussesDto getSheepStatusses() {
        List<Sheep> healthySheep = sheepRepository.findAllByState(State.HEALTHY);
        List<Sheep> deadSheep = sheepRepository.findAllByState(State.DEAD);

        return new SheepStatussesDto(
                healthySheep.size(),
                deadSheep.size()
        );
    }

    public BigInteger getBalance() {
        return currentBalanceRepository.findFirstByOrderByTimestampDesc().getBalance();
    }

    public String orderNewSheep(int nofSheepDesired) {
        CurrentBalance currentBalance = currentBalanceRepository.findFirstByOrderByTimestampDesc();
        currentBalanceRepository.save(CurrentBalanceUtils.subtractBalance(currentBalance, BigInteger.valueOf(priceOfSheep).multiply(BigInteger.valueOf(nofSheepDesired))));
        for (int i = 0; i < nofSheepDesired; i++) {
            Sheep s = new Sheep();
            sheepRepository.save(s);
        }
        // What to do, what to do...
        return String.format("In total %s sheep were ordered and added to your flock! Total costs: %s", nofSheepDesired, BigInteger.valueOf(priceOfSheep).multiply(BigInteger.valueOf(nofSheepDesired)));
    }
}
