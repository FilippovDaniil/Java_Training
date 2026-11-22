package m125_kafka_testing.practice.task07;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tasks")
public class TaskEntity {
    @Id
    private Long id;
    private String title;
    private long authorId;
    private String status;
    // getters/setters/конструкторы


    public TaskEntity(Long id, String title, String status, long authorId) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.authorId = authorId;
    }

    public TaskEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}