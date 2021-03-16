package DAO;

import logic.Position;
import logic.StaffMember;
import logic.Department;

import java.util.Collection;
import java.sql.SQLException;

/**
 *  Interface for position class
 */
public interface PositionDAO {
    /** Add position into table
     * @param position Position to be added
     */
    public void addPosition(Position position) throws SQLException;
    
    /** Update position into table
     * @param position_id Old position id
     * @param position New Position
     */
    public void updatePosition(Long position_id, Position position) throws SQLException;
    
    /** Get position
     * @param position_id Position_id
     * @return Returns specified position
     */
    public Position getPositionById(Long position_id) throws SQLException;
    
    /** Get all positions
     * @return Returns all positions
     */
    public Collection getAllPositions() throws SQLException;
    
    /** Deletes position
     * @param position Position to be deleted
     */
    public void deletePosition(Position position) throws SQLException;
    
    /** Get positions from department
     * @param department Department from which to get positions
     * @return Returns collection of positions from department
     */
    public Collection getPositionsByDepartment(Department department) throws SQLException;
    
    /** Get positions taken by staff member
     * @param member Staff member to be work with
     * @return Returns collection of staff members positions
     */
    public Collection getPositionsByStaffMember(StaffMember member) throws SQLException;
}