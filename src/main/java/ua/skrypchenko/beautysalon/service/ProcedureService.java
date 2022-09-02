package ua.skrypchenko.beautysalon.service;

import ua.skrypchenko.beautysalon.dao.ProcedureDao;
import ua.skrypchenko.beautysalon.dao.impl.ProcedureDaoImpl;
import ua.skrypchenko.beautysalon.dto.ProcedureDto;
import ua.skrypchenko.beautysalon.mapper.ProcedureMapper;

import java.util.List;

public class ProcedureService {
    private final ProcedureDao procedureDao = new ProcedureDaoImpl();
    private final ProcedureMapper procedureMapper = new ProcedureMapper();

    public List<ProcedureDto> getAll(){
        return procedureMapper.entityToDtos(procedureDao.getAll());
    }

    public List<ProcedureDto> getProcedureByNameOfMaster(String name){
        return procedureMapper.entityToDtos(procedureDao.getProcedureByNameOfMaster(name));
    }
}
