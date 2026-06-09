// Защитный прокси: пропускает чтение только для роли "admin".
class ProtectedDocument implements Document {
    // TODO: поля RealDocument real и String userRole + конструктор
    //       ProtectedDocument(RealDocument real, String userRole)

    @Override
    public String read() {
        // TODO: если userRole == "admin" → real.read(); иначе → "доступ запрещён"
        return "";
    }
}
