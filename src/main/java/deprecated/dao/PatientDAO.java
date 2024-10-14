package deprecated.dao;

import models.Patient;
import javax.sql.DataSource;
import java.sql.*;

public class PatientDAO extends AbstractDAO<Patient> {
    public PatientDAO(DataSource dataSource) {
        super(dataSource, "Patient", Patient.class);
    }

    @Override
    protected PreparedStatement createInsertStatement(Patient entity) throws SQLException {
        String sql = "INSERT INTO Patient (name, age, address, phone) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = dataSource.getConnection().prepareStatement(sql);
        stmt.setString(1, entity.getName());
        stmt.setInt(2, entity.getAge());
        stmt.setString(3, entity.getAddress());
        stmt.setString(4, entity.getPhone());
        return stmt;
    }

    @Override
    protected PreparedStatement createSelectByIdStatement(int id) throws SQLException {
        String sql = "SELECT * FROM Patient WHERE patientId = ?";
        PreparedStatement stmt = dataSource.getConnection().prepareStatement(sql);
        stmt.setInt(1, id);
        return stmt;
    }

    @Override
    protected PreparedStatement createSelectAllStatement() throws SQLException {
        String sql = "SELECT * FROM Patient";
        return dataSource.getConnection().prepareStatement(sql);
    }

    @Override
    protected PreparedStatement createUpdateStatement(Patient entity) throws SQLException {
        String sql = "UPDATE Patient SET name = ?, age = ?, address = ?, phone = ? WHERE patientId = ?";
        PreparedStatement stmt = dataSource.getConnection().prepareStatement(sql);
        stmt.setString(1, entity.getName());
        stmt.setInt(2, entity.getAge());
        stmt.setString(3, entity.getAddress());
        stmt.setString(4, entity.getPhone());
        stmt.setInt(5, entity.getPatientId());
        return stmt;
    }

    @Override
    protected PreparedStatement createDeleteByIdStatement(int id) throws SQLException {
        String sql = "DELETE FROM Patient WHERE patientId = ?";
        PreparedStatement stmt = dataSource.getConnection().prepareStatement(sql);
        stmt.setInt(1, id);
        return stmt;
    }

    @Override
    protected Patient createEntity(ResultSet rs) throws SQLException {
        Patient patient = new Patient();
        patient.setPatientId(rs.getInt("patientId"));
        patient.setName(rs.getString("name"));
        patient.setAge(rs.getInt("age"));
        patient.setAddress(rs.getString("address"));
        patient.setPhone(rs.getString("phone"));
        return patient;
    }
}
