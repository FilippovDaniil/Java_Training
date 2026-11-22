package m125_kafka_testing.practice.task07;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "task_projection")
public class TaskProjection {
    @Id
    private Long id;
    private String title;
    private String status;

    public TaskProjection() {}
    public TaskProjection(Long id, String title, String status) {
        this.id = id; this.title = title; this.status = status;
    }
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
