package training.luxoft.ro.myapp.helpers;

public class Entities {

    public static class UserEntity {
        public static final String USER_TABLE_NAME = "users";
        public static final String USER_ID = "user_id";
        public static final String USER_NAME = "name";
        public static final String USER_SURNAME = "surname";
        public static final String USER_USERNAME = "username";
        public static final String USER_PASSWORD = "password";
    }

    public static class ToDoEntity {
        public static final String TODO_TABLE_NAME = "todo_items";
        public static final String TODO_ID = "todo_id";
        public static final String TODO_TASK_NAME = "task_name";
        public static final String TODO_PRIORITY = "priority";
        public static final String TODO_DURATION = "duration";
        public static final String TODO_STATUS = "status";
    }
}
