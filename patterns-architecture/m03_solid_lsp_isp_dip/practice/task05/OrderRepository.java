package m03_solid_lsp_isp_dip.practice.task05;

import java.util.List;

// Абстракция принадлежит бизнес-слою. Детали (InMemory, БД) реализуют её.
interface OrderRepository {
    List<Long> findAllAmountsCents();
}
