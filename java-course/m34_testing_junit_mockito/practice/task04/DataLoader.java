package m34_testing_junit_mockito.practice.task04;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DataLoader {

    private final RemoteApi api;

    DataLoader(RemoteApi api) {
        this.api = api;
    }
    String loadSafe() {
        try {
            return api.fetch();
        } catch (RuntimeException e) {
            return "";
        }
    }
}
