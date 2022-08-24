package ua.skrypchenko.beautysalon.dto;

public class ProcedureDto {
    private String name;
    private String description;
    private int durationHours;

    public ProcedureDto(String name, String description, int durationHours) {
        this.name = name;
        this.description = description;
        this.durationHours = durationHours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDurationHours() {
        return durationHours;
    }

    public void setDurationHours(int durationHours) {
        this.durationHours = durationHours;
    }
}
