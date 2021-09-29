package codejava.Services.impl;

import codejava.Entity.UnitType;
import codejava.Responsitory.UnitTypeRepository;
import codejava.Services.UnitTypeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitTypeServicesImpl implements UnitTypeServices {
    @Autowired
    UnitTypeRepository repo;

    @Override
    public List<UnitType> findAll(){
        return repo.findAll();
    }
}