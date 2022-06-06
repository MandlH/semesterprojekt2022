package at.FH.General;

import at.FH.Task.Bachelor;
import at.FH.Task.Master;
import at.FH.Task.Project;
import at.FH.User.LoggedInUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Check {

    public boolean checkPassword(char[] password, String input){
        String p = new String(password);
        return input.equals(p);
    }

    private final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public boolean checkEmailRegex(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public boolean notEmpty(String s){
        return s.isEmpty();
    }

    public boolean checkPositiveProject(LoggedInUser user){
        boolean bool = true;
        for(Project p : user.getStudent().getProjects()){
            if(p.getMark() == 0){
                bool = false;
                break;
            }
            if(p.getMark() < 0 || p.getMark() >= 5){
                bool = false;
                break;
            }
        }
        return bool;
    }

    public boolean checkPositiveBachelor(LoggedInUser user){
        boolean bool = true;
        for(Bachelor b : user.getStudent().getBachelors()){
            if(b.getMark() == 0){
                bool = false;
                break;
            }
            if(b.getMark() < 0 || b.getMark() >= 5){
                bool = false;
                break;
            }
            if(user.getStudent().getProjects().size() <= 0){
                bool = false;
                break;
            }
        }
        return bool;
    }

    public boolean checkPositiveMaster(LoggedInUser user){
        boolean bool = true;
        for(Master m : user.getStudent().getMasters()){
            if (m.getMark() == 0) {
                bool = false;
                break;
            }
            if(user.getStudent().getBachelors().size() <= 0){
                bool = false;
                break;
            }
        }
        return bool;
    }
}
