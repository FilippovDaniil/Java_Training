package m07_adapter_decorator.practice.task05;

class RawChannel implements DataChannel {
    @Override
    public String pack(String data) {
        // TODO: вернуть данные как есть
        return data;
    }
}
