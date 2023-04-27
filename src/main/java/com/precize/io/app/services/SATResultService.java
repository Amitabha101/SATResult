package com.precize.io.app.services;

import com.precize.io.app.exceptions.InvalidAddressException;
import com.precize.io.app.dtos.InsertDataDTO;
import com.precize.io.app.enums.SATResultStatus;
import com.precize.io.app.exceptions.InvalidScoreException;
import com.precize.io.app.exceptions.NotFoundException;
import com.precize.io.app.models.Address;
import com.precize.io.app.models.SATResult;
import com.precize.io.app.repositories.AddressRepository;
import com.precize.io.app.repositories.SATResultRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SATResultService {

    private final SATResultRepository satResultRepository;
    private final AddressRepository addressRepository;
    private final int totalSatScore = 1600;
    private final double passingPercent = 30;
    public SATResultService(SATResultRepository satResultRepository, AddressRepository addressRepository) {
        this.satResultRepository = satResultRepository;
        this.addressRepository = addressRepository;
    }

    private SATResultStatus checkResultStatus(double score) {
        return score > (totalSatScore * passingPercent) / 100 ? SATResultStatus.PASS : SATResultStatus.FAIL;
    }
    void validateAddress(String country,String city,String pincode)
    {
        if(country.matches("^[a-zA-Z]+(?:(?:\\\\s+|-)[a-zA-Z]+)*$"))
        {
            if(city.matches("^[a-zA-Z]+(?:(?:\\\\s+|-)[a-zA-Z]+)*$"))
            {
                if(pincode.matches("^[1-9]{1}[0-9]{2}\\s{0,1}[0-9]{3}$"))
                {
                    return;
                }
                else {
                    throw new InvalidAddressException("wrong pincode passed");
                }
            }
            else{
                throw new InvalidAddressException("wrong city name passed");
            }
        }
        else{
            throw new InvalidAddressException("wrong country name passed");
        }
    }
    void validateScore(double score)
    {
        if(score<0||score>totalSatScore)
        {
             throw new InvalidScoreException("sat score not valid");
        }
    }
    public SATResult insertData(InsertDataDTO insertDataDTO) {

        validateAddress(insertDataDTO.getCountry(),insertDataDTO.getCity(),insertDataDTO.getPincode());
        Address address = Address.builder()
                .country(insertDataDTO.getCountry())
                .city(insertDataDTO.getCity())
                .pincode(insertDataDTO.getPincode())
                .build();
        addressRepository.save(address);

        validateScore(insertDataDTO.getSatScore());

        SATResult satResult = SATResult.builder()
                .name(insertDataDTO.getName())
                .satScore(insertDataDTO.getSatScore())
                .address(address)
                .satResultStatus(checkResultStatus(insertDataDTO.getSatScore()))
                .build();
        return satResultRepository.save(satResult);
    }

    public List<SATResult> getAllData() {
        return satResultRepository.findAll();
    }

    public int getRank(String name) {
        List<SATResult> results = satResultRepository.findAllByOrderBySatScoreDesc();
        for (int i = 0; i < results.size(); i++) {
            if (results.get(i).getName().equals(name)) {
                return i + 1;
            }
        }
        throw new NotFoundException("no data available for the given name");
    }

    public SATResult updateScore(String name, double score) {
        SATResult satResult = satResultRepository.findByName(name);
        if (satResult == null) {
            throw new NotFoundException("no data available for the given name");
        } else {
            validateScore(score);
            satResult.setSatScore(score);
            satResult.setSatResultStatus(checkResultStatus(score));
            return satResultRepository.save(satResult);
        }
    }

    public void deleteRecord(String name) {
        satResultRepository.deleteByName(name);
    }
}
