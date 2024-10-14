package deprecated.dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDAO<T> implements DAO<T> {
    protected DataSource dataSource;
    private String tableName;
    private Class<T> entityClass;

    public AbstractDAO(DataSource dataSource, String tableName, Class<T> entityClass) {
        this.dataSource = dataSource;
        this.tableName = tableName;
        this.entityClass = entityClass;
    }

    @Override
    public void save(T entity) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = createInsertStatement(entity)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error saving " + entity.getClass().getSimpleName(), e);
        }
    }

    @Override
    public Optional<T> findById(int id) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = createSelectByIdStatement(id)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(createEntity(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching " + entityClass.getSimpleName() + " by id", e);
        }
        return Optional.empty();
    }

    @Override
    public List<T> findAll() {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = createSelectAllStatement()) {
            ResultSet rs = stmt.executeQuery();
            List<T> entities = new ArrayList<>();
            while (rs.next()) {
                entities.add(createEntity(rs));
            }
            return entities;
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching all " + entityClass.getSimpleName(), e);
        }
    }

    @Override
    public void update(T entity) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = createUpdateStatement(entity)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating " + entity.getClass().getSimpleName(), e);
        }
    }

    @Override
    public void delete(int id) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = createDeleteByIdStatement(id)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting " + entityClass.getSimpleName());
        }
    }

    protected abstract PreparedStatement createInsertStatement(T entity) throws SQLException;
    protected abstract PreparedStatement createSelectByIdStatement(int id) throws SQLException;
    protected abstract PreparedStatement createSelectAllStatement() throws SQLException;
    protected abstract PreparedStatement createUpdateStatement(T entity) throws SQLException;
    protected abstract PreparedStatement createDeleteByIdStatement(int id) throws SQLException;
    protected abstract T createEntity(ResultSet rs) throws SQLException;
}

