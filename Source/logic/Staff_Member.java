package logic;

import java.util.Date;

public class Staff_Member {
    private Long id;
    private String name;
    private String address;
    private Date work_start;
    private String education;
    
    public Staff_Member() {
    }
   
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setWork_start(Date work_start) {
        this.work_start = work_start;
    }

    private String setEducation(String education) {
        this.education = education;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Date getWork_start() {
        return work_start;
    }

    public String getEducation() {
        return education;
    }
}
