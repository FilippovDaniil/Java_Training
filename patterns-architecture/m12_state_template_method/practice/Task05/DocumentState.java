package m12_state_template_method.practice.task05;

interface DocumentState {
    DocumentState submit();
    DocumentState approve();
    DocumentState reject();
    String name();
}
