class ErrorHandler extends Handler {
    @Override
    public void handle(int level, String msg) {
        // TODO: если level >= 2 → напечатать "ERROR: " + msg; иначе passToNext(level, msg)
    }
}
