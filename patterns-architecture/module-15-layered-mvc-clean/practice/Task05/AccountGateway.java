// Port (внутренний слой): что нужно use case'у от внешнего мира.
// Принадлежит домену; реализуется внешним адаптером.
interface AccountGateway {
    Account load(String id);
}
