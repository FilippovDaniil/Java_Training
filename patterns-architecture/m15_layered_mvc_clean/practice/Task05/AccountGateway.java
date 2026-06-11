package m15_layered_mvc_clean.practice.task05;

// Port (внутренний слой): что нужно use case'у от внешнего мира.
// Принадлежит домену; реализуется внешним адаптером.
interface AccountGateway {
    Account load(String id);
}
