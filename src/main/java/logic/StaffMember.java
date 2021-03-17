package logic;

import javax.persistence.*;
import java.util.Date;
/**
 *  Entity for table 'StaffMembers'
 */
@Entity
@Table(name = "public.StaffMembers")
public class StaffMember {
    /** staff member identificator */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    /** staff member name */
    @Column(name = "member_name")
    private String name;

    /** staff member address */
    @Column(name = "address")
    private String address;

    /** staff member word start date */
    @Column(name = "work_start")
    private Date workStart;

    /** staff member education */
    @Column(name = "education")
    private String education;
    
    /** Default constructor */
    public StaffMember() {
    }
    
    /** Constructor */
    public StaffMember(String name, String address, Date workStart, String education) {
        this.name = name;
        this.address = address;
        this.workStart = workStart;
        this.education = education;
    }

    /** Set staff member identificator
     * @param id - identificator to be set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /** Set staff member name
     * @param name - name to be set
     */
    public void setName(String name) {
        this.name = name;
    }

    /** Set staff member address
     * @param address - address to be set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /** Set staff member work start date
     * @param work_start - date to be set
     */
    public void setWorkStart(Date workStart) {
        this.workStart = workStart;
    }

    /** Set staff member education
     * @param education - education to be set
     */
    public void setEducation(String education) {
        this.education = education;
    }

    /** Get staff member identificator
     * @return Returns staff member identificator
     */
    public Long getId() {
        return id;
    }

    /** Get staff member name
     * @return Returns staff member name
     */
    public String getName() {
        return name;
    }

    /** Get staff member address
     * @return Returns staff member address
     */
    public String getAddress() {
        return address;
    }

    /** Get work start date
     * @return Returns staff member work start date 
     */
    public Date getWorkStart() {
        return workStart;
    }

    /** Get staff member education
     * @return Returns staff member education
     */
    public String getEducation() {
        return education;
    }

    /**
     * toString method
     * @return Returns text repersentation
     */
    @Override
    public String toString() {
        return "StaffMember:\n" +
                "Id: " + id + "\n" +
                "Name: " + name + "\n" +
                "Address: " + address + "\n" +
                "StartWork: " + workStart + "\n";
    }

    /**
     * Equals method
     * @param obj Another object
     * @return Returns true, if objects are equal
     */
    public boolean my_equals(StaffMember mem) {
        boolean nameEQ = (mem.getName() == null && this.getName() == null) ||
            (mem.getName() != null && this.getName() != null && mem.getName().equals(this.getName()));
        boolean addressEQ = (mem.getAddress() == null && this.getAddress() == null) ||
            (mem.getAddress() != null && this.getAddress() != null && mem.getAddress().equals(this.getAddress()));
        boolean workStartEQ = (mem.getWorkStart() == null && this.getWorkStart() == null) ||
        (mem.getWorkStart() != null && this.getWorkStart() != null && Math.abs(mem.getWorkStart().getTime() - this.getWorkStart().getTime()) < 60 * 60 * 24);
        if (mem.getId() == this.getId() && nameEQ && addressEQ && workStartEQ) {
            return true;
        }
        return false;
    }

}
