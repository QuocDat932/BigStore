package codejava.Services;

import codejava.Entity.UnitType;

import java.util.List;

public interface UnitTypeServices {

    List<UnitType> findAll();

    UnitType findById(Integer id);
}