package codejava.Services.impl;

import codejava.Entity.TypeOfProduct;
import codejava.Entity.UnitType;
import codejava.Responsitory.UnitTypeRepository;
import codejava.Services.UnitTypeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnitTypeServicesImpl implements UnitTypeServices {
    @Autowired
    UnitTypeRepository repo;

    @Override
    public List<UnitType> findAll(){
        return repo.findAll();
    }
    @Override
    public UnitType findById(Integer id){
        Optional<UnitType> result = repo.findById(id);
        return result.isPresent() ? result.get() : null;
    }
}