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
    @GeneratedValue
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
    private Date work_start;

    /** staff member education */
    @Column(name = "education")
    private String education;
    
    /** Default constructor */
    public StaffMember() {
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
    public void setWork_start(Date work_start) {
        this.work_start = work_start;
    }

    /** Set staff member education
     * @param education - education to be set
     */
    private void setEducation(String education) {
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
    public Date getWork_start() {
        return work_start;
    }

    /** Get staff member education
     * @return Returns staff member education
     */
    public String getEducation() {
        return education;
    }
}
