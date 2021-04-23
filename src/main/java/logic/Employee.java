package logic;

import javax.persistence.*;
import java.util.Date;
/**
 * Entity for table 'Employees'
 *
 */
@Entity
@Table(name = "public.Employees")
public class Employee {

    /** Employee id */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long id;

    /** Position id */
    @ManyToOne
    @JoinColumn(name = "position")
    private Position position;

    /** Staff member id */
    @ManyToOne
    @JoinColumn(name = "staff_member")
    private StaffMember staffMember;

    /** Work start time */
    @Column(name = "start_time")
    private Date startTime;

    /** Work end time (may be NULL?) */
    @Column(name = "end_time")
    private Date endTime;

    /** Default constructor */
    public Employee() {
    }

    /** Constructor */
    public Employee(Position position, StaffMember staffMember, Date startTime, Date endTime) {
        this.position = position;
        this.staffMember = staffMember;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /** Setting employee id
     * @param id - Employee id to be set
     */
    public void setId(Long id) {
        this.id = id;
    }
    /** Setting position id 
     *  @param position - position id to be set
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /** Setting staff member id
     * @param staff_member - staff member id to be set
     */
    public void setStaffMember(StaffMember staffMember) {
        this.staffMember = staffMember;
    }

    /** Setting work start time
     * @param start_time - start time to be set
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /** Setting work end time
     * @param end_time - end time to be set
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /** Get employee id
     * @return Returns employee id
     */
    public Long getId() {
        return id;
    }

    /** Get position id
     * @return Returns position id
     */
    public Position getPosition() {
        return position;
    }

    /** Get staff member id
     * @return Returns staff member id
     */
    public StaffMember getStaffMember() {
        return staffMember;
    }

    /** Get work start time
     * @return Returns work start time
     */
    public Date getStartTime() {
        return startTime;
    }

    /** Get work end time
     * @return Returns work end time
     */
    public Date getEndTime() {
        return endTime;
    }
    
    /**
     * toString method
     * @return Returns text repersentation
     */
    @Override
    public String toString() {
        return "Id: " + id + "\n" +
                "Position: " + ((position == null) ? "NONE" : position.getId()) + "\n" +
                "Member: " + ((staffMember == null) ? "NONE" : staffMember.getId()) + "\n" +
                "StartTime: " + startTime + "\n" + 
                "EndTime: " + endTime + "\n";
    }

    /**
     * Equals method
     * @param obj Another object
     * @return Returns true, if objects are equal
     */
    public boolean my_equals(Employee emp) {
        boolean st_EQ = (emp.getStartTime() == null && this.getStartTime() == null) ||
            (emp.getStartTime() != null && emp.getEndTime() != null && 
             Math.abs(emp.getStartTime().getTime() - this.getStartTime().getTime()) < 60 * 60 * 24);
        boolean et_EQ = (emp.getEndTime() == null && this.getStartTime() == null) ||
            (emp.getEndTime() != null && this.getEndTime() != null && 
             Math.abs(emp.getStartTime().getTime() - this.getStartTime().getTime()) < 60 * 60 * 24);
        boolean posEQ = (emp.getPosition() == null && this.getPosition() == null) ||
            (emp.getPosition() != null && this.getPosition() != null && 
             emp.getPosition().getId() == this.getPosition().getId());
        boolean memEQ = (emp.getStaffMember() == null && this.getStaffMember() == null) ||
            (emp.getStaffMember() != null && this.getStaffMember() != null && 
             emp.getStaffMember().getId() == this.getStaffMember().getId());

        if (emp.getId() == this.getId() && posEQ && memEQ && st_EQ && et_EQ) {
            return true;
        }
        return false;
    }

}
