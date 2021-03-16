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
    @Column(name = "department_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /** Department name */
    @Column(name = "department_name")
    private String name;

    /** Head department id */
    @Column(name = "head_department")
    private Long headDepartment;

    /** Department director id */
    @Column(name = "director")
    private Long director;
    
    /** Default constructor */
    public Department() {
    }

    /** Constructor 
     * @param id Id
     * @param name Department name
     * @param head_department Department head department
     * @param director Department director id
     */
    public Department(Long id, String name, Long headDepartment, Long director) {
        this.id = id;
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
    public void setHeadDepartment(Long headDepartment) {
        this.headDepartment = headDepartment;
    }

    /** Setting director identificator
     * @param director - director identificator to be set
     */
    public void setDirector(Long director) {
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
    public Long getHeadDepartment() {
        return headDepartment;
    }
    
    /** Get director identificator
     * @return Returns director identificator
     */
    public Long getDirector() {
        return director;
    }

    /**
     * toString method 
     * @return Returns text repersentation
     */
    @Override
    public String toString() {
        return "Department:\n" +
                "Id: " + id +
                "\nName: " + name + "\n" +
                "Head department: " + headDepartment + "\n" +
                "Director: " + director + "\n";
    }

}
