package User;

/**
 * using the singleton  design pattern for the user manager
 * */
public class UserManager {
    //The only instance of the UserManager object
    private static UserManager instance;
    // The private constructor
    private UserManager() {}
    // static function to get the instance
    public static UserManager getInstance() {
        return instance;
    }
   // Function to insert user into the database
    public boolean insertUser (){
        return true ;
    }
    // Function to check if the user is valid
    public boolean checkUser () {
        return true ;
    }
}
