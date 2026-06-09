import java.util.ArrayList;
import java.util.List;

class Team {
    // TODO: поля name (String) и members (List<String>)

    public Team(String name) {
        // TODO: задать name, пустой список members
    }

    // вспомогательный приватный конструктор, принимающий готовый список
    private Team(String name, List<String> members) {
        // TODO: задать поля как есть (без копирования) — используется обоими копировщиками
    }

    public void addMember(String member) {
        // TODO
    }

    public Team shallowCopy() {
        // TODO: вернуть Team с ТЕМ ЖЕ списком (members по ссылке)
        return null;
    }

    public Team deepCopy() {
        // TODO: вернуть Team с НОВЫМ списком (new ArrayList<>(members))
        return null;
    }

    public List<String> getMembers() {
        // TODO
        return List.of();
    }
}
