package m52_hibernate_relationships.practice.task04;

import jakarta.persistence.*;

// ─── Сущности ────────────────────────────────────────────────────────────────

// ===== ENTITY AUTHOR =====
@Entity
@Table(name = "authors4")
class Author4 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", unique = true)
    private UserProfile4 profile;

    public Author4() {}

    public Author4(String name) {
        this.name = name;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public UserProfile4 getProfile() { return profile; }

    // ✅ ПРОСТОЙ СЕТТЕР - БЕЗ СИНХРОНИЗАЦИИ
    public void setProfile(UserProfile4 profile) {
        this.profile = profile;
    }
}