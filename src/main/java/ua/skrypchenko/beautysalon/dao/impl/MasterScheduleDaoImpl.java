package ua.skrypchenko.beautysalon.dao.impl;

import ua.skrypchenko.beautysalon.config.PostgresConfig;
import ua.skrypchenko.beautysalon.dao.MasterScheduleDao;
import ua.skrypchenko.beautysalon.dto.MastersScheduleDto;
import ua.skrypchenko.beautysalon.entity.Comment;
import ua.skrypchenko.beautysalon.entity.MasterSchedule;
import ua.skrypchenko.beautysalon.entity.User;
import ua.skrypchenko.beautysalon.exeption.DBConnectionException;
import ua.skrypchenko.beautysalon.exeption.UserNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MasterScheduleDaoImpl implements MasterScheduleDao {

    private final String SQL_GET_SCHEDULE_FOR_CLIENT = "SELECT users.username, master_schedule.work_day, master_schedule.start_work_day, master_schedule.end_work_day  from procedures_masters, users, procedures, master_schedule WHERE procedures_masters.master_id = users.user_id AND procedures_masters.procedure_id = procedures.procedure_id AND master_schedule.master_id = users.user_id AND procedures.name = ?";
    private final String SQL_GET_SCHEDULE_FOR_MASTER = "SELECT work_day, start_work_day, end_work_day FROM master_schedule join users u on u.user_id = master_schedule.master_id WHERE username = ?";
    private final String SQL_GET_SCHEDULE_Masters = "SELECT start_work_day, end_work_day from master_schedule left join users u on u.user_id = master_schedule.master_id WHERE work_day = ? AND username = ?";
    private final String SQL_GET_BUSY_SLOTS_WITH_DURATION = "SELECT start_hour, duration_hours from reservations, procedures, users WHERE procedures.procedure_id = reservations.procedure_id AND reservations.beauty_master_user_id = users.user_id AND username = ? AND data = ?";
    private final String SQL_GET_BUSY_SLOTS = "SELECT start_hour from reservations left join users u on u.user_id = reservations.beauty_master_user_id WHERE username = ? AND data = ?;";
    private final String SQL_SET_SCHEDULE = "INSERT INTO master_schedule(work_day, start_work_day, end_work_day, master_id) VALUES (?, ?, ?, (SELECT user_id FROM users WHERE username = ?))";

    private final  PostgresConfig postgresConfig = new PostgresConfig();

    @Override
    public List<MastersScheduleDto> getScheduleForClient(String procedureName) {
        List<MastersScheduleDto> masterSchedules = new ArrayList<>();

        try (Connection connection = postgresConfig.getСonnection();
             PreparedStatement ps = connection.prepareStatement(SQL_GET_SCHEDULE_FOR_CLIENT)) {

            ps.setString(1, procedureName);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                masterSchedules.add(new MastersScheduleDto(rs.getDate("work_day"), rs.getTime("start_work_day"), rs.getTime("end_work_day"), new User(rs.getString("username"))));

            }
        } catch (SQLException e) {
            throw new DBConnectionException(e);
        }

        return masterSchedules;
    }

    public void setMasterSchedule(MasterSchedule masterSchedule) {
        try (Connection connection = postgresConfig.getСonnection();
             PreparedStatement ps = connection.prepareStatement(SQL_SET_SCHEDULE)) {

                ps.setDate(1, (Date) masterSchedule.getWorkDay());
                ps.setTime(2, masterSchedule.getStartWorkDay());
                ps.setTime(3, masterSchedule.getEndWorkDay());
                ps.setString(4, masterSchedule.getMasterId().getUsername());
                ps.executeUpdate();

        } catch (SQLException e) {
            throw new DBConnectionException(e);
        }
    }

    @Override
    public List<String> getScheduleForFreeTimeSlots(String masterName, Date date) {
        List<String> schedule = new ArrayList<>();
        try (Connection connection = postgresConfig.getСonnection();
             PreparedStatement ps = connection.prepareStatement(SQL_GET_SCHEDULE_Masters)) {

            ps.setDate(1, date);
            ps.setString(2, masterName);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                schedule.add(rs.getString("start_work_day"));
                schedule.add(rs.getString("end_work_day"));
            }

        } catch (SQLException e) {
            throw new DBConnectionException(e);
        }
        return schedule;
    }

    @Override
    public List<String> getBusyTimeSlotsWithDuration(String masterName, Date date) {
        List<String> busySlots = new ArrayList<>();
        try (Connection connection = postgresConfig.getСonnection();
             PreparedStatement ps = connection.prepareStatement(SQL_GET_BUSY_SLOTS_WITH_DURATION)) {

            ps.setString(1, masterName);
            ps.setDate(2, date);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                busySlots.add(rs.getString("start_hour"));
                busySlots.add(rs.getString("duration_hours"));
            }

        } catch (SQLException e) {
            throw new DBConnectionException(e);
        }
        return busySlots;
    }

    @Override
    public List<String> getBusyTimeSlots(String masterName, Date date) {
        List<String> busySlots = new ArrayList<>();
        try (Connection connection = postgresConfig.getСonnection();
             PreparedStatement ps = connection.prepareStatement(SQL_GET_BUSY_SLOTS)) {

            ps.setString(1, masterName);
            ps.setDate(2, date);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                busySlots.add(rs.getString("start_hour"));
            }

        } catch (SQLException e) {
            throw new DBConnectionException(e);
        }
        return busySlots;
    }

    @Override
    public List<MastersScheduleDto> getScheduleForMaster(String masterName) {
        List<MastersScheduleDto> masterSchedules = new ArrayList<>();

        try (Connection connection = postgresConfig.getСonnection();
             PreparedStatement ps = connection.prepareStatement(SQL_GET_SCHEDULE_FOR_MASTER)) {

            ps.setString(1, masterName);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                masterSchedules.add(new MastersScheduleDto(rs.getDate("work_day"), rs.getTime("start_work_day"), rs.getTime("end_work_day")));
            }
        } catch (SQLException e) {
            throw new DBConnectionException(e);
        }
        return masterSchedules;
    }
}
