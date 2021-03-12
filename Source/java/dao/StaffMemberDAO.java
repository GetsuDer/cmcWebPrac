package DAO;

import logic.StaffMember;
import logic.Department;
import logic.Position;

import java.util.Collection;
import java.sql.SQLException;

public interface StaffMemberDAO {
    public void addStaffMember(StaffMember member) throws SQLException;
    public void updateStaffMember(Long member_id, StaffMember member) throws SQLException;
    public StaffMember getStaffMemberById(Long member_id) throws SQLException;
    public Collection getAllStaffMembers() throws SQLException;
    public void deleteStaffMember(StaffMember member) throws SQLException;
    public Collection getStaffMembersByDepartment(Department department) throws SQLException;
    public Collection getStaffMembersByPosition(Position position) throws SQLException;
}
