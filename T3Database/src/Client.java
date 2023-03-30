import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Client {
    private String username;
    private String fullName;
    private String email;
    private String phone;
    private int age;

    private static final Pattern usernamePattern = Pattern.compile("[a-z 0-9]{3,20}");
    private static final Pattern emailPattern = Pattern.compile("[a-z 0-9 \\- \\. \\_ ]+ @ ([a-z 0-9 \\- \\_]+ \\.)+ [a-z]{2,4}",  Pattern.COMMENTS);
    private static final Pattern phonePattern = Pattern.compile("[0-9]{7,15}");

    public Client(String username, String fullName, String email, String phone, int age) {
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.age = age;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((phone == null) ? 0 : phone.hashCode());
        result = prime * result + age;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Client other = (Client) obj;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        if (fullName == null) {
            if (other.fullName != null)
                return false;
        } else if (!fullName.equals(other.fullName))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (phone == null) {
            if (other.phone != null)
                return false;
        } else if (!phone.equals(other.phone))
            return false;
        if (age != other.age)
            return false;
        return true;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) throws Exception {
        //passa o usuario pelo regex usernamePattern
        Matcher usernameMatcher = usernamePattern.matcher(username.toLowerCase());
        //se o matcher retornar true, alteramos o nome de usuario, se não lançamos uma exceção
        if(usernameMatcher.matches()){
            this.username = username.toLowerCase();
        }else{
            throw new Exception("Invalid username");
        }
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws Exception{
        //passa o email pelo regex emailPattern
        Matcher emailMatcher = emailPattern.matcher(email.toLowerCase());
        //se o matcher retornar true, alteramos o email, se não lançamos uma exceção
        if(emailMatcher.matches()){
            this.email = email.toLowerCase();
        }else{
            throw new Exception("Invalid email");
        }
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) throws Exception {
        //passa o numero de telefone pelo regex phonePattern
        Matcher phoneMatcher = phonePattern.matcher(phone);
        //se o matcher retornar true, alteramos o phone, se não lançamos uma exceção
        if(phoneMatcher.matches()){
            this.phone = phone;
        }else{
            throw new Exception("Invalid Phone Number");
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Client [username=" + username + ", fullName=" + fullName + ", email=" + email + ", phone=" + phone
                + ", age=" + age + "]";
    }
    
}
