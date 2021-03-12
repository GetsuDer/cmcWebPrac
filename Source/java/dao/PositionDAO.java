package DAO;

import logic.Position;
import logic.StaffMember;
import logic.Department;

import java.util.Collection;
import java.sql.SQLException;

public interface PositionDAO {
    public void addPosition(Position position) throws SQLException;
    public void updatePosition(Long position_id, Position position) throws SQLException;
    public Position getPositionById(Long position_id) throws SQLException;
    public Collection getAllPositions() throws SQLException;
    public void deletePosition(Position position) throws SQLException;
    public Collection getPositionsByDepartment(Department department) throws SQLException;
    public Collection getPositionsByStaffMember(StaffMember member) throws SQLException;
}
