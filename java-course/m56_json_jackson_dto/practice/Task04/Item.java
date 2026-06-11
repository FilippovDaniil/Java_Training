package m56_json_jackson_dto.practice.task04;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;

record Item(String productName, double price, int quantity) {}
