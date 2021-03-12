package logic;

/**
 *  Entity for table 'Positions'
 */
@Entity
@Table(name = "public.Positions")
public class Position {
    /** position identificator */
    @Id
    @GeneratedValue
    @Column(name = "position_id")
    private Long id;
    
    /** position name */
    @Column(name = "position_name")
    private String name;

    /** position responsibilities */
    @Column(name = "responsibilities")
    private String responsibilities;

    /** position department id */
    @ManyToOne
    @JoinColumn(name = "department_id",
        foreignKey = @ForeignKey())
    @Column(name = "department")
    private Department department;

    /** position size */
    @Column(name = "size")
    private Long size;

    /** Default constructor */
    public Position() {
    }

    /** Set position identificator
     * @param id - identificator to be set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /** Set position name
     * @param name - name to be set
     */
    public void setName(String name) {
        this.name = name;
    }

    /** Set position responsibilities
     * @param responsibilities - responsibilities to be set
     */
    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }

    /** Set position department id
     * @param department - department id to be set
     */
    public void setDepartment(Department department) {
        this.department = department;
    }

    /** Set position size
     * @param size - size to be set
     */
    public void setSize(Long size) {
        this.size = size;
    }

    /** Get position id
     * @return Returns position id
     */
    public Long getId() {
        return id;
    }

    /** Get position name
     * @return Returns position name
     */
    public String getName() {
        return name;
    }

    /** Get position responsibilities
     * @return Returns position responsibilities
     */
    public String getResponsibilities() {
        return responsibilities;
    }

    /** Get department identificator
     * @return Returns department identificator
     */
    public Department getDepartment() {
        return department;
    }

    /** Get position size
     * @return Returns position size
     */
    public Long getSize() {
        return size;
    }
}
