package logic;

import javax.persistence.*;

/**
 *  Entity for table "Departments"
 *
 */
@Entity
@Table(name = "public.Departments")
public class Department {
    /** Department id */
    @Id
    @GeneratedValue
    @Column(name = "department_id")
    private Long id;
    
    /** Department name */
    @Column(name = "department_name")
    private String name;

    /** Head department id */

    @Column(name = "head_department")
    private Department head_department;

    /** Department director id */
    @ManyToOne
    @JoinColumn(name = "member_id",
        foreignKey = @ForeignKey())
    @Column(name = "director")
    private StaffMember director;
    
    /** Default constructor */
    public Department() {
    }

    /** Setting department id
     * @param id - identificator to be set
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /** Setting department name
     * @param name - name to be set
     */
    public void setName(String name) {
        this.name = name;
    }

    /** Setting head department identificator
     *  @param head_department - department identificator to be set
     */
    public void setHead_department(Department head_department) {
        this.head_department = head_department;
    }

    /** Setting director identificator
     * @param director - director identificator to be set
     */
    public void setDirector(StaffMember director) {
        this.director = director;
    }

    /** Get department identificator
     * @return Returns department identificator
     */
    public Long getId() {
        return id;
    }

    /** Get department name
     * @return Returns department name
     */
    public String getName() {
        return name;
    }

    /** Get head department identificator
     * @return Returns head department identificator
     */
    public Department getHead_department() {
        return head_department;
    }
    
    /** Get director identificator
     * @return Returns director identificator
     */
    public StaffMember getDirector() {
        return director;
    }

}
