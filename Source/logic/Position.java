package logic;

public class Position {
    private Long id;
    private String name;
    private String responsibilities;
    private Long department;
    private Long size;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }

    public void setDepartment(Long department) {
        this.department = department;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public Long getDepartment() {
        return department;
    }

    public Long getSize() {
        return size;
    }
}
