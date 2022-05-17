package at.FH.User;

public enum Role {
    ADMIN{
        @Override
        public String toString() {
            return "Admin";
        }
    },
    ASSISTANT{
        @Override
        public String toString() {
            return "Assistant";
        }
    },
    STUDENT{
        @Override
        public String toString() {
            return "Student";
        }
    },
}



