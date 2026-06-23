package m52_hibernate_relationships.practice.task04;

import jakarta.persistence.*;

// ===== ENTITY USER PROFILE =====
@Entity
@Table(name = "user_profiles4")
class UserProfile4 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000)
    private String bio;

    private String avatarUrl;

    @OneToOne(mappedBy = "profile", fetch = FetchType.LAZY)
    private Author4 author;

    public UserProfile4() {}

    public UserProfile4(String bio, String avatarUrl) {
        this.bio = bio;
        this.avatarUrl = avatarUrl;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public String getAvatarUrl() { return avatarUrl; }
    public void setAvatarUrl(String avatarUrl) { this.avatarUrl = avatarUrl; }

    public Author4 getAuthor() { return author; }

    // ✅ ПРОСТОЙ СЕТТЕР - БЕЗ СИНХРОНИЗАЦИИ
    public void setAuthor(Author4 author) {
        this.author = author;
    }
}