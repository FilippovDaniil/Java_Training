import jakarta.persistence.*;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import java.util.ArrayList;
import java.util.List;

record CatRow07(String name, long count) {}
