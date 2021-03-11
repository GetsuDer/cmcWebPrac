package logic;

public class Department {
    private Long id;
    private String name;
    private Long head_department;
    private Long director;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHead_department(Long head_department) {
        this.head_department = head_department;
    }

    public void setDirector(Long director) {
        this.director = director;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getHead_department() {
        return head_department;
    }

    public Long getDirector(Long director) {
        return director;
    }

}
