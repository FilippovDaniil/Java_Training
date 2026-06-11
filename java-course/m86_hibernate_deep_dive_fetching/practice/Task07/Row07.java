package m86_hibernate_deep_dive_fetching.practice.task07;

import jakarta.persistence.*;
import org.hibernate.Hibernate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

record Row07(String name, long count) {}
