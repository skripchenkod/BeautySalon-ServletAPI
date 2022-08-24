package ua.skrypchenko.beautysalon.dao.impl;

import ua.skrypchenko.beautysalon.config.PostgresConfig;
import ua.skrypchenko.beautysalon.dao.ProcedureDao;
import ua.skrypchenko.beautysalon.entity.Procedure;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProcedureDaoImpl implements ProcedureDao {

    private final String SQL_GET_ALL_PROCEDURES = "SELECT * FROM procedures";
    private final String SQL_GET_PROCEDURES_BY_MASTER_NAME = "SELECT procedures.procedure_id, procedures.name, procedures.description, procedures.duration_hours from procedures_masters, procedures, users WHERE procedures_masters.master_id = users.user_id AND procedures_masters.procedure_id = procedures.procedure_id AND users.username = ?";

    private final DataSource dataSource = PostgresConfig.getInstance();
    private Connection connection;

    @Override
    public List<Procedure> getAll() {
        List<Procedure> procedures= new ArrayList<>();
        try {
            this.connection = dataSource.getConnection();

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQL_GET_ALL_PROCEDURES);

            while (rs.next()){
                procedures.add(new Procedure(
                        rs.getInt("procedure_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("duration_hours")
                        ));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return procedures;
    }

    @Override
   public List<Procedure> getProcedureByNameOfMaster(String name) {
        List<Procedure> procedures = new ArrayList<>();

        try {
            this.connection = dataSource.getConnection();

            PreparedStatement ps = connection.prepareStatement(SQL_GET_PROCEDURES_BY_MASTER_NAME);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                procedures.add(new Procedure(
                        rs.getInt("procedure_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("duration_hours")
                ));

            }
        } catch (SQLException s) {
            s.printStackTrace();
        }
        return procedures;
    }

}
