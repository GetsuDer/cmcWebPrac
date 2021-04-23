package logic;

import javax.persistence.*;
import java.util.Arrays;
/**
 *  Entity for table "Departments"
 *
 */
@Entity
@Table(name = "public.Departments")
public class Department {
    /** Department id */
    @Id
    @Column(name = "department_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /** Department name */
    @Column(name = "department_name")
    private String name;

    /** Head department id */
    @ManyToOne
    @JoinColumn(name = "head_department")
    private Department headDepartment;

    /** Department director id */
    @ManyToOne
    @JoinColumn(name = "director")
    private StaffMember director;
    
    /** Default constructor */
    public Department() {
    }

    /** Constructor 
     * @param id Id
     * @param name Department name
     * @param head_department Department head department
     * @param director Department director id
     */
    public Department(String name, Department headDepartment, StaffMember director) {
        this.name = name;
        this.headDepartment = headDepartment;
        this.director = director;
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
    public void setHeadDepartment(Department headDepartment) {
        this.headDepartment = headDepartment;
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
    public Department getHeadDepartment() {
        return headDepartment;
    }
    
    /** Get director identificator
     * @return Returns director identificator
     */
    public StaffMember getDirector() {
        return director;
    }

    /**
     * toString method 
     * @return Returns text repersentation
     */
    @Override
    public String toString() {
        return "Id: " + id +
                "\nName: " + ((name == null) ? "NONE" : name) + "\n" +
                "Head department: " + ((headDepartment == null) ? "NONE" : headDepartment.getId()) + "\n" +
                "Director: " + ((director == null) ? "NONE" : director.getId()) + "\n";
    }

    /**
     * Equals method
     * @param obj Another object
     * @return Returns true, if objects are equal
     */
    public boolean my_equals(Department dep) {
        boolean nameEQ = (dep.getName() == null && this.getName() == null) ||
           (dep.getName() != null && this.getName() != null && dep.getName().equals(this.getName()));
        boolean headEQ = (dep.getHeadDepartment() == null && this.getHeadDepartment() == null) ||
           (dep.getHeadDepartment() != null && this.getHeadDepartment() != null && dep.getHeadDepartment().getId() == this.getHeadDepartment().getId());
        boolean directorEQ = (dep.getDirector() == null && this.getDirector() == null) ||
            (dep.getDirector() != null && this.getDirector() != null && dep.getDirector().getId() == this.getDirector().getId());
        if ((dep.getId() == this.getId()) && nameEQ && headEQ && directorEQ) {
            return true;
        }
        return false;
    }
}
