interface DocumentState {
    DocumentState submit();
    DocumentState approve();
    DocumentState reject();
    String name();
}
