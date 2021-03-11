package logic;

public class Employee {
    private Long position;
    private Long staff_member;
    private Date start_time;
    private Date end_time;

    public void setPosition(Long position) {
        this.position = position;
    }

    public void setStaff_member(Long staff_member) {
        this.staff_member = staff_member;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public Long getPosition() {
        return position;
    }

    public Long getStaff_member() {
        return staff_member;
    }

    public Date getStart_time() {
        return start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }
}
