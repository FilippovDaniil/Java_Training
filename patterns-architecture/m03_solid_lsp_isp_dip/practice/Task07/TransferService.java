package m03_solid_lsp_isp_dip.practice.task07;

// Бизнес-логика перевода. Зависит только от абстракции AccountRepository (DIP).
class TransferService {

    // TODO: поле AccountRepository repository + конструктор
    //       TransferService(AccountRepository repository)

    public void transfer(String fromId, String toId, long amountCents) {
        // TODO: найти оба счёта; withdraw у отправителя (кинет исключение при
        //       нехватке), deposit получателю, save обоих.
        //       При неуспехе балансы не должны измениться.
    }
}
