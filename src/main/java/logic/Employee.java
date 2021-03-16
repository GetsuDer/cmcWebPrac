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
    @Column(name = "position")
    private Long position;

    /** Staff member id */
    @Column(name = "staff_member")
    private Long staffMember;

    /** Work start time */
    @Column(name = "start_time")
    private Date startTime;

    /** Work end time (may be NULL?) */
    @Column(name = "end_time")
    private Date endTime;

    /** Default constructor */
    public Employee() {
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
    public void setPosition(Long position) {
        this.position = position;
    }

    /** Setting staff member id
     * @param staff_member - staff member id to be set
     */
    public void setStaffMember(Long staffMember) {
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
    public Long getPosition() {
        return position;
    }

    /** Get staff member id
     * @return Returns staff member id
     */
    public Long getStaffMember() {
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
        return "Employee:\n" +
                "Id: " + id + "\n" +
                "Position: " + position + "\n" +
                "Member: " + staffMember + "\n" +
                "StartTime: " + startTime + "\n" + 
                "EndTime: " + endTime + "\n";
    }

}
