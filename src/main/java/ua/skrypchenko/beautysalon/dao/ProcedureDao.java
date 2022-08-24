package ua.skrypchenko.beautysalon.dao;

import ua.skrypchenko.beautysalon.entity.Procedure;

import java.util.List;

public interface ProcedureDao {
    List<Procedure> getAll();
    List<Procedure> getProcedureByNameOfMaster(String name);


}
