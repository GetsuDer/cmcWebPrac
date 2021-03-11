package logic;

import java.util.Date;
/**
 * Entity for table 'Employees'
 *
 */
public class Employee {
    /** Position id */
    private Long position;

    /** Staff member id */
    private Long staff_member;

    /** Work start time */
    private Date start_time;

    /** Work end time (may be NULL?) */
    private Date end_time;

    /** Default constructor */
    public Employee() {
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
    public void setStaff_member(Long staff_member) {
        this.staff_member = staff_member;
    }

    /** Setting work start time
     * @param start_time - start time to be set
     */
    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    /** Setting work end time
     * @param end_time - end time to be set
     */
    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
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
    public Long getStaff_member() {
        return staff_member;
    }

    /** Get work start time
     * @return Returns work start time
     */
    public Date getStart_time() {
        return start_time;
    }

    /** Get work end time
     * @return Returns work end time
     */
    public Date getEnd_time() {
        return end_time;
    }
}
