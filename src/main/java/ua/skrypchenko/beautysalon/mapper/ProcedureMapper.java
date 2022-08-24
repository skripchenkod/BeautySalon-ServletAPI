package ua.skrypchenko.beautysalon.mapper;

import ua.skrypchenko.beautysalon.dto.ProcedureDto;
import ua.skrypchenko.beautysalon.entity.Procedure;

import java.util.ArrayList;
import java.util.List;

public class ProcedureMapper {

    public List<ProcedureDto> entityToDtos(List<Procedure> procedures){
        List<ProcedureDto> procedureDtos = new ArrayList<>();

        for (Procedure procedure : procedures) {
            procedureDtos.add(new ProcedureDto(procedure.getName(), procedure.getDescription(), procedure.getDurationHours()));
        }
        return procedureDtos;
    }
}
