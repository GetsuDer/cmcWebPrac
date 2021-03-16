package DAO;

import logic.StaffMember;
import logic.Department;
import logic.Position;

import java.util.Collection;
import java.sql.SQLException;

/** 
 * Interface for StaffMember class
 */
public interface StaffMemberDAO {
    /** Add staff member into table
     * @param member Member to add
     */
    public void addStaffMember(StaffMember member) throws SQLException;
    
    /** Update staff member into table
     * @param member_id Old member id
     * @param member New member
     */
    public void updateStaffMember(Long member_id, StaffMember member) throws SQLException;
    
    /** Get staff member
     * @param member_id Member id
     * @return Returns specified member
     */
    public StaffMember getStaffMemberById(Long member_id) throws SQLException;
    
    /** Get all existing members
     * @return Returns collection of all members
     */
    public Collection getAllStaffMembers() throws SQLException;
    
    /** Deletes staff member
     * @param member Member to be deleted
     */
    public void deleteStaffMember(StaffMember member) throws SQLException;
    
    /** Get members of current department
     * @param department Current department
     * @return Returns collection of members from department
     */
    public Collection getStaffMembersByDepartment(Department department) throws SQLException;
    
    /** Get members on current position
     * @param position Current position
     * @return Returns collection of members on current position
     */
    public Collection getStaffMembersByPosition(Position position) throws SQLException;
}
