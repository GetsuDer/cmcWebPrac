package logic;

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
    @GeneratedValue
    @Column(name = "employee_id")
    private Long employee_id;

    /** Position id */
    @ManyToOne
    @JoinColumn(name = "position_id",
        foreignKey = @ForeignKey())
    @Column(name = "position")
    private Position position;

    /** Staff member id */
    @ManyToOne
    @JoinColumn(name = "member_id",
        foreignKey = @ForeignKey())
    @Column(name = "staff_member")
    private StaffMember staff_member;

    /** Work start time */
    @Column(name = "start_time")
    private Date start_time;

    /** Work end time (may be NULL?) */
    @Column(name = "end_time")
    private Date end_time;

    /** Default constructor */
    public Employee() {
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
    public void setStaffMember(StaffMember staff_member) {
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
    public Position getPosition() {
        return position;
    }

    /** Get staff member id
     * @return Returns staff member id
     */
    public StaffMember getStaffMember() {
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
