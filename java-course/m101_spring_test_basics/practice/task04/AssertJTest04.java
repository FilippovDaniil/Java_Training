package m101_spring_test_basics.practice.task04;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

// --- ТЕСТ (каркас) ---
class AssertJTest04 {

    @Test
    void title_chain() {
        // TODO: assertThat("Кофе").isNotNull().startsWith("Ко").hasSize(4);
    }

    @Test
    void list_contents() {
        List<String> list = List.of("a", "b", "c");
        // TODO: assertThat(list).hasSize(3).contains("b").containsExactly("a", "b", "c");
    }

    @Test
    void extracting_titles() {
        List<Task04> tasks = List.of(new Task04(1L, "Кофе"), new Task04(2L, "Чай"));
        // TODO: assertThat(tasks).extracting("title").containsExactly("Кофе", "Чай");
    }

    @Test
    void throws_assertj() {
        // TODO: assertThatThrownBy(() -> { throw new IllegalStateException("done"); })
        //              .isInstanceOf(IllegalStateException.class)
        //              .hasMessageContaining("done");
    }
}
