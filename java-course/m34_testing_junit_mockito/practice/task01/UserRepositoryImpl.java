package m34_testing_junit_mockito.practice.task01;

public class UserRepositoryImpl implements UserRepository{
    @Override
    public String findName(long id) {
        return "Ivan";
    }
}
